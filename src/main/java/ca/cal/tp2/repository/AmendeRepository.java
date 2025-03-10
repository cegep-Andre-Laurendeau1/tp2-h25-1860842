package ca.cal.tp2.repository;

import ca.cal.tp2.exception.DatabaseException;

public interface AmendeRepository {
    void calculMontant(double amende) throws DatabaseException;

    void updateStatus(long id) throws DatabaseException;
}
