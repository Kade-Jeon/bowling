package Exceptions;

import java.util.InputMismatchException;

public class SpareInputException extends InputMismatchException {
    public SpareInputException(String message) {
        super(message);
    }
}
