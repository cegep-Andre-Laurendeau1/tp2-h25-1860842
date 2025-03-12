 ```mermaid

classDiagram
class Utilisateur {
-userID: int
-name: String
-email: String
-phoneNumber: String
+login()
}

    class Prepose {
        +entreNouveauDocument(Document)
        +collecteAmende(Emprunteur, double)
        +rapportAmendes()
        +rapportEmprunts()
    }

    class Emprunteur {
        +emprunte(Document)
        +retourneDocument(Document)
        +payeAmende(double)
        +rapportHistoriqueEmprunt()
    }

    class Document {
        -documentID: int
        -titre: String
        -nombreExemplaires: int
        +verifieDisponibilite()
    }

    class Livre {
        -ISBN: String
        -auteur: String
        -editeur: String
        -nombrePages: int
    }

    class CD {
        -artiste: String
        -duree: int
        -genre: String
    }

    class DVD {
        -director: String
        -duree: int
        -rating: String
    }

    class Amende {
        -fineID: int
        -montant: double
        -dateCreation: Date
        -status: boolean
        +calculMontant()
        +updateStatus()
    }

    class Emprunt {
        -borrowID: int
        -dateEmprunt: Date
        -status: String
        +getItems()
    }

    class EmpruntDetail {
        -lineItemID: int
        -dateRetourPrevue: Date
        -dateRetourActuelle: Date
        -status: String
        +isEnRetard()
        +calculAmende()
        +updateStatus()
    }

    Utilisateur <|-- Prepose
    Utilisateur <|-- Emprunteur
    Document <|-- Livre
    Document <|-- CD
    Document <|-- DVD
    Emprunteur "1" -- "*" Emprunt : has
    Emprunt "1" -- "*" EmpruntDetail : contains
    EmpruntDetail "*" -- "1" Document : references
    Amende "*" -- "1" Emprunteur : has
    Prepose "1" -- "*" Document : manages
```