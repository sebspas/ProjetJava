package parking;

import parking.business.*;
import parking.gui.Vue;
import parking.gui.VueParking;
import parking.gui.VueVehicule;

import javax.swing.*;
import java.util.Date;

/**
 * Created by Administrateur on 10/01/2015.
 */
public class Main {
    public static void main(String[] args) {

        Vue test = new VueParking();
        Vue listeVehicule = new VueVehicule();
        Parking.addVue(test);
        Parking.addVue(listeVehicule);
        // Cr�ation des places //
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

        // Création des clients //
        Client client1 = new Client("Voili","Voilou", "69 rue du Porn",new CalculerTarifHeure());
        Client client2 = new Client("Titi","Tata", "69 rue du Porn",new CalculerTarifHeure());
        Client client3 = new Client("Claude","François", "69 rue du Porn",new CalculerTarifPointsFidelite());
        Client client4 = new Client("Toto","Tata", "69 rue du Porn",new CalculerTarifHeure());

        Parking.addClient(client1);
        Parking.addClient(client2);
        Parking.addClient(client3);
        Parking.addClient(client4);

        // Cr�ation des v�hicules //
        Vehicule v1 = new Voiture("E4IL", "Sitrohaine", "NTM", client1);
        Vehicule v2 = new Voiture("R3T4RD", "Beta Juliette", "LMAO", client2);
        Vehicule v3 = new Voiture("KDNAPPR", "Pherrary", "SWAG", client3);
        Vehicule c1 = new Camion("S0L31L", "Porschiaaaaa", "YOLO", client4,15, 355);

        client1.addVehicule(v1);
        client2.addVehicule(v2);
        client3.addVehicule(v3);
        client4.addVehicule(c1);

        // Ajout des places au parking //
        Parking.ajouterPlace(p1);
        Parking.ajouterPlace(t1);
        Parking.ajouterPlace(p2);
        Parking.ajouterPlace(t2);
        Parking.ajouterPlace(p5);
        Parking.ajouterPlace(t6);
        Parking.ajouterPlace(p13);
        Parking.ajouterPlace(t8);
        Parking.ajouterPlace(p9);
        Parking.ajouterPlace(t10);
        Parking.ajouterPlace(p12);
        Parking.ajouterPlace(t14);

        // Placements des v�hicules sur les places //
        Parking.park(v1);
        Parking.park(c1);
        Parking.park(v2);
        Parking.park(v3);

        client1.setPointsDeFidelite(450);
        Parking.unpark(2);

        Parking.bookPlace();

    }


}
