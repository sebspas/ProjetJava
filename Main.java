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

/**
 * Created by Administrateur on 10/01/2015.
 */
public class Main {
    public static void main(String[] args) {

        Vue test = new VueParking();
        //Vue listeVehicule = new VueVehicule();
        Parking.addVue(test);
        //Parking.addVue(listeVehicule);
        // Creation des places //
        Place p1 = new Place("Particulier");
        Place p2 = new Place("Particulier");
        Place t1 = new Place("Transporteur");
        Place t2 = new Place("Transporteur");
        Place p4 = new Place("Particulier");
        Place p5 = new Place("Particulier");
        Place t6 = new Place("Transporteur");
        Place t7 = new Place("Transporteur");
        Place t8 = new Place("Particulier");
        Place p9 = new Place("Particulier");
        Place t10 = new Place("Transporteur");
        Place t11 = new Place("Transporteur");
        Place p12 = new Place("Particulier");
        Place p13 = new Place("Particulier");
        Place t14 = new Place("Transporteur");
        Place t15 = new Place("Transporteur");

        // Creation des clients //
        Client client1 = new Client("Voili","Voilou", "69 rue du Porn",new CalculerTarifHeure());
        Client client2 = new Client("Titi","Tata", "69 rue du Porn",new CalculerTarifHeure());
        Client client3 = new Client("Claude","François", "69 rue du Porn",new CalculerTarifPointsFidelite());
        Client client4 = new Client("Toto","Tata", "69 rue du Porn",new CalculerTarifHeure());

        // Creation des vehicules //
        Vehicule v1 = new Voiture("E4IL", "Sitrohaine", "NTM", client1);
        Vehicule v2 = new Voiture("R3T4RD", "Beta Juliette", "LMAO", client2);
        Vehicule v3 = new Voiture("KDNAPPR", "Pherrary", "SWAG", client3);
        Vehicule c1 = new Camion("S0L31L", "Porschiaaaaa", "YOLO", client4,15, 355);

        // Placements des vehicules sur les places //
        Parking.park(v1);
        Parking.park(c1);
        Parking.park(v2);
        Parking.park(v3);

        client1.setPointsDeFidelite(450);
        Parking.unpark(2);

        for(Facture f : Parking.getListeFacture()){
            f.sauvegarder();
        }
        Parking.bookPlace();

    } // main()

} // Main class
