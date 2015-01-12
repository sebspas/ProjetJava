package parking.business;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrateur on 12/01/2015.
 */
public class CalculerTarifHeure implements CalculerTarif{

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

    @Override
    public String toString() {
        return "CalculerTarifHeure";
    }
}
