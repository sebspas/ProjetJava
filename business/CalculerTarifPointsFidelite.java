package parking.business;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import java.util.Date;

/**
 * Created by Administrateur on 12/01/2015.
 */
public class CalculerTarifPointsFidelite implements CalculerTarif {
    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
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

    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    @Override
    public String toString() {
        return "CalculerTarifPtsFidelite";
    } // toString()

} // CalculerTarifPointsFidelite class
