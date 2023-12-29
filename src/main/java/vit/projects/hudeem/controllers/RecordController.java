package vit.projects.hudeem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vit.projects.hudeem.dto.RecordDTO;
import vit.projects.hudeem.repositories.RecordRepository;
import vit.projects.hudeem.services.RecordService;
import vit.projects.hudeem.services.UserService;

@RestController
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;
    private final RecordRepository recordRepository;
    private final UserService userService;

    @PostMapping("/auth/record")
    public ResponseEntity<String> saveRecord(@RequestBody RecordDTO recordDTO) {
        recordService.saveRecord(recordDTO);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/auth/record")
    public ResponseEntity<String> deleteRecord(@RequestHeader String email, @RequestParam long id) {
        if (!userService.getUserByEmail(email).getGoals().contains(recordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found")).getGoal()))
            throw new RuntimeException("Record not found");
        return ResponseEntity.ok(recordService.deleteRecord(id));
    }
}
