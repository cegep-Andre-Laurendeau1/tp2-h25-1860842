package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "EMPRUNT")
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate dateEmprunt;
    private LocalDate dateRetour;
    private String status;

    @ManyToOne
    @JoinColumn(name = "emprunteur_id", referencedColumnName = "id", nullable = false)
    private Emprunteur emprunteur;

    @ManyToOne
    @JoinColumn(name = "document_id", referencedColumnName = "documentID", nullable = false)
    private Document document;


    public Emprunt(LocalDate dateEmprunt, LocalDate dateRetour, String status, Emprunteur emprunteur, Document document) {
        this.dateEmprunt = dateEmprunt;
        this.status = status;
        this.emprunteur = emprunteur;
        this.document = document;
        this.dateRetour = dateRetour;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", document='" + document.getTitre() + '\'' +
                ", emprunteur='" + emprunteur.getFirstName() + " " + emprunteur.getLastName() + '\'' +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetour=" + dateRetour +
                ", status='" + status + '\'' +
                '}';
    }

}
