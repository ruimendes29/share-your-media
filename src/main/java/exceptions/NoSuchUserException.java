package exceptions;

public class NoSuchUserException extends Exception {

    public NoSuchUserException() {
        super("There's no user registered with that email");
    }

}
