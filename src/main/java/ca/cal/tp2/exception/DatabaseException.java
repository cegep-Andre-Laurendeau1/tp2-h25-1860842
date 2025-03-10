package ca.cal.tp2.exception;

public class DatabaseException extends Exception {
    public DatabaseException(Exception e) {
        super(e);
    }
}
