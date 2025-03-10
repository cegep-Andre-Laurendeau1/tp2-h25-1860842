package ca.cal.tp2.repository;

import ca.cal.tp2.exception.DatabaseException;
import jakarta.persistence.*;

public class AmendeRepositoryJPA implements AmendeRepository {

    private final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("tp2");

    @Override
    public void calculMontant(double amende) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(amende);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void updateStatus(long id) throws DatabaseException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Query query = em.createQuery(
                    "UPDATE Amende SET payed = TRUE " +
                            " WHERE id = :id"
            );

            query.setParameter("id", id);

            query.executeUpdate();
            em.getTransaction().commit();
            System.out.println("Amende payée");
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new DatabaseException(e);
        } finally {
            em.close();
        }
    }
}
