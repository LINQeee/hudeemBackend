package vit.projects.hudeem.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private char gender;
    private double height;
    private double initialWeight;
    private double goalWeight;
    private double progress;
}
