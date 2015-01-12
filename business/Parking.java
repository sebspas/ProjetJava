package parking.business;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.exception.*;
import parking.gui.Vue;
import java.util.ArrayList;
import java.util.Date;

public class Parking {
	/***************************************************************/
	/*						Debut Donnees Membres 				   */
	/***************************************************************/
	/**
	 * Numero de place, servant a remplir le parking. Il permet de savoir le nulmero de la derniere place ajoutee.
	 */
	static private int numeroPlace = 0;

	/**
	 * Nombre de place maximum du parking.
	 */
	static private int nbPlacesMax;

	/**
	 * Nom du parking.
	 */
	private static String nom;

	/**
	 * Liste des vehicule du parking.
	 * Type de collection a definir ...
	 */
	private static ArrayList<Place> listeVehicules = new ArrayList<Place>();

	private static ArrayList<Client> listeClients = new ArrayList<Client>();

	private static ArrayList<Facture> listeFacture = new ArrayList<Facture>();

	private static ArrayList<Vue> listeVue = new ArrayList<Vue>();

	private static double tarif_particulier;

	private static double tarif_transporteur;

	private static int numeroFacture;

	private static boolean appelInterne;

	/**
	 * Initialisation des informations generales du parking. En static car le parking est unique.
	 */
	static {
		nom = "Mon Parking";
		nbPlacesMax = 20;
		numeroFacture = 0;
		tarif_particulier = 1;
		tarif_transporteur = 1.5;
		appelInterne = false;
	}


	/***************************************************************/
	/*						Getter								   */
	/***************************************************************/
	/**
	 * Methode renvoyant le numero de la derniere place ajoutee au parking.
	 *
	 * @return le numero de la derniere place ajoutee.
	 */
	public static int getNumeroPlace() {
		return numeroPlace;
	}// getNumeroPlace()

	/**
	 * Methode renvoyant le nombre de place maximal du parking.
	 *
	 * @return Nombre de place max du parking.
	 */
	public static int getNbPlacesMax() {
		return nbPlacesMax;
	}// getNbPlacesMax()

	public static ArrayList<Place> getListeVehicules() { return listeVehicules; }

	public static double getTarif_transporteur() { return tarif_transporteur; }

	public static double getTarif_particulier() { return tarif_particulier; }

	public static int getNumeroFacture() {return numeroFacture; }

	/***************************************************************/
	/*						Setter								   */
	/***************************************************************/
	/**
	 * Methode permettant de modifier la valeur de la derniere place ajoutee.
	 *
	 * @param numeroPlace
	 * 		Numero de la derniere place ajouter au parking.
	 */
	public static void setNumeroPlace(int numeroPlace) {
		Parking.numeroPlace = numeroPlace;
	}// setNumeroPlace()

	public static void setTarif_transporteur(double tarif_transporteur) {
		Parking.tarif_transporteur = tarif_transporteur;
	}

	public static void setTarif_particulier(double tarif_particulier) {
		Parking.tarif_particulier = tarif_particulier;
	}

	public static void setNumeroFacture(int numeroFacture) {
		Parking.numeroFacture = numeroFacture;
	}

	public static void addFacture(Facture facture) {
		listeFacture.add(facture);
	}

	/***************************************************************/
	/*						Methodes							   */
	/***************************************************************/

	public static void addVue(Vue v) {
		listeVue.add(v);
	}
	public static void notifier() {
		for (Vue v : listeVue) {
			System.out.println("Modification notifier");
			v.mettreAJour();
		}
	}

	public static void addClient(Client c){
		listeClients.add(c);
	}

	/**
	 * Methode toString() permettant de connaitre toutes les informations detaillees sur le parking.
	 *
	 * @return rencoie une chaine de caracteres contenant les informatiosn sur le parking.
	 */
	@Override
	public String toString() {
		return "Parking [nom=" + nom + ", listeVehicules=" + listeVehicules + "]";
	}// toString()

