package parking.business.facture.Calcul;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;
import parking.business.Place;

/**
 * Class CalculerTarifPointsFidelite, qui implemente l'interface CalculerTarif, et rajoute des
 * informations suplementaires dont elle a besoin pour calculer le tarif selon des points de fidelite.
 *
 * @see CalculerTarif
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class CalculerTarifPointsFidelite implements CalculerTarif {
    private CalculerTarif calculerTarifHeure = new CalculerTarifHeure();
    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    /**
     * Implementation calculerTarif(), de la classe CalculerTarifPointsFidelite, permettant
     * de calculer le tarif selon les points de fidelite d'un client.
     *
     * @param p
     *          La place sur laquelle on effectue le calcul du tarif selon
     *          les points de fidelite d'un client.
     * @return Le tarif du client selon ses points de fidelite.
     */
    @Override
    public double calculerTarif(Place p) {
        // Utilisation de la méthode calculer tarif à l'heure
        double tarif = calculerTarifHeure.calculerTarif(p);
        
        // Récupération de la remise du client en fonction de son nombre de point de fidélité
        double remise = 0;
        Client client = p.getVehicule().getProprietaire();
        client.setPointsDeFidelite(client.getPointsDeFidelite() + (int) (tarif * 2));
        
        if (client.getPointsDeFidelite() >= 10) {
            remise = 1.0;
            client.setPointsDeFidelite(client.getPointsDeFidelite()- 10);
        }
        
        // Application de la remise
        return  tarif - remise;
    } // CalculerTarifPointsFidelite()

    /**
     * Methode toString() affichant le tarif selon les points de fidelite d'un client.
     *
     * @return Une chaine de caracteres contenant l'information.
     */
    @Override
    public String toString() {
        return "CalculerTarifPtsFidelite";
    } // toString()

} // CalculerTarifPointsFidelite class
