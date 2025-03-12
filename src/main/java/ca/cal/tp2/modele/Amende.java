package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "AMENDE")
@Access(AccessType.FIELD)
@Getter
@NoArgsConstructor
public class Amende {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double montant;
    private LocalDate dateCreation;
    private boolean payed;

    @ManyToOne
    @JoinColumn(name = "emprunteur_id", referencedColumnName = "id", nullable = false)
    private Emprunteur emprunteur;


    public Amende(long id, double montant, LocalDate dateCreation, boolean payed, Emprunteur emprunteur) {
        this.id = id;
        this.montant = montant;
        this.dateCreation = dateCreation;
        this.payed = payed;
        this.emprunteur = emprunteur;
    }
}
