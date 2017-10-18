package exceptions;

import java.io.IOException;

public class DuplicateUnitId extends IOException {
    public DuplicateUnitId (String message) {
        super(message);
    }
}
