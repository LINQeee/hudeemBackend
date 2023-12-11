package vit.projects.hudeem.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SummaryDTO {
    private UserDTO userDTO;
    private List<IpDTO> ipDTOList;
    private List<GoalDTO> goalDTOList;
}
