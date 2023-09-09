package vit.projects.hudeem.exceptions;


import lombok.Getter;
import vit.projects.hudeem.utils.InputFieldType;

@Getter
public class ValidationException extends RuntimeException {

    private final InputFieldType fieldType;

    public ValidationException(String msg, InputFieldType fieldType) {
        super(msg);
        this.fieldType = fieldType;
    }


}
