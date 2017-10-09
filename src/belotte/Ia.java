package belotte;

import java.util.ArrayList;

public class Ia {
		
	private Joueur joueurLie;
	
		public Ia(Joueur joueurLie){
			this.joueurLie= joueurLie;
			joueurLie.setIaJoueur(this);
		}
		
		public Carte choisirAtout(ArrayList<Carte> main){
			return null;
		}
		
		public Carte choisirAtoutCouleur(ArrayList<Carte> main){
			return null;
		}
		
		public Carte choisirCarteAPoser(Pli pli,ArrayList<Carte> main){
			return null;
		}
		
		private int pointsMainSiAtout(ArrayList<Carte> main, Couleur atout, Carte laRetourne){
			int pts = laRetourne.getValeurAtout();
			for(Carte c: main){
				if (c.getCouleur() == atout){
					pts += c.getValeurAtout();
				}
			}
			return pts;
		}
}
