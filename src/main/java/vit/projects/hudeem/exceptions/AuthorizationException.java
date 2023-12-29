package vit.projects.hudeem.exceptions;


import lombok.Getter;

@Getter
public class AuthorizationException extends RuntimeException {

    public AuthorizationException(String msg) {
        super(msg);
    }

}
