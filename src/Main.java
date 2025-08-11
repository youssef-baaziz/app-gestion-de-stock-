import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Product> products = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    // ---------- Utility Methods ----------
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre entier valide.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre décimal valide.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // ---------- Core Features ----------
    public static void printMenu() {
        System.out.println("\n=== Gestion de Stock (Java - POO) ===");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher tous les produits");
        System.out.println("5. Rechercher un produit");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("7. Charger données d'exemple");
        System.out.println("0. Quitter");
    }

    public static void ajouterProduit() {
        int code = readInt("Code produit : ");
        if (findByCode(code) != null) {
            System.out.println("Erreur : code déjà existant !");
            return;
        }
        String name = readString("Nom produit : ");
        int qty = readInt("Quantité : ");
        double price = readDouble("Prix unitaire : ");
        products.add(new Product(code, name, qty, price));
        System.out.println("Produit ajouté avec succès.");
    }

    public static void modifierProduit() {
        int code = readInt("Code du produit à modifier : ");
        Product p = findByCode(code);
        if (p == null) {
            System.out.println("Produit introuvable.");
            return;
        }
        String name = readString("Nouveau nom (laisser vide pour garder) : ");
        if (!name.isEmpty()) p.setName(name);

        String qtyStr = readString("Nouvelle quantité (laisser vide pour garder) : ");
        if (!qtyStr.isEmpty()) p.setQuantity(Integer.parseInt(qtyStr));

        String priceStr = readString("Nouveau prix (laisser vide pour garder) : ");
        if (!priceStr.isEmpty()) p.setPrice(Double.parseDouble(priceStr));

        System.out.println("Produit modifié.");
    }

    public static void supprimerProduit() {
        int code = readInt("Code du produit à supprimer : ");
        Product p = findByCode(code);
        if (p == null) {
            System.out.println("Produit introuvable.");
            return;
        }
        products.remove(p);
        System.out.println("Produit supprimé.");
    }

    public static void afficherProduits() {
        if (products.isEmpty()) {
            System.out.println("Aucun produit.");
            return;
        }
        System.out.printf("%-6s  %-25s  %-10s  %-10s%n", "Code", "Nom", "Quantité", "Prix");
        System.out.println("-----------------------------------------------------------------");
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public static void rechercherProduit() {
        String name = readString("Nom ou partie du nom : ").toLowerCase();
        boolean found = false;
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(name)) {
                if (!found) {
                    System.out.printf("%-6s  %-25s  %-10s  %-10s%n", "Code", "Nom", "Quantité", "Prix");
                    System.out.println("-----------------------------------------------------------------");
                }
                System.out.println(p);
                found = true;
            }
        }
        if (!found) System.out.println("Aucun produit trouvé.");
    }

    public static void calculerValeurStock() {
        double total = 0;
        for (Product p : products) {
            total += p.getTotalValue();
        }
        System.out.printf("Valeur totale du stock : %.2f%n", total);
    }

    public static void chargerExemples() {
        products.clear();
        products.add(new Product(1001, "Stylo Bic", 120, 0.75));
        products.add(new Product(1002, "Cahier A4", 60, 2.5));
        products.add(new Product(1003, "Trousse", 25, 7.5));
        System.out.println("Exemples chargés.");
    }

    // ---------- Helper ----------
    private static Product findByCode(int code) {
        for (Product p : products) {
            if (p.getCode() == code) return p;
        }
        return null;
    }

    // ---------- Main ----------
    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = readInt("Choix > ");
            switch (choice) {
                case 1: ajouterProduit(); break;
                case 2: modifierProduit(); break;
                case 3: supprimerProduit(); break;
                case 4: afficherProduits(); break;
                case 5: rechercherProduit(); break;
                case 6: calculerValeurStock(); break;
                case 7: chargerExemples(); break;
                case 0: exit = true; System.out.println("Au revoir !"); break;
                default: System.out.println("Choix invalide."); break;
            }
        }
    }
}
