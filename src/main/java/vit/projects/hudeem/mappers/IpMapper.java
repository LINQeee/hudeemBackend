package vit.projects.hudeem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import vit.projects.hudeem.dto.IpDTO;
import vit.projects.hudeem.entities.IpEntity;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.repositories.UserRepository;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Mapper(componentModel = "spring")
public abstract class IpMapper {
    @Autowired
    public UserRepository userRepository;
    @Mapping(target = "user", expression = "java(getUser(ipDTO.getUserId()))")
    public abstract IpEntity fromDTO(IpDTO ipDTO);
    @Mapping(target = "userId", source = "user.id")
    public abstract IpDTO toDTO(IpEntity entity);

    public UserEntity getUser(Long id) {
        return userRepository.findById(id).get();
    }
}
