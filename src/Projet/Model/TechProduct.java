package Projet.Model;

public class TechProduct extends Product {

    private int stockageGo;
    private int autonomieHeures;
    private int ramGo;

    public TechProduct(String nom, double prix, int stockageGo, int autonomieHeures, int ramGo)
    {
        super(nom, prix);
        this.stockageGo = stockageGo;
        this.autonomieHeures = autonomieHeures;
        this.ramGo = ramGo;
    }


    public int getStockageGo()
    {
        return stockageGo;
    }
    public void setStockageGo(int stockageGo)
    {
        this.stockageGo = stockageGo;
    }

    public int getAutonomieHeures()
    {
        return autonomieHeures;
    }
    public void setAutonomieHeures(int h)
    {
        this.autonomieHeures = h;
    }

    public int getRamGo()
    {
        return ramGo;
    }
    public void setRamGo(int ramGo)
    {
        this.ramGo = ramGo;
    }
}
