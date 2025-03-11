package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private String status;

    @ManyToOne
    @JoinColumn(name = "emprunteur_id", referencedColumnName = "id", nullable = false)
    private Emprunteur emprunteur;

    @ManyToOne
    @JoinColumn(name = "document_id", referencedColumnName = "documentID", nullable = false)
    private Document document;

    @OneToOne(mappedBy = "emprunt", cascade = CascadeType.ALL)
    private EmpruntDetail empruntDetail;


    public Emprunt(LocalDate dateEmprunt, String status, Emprunteur emprunteur, Document document) {
        this.dateEmprunt = dateEmprunt;
        this.status = status;
        this.emprunteur = emprunteur;
        this.document = document;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "Emprunteur='" + emprunteur.getFirstName() + " " + emprunteur.getLastName() + '\'' +
                ", idEmprunt=" + id +
                ", document='" + document.getTitre() + '\'' +
                ", dateEmprunt=" + dateEmprunt +
                ", status='" + status + '\'' +
                (empruntDetail != null ?
                        ", dateRetourPrevue=" + empruntDetail.getDateRetourPrevue() +
                                ", dateRetourActuelle=" + empruntDetail.getDateRetourActuelle() +
                                ", statutRetour='" + empruntDetail.getStatus() + '\'' :
                        ", Pas de détails d'emprunt disponibles") +
                '}';
    }
}
