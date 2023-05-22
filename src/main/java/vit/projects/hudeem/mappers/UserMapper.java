package vit.projects.hudeem.mappers;

import org.mapstruct.Mapper;
import vit.projects.hudeem.dto.UserDTO;
import vit.projects.hudeem.entities.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity fromDTO(UserDTO userDTO);
    UserDTO toDTO(UserEntity entity);
}
