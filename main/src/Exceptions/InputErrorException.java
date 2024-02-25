package Exceptions;

import java.util.InputMismatchException;

public class InputErrorException extends InputMismatchException {
    public InputErrorException(String message) {
        super(message);
    }
}
