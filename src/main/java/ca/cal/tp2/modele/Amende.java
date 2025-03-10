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
    
    public Amende(long id, double montant, LocalDate dateCreation, boolean payed) {
        this.id = id;
        this.montant = montant;
        this.dateCreation = dateCreation;
        this.payed = payed;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
