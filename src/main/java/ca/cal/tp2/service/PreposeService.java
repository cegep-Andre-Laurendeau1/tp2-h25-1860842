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
                          CdRepositoryJPA cdRepository, DvdRepositoryJPA dvdRepository, LivreRepositoryJPA livreRepository) {
        this.preposeRepository = preposeRepository;
        this.emprunteurRepository = emprunteurRepository;
        this.cdRepository = cdRepository;
        this.dvdRepository = dvdRepository;
        this.livreRepository = livreRepository;
    }

    public void createPrepose(String firstName, String lastName, String email, String phoneNumber, String numEmploye) throws DatabaseException {
        try {
            preposeRepository.save(new Prepose(firstName, lastName, email, phoneNumber, numEmploye));
        } catch (DatabaseException e) {
            System.out.println("Erreur lors de la création du préposé");
        }
    }

    public PreposeDTO findPrepose(long id) {
        Prepose prepose = preposeRepository.find(id);
        if (prepose == null) {
            throw new RuntimeException("Prepose n'existe pas");
        }
        return PreposeDTO.toDto(prepose);
    }

    public void createEmprunteur(String firstName, String lastName, String email, String phoneNumber, double fineBalance, List<Emprunt> emprunts, List<Amende> amendes) throws DatabaseException {
        try {
            emprunteurRepository.save(new Emprunteur(firstName, lastName, email, phoneNumber, fineBalance, emprunts, amendes));
        } catch (DatabaseException e) {
            System.out.println("Erreur lors de la création du préposé");
        }
    }

    public EmprunteurDTO findEmprunteur(long id) throws DatabaseException {
        Emprunteur emprunteur = emprunteurRepository.find(id);
        if (emprunteur == null) {
            throw new RuntimeException("Emprunteur n'existe pas");
        }
        return EmprunteurDTO.toDto(emprunteur);
    }

    public void saveCd(String titre, String artiste, long duration, String genre, long nbExemplaires) throws DatabaseException {
        cdRepository.save(new CD(titre, artiste, duration, genre, nbExemplaires));
    }

    public CdDTO findCd(long id) {
        CD cd = cdRepository.find(id);
        if (cd == null) {
            throw new RuntimeException("CD n'existe pas");
        }
        return CdDTO.toDto(cd);
    }

    public void saveDvd(String titre, String director, long duree, String rating, long nbExemplaires) throws DatabaseException {
        dvdRepository.save(new DVD(titre, director, duree, rating, nbExemplaires));
    }

    public DvdDTO findDvd(long id) throws DatabaseException {
        DVD dvd = dvdRepository.find(id);
        if (dvd == null) {
            throw new RuntimeException("DVD n'existe pas");
        }
        return DvdDTO.toDto(dvd);
    }

    public void saveLivre(String titre, String auteur, long anneePublication, long nbExemplaires) throws DatabaseException {
        livreRepository.save(new Livre(titre, auteur, anneePublication, nbExemplaires));
    }

    public LivreDTO findLivre(long id) throws DatabaseException {
        Livre livre = livreRepository.find(id);
        if (livre == null) {
            throw new RuntimeException("Livre n'existe pas");
        }
        return LivreDTO.toDto(livre);
    }

}
