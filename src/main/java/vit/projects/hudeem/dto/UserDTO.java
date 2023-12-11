package vit.projects.hudeem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private char gender;
    private double height;
    private int age;
    private String code;
    private LocalDate expireAuthorisationDate;
    private boolean isRememberMe;
    private String ip;

    public UserDTO(String email, String password){
        this.email = email;
        this.password = password;
    }
}
