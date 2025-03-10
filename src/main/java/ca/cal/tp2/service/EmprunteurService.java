package ca.cal.tp2.service;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Emprunt;
import ca.cal.tp2.modele.Emprunteur;
import ca.cal.tp2.modele.Livre;
import ca.cal.tp2.repository.DocumentRepositoryJPA;
import ca.cal.tp2.repository.EmprunteurRepositoryJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmprunteurService {

    private final EmprunteurRepositoryJPA emprunteurRepository;
    private final DocumentRepositoryJPA documentRepository;

    public EmprunteurService(EmprunteurRepositoryJPA emprunteurRepository, DocumentRepositoryJPA documentRepository) {
        this.emprunteurRepository = emprunteurRepository;
        this.documentRepository = documentRepository;
    }
    // save gere les requetes CREATE or UPDATE
    Emprunteur emprunteur;

    public void emprunteDocument(long emprunteurId, long documentId) throws DatabaseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp2.pu");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Emprunteur emprunteur = em.find(Emprunteur.class, emprunteurId);
            if (emprunteur == null) {
                throw new RuntimeException("Emprunteur introuvable");
            }

            Document document = em.find(Document.class, documentId);
            if (document == null) {
                throw new RuntimeException("Document introuvable");
            }

            if (document.getNbExemplaires() <= 0) {
                throw new RuntimeException("Aucun exemplaire disponible pour ce document");
            }

            Emprunt nouvelEmprunt = new Emprunt();
            nouvelEmprunt.setEmprunteur(emprunteur);
            nouvelEmprunt.setDocument(document);

            em.persist(nouvelEmprunt);

            document.setNbExemplaires(document.getNbExemplaires() - 1);
            em.merge(document);

            emprunteur.getEmprunts().add(nouvelEmprunt);
            em.merge(emprunteur);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erreur lors de l'emprunt du document", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }


    }
    public void retourneDocument(Document document) {}
    public void payeAmende(double amende){}
    public void getRapportHistoriqueEmprunt(){}

    public Emprunteur getEmprunteur(){
        return null;
    }
    public Livre getLivre(){
        return null;
    }

    public void login(String email, String password) {
    }
}
