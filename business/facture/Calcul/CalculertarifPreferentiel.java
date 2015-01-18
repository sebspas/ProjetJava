package parking.business.facture.Calcul;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Place;
import java.io.Serializable;

/**
 * Class CalculerTarifPointsFidelite, qui implemente l'interface CalculerTarif, et rajoute des
 * informations suplementaires dont elle a besoin pour calculer le tarif selon des points de fidelite.
 *
 * @see CalculerTarif, Serializable
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class CalculertarifPreferentiel implements CalculerTarif, Serializable {
    private CalculerTarif calculerTarifHeure = new CalculerTarifHeure();
    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    /**
     * Implementation calculerTarif(), de la classe CalculerTarifPreferentiel, permettant
     * de calculer le tarif avec une reduction preferentiel.
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


        // Application de la reduction
        return  tarif - (tarif*0.20);
    }

    /**
     * Methode toString() affichant le tarif selon les points de fidelite d'un client.
     *
     * @return Une chaine de caracteres contenant l'information.
     */
    @Override
    public String toString() {
        return "CalculerTarifPreferentiel";
    } // toString()

} // CalculertarifPreferentiel class
