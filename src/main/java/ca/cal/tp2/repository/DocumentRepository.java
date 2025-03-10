package ca.cal.tp2.repository;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.Document;

public interface DocumentRepository {
    void save (Document document) throws DatabaseException;
    Document find(long id) throws DatabaseException;
}
