package vit.projects.hudeem.dto;

import lombok.Data;

@Data
public class IpDTO {
    private Long id;
    private Long userId;
    private String ip;

    public IpDTO(Long userId, String ip) {
        this.userId = userId;
        this.ip = ip;
    }
}
