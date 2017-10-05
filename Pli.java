package belotte;

import java.util.ArrayList;

public class Pli {
	private ArrayList<Carte> cartesJouees = new ArrayList<Carte>();
	
	
	public ArrayList<Carte> getCartesJouees() {
		return cartesJouees;
	}

	public Pli(){
		
	}
	
	public void addCarteJouee(Carte c){
		cartesJouees.add(c);
	}
	
	
	
	
}
