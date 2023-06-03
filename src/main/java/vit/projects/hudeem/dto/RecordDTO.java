package vit.projects.hudeem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class RecordDTO {
    private Long id;
    private double currentWeight;
    private LocalDate date;
    private Long userId;
}
