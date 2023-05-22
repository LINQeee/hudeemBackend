package vit.projects.hudeem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RecordDTO {
    private Long id;
    private double currentWeight;
    private Date date;
    private Long userId;
}
