package parking.business.facture.Calcul;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.Constante;
import parking.business.Parking;
import parking.business.Place;
import parking.business.Timer;

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
        // Récupération de l'heure et du jour actuel selon le timer
        int heureActuelle = Timer.getInstance().getHeures();
        int jourActuel = Timer.getInstance().getDay();

        // Récupération de l'heure et du jour d'arrivée du véhicule
        int heureArrivee = p.getVehicule().getHeureArrivee();
        int jourArrivee = p.getVehicule().getJourArrivee();

        //Calcul du nombre d'heure passé sur le parking
        int nombreHeures = 0;
        if (jourActuel == jourArrivee) {
            nombreHeures = heureActuelle - heureArrivee;
            if (nombreHeures == 0) nombreHeures = 1;
        }
        else {
            nombreHeures = ((jourActuel-jourArrivee)*24 + (24-heureArrivee) + (heureActuelle));
        }
        
        //Récupération du prix de l'heure sur ce type de place
        double tarif = Parking.getInstance().getTarifParticulier();
        if (p.getVehicule().getType() == "Transporteur")
            tarif = Parking.getInstance().getTarifTransporteur();

        // Calcul final du tarif
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