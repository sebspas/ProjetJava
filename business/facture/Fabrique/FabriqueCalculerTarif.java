package parking.business.facture.Fabrique;

import parking.business.facture.Calcul.CalculerTarif;
import parking.business.facture.Calcul.CalculerTarifHeure;
import parking.business.facture.Calcul.CalculerTarifPointsFidelite;
import parking.exception.business.MethodeNonGeree;

/**
 * Created by Administrateur on 17/01/2015.
 */
public class FabriqueCalculerTarif implements IFabriqueCalculerTarif {

    @Override
    public CalculerTarif creer(String nom) throws MethodeNonGeree {
        if (nom == "CalculerTarifHeure") {
            return new CalculerTarifHeure();
        }
        else if (nom == "CalculerTarifPointsFidelite") {
            return new CalculerTarifPointsFidelite();
        }
        else {
            throw new MethodeNonGeree();
        }
    }
}
