package belotte;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
	private String nom;
	private String prenom;
	private int age;
	private ArrayList<Carte> main = new ArrayList<Carte>();
	private Joueur joueurSuivant;
	private boolean aPritAtout=false;
	
	
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
	
	
	public Joueur(String prenom, String nom, int _age) {
		this.nom = nom;
		this.prenom = prenom;
		age = age;

	}

	public void jouerCarte(Carte c,Pli pli){
		if (main.contains(c)) {
			pli.addCarteJouee(c);
			retirerCarte(c);
		}
	}
	
	public void donnerCarte(Carte c){
		main.add(c);
	}
	public Carte retirerCarte(){
		if (main.size()>0) {
			Carte c = main.get(0);
			main.remove(0);
			return c;
		}
		else {
			return null;
		}
	}
	
	public void retirerCarte(Carte c){
		if (main.contains(c)) {
			main.remove(c);
		}
	}
	
	public int getNbCarte(){
		return main.size();
	}

	public void afficherMain(){
		System.out.println("la main de "+this);
		for (Carte carte : main) {
			System.out.print(carte+" | ");
		}
		System.out.println();
	}
	
	public boolean choisirAtout(){
		System.out.println(this+", voulez vous prendre atout ? ");
		Scanner myScanner = new Scanner(System.in);
		String mot = myScanner.nextLine().toString();
		if (mot.toLowerCase().compareTo("oui")==0 || mot.toLowerCase().compareTo("yes")==0) {
			this.aPritAtout = true;
			return true;
		}
		return false;
	}
	
	public Couleur choisirCouleurAtout(){
		System.out.println(this+", indiquez la couleur a laquelle vous voulez prendre. N'indiquez rien sinon. ");
		Scanner myScanner = new Scanner(System.in);
		String mot = myScanner.nextLine().toString();
		for (Couleur couleur : Couleur.values()) {
			if (mot.toLowerCase().compareTo(couleur.toString())==0) {
				this.aPritAtout = true;
				return couleur;
			}
		}
		return null;
	}
	
	public String toString(){
		return prenom+" "+nom;
	}
	
	
	
}
