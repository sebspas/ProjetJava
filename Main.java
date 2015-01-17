package parking;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;
import parking.business.Parking;
import parking.business.Place;
import parking.business.facture.Calcul.CalculerTarifHeure;
import parking.business.facture.Calcul.CalculerTarifPointsFidelite;
import parking.business.vehicule.Fabrique.FabriqueVehicule;
import parking.business.vehicule.Fabrique.IFabriqueVehicule;
import parking.business.vehicule.Vehicule;
import parking.gui.ajouter.VueCreation;

/**
 * Class Main qui permet de faire toutes les creations dont nous avons
 * besoin pour creer l'application (clients, vehicules, places).
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class Main {
    public static void main(String[] args) {
        try {
            new VueCreation();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    } // main()

} // Main class
