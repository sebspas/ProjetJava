package parking.business;

/**
 * Class vehicule permettant de consturire un vehicule dit classique, à l'aide de diverses informations
 * tel que son immatriculation, sa marque, ...
 *
 * @author Coadalen, Chergui, Corral, Corfa
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
	 * Le propietaire du vehicule.
	 */
	protected String proprietaire;

	/**
	 * Le type du vehicule.
	 */
	protected String type;


	/***************************************************************/
	/*						Constructeur						   */
	/***************************************************************/
	/**
	 * Constructeur de la class camion, utilisant le constructeur d'unvehicule classique
	 * et ajoutant les specificites d'un camion
	 *
	 * @param immatriculation
	 * 			L'immatriculation du camion a construire.
	 * @param marque
	 * 			La marque du vehicule a construire.
	 * @param modele
	 * 			Le modele du camion a construire.
	 * @param proprietaire
	 * 			Le proprietaire du camion a construire.
	 * @param type
	 * 			Le type du vehicule a construire.
	 */
	public Vehicule(String immatriculation, String marque, String modele, String proprietaire, String type) {
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.proprietaire = proprietaire;
		this.type = type;
	} //Constructeur


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
	}// toString()
	
	
} // Vehicule class
