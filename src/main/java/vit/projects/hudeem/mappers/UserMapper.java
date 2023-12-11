package vit.projects.hudeem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import vit.projects.hudeem.dto.UserDTO;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.services.HashService;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    public HashService hashService;

    @Mapping(target = "passwordHash", expression = "java(hashService.getHashFrom(userDTO.getPassword()))")
    @Mapping(target = "codeHash", expression = "java(hashService.getHashFrom(userDTO.getCode()))")
    public abstract UserEntity fromDTO(UserDTO userDTO);
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "code", ignore = true)
    public abstract UserDTO toDTO(UserEntity entity);
}
