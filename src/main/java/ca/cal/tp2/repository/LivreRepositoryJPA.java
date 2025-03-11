package ca.cal.tp2.repository;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class LivreRepositoryJPA implements LivreRepository {
    private final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("tp2.pu");

    @Override
    public void save(Livre livre) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(livre);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Livre find(long id) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Livre livre = em.find(Livre.class, id);
            em.getTransaction().commit();
            return livre;
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Livre findByAuthor(String auteur) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Livre> query = em.createQuery(
                    "SELECT l FROM Livre l WHERE l.auteur LIKE :auteur", Livre.class);
            query.setParameter("auteur", "%" + auteur + "%");
            Livre livre = query.getSingleResult();
            em.getTransaction().commit();
            return livre;
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Livre findByTitle(String titre) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            TypedQuery<Livre> query = em.createQuery(
                    "SELECT l FROM Livre l WHERE l.titre LIKE :titre", Livre.class);

            query.setParameter("titre", "%" + titre + "%");

            Livre livre = query.getSingleResult();
            em.getTransaction().commit();
            return livre;
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Livre findByYear(long anneePublication) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            TypedQuery<Livre> query = em.createQuery(
                    "SELECT l FROM Livre l " +
                    "WHERE l.anneePublication = :anneePublication", Livre.class);

            query.setParameter("anneePublication", anneePublication);
            Livre livre = query.getSingleResult();
            em.getTransaction().commit();
            return livre;
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }
}
