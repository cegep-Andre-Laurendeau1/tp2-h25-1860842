package ca.cal.tp2.exception;

public class EntityDoesNotExist extends RuntimeException {
    public EntityDoesNotExist(Exception e) {
        super(e);
    }

    public EntityDoesNotExist(String message) {
        super(message);
    }

    public EntityDoesNotExist(String message, Exception e) {
        super(message, e);
    }
}
