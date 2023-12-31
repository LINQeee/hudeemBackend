package vit.projects.hudeem.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
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

    @PostMapping("/check-code")
    public ResponseEntity<?> checkCode(@RequestBody UserDTO userDTO) {
        userDTO.setIp(ControllerUtils.getIpAddress(request));
        return codeService.checkCode(userDTO);
    }
}
