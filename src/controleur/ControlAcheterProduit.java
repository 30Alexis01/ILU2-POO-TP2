package controleur;

import villagegaulois.Etal;
import villagegaulois.Village;
import personnages.Gaulois;

public class ControlAcheterProduit {
    private Village village;
    private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
    private ControlVerifierIdentite controlVerifierIdentite;

    public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
                                 ControlTrouverEtalVendeur controlTrouverEtalVendeur,
                                 Village village) {
        this.village = village;
        this.controlVerifierIdentite = controlVerifierIdentite;
        this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
    }

    public boolean verifierIdentiteClient(String nomClient) {
        return controlVerifierIdentite.verifierIdentite(nomClient);
    }

    public String[] listerVendeurs(String produit) {
        Gaulois[] vendeurs = village.rechercherVendeursProduit(produit);
        if (vendeurs == null || vendeurs.length == 0) {
            return null;
        }
        String[] nomsVendeurs = new String[vendeurs.length];
        for (int i = 0; i < vendeurs.length; i++) {
            nomsVendeurs[i] = vendeurs[i].getNom();
        }
        return nomsVendeurs;
    }

    public String acheterProduit(String nomClient, String nomVendeur, String produit, int quantite) {
        if (!verifierIdentiteClient(nomClient)) {
            return "Je suis désolée " + nomClient + " mais il faut être un habitant de notre village pour commercer ici.";
        }

        Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
        if (etal == null || !etal.getProduit().equals(produit)) {
            return "Désolé, aucun vendeur ne correspond à votre recherche.";
        }

        int quantiteAchetee = etal.acheterProduit(quantite);
        if (quantiteAchetee < quantite) {
            return nomClient + " veut acheter " + quantite + " " + produit + 
                   " mais malheureusement, " + nomVendeur + " n'en a que " + quantiteAchetee + ".";
        } else {
            return nomClient + " achète " + quantiteAchetee + " " + produit + " à " + nomVendeur + ".";
        }
    }
}
