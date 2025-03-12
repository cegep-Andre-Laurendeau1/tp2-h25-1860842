package ca.cal.tp2.repository;

import ca.cal.tp2.exception.*;
import ca.cal.tp2.modele.CD;
import ca.cal.tp2.modele.DVD;
import ca.cal.tp2.modele.Document;
import ca.cal.tp2.modele.Livre;
import jakarta.persistence.*;

import java.util.List;

public class DocumentRepositoryJPA implements DocumentRepository {

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
    public Document find(long id) throws EntityDoesNotExist {
        try (EntityManager em = emf.createEntityManager()) {

            TypedQuery<Document> query = em.createQuery(
                    "SELECT d FROM Document d " +
                            "WHERE d.documentID = :id", Document.class
            );

            query.setParameter("id", id);

            return query.getSingleResult();
        } catch (Exception e) {
            throw new EntityDoesNotExist(e);
        }
    }

    public List<Livre> findAllLivres() throws EntityDoesNotExist {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Livre> livresQuery = em.createQuery("SELECT l FROM Livre l", Livre.class);
            List<Livre> livres = livresQuery.getResultList();
            em.getTransaction().commit();
            return livres;
        } catch (Exception e) {
            throw new EntityDoesNotExist(e);
        }
    }

    public List<CD> findAllCDs() throws EntityDoesNotExist {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<CD> CDQuery = em.createQuery("SELECT c FROM CD c", CD.class);
            List<CD> cds = CDQuery.getResultList();
            em.getTransaction().commit();
            return cds;
        } catch (Exception e) {
            throw new EntityDoesNotExist(e);
        }
    }

    public List<DVD> findAllDVDs() throws EntityDoesNotExist {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            TypedQuery<DVD> DVDQuery = em.createQuery("SELECT d FROM DVD d", DVD.class);

            List<DVD> dvds = DVDQuery.getResultList();
            em.getTransaction().commit();
            return dvds;
        } catch (Exception e) {
            throw new EntityDoesNotExist(e);
        }
    }

    public long nbDocumentEmprunter(long documentId) throws DatabaseException {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();


            String sqlString =  """
                    SELECT count(ed) FROM EmpruntDetail ed
                    WHERE ed.emprunt.document.documentID = :documentId
                    AND ed.dateRetourActuelle IS NULL
                    """;

            Query countQuery = em.createQuery(sqlString);
            countQuery.setParameter("documentId", documentId);

            long count = (long) countQuery.getSingleResult();

            em.getTransaction().commit();

            return count;

        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    // Méthodes de CDs
    @Override
    public void saveCd(CD cd) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(cd);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DatabaseException("Erreur lors de l'ajout du CD", e);
        }
    }

    @Override
    public CD findCd(long id) throws EntityDoesNotExist {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            CD cd = em.find(CD.class, id);
            em.getTransaction().commit();
            return cd;
        } catch (Exception e) {
            throw new EntityDoesNotExist(e);
        }
    }

    @Override
    public CD findCdByTitle(String titre) throws EntityDoesNotExist {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            TypedQuery<CD> query = em.createQuery(
                    "SELECT c FROM CD c WHERE c.titre ILIKE :titre", CD.class);

            query.setParameter("titre", "%" + titre + "%");

            CD cd = query.getSingleResult();
            em.getTransaction().commit();
            return cd;
        } catch (Exception e) {
            throw new EntityDoesNotExist(e);
        }
    }

    @Override
    public CD findCdByArtist(String artiste) throws EntityDoesNotExist {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<CD> query = em.createQuery(
                    "SELECT c FROM CD c WHERE c.artiste ILIKE :artiste", CD.class);
            query.setParameter("artiste", "%" + artiste + "%");
            CD cd = query.getSingleResult();
            em.getTransaction().commit();
            return cd;
        } catch (Exception e) {
            throw new EntityDoesNotExist(e);
        }
    }

    // Méthodes de DVDs
    @Override
    public void saveDvd(DVD dvd) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(dvd);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public DVD findDvd(long id) throws EntityDoesNotExist {
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
    public DVD findDvdByTitle(String titre) throws EntityDoesNotExist {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            TypedQuery<DVD> query = em.createQuery(
                    "SELECT d FROM DVD d WHERE d.titre ILIKE :titre", DVD.class);

            query.setParameter("titre", "%" + titre + "%");

            DVD dvd = query.getSingleResult();
            em.getTransaction().commit();
            return dvd;
        } catch (Exception e) {
            throw new EntityDoesNotExist("DVD not found");
        }
    }

    @Override
    public DVD findDvdByDirector(String director) throws EntityDoesNotExist {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<DVD> query = em.createQuery(
                    "SELECT d FROM DVD d WHERE d.director ILIKE :director", DVD.class);
            query.setParameter("director", "%" + director + "%");
            DVD dvd = query.getSingleResult();
            em.getTransaction().commit();
            return dvd;
        } catch (Exception e) {
            throw new EntityDoesNotExist("DVD not found");
        }
    }

    // Méthodes de Livres
    @Override
    public void saveLivre(Livre livre) throws DatabaseException {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(livre);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Livre findLivre(long id) throws EntityDoesNotExist {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Livre livre = em.find(Livre.class, id);
            em.getTransaction().commit();
            return livre;
        } catch (Exception e) {
            throw new EntityDoesNotExist(e);
        }
    }

    @Override
    public Livre findLivreByAuthor(String auteur) throws EntityDoesNotExist {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Livre> query = em.createQuery(
                    "SELECT l FROM Livre l WHERE l.auteur ILIKE :auteur", Livre.class);
            query.setParameter("auteur", "%" + auteur + "%");
            Livre livre = query.getSingleResult();
            em.getTransaction().commit();
            return livre;
        } catch (Exception e) {
            throw new EntityDoesNotExist(e);
        }
    }

    @Override
    public Livre findLivreByTitle(String titre) throws EntityDoesNotExist {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            TypedQuery<Livre> query = em.createQuery(
                    "SELECT l FROM Livre l WHERE l.titre ILIKE :titre", Livre.class);

            query.setParameter("titre", "%" + titre + "%");

            Livre livre = query.getSingleResult();
            em.getTransaction().commit();
            return livre;
        } catch (Exception e) {
            throw new EntityDoesNotExist(e);
        }
    }

    @Override
    public Livre findLivreByYear(long anneePublication) throws EntityDoesNotExist {
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
            throw new EntityDoesNotExist(e);
        }
    }
}
