package ca.cal.tp2.service.dto;

import ca.cal.tp2.modele.CD;

public record CdDTO(String titre, String artiste, long duration,
                    String genre, long nbExemplaires) {

    public static CdDTO toDto(CD cd) {
        return new CdDTO(cd.getTitre(), cd.getArtiste(), cd.getDuration(),
                cd.getGenre(), cd.getNbExemplaires());
    }

}
