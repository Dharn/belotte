package belotte;

public class Belotte {

	public static void main(String[] args) {

		Joueur j1 = new Joueur("Bertrand", "Nie", 30);
		Joueur j2 = new Joueur("Gerard", "Nie", 35);
		Joueur j3 = new Joueur("Alexis", "Mike", 22);
		Joueur j4 = new Joueur("Alice", "Lia", 25);
		Equipe e1 = new Equipe("winner", j1, j2);
		Equipe e2 = new Equipe("yeah", j3, j4);

		new Ia(j1,"facile");
		new Ia(j2,"facile");
		Interface interf = new Interface();
		interf.setEmptyLabel("lol");
		new Ia(j3,"difficile");
		new Ia(j4,"difficile");
		//Interface interf = new Interface();
		//interf.setEmptyLabel("lol");
		new Jeu(e1, e2);

	}

}
