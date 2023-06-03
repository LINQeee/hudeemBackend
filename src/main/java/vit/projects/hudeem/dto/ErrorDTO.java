package vit.projects.hudeem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    private String type;
    private String msg;
}
