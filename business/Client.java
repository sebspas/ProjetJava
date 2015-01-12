package parking.business;

import parking.exception.VehiculeGareException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrateur on 12/01/2015.
 */
public class Client {
    private String nom;

    private String prenom;

    private String adresse;

    private int pointsDeFidelite;

    public String getAdresse() {
        return adresse;
    }

    public String getPrenom() {
        return prenom;
    }

    private ArrayList<Vehicule> listeVehicule;

    private CalculerTarif calculerTarif;

    public Client(String nom, String prenom, String adresse,CalculerTarif calculerTarif) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.listeVehicule = new ArrayList<Vehicule>();
        this.calculerTarif = calculerTarif;
    }

    public int getPointsDeFidelite() {
        return pointsDeFidelite;
    }

    public CalculerTarif getCalculerTarif() {
        return calculerTarif;
    }

    public String getNom() {
        return nom;
    }

    public void setPointsDeFidelite(int pointsDeFidelite) {
        this.pointsDeFidelite = pointsDeFidelite;
    }

    public void addVehicule(Vehicule vehicule) {
        listeVehicule.add(vehicule);
    }

    public void removeVehicule(Vehicule vehicule) throws VehiculeGareException {
        if (Parking.vehiculeExiste(vehicule))
            throw new VehiculeGareException();
        else
            listeVehicule.remove(vehicule);
    }

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
    }
}
