package ca.cal.tp2.service;

import ca.cal.tp2.exception.DatabaseException;
import ca.cal.tp2.modele.*;
import ca.cal.tp2.repository.*;
import ca.cal.tp2.service.dto.*;

import java.util.List;

public class PreposeService {

    private final PreposeRepositoryJPA preposeRepository;
    private final EmprunteurRepositoryJPA emprunteurRepository;
    private final CdRepositoryJPA cdRepository;
    private final DvdRepositoryJPA dvdRepository;
    private final LivreRepositoryJPA livreRepository;

    public PreposeService(PreposeRepositoryJPA preposeRepository, EmprunteurRepositoryJPA emprunteurRepository,
                          CdRepositoryJPA cdRepository, DvdRepositoryJPA dvdRepository,
                          LivreRepositoryJPA livreRepository) {
        this.preposeRepository = preposeRepository;
        this.emprunteurRepository = emprunteurRepository;
        this.cdRepository = cdRepository;
        this.dvdRepository = dvdRepository;
        this.livreRepository = livreRepository;
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
            throw new DatabaseException("Prepose n'existe pas");
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
            throw new DatabaseException("Emprunteur n'existe pas");
        }
        return EmprunteurDTO.toDto(emprunteur);
    }

    public void saveCd(String titre, String artiste, long duration, String genre,
                       long nbExemplaires) throws DatabaseException {
        try {
            cdRepository.save(new CD(titre, artiste, duration, genre, nbExemplaires));
        } catch (DatabaseException e) {
            System.out.println("Erreur lors de la création du CD");
        }
    }


    public DvdDTO findDvd(long id) throws DatabaseException {
        DVD dvd = dvdRepository.find(id);
        if (dvd == null) {
            throw new DatabaseException("DVD n'existe pas");
        }
        return DvdDTO.toDto(dvd);
    }
    public DvdDTO findDvdByTitle(String titre) throws DatabaseException {
        DVD dvd = dvdRepository.findByTitle(titre);
        if (dvd == null) {
            throw new DatabaseException("Aucun DVD trouvé avec le titre : " + titre);
        }
        return DvdDTO.toDto(dvd);
    }

    public DvdDTO findDvdByDirector(String director) throws DatabaseException {
        DVD dvd = dvdRepository.findByDirector(director);
        if (dvd == null) {
            throw new DatabaseException("Aucun DVD trouvé avec le directeur : " + director);
        }
        return DvdDTO.toDto(dvd);
    }

    public void saveDvd(String titre, String director, long duree, String rating,
                        long nbExemplaires) throws DatabaseException {
        try {
            dvdRepository.save(new DVD(titre, director, duree, rating, nbExemplaires));
        } catch (DatabaseException e) {
            System.out.println("Erreur lors de la création du DVD");
        }
    }

    public CdDTO findCd(long id) throws DatabaseException {
         CD cd = cdRepository.find(id);
         if (cd == null) {
            throw new DatabaseException("CD n'existe pas");
         }
         return CdDTO.toDto(cd);
    }

    public CdDTO findCdByTitle(String titre) throws DatabaseException {
        CD cd = cdRepository.findByTitle(titre);
        if (cd == null) {
            throw new DatabaseException("Aucun CD trouvé avec le titre : " + titre);
        }
        return CdDTO.toDto(cd);
    }

    public CdDTO findCdByArtist(String artiste) throws DatabaseException {
        CD cd = cdRepository.findByArtist(artiste);
        if (cd == null) {
            throw new DatabaseException("Aucun CD trouvé avec l'artiste : " + artiste);
        }
        return CdDTO.toDto(cd);
    }

    public void saveLivre(String titre, String auteur,
                          long anneePublication, long nbExemplaires) throws DatabaseException {
        try {
            livreRepository.save(new Livre(titre, auteur, anneePublication, nbExemplaires));
        } catch (DatabaseException e) {
            System.out.println("Erreur lors de la création du livre");
        }
    }

    public LivreDTO findLivre(long id) throws DatabaseException {
        Livre livre = livreRepository.find(id);
        if (livre == null) {
            throw new DatabaseException("Livre n'existe pas");
        }
        return LivreDTO.toDto(livre);
    }

    public LivreDTO findLivreByTitle(String titre) throws DatabaseException {
        Livre livre = livreRepository.findByTitle(titre);
        if (livre == null) {
            throw new DatabaseException("Aucun livre trouvé avec le titre : " + titre);
        }
        return LivreDTO.toDto(livre);
    }

    public LivreDTO findLivreByAuthor(String auteur) throws DatabaseException {
        Livre livre = livreRepository.findByAuthor(auteur);
        if (livre == null) {
            throw new DatabaseException("Aucun livre trouvé de l'auteur : " + auteur);
        }
        return LivreDTO.toDto(livre);
    }

    public LivreDTO findLivreByYear(long anneePublication) throws DatabaseException {
        Livre livre = livreRepository.findByYear(anneePublication);
        if (livre == null) {
            throw new DatabaseException("Aucun livre trouvé pour l'année : " + anneePublication);
        }
        return LivreDTO.toDto(livre);
    }

    public List<Emprunt> getRapportEmprunts() throws DatabaseException {
        try {
            return preposeRepository.getRapportEmprunts(); 
        } catch (Exception e) {
            throw new DatabaseException("Erreur lors de la récupération des emprunts", e);
        }
    }



}
