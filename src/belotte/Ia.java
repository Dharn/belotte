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
		
		private int pointsMainSiAtout(Couleur atout){
			
			return 0;
		}
}
