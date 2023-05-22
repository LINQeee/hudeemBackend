package vit.projects.hudeem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vit.projects.hudeem.dto.UserDTO;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.mappers.UserMapper;
import vit.projects.hudeem.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity getUser(Long id) {
        return userRepository.findById(id).get();
    }
}
