package exceptions;

        import java.io.IOException;

// TODO 4: define and throw custom exceptions
// For each custom exception class
// Just define a constructor that takes an error message
// and calls the constructor of superclass with the error message.
public class InvalidUnitType extends IOException {
    public InvalidUnitType (String message) {
        super(message);
    }
}