package vit.projects.hudeem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vit.projects.hudeem.dto.RecordDTO;
import vit.projects.hudeem.services.RecordService;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class RecordController {
    private final RecordService recordService;

    @PostMapping("/record")
    public ResponseEntity<?> saveRecord(@RequestBody RecordDTO recordDTO) {
        return ResponseEntity.ok(recordService.saveRecord(recordDTO));
    }
}
