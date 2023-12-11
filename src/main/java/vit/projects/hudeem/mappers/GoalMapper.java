package vit.projects.hudeem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import vit.projects.hudeem.dto.GoalDTO;
import vit.projects.hudeem.dto.RecordDTO;
import vit.projects.hudeem.entities.GoalEntity;
import vit.projects.hudeem.entities.RecordEntity;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.repositories.RecordRepository;
import vit.projects.hudeem.repositories.UserRepository;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Mapper(componentModel = "spring")
public abstract class GoalMapper {
    @Autowired
    public RecordMapper recordMapper;
    @Autowired
    public RecordRepository recordRepository;
    @Autowired
    public UserRepository userRepository;

    @Mapping(target = "user", expression = "java(getUser(goalDTO.getUserId()))")
    public abstract GoalEntity fromDTO(GoalDTO goalDTO);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "recordDTOList", expression = "java(getDTOs(entity.getId()))")
    public abstract GoalDTO toDTO(GoalEntity entity);

    public UserEntity getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public List<RecordDTO> getDTOs(Long goalId) {
        List<RecordEntity> recordEntities = recordRepository.findAllByGoalId(goalId).get();

        return recordEntities.stream().map(recordEntity -> {
            var dto = recordMapper.toDTO(recordEntity);
            dto.setGoalId(goalId);
            return dto;
        }).toList();
    }
}
