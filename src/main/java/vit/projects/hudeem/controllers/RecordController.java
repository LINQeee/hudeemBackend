package vit.projects.hudeem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vit.projects.hudeem.dto.RecordDTO;
import vit.projects.hudeem.entities.RecordEntity;
import vit.projects.hudeem.mappers.RecordMapper;
import vit.projects.hudeem.services.RecordService;

@RestController
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;
    private final RecordMapper recordMapper;

    @PostMapping("/record")
    public ResponseEntity<?> saveRecord(@RequestBody RecordDTO recordDTO) {
        RecordEntity recordEntity = recordMapper.fromDTO(recordDTO);
        RecordEntity saved = recordService.saveRecord(recordEntity, recordDTO.getUserId());
        RecordDTO savedDTO = recordMapper.toDTO(saved);
        savedDTO.setUserId(saved.getUser().getId());
        return ResponseEntity.ok(savedDTO);
    }
}
