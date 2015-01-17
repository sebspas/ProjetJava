package parking.business.vehicule;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;
import parking.business.Parking;
import parking.business.Timer;

import java.io.Serializable;
import java.util.Date;

/**
 * Class Vehicule permettant de creer un nouveau vehicule avec ses specificites comme
 * l'immatriculation, la marque, le modele, le type, le nom du proprietaire, et la date d'arrivee.
 *
 * @see Serializable
 * @author Chergui, Coadalen, Corfa, Corral
 */
public abstract class Vehicule implements Serializable{
	/***************************************************************/
	/*						Debut Donnees Membres 				   */
	/***************************************************************/
	/**
	 * Le numero d'immatriculation du vehicule.
	 */
	protected String immatriculation;

	/**
	 * La marque du vehicule.
	 */
	protected String marque;

	/**
	 * Le modele du vehicule.
	 */
	protected String modele;

	/**
	 * Le proprietaire du vehicule.
	 */
	protected Client proprietaire;

	/**
	 * Le type du vehicule.
	 */
	protected String type;

	/**
	 * La date d'arrivée du vehicule.
	 */
	private int jourArrivee;

	/**
	 * L'heure d'arrivée du vehicule.
	 */
	private int heureArrivee;

	/***************************************************************/
	/*						Getter								   */
	/***************************************************************/
	/**
	 * Methode getType() renvoie le type du vehicule.
	 *
	 * @return Le type du vehicule.
	 */
	public String getType() {
		return type;
	} // getType()

	/**
	 * Methode getImmatriculation() renvoie le numero d'immatriculation du vehicule.
	 *
	 * @return Le numero d'immatriculation du vehicule.
	 */
	public String getImmatriculation() {
		return immatriculation;
	} // getImmatriculation()
	
	/**
	 * Methode getProprietaire() renvoie le numero d'immatriculation du vehicule.
	 *
	 * @return Le proprietaire du vehicule.
	 */
	public Client getProprietaire() {
		return proprietaire;
	} // getProprietaire()

	/**
	 * Methode getProprietaire() renvoie le numero d'immatriculation du vehicule.
	 *
	 * @return Le jour d'arrivee du vehicule sur la place qu'il occupe.
	 */
	public int getJourArrivee() {
		return jourArrivee;
	}

	/**
	 * Methode getProprietaire() renvoie le numero d'immatriculation du vehicule.
	 *
	 * @return L'heure d'arrivee du vehicule sur la place qu'il occupe.
	 */
	public int getHeureArrivee() {
		return heureArrivee;
	}

	/***************************************************************/
	/*						Setter								   */
	/***************************************************************/
	/**
	 * Methode setDateArrivee() modifie la date d'arrivee du vehicule.
	 *
	 * 			La date d'arrivee du vehicule.
	 */
	public void setDateArrivee() {
		this.jourArrivee = Timer.getInstance().getDay();
		this.heureArrivee = Timer.getInstance().getHeures();
	} // setDateArrivee()

	/***************************************************************/
	/*						Methodes							   */
	/***************************************************************/
	/**
	 * Methode abstraite toString() servant a afficher toutes les informations du vehicule.
	 */
	@Override
	public abstract String toString(); // toString()
	
} // Vehicule class
