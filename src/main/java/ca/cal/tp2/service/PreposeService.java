package ca.cal.tp2.service;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.exception.EntityDoesNotExist;
import ca.cal.tp2.modele.*;
import ca.cal.tp2.repository.*;
import ca.cal.tp2.service.dto.*;

import java.util.List;

public class PreposeService {

    private final PreposeRepositoryJPA preposeRepository;
    private final EmprunteurRepositoryJPA emprunteurRepository;
    private final DocumentRepositoryJPA documentRepository;

    public PreposeService(PreposeRepositoryJPA preposeRepository, EmprunteurRepositoryJPA emprunteurRepository,
                          DocumentRepositoryJPA documentRepository) {
        this.preposeRepository = preposeRepository;
        this.emprunteurRepository = emprunteurRepository;
        this.documentRepository = documentRepository;
    }

    public void createPrepose(String firstName, String lastName, String email,
                              String phoneNumber, String numEmploye) throws DatabaseException {
        try {
            preposeRepository.save(new Prepose(firstName, lastName, email, phoneNumber, numEmploye));
        } catch (DatabaseException e) {
            System.out.println("Erreur lors de la création du préposé");
        }
    }

    public PreposeDTO findPrepose(long id) throws DatabaseException {
        Prepose prepose = preposeRepository.find(id);
        if (prepose == null) {
            throw new EntityDoesNotExist("Prepose n'existe pas");
        }
        return PreposeDTO.toDto(prepose);
    }

    public void createEmprunteur(String firstName, String lastName, String email, String phoneNumber,
                                 double fineBalance, List<Emprunt> emprunts, List<Amende> amendes)
                                throws DatabaseException {
        try {
            emprunteurRepository.save(new Emprunteur(firstName, lastName,
                    email, phoneNumber, fineBalance, emprunts, amendes));
        } catch (DatabaseException e) {
            System.out.println("Erreur lors de la création du préposé");
        }
    }

    public EmprunteurDTO findEmprunteur(long id) throws DatabaseException {
        Emprunteur emprunteur = emprunteurRepository.find(id);
        if (emprunteur == null) {
            throw new EntityDoesNotExist("Emprunteur n'existe pas");
        }
        return EmprunteurDTO.toDto(emprunteur);
    }

    public void saveCd(String titre, String artiste, long duration, String genre,
                       long nbExemplaires) throws DatabaseException {
        try {
            documentRepository.saveCd(new CD(titre, artiste, duration, genre, nbExemplaires));
        } catch (DatabaseException e) {
            System.out.println("Erreur lors de la création du CD");
        }
    }


    public DvdDTO findDvd(long id) throws EntityDoesNotExist {
        DVD dvd = documentRepository.findDvd(id);
        if (dvd == null) {
            throw new EntityDoesNotExist("DVD n'existe pas");
        }
        return DvdDTO.toDto(dvd);
    }
    public DvdDTO findDvdByTitle(String titre) throws EntityDoesNotExist {
        DVD dvd = documentRepository.findDvdByTitle(titre);
        if (dvd == null) {
            throw new EntityDoesNotExist("Aucun DVD trouvé avec le titre : " + titre);
        }
        return DvdDTO.toDto(dvd);
    }

    public DvdDTO findDvdByDirector(String director) throws EntityDoesNotExist {
        DVD dvd = documentRepository.findDvdByDirector(director);
        if (dvd == null) {
            throw new EntityDoesNotExist("Aucun DVD trouvé avec le directeur : " + director);
        }
        return DvdDTO.toDto(dvd);
    }

    public void saveDvd(String titre, String director, long duree, String rating,
                        long nbExemplaires) throws DatabaseException {
        try {
            documentRepository.saveDvd(new DVD(titre, director, duree, rating, nbExemplaires));
        } catch (DatabaseException e) {
            System.out.println("Erreur lors de la création du DVD");
        }
    }

    public CdDTO findCd(long id) throws EntityDoesNotExist {
         CD cd = documentRepository.findCd(id);
         if (cd == null) {
            throw new EntityDoesNotExist("CD n'existe pas");
         }
         return CdDTO.toDto(cd);
    }

    public CdDTO findCdByTitle(String titre) throws EntityDoesNotExist {
        CD cd = documentRepository.findCdByTitle(titre);
        if (cd == null) {
            throw new EntityDoesNotExist("Aucun CD trouvé avec le titre : " + titre);
        }
        return CdDTO.toDto(cd);
    }

    public CdDTO findCdByArtist(String artiste) throws EntityDoesNotExist {
        CD cd = documentRepository.findCdByArtist(artiste);
        if (cd == null) {
            throw new EntityDoesNotExist("Aucun CD trouvé avec l'artiste : " + artiste);
        }
        return CdDTO.toDto(cd);
    }

    public void saveLivre(String titre, String auteur,
                          long anneePublication, long nbExemplaires) throws DatabaseException {
        try {
            documentRepository.saveLivre(new Livre(titre, auteur, anneePublication, nbExemplaires));
        } catch (DatabaseException e) {
            System.out.println("Erreur lors de la création du livre");
        }
    }

    public LivreDTO findLivre(long id) throws DatabaseException {
        Livre livre = documentRepository.findLivre(id);
        if (livre == null) {
            throw new DatabaseException("Livre n'existe pas");
        }
        return LivreDTO.toDto(livre);
    }

    public LivreDTO findLivreByTitle(String titre) throws DatabaseException {
        Livre livre = documentRepository.findLivreByTitle(titre);
        if (livre == null) {
            throw new DatabaseException("Aucun livre trouvé avec le titre : " + titre);
        }
        return LivreDTO.toDto(livre);
    }

    public LivreDTO findLivreByAuthor(String auteur) throws DatabaseException {
        Livre livre = documentRepository.findLivreByAuthor(auteur);
        if (livre == null) {
            throw new DatabaseException("Aucun livre trouvé de l'auteur : " + auteur);
        }
        return LivreDTO.toDto(livre);
    }

    public LivreDTO findLivreByYear(long anneePublication) throws DatabaseException {
        Livre livre = documentRepository.findLivreByYear(anneePublication);
        if (livre == null) {
            throw new DatabaseException("Aucun livre trouvé pour l'année : " + anneePublication);
        }
        return LivreDTO.toDto(livre);
    }

    public long getNbDocumentEmprunte(long documentId) throws DatabaseException {
        try {
            return documentRepository.nbDocumentEmprunter(documentId);
        } catch (Exception e) {
            throw new DatabaseException("Erreur lors de la récupération du nombre de documents empruntés", e);
        }
    }
    public List<Emprunt> getRapportEmprunts() throws DatabaseException {
        try {
            return preposeRepository.getRapportEmprunts(); 
        } catch (Exception e) {
            throw new DatabaseException("Erreur lors de la récupération des emprunts", e);
        }
    }
}
