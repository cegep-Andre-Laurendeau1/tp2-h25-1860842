package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(force = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_document")
public abstract class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long documentID;

    private String titre;
    private final long nbExemplaires;

    public Document(String titre, long nbExemplaires) {
        this.titre = titre;
        this.nbExemplaires = nbExemplaires;
    }
}
