package ca.cal.tp2.exception;

import jakarta.persistence.RollbackException;

public class DuplicateEntityException extends Exception {
    public DuplicateEntityException(RollbackException e) {
    }
}
