package Projet;

import Projet.Model.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class Main {
    private static final Path BASE = Paths.get(System.getProperty("user.dir"))
            .resolve("src")
            .resolve("Projet");

    private static final String TECH_CSV = BASE + "/tech_products.csv";
    private static final String FOOD_CSV = BASE + "/food_products.csv";

    private static final Scanner SCAN = new Scanner(System.in);
    private static final List<Product> STOCK = new ArrayList<>();
    private static final List<Product> CART  = new ArrayList<>();

    /*----------------------------------------------------------------------*/

    public static void main(String[] args) {
        loadProducts();
        if (STOCK.isEmpty()) {
            System.out.println("Aucun produit chargé – arrêt.");
            return;
        }
        loop();
    }

    /*----------------------------------------------------------------------*/

    private static void loadProducts() {
        readCSV(TECH_CSV, true);
        readCSV(FOOD_CSV, false);
    }

    /*----------------------------------------------------------------------*/

    private static void readCSV(String file, boolean tech) {
        Path p = Paths.get(file);
        if (!Files.exists(p)) {
            System.out.println("Fichier " + (tech ? "TECH" : "FOOD") + " introuvable : " + p.toAbsolutePath());
            return;
        }
        try (BufferedReader br = Files.newBufferedReader(p)) {
            String l;
            while ((l = br.readLine()) != null) {
                if (l.isBlank()) continue;
                String[] t = l.split(",");
                if (tech && t.length >= 5) {
                    try {
                        STOCK.add(new TechProduct(t[0].trim(), Double.parseDouble(t[1].trim()),
                                Integer.parseInt(t[2].trim()), Integer.parseInt(t[3].trim()), Integer.parseInt(t[4].trim())));
                    } catch (NumberFormatException ignored) { }
                } else if (!tech && t.length >= 3) {
                    try {
                        STOCK.add(new FoodProduct(t[0].trim(), Double.parseDouble(t[1].trim()), t[2].trim()));
                    } catch (NumberFormatException ignored) { }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*----------------------------------------------------------------------*/

    private static void addProductMenu() {
        boolean retour = false;
        while (!retour) {
            System.out.println("\n1) Ajouter Food\n2) Ajouter Tech\n3) Retour");
            System.out.print("Choix : ");
            int choix = readInt();
            switch (choix) {
                case 1 -> appendToCSV(FOOD_CSV, false);
                case 2 -> appendToCSV(TECH_CSV, true);
                case 3 -> retour = true;
                default -> System.out.println("Choix invalide");
            }
        }
    }

    /*----------------------------------------------------------------------*/

    private static void appendToCSV(String file, boolean tech) {
        Path p = Paths.get(file);

        int cols = tech ? 5 : 3;
        if (Files.exists(p)) {
            try (BufferedReader br = Files.newBufferedReader(p)) {
                String first = br.readLine();
                if (first != null && !first.isBlank()) cols = first.split(",").length;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        SCAN.nextLine();
        String[] vals = new String[cols];
        for (int i = 0; i < cols; i++) {
            System.out.print("Valeur " + (i + 1) + " : ");
            vals[i] = SCAN.nextLine().trim();
        }
        String line = String.join(",", vals);

        try (BufferedWriter bw = Files.newBufferedWriter(p, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bw.newLine();
            bw.write(line);
            System.out.println("Ligne ajoutée.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            if (tech && vals.length >= 5) {
                STOCK.add(new TechProduct(vals[0], Double.parseDouble(vals[1]),
                        Integer.parseInt(vals[2]), Integer.parseInt(vals[3]), Integer.parseInt(vals[4])));
            } else if (!tech && vals.length >= 3) {
                STOCK.add(new FoodProduct(vals[0], Double.parseDouble(vals[1]), vals[2]));
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /*----------------------------------------------------------------------*/

    private static void loop() {
        boolean run = true;
        while (run) {
            switch (menu()) {
                case 1 -> courses();
                case 2 -> panier();
                case 3 -> addProductMenu();
                case 4 -> run = false;
                default -> System.out.println("Choix invalide");
            }
        }
    }

    /*----------------------------------------------------------------------*/

    private static int menu() {
        System.out.println("\n1) Faire ses courses\n2) Voir panier\n3) Ajouter produit\n4) Quitter");
        System.out.print("Choix : ");
        return readInt();
    }

    /*----------------------------------------------------------------------*/
    private static void courses() {
        boolean back = false;
        while (!back) {
            System.out.println("\n1) Food\n2) Tech\n3) Retour");
            System.out.print("Choix : ");
            int c = readInt();
            switch (c) {
                case 1 -> select(true);
                case 2 -> select(false);
                case 3 -> back = true;
                default -> System.out.println("Choix invalide");
            }
        }
    }

    /*----------------------------------------------------------------------*/

    private static void select(boolean food) {
        List<Product> list = new ArrayList<>();
        for (Product p : STOCK) {
            if ((food && p instanceof FoodProduct) || (!food && p instanceof TechProduct)) list.add(p);
        }
        if (list.isEmpty()) {
            System.out.println("Aucun produit");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Product p = list.get(i);
            System.out.printf("%d) %s - %.2f €%n", i + 1, p.getNom(), p.getPrix());
        }
        System.out.print("Sélection (0 retour): ");
        int idx = readInt();
        if (idx < 1 || idx > list.size()) return;
        CART.add(list.get(idx - 1));
    }

    /*----------------------------------------------------------------------*/

    private static void panier() {
        if (CART.isEmpty()) {
            System.out.println("Panier vide");
            return;
        }
        double total = 0;
        for (Product p : CART) {
            System.out.println(p.getNom() + " - " + p.getPrix() + " €");
            total += p.getPrix();
        }
        System.out.printf("Total : %.2f €%n", total);
    }

    /*----------------------------------------------------------------------*/

    private static int readInt() {
        while (!SCAN.hasNextInt()) SCAN.next();
        return SCAN.nextInt();
    }
}

/* les split, trim, etc ont été appris en entreprise, et nous avons appris à les utiliser en java en s'aidant d'internet */
