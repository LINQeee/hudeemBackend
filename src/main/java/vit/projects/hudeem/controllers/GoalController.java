package vit.projects.hudeem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vit.projects.hudeem.dto.GoalDTO;
import vit.projects.hudeem.services.GoalService;
import vit.projects.hudeem.services.UserService;

@RestController
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;
    private final UserService userService;

    @PostMapping("/auth/goal")
    public ResponseEntity<String> createGoal(@RequestHeader String email, @RequestBody GoalDTO goalDTO) {
        goalDTO.setUserId(userService.getUserByEmail(email).getId());
        goalService.saveGoal(goalDTO);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/auth/goal/{id}")
    public ResponseEntity<String> deleteGoal(@RequestHeader String email, @PathVariable Long id) {
        if (!userService.getUserByEmail(email).containsGoal(id))
            throw new RuntimeException("Goal not found");
        goalService.deleteGoal(id);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/auth/goal/{id}")
    public ResponseEntity<?> getGoal(@RequestHeader String email, @PathVariable Long id) {
        if (!userService.getUserByEmail(email).containsGoal(id))
            throw new RuntimeException("Goal not found");
        return ResponseEntity.ok(goalService.getGoalDTOById(id).orElseThrow(() -> new RuntimeException("Goal not found")));
    }
}
