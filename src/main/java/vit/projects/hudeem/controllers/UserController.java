package vit.projects.hudeem.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vit.projects.hudeem.dto.SummaryDTO;
import vit.projects.hudeem.dto.UserDTO;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.services.UserService;
import vit.projects.hudeem.utils.ControllerUtils;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/user")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        userDTO.setIp(ControllerUtils.getIpAddress(request));
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/auth/user-bio")
    public ResponseEntity<String> updateUserBio(@RequestHeader String email, @RequestBody UserDTO userDTO) {
        userDTO.setEmail(email);
        userService.updateUserBio(userDTO);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/auth/summary/{id}")
    public ResponseEntity<SummaryDTO> getUserInfo(@RequestHeader String email, @PathVariable Long id) {
        Optional<UserEntity> userEntity = Optional.ofNullable(userService.getUserByEmail(email));
        if (userEntity.isEmpty() || !userEntity.get().getId().equals(id))
            throw new RuntimeException("Wrong user");
        return ResponseEntity.ok(userService.getSummary(id));
    }

    @GetMapping("/user-login-psw")
    public ResponseEntity<String> userLoginPsw(@RequestParam String email, @RequestParam String password) {
        UserDTO userDTO = UserDTO.builder().email(email).password(password).build();
        return ResponseEntity.ok(userService.checkLoginAbilityWithPsw(userDTO));
    }
}
