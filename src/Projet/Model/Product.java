package Projet.Model;

public abstract class Product {

    private String nom;
    private double prix;

    public Product(String nom, double prix) {
        this.nom  = nom;
        this.prix = prix;
    }

    public String getNom()
    {
        return nom;
    }
    public void   setNom(String nom)
    {
        this.nom = nom;
    }

    public double getPrix()
    {
        return prix;
    }
    public void   setPrix(double prix)
    {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return String.format("%-25s | %.2f €", nom, prix);
    }
}
