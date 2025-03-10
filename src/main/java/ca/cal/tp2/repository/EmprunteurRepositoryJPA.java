package ca.cal.tp2.repository;


import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class EmprunteurRepositoryJPA implements EmprunteurRepository {

    private final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("tp2.pu");

    @Override
    public void save(Emprunteur emprunteur) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            if (em.contains(emprunteur)) {
                em.merge(emprunteur);
            } else {
                em.persist(emprunteur);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Emprunteur find(long id) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            Emprunteur emprunteur = em.createQuery(
                            "SELECT e FROM Emprunteur e LEFT JOIN FETCH e.emprunts WHERE e.id = :id", Emprunteur.class)
                    .setParameter("id", id)
                    .getSingleResult();

            if (emprunteur == null) {
                throw new DatabaseException("Emprunteur non trouvé.");
            }

            return emprunteur;
        } catch (Exception e) {
            throw new DatabaseException("Erreur lors de la récupération de l'emprunteur.", e);
        }
    }

    @Override
    public void emprunte(Document document, long emprunteurId) throws DatabaseException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Vérifier si l'emprunteur existe
            Emprunteur emprunteur = em.find(Emprunteur.class, emprunteurId);
            if (emprunteur == null) {
                throw new DatabaseException("Emprunteur non trouvé.");
            }

            // Vérifier si le document est disponible
            if (document.getNbExemplaires() <= 0) {
                throw new DatabaseException("Aucun exemplaire disponible pour ce document.");
            }

            // Déterminer la date de retour en fonction du type de document
            LocalDate dateRetour;
            if (document instanceof Livre) {
                dateRetour = LocalDate.now().plusWeeks(3);
            } else if (document instanceof CD) {
                dateRetour = LocalDate.now().plusWeeks(2);
            } else if (document instanceof DVD) {
                dateRetour = LocalDate.now().plusWeeks(1);
            } else {
                throw new DatabaseException("Type de document inconnu.");
            }

            // Créer un emprunt
            Emprunt emprunt = new Emprunt();
            emprunt.setDocument(document);
            emprunt.setEmprunteur(emprunteur);
            emprunt.setDateEmprunt(LocalDate.now());
            emprunt.setDateRetour(dateRetour);
            emprunt.setStatus("Emprunté");// Définition de la date de retour

            // Mettre à jour le nombre d'exemplaires du document
            document.setNbExemplaires(document.getNbExemplaires() - 1);

            // Sauvegarder l'emprunt et mettre à jour le document
            em.persist(emprunt);
            em.merge(document);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DatabaseException("Erreur lors de l'emprunt du document.", e);
        } finally {
            em.close();
        }
    }



    @Override
    public void retourne(Document document) throws DatabaseException {

    }

    @Override
    public void payeAmende(long id, double montant) throws DatabaseException {

    }

    @Override
    public List<Emprunt> rapportHistoriqueEmprunts(long id) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            // Utilisation de LEFT JOIN FETCH pour charger 'Emprunts' avec 'Emprunteur'
            Emprunteur emprunteur = em.createQuery(
                            "SELECT e FROM Emprunteur e LEFT JOIN FETCH e.emprunts WHERE e.id = :id", Emprunteur.class)
                    .setParameter("id", id)
                    .getSingleResult();

            if (emprunteur == null) {
                throw new DatabaseException("Emprunteur non trouvé.");
            }

            return emprunteur.getEmprunts();
        } catch (Exception e) {
            throw new DatabaseException("Erreur lors de la récupération de l'historique des emprunts.", e);
        }
    }
}
