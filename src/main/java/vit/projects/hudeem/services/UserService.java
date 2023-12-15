package vit.projects.hudeem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vit.projects.hudeem.dto.*;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.exceptions.AuthorizationException;
import vit.projects.hudeem.exceptions.ValidationException;
import vit.projects.hudeem.mappers.GoalMapper;
import vit.projects.hudeem.mappers.IpMapper;
import vit.projects.hudeem.mappers.RecordMapper;
import vit.projects.hudeem.mappers.UserMapper;
import vit.projects.hudeem.repositories.IpRepository;
import vit.projects.hudeem.repositories.UserRepository;
import vit.projects.hudeem.utils.InputFieldType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RecordMapper recordMapper;
    private final HashService hashService;
    private final GoalMapper goalMapper;
    private final IpMapper ipMapper;
    private final IpRepository ipRepository;

    public void updateUserBio(UserDTO userDTO) {
        checkIsUserAbleToLogin(userDTO);
        UserEntity userEntity = userRepository.findByEmail(userDTO.getEmail()).get();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setGender(userDTO.getGender());
        userEntity.setHeight(userDTO.getHeight());
        userEntity.setAge(userDTO.getAge());
        userRepository.save(userEntity);
    }

    public String checkIsUserAbleToLogin(UserDTO userDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(userDTO.getEmail());

        boolean isPasswordOrLoginCorrect = userEntityOptional.isEmpty() || (userDTO.getPassword() != null ?
                !hashService.getHashFrom(userDTO.getPassword()).equals(userEntityOptional.get().getPasswordHash()) :
                !userDTO.getCode().equals(userEntityOptional.get().getCodeHash()));

        if (isPasswordOrLoginCorrect)
            throw new AuthorizationException("Неправильная почта или пароль");
        UserEntity userEntity = userEntityOptional.get();
        if (userEntity.getExpireAuthorisationDate() == null || LocalDate.now().isAfter(userEntity.getExpireAuthorisationDate()))
            throw new AuthorizationException("Срок авторизации истёк");
        if (userEntity.getIps() == null || !userEntity.containsIp(userDTO.getIp()))
            throw new AuthorizationException("Неавторизованное устройство");

        return "success";
    }

    public UserDTO saveUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent())
            throw new ValidationException("Этот email занят", InputFieldType.EMAIL);

        UserEntity saved = userRepository.save(userMapper.fromDTO(userDTO));
        IpDTO ipDTO = new IpDTO(saved.getId(), userDTO.getIp());
        ipRepository.save(ipMapper.fromDTO(ipDTO));
        return userMapper.toDTO(saved);
    }

    public UserEntity getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public SummaryDTO getSummary(Long id) {
        UserEntity userEntity = getUser(id);
        UserDTO userDTO = userMapper.toDTO(userEntity);
        List<IpDTO> ipDTOList = userEntity.getIps()
                .stream()
                .map(ipEntity -> {
                    var dto = ipMapper.toDTO(ipEntity);
                    dto.setUserId(id);
                    return dto;
                })
                .toList();
        List<GoalDTO> goalDTOList = userEntity.getGoals()
                .stream()
                .map(goalEntity -> {
                    var dto = goalMapper.toDTO(goalEntity);
                    dto.setUserId(id);
                    return dto;
                }).toList();
        return SummaryDTO.builder().userDTO(userDTO).ipDTOList(ipDTOList).goalDTOList(goalDTOList).build();
    }
}
