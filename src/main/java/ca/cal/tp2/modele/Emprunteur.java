package ca.cal.tp2.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(force = true)
@DiscriminatorValue("EMPRUNTEUR")
public class Emprunteur extends Utilisateur{
    private double fineBalance;

    @Setter
    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    private List<Emprunt> emprunts;

    @Setter
    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    private List<Amende> amendes;

    public Emprunteur (String firstName, String lastName, String email, String phoneNumber,
                       double fineBalance, List<Emprunt> emprunts, List<Amende> amendes) {
        super(firstName, lastName, email, phoneNumber);
        this.fineBalance = fineBalance;
        this.emprunts = emprunts;
        this.amendes = amendes;
    }
}
