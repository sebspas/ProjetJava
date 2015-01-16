package parking.business.vehicule;

import parking.business.Client;

/**
 * Created by Administrateur on 16/01/2015.
 */
public interface IFabriqueVehicule {
    public Vehicule creer(String marque, String modele, String immatriculation, Client client);
    public Vehicule creer(String marque, String modele, String immatriculation, Client client, int tonnage, int hauteur);
}
