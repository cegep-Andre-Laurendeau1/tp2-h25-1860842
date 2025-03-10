package ca.cal.tp2.repository;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.Document;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class DocumentRepositoryJPA implements DocumentRepository {

    // Le UnitName doit etre le meme que persistance.xml
    private final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("tp2.pu");

    @Override
    public void save(Document document) throws DatabaseException {

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(document);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Document find(long id) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {

            TypedQuery<Document> query = em.createQuery(
                    "SELECT d FROM Document d " +
                            "WHERE d.documentID = :id", Document.class
            );

            query.setParameter("id", id);

            return query.getSingleResult();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }
}
