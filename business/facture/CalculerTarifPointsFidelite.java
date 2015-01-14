package parking.business.facture;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.Constante;
import parking.business.Parking;
import parking.business.Place;

import java.util.Date;

/**
 * Class CalculerTarifPointsFidelite, qui implemente l'interface CalculerTarif, et rajoute des
 * informations suplementaires dont elle a besoin pour calculer le tarif selon des points de fidelite.
 *
 * @see CalculerTarif
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class CalculerTarifPointsFidelite implements CalculerTarif {
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
        Date dateActuelle = new Date();
        int heureActuelle = dateActuelle.getHours();
        int jourActuel = dateActuelle.getDate();

        int heureArrivee = p.getVehicule().getDateArrivee().getHours();
        int jourArrivee = p.getVehicule().getDateArrivee().getDate();

        int nombreHeures;

        if (jourActuel == jourArrivee) {
            nombreHeures = heureActuelle - heureArrivee;
            if (nombreHeures == 0)
                nombreHeures = 1;
        }
        else {
            nombreHeures = (jourActuel-jourArrivee)*24 + (24-heureArrivee) + (heureActuelle);
        }

        double tarif = Parking.getTarif_particulier();
        if (p.getVehicule().getType() == "Transporteur")
            tarif = Parking.getTarif_transporteur();

        return ((((double)nombreHeures)* tarif) * (1-(p.getVehicule().getProprietaire().getPointsDeFidelite()/1000)) +
                (((double)nombreHeures)* tarif) * (1-(p.getVehicule().getProprietaire().getPointsDeFidelite()/1000)) * Constante.TVA / 100);
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
