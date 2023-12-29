package vit.projects.hudeem.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vit.projects.hudeem.dto.ErrorDTO;
import vit.projects.hudeem.exceptions.AuthorizationException;
import vit.projects.hudeem.exceptions.ValidationException;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity.internalServerError().body(ErrorDTO.builder()
                .type("UNEXPECTED")
                .msg(ex.getMessage())
                .build());
    }

    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<Object> handleException(ValidationException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.internalServerError().body(ErrorDTO.builder()
                .type("VALIDATION")
                .msg(ex.getMessage())
                .inputFieldType(ex.getFieldType())
                .build());
    }

    @ExceptionHandler(value = AuthorizationException.class)
    protected ResponseEntity<Object> handleException(AuthorizationException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.internalServerError().body(ErrorDTO.builder()
                .type("AUTHORIZATION")
                .msg(ex.getMessage())
                .build());
    }
}
