package ca.cal.tp2.exception;

public class EntityDoesNotExist extends RuntimeException {
  public EntityDoesNotExist(String message) {
    super(message);
  }
}
