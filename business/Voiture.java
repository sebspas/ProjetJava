package parking.business;

/**
 * Class Voiture, qui herite de la classe vehicule, et rajoute les informations suplementaires
 * dont elle a besoin pour cree un vehicule du type voiture.
 *
 * @see Vehicule
 * @author Coadalen, Chergui, Corral, Corfa
 */
public class Voiture extends Vehicule{
    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la class voiture, utilisant le constructeur d'un vehicule classique
     * et ajoutant les specificites d'une voiture
     *
     * @param immatriculation L'immatriculation du camion a construire.
     * @param marque          La marque du vehicule a construire.
     * @param modele          Le modele du camion a construire.
     * @param proprietaire    Le proprietaire du camion a construire.
     */
    public Voiture(String immatriculation, String marque, String modele, String proprietaire) {
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
                + marque + ", modele=" + modele + ", proprietaire=" + proprietaire
                +  "]";
    }// toString()
}
