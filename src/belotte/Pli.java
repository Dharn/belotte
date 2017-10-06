package belotte;

import java.util.ArrayList;

public class Pli {
	private ArrayList<Carte> cartesJouees = new ArrayList<Carte>();
	private Tour tour;
	private Joueur premierJoueurPli;
	
	public Tour getTour() {
		return tour;
	}

	public ArrayList<Carte> getCartesJouees() {
		return cartesJouees;
	}

	public Pli(Tour tour){
		this.tour = tour;
		premierJoueurPli = tour.getJoueurAQuiDistribuer();
		jouerPli();
	}
	
	public void jouerPli(){
		for (int j = 0; j < 4; j++) {
			premierJoueurPli.choisirCarteAjouer(this); //pose une carte sur le pli
			premierJoueurPli= premierJoueurPli.getJoueurSuivant(); //passe au joueur suivant
		}
		calculerPliEtAjouterPoint();
		for (Carte carte : cartesJouees) {
			carte.setPossesseur(null);
			tour.getPaquetDeCarte().add(carte);
		}
	}
	
	public void calculerPliEtAjouterPoint() {
		int point = 0;
		Joueur joueurGagnant = maitreDuPli();
		Equipe equipeGagnante = null;
		
		for (Equipe e : tour.getEquipes()) {
			for (Joueur j : e.getListeJoueur()) {
				if (j == joueurGagnant) {
					equipeGagnante = e;
				}
			}
		}
		
		Carte c;
		
		for (int i=0;i< cartesJouees.size(); i++) {
			c= cartesJouees.get(0);
			if (c.getCouleur() == tour.getCouleurAtout()) {
				point += c.getValeurAtout();
				tour.getPaquetDeCarte().add(c);
				c.setPossesseur(null);
				cartesJouees.remove(c);
				
			}
			else {
				point += c.getValeur();
				tour.getPaquetDeCarte().add(c);
				c.setPossesseur(null);
				cartesJouees.remove(c);
			}
		}
		
		// ajouter les point a l'équipe gagnante
		if (equipeGagnante!= null) {
			equipeGagnante.setPoints(equipeGagnante.getPoints()+point);
		}
		
		
	}
	
	public Joueur maitreDuPli(){
		
		if (cartesJouees.size()>0) {
			Couleur couleurDuPli = cartesJouees.get(0).getCouleur();
			for (Carte carte : cartesJouees) {
				if (carte.getCouleur()== couleurDuPli || carte.getCouleur()==tour.getCouleurAtout()) {
					if (carte.getCouleur() == tour.getCouleurAtout()) {
						
					}
				}
			}
		}
		
		return null;
	}
	
	public void addCarteJouee(Carte c){
		cartesJouees.add(c);
	}
	
	public void afficherPli(){
		System.out.print("Pli : ");
		for (Carte carte : cartesJouees) {
			System.out.print(carte+" | ");
		}
		System.out.println();
	}
	
	
}