	/**
	 * Methode permettant d'ajouter une place dans le parking.
	 *
	 * @param p
	 * 		la place a ajoutee.
	 */
	public static void ajouterPlace(Place p){
		try {
			if(Parking.getNbPlacesMax() == Parking.getNumeroPlace())
				throw new NombrePlacesMaxException();
			p.setNumero(Parking.getNumeroPlace());
			Parking.setNumeroPlace(Parking.getNumeroPlace()+1);
			Parking.listeVehicules.add(p);
			notifier();
		}
		catch (NombrePlacesMaxException e) {
			System.out.println("Le parking a atteint le nombre maximal de places");
		}
	}// ajouterPlace()

	/**
	 * Methode testant l'existance d'un vehicule dans le parking.
	 *
	 * @param v
	 * 		Vehicule a recherche dans le parking.
	 * @return Renvoie un booleen indiquant si le vehicule est present ou non.
	 */
	public static boolean vehiculeExiste(Vehicule v){
		for(Place p : Parking.listeVehicules ){
			if(p.getVehicule() == v)
				return true;
		}
		return false;
	}// vehiculeExiste()

	public static ArrayList<Facture> getListeFacture() {
		return listeFacture;
	}

	/**
	 * Methode permettant de retirer un vehicule de sa place sur le parking.
	 *
	 * @param numeroPlace
	 * 			Numero de la place ou retirer le vehicule.
	 * @return renvoie le vehicule retire.
	 */
	public static Vehicule unpark(int numeroPlace) {
		for(Place p : Parking.listeVehicules ){
			if (numeroPlace == p.getNumero())
				try {
					if (!appelInterne) {
						Parking.addFacture(new Facture(p));
					}
					return p.retirerVehicule();
				}
				catch (PlaceLibreException e) {
					System.out.println("La place est déja vide !");
					return null;
				}
				finally {
					notifier();
				}
		}
		return null;
	}// unpark()

	/**
	 * Methode permettant de garrer un vehicule sur une place de parking. La fonction va
	 * chercher la place du meme type que le vehicule a garer, sinon elle le placera sur une place
	 * du type transporteur si il y en a une de disponible.
	 *
	 * @param vehicule
	 * 		Vehicule a garer su rle parking.
	 */
	public static void park(Vehicule vehicule) {
		try {
			String typePlace = "Transporteur";
			if (vehicule.getType().equals("Voiture")) {
				typePlace = "Particulier";
			}
			for (Place p : Parking.listeVehicules) {
				if (p.getVehicule() == null && !(p.getReservation())) {
					if (p.getType().equals(typePlace)) {
						p.setVehicule(vehicule);
						if (!appelInterne)
							p.getVehicule().setDateArrivee(new Date());
						return;
					}
				}
			}
			for (Place p : Parking.listeVehicules) {
				if (p.getVehicule() == null && !(p.getReservation())) {
					p.setVehicule(vehicule);
					return;
				}
			}
			throw new PlusAucunePlaceException();
		}
		catch(PlaceOccupeeException e){
			System.out.println("La place est d�j� occup�e et/ou n'est pas adapt�e � ce v�hicule");
		}
		catch (PlaceReserverException e) {
			System.out.println("La place " + numeroPlace + " est réservée !");
		}
		catch (PlusAucunePlaceException e) {
			System.out.println("Le parking est complet !");
		}
		finally {
			notifier();
		}
	} // park()

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

