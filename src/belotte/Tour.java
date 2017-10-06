package belotte;

import java.util.ArrayList;

public class Tour {
	private Couleur couleurAtout;
	private ArrayList<Carte> paquetDeCarte = new ArrayList<Carte>();
	private ArrayList<Equipe> equipes = new ArrayList<>();
	private Joueur joueurAQuiDistribuer;
	private Jeu jeu;
	

	public Couleur getCouleurAtout() {
		return couleurAtout;
	}

	public void setCouleurAtout(Couleur couleurAtout) {
		this.couleurAtout = couleurAtout;
	}

	public ArrayList<Carte> getPaquetDeCarte() {
		return paquetDeCarte;
	}

	public void setPaquetDeCarte(ArrayList<Carte> paquetDeCarte) {
		this.paquetDeCarte = paquetDeCarte;
	}

	public ArrayList<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(ArrayList<Equipe> equipes) {
		this.equipes = equipes;
	}

	public Joueur getJoueurAQuiDistribuer() {
		return joueurAQuiDistribuer;
	}

	public void setJoueurAQuiDistribuer(Joueur joueurAQuiDistribuer) {
		this.joueurAQuiDistribuer = joueurAQuiDistribuer;
	}

	public Tour(Couleur couleurAtout, Jeu jeu) {
		this.jeu = jeu;
		this.couleurAtout = couleurAtout;
		this.paquetDeCarte = jeu.getPaquetDeCarte();
		this.equipes = jeu.getEquipes();
		this.joueurAQuiDistribuer= jeu.getJoueurAQuiDistribuer();
		jouerTour();
		
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
	
	public void jouerTour(){
		
		for (int i = 0; i < 8; i++) {
			
			Pli pliActuel = new Pli(this);
			
		}
		for (Equipe equipe : equipes) {
			for (Joueur joueur : equipe.getListeJoueur()) {
				joueur.setAPritAtout(false);
			}
		}
	}
	
	

}
