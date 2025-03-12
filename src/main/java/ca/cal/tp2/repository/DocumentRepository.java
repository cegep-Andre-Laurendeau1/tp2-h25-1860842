package ca.cal.tp2.repository;

import ca.cal.tp2.exception.*;
import ca.cal.tp2.modele.CD;
import ca.cal.tp2.modele.DVD;
import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Livre;

public interface DocumentRepository {
    // Méthode des documents
    void save (Document document) throws DatabaseException;
    Document find(long id) throws EntityDoesNotExist;

    // Méthodes des CDs
    void saveCd(CD cd) throws DatabaseException;
    CD findCd(long id) throws EntityDoesNotExist;
    CD findCdByTitle(String title) throws EntityDoesNotExist;
    CD findCdByArtist(String artist) throws EntityDoesNotExist;

    // Méthodes des DVDs
    void saveDvd(DVD dvd) throws DatabaseException;
    DVD findDvd(long id) throws EntityDoesNotExist;
    DVD findDvdByTitle(String titre) throws EntityDoesNotExist;
    DVD findDvdByDirector(String director) throws EntityDoesNotExist;

    // Méthodes des Livres
    void saveLivre(Livre livre) throws DatabaseException;
    Livre findLivre(long id) throws EntityDoesNotExist;
    Livre findLivreByAuthor(String author) throws EntityDoesNotExist;
    Livre findLivreByTitle(String title) throws EntityDoesNotExist;
    Livre findLivreByYear(long year) throws EntityDoesNotExist;
}
