package belotte;

import java.util.ArrayList;
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

		if (pointsMainSiAtout(main, laRetourne) >= 24) {
			Random r = new Random();
			float point = (float) pointsMainSiAtout(main, laRetourne) + (float) r.nextInt(21);
			float pourcentage = (point * 100) / 44;
			if (r.nextFloat() * 100 < pourcentage) {
				return " oui";
			} else {
				return "non";
			}

		} else {
			return "non";
		}
	}

	public Carte choisirAtoutCouleur(ArrayList<Carte> main) {
		return null;
	}

	public Carte choisirCarteAPoser(Pli pli, ArrayList<Carte> main, String niveau) {
		ArrayList<Carte> CartesAutorisees = new ArrayList<Carte>();
		
		for(Carte c:main){
			if(pli.getTour().getJeu().estAutorise(pli, c, main) == true){
				CartesAutorisees.add(c);
			}
		}
		if (niveau == "facile"){
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

}
