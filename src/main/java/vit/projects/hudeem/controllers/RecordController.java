package vit.projects.hudeem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @DeleteMapping("/record")
    public ResponseEntity<?> deleteRecord(@RequestParam long id) {
        return ResponseEntity.ok(recordService.deleteRecord(id));
    }


}
