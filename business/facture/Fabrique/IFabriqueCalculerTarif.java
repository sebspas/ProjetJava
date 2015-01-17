package parking.business.facture.Fabrique;

import parking.business.facture.Calcul.CalculerTarif;
import parking.exception.business.MethodeNonGeree;

/**
 * Created by Administrateur on 17/01/2015.
 */
public interface IFabriqueCalculerTarif {
    
    public CalculerTarif creer(String nom) throws MethodeNonGeree;
}
