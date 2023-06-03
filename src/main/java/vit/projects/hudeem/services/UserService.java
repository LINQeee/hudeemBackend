package vit.projects.hudeem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vit.projects.hudeem.dto.RecordDTO;
import vit.projects.hudeem.dto.SummaryDTO;
import vit.projects.hudeem.dto.UserDTO;
import vit.projects.hudeem.entities.RecordEntity;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.mappers.RecordMapper;
import vit.projects.hudeem.mappers.UserMapper;
import vit.projects.hudeem.repositories.UserRepository;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RecordMapper recordMapper;

    public UserDTO saveUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.fromDTO(userDTO);
        UserEntity saved = userRepository.save(userEntity);
        return userMapper.toDTO(saved);
    }

    public UserEntity getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public SummaryDTO getSummary(Long id) {
        UserEntity userEntity = getUser(id);
        UserDTO userDTO = userMapper.toDTO(userEntity);
        List<RecordDTO> recordDTOList = userEntity.getRecords()
                .stream()
                .map(recordEntity -> {
                    var dto = recordMapper.toDTO(recordEntity);
                    dto.setUserId(id);
                    return dto;
                })
                .sorted(Comparator.comparing(RecordDTO::getDate))
                .toList();
        return SummaryDTO.builder().userDTO(userDTO).recordDTOList(recordDTOList).build();
    }

}
