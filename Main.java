package parking;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.*;
import parking.business.facture.CalculerTarifHeure;
import parking.business.facture.CalculerTarifPointsFidelite;
import parking.business.facture.Facture;
import parking.business.vehicule.*;
import parking.gui.Vue;
import parking.gui.VueParking;
import parking.gui.VueTimer;

/**
 * Class Main qui permet de faire toutes les creations dont nous avons
 * besoin pour creer l'application (clients, vehicules, places).
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class Main {
    public static void main(String[] args) {
        try {
            Parking parking = Parking.getInstance();
            
            // Creation des places Particulier //
            for (int i = 0; i < 14; ++i) {
                new Place("Particulier");
            }

            // Creation des places Transporteur //
            for (int i = 0; i < 10; ++i) {
                new Place("Transporteur");
            }

            // Creation des clients //
            Client client1 = new Client("Jean", "Carna", "2, Rue des Concepts", new CalculerTarifHeure());
            Client client2 = new Client("Tom", "Galendo", "44, Chemin de Chateau Roi", new CalculerTarifHeure());
            Client client3 = new Client("Claude", "Hamari", "57 Avenue de la Garde", new CalculerTarifPointsFidelite());
            Client client4 = new Client("Kevin", "Alama", "29 Traverse des Buissons", new CalculerTarifHeure());
            Client client5 = new Client("Michel", "Bernard", "5, Avenue de la Republique", new CalculerTarifHeure());
            Client client6 = new Client("Nathan", "Delamard", "149, Route des Mirages", new CalculerTarifHeure());

            // Creation des vehicules //
            IFabriqueVehicule fabriqueVehicule = new FabriqueVehicule();
            Vehicule v1 = fabriqueVehicule.creer("AB-531-MT-13", "Citroen", "C3", client1);
            Vehicule v2 = fabriqueVehicule.creer("BT-640-AE-83", "Peugeot", "306", client2);
            Vehicule v3 = fabriqueVehicule.creer("AN-155-GT-45", "Renault", "Scenic", client3);
            Vehicule v4 = fabriqueVehicule.creer("CD-294-ZE-69", "Audi", "SWAG", client3);
            Vehicule c1 = fabriqueVehicule.creer("BG-951-KC-29", "GMC", "Savana", client4, 15, 355);
            Vehicule c2 = fabriqueVehicule.creer("AP-735-LP-84", "International", "CF600", client5, 15, 355);
            Vehicule c3 = fabriqueVehicule.creer("BR-848-MM-75", "Chevrolet", "Express", client5, 15, 355);
            Vehicule c4 = fabriqueVehicule.creer("CZ-609-OK-31", "Ford", "E350", client6, 15, 355);

            // Placements des vehicules sur les places //
            parking.park(v1);
            parking.park(c1);
            parking.park(v2);
            parking.park(c2);
            parking.park(v3);
            parking.park(c3);
            parking.park(v4);
            parking.park(c4);

            parking.bookPlace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    } // main()

} // Main class
