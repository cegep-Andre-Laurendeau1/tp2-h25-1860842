package ca.cal.tp2.repository;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.Livre;

public interface LivreRepository {
    void save(Livre livre) throws DatabaseException;
    Livre find(long id) throws DatabaseException;
    Livre findByAuthor(String author) throws DatabaseException;
    Livre findByTitle(String title) throws DatabaseException;
    Livre findByYear(long year) throws DatabaseException;
}
