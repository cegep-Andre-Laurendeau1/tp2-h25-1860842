package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    private Date dateRetourPrevue;
    private Date dateRetourActuelle;
    private String status;

    public EmpruntDetail(Emprunt emprunt, Date dateRetourPrevue, Date dateRetourActuelle, String status) {
        this.emprunt = emprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourActuelle = dateRetourActuelle;
        this.status = status;
    }
}
