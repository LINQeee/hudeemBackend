package vit.projects.hudeem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vit.projects.hudeem.dto.RecordDTO;
import vit.projects.hudeem.dto.SummaryDTO;
import vit.projects.hudeem.dto.UserDTO;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.mappers.RecordMapper;
import vit.projects.hudeem.mappers.UserMapper;
import vit.projects.hudeem.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final RecordMapper recordMapper;

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = userMapper.fromDTO(userDTO);
        UserEntity saved = userService.saveUser(userEntity);
        UserDTO savedDTO = userMapper.toDTO(saved);
        return ResponseEntity.ok(savedDTO);
    }

    @GetMapping("/summary")
    public ResponseEntity<?> getUserInfo(@RequestParam Long id) {
        UserEntity userEntity = userService.getUser(id);
        UserDTO userDTO = userMapper.toDTO(userEntity);
        List<RecordDTO> recordDTOList = userEntity.getRecords()
                .stream()
                .map(recordEntity -> {
                    var dto = recordMapper.toDTO(recordEntity);
                    dto.setUserId(id);
                    return dto;
                })
                .toList();
        SummaryDTO summaryDTO = SummaryDTO.builder().userDTO(userDTO).recordDTOList(recordDTOList).build();
        return ResponseEntity.ok(summaryDTO);
    }
}
