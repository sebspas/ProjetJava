package parking.business.facture;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;
import parking.business.Parking;
import parking.business.Place;
import parking.business.Timer;
import parking.business.vehicule.Vehicule;
import parking.gui.gerer.VueFacture;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;

/**
 * Class Facture permettant de creer une nouvelle facture comportant des informations
 * telles que le numero de facture, le tarif, le client et le vehicule associe.
 *
 * @see Serializable
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class Facture implements Serializable{
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

    /**
     * L'heure d'arrivee du vehicule.
     */
    private int heureArr;

    /**
     * L'heure de depart du vehicule.
     */
    private int heureDep;

    /**
     * Le jour d'arrive du vehicule.
     */
    private int jourArr;

    /**
     * Le jour de depart du vehicule.
     */
    private int jourDep;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe Facture, permettant de creer
     * une facture a partir de la place concernee par celle ci.
     *
     * @param place
     *          La place associee a la facture.
     */
    public Facture(Place place) {
        // Récupération de l'instance du parking
        Parking p = Parking.getInstance();
        
        // Récupération du numéro de facture et incrémentation pour la facture à venir
        this.numeroFacture = p.getNumeroFacture();
        p.setNumeroFacture(p.getNumeroFacture()+1);
        
        // Récupération du véhicule
        this.vehicule = place.getVehicule();
        
        // Récupération du client
        client = vehicule.getProprietaire();
        
        // Récupération du tarif
        tarif = client.getCalculerTarif().calculerTarif(place);
        
        // Récupération de l'heure et du jour
        heureArr = vehicule.getHeureArrivee();
        heureDep = Timer.getInstance().getHeures();
        
        jourArr = vehicule.getJourArrivee();
        jourDep = Timer.getInstance().getDay();
        
        // Ajout de la facture à la liste des facture concernant le parking
        p.addFacture(this);
        
        // Création et affichage d'une vue contenant la facture et proposant l'enregistrement
        new VueFacture(this);
    } // Constructeur

    /***************************************************************/
	/*						Getter								   */
    /***************************************************************/
    /**
     * Methode getNumeroFacture() renvoie le numero de la facture du client.
     * Utilisee pour le nom de la vueFacture.
     *
     * @return Le nom du client
     */
    public int getNumeroFacture() {
        return numeroFacture;
    }

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
        return "####################### Facture " + numeroFacture + " #######################" + "\r\n" + 
                "#"                                   + "\r\n" +
                "# Véhicule :" + vehicule             + "\r\n" +
                "# Jour Arrivé :" + jourArr           + "\r\n" +
                "# Heure Arrivée : " + heureArr       + "\r\n" +
                "# Jour Départ : " + jourDep          + "\r\n" +
                "# Heure Départ : " + heureDep        + "\r\n" +
                "# Tarif : "   + tarif + "€"          + "\r\n" +
                "#"                                   + "\r\n" +
                "# Client :"                          + "\r\n" +
                "# Nom : "     + client.getNom()      + "\r\n" +
                "# Prénom : "  + client.getPrenom()   + "\r\n" +
                "# Adresse : " + client.getAdresse()  + "\r\n" +
                "#########################################################";
    } // toString()

    /**
     * Methode sauvegarder() permettant de sauvegarder la facture dans un fichier
     * a part et avec une mise en page minimaliste d'une reelle facture.
     */
    public void sauvegarder(){
        try{
            // Création du répertoire
            new File("Factures").mkdir();
            
            // Création du fichier
            File ff=new File("Factures/Factures" + numeroFacture + ".txt");
            ff.createNewFile();
            
            // Ecriture de la facture dans le fichier
            FileWriter ffw=new FileWriter(ff);
            ffw.write("####################### Facture " + numeroFacture + " #######################" + "\r\n" );
            ffw.write("#"                                   + "\r\n");
            ffw.write("# Véhicule :" + vehicule             + "\r\n");
            ffw.write("# Jour Arrivé :" + jourArr           + "\r\n");
            ffw.write("# Heure Arrivée : " + heureArr       + "\r\n");
            ffw.write("# Jour Départ : " + jourDep          + "\r\n");
            ffw.write("# Heure Départ : " + heureDep        + "\r\n");
            ffw.write("# Tarif : "   + tarif + "€"          + "\r\n");
            ffw.write("#"                                   + "\r\n");
            ffw.write("# Client :"                          + "\r\n");
            ffw.write("# Nom : "     + client.getNom()      + "\r\n");
            ffw.write("# Prénom : "  + client.getPrenom()   + "\r\n");
            ffw.write("# Adresse : " + client.getAdresse()  + "\r\n");
            ffw.write("#########################################################");
            
            // Fermeture du fichier
            ffw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // sauvegarder()

} // Facture class
