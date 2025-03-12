package ca.cal.tp2.repository;

import ca.cal.tp2.exception.*;
import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Emprunt;
import ca.cal.tp2.modele.Emprunteur;
import ca.cal.tp2.modele.Prepose;

import java.util.List;

public interface PreposeRepository {
    void save(Prepose p) throws DatabaseException;
    Prepose find(long id) throws EntityDoesNotExist;

    public String entrerNouveauDocument(Document document);
    public String collecteAmende(Emprunteur emprunteur, double montant);
    public void getRapportAmendes();
    public List<Emprunt> getRapportEmprunts();
}
