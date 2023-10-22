package vit.projects.hudeem.exceptions;


import lombok.Getter;
import vit.projects.hudeem.utils.InputFieldType;

@Getter
public class AuthorizationException extends RuntimeException {

    public AuthorizationException(String msg) {
        super(msg);
    }

}
