package ca.cal.tp2.repository;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Emprunt;
import ca.cal.tp2.modele.Emprunteur;

import java.util.List;

public interface EmprunteurRepository {
    void save(Emprunteur emprunteur) throws DatabaseException;
    Emprunteur find(long id) throws DatabaseException;
    void emprunte(Document document, long emprunteurId) throws DatabaseException;
    void retourne(Document document) throws DatabaseException;
    void payeAmende(long id, double montant) throws DatabaseException;
    List<Emprunt> rapportHistoriqueEmprunts(long id) throws DatabaseException;
}
