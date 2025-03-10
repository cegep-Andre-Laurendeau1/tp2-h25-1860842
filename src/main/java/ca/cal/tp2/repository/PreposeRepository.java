package ca.cal.tp2.repository;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Emprunteur;
import ca.cal.tp2.modele.Prepose;

public interface PreposeRepository {
    void save(Prepose p) throws DatabaseException;
    Prepose find(long id);

    public String entrerNouveauDocument(Document document);
    public String collecteAmende(Emprunteur emprunteur, double montant);
    public void getRapportAmendes();
    public void getRapportEmprunts();
}
