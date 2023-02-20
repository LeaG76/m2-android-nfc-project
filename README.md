<div align="center">

# Projet NFC - Android/Mobile

### Lecture d'une carte NFC à partir d'un téléphone android

<img alt="Android" src="https://img.shields.io/badge/-Android-9FC138?style=flat&logo=android&logoColor=white" />
<img alt="Android Studio" src="https://img.shields.io/badge/-Android_Studio-90BF58?style=flat&logo=android-studio&logoColor=white" />
<img alt="Java" src="https://img.shields.io/badge/-Java-E61F24?style=flat&logo=java&logoColor=white" />
<img alt="NFC" src="https://img.shields.io/badge/-NFC-000000?style=flat&logo=nfc&logoColor=white" />
<img alt="SQLite" src="https://img.shields.io/badge/-SQLite-0E7DCC?style=flat&logo=sqlite&logoColor=white" />

</div>

<table>
    <thead>
        <tr>
            <th width="150px">Année</th>
            <th width="150px">Filière</th>
            <th width="300px">Matière</th>
            <th width="300px">Projet</th>
            <th width="350px">Collaborateurs</th>
        </tr>
    </thead>
    <tbody>
        <tr>
        <td align="center">2022-2023</td>
        <td align="center">M2 IWOCS</td>
        <td align="center">Programmation Android</td>
        <td align="center">NFC</td>
        <td align="center">Léa Gallier et Kévin Leroux</td>
        </tr>
    </tbody>
</table>

### Plan

1. [Présentation du projet](#présentation-du-projet)
2. [Résultat](#résultat)

## Présentation du projet

L'objectif de ce projet est de développer une application Android sous **Java** (ou Kotlin mais nous avons choisi Java) qui va permettre de lire une carte avec la technologie de communication sans fil **NFC** (Near-field communication). Et plus particulièrement, on va s'intéresser au cas de notre carte étudiante "Léocarte". Lors de la lecture de celle-ci, cela va nous retourner un code.

A partir de là, nous allons créer une base de données **SQLite**. Dans cette base nous aurons une table *Person* qui aura comme champs/colonnes : un identifiant de la table (*id*), l'identifiant de la carte (*card_id*), le prénom de l'étudiant (*surname*) et son nom (*last_name*).

Notre but est que lorsque l'on va scanner une carte étudiante, si l'étudiant est déjà présent dans notre base de données alors on va faire une recherche sur son identifiant dans la table *Person* et on va retourner son nom et son prénom. S'il n'est pas présent dans la base alors après la lecture de la carte, un formulaire va s'ouvrir sur l'application Android et on va pouvoir renseigné le nom et prénom du porteur de la carte, après cela les informations saisies seront enregistrées dans la table *Person*.

Ce projet a été réalisé sous **Android Studio**, de plus il n'est pas restreint qu'à un seul type de carte ("Léocarte"), cela fonctionne également pour la plus grande partie des cartes possédant la technologie NFC.

## Résultat

<div align="center">
<img title="Preview 1" src="preview1.jpg" alt="Preview | 1" width="200px" />
<img title="Preview 2" src="preview2.jpg" alt="Preview | 2" width="200px" />
<img title="Preview 3" src="preview3.jpg" alt="Preview | 3" width="200px" />
<img title="Preview 4" src="preview4.jpg" alt="Preview | 4" width="200px" />
</div>

- Sur la première image, nous pouvons voir ce qu'il se passe sur l'application lorsque l'on scanne une nouvelle carte NFC. On permet à l'utilisateur d'entrer ses informations dans un formulaire.

- Sur la deuxième image, on peut voir que le NFC redirige vers un identifiant connu dans la base de données et donc l'application nous affiche les informations du propriétaire de la carte.

- Sur les images 3 et 4, on peut voir ce qu'il se passe lorsque l'on scanne une nouvelle carte NFC mais qu'on ne remplit pas les champs du nom et prénom. Dans un premier temps, nous avons un message d'erreur, rien ne s'est ajouté dans la base de données. Puis si on re tente de scanner la même carte le formulaire s'ouvre bien à nouveau avec les champs vide à remplir.

N.b. : sur l'image 3, on peut voir la structure de l'application avec un logo et un titre. Source du logo de l'application : <a href="https://www.flaticon.com/fr/icones-gratuites/nfc" title="Icône | NFC">Nfc icônes créées par le graphiste Darius Dan - Flaticon</a>
