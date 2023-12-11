package vit.projects.hudeem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vit.projects.hudeem.dto.GoalDTO;
import vit.projects.hudeem.services.GoalService;

@RestController
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @PostMapping("/goal")
    public ResponseEntity<?> createGoal(@RequestBody GoalDTO goalDTO) {
        return ResponseEntity.ok(goalService.saveGoal(goalDTO));
    }

    @DeleteMapping("/goal")
    public ResponseEntity<?> deleteGoal(@RequestParam Long id) {
        return ResponseEntity.ok(goalService.deleteGoal(id));
    }

    @GetMapping("/goal")
    public ResponseEntity<?> getGoal(@RequestParam Long id) {
        return ResponseEntity.ok(goalService.getGoalDTOById(id));
    }
}
