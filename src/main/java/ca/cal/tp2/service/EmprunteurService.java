package ca.cal.tp2.service;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Emprunt;
import ca.cal.tp2.modele.Emprunteur;
import ca.cal.tp2.repository.DocumentRepositoryJPA;
import ca.cal.tp2.repository.EmprunteurRepositoryJPA;

import java.util.List;

public class EmprunteurService {

    private final EmprunteurRepositoryJPA emprunteurRepository;
    private final DocumentRepositoryJPA documentRepository;

    public EmprunteurService(EmprunteurRepositoryJPA emprunteurRepository,
                             DocumentRepositoryJPA documentRepository) {
        this.emprunteurRepository = emprunteurRepository;
        this.documentRepository = documentRepository;
    }

    public void emprunteDocument(long emprunteurId, long documentId) throws DatabaseException {
        Document document = documentRepository.find(documentId);
        if (document == null) {
            throw new DatabaseException("Document non trouvé.");
        }

        try {
            emprunteurRepository.emprunte(document, emprunteurId);
            System.out.println("Document emprunté avec succès.");
        } catch (DatabaseException e) {
            throw new DatabaseException("Erreur lors de l'emprunt du document.", e);
        }
    }

    public List<Emprunt> getRapportsEmprunts(long emprunteurId) throws DatabaseException {
        try {
            Emprunteur emprunteur = emprunteurRepository.find(emprunteurId);
            List <Emprunt> emprunts = emprunteur.getEmprunts();
            if (emprunts.isEmpty()) {
                throw new DatabaseException("Aucun emprunt trouvé pour cet emprunteur.");
            }
            return emprunts;
        } catch (Exception e) {
            throw new DatabaseException("Erreur lors de la récupération des emprunts de l'emprunteur", e);
        }
    }

    public void retourneDocument(Document document) {}
    public void payeAmende(double amende){}
}
