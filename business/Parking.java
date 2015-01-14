package parking.business;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.facture.Facture;
import parking.business.vehicule.Vehicule;
import parking.exception.business.*;
import parking.gui.Vue;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class Parking permettant de creer le parking selon des criteres tels que le numero d'une
 * place (derniere place ajoutee), le nombre de place maximum, le nom du parking, etc..
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class Parking {
	/***************************************************************/
	/*						Debut Donnees Membres 				   */
	/***************************************************************/
	/**
	 * Le numero d'une place, servant a remplir le parking.
	 * Il permet de savoir le numero de la derniere place ajoutee.
	 */
	static private int numeroPlace = 0;

	/**
	 * Le nombre de place maximum du parking.
	 */
	static private int nbPlacesMax;

	static private int nbVehicule;
	/**
	 * Le nom du parking.
	 */
	private static String nom;

	/**
	 * La liste des vehicule du parking.
	 * Type de collection a definir ...
	 */
	private static ArrayList<Place> listePlaces = new ArrayList<Place>();

	/**
	 * La liste de tous les clients.
	 * Type de collection a definir ...
	 */
	private static ArrayList<Client> listeClients = new ArrayList<Client>();

	/**
	 * La liste de toutes les factures.
	 * Type de collection a definir ...
	 */
	private static ArrayList<Facture> listeFacture = new ArrayList<Facture>();

	/**
	 * La liste de toute les vues.
	 * Type de collection a definir ...
	 */
	private static ArrayList<Vue> listeVue = new ArrayList<Vue>();

	/**
	 * Le tarif d'une place de type particulier.
	 */
	private static double tarif_particulier;

	/**
	 * Le tarif d'une place de type transporteur.
	 */
	private static double tarif_transporteur;

	/**
	 * Le numero de la facture associee a un client.
	 */
	private static int numeroFacture;

	/**
	 * Un booleen permettant de savoir si une fonction a ete appele en interne ou non.
	 */
	private static boolean appelInterne;

	/**
	 * Initialisation des informations generales du parking. Statiquement car le parking est unique.
	 */
	static {
		nom = "Mon Parking";
		nbPlacesMax = 25;
		numeroFacture = 0;
		tarif_particulier = 1;
		tarif_transporteur = 1.5;
		appelInterne = false;
	}

	/***************************************************************/
	/*						Getter								   */
	/***************************************************************/
	public static int getNbVehicule() {
		return nbVehicule;
	}

	/**
	 * Methode renvoyant le numero de la derniere place ajoutee au parking.
	 *
	 * @return le numero de la derniere place ajoutee.
	 */
	public static int getNumeroPlace() {
		return numeroPlace;
	} // getNumeroPlace()

	/**
	 * Methode renvoyant le nombre de place maximal du parking.
	 *
	 * @return Nombre de place max du parking.
	 */
	public static int getNbPlacesMax() {
		return nbPlacesMax;
	} // getNbPlacesMax()

	/**
	 * Methode getTarif_transporteur() renvoie le tarif d'une place de type transporteur.
	 *
	 * @return Le tarif d'une place de type transporteur.
	 */
	public static double getTarif_transporteur() { return tarif_transporteur; } // getTarif_transporteur()

	/**
	 * Methode getTarif_particulier() renvoie le tarif d'une place de type particulier.
	 *
	 * @return Le tarif d'une place de type particulier.
	 */
	public static double getTarif_particulier() { return tarif_particulier; } // getTarif_particulier()

	/**
	 * Methode getNumeroFacture() renvoie le numero de la facture.
	 *
	 * @return Le numero de la facture.
	 */
	public static int getNumeroFacture() { return numeroFacture; } // getNumeroFacture()

	/**
	 * Methode getLocation() permet de connaitre la place ou se situe un vehicule a partir de son numero d'immatriculation.
	 *
	 * @param numeroImmatriculation
	 * 			numero d'immatriculation du vehicule a recherche.
	 * @return le numero ou le vehicule se trouve sur le parking.
	 */
	public static int getLocation(String numeroImmatriculation) {
		for (Place p : Parking.listePlaces) {
			if (p.getVehicule() != null && (p.getVehicule().getImmatriculation()).equals(numeroImmatriculation)) {
				return p.getNumero();
			}
		}
		return -1;
	} // getLocation()

	/**
	 * Methode getListeClients() renvoie la liste des clients.
	 *
	 * @return La liste des clients.
	 */
	public static ArrayList<Client> getListeClient() { return listeClients; } // getListeClients()

	/**
	 * Methode getListePlaces() renvoie la liste des vehicules existants.
	 *
	 * @return La liste des vehicules existants.
	 */
	public static ArrayList<Place> getListePlaces() { return listePlaces; } // getListePlaces()

	/**
	 * Methode getListeFacture() renvoie la liste de toutes les factures.
	 *
	 * @return La liste des factures.
	 */
	public static ArrayList<Facture> getListeFacture() { return listeFacture; } // getListeFacture()

	/***************************************************************/
	/*						Setter								   */
	/***************************************************************/
	/**
	 * Methode setNumeroPlace modifie la valeur de la derniere place ajoutee.
	 *
	 * @param numeroPlace
	 * 		Numero de la derniere place ajouter au parking.
	 */
	public static void setNumeroPlace(int numeroPlace) {
		Parking.numeroPlace = numeroPlace;
	}// setNumeroPlace()

	/**
	 * Methode setTarif_transporteur() modifie le tarif d'une place de type transporteur.
	 *
	 * @param tarif_transporteur
	 * 			Le tarif d'une place de type transporteur.
	 */
	public static void setTarif_transporteur(double tarif_transporteur) {
		Parking.tarif_transporteur = tarif_transporteur;
	}

	/**
	 * Methode setTarif_particulier() modifie le tarif d'une place de type particulier.
	 *
	 * @param tarif_particulier
	 * 			Le tarif d'une place de type particulier.
	 */
	public static void setTarif_particulier(double tarif_particulier) {
		Parking.tarif_particulier = tarif_particulier;
	}

	/**
	 * Methode setNumeroFacture() modifie le numero de la facture.
	 *
	 * @param numeroFacture
	 * 			Le numero de la facture.
	 */
	public static void setNumeroFacture(int numeroFacture) {
		Parking.numeroFacture = numeroFacture;
	}

	/***************************************************************/
	/*						Methodes							   */
	/***************************************************************/
	/**
	 * Methode addClient() ajoute un client a la liste des clients.
	 *
	 * @param c
	 * 			Le client a ajouter.
	 */
	public static void addClient(Client c){
		listeClients.add(c);
	}

	/**
	 * Methode addFacture() ajoute une facture a la liste des factures.
	 *
	 * @param facture
	 * 			La facture a ajouter.
	 */
	public static void addFacture(Facture facture) {
		listeFacture.add(facture);
	}

	/**
	 * Methode addVue() ajoute une vue a la liste des vues.
	 *
	 * @param v
	 * 			La vue a ajouter.
	 */
	public static void addVue(Vue v) {
		listeVue.add(v);
	} // addVue()

	/**
	 * Methode notifier() affiche un message a chaque vue de la liste des vues.
	 */
	public static void notifier() {
		for (Vue v : listeVue) {
			System.out.println("Modification notifier");
			v.mettreAJour();
		}
	} // notifier()

	/**
	 * Methode toString() permettant de connaitre toutes les informations detaillees sur le parking.
	 *
	 * @return rencoie une chaine de caracteres contenant les informatiosn sur le parking.
	 */
	@Override
	public String toString() {
		return "Parking [nom=" + nom + ", listePlaces=" + listePlaces + "]";
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
			Parking.listePlaces.add(p);
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
	public static boolean vehiculeGare(Vehicule v){
		for(Place p : Parking.listePlaces ){
			if(p.getVehicule() == v)
				return true;
		}
		return false;
	} // vehiculeGare()

	/**
	 * Methode unpark() permet de retirer un vehicule de sa place sur le parking.
	 *
	 * @param numeroPlace
	 * 			Numero de la place ou retirer le vehicule.
	 * @return renvoie le vehicule retire.
	 */
	public static Vehicule unpark(int numeroPlace) {
		for(Place p : Parking.listePlaces){
			if (numeroPlace == p.getNumero())
				try {
					if (!appelInterne) {
						--nbVehicule;
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
	} // unpark()

	/**
	 * Methode park() permet de garer un vehicule sur une place de parking. La fonction va
	 * chercher la place du meme type que le vehicule a garer, sinon elle le placera sur
	 * une place du type transporteur si il y en a une de disponible.
	 *
	 * @param vehicule
	 * 		Le vehicule a garer sur le parking.
	 */
	public static void park(Vehicule vehicule) {
		try {
			String typePlace = "Transporteur";
			if (vehicule.getType().equals("Voiture")) {
				typePlace = "Particulier";
			}
			for (Place p : Parking.listePlaces) {
				if (p.getVehicule() == null && !(p.getReservation())) {
					if (p.getType().equals(typePlace)) {
						p.setVehicule(vehicule);
						if (!appelInterne) {
							++nbVehicule;
							p.getVehicule().setDateArrivee(new Date());
						}
						return;
					}
				}
			}
			for (Place p : Parking.listePlaces) {
				if (p.getVehicule() == null && !(p.getReservation())) {
					p.setVehicule(vehicule);
					return;
				}
			}
			throw new PlusAucunePlaceException();
		}
		catch(PlaceOccupeeException e){
			System.out.println("La place est déja occupée et/ou n'est pas adaptée à ce véhicule");
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

	/**
	 * Methode etatParking() affiche l'etat d'un parking.
	 * Pour cela elle affiche, pour chaque vehicule, la
	 * place qu'il occupe, le type de cette place, etc..
	 */
	public static void etatParking() {
		System.out.println("Debut de l'affichage du parking !");
		for(Place p : Parking.listePlaces) {
			System.out.println("La place numero : " + p.getNumero() + " du type : " + p.getType());
			if (p.getVehicule() != null)
				System.out.println("La place a pour vehicule : " + p.getVehicule() + "\n");
			else
				System.out.println("La place n'a pas de vehicule ! Elle " + p.getReserver() + "\n");
		}
		System.out.println("Fin de l'affichage du parking !\n");
	} // etatParking()

	/**
	 * Methode bookPlace() permet de reserver une place disponible sur le parking.
	 *
	 * @return La place reserver.
	 */
	public static Place bookPlace() {
		try {
			for (Place p : Parking.listePlaces) {
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
	 * Methode freePlace() permet de liberer une place reserver sur le parking.
	 *
	 * @param numeroPlace
	 * 		Numero de la place a dereserver.
	 * @return La place de nouveau libre.
	 */
	public static Place freePlace(int numeroPlace) {
		try {
			for (Place p : Parking.listePlaces) {
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
			System.out.println("Place déja disponible ! (Non réservée)");
			return null;
		}
		finally {
			notifier();
		}
	} // freePlace()

	/**
	 * Methode retirerVehicule() permet de retirer un vehicule d'une place a partir de son numero d'immatriculation.
	 *
	 * @param numeroImmatriculation
	 * 			Numero d'immatriculation du vehicule a retirer.
	 * @return Le vehivule retire de sa place de parking.
	 */
	public static Vehicule retirerVehicule(String numeroImmatriculation) {
		int numPlace = getLocation(numeroImmatriculation);
		if (numPlace == -1)
			return null;
		else
			return unpark(numPlace);
	} // retirerVehicule()

	/**
	 * Methode reorganiserPlaces() reorganise les places de parking apres le depart d'un vehicule.
	 * par exemple si une place du type particulier c'est liberer et qu'une voiture et sur une
	 * place du type camion , on va alors la deplacer sur la place libre.
	 */
	public static void reorganiserPlaces() {
		appelInterne = true;
		for (Place p : Parking.listePlaces) {
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
		// Creation du parking //
		//Parking parking = new Parking("My fucking parking", 4);
		
		// Creation des places //
		Place p1 = new Place("Particulier");
		Place p2 = new Place("Particulier");
		Place t1 = new Place("Transporteur");
		Place t2 = new Place("Transporteur");

		// Creation des vehicules //
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

		// Placements des vehicules sur les places //
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