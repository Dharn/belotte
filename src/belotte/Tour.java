package belotte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tour {
	private Couleur couleurAtout;
	private ArrayList<Carte> paquetDeCarte = new ArrayList<Carte>();
	private ArrayList<Equipe> equipes = new ArrayList<>();
	private Joueur joueurAQuiDistribuer;
	private Jeu jeu;
	private Map<Couleur,ArrayList<Carte>> cartesJouees = new HashMap<Couleur, ArrayList<Carte>>();
	

	public Map<Couleur, ArrayList<Carte>> getCartesJouees() {
		return cartesJouees;
	}
	
	public Map<Couleur, ArrayList<Carte>> getCartesNonJouees(){
		Map<Couleur,ArrayList<Carte>> cartesNonJouees = new HashMap<Couleur, ArrayList<Carte>>();
		cartesNonJouees.put(Couleur.carreau, new ArrayList<Carte>());
		cartesNonJouees.put(Couleur.coeur, new ArrayList<Carte>());
		cartesNonJouees.put(Couleur.pique, new ArrayList<Carte>());
		cartesNonJouees.put(Couleur.tr�fle, new ArrayList<Carte>());
		for(Carte c: jeu.getCarteDeJeu()){
			if(cartesJouees.containsValue(c) == false){
				cartesNonJouees.get(c.getCouleur()).add(c);
			}			
		}
		return cartesNonJouees;
	}

	public void setCartesJouees(Map<Couleur, ArrayList<Carte>> cartesJouees) {
		this.cartesJouees = cartesJouees;
	}

	public Jeu getJeu() {
		return jeu;
	}

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
		cartesJouees.put(Couleur.carreau, new ArrayList<Carte>());
		cartesJouees.put(Couleur.coeur, new ArrayList<Carte>());
		cartesJouees.put(Couleur.pique, new ArrayList<Carte>());
		cartesJouees.put(Couleur.tr�fle, new ArrayList<Carte>());
		jouerTour();
		
	}

	
	
	public void jouerTour(){
		
		for (int i = 0; i < 8; i++) {
			
			Pli pli = new Pli(this);
			
			if (i==7) {
				pli.getEquipeGagnante().setPoints(pli.getEquipeGagnante().getPoints()+10);
				
				pli.calculerPliEtAjouterPoint();
				for (Carte carte : pli.getCartesJouees()) {
					carte.setPossesseur(null);
					paquetDeCarte.add(carte);
				}
			}
			else {
				pli.calculerPliEtAjouterPoint();
				for (Carte carte : pli.getCartesJouees()) {
					carte.setPossesseur(null);
					paquetDeCarte.add(carte);
				}
			}
			
		}
		for (Equipe equipe : equipes) {
			for (Joueur joueur : equipe.getListeJoueur()) {
				joueur.setAPritAtout(false);
			}
		}
	}
	
	

}
