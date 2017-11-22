package exceptions;

import java.io.IOException;

public class DuplicatePlayerNameException extends IOException {
    public DuplicatePlayerNameException(String message) {
        super(message);
    }
}
