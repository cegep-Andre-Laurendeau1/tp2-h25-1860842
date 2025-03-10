package ca.cal.tp2.service.dto;

import ca.cal.tp2.modele.DVD;

public record DvdDTO(String titre, String director, long duree, String rating, long nbExemplaires) {

    public static DvdDTO toDto(DVD dvd) {
        return new DvdDTO(dvd.getTitre(), dvd.getDirector(), dvd.getDuree(), dvd.getRating(), dvd.getNbExemplaires());
    }
    
}
