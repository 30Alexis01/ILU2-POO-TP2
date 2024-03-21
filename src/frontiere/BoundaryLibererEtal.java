package frontiere;

import controleur.ControlLibererEtal;

public class BoundaryLibererEtal {
	private ControlLibererEtal controlLibererEtal;

	public BoundaryLibererEtal(ControlLibererEtal controlLibererEtal) {
		this.controlLibererEtal = controlLibererEtal;
	}

	public void libererEtal(String nomVendeur) {
		boolean vendeurReconnu = controlLibererEtal.isVendeur(nomVendeur);
		if (!vendeurReconnu) {
			System.out.println("Mais vous n'�tes pas inscrit sur notre march� aujourd'hui");
		}else {
			String[] donneesEtal = controlLibererEtal.libererEtal(nomVendeur);
			boolean etalOccupe = Boolean.parseBoolean(donneesEtal[0]);
			//System.out.println (etalOccupe);
			if (etalOccupe) {
				StringBuilder message = new StringBuilder();
				String produit = donneesEtal[2];
				String quantiteInitial = donneesEtal[3];
				String quantiteVendu = donneesEtal[4];
				message.append("Vous avez vendu "+ quantiteVendu+ " sur "+quantiteInitial+" "+ produit);
				message.append(" Au revoir "+ nomVendeur +", passez une bonne journ�e");
				System.out.println(message.toString());
				
			}
		}
	}

}
