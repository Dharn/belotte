package belotte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Jeu {

	private ArrayList<Carte> paquetDeCarte = new ArrayList<Carte>();
	private ArrayList<Equipe> equipes = new ArrayList<Equipe>();
	private Joueur joueurAQuiDistribuer;
	private Joueur joueurQuiDistribue;

	public ArrayList<Carte> getPaquetDeCarte() {
		return paquetDeCarte;
	}

	public void setPaquetDeCarte(ArrayList<Carte> paquetDeCarte) {
		this.paquetDeCarte = paquetDeCarte;
	}

	public ArrayList<Equipe> getEquipes() {
		return equipes;
	}

	public Joueur getJoueurQuiDistribue() {
		return joueurQuiDistribue;
	}

	public void setJoueurQuiDistribue(Joueur joueurQuiDistribue) {
		this.joueurQuiDistribue = joueurQuiDistribue;
	}

	public Joueur getJoueurAQuiDistribuer() {
		return joueurAQuiDistribuer;
	}

	public void setJoueurAQuiDistribuer(Joueur joueurAQuiDistribuer) {
		this.joueurAQuiDistribuer = joueurAQuiDistribuer;
	}

	public Jeu(Equipe e1, Equipe e2) {

		equipes.add(e1);
		equipes.add(e2);
		e1.getListeJoueur().get(0).setJoueurSuivant(e2.getListeJoueur().get(0));
		e2.getListeJoueur().get(0).setJoueurSuivant(e1.getListeJoueur().get(1));
		e1.getListeJoueur().get(1).setJoueurSuivant(e2.getListeJoueur().get(1));
		e2.getListeJoueur().get(1).setJoueurSuivant(e1.getListeJoueur().get(0));
		joueurAQuiDistribuer = e1.getListeJoueur().get(0);
		joueurQuiDistribue = e2.getListeJoueur().get(1);
		preparerCartes();
		melangerPaquetDeCarte();

		int temp = 0;
		while (equipes.get(0).getPoints() < 1000 || equipes.get(1).getPoints() < 1000) { // fin
																							// du
																							// jeu
																							// apres
																							// 1000
																							// points
			System.out.println("tour " + temp);
			temp++;
			boolean atoutEstPris = false;
			Couleur couleurAtout = null;
			while (!atoutEstPris) { // tant que l'atout n'est pas prit,
									// redistribution
				joueurQuiDistribue.couper(paquetDeCarte);
				distribuerCinqCarte();
				for (Equipe equipe : equipes) {
					for (Joueur joueur : equipe.getListeJoueur()) {
						joueur.afficherMain();
					}
				}

				couleurAtout = tourChoixAtout();
				if (couleurAtout != null) { // si atout prit, fin du while
					System.out.println(couleurAtout);
					atoutEstPris = true;
				}
			}

			// for (Equipe equipe : equipes) {
			// for (Joueur joueur : equipe.getListeJoueur()) {
			// joueur.afficherMain();
			// }
			// }

			distribuerApresAtout();
			new Tour(couleurAtout, this);
			joueurAQuiDistribuer = joueurAQuiDistribuer.getJoueurSuivant();
			joueurQuiDistribue = joueurQuiDistribue.getJoueurSuivant();

		}
		// apres les 1000 points
	}

	public Couleur tourChoixAtout() {
		Joueur joueurChoix = joueurAQuiDistribuer;
		System.out.println(paquetDeCarte.size());
		System.out.println("La retourne : " + paquetDeCarte.get(0));
		for (int i = 0; i < 4; i++) {
			if (joueurChoix.choisirAtout()) {
				Couleur couleurAtout = paquetDeCarte.get(0).getCouleur();
				joueurChoix.donnerCarte(paquetDeCarte.get(0));
				paquetDeCarte.remove(0);
				return couleurAtout;
			}
			joueurChoix = joueurChoix.getJoueurSuivant();

		}
		for (int i = 0; i < 4; i++) {
			Couleur couleurAtout = joueurChoix.choisirCouleurAtout();
			if (couleurAtout != null) {
				joueurChoix.donnerCarte(paquetDeCarte.get(0));
				paquetDeCarte.remove(0);
				return couleurAtout;
			}
			joueurChoix = joueurChoix.getJoueurSuivant();

		}
		return null;
	}

	public void preparerCartes() {

		for (Couleur couleur : Couleur.values()) {
			paquetDeCarte.add(new Carte("7", couleur, 0, 0));
			paquetDeCarte.add(new Carte("8", couleur, 0, 0));
			paquetDeCarte.add(new Carte("9", couleur, 0, 14));
			paquetDeCarte.add(new Carte("10", couleur, 10, 10));
			paquetDeCarte.add(new Carte("Valet", couleur, 2, 20));
			paquetDeCarte.add(new Carte("Dame", couleur, 3, 3));
			paquetDeCarte.add(new Carte("Roi", couleur, 4, 4));
			paquetDeCarte.add(new Carte("AS", couleur, 11, 11));
		}

	}

	public void distribuerCinqCarte() {
		if (paquetDeCarte.size() < 32) {
			for (int i = 0; i < 4; i++) {
				while (joueurAQuiDistribuer.getNbCarte() > 0) {
					paquetDeCarte.add(joueurAQuiDistribuer.retirerCarte());
				}
				setJoueurAQuiDistribuer(getJoueurAQuiDistribuer().getJoueurSuivant());

			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				this.getJoueurAQuiDistribuer().donnerCarte(paquetDeCarte.get(0));
				paquetDeCarte.remove(0);
				setJoueurAQuiDistribuer(getJoueurAQuiDistribuer().getJoueurSuivant());
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				this.getJoueurAQuiDistribuer().donnerCarte(paquetDeCarte.get(0));
				paquetDeCarte.remove(0);
				setJoueurAQuiDistribuer(getJoueurAQuiDistribuer().getJoueurSuivant());
			}
		}

	}

	public void distribuerApresAtout() {
		for (int i = 0; i < 4; i++) {
			if (joueurAQuiDistribuer.getAPritAtout()) {
				for (int j = 0; j < 2; j++) {
					joueurAQuiDistribuer.donnerCarte(paquetDeCarte.get(0));
					paquetDeCarte.remove(0);
				}
				setJoueurAQuiDistribuer(joueurAQuiDistribuer.getJoueurSuivant());
			} else {
				for (int k = 0; k < 3; k++) {
					joueurAQuiDistribuer.donnerCarte(paquetDeCarte.get(0));
					paquetDeCarte.remove(0);
				}
				setJoueurAQuiDistribuer(joueurAQuiDistribuer.getJoueurSuivant());
			}

		}
	}

	public void AfficherPaquetDeCarte() {
		for (Carte carte : paquetDeCarte) {
			System.out.println(carte);
			System.out.println("salut");
		}
	}

	public void melangerPaquetDeCarte() {
		Collections.shuffle(paquetDeCarte);
	}

	public void donnerCarte(Joueur j) {
		j.donnerCarte(paquetDeCarte.get(0));
		paquetDeCarte.remove(0);
	}
	
	public boolean estAutorise(Pli pli, Carte carte){
		ArrayList<Carte> CartesJouees = new ArrayList<Carte>();
		CartesJouees = pli.getCartesJouees();
		
		Carte premiereCarte = new Carte();
		premiereCarte = CartesJouees.get(0);
		
		if(carte.getCouleur() == premiereCarte.getCouleur()){
			return true;
		}
		else if(pli.getTour().getCouleurAtout() == carte.getCouleur()){
			return true;
		}
		else if(carte.getPossesseur().getEquipe() == pli.maitreDuPli().getEquipe()){
			return true;
		}
		else {
			return false;
		}
		
	}

}
