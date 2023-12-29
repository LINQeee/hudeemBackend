package vit.projects.hudeem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IpDTO {
    private Long id;
    private Long userId;
    private String ip;
}
