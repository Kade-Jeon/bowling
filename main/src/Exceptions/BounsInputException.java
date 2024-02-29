package Exceptions;

import java.util.InputMismatchException;

public class BounsInputException extends InputMismatchException {
    public BounsInputException(String message) {
        super(message);
    }
}
