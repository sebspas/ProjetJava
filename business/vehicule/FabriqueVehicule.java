package parking.business.vehicule;

import parking.business.Client;

/**
 * Created by Administrateur on 16/01/2015.
 */
public class FabriqueVehicule implements IFabriqueVehicule {
    
    @Override
    public Vehicule creer(String immatriculation, String marque, String modele, Client client) {
            return new Voiture(immatriculation,marque,modele,client);
    }

    @Override
    public Vehicule creer(String immatriculation, String marque, String modele, Client client, int tonnage, int hauteur) {
        return new Camion(immatriculation,marque,modele,client,tonnage,hauteur);
    }
}
