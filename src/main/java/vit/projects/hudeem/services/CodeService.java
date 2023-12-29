package vit.projects.hudeem.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vit.projects.hudeem.dto.UserDTO;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.repositories.UserRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class CodeService {

    private final EmailService emailService;
    private final HashService hashService;
    private final UserRepository userRepository;
    private final FileService fileService;
    private final IpService ipService;

    public void generateAndSendCode(String email) {
        String newCode = generateCode();
        emailService.sendEmail(email, "Ваш код подтверждения", "", generateCodeEmail(newCode));

        String codeHash = hashService.getHashFrom(newCode);
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found when sending email to: " + email));
        userEntity.setCodeHash(codeHash);
        userRepository.save(userEntity);
    }

    public ResponseEntity<?> checkCode(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findByEmail(userDTO.getEmail()).orElseThrow(() -> new RuntimeException("User with id " + userDTO.getId() + " not found when checking code"));

        String codeHash = hashService.getHashFrom(userDTO.getCode());
        if (codeHash.equals(userEntity.getCodeHash())) {
            userEntity.setExpireAuthorisationDate(userDTO.isRememberMe() ? LocalDate.now().plusDays(30) : LocalDate.now());
            userRepository.save(userEntity);
            ipService.saveNewIp(userEntity.getId(), userDTO.getIp());
            return ResponseEntity.ok(UserDTO.builder().email(userEntity.getEmail()).authToken(userEntity.getAuthTokenHash()).build());
        } else return ResponseEntity.status(500).body("Неверный код");
    }

    private String generateCode() {
        int number = new Random().nextInt(999999);
        return String.format("%06d", number);
    }

    @SneakyThrows
    public String generateCodeEmail(String code) {
        InputStream inputStream = getClass().getResourceAsStream("/emails/codeVerificationEmail.html");
        return fileService.readFromInputStream(inputStream).replace("CODE", code);
    }
}
