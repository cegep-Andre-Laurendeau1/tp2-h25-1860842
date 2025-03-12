package ca.cal.tp2.repository;


import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.exception.EntityDoesNotExist;
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
    public Emprunteur find(long id) throws EntityDoesNotExist {
        try (EntityManager em = emf.createEntityManager()) {
            Emprunteur emprunteur = em.createQuery(
                            "SELECT e FROM Emprunteur e WHERE e.id = :id", Emprunteur.class)
                    .setParameter("id", id)
                    .getSingleResult();

            if (emprunteur == null) {
                throw new EntityDoesNotExist("Emprunteur non trouvé.");
            }

            emprunteur.setEmprunts(em.createQuery(
                            "SELECT e FROM Emprunt e WHERE e.emprunteur.id = :id", Emprunt.class)
                    .setParameter("id", id)
                    .getResultList());

            emprunteur.setAmendes(em.createQuery(
                            "SELECT a FROM Amende a WHERE a.emprunteur.id = :id", Amende.class)
                    .setParameter("id", id)
                    .getResultList());
            
            return emprunteur;

        } catch (Exception e) {
            throw new EntityDoesNotExist("Erreur lors de la récupération de l'emprunteur.", e);
        }
    }

    @Override
    public void emprunte(long documentId, long emprunteurId) throws DatabaseException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Emprunteur emprunteur = em.find(Emprunteur.class, emprunteurId);

            if (emprunteur == null) {
                throw new DatabaseException("Emprunteur non trouvé avec l'ID : " + emprunteurId);
            }

            Document document = em.find(Document.class, documentId);

            if (document == null) {
                throw new EntityDoesNotExist("Le document n'existe pas.");
            }

            long nbEmpruntes = (long) em.createQuery(
                            "SELECT COUNT(ed) FROM EmpruntDetail ed " +
                                    "WHERE ed.emprunt.document.documentID = :documentId " +
                                    "AND ed.dateRetourActuelle IS NULL")
                    .setParameter("documentId", documentId)
                    .getSingleResult();

            if (document.getNbExemplaires() <= nbEmpruntes) {
                throw new DatabaseException("Aucun exemplaire disponible pour ce document.");
            }

            LocalDate dateRetourPrevue;
            if (document instanceof Livre) {
                dateRetourPrevue = LocalDate.now().plusWeeks(3);
            } else if (document instanceof CD) {
                dateRetourPrevue = LocalDate.now().plusWeeks(2);
            } else if (document instanceof DVD) {
                dateRetourPrevue = LocalDate.now().plusWeeks(1);
            } else {
                throw new DatabaseException("Type de document inconnu.");
            }

            Emprunt emprunt = new Emprunt(
                    LocalDate.now(), "Emprunté", emprunteur, document);

            EmpruntDetail empruntDetail = new EmpruntDetail(emprunt, dateRetourPrevue,
                    null, "Emprunté");

            emprunt.setEmpruntDetail(empruntDetail);

            em.persist(emprunt);
            em.persist(empruntDetail);
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
            TypedQuery<Emprunt> query = em.createQuery(
                    "SELECT e FROM Emprunt e " +
                            "LEFT JOIN FETCH e.emprunteur " +
                            "LEFT JOIN FETCH e.document " +
                            "LEFT JOIN FETCH e.empruntDetail " +
                            "WHERE e.emprunteur.id = :id", Emprunt.class);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException("Erreur lors de la récupération de l'historique des emprunts.", e);
        }
    }
}
