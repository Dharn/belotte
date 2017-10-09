package belotte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Ia {

	private Joueur joueurLie;
	private String niveau;
	private ArrayList<Carte> cartesJouees;

	public Ia(Joueur joueurLie) {
		this.joueurLie = joueurLie;
		joueurLie.setIaJoueur(this);
	}

	public String choisirAtout(ArrayList<Carte> main, Carte laRetourne) {

		if (pointsMainSiAtout(main, laRetourne) >= 24 && pointsTotalMainSiAtout(main, laRetourne) >= 35) {
			Random r = new Random();
			float point = (float) pointsMainSiAtout(main, laRetourne);
			float pourcentage = (point * 100) / 44;
			System.out.println("pourcent : "+pourcentage);
			float rand = r.nextFloat()*100;
			System.out.println("lancé : "+rand);
			if (rand < pourcentage) {
				System.out.println("oui");
				return "oui";
			} else {
				System.out.println("non");
				return "non";
			}

		} else {
			System.out.println("non");
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
			if (couleursmain.get(couleur).size()!=0) {
				Carte couleuretourne = couleursmain.get(couleur).get(0);
				couleursmain.get(couleur).remove(0);
				int points = pointsMainSiAtout(couleursmain.get(couleur), couleuretourne);
				if (points > pointsCouleurPlusForte) {
					pointsCouleurPlusForte = points;
					couleurPlusForte = couleur;
				}
			}
			
		}
		
		if (pointsCouleurPlusForte >= 24 && pointsTotalMainSiAtout(main, couleurPlusForte) >= 35) {
			Random r = new Random();
			float point = (float) pointsCouleurPlusForte;
			float pourcentage = (point * 100) / 44;
			System.out.println("pourcent : "+pourcentage);
			float rand = r.nextFloat()*100;
			System.out.println("lancé : "+rand);
			if (rand < pourcentage) {
				System.out.println(couleurPlusForte);
				return couleurPlusForte;
			} else {
				System.out.println("non");
				return null;
			}
		}
		else {
			System.out.println("non");
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
		if (this.niveau == "facile"){
			if(pli.getEquipeGagnante() == this.joueurLie.getEquipe());
			
		}
		
		return null;
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
	
	private int pointsTotalMainSiAtout(ArrayList<Carte> main, Carte laRetourne) {
		int pts = laRetourne.getValeurAtout();
		for (Carte c : main) {
			if (c.getCouleur() == laRetourne.getCouleur()) {
				pts += c.getValeurAtout();
			}
			else {
				pts+= c.getValeur();
			}
		}
		return pts;
	}
	private int pointsTotalMainSiAtout(ArrayList<Carte> main, Couleur couleurAtout) {
		int pts = 0;
		for (Carte c : main) {
			if (c.getCouleur() == couleurAtout) {
				pts += c.getValeurAtout();
			}
			else {
				pts+= c.getValeur();
			}
		}
		return pts;
	}
}
