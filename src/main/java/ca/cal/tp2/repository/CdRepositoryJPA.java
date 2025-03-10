package ca.cal.tp2.repository;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.CD;
import ca.cal.tp2.modele.Emprunteur;
import ca.cal.tp2.modele.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class CdRepositoryJPA implements CdRepository {

    private final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("tp2.pu");

    @Override
    public void save(CD cd) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(cd);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DatabaseException("Erreur lors de l'ajout du CD", e);
        }
    }

    @Override
    public CD find(long id) {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            CD cd = em.find(CD.class, id);
            em.getTransaction().commit();
            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CD findByTitle(String titre) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            TypedQuery<CD> query = em.createQuery(
                    "SELECT c FROM CD c WHERE c.titre LIKE :titre", CD.class);

            query.setParameter("titre", "%" + titre + "%");

            CD cd = query.getSingleResult();
            em.getTransaction().commit();
            return cd;
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public CD findByArtist(String artiste) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<CD> query = em.createQuery(
                    "SELECT c FROM CD c WHERE c.artiste LIKE :artiste", CD.class);
            query.setParameter("artiste", "%" + artiste + "%");
            CD cd = query.getSingleResult();
            em.getTransaction().commit();
            return cd;
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }
}
