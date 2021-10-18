package exceptions;

public class LackOfPermissions extends Exception {

    public LackOfPermissions(final String message) {
        super(message);
    }
}
