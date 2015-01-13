package parking.business;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class CalculerTarifHeure, qui implemente l'interface CalculerTarif, et rajoute des
 * informations suplementaires dont elle a besoin pour calculer le tarif à l'heure.
 *
 * @see CalculerTarif
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class CalculerTarifHeure implements CalculerTarif{
    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe CalculerTarifHeure, permettant de calculer le tarif
     * a l'heure d'une place.
     *
     * @param p
     *          La place sur laquelle on effectue le calcul du tarif à l'heure.
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
    }

    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    /**
     * Methode toString() affichant le tarif à l'heure d'une place.
     *
     * @return Une chaine de caracteres contenant l'information.
     */
    @Override
    public String toString() {
        return "CalculerTarifHeure";
    }
}
