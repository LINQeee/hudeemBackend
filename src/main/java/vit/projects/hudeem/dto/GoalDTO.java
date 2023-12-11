package vit.projects.hudeem.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GoalDTO {
    private Long id;
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
    private Long userId;
    private List<RecordDTO> recordDTOList;
}
