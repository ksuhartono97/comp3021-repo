package exceptions;

import java.io.IOException;

public class InvalidUnitLocationException extends IOException {
    public InvalidUnitLocationException(String message) {
        super(message);
    }
}
