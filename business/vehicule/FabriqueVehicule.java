package parking.business.vehicule;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;

/**
 * Class FabriqueVehicule, qui implemente la classe IFabriqueVehicule,
 * et permet de fabriquer un vehicule (Voiture ou Camion).
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class FabriqueVehicule implements IFabriqueVehicule {

    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    /**
     * Methode creer() permet de creer un nouveau vehicule (ici une voiture), avec
     * ses particularites comme la plaque d'immatriculation, la marque, le modele, etc.
     *
     * @param immatriculation
     *          La plaque d'immatriculation de la voiture.
     * @param marque
     *          La marque de la voiture.
     * @param modele
     *          Le modele de la voiture.
     * @param client
     *          Le client/proprietaire de la voiture.
     * @return La nouvelle voiture.
     */
    @Override
    public Vehicule creer(String immatriculation, String marque, String modele, Client client) {
            return new Voiture(immatriculation,marque,modele,client);
    } // creer()

    /**
     * Methode creer() (surcharge de la methode ci dessus) permet de creer un nouveau vehicule (ici
     * un camion), avec ses specificites en plus d'une voiture telles que le tonnage et la hauteur.
     *
     * @param immatriculation
     *          La plaque d'immatriculation du camion.
     * @param marque
     *          La marque du camion.
     * @param modele
     *          Le modele du camion.
     * @param client
     *          Le client/proprietaire du camion.
     * @param tonnage
     *          Le poids du camion.
     * @param hauteur
     *          La hauteur du camion.
     * @return Le nouveau camion.
     */
    @Override
    public Vehicule creer(String immatriculation, String marque, String modele, Client client, int tonnage, int hauteur) {
        return new Camion(immatriculation,marque,modele,client,tonnage,hauteur);
    } // creer()

} // FabriqueVehicule class
