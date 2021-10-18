package exceptions;

public class NotSecureEnoughPassword extends Exception {

    public NotSecureEnoughPassword(final String message) {
        super(message);
    }
}
