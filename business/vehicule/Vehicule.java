package parking.business.vehicule;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;

import java.util.Date;

/**
 * Class Vehicule permettant de creer un nouveau vehicule avec ses specificites comme
 * l'immatriculation, la marque, le modele, le type, le nom du proprietaire, et la date d'arrivee.
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public abstract class Vehicule {
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
	private Date dateArrivee;

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
	 * @return Le numero d'immatriculation du vehicule.
	 */
	public Client getProprietaire() {
		return proprietaire;
	} // getProprietaire()

	/**
	 * Methode getDateArrivee() renvoie la date d'arrivee du vehicule.
	 *
	 * @return La date d'arrivee du vehicule.
	 */
	public Date getDateArrivee() {
		return dateArrivee;
	} // getDateArrivee()

	/***************************************************************/
	/*						Setter								   */
	/***************************************************************/
	/**
	 * Methode setDateArrivee() modifie la date d'arrivee du vehicule.
	 *
	 * @param dateArrivee
	 * 			La date d'arrivee du vehicule.
	 */
	public void setDateArrivee(Date dateArrivee) {
		this.dateArrivee = dateArrivee;
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
