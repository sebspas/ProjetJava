package parking.business;

import parking.exception.PlaceDisponibleException;
import parking.exception.PlusAucunePlaceException;

import java.util.ArrayList;

public class Parking {
	static private int numeroPlace = 0, nbPlacesMax;

	private static String nom;
	private static ArrayList<Place> listeVehicules = new ArrayList<Place>(); // Le type de collection n'est sans doute pas d�finitif

	@Override
	public String toString() {
		return "Parking [nom=" + nom + ", listeVehicules=" + listeVehicules + "]";
	}// toString()

	/*public Parking(String nom, int nbPlacesMax) {
		this.nom = nom;
		Parking.nbPlacesMax = nbPlacesMax;
	}// Constructeur()*/

	static {
		nom = "Mon Parking";
		nbPlacesMax = 4;
	}

	public static int getNumeroPlace() {
		return numeroPlace;
	}// getNumeroPlace()
	
	public static void setNumeroPlace(int numeroPlace) {
		Parking.numeroPlace = numeroPlace;
	}// setNumeroPlace()
	
	public static int getNbPlacesMax() {
		return nbPlacesMax;
	}// getNbPlacesMax()

	public static void ajouterPlace(Place p){
		Parking.listeVehicules.add(p);
	}// ajouterPlace()

	public static boolean vehiculeExiste(Vehicule v){
		for(Place p : Parking.listeVehicules ){
			if(p.getVehicule() == v)
				return true;
		}
		return false;
	}// vehiculeExiste()

	public static Vehicule unpark(int numeroPlace) {
		for(Place p : Parking.listeVehicules ){
			if(numeroPlace == p.getNumero())
				return p.unpark();
		}
		return null;
	}// unpark()

	public static void etatParking() {
		System.out.println("Debut de l'affichage du parking !");
		for(Place p : Parking.listeVehicules ) {
			System.out.println("La place numero : " + p.getNumero() + " du type : " + p.getType());
			if (p.getVehicule() != null)
				System.out.println("La place a pour vehicule : " + p.getVehicule() + "\n");
			else
				System.out.println("La place n'a pas de vehicule ! Elle " + p.getReserver() + "\n");
		}
		System.out.println("Fin de l'affichage du parking !\n");
	} // etatParking()

	public static Place bookPlace() {
		try {
			for (Place p : Parking.listeVehicules) {
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

	public static Place freePlace(int numeroPlace) {
		try {
			for (Place p : Parking.listeVehicules) {
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

	public static int getLocation(String numeroImmatriculation) {
		for (Place p : Parking.listeVehicules) {
			if (p.getVehicule() != null && (p.getVehicule().getImmatriculation()).equals(numeroImmatriculation)) {
				return p.getNumero();
			}
		}
		return -1;
	} // getlocation()

	public static Vehicule retirerVehicule(String numeroImmatriculation) {
		int numPlace = getLocation(numeroImmatriculation);
		if (numPlace == -1)
			return null;
		else
			return unpark(numPlace);
	} // retirerVehicule()

	public static void main(String[] args) {
		// Cr�ation du parking //
		//Parking parking = new Parking("My fucking parking", 4);
		
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
		Parking.ajouterPlace(p1);
		Parking.ajouterPlace(t1);
		Parking.ajouterPlace(p2);
		Parking.ajouterPlace(t2);
		
		// Placements des v�hicules sur les places //
		p1.park(v1);
		t1.park(c1);
		p2.park(v2);
		t2.park(v3);
		
		//System.out.println(parking);
		Parking.etatParking();

		Vehicule vehiculetest = Parking.unpark(1);

		System.out.println("Le vhehicule à été retiré :" + vehiculetest + "à la place numero :" + 1);

		//System.out.println(parking);
		Parking.etatParking();

		Place p = Parking.bookPlace();

		System.out.println("Place réserver : " + p);

		Parking.etatParking();

		Parking.freePlace(1);

		Parking.etatParking();

		int numPlace = Parking.getLocation("E4IL");

		System.out.println("Le vehicule immatriculé : E4IL est garer à la place : " + numPlace);

		Parking.etatParking();

		System.out.println("On retire le vehicule immatriculé E4IL de la place trouver via getLocation");
		Vehicule vehiculeRetire = Parking.retirerVehicule("E4IL");

		Parking.etatParking();
	}

}