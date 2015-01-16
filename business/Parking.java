package parking.business;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.facture.Facture;
import parking.business.vehicule.Vehicule;
import parking.exception.business.*;
import parking.gui.Vue;
import parking.gui.VueParking;
import parking.gui.VueTimer;

import java.util.ArrayList;

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
	private int numeroPlace;

	/**
	 * Le nombre de place maximum du parking.
	 */
	private int nbPlacesMax;

	private int nbPlaceOccupees;
	/**
	 * Le nom du parking.
	 */
	private String nom;

	/**
	 * La liste des vehicule du parking.
	 * Type de collection a definir ...
	 */
	private ArrayList<Place> listePlaces;

	/**
	 * La liste de tous les clients.
	 * Type de collection a definir ...
	 */
	private ArrayList<Client> listeClients;

	/**
	 * La liste de toutes les factures.
	 * Type de collection a definir ...
	 */
	private ArrayList<Facture> listeFacture;

	/**
	 * La liste de toute les vues.
	 * Type de collection a definir ...
	 */
	private ArrayList<Vue> listeVueNotifiable;

	/**
	 * Le tarif d'une place de type particulier.
	 */
	private double tarif_particulier;

	/**
	 * Le tarif d'une place de type transporteur.
	 */
	private double tarif_transporteur;

	/**
	 * Le numero de la facture associee a un client.
	 */
	private int numeroFacture;

	/**
	 * Un booleen permettant de savoir si une fonction a ete appele en interne ou non.
	 */
	private boolean appelInterne;
	
	private static Parking singleton = null;
	/**
	 * Initialisation des informations generales du parking. Statiquement car le parking est unique.
	 */
	public static Parking getInstance() {
		if (singleton != null) {
			return singleton;
		}
		singleton = new Parking();
		return  singleton;
	}
	
	private Parking() {
		/* Initialisation des donnees membres */
		nom = "Mon Parking";
		numeroPlace = 0;
		listeVueNotifiable = new ArrayList<Vue>();
		listePlaces = new ArrayList<Place>();
		listeFacture = new ArrayList<Facture>();
		listeClients = new ArrayList<Client>();
		nbPlacesMax = 24;
		numeroFacture = 0;
		tarif_particulier = 1;
		tarif_transporteur = 1.5;
		appelInterne = false;
		
		/* Ajout de la vue Parking */
		Vue test = new VueParking();
		listeVueNotifiable.add(test);
		
		/*	Ajout de la vue du timer */
		Vue vueTimer = new VueTimer(Timer.getInstance());
		Timer.getInstance().setVue(vueTimer);
		
		/* Demarrage Timer */
		Timer.getInstance().start();
	} // Constructeur

	/***************************************************************/
	/*						Getter								   */
	/***************************************************************/

	public int getNbPlaceOccupees() {
		return nbPlaceOccupees;
	}

	/**
	 * Methode renvoyant le numero de la derniere place ajoutee au parking.
	 *
	 * @return le numero de la derniere place ajoutee.
	 */
	public int getNumeroPlace() {
		return numeroPlace;
	} // getNumeroPlace()

	/**
	 * Methode renvoyant le nombre de place maximal du parking.
	 *
	 * @return Nombre de place max du parking.
	 */
	public int getNbPlacesMax() {
		return nbPlacesMax;
	} // getNbPlacesMax()

	/**
	 * Methode getTarif_transporteur() renvoie le tarif d'une place de type transporteur.
	 *
	 * @return Le tarif d'une place de type transporteur.
	 */
	public double getTarif_transporteur() { return tarif_transporteur; } // getTarif_transporteur()

	/**
	 * Methode getTarif_particulier() renvoie le tarif d'une place de type particulier.
	 *
	 * @return Le tarif d'une place de type particulier.
	 */
	public double getTarif_particulier() { return tarif_particulier; } // getTarif_particulier()

	/**
	 * Methode getNumeroFacture() renvoie le numero de la facture.
	 *
	 * @return Le numero de la facture.
	 */
	public int getNumeroFacture() { return numeroFacture; } // getNumeroFacture()

	/**
	 * Methode getLocation() permet de connaitre la place ou se situe un vehicule a partir de son numero d'immatriculation.
	 *
	 * @param numeroImmatriculation
	 * 			numero d'immatriculation du vehicule a recherche.
	 * @return le numero ou le vehicule se trouve sur le parking.
	 */
	public int getLocation(String numeroImmatriculation) {
		for (Place p : this.listePlaces) {
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
	public ArrayList<Client> getListeClient() { return listeClients; } // getListeClients()

	/**
	 * Methode getListePlaces() renvoie la liste des vehicules existants.
	 *
	 * @return La liste des vehicules existants.
	 */
	public ArrayList<Place> getListePlaces() { return listePlaces; } // getListePlaces()

	/**
	 * Methode getListeFacture() renvoie la liste de toutes les factures.
	 *
	 * @return La liste des factures.
	 */
	public ArrayList<Facture> getListeFacture() { return listeFacture; } // getListeFacture()

	/***************************************************************/
	/*						Setter								   */
	/***************************************************************/
	/**
	 * Methode setNumeroPlace modifie la valeur de la derniere place ajoutee.
	 *
	 * @param numeroPlace
	 * 		Numero de la derniere place ajouter au parking.
	 */
	public void setNumeroPlace(int numeroPlace) {
		this.numeroPlace = numeroPlace;
	}// setNumeroPlace()

	/**
	 * Methode setTarif_transporteur() modifie le tarif d'une place de type transporteur.
	 *
	 * @param tarif_transporteur
	 * 			Le tarif d'une place de type transporteur.
	 */
	public void setTarif_transporteur(double tarif_transporteur) {
		this.tarif_transporteur = tarif_transporteur;
	}

	/**
	 * Methode setTarif_particulier() modifie le tarif d'une place de type particulier.
	 *
	 * @param tarif_particulier
	 * 			Le tarif d'une place de type particulier.
	 */
	public void setTarif_particulier(double tarif_particulier) {
		this.tarif_particulier = tarif_particulier;
	}

	/**
	 * Methode setNumeroFacture() modifie le numero de la facture.
	 *
	 * @param numeroFacture
	 * 			Le numero de la facture.
	 */
	public void setNumeroFacture(int numeroFacture) {
		this.numeroFacture = numeroFacture;
	}

	public void setNbPlaceOccupees(int nbPlaceOccupees) {
		this.nbPlaceOccupees = nbPlaceOccupees;
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
	public void addClient(Client c){
		listeClients.add(c);
	}

	/**
	 * Methode addFacture() ajoute une facture a la liste des factures.
	 *
	 * @param facture
	 * 			La facture a ajouter.
	 */
	public void addFacture(Facture facture) {
		listeFacture.add(facture);
	}

	/**
	 * Methode addVue() ajoute une vue a la liste des vues.
	 *
	 * @param v
	 * 			La vue a ajouter.
	 */
	public void addVue(Vue v) {
		listeVueNotifiable.add(v);
	} // addVue()

	/**
	 * Methode notifier() affiche un message a chaque vue de la liste des vues.
	 */
	public void notifier() {
		for (Vue v : listeVueNotifiable) {
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
	public void ajouterPlace(Place p){
		try {
			if(this.getNbPlacesMax() == this.getNumeroPlace())
				throw new NombrePlacesMaxException();
			p.setNumero(this.getNumeroPlace());
			this.setNumeroPlace(this.getNumeroPlace() + 1);
			this.listePlaces.add(p);
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
	public boolean vehiculeGare(Vehicule v){
		for(Place p : this.listePlaces ){
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
	public Vehicule unpark(int numeroPlace) {
		for(Place p : this.listePlaces){
			if (numeroPlace == p.getNumero())
				try {
					if (!appelInterne) {
						--nbPlaceOccupees;
						this.addFacture(new Facture(p));
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
	public void park(Vehicule vehicule) {
		try {
			String typePlace = "Transporteur";
			if (vehicule.getType().equals("Voiture")) {
				typePlace = "Particulier";
			}
			for (Place p : this.listePlaces) {
				if (p.getVehicule() == null && !(p.getReservation())) {
					if (p.getType().equals(typePlace)) {
						p.setVehicule(vehicule);
						if (!appelInterne) {
							++nbPlaceOccupees;
							p.getVehicule().setDateArrivee();
						}
						return;
					}
				}
			}
			for (Place p : this.listePlaces) {
				if (p.getVehicule() == null && !(p.getReservation())) {
					++nbPlaceOccupees;
					p.setVehicule(vehicule);
					p.getVehicule().setDateArrivee();
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
	public void etatParking() {
		System.out.println("Debut de l'affichage du parking !");
		for(Place p : this.listePlaces) {
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
	public Place bookPlace() {
		try {
			for (Place p : this.listePlaces) {
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
	public Place freePlace(int numeroPlace) {
		try {
			for (Place p : this.listePlaces) {
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
	public Vehicule retirerVehicule(String numeroImmatriculation) {
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
	public void reorganiserPlaces() {
		appelInterne = true;
		for (Place p : this.listePlaces) {
			if (p.getType().equals("Transporteur") && p.getVehicule() != null) {
				if (p.getVehicule().getType().equals("Voiture")) {
					Vehicule vretire = unpark(p.getNumero());
					this.park(vretire);
				}
			}
		}
		notifier();
		appelInterne = false;
	} // reorganiserPlaces()

} // Parking class