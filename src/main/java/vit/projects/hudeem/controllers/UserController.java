package vit.projects.hudeem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vit.projects.hudeem.dto.UserDTO;
import vit.projects.hudeem.services.UserService;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/user-bio")
    public ResponseEntity<?> updateUserBio(@RequestBody UserDTO userDTO){
        userService.updateUserBio(userDTO);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/summary")
    public ResponseEntity<?> getUserInfo(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getSummary(id));
    }

    @PostMapping("user-login")
    public ResponseEntity<?> userLogin(@RequestBody UserDTO userDTO){
        userService.checkIsUserAbleToLogin(userDTO);
        return ResponseEntity.ok("success");
    }
}
