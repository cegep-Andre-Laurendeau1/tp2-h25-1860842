package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(force = true)
@Table(name = "DVD")
public class DVD extends Document{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String director, rating;
    private long duree;

    public DVD(String titre, String director, long duree, String rating, long nbExemplaires) {
        super(titre, nbExemplaires);
        this.director = director;
        this.duree = duree;
        this.rating = rating;
    }
}
