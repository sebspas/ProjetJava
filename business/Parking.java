package parking.business;

import java.util.ArrayList;

public class Parking {
	static private int numeroPlace = 0, nbPlacesMax;

	private String nom;
	private ArrayList<Place> listeVehicules = new ArrayList<Place>(); // Le type de collection n'est sans doute pas définitif

	@Override
	public String toString() {
		return "Parking [nom=" + nom + ", listeVehicules=" + listeVehicules + "]";
	}// toString()

	public Parking(String nom, int nbPlacesMax) {
		this.nom = nom;
		Parking.nbPlacesMax = nbPlacesMax;
	}// Constructeur()

	public static int getNumeroPlace() {
		return numeroPlace;
	}// getNumeroPlace()
	
	public static void setNumeroPlace(int numeroPlace) {
		Parking.numeroPlace = numeroPlace;
	}// setNumeroPlace()
	
	public static int getNbPlacesMax() {
		return nbPlacesMax;
	}// getNbPlacesMax()

	public void ajouterPlace(Place p){
		this.listeVehicules.add(p);
	}// ajouterPlace()

	public boolean vehiculeExiste(Vehicule v){
		for(Place p : this.listeVehicules ){
			if(p.getVehicule() == v)
				return true;
		}
		return false;
	}// vehiculeExiste()
	
	public static void main(String[] args) {
		// Création du parking //
		Parking parking = new Parking("My fucking parking", 4);
		
		// Création des places //
		Particulier p1 = new Particulier();
		Particulier p2 = new Particulier();
		Transporteur t1 = new Transporteur();
		Transporteur t2 = new Transporteur();

		// Création des véhicules //
		Vehicule v1 = new Vehicule("E4IL", "Sitrohaine", "NTM", "Voili Voilou");
		Vehicule v2 = new Vehicule("R3T4RD", "Beta Juliette", "LMAO", "Titi Tata");
		Vehicule v3 = new Vehicule("KDNAPPR", "Pherrary", "SWAG", "Claude François");
		Camion c1 = new Camion("S0L31L", "Porschiaaaaa", "YOLO", "Toto Tata", 15, 355);
		
		// Ajout des places au parking //
		parking.ajouterPlace(p1);
		parking.ajouterPlace(t1);
		parking.ajouterPlace(p2);
		parking.ajouterPlace(t2);
		
		// Placements des véhicules sur les places //
		p1.park(v1);
		t1.park(c1);
		p2.park(v2);
		t2.park(v3);
		
		System.out.println(parking);
	}

}