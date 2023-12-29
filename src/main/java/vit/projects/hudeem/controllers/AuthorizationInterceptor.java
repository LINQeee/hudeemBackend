package vit.projects.hudeem.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import vit.projects.hudeem.dto.UserDTO;
import vit.projects.hudeem.services.UserService;
import vit.projects.hudeem.utils.ControllerUtils;

@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        UserDTO userDTO = UserDTO.builder().ip(ControllerUtils.getIpAddress(request)).authToken(request.getHeader("Authorization")).email(request.getHeader("email")).build();
        userService.checkLoginAbilityWithToken(userDTO);
        return true;
    }
}
