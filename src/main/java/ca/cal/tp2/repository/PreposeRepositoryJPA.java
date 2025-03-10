package ca.cal.tp2.repository;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Emprunteur;
import ca.cal.tp2.modele.Prepose;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
    public void getRapportEmprunts() {

    }
}
