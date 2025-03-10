package ca.cal.tp2.repository;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Emprunt;
import ca.cal.tp2.modele.Emprunteur;
import ca.cal.tp2.modele.Prepose;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PreposeRepositoryJPA implements PreposeRepository {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("tp2.pu");

    @Override
    public void save(Prepose prepose) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(prepose);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Prepose find(long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Prepose prepose = em.find(Prepose.class, id);
            em.getTransaction().commit();
            return prepose;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String entrerNouveauDocument(Document document) {
        return "";
    }

    @Override
    public String collecteAmende(Emprunteur emprunteur, double montant) {
        return "";
    }

    @Override
    public void getRapportAmendes() {

    }

    @Override
    public List<Emprunt> getRapportEmprunts() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Emprunt> query = em.createQuery("SELECT e FROM Emprunt e", Emprunt.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des emprunts", e);
        }
    }

    public void afficherTousLesEmprunteurs() throws DatabaseException{
        try (EntityManager em = emf.createEntityManager()) {
            List<Emprunteur> emprunteurs = em.createQuery("SELECT e FROM Emprunteur e", Emprunteur.class).getResultList();
            for (Emprunteur e : emprunteurs) {
                System.out.println("Emprunteur : " + e.getFirstName() + " " + e.getLastName());
            }
        } catch (Exception e) {
            throw new DatabaseException("Erreur lors de la récupération des emprunteurs.", e);
        }
    }
}
