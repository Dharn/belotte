package belotte;

import java.util.ArrayList;

public class Tour {
	private Couleur couleurAtout;
	private ArrayList<Carte> paquetDeCarte = new ArrayList<Carte>();
	private ArrayList<Equipe> equipes = new ArrayList<>();
	

	public Tour(Couleur couleurAtout, ArrayList<Carte> paquetDeCarte,ArrayList<Equipe> equipes) {
		this.couleurAtout = couleurAtout;
		this.paquetDeCarte = paquetDeCarte;
		this.equipes = equipes;
	}

	public int calculerPli(Pli p) {
		int point = 0;
		Carte c;
		for (int i=0;i< p.getCartesJouees().size(); i++) {
			c= p.getCartesJouees().get(0);
			if (c.getCouleur() == couleurAtout) {
				point += c.getValeurAtout();
				paquetDeCarte.add(c);
				p.getCartesJouees().remove(c);
				
			}
			else {
				point += c.getValeur();
				paquetDeCarte.add(c);
				p.getCartesJouees().remove(c);
			}
		}
		
		
		return point;
	}
	
	
	

}
