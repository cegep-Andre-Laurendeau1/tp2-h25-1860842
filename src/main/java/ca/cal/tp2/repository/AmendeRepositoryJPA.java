package ca.cal.tp2.repository;

import ca.cal.tp2.exception.DatabaseException;
import jakarta.persistence.*;

public class AmendeRepositoryJPA implements AmendeRepository {

    private final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("tp2");

    @Override
    public void calculMontant(double amende) throws DatabaseException {

    }

    @Override
    public void updateStatus(long id) throws DatabaseException {

    }
}
