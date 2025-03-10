package ca.cal.tp2.service.dto;

import ca.cal.tp2.modele.Amende;
import ca.cal.tp2.modele.Emprunt;
import ca.cal.tp2.modele.Emprunteur;

import java.util.List;

public record EmprunteurDTO(String firstName, String lastName, String email, String phoneNumber,
                            List<Amende> amendes, List<Emprunt> emprunts) {

    public static EmprunteurDTO toDto(Emprunteur emprunteur) {
           return new EmprunteurDTO(emprunteur.getFirstName(), emprunteur.getLastName(), emprunteur.getEmail(),
                                    emprunteur.getPhoneNumber(), emprunteur.getAmendes(), emprunteur.getEmprunts());
    }

}
