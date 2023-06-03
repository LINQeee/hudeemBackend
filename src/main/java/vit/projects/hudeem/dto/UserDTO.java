package vit.projects.hudeem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private char gender;
    private double height;
    private double initialWeight;
    private double goalWeight;
    private double progress;
    private LocalDate startDate;
    private double perDay;
    private double perWeek;
    private LocalDate plannedDate;
    private double currentWeight;
    private double weightLost;
    private double weightLeft;
}
