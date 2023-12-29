package vit.projects.hudeem.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vit.projects.hudeem.dto.UserDTO;
import vit.projects.hudeem.services.CodeService;
import vit.projects.hudeem.utils.ControllerUtils;

@RestController
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/send-code")
    public ResponseEntity<String> sendCode(@RequestBody String email) {
        codeService.generateAndSendCode(email);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/check-code")
    public ResponseEntity<?> checkCode(@RequestParam String code, @RequestParam boolean rememberMe, @RequestHeader String email) {
        UserDTO userDTO = UserDTO.builder()
                .code(code)
                .email(email)
                .ip(ControllerUtils.getIpAddress(request))
                .isRememberMe(rememberMe)
                .build();
        return codeService.checkCode(userDTO);
    }
}
