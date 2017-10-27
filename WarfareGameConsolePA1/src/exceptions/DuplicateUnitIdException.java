package exceptions;

import java.io.IOException;

public class DuplicateUnitIdException extends IOException {
    public DuplicateUnitIdException(String message) {
        super(message);
    }
}
