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

    public void generateAndSendCode(String email) {
        String newCode = generateCode();
        emailService.sendEmail(email, "Ваш код подтверждения","", generateCodeEmail(newCode));

        String codeHash = hashService.getHashFrom(newCode);
        UserEntity userEntity = userRepository.findByEmail(email).get();
        userEntity.setCodeHash(codeHash);
        userRepository.save(userEntity);
    }

    public ResponseEntity<?> checkCode(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findByEmail(userDTO.getEmail()).get();

        String codeHash = hashService.getHashFrom(userDTO.getCode());
        if (codeHash.equals(userEntity.getCodeHash())) {

            if (userDTO.isRememberMe())
                userEntity.setExpireAuthorisationDate(LocalDate.now().plusDays(7));
            else
                userEntity.setExpireAuthorisationDate(LocalDate.now());
            userRepository.save(userEntity);
            return ResponseEntity.ok("success");
        } else return ResponseEntity.status(500).body("Неверный код");
    }

    private String generateCode() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    @SneakyThrows
    public String generateCodeEmail(String code) {
        InputStream inputStream = getClass().getResourceAsStream("/emails/codeVerificationEmail.html");
        return fileService.readFromInputStream(inputStream).replace("CODE", code);
    }


}
