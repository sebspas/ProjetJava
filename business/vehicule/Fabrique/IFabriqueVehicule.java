package parking.business.vehicule.Fabrique;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;
import parking.business.vehicule.Vehicule;

/**
 * Class IFabriqueVehicule  Fait appel aux constructeur de Voiture ou 
 * de Camion selon les parametres avec lesquels est appelee la fonction creer.
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
