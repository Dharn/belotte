package belotte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Ia {

	private Joueur joueurLie;
	private String niveau;
	private ArrayList<Carte> cartesJouees;

	public Ia(Joueur joueurLie, String niveau) {
		this.joueurLie = joueurLie;
		joueurLie.setIaJoueur(this);
		this.niveau = niveau;
	}

	public String choisirAtout(ArrayList<Carte> main, Carte laRetourne) {

		if (pointsMainSiAtout(main, laRetourne) >= 24) {
			Random r = new Random();
			float point = (float) pointsMainSiAtout(main, laRetourne);
			float pourcentage = (point * 100) / 44;
			if (r.nextFloat() * 100 < pourcentage) {
				return "oui";
			} else {
				return "non";
			}

		} else {
			return "non";
		}
	}

	public Couleur choisirAtoutCouleur(ArrayList<Carte> main) {

		ArrayList<Couleur> couleursCartes = new ArrayList<Couleur>();
		Map<Couleur, ArrayList<Carte>> couleursmain = new HashMap<Couleur, ArrayList<Carte>>();
		couleursmain.put(Couleur.carreau, new ArrayList<Carte>());
		couleursmain.put(Couleur.coeur, new ArrayList<Carte>());
		couleursmain.put(Couleur.pique, new ArrayList<Carte>());
		couleursmain.put(Couleur.trèfle, new ArrayList<Carte>());

		for (Carte carte : main) {
			for (Couleur couleur : Couleur.values()) {
				if (carte.getCouleur() == couleur) {
					couleursmain.get(couleur).add(carte);
				}
			}
		}

		Couleur couleurPlusForte = null;
		int pointsCouleurPlusForte = 0;

		for (Couleur couleur : Couleur.values()) {
			if (couleursmain.get(couleur).size()>0) {
				Carte couleuretourne = couleursmain.get(couleur).get(0);
				couleursmain.get(couleur).remove(0);
				int points = pointsMainSiAtout(couleursmain.get(couleur), couleuretourne);
				if (points > pointsCouleurPlusForte) {
					pointsCouleurPlusForte = points;
					couleurPlusForte = couleur;
				}
			}
		}
		
		if (pointsCouleurPlusForte >= 24) {
			Random r = new Random();
			float point = (float) pointsCouleurPlusForte;
			float pourcentage = (point * 100) / 44;
			if (r.nextFloat() * 100 < pourcentage) {
				System.out.println("couleur atout : " + couleurPlusForte);
				return couleurPlusForte;
			} else {
				return null;
			}
		}
		else {
			return null;
		}
	}

	public Carte choisirCarteAPoser(Pli pli, ArrayList<Carte> main) {
		ArrayList<Carte> CartesAutorisees = new ArrayList<Carte>();
		
		for(Carte c:main){
			if(pli.getTour().getJeu().estAutorise(pli, c, main) == true){
				CartesAutorisees.add(c);
			}
		}
		if (this.niveau == "facile" && pli.getCartesJouees().size() != 0){
			if(pli.getEquipeGagnante() == this.joueurLie.getEquipe()){
				if(pli.getTour().getCouleurAtout() == CartesAutorisees.get(0).getCouleur()){
					return this.joueurLie.getMaxMain(true, CartesAutorisees);
				}
				else {
					return this.joueurLie.getMaxMain(false, CartesAutorisees);
				}
			}
			else{
				if(pli.getTour().getCouleurAtout() == CartesAutorisees.get(0).getCouleur()){
					return this.joueurLie.getMinMain(true, CartesAutorisees);
				}
				else {
					return this.joueurLie.getMinMain(false, CartesAutorisees);
				}
			}
			
		}
		else if(this.niveau == "facile" && pli.getCartesJouees().size() == 0){
			for(Carte c : CartesAutorisees){
				if (c.getCouleur() != pli.getTour().getCouleurAtout() && c.getNom() == "AS"){
					return c;
				}
			}
			return this.joueurLie.getMinMain(false, CartesAutorisees);
		}
		else if (this.niveau == "difficile" && pli.getCartesJouees().size() != 0 && CartesAutorisees.get(0).getCouleur() != pli.getTour().getCouleurAtout()){
			ArrayList<Carte> nonJouees = new ArrayList<Carte>();
			nonJouees = pli.getTour().getCartesNonJouees().get(CartesAutorisees.get(0).getCouleur());
			if(pli.getCartesJouees().size() == 3 && pli.getEquipeGagnante() == this.joueurLie.getEquipe()){
				return this.joueurLie.getMaxMain(false, CartesAutorisees); 
			}
			for (Carte c: CartesAutorisees){
				if(c.getValeur() > this.joueurLie.getMaxMain(false, nonJouees).getValeur()){
					return c;
				}
			}
			if (this.joueurLie.getValeurTotale(nonJouees, false) < this.joueurLie.getValeurTotale(CartesAutorisees, false)){
				return this.joueurLie.getMaxMain(false, CartesAutorisees);
			}
		}
		else if (this.niveau == "difficile" && pli.getCartesJouees().size() != 0 && CartesAutorisees.get(0).getCouleur() == pli.getTour().getCouleurAtout()){
			ArrayList<Carte> nonJouees = new ArrayList<Carte>();
			nonJouees = pli.getTour().getCartesNonJouees().get(CartesAutorisees.get(0).getCouleur());
			if(pli.getCartesJouees().size() == 3 && pli.getEquipeGagnante() == this.joueurLie.getEquipe()){
				return this.joueurLie.getMaxMain(false, CartesAutorisees); 
			}
			for (Carte c: CartesAutorisees){
				if(c.getValeur() > this.joueurLie.getMaxMain(true, nonJouees).getValeur()){
					return c;
				}
			}
			if (this.joueurLie.getValeurTotale(nonJouees, true) < this.joueurLie.getValeurTotale(CartesAutorisees, true)){
				return this.joueurLie.getMaxMain(true, CartesAutorisees);
			}
		}
		return CartesAutorisees.get(0);
	}

	private int pointsMainSiAtout(ArrayList<Carte> main, Carte laRetourne) {
		int pts = laRetourne.getValeurAtout();
		for (Carte c : main) {
			if (c.getCouleur() == laRetourne.getCouleur()) {
				pts += c.getValeurAtout();
			}
		}
		return pts;
	}

}
