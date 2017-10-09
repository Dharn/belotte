package belotte;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Joueur {
	private String nom;
	private String prenom;
	private int age;
	private Equipe equipe;
	private ArrayList<Carte> main = new ArrayList<Carte>();
	private Joueur joueurSuivant;
	private boolean aPritAtout = false;
	private Ia iaJoueur = null;

	public Ia getIaJoueur() {
		return iaJoueur;
	}

	public void setIaJoueur(Ia iaJoueur) {
		this.iaJoueur = iaJoueur;
	}

	public boolean aAtout(Tour t) {
		boolean aAtout = false;
		for (Carte c : this.main) {
			if (c.getCouleur() == t.getCouleurAtout()) {
				aAtout = true;
				return aAtout;
			}
		}
		return false;
	}

	public boolean aCouleur(Couleur coul) {
		boolean couleurEnMain = false;
		for (Carte c : this.main) {
			if (c.getCouleur() == coul) {
				couleurEnMain = true;
				return couleurEnMain;
			}
		}
		return couleurEnMain;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public boolean getAPritAtout() {
		return aPritAtout;
	}

	public void setAPritAtout(boolean aPritAtout) {
		this.aPritAtout = aPritAtout;
	}

	public String getNom() {
		return nom;
	}

	public Joueur getJoueurSuivant() {
		return joueurSuivant;
	}

	public void setJoueurSuivant(Joueur joueurSuivant) {
		this.joueurSuivant = joueurSuivant;
	}

	public Joueur(String prenom, String nom, int age) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;

	}

	public void choisirCarteAjouer(Pli pli) {
		boolean aPose = false;
		while (!aPose) {
			System.out.println(this + " choisissez une carte a jouer");
			this.afficherMain();
			pli.afficherPli();
			String nomDeCarte = null;
			if (iaJoueur == null) {
				Scanner myScanner = new Scanner(System.in);
				nomDeCarte = myScanner.nextLine().toString();
			} else {
				nomDeCarte = iaJoueur.choisirCarteAPoser(pli, this.main).toString();
			}

			// String nomDeCarte = main.get(0).toString();
			for (Carte carte : main) {
				if (nomDeCarte.compareTo(carte.toString()) == 0) {
					aPose = verifierEtPoserCarte(carte, pli);
					break;
				}
			}
		}
	}

	public boolean verifierEtPoserCarte(Carte carte, Pli pli) { // true si carte
																// posable,
																// false sinon
		if (pli.getTour().getJeu().estAutorise(pli, carte, main) == true) {
			poserCarte(carte, pli);
			return true;
		}
		return false;

	}

	public void poserCarte(Carte c, Pli pli) {
		if (main.contains(c)) {
			pli.addCarteJouee(c);
			retirerCarte(c);
		}
	}

	public void donnerCarte(Carte c) {
		c.setPossesseur(this);
		main.add(c);
	}

	public Carte retirerCarte() {
		if (main.size() > 0) {
			Carte c = main.get(0);
			main.remove(0);
			return c;
		} else {
			return null;
		}
	}

	public void retirerCarte(Carte c) {
		if (main.contains(c)) {
			main.remove(c);
		}
	}

	public int getNbCarte() {
		return main.size();
	}

	public void afficherMain() {
		System.out.println("la main de " + this);
		for (Carte carte : main) {
			System.out.print(carte + " | ");

		}
		System.out.println();
		// System.out.println("nb carte : "+ main.size());
	}

	public boolean choisirAtout(Carte laRetourne) {
		System.out.println(this + ", voulez vous prendre atout ? ");
		String mot = null;
		if (iaJoueur == null) {
			Scanner myScanner = new Scanner(System.in);
			mot = myScanner.nextLine().toString();
		} else {
			mot = iaJoueur.choisirAtout(main, laRetourne).toString();
		}

		if (mot.toLowerCase().compareTo("oui") == 0 || mot.toLowerCase().compareTo("yes") == 0) {
			this.aPritAtout = true;
			return true;
		}
		return false;
	}

	public Couleur choisirCouleurAtout() {
		String mot = null;
		if (iaJoueur == null) {
			System.out.println(this + ", indiquez la couleur a laquelle vous voulez prendre. N'indiquez rien sinon. ");
			Scanner myScanner = new Scanner(System.in);
			mot = myScanner.nextLine().toString();
		} else {
			mot = iaJoueur.choisirAtoutCouleur(main).toString();
		}

		for (Couleur couleur : Couleur.values()) {
			if (mot.toLowerCase().compareTo(couleur.toString()) == 0) {
				this.aPritAtout = true;
				return couleur;
			}
		}
		return null;
	}

	public String toString() {
		return prenom + " " + nom;
	}

	public void couper(ArrayList<Carte> paquetACouper) {
		int randint;
		Random rand = new Random();
		randint = rand.nextInt(paquetACouper.size());
		ArrayList<Carte> paquetCoupe = new ArrayList<Carte>();

		for (int idx_carte = randint; idx_carte < paquetACouper.size(); idx_carte++) {
			paquetCoupe.add(paquetACouper.get(idx_carte));
		}

		for (int idx_carte = 0; idx_carte < randint; idx_carte++) {
			paquetCoupe.add(paquetACouper.get(idx_carte));
		}
		paquetCoupe = paquetACouper;
	}

}
