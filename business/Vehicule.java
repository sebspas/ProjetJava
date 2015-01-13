package parking.business;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import java.util.Date;

/**
 * Class Vehicule permettant de construire un vehicule dit classique, à l'aide de diverses informations
 * telles que son immatriculation, sa marque, ...
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class Vehicule {
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
	/*						Constructeur						   */
	/***************************************************************/
	/**
	 * Constructeur de la classe Vehicule permettant de construire un vehicule dit classique
	 * et ajoutant les specificites d'un vehicule.
	 *
	 * @param immatriculation
	 * 			L'immatriculation du vehicule a construire.
	 * @param marque
	 * 			La marque du vehicule a construire.
	 * @param modele
	 * 			Le modele du vehicule a construire.
	 * @param proprietaire
	 * 			Le proprietaire du vehicule a construire.
	 * @param type
	 * 			Le type du vehicule a construire.
	 */
	public Vehicule(String immatriculation, String marque, String modele, Client proprietaire, String type) {
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.proprietaire = proprietaire;
		this.type = type;
	} // Constructeur

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
	 * Methode toString() affichant toutes les informations du vehicule.
	 *
	 * @return Une chaine de caracteres contenant les informations.
	 */
	@Override
	public String toString() {
		return "Vehicule [immatriculation=" + immatriculation + ", marque="
				+ marque + ", modele=" + modele + ", proprietaire="
				+ proprietaire + "type=" + type + "]";
	} // toString()
	
} // Vehicule class
