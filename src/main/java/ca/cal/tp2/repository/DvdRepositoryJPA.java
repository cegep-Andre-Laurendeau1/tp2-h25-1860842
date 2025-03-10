package ca.cal.tp2.repository;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.CD;
import ca.cal.tp2.modele.DVD;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class DvdRepositoryJPA implements DvdRepository{

    private final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("tp2.pu");

    @Override
    public void save(DVD dvd) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(dvd);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public DVD find(long id) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            DVD dvd = em.find(DVD.class, id);
            em.getTransaction().commit();
            return dvd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DVD findByTitle(String titre) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            TypedQuery<DVD> query = em.createQuery(
                    "SELECT d FROM DVD d WHERE d.titre LIKE :titre", DVD.class);

            query.setParameter("titre", "%" + titre + "%");

            DVD dvd = query.getSingleResult();
            em.getTransaction().commit();
            return dvd;
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public DVD findByDirector(String director) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<DVD> query = em.createQuery(
                    "SELECT d FROM DVD d WHERE d.director LIKE :director", DVD.class);
            query.setParameter("director", "%" + director + "%");
            DVD dvd = query.getSingleResult();
            em.getTransaction().commit();
            return dvd;
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }
}
