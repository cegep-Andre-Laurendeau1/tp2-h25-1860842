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

public class EmprunteurRepositoryJPA implements EmprunteurRepository {

    private final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("tp2.pu");

    @Override
    public void save(Emprunteur emprunteur) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(emprunteur);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Emprunteur find(long id) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Emprunteur emprunteur = em.find(Emprunteur.class, id);
            em.getTransaction().commit();
            return emprunteur;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void emprunte(Document document) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.getTransaction().commit();

        } catch (Exception e) {
            throw new DatabaseException(e);
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
        return List.of();
    }
}
