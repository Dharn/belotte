package belotte;
// Salut
public class Belotte {

	public static void main(String[] args) {
		
		Joueur j1 = new Joueur("Bertrand", "Nie", 30);
		Joueur j2 = new Joueur("Gerard", "Nie", 35);
		Joueur j3 = new Joueur("Alexis", "Mike", 22);
		Joueur j4 = new Joueur("Alice", "Lia", 25);
		Equipe e1 = new Equipe("winner",j1,j2);
		Equipe e2 = new Equipe("yeah",j3,j4);
		
		new Jeu(e1,e2);

	}

}
