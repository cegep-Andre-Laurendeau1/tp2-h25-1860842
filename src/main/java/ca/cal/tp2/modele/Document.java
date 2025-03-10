package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(force = true)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long documentID;

    private String titre;
    @Setter
    private long nbExemplaires;

    public Document(String titre, long nbExemplaires) {
        this.titre = titre;
        this.nbExemplaires = nbExemplaires;
    }
}
