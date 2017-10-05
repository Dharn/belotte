package belotte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Jeu {

	private ArrayList<Carte> paquetDeCarte = new ArrayList<Carte>();
	private ArrayList<Equipe> equipes = new ArrayList<Equipe>();
	private Joueur joueurAQuiDistribuer;

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
		preparerCartes();
		melangerPaquetDeCarte();
		distribuerCinqCarte();
		for (Equipe equipe : equipes) {
			for (Joueur joueur : equipe.getListeJoueur()) {
				joueur.afficherMain();
			}
		}
		tourChoixAtout();
		distribuerApresAtout();
		for (Equipe equipe : equipes) {
			for (Joueur joueur : equipe.getListeJoueur()) {
				joueur.afficherMain();
			}
		}
	}

	
	public void tourChoixAtout (){
		Joueur joueurChoix= joueurAQuiDistribuer;
		System.out.println("La retourne : "+paquetDeCarte.get(0));
		for (int i = 0; i < 4; i++) {
			if (joueurChoix.choisirAtout()) {
				joueurChoix.donnerCarte(paquetDeCarte.get(0));
				paquetDeCarte.remove(0);
				return;
			}
			joueurChoix= joueurChoix.getJoueurSuivant();
			
		}
		for (int i = 0; i < 4; i++) {
			if (joueurChoix.choisirCouleurAtout()!=null) {
				joueurChoix.donnerCarte(paquetDeCarte.get(0));
				paquetDeCarte.remove(0);
				return;
			}
			joueurChoix= joueurChoix.getJoueurSuivant();
			
		}
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
	private void distribuerNCarte(int n){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < n; j++) {
				this.getJoueurAQuiDistribuer().donnerCarte(paquetDeCarte.get(0));
				paquetDeCarte.remove(0);
				setJoueurAQuiDistribuer(getJoueurAQuiDistribuer().getJoueurSuivant());
				
			}
			
		}
	}
	public void distribuerCinqCarte() {
		if (paquetDeCarte.size()<32) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < joueurAQuiDistribuer.getNbCarte(); j++) {
					paquetDeCarte.add(joueurAQuiDistribuer.retirerCarte());
					setJoueurAQuiDistribuer(getJoueurAQuiDistribuer().getJoueurSuivant());
				}
			}
		}
		this.distribuerNCarte(3);
		this.distribuerNCarte(2);
		
	}
	
	public void distribuerApresAtout(){
		for (int i = 0; i < 4; i++) {
			if (joueurAQuiDistribuer.getAPritAtout()) {
				for (int j = 0; j < 2; j++) {
					joueurAQuiDistribuer.donnerCarte(paquetDeCarte.get(0));
					paquetDeCarte.remove(0);					
				}
				setJoueurAQuiDistribuer(joueurAQuiDistribuer.getJoueurSuivant());
			}
			else {
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
	
}
