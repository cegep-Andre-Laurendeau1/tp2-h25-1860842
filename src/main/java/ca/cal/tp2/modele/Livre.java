package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(force = true)
@DiscriminatorValue("Livre")
public class Livre extends Document{
    private String auteur;
    private long anneePublication;


    public Livre(String titre, String auteur, long nbExemplaires, long anneePublication) {
        super(titre, nbExemplaires);
        this.auteur = auteur;
        this.anneePublication = anneePublication;
    }
}
