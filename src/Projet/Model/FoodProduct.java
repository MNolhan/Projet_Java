package Projet.Model;

public class FoodProduct extends Product {

    private String expiration;

    public FoodProduct(String nom, double prix, String expiration)
    {
        super(nom, prix);
        this.expiration = expiration;
    }

    public String getExpiration()
    {
        return expiration;
    }
    public void   setExpiration(String expiration)
    {
        this.expiration = expiration;
    }
}
