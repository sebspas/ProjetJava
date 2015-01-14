package parking.business.vehicule;

import parking.business.Client;

/**
 * Class Camion, qui herite de la classe Vehicule, et rajoute les informations suplementaires
 * dont elle a besoin pour cree un vehicule du type camion.
 *
 * @see Vehicule
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class Camion extends Vehicule{
	/***************************************************************/
	/*						Debut Donnees Membres 				   */
	/***************************************************************/
	/**
	 * Le tonnage du camion.
	 */
	private int tonnage;

	/**
	 * La hauteur du camion.
	 */
	private int hauteur;


	/***************************************************************/
	/*						Constructeur						   */
	/***************************************************************/
	/**
	 * Constructeur de la classe Camion, utilisant le constructeur d'un vehicule classique
	 * et ajoutant les specificites d'un camion
	 *
	 * @param immatriculation
	 * 			L'immatriculation du camion a construire.
	 * @param marque
	 * 			La marque du camion a construire.
	 * @param modele
	 * 			Le modele du camion a construire.
	 * @param proprietaire
	 * 			Le proprietaire du camion a construire.
	 * @param tonnage
	 * 			Le tonnage du camion a construire.
	 * @param hauteur
	 * 			La hauteur du camion a construire.
	 */
	public Camion(String immatriculation, String marque, String modele,
			Client proprietaire, int tonnage, int hauteur) {
		super(immatriculation, marque, modele, proprietaire,"Camion");
		this.tonnage = tonnage;
		this.hauteur = hauteur;
	}// Constructeur()

	/***************************************************************/
	/*						Methodes							   */
	/***************************************************************/
	/**
	 * Methode toString() affichant toutes les informations du camion.
	 *
	 * @return Une chaine de caracteres contenant les informations.
	 */
	@Override
	public String toString() {
		return "Camion [immatriculation=" + immatriculation + ", marque="
				+ marque + ", modele=" + modele + ", proprio=" + proprietaire.getNom()
				+ ", tonnage=" + tonnage + ", hauteur=" + hauteur + "]";
	}// toString()
	
} // Camion class
