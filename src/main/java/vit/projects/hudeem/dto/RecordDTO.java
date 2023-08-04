package vit.projects.hudeem.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordDTO {
    private Long id;
    private double currentWeight;
    private LocalDate date;
    private Long userId;
}
