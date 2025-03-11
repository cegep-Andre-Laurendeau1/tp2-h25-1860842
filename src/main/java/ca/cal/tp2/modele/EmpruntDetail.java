package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "EMPRUNTDETAIL")
public class EmpruntDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emprunt_id", referencedColumnName = "id", nullable = false, unique = true)
    Emprunt emprunt;

    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourActuelle;
    private String status;

    public EmpruntDetail(Emprunt emprunt, LocalDate dateRetourPrevue, LocalDate dateRetourActuelle, String status) {
        this.emprunt = emprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourActuelle = dateRetourActuelle;
        this.status = status;
    }
}
