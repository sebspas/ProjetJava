package parking.business.facture;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.Constante;
import parking.business.Parking;
import parking.business.Place;

import java.util.Date;

/**
 * Class CalculerTarifHeure, qui implemente l'interface CalculerTarif, et rajoute des
 * informations suplementaires dont elle a besoin pour calculer le tarif à l'heure.
 *
 * @see CalculerTarif
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class CalculerTarifHeure implements CalculerTarif{
    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    /**
     * Implementation calculerTarif(), de la classe CalculerTarifHeure, permettant de
     * calculer le tarif a l'heure d'une place pour un client.
     *
     * @param p
     *          La place sur laquelle on effectue le calcul du tarif a l'heure.
     * @return Le tarif à l'heure du client.
     */
    @Override
    public double calculerTarif(Place p) {

        Date dateActuelle = new Date();
        int heureActuelle = dateActuelle.getHours();
        int jourActuel = dateActuelle.getDate();

        int heureArrivee = p.getVehicule().getDateArrivee().getHours();
        int jourArrivee = p.getVehicule().getDateArrivee().getDate();

        int nombreHeures = 0;
        if (jourActuel == jourArrivee) {
            nombreHeures = heureActuelle - heureArrivee;
            if (nombreHeures == 0)
                nombreHeures = 1;
        }
        else {
            nombreHeures = ((jourActuel-jourArrivee)*24 + (24-heureArrivee) + (heureActuelle));
        }

        double tarif = Parking.getTarif_particulier();
        if (p.getVehicule().getType() == "Transporteur")
            tarif = Parking.getTarif_transporteur();

        return ((double)nombreHeures)* tarif + ((double)nombreHeures)* tarif * Constante.TVA / 100;
    } // calculerTarif()

    /**
     * Methode toString() affichant le tarif a l'heure d'une place pour un client.
     *
     * @return Une chaine de caracteres contenant l'information.
     */
    @Override
    public String toString() {
        return "CalculerTarifHeure";
    }

} // CalculerTarifHeure class