package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(force = true)
@Table(name = "LIVRE")
public class Livre extends Document{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String auteur;
    private long anneePublication;


    public Livre(String titre, String auteur, long nbExemplaires, long anneePublication) {
        super(titre, nbExemplaires);
        this.auteur = auteur;
        this.anneePublication = anneePublication;
    }
}
