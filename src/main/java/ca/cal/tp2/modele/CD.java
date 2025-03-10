package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(force = true)
@DiscriminatorValue("CD")
public class CD extends Document{
    private String artiste, genre;
    private long duration;

    public CD(String titre, String artiste, long duration, String genre, long nbExemplaires) {
        super(titre, nbExemplaires);
        this.artiste = artiste;
        this.duration = duration;
        this.genre = genre;
    }

}
