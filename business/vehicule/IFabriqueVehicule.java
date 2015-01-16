package parking.business.vehicule;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;

/**
 * Class IFabriqueVehicule et rajoute les informations suplementaires
 * dont elle a besoin pour cree un vehicule du type voiture.
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public interface IFabriqueVehicule {
    /***************************************************************/
	/*						Profil methodes						   */
    /***************************************************************/
    /**
     * * Profil de la methode creer() (pour une voiture), le corps
     * de cette methode se trouve dans la classe FabriqueVehicule.
     */
    public Vehicule creer(String marque, String modele, String immatriculation, Client client);

    /**
     * Profil de la methode creer() (pour un camion), le corps de
     * cette methode se trouve dans la classe FabriqueVehicule.
     */
    public Vehicule creer(String marque, String modele, String immatriculation, Client client, int tonnage, int hauteur);

} // IFabriqueVehicule interface
