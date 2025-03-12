## Backlog des Stories - Système de Gestion de Bibliothèque Javatown

### Identification des Acteurs 

- **Emprunteur** 
    
- **Préposé de bibliothèque**
    

### Stories pour les Emprunteurs

#### Histoire Utilisateur 1 : Emprunter un Document

**"En tant qu'emprunteur, je veux emprunter un document (livre, CD, DVD) afin d'avoir accès au contenu pour une période déterminée."**

**Critères d'Acceptation :**

1. **Étant donné que je suis un emprunteur inscrit et je suis authentifié** 
    
    - Quand je demande à emprunter un document
        
    - Alors le système doit vérifier :
        
        - Que je n'ai pas d'amendes impayées
            
        - Que le document est disponible
            
        - Le type de document pour déterminer la durée du prêt (3 semaines pour livres, 2 pour CD, 1 pour DVD)
            
2. **Étant donné que je suis éligible à emprunter**
    
    - Quand le prêt est accordé
        
    - Alors je dois :
        
        - Recevoir une confirmation avec la date de retour
            
        - Voir le document ajouté à ma liste d'emprunts
            
        - Voir la date de retour calculée selon le type de document
            

---

#### Histoire Utilisateur 2 : Retourner un Document

**"En tant qu'emprunteur, je veux retourner un document afin de respecter les conditions d'emprunt et éviter des amendes."**

**Critères d'Acceptation :**

1. **Étant donné que j'ai emprunté un document**
    
    - Quand je le rapporte en présentiel
        
    - Alors le préposé doit enregistrer son retour
        
    - Et retirer ce document de mes emprunts en cours
        

---

#### Histoire Utilisateur 3 : Rechercher un Document

**"En tant qu'emprunteur, je veux rechercher un document afin de trouver rapidement ce qui m'intéresse."**

**Critères d'Acceptation :**

1. **Étant donné que je suis sur l'interface de recherche**
    
    - Quand je saisis des critères de recherche
        
    - Alors je dois pouvoir chercher par :
        
        - Titre
            
        - Auteur
            
        - Éditeur
            
        - Type de document
            
2. **Étant donné que j'effectue une recherche**
    
    - Quand les résultats s'affichent
        
    - Alors je dois voir :
        
        - Les informations détaillées de chaque document
            
        - La disponibilité du document
            
        - La durée d'emprunt possible selon le type
            

---

#### Histoire Utilisateur 4 : Consulter Mon Compte

**"En tant qu'emprunteur, je veux consulter mon compte afin de gérer mes emprunts et amendes."**

**Critères d'Acceptation :**

1. **Étant donné que je suis connecté à mon compte**
    
    - Quand j'accède à mon profil
        
    - Alors je dois voir :
        
        - Mes emprunts en cours
            
        - Les dates de retour pour chaque document
            
        - Les amendes impayées
            
        - L'historique de mes emprunts
            

---

#### Histoire Utilisateur 5 : Payer une Amende

**"En tant qu'emprunteur, je veux payer mes amendes afin de régulariser ma situation et pouvoir continuer à emprunter."**

**Critères d'Acceptation :**

1. **Étant donné que j'ai une amende impayée**
    
    - Quand je me présente au préposé pour payer
        
    - Alors le système doit mettre à jour mon solde
        
    - Et me permettre d'emprunter de nouveaux documents
        

---

### Stories pour les Préposés

#### Histoire Utilisateur 6 : Gérer les Documents

**"En tant que préposé, je veux gérer les documents afin de maintenir la collection de la bibliothèque à jour."**

**Critères d'Acceptation :**

1. **Étant donné que je suis authentifié comme préposé**

- Quand j'ajoute un nouveau **livre**
- Alors je dois pouvoir saisir :

   -  Le titre
   -  L'auteur
   -  L'éditeur
   -  L'année de publication
   -  Le nombre de pages

- Quand j'ajoute un nouveau **CD**
- Alors je dois pouvoir saisir :

    - Le titre
    - L'artiste/Compositeur
    - L'éditeur
    - L'année de publication

- Quand j'ajoute un nouveau **DVD**
- Alors je dois pouvoir saisir :

    - Le titre
    - Le réalisateur
    - L'éditeur
    - L'année de publication
            

---

#### Histoire Utilisateur 7 : Gérer les Amendes

**"En tant que préposé, je veux gérer les amendes afin de maintenir le système de prêt équitable."**

**Critères d'Acceptation :**

1. **Étant donné qu'un document est en retard**
    
    - Quand je calcule l'amende
        
    - Alors le système doit :
        
        - Appliquer le tarif de 0.25$ par jour de retard
            
        - Mettre à jour le solde du compte de l'emprunteur
            
        - Bloquer les nouveaux emprunts si amende impayée
            
2. **Étant donné qu'un emprunteur paie son amende**
    
    - Quand j'enregistre le paiement
        
    - Alors le système doit :
        
        - Mettre à jour le solde
            
        - Débloquer les privilèges d'emprunt
            
        - Générer un reçu

#### Histoire Utilisateur 8 : Consulter Rapport

**"En tant que préposé, je veux consulter les statistiques afin de suivre l'activité de la bibliothèque."**

**Critères d'Acceptation :**

1. **Étant donné que je suis dans l'interface statistiques et suis une préposé authentifié**
    - Quand je consulte les rapports
    - Alors je dois voir :
        - Le nombre d'emprunts par mois
        - Le montant total des amendes par période
        - Les dates de retour à venir
        - L'historique des emprunts par document

#### Histoire Utilisateur 9 : Inscrire un Nouvel Emprunteur

**"En tant que préposé, j'inscris un nouvel emprunteur afin qu’il puisse emprunter des documents."**

**Critères d'Acceptation :**

1. **Étant donné que je suis un préposé authentifié**
    
    - Quand j'inscris un nouvel emprunteur en donnant son nom, prénom,matricule
        
    - Alors le système doit enregistrer ses informations personnelles
        
    - Et lui attribuer un identifiant unique
  