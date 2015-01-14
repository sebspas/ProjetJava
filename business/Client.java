package parking.business;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.facture.CalculerTarif;
import parking.business.vehicule.Vehicule;
import parking.exception.business.VehiculeGareException;

import java.util.ArrayList;

/**
 * Class Client permettant d'ajouter un client en indiquant diverses informations telles que
 * son nom, son prenom, son adresse, ses points de fidelite, etc..
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class Client {
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    /**
     * Le nom du client.
     */
    private String nom;

    /**
     * Le prenom du client.
     */
    private String prenom;

    /**
     * L'adresse du client.
     */
    private String adresse;

    /**
     * Les points de fidelite du client.
     */
    private int pointsDeFidelite;

    /**
     * Le calcul (general) du tarif du client.
     */
    private CalculerTarif calculerTarif;

    /**
     * La liste des vehicules du client.
     */
    private ArrayList<Vehicule> listeVehicule;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe Client, permettant de construire/creer un client ainsi que
     * toute les specificites utiles pour un client.
     *
     * @param nom
     *          Le nom du client a creer.
     * @param prenom
     *          Le prenom du client a creer.
     * @param adresse
     *          L'adresse du client a creer.
     * @param calculerTarif
     *          Le calcul (general) du tarif du client a creer.
     */
    public Client(String nom, String prenom, String adresse,CalculerTarif calculerTarif) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.calculerTarif = calculerTarif;
        this.listeVehicule = new ArrayList<Vehicule>();
    } // Constructeur

    /***************************************************************/
	/*						Getter								   */
    /***************************************************************/
    /**
     * Methode getNom() renvoie le nom du client.
     *
     * @return Le nom du client
     */
    public String getNom() {
        return nom;
    } // getNom()

    /**
     * Methode getPrenom() renvoie le prenom du client.
     *
     * @return Le prenom du client.
     */
    public String getPrenom() {
        return prenom;
    } // getPrenom()

    /**
     * Methode getAdresse() renvoie l'adresse du client.
     *
     * @return L'adresse du client.
     */
    public String getAdresse() { return adresse; } // getAdresse()

    /**
     * 
     *
     * @return
     */
    public int getPointsDeFidelite() {
        return pointsDeFidelite;
    } // getPointsDeFidelite()

    public CalculerTarif getCalculerTarif() {
        return calculerTarif;
    } // getCalculerTarif()

    /***************************************************************/
	/*						Setter								   */
    /***************************************************************/
    public void setPointsDeFidelite(int pointsDeFidelite) { this.pointsDeFidelite = pointsDeFidelite; } // setPointsDeFidelite()

    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", methode tarif=" + calculerTarif.toString() +
                ", pointsDeFidelite=" + pointsDeFidelite +
                ", listeVehicule=" + listeVehicule +
                + '}';
    } // toString()

    public void addVehicule(Vehicule vehicule) { listeVehicule.add(vehicule); } // addVehicule()

    public void removeVehicule(Vehicule vehicule) throws VehiculeGareException {
        if (Parking.vehiculeExiste(vehicule))
            throw new VehiculeGareException();
        else
            listeVehicule.remove(vehicule);
    } // removeVehicule()

} // Client class
