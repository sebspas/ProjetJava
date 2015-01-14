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
     * toute les informations utiles pour un client comme son nom, son prenom, son adresse, etc..
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
        Parking.addClient(this);
    } // Constructeur

    /***************************************************************/
	/*						Getter								   */
    /***************************************************************/
    public ArrayList<Vehicule> getListeVehicule() {
        return listeVehicule;
    }

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
     * Methode getPointsDeFidelite() renvoie le nombre de points de fidelite du client.
     *
     * @return Le nombre de points de fidelite du client.
     */
    public int getPointsDeFidelite() {
        return pointsDeFidelite;
    } // getPointsDeFidelite()

    /**
     * Methode getCalculerTarif() renvoie le calcul du tarif (general) du client.
     *
     * @return Le calcul du tarif du client.
     */
    public CalculerTarif getCalculerTarif() {
        return calculerTarif;
    } // getCalculerTarif()

    /***************************************************************/
	/*						Setter								   */
    /***************************************************************/
    /**
     * Methode setPointsDeFidelite() modifie le nombre de points de fidelite du client.
     *
     * @param pointsDeFidelite
     *          Le nombre de points de fidelite du client.
     */
    public void setPointsDeFidelite(int pointsDeFidelite) { this.pointsDeFidelite = pointsDeFidelite; } // setPointsDeFidelite()

    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    /**
     * Methode toString() affiche toutes les informations du client.
     *
     * @return Une chaine de caracteres contenant les informations.
     */
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

    /**
     * Methode addVehicule() ajoute un vehicule a la liste de tous les vehicules existants,
     * qu'ils soient sur le parking ou non.
     *
     * @param vehicule
     *          Le vehicule a ajouter.
     */
    public void addVehicule(Vehicule vehicule) { listeVehicule.add(vehicule); } // addVehicule()

    /**
     * Methode removeVehicule() supprime un vehicule de la liste de tous les vehicules existants.
     * Si le vehicule n'existe pas, la methode se contente de propager une exception.
     *
     * @param vehicule
     *          Le vehicule a supprimer.
     * @throws VehiculeGareException
     *          L'exception propagee en cas d'erreur.
     */
    public void removeVehicule(Vehicule vehicule) throws VehiculeGareException {
        if (Parking.vehiculeGare(vehicule))
            throw new VehiculeGareException();
        else
            listeVehicule.remove(vehicule);
    } // removeVehicule()

} // Client class
