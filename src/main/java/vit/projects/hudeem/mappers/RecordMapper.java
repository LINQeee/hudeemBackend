package vit.projects.hudeem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import vit.projects.hudeem.dto.RecordDTO;
import vit.projects.hudeem.entities.GoalEntity;
import vit.projects.hudeem.entities.RecordEntity;
import vit.projects.hudeem.repositories.GoalRepository;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Mapper(componentModel = "spring")
public abstract class RecordMapper {

    @Autowired
    public GoalRepository goalRepository;
    @Mapping(target = "goal", expression = "java(getGoal(recordDTO.getGoalId()))")
    public abstract RecordEntity fromDTO(RecordDTO recordDTO);
    @Mapping(target = "goalId", source = "goal.id")
    public abstract RecordDTO toDTO(RecordEntity entity);

    public GoalEntity getGoal(Long id) {
        return goalRepository.findById(id).get();
    }
}
