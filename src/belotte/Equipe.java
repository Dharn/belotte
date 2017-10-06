package belotte;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Equipe {
	private String nom;
	private int points;
	private ArrayList<Joueur> listeJoueur = new ArrayList<Joueur>();

	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}
	
	public String getNom() {
		return nom;
	}


	public ArrayList<Joueur> getListeJoueur() {
		return listeJoueur;
	}

	public Equipe(String _nom) {
		nom = _nom;
	}
	public Equipe(String _nom, Joueur j1, Joueur j2) {
		nom = _nom;
		ajouterJoueur(j1);
		ajouterJoueur(j2);
		
	}
	
	public void ajouterJoueur(Joueur j) {
		listeJoueur.add(j);
	}

	void afficherEquipe() {
		// for (int i = 0; i < listeJoueur.size(); i++) {
		// listeJoueur.get(index)
		// }
		for (Joueur joueur : listeJoueur) {
			System.out.println(joueur.getNom());
		}

	}
}
