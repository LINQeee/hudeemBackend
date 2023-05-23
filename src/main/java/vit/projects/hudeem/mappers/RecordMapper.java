package vit.projects.hudeem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import vit.projects.hudeem.dto.RecordDTO;
import vit.projects.hudeem.entities.RecordEntity;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.repositories.UserRepository;

@Mapper(componentModel = "spring")
public abstract class RecordMapper {

    @Autowired
    UserRepository userRepository;
    @Mapping(target = "user", expression = "java(getUser(recordDTO.getUserId()))")
    public abstract RecordEntity fromDTO(RecordDTO recordDTO);
    @Mapping(target = "userId", source = "user.id")
    public abstract RecordDTO toDTO(RecordEntity entity);

    public UserEntity getUser(Long id) {
        return userRepository.findById(id).get();
    }
}
