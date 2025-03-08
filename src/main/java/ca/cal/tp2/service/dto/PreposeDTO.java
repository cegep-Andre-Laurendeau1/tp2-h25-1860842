package ca.cal.tp2.service;

import ca.cal.tp2.modele.Prepose;

public record PreposeDTO(String firstName, String lastName, String email, String phoneNumber, String numEmploye) {
    public static PreposeDTO toDto(Prepose prepose) {
        return new PreposeDTO(prepose.getFirstName(), prepose.getLastName(), prepose.getEmail(), prepose.getPhoneNumber(), prepose.getNumEmploye());
    }
}
