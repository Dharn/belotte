package belotte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
		while (equipes.get(0).getPoints() <= 1000 && equipes.get(1).getPoints() <= 1000) { // fin
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
					atoutEstPris = true;
				}
			}

			for (Equipe equipe : equipes) {
				for (Joueur joueur : equipe.getListeJoueur()) {
					joueur.afficherMain();
				}
			}

			distribuerApresAtout();
			new Tour(couleurAtout, this);
			joueurAQuiDistribuer = joueurAQuiDistribuer.getJoueurSuivant();
			joueurQuiDistribue = joueurQuiDistribue.getJoueurSuivant();
			for (Equipe equipe : equipes) {
				System.out.println(equipe.getNom() + " point : " + equipe.getPoints());
			}
		}
		// apres les 1000 points
		Equipe equipeGagnante = null;
		if (equipes.get(0).getPoints() > equipes.get(1).getPoints()) {
			System.out.println(
					equipes.get(0).getNom() + " est l'équipe gagnate avec " + equipes.get(0).getPoints() + " points.");
		} else if (equipes.get(0).getPoints() < equipes.get(1).getPoints()) {
			System.out.println(
					equipes.get(1).getNom() + " est l'équipe gagnate avec " + equipes.get(1).getPoints() + " points.");
		} else {
			System.out.println("il y a ex aequo a " + equipes.get(0).getPoints() + " points.");
		}
	}

	public Couleur tourChoixAtout() {
		Joueur joueurChoix = joueurAQuiDistribuer;
		System.out.println("La retourne : " + paquetDeCarte.get(0));
		for (int i = 0; i < 4; i++) {
			if (joueurChoix.choisirAtout(paquetDeCarte.get(0))) {
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
		}
	}

	public void melangerPaquetDeCarte() {
		Collections.shuffle(paquetDeCarte);
	}

	public void donnerCarte(Joueur j) {
		j.donnerCarte(paquetDeCarte.get(0));
		paquetDeCarte.remove(0);
	}

	public boolean estAutorise(Pli pli, Carte carte, ArrayList<Carte> main) {
		ArrayList<Carte> CartesJouees = new ArrayList<Carte>();
		CartesJouees = pli.getCartesJouees();

		Carte premiereCarte = null;
		if (CartesJouees.size() != 0) {
			premiereCarte = CartesJouees.get(0);
		}

		if (premiereCarte == null) {
			return true;
		} else if (carte.getCouleur() == premiereCarte.getCouleur()) {
			boolean peutPoserAtout = true;
			if (carte.getCouleur() == pli.getTour().getCouleurAtout()) {
				for (Carte carteMain : main) {
					if (pli.atoutMaxPli().getValeurAtout() > carte.getValeurAtout()
							&& carteMain.getCouleur() == pli.getTour().getCouleurAtout()
							&& carteMain.getValeurAtout() > pli.atoutMaxPli().getValeurAtout()) {
						// si ma carte est plus faible, et j'ai un atout plus fort : peut pas poser
						peutPoserAtout = false;
						System.out.println("peut pas poser car atout dans pli:" + carteMain);
					}
				}
			}
			return peutPoserAtout;

		} else if (carte.getCouleur() == pli.getTour().getCouleurAtout()) {
			boolean peutPoserAtout = true;

			for (Carte carteMain : main) {
				if (carteMain.getCouleur() == premiereCarte.getCouleur() && carte != carteMain) {
					// si carte de la couleur demandée dans la main : pet pas poser
					peutPoserAtout = false;
					System.out.println("peut pas poser car couleur demander presente :" + carteMain);
				}
				if (pli.atoutMaxPli() != null && pli.atoutMaxPli().getValeurAtout() > carte.getValeurAtout()
						&& carteMain.getCouleur() == pli.getTour().getCouleurAtout()
						&& carteMain.getValeurAtout() > pli.atoutMaxPli().getValeurAtout()) { // si
																								// ma
																								// carte
																								// est
																								// plus
																								// faible,
																								// et
																								// j'ai
																								// un
																								// atout
																								// plus
																								// fort
																								// :
																								// peut
																								// pas
																								// poser
					peutPoserAtout = false;
					System.out.println("peut pas poser car atout dans pli:" + carteMain);
				}

			}
			if (peutPoserAtout) {
				return peutPoserAtout;
			}
		} else if (carte.getPossesseur().getEquipe() == pli.maitreDuPli().getEquipe()) {
			if (carte.getPossesseur().aCouleur(premiereCarte.getCouleur()) == false) {
				return true;
			}
		} else if (carte.getPossesseur().aCouleur(premiereCarte.getCouleur()) == false
				&& carte.getPossesseur().aAtout(pli.getTour()) == false) {
			return true;
		}

		return false;
	}

}
