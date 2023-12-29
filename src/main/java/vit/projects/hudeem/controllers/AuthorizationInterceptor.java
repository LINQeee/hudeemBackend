package vit.projects.hudeem.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import vit.projects.hudeem.dto.UserDTO;
import vit.projects.hudeem.services.UserService;

@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        UserDTO userDTO = UserDTO.builder().ip(ipAddress).authToken(request.getHeader("Authorization")).email(request.getHeader("email")).build();
        userService.checkLoginAbilityWithToken(userDTO);
        return true;
    }
}
