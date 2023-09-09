package vit.projects.hudeem.dto;

import lombok.Builder;
import lombok.Data;
import vit.projects.hudeem.utils.InputFieldType;

@Data
@Builder
public class ErrorDTO {
    private String type;
    private String msg;
    private InputFieldType inputFieldType;
}
