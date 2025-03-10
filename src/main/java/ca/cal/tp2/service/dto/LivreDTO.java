package ca.cal.tp2.service.dto;

import ca.cal.tp2.modele.Livre;

public record LivreDTO(String titre, String auteur, long annePublication, long nbExemplaire) {

    public static LivreDTO toDto(Livre livre) {
        return new LivreDTO(livre.getTitre(), livre.getAuteur(), livre.getAnneePublication(), livre.getNbExemplaires());
    }
}
