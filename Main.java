package parking;

import parking.business.*;
import parking.business.facture.CalculerTarifHeure;
import parking.business.facture.CalculerTarifPointsFidelite;
import parking.business.facture.Facture;
import parking.business.vehicule.Camion;
import parking.business.vehicule.Vehicule;
import parking.business.vehicule.Voiture;
import parking.gui.Vue;
import parking.gui.VueParking;
import parking.gui.VueTimer;

/**
 * Created by Administrateur on 10/01/2015.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Parking parking = Parking.getInstance();

            Vue test = new VueParking();

            // Creation des places Particulier //
            for (int i = 0; i < 2; ++i) {
                new Place("Particulier");
            }

            // Creation des places Transporteur //
            for (int i = 0; i < 5; ++i) {
                new Place("Transporteur");
            }

            // Creation des clients //
            Client client1 = new Client("Voili", "Voilou", "69 rue du Porn", new CalculerTarifHeure());
            Client client2 = new Client("Titi", "Tata", "69 rue du Porn", new CalculerTarifHeure());
            Client client3 = new Client("Claude", "François", "69 rue du Porn", new CalculerTarifPointsFidelite());
            Client client4 = new Client("Toto", "Tata", "69 rue du Porn", new CalculerTarifHeure());

            // Creation des vehicules //
            Vehicule v1 = new Voiture("E4IL", "Sitrohaine", "NTM", client1);
            Vehicule v2 = new Voiture("R3T4RD", "Beta Juliette", "LMAO", client2);
            Vehicule v3 = new Voiture("KDNAPPR", "Pherrary", "SWAG", client3);
            Vehicule c1 = new Camion("S0L31L", "Porschiaaaaa", "YOLO", client4, 15, 355);

            // Placements des vehicules sur les places //
            parking.park(v1);
            parking.park(c1);
            parking.park(v2);
            parking.park(v3);

            parking.bookPlace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    } // main()

} // Main class
