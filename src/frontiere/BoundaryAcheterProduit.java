package frontiere;

import java.util.Scanner;
import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
    private Scanner scan = new Scanner(System.in);
    private ControlAcheterProduit controlAcheterProduit;

    public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
        this.controlAcheterProduit = controlAcheterProduit;
    }

    public void acheterProduit(String nomAcheteur) {
        if (!controlAcheterProduit.verifierIdentiteClient(nomAcheteur)) {
            System.out.println("Je suis désolée " + nomAcheteur + " mais il faut être un habitant de notre village pour commercer ici.");
            return;
        }
        
        System.out.println("Quel produit voulez-vous acheter ?");
        String produit = scan.nextLine();

        String[] vendeurs = controlAcheterProduit.listerVendeurs(produit);
        if (vendeurs == null || vendeurs.length == 0) {
            System.out.println("Désolé, personne ne vend ce produit au marché.");
            return;
        }

        System.out.println("Chez quel commerçant voulez-vous acheter des " + produit + " ?");
        for (int i = 0; i < vendeurs.length; i++) {
            System.out.println((i + 1) + " - " + vendeurs[i]);
        }
        int choixVendeur = scan.nextInt() - 1; 
        scan.nextLine(); 

        if (choixVendeur < 0 || choixVendeur >= vendeurs.length) {
            System.out.println("Choix invalide.");
            return;
        }

        String nomVendeur = vendeurs[choixVendeur];
        System.out.println("Combien de " + produit + " voulez-vous acheter ?");
        int quantite = scan.nextInt();
        scan.nextLine(); 

        String message = controlAcheterProduit.acheterProduit(nomAcheteur, nomVendeur, produit, quantite);
        System.out.println(message);
    }
}
