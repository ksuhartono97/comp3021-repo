package exceptions;

import java.io.IOException;

public class InvalidTerrainType extends IOException {
    public InvalidTerrainType (String message) {
        super(message);
    }
}
