package vit.projects.hudeem.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.repositories.UserRepository;


import java.io.InputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class CodeServiceTest {

    @MockBean
    private FileService fileService;
    @MockBean
    private HashService hashService;
    @MockBean
    private EmailService emailService;
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private CodeService codeService;

    @Test
    void generateAndSendCode() {
        String email = "email";


        Mockito.when(hashService.getHashFrom(anyString())).thenReturn("123456");
        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.of(new UserEntity()));
        Mockito.when(fileService.readFromInputStream(any(InputStream.class))).thenReturn("htmlCode");
        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);

        codeService.generateAndSendCode(email);

        verify(userRepository).save(captor.capture());
        Mockito.verify(emailService).sendEmail(email, "Ваш код подтверждения", "", "htmlCode");

        Assertions.assertThat(captor.getValue().getCodeHash()).isEqualTo("123456");
    }

    @Test
    void checkCode_Positive_Result_Remember_Me() {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setEmail("email");
//        userEntity.setCodeHash("123456");
//        userEntity.setPasswordHash("123456");
//
//        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.of(userEntity));
//        Mockito.when(hashService.getHashFrom("123456")).thenReturn("123456");
//
//
//        Mockito.when(userRepository.save(any(UserEntity.class))).thenReturn(new UserEntity());
//        UserDTO userDTO = new UserDTO();
//        userDTO.setCode("123456");
//        userDTO.setRememberMe(true);
//        userDTO.setEmail("email");
//
//        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);
//        Mockito.when(userRepository.save(captor.capture())).thenReturn(new UserEntity());
//
//        ResponseEntity responseEntity = codeService.checkCode(userDTO);
//        assertEquals(responseEntity, ResponseEntity.ok(new UserDTO("email", "123456")));
//        assertEquals(captor.getValue().getExpireAuthorisationDate(), LocalDate.now().plusDays(30));
    }

    @Test
    void checkCode_Positive_Result_Not_Remember_Me() {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setEmail("email");
//        userEntity.setCodeHash("123456");
//        userEntity.setPasswordHash("123456");
//
//        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.of(userEntity));
//        Mockito.when(hashService.getHashFrom("123456")).thenReturn("123456");
//
//
//        Mockito.when(userRepository.save(any(UserEntity.class))).thenReturn(new UserEntity());
//        UserDTO userDTO = new UserDTO();
//        userDTO.setCode("123456");
//        userDTO.setRememberMe(false);
//        userDTO.setEmail("email");
//
//        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);
//        Mockito.when(userRepository.save(captor.capture())).thenReturn(new UserEntity());
//
//        ResponseEntity responseEntity = codeService.checkCode(userDTO);
//        assertEquals(responseEntity, ResponseEntity.ok(new UserDTO("email", "123456")));
//        assertEquals(captor.getValue().getExpireAuthorisationDate(), LocalDate.now());
    }

    @Test
    void checkCode_Negative_Result_Wrong_Code() {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setEmail("email");
//        userEntity.setCodeHash("123456");
//
//        Mockito.when(userRepository.findByEmail("email")).thenReturn(Optional.of(userEntity));
//        Mockito.when(hashService.getHashFrom("wrongCode")).thenReturn("wrongCode");
//
//
//        Mockito.when(userRepository.save(any(UserEntity.class))).thenReturn(new UserEntity());
//        UserDTO userDTO = new UserDTO();
//        userDTO.setCode("wrongCode");
//        userDTO.setEmail("email");
//
//        ResponseEntity responseEntity = codeService.checkCode(userDTO);
//        assertEquals(responseEntity, ResponseEntity.status(500).body("Неверный код"));
    }

    @Test
    void generateCodeEmail() {
        String fileContent = "<html><h1>CODE</h1></html>";
        Mockito.when(fileService.readFromInputStream(any(InputStream.class))).thenReturn(fileContent);

        String code = "123456";
        String generateEmail = codeService.generateCodeEmail(code);
        String expectedEmail = "<html><h1>" + code + "</h1></html>";

        assertEquals(expectedEmail, generateEmail);
    }
}