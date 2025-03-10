package ca.cal.tp2.repository;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.DVD;

public interface DvdRepository {
    void save(DVD dvd) throws DatabaseException;
    DVD find(long id) throws DatabaseException;
    DVD findByTitle(String titre) throws DatabaseException;
    DVD findByDirector(String director) throws DatabaseException;
}
