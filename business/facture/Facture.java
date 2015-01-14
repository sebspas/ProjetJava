package parking.business.facture;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;
import parking.business.Parking;
import parking.business.Place;
import parking.business.vehicule.Vehicule;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by Administrateur on 12/01/2015.
 */
public class Facture {
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    private int numeroFacture;

    private double tarif;

    private Client client;

    private Vehicule vehicule;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    public Facture(Place place) {
        this.numeroFacture = Parking.getNumeroFacture();
        Parking.setNumeroFacture(Parking.getNumeroFacture()+1);
       this.vehicule = place.getVehicule();
        tarif = place.getVehicule().getProprietaire().getCalculerTarif().calculerTarif(place);
        client = place.getVehicule().getProprietaire();
        Parking.addFacture(this);
    } // Constructeur

    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    @Override
    public String toString() {
        return "Facture{" +
                "numeroFacture=" + numeroFacture +
                ", tarif=" + tarif +
                ", client=" + client +
                '}';
    } // toString()

    public void sauvegarder(){
        try{
            new File("Factures").mkdir();
            File ff=new File("Factures/Factures" + numeroFacture + ".txt");
            ff.createNewFile();
            FileWriter ffw=new FileWriter(ff);
            ffw.write("####################### Facture " + numeroFacture + " #######################" + "\r\n" );
            ffw.write("#"                                   + "\r\n");
            ffw.write("# Véhicule :" + vehicule             + "\r\n");
            ffw.write("# Tarif : "   + tarif + "€"          + "\r\n");
            ffw.write("#"                                   + "\r\n");
            ffw.write("# Client :"                          + "\r\n");
            ffw.write("# Nom : "     + client.getNom()      + "\r\n");
            ffw.write("# Prénom : "  + client.getPrenom()   + "\r\n");
            ffw.write("# Adresse : " + client.getAdresse()  + "\r\n");
            ffw.write("#########################################################");
            ffw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // sauvegarder()

} // Facture class
