package vit.projects.hudeem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import vit.projects.hudeem.dto.IpDTO;
import vit.projects.hudeem.entities.IpEntity;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.repositories.UserRepository;

@Mapper(componentModel = "spring")
public abstract class IpMapper {
    @Autowired
    UserRepository userRepository;
    @Mapping(target = "user", expression = "java(getUser(ipDTO.getUserId()))")
    public abstract IpEntity fromDTO(IpDTO ipDTO);
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "id", ignore = true)
    public abstract IpDTO toDTO(IpEntity entity);

    public UserEntity getUser(Long id) {
        return userRepository.findById(id).get();
    }
}
