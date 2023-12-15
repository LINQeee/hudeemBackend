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
    public ResponseEntity<?> updateUserBio(@RequestBody UserDTO userDTO) {
        userService.updateUserBio(userDTO);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/summary")
    public ResponseEntity<?> getUserInfo(@RequestParam Long id) {
        return ResponseEntity.ok(userService.getSummary(id));
    }

    @PostMapping("/user-login-psw")
    public ResponseEntity<?> userLoginPsw(@RequestBody UserDTO userDTO) {

        return ResponseEntity.ok(userService.checkLoginAbilityWithPsw(userDTO));
    }

    @PostMapping("/user-login-code")
    public ResponseEntity<?> userLoginCode(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.checkLoginAbilityWithCode(userDTO));
    }
}
