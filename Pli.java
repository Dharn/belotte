package belotte;

import java.util.ArrayList;

public class Pli {
	private ArrayList<Carte> cartesJouees = new ArrayList<Carte>();
	
	
	public ArrayList<Carte> getCartesJouees() {
		return cartesJouees;
	}

	public Pli(Tour tour){
		for (int j = 0; j < 4; j++) {
			tour.getJoueurAQuiDistribuer().choisirCarteAjouer(this); //pose une carte sur le pli
			tour.setJoueurAQuiDistribuer(tour.getJoueurAQuiDistribuer().getJoueurSuivant()); //passe au joueur suivant
		}
		
		
	}
	
	public Joueur maitreDuPli(){
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
