package ca.cal.tp2.repository;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.CD;

public interface CdRepository {
    void save(CD cd) throws DatabaseException;
    CD find(long id) throws DatabaseException;
    CD findByTitle(String title) throws DatabaseException;
    CD findByArtist(String artist) throws DatabaseException;
}
