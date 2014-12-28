package parking.business;

import parking.exception.PlaceDisponibleException;
import parking.exception.PlusAucunePlaceException;

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
				System.out.println("La place n'a pas de vehicule ! Elle " + p.getReserver() + "\n");
		}
		System.out.println("Fin de l'affichage du parking !\n");
	} // etatParking()

	public Place bookPlace() {
		try {
			for (Place p : this.listeVehicules) {
				if (p.getVehicule() == null) {
					p.setReservation(true);
					return p;
				}
			}
			throw new PlusAucunePlaceException();
		}
		catch (PlusAucunePlaceException e) {
			System.out.println("Aucune place disponble");
			return null;
		}
	} // bookPlace()

	public Place freePlace(int numeroPlace) {
		try {
			for (Place p : this.listeVehicules) {
				if (p.getNumero() == numeroPlace ) {
					if (p.getReservation()) {
						p.setReservation(false);
						return p;
					}
					else
						throw new PlaceDisponibleException();
				}
			}
			return null;
		}
		catch (PlaceDisponibleException e) {
			System.out.println("place déja disponible ! (pas réservée)");
			return null;
		}
	} // freePlace()

	public int getLocation(String numeroImmatriculation) {
		for (Place p : this.listeVehicules) {
			if (p.getVehicule() != null && (p.getVehicule().getImmatriculation()).equals(numeroImmatriculation)) {
				return p.getNumero();
			}
		}
		return -1;
	} // getlocation()

	public Vehicule retirerVehicule(String numeroImmatriculation) {
		int numPlace = getLocation(numeroImmatriculation);
		if (numPlace == -1)
			return null;
		else
			return unpark(numPlace);
	} // retirerVehicule()

	public static void main(String[] args) {
		// Cr�ation du parking //
		Parking parking = new Parking("My fucking parking", 4);
		
		// Cr�ation des places //
		Particulier p1 = new Particulier();
		Particulier p2 = new Particulier();
		Transporteur t1 = new Transporteur();
		Transporteur t2 = new Transporteur();

		// Cr�ation des v�hicules //
		Vehicule v1 = new Vehicule("E4IL", "Sitrohaine", "NTM", "Voili Voilou","Voiture");
		Vehicule v2 = new Vehicule("R3T4RD", "Beta Juliette", "LMAO", "Titi Tata","Voiture");
		Vehicule v3 = new Vehicule("KDNAPPR", "Pherrary", "SWAG", "Claude Fran�ois","Voiture");
		Camion c1 = new Camion("S0L31L", "Porschiaaaaa", "YOLO", "Toto Tata", "Camion",15, 355);
		
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

		Place p = parking.bookPlace();

		System.out.println("Place réserver : " + p);

		parking.etatParking();

		parking.freePlace(1);

		parking.etatParking();

		int numPlace = parking.getLocation("E4IL");

		System.out.println("Le vehicule immatriculé : E4IL est garer à la place : " + numPlace);

		parking.etatParking();

		System.out.println("On retire le vehicule immatriculé E4IL de la place trouver via getLocation");
		Vehicule vehiculeRetire = parking.retirerVehicule("E4IL");

		parking.etatParking();
	}

}