package exceptions;

import java.io.IOException;

public class InvalidTerrainTypeException extends IOException {
    public InvalidTerrainTypeException(String message) {
        super(message);
    }
}
