package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "PREPOSE")
public class Prepose extends Utilisateur{
    private String numEmploye;

    public Prepose(String firstName, String lastName, String email, String phoneNumber, String numEmploye) {
        super(firstName, lastName, email, phoneNumber);
        this.numEmploye = numEmploye;
    }
}
