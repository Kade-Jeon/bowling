package Exceptions;

import java.util.InputMismatchException;

public class FirstInputException extends InputMismatchException {
    public FirstInputException(String message) {
        super(message);
    }
}
