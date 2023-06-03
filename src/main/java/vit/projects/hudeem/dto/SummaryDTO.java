package vit.projects.hudeem.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class SummaryDTO {
    private UserDTO userDTO;
    private List<RecordDTO> recordDTOList;
}
