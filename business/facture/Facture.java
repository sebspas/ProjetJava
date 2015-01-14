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
 * Class Facture permettant de creer une nouvelle facture comportant des informations telles
 * que le numero de facture, le tarif, le client et le vehicule associe.
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class Facture {
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    /**
     * Le numero de la facture.
     */
    private int numeroFacture;

    /**
     * Le tarif de la facture.
     */
    private double tarif;

    /**
     * Le client concerne par la facture.
     */
    private Client client;

    /**
     * Le vehicule concerne par la facture.
     */
    private Vehicule vehicule;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe Facture, permettant de creer une facture
     * a partir de la place concernee par celle ci.
     *
     * @param place
     *          La place associee a la facture.
     */
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
    /**
     * Methode toString() affiche toutes les informations de la facture.
     *
     * @return Une chaine de caracteres contenant les informations.
     */
    @Override
    public String toString() {
        return "Facture{" +
                "numeroFacture=" + numeroFacture +
                ", tarif=" + tarif +
                ", client=" + client +
                '}';
    } // toString()

    /**
     * Methode sauvegarder() permettant de sauvegarder la facture dans un fichier
     * a part et avec une mise en page minimaliste d'une reelle facture.
     */
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
