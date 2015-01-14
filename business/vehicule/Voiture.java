package parking.business.vehicule;

import parking.business.Client;

/**
 * Class Voiture, qui herite de la classe Vehicule, et rajoute les informations suplementaires
 * dont elle a besoin pour cree un vehicule du type voiture.
 *
 * @see Vehicule
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class Voiture extends Vehicule{
    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe Voiture, utilisant le constructeur d'un vehicule classique
     * et ajoutant les specificites d'une voiture.
     *
     * @param immatriculation
     *          L'immatriculation de la voiture a construire.
     * @param marque
     *          La marque de la voiture a construire.
     * @param modele
     *          Le modele de la voiture a construire.
     * @param proprietaire
     *          Le proprietaire de la voiture a construire.
     */
    public Voiture(String immatriculation, String marque, String modele, Client proprietaire) {
        super(immatriculation, marque, modele, proprietaire,"Voiture");
    }

    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    /**
     * Methode toString() affichant toutes les informations de la voiture.
     *
     * @return Une chaine de caracteres contenant les informations.
     */
    @Override
    public String toString() {
        return "Voiture[immatriculation=" + immatriculation + ", marque="
                + marque + ", modele=" + modele + ", proprio=" + proprietaire.getNom()
                +  "]";
    }// toString()

} // Voiture class
