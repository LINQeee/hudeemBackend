package vit.projects.hudeem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vit.projects.hudeem.dto.UserDTO;
import vit.projects.hudeem.services.CodeService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    @PostMapping("/send-code")
    public ResponseEntity<?> sendCode(@RequestBody String email) {
        codeService.generateAndSendCode(email);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/check-code")
    public ResponseEntity<?> checkCode(@RequestBody UserDTO userDTO) {
        return codeService.checkCode(userDTO);
    }
}
