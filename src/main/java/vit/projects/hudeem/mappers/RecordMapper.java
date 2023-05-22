package vit.projects.hudeem.mappers;

import org.mapstruct.Mapper;
import vit.projects.hudeem.dto.RecordDTO;
import vit.projects.hudeem.entities.RecordEntity;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    RecordEntity fromDTO(RecordDTO recordDTO);
    RecordDTO toDTO(RecordEntity entity);
}
