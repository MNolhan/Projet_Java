# Projet Java – CourseMarket 🛒

Ce projet est une application **Java en ligne de commande** simulant un système de gestion de panier d'achat. Il permet à l'utilisateur de :
- Parcourir deux types de produits (alimentaires et technologiques),
- Ajouter des produits à son panier,
- Consulter son panier,
- Lire et écrire les produits dans des fichiers CSV.

Ce projet met en œuvre des concepts de **POO** (héritage, encapsulation, polymorphisme) ainsi que la **gestion de fichiers (lecture/écriture)**.

---

## 📁 Arborescence du projet

```
Projet_Java/
├── src/
│   └── Projet/
│       ├── Model/
│       │   ├── Product.java
│       │   ├── FoodProduct.java
│       │   ├── TechProduct.java
│       │   ├── Product.class
│       │   ├── FoodProduct.class
│       │   └── TechProduct.class
│       ├── Main.java
│       ├── Main.class
│       ├── food_products.csv
│       └── tech_products.csv
├── .gitignore
├── jav.iml
└── README.md
```

---

## 💡 Fonctionnalités

- **Modélisation objet** :
  - `Product` : classe mère abstraite
  - `FoodProduct` et `TechProduct` : classes filles
  - Utilisation du **polymorphisme** pour gérer les produits de manière uniforme

- **Gestion des fichiers** :
  - Lecture de fichiers `CSV` pour afficher les produits disponibles
  - Écriture et persistance des ajouts au panier

- **Interface CLI (ligne de commande)** :
  - Menu d'interaction avec l'utilisateur
  - Saisie via `Scanner`
  - Navigation dans le panier, ajout de produits, affichage, etc.

---

## 🚀 Lancer le projet

### 1. Cloner le dépôt

```bash
git clone https://github.com/MNolhan/Projet_Java.git
cd Projet_Java
```

### 2. Compiler le projet

Depuis la racine du projet :

```bash
javac -d out src/Projet/*.java src/Projet/Model/*.java
```

### 3. Exécuter le projet

```bash
java -cp out Projet.Main
```

⚠️ Assure-toi d'avoir Java installé (`java -version` doit renvoyer une version ≥ 11).

---

## 📦 Exemple d’interaction

```bash
Bienvenue dans CourseMarket !
1. Faire ses Courses
2. Voir Panier
3. Ajouter Produit  ## Ajouter un produit au CSV
4. Quitter
Votre choix : 
```

---

## ✨ Concepts abordés

- les Bases de Java
- Programmation Orientée Objet (POO)
- Héritage / Polymorphisme
- Gestion de fichiers CSV
- Interaction utilisateur via terminal

---

## 🧠 Auteur

Projet réalisé dans le cadre d’un exercice académique.  
Développé par **Nolhan** et **Isaac**.

---
