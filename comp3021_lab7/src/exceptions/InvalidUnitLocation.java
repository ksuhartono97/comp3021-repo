package exceptions;

import java.io.IOException;

public class InvalidUnitLocation extends IOException {
    public InvalidUnitLocation (String message) {
        super(message);
    }
}
