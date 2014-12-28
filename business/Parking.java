package parking.business;

import java.util.ArrayList;

public class Parking {
	static private int numeroPlace = 0, nbPlacesMax;

	private String nom;
	private ArrayList<Place> listeVehicules = new ArrayList<Place>(); // Le type de collection n'est sans doute pas d�finitif

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

	public Vehicule unpark(int numeroPlace) {
		for(Place p : this.listeVehicules ){
			if(numeroPlace == p.getNumero())
				return p.unpark();
		}
		return null;
	}// unpark()

	public void etatParking() {
		System.out.println("Debut de l'affichage du parking !");
		for(Place p : this.listeVehicules ) {
			System.out.println("La place numero : " + p.getNumero() + " du type : " + p.getType());
			if (p.getVehicule() != null)
				System.out.println("La place a pour vehicule : " + p.getVehicule() + "\n");
			else
				System.out.println("La place n'a pas de vehicule !\n");
		}
		System.out.println("Fin de l'affichage du parking !\n");
	}

	public static void main(String[] args) {
		// Cr�ation du parking //
		Parking parking = new Parking("My fucking parking", 4);
		
		// Cr�ation des places //
		Particulier p1 = new Particulier();
		Particulier p2 = new Particulier();
		Transporteur t1 = new Transporteur();
		Transporteur t2 = new Transporteur();

		// Cr�ation des v�hicules //
		Vehicule v1 = new Vehicule("E4IL", "Sitrohaine", "NTM", "Voili Voilou");
		Vehicule v2 = new Vehicule("R3T4RD", "Beta Juliette", "LMAO", "Titi Tata");
		Vehicule v3 = new Vehicule("KDNAPPR", "Pherrary", "SWAG", "Claude Fran�ois");
		Camion c1 = new Camion("S0L31L", "Porschiaaaaa", "YOLO", "Toto Tata", 15, 355);
		
		// Ajout des places au parking //
		parking.ajouterPlace(p1);
		parking.ajouterPlace(t1);
		parking.ajouterPlace(p2);
		parking.ajouterPlace(t2);
		
		// Placements des v�hicules sur les places //
		p1.park(v1);
		t1.park(c1);
		p2.park(v2);
		t2.park(v3);
		
		//System.out.println(parking);
		parking.etatParking();

		Vehicule vehiculetest = parking.unpark(1);

		System.out.println("Le vhehicule à été retiré :" + vehiculetest + "à la place numero :" + 1);

		//System.out.println(parking);
		parking.etatParking();
	}

}