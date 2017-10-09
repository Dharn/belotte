package belotte;

import java.util.ArrayList;

public class Pli {
	private ArrayList<Carte> cartesJouees = new ArrayList<Carte>();
	private Tour tour;
	private Joueur premierJoueurPli;
	private Equipe equipeGagnante=null;
	
	public Carte atoutMaxPli(){
		ArrayList<Carte> atoutsPli = new ArrayList<Carte>();
		Couleur couleurAtout = tour.getCouleurAtout();
		Carte plusFortAtout = null; 
		for(Carte c: cartesJouees){
			if(c.getCouleur() == couleurAtout){
				atoutsPli.add(c);
			}
		}
		if(atoutsPli.size() > 0 ){
			plusFortAtout = atoutsPli.get(0);
			for(Carte c: atoutsPli){
				if(c.getValeurAtout() > plusFortAtout.getValeurAtout()){
					plusFortAtout = c;
				}
			}
		}
		return plusFortAtout;
	}
 	public Equipe getEquipeGagnante() {
 		if (maitreDuPli()!= null) {
 			return maitreDuPli().getEquipe();
		}
 		else {
			return null;
		}
		
	}

	public Tour getTour() {
		return tour;
	}

	public ArrayList<Carte> getCartesJouees() {
		return cartesJouees;
	}

	public Pli(Tour tour) {
		this.tour = tour;
		premierJoueurPli = tour.getJoueurAQuiDistribuer();
		jouerPli();
	}

	public void jouerPli() {
		for (int j = 0; j < 4; j++) {
			premierJoueurPli.choisirCarteAjouer(this); // pose une carte sur le
														// pli
			premierJoueurPli = premierJoueurPli.getJoueurSuivant(); // passe au
																	// joueur
																	// suivant
		}
		
	}

	public void calculerPliEtAjouterPoint() {
		
		int point = 0;
		Joueur joueurGagnant = maitreDuPli();
		this.afficherPli();
		System.out.println(joueurGagnant + "remporte le pli");
		
		for (Equipe e : tour.getEquipes()) {
			for (Joueur j : e.getListeJoueur()) {
				if (j == joueurGagnant) {
					equipeGagnante = e;
				}
			}
		}

		Carte c;
		for (int i = 0; i < 4; i++) {
			c = cartesJouees.get(0);
			if (c.getCouleur() == tour.getCouleurAtout()) {
				point += c.getValeurAtout();
				tour.getCartesJouees().get(c.getCouleur()).add(c);
				tour.getPaquetDeCarte().add(c);
				c.setPossesseur(null);
				cartesJouees.remove(c);

			} else {
				point += c.getValeur();
				tour.getPaquetDeCarte().add(c);
				c.setPossesseur(null);
				cartesJouees.remove(c);
			}
		}

		// ajouter les point a l'équipe gagnante
		if (equipeGagnante != null) {
			equipeGagnante.setPoints(equipeGagnante.getPoints() + point);
		}
		else {
		}

	}

	public Joueur maitreDuPli() {

		if (cartesJouees.size() > 0) {
			Carte tempCarteMaitre = cartesJouees.get(0);
			Couleur couleurDuPli = cartesJouees.get(0).getCouleur();
			for (Carte carte : cartesJouees) {
				if (carte.getCouleur() == tour.getCouleurAtout()) {
					if (tempCarteMaitre.getCouleur() == tour.getCouleurAtout()) {
						if (carte.getValeurAtout() > tempCarteMaitre.getValeurAtout()) {
							tempCarteMaitre = carte;
						}
					} else {
						tempCarteMaitre = carte;
					}
				} else if (carte.getCouleur() == couleurDuPli) {
					if (tempCarteMaitre.getCouleur() != tour.getCouleurAtout()) {
						if (carte.getValeur() > tempCarteMaitre.getValeur()) {
							tempCarteMaitre = carte;
						}
					}
				}
			}
			return tempCarteMaitre.getPossesseur();
		}

		return null;
	}

	public void addCarteJouee(Carte c) {
		cartesJouees.add(c);
	}

	public void afficherPli() {
		System.out.print("Pli : ");
		for (Carte carte : cartesJouees) {
			System.out.print(carte + " | ");
		}
		System.out.println();
	}

}
