package parking.business;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.exception.VehiculeGareException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrateur on 12/01/2015.
 */
public class Client {
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    private String nom;

    private String prenom;

    private String adresse;

    private int pointsDeFidelite;

    private ArrayList<Vehicule> listeVehicule;

    private CalculerTarif calculerTarif;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    public Client(String nom, String prenom, String adresse,CalculerTarif calculerTarif) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.listeVehicule = new ArrayList<Vehicule>();
        this.calculerTarif = calculerTarif;
    } // Constructeur

    /***************************************************************/
	/*						Getter								   */
    /***************************************************************/
    public String getNom() {
        return nom;
    } // getNom()

    public String getPrenom() {
        return prenom;
    } // getPrenom()

    public String getAdresse() { return adresse; } // getAdresse()

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