	/**
	 * Methode permettant de reserver une place disponible sur le parking
	 *
	 * @return La place reserver.
	 */
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
		finally {
			notifier();
		}
	} // bookPlace()

	/**
	 * Methode permettant de liberer une place reserver sur le parking.
	 *
	 * @param numeroPlace
	 * 		Numero de la place a dereserver.
	 * @return la place de nouveau libre.
	 */
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
		finally {
			notifier();
		}
	} // freePlace()

	/**
	 * Permet de connaitre la place ou se situe un vehicule a partir de son numero d'immatriculation.
	 *
	 * @param numeroImmatriculation
	 * 			numero d'immatriculation du vehicule a recherche.
	 * @return le numero ou le vehicule se trouve sur le parking.
	 */
	public static int getLocation(String numeroImmatriculation) {
		for (Place p : Parking.listeVehicules) {
			if (p.getVehicule() != null && (p.getVehicule().getImmatriculation()).equals(numeroImmatriculation)) {
				return p.getNumero();
			}
		}
		return -1;
	} // getlocation()

	/**
	 * Methode permettant de retirer un vehicule d'une place a partir de son numero d'immatriculation.
	 *
	 * @param numeroImmatriculation
	 * 			Numero d'immatriculation du vehicule a retirer.
	 * @return le vehivule retirer de sa place de parking.
	 */
	public static Vehicule retirerVehicule(String numeroImmatriculation) {
		int numPlace = getLocation(numeroImmatriculation);
		if (numPlace == -1)
			return null;
		else
			return unpark(numPlace);
	} // retirerVehicule()

	/**
	 * Methode reorganisant les places de parking apres le depart d'un vehicule.
	 * par exemple si une place du type particulier c'est liberer et q'une voiture et sur une
	 * place du type camion , on va alors la deplacer sur la place libre.
	 */
	public static void reorganiserPlaces() {
		appelInterne = true;
		for (Place p : Parking.listeVehicules) {
			if (p.getType().equals("Transporteur") && p.getVehicule() != null) {
				if (p.getVehicule().getType().equals("Voiture")) {
					Vehicule vretire = unpark(p.getNumero());
					Parking.park(vretire);
				}
			}
		}
		notifier();
		appelInterne = false;
	} // reorganiserPlaces()

	/***************************************************************/
	/*						Main							  	   */
	/***************************************************************/
	/*public static void main(String[] args) {
		//new ParkingIHM();
		// Cr�ation du parking //
		//Parking parking = new Parking("My fucking parking", 4);
		
		// Cr�ation des places //
		Place p1 = new Place("Particulier");
		Place p2 = new Place("Particulier");
		Place t1 = new Place("Transporteur");
		Place t2 = new Place("Transporteur");

		// Cr�ation des v�hicules //
		Vehicule v1 = new Voiture("E4IL", "Sitrohaine", "NTM", "Voili Voilou");
		Vehicule v2 = new Voiture("R3T4RD", "Beta Juliette", "LMAO", "Titi Tata");
		Vehicule v3 = new Voiture("KDNAPPR", "Pherrary", "SWAG", "Claude Francois");
		Vehicule c1 = new Camion("S0L31L", "Porschiaaaaa", "YOLO", "Toto Tata",15, 355);
		
		// Ajout des places au parking //
		Parking.ajouterPlace(p1);
		Parking.ajouterPlace(t1);
		Parking.ajouterPlace(p2);
		Parking.ajouterPlace(t2);

		System.out.println("===================================Premier affichage parking vide===========================================");
		Parking.etatParking();

		// Placements des v�hicules sur les places //
		Parking.park(v1);
		Parking.park(c1);
		Parking.park(v2);
		Parking.park(v3);

		System.out.println("===================================Second affichage parking remplit===========================================");
		Parking.etatParking();

		Vehicule vehiculetest = Parking.unpark(1);

		System.out.println("Le vhehicule à été retiré :" + vehiculetest + "à la place numero :" + 1);

		System.out.println("===================================Troisieme affichage sans le vehicule retiré===========================================");
		Parking.etatParking();

		Place p = Parking.bookPlace();

		System.out.println("Place réserver : " + p);

		System.out.println("===================================Quatireme affichage après reservation d'une place===========================================");
		Parking.etatParking();

		Parking.freePlace(1);
		System.out.println("===================================Cinquieme affichage après dereservation de la place reservée===========================================");
		Parking.etatParking();

		int numPlace = Parking.getLocation("E4IL");

		System.out.println("Le vehicule immatriculé : E4IL est garer à la place : " + numPlace);
		System.out.println("On retire le vehicule immatriculé E4IL de la place trouver via getLocation");
		Vehicule vehiculeRetire = Parking.retirerVehicule("E4IL");
		System.out.println("===================================Sixieme affichage après avoir retiré le vehicule de la place trouvée===========================================");
		Parking.etatParking();

		Parking.reorganiserPlaces();

		System.out.println("===================================Septieme affichage après avoir reorganiser le parking===========================================");
		Parking.etatParking();
	} // main()*/

} // Parking class