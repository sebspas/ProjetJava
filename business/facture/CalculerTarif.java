package parking.business.facture;

import parking.business.Place;

/**
 * Interface CalculerTarif, utilisant la fonction calculerTarif(),
 * permettant de calculer le tarif d'une place donnee en parametre.
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public interface CalculerTarif {

    public double calculerTarif(Place p);

}
