package belotte;
public class Carte {
	private String nom;
	private Couleur couleur;
	private Integer valeur;
	private int valeurAtout;
	private Joueur possesseur;

	public int getValeurAtout() {
		return valeurAtout;
	}

	public String getNom() {
		return nom;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public int getValeur() {
		return valeur;
	}

	public Joueur getPossesseur() {
		return possesseur;
	}

	public void setPossesseur(Joueur possesseur) {
		this.possesseur = possesseur;
	}

	public Carte(String nom, Couleur couleur, int valeur, int valeurAtout) {
		this.nom = nom;
		this.couleur = couleur;
		this.valeur = valeur;
		this.valeurAtout = valeurAtout;
	}
	
	public String toString() {
        return nom+" de "+couleur;
    }

}
