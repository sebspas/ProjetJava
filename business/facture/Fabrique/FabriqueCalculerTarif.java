package parking.business.facture.Fabrique;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.facture.Calcul.CalculerTarif;
import parking.business.facture.Calcul.CalculerTarifHeure;
import parking.business.facture.Calcul.CalculerTarifPointsFidelite;
import parking.business.facture.Calcul.CalculertarifPreferentiel;
import parking.exception.business.MethodeNonGeree;

/**
 * Class FabriqueCalculerTarif, qui implemente la classe IFabriqueCalculerTarif,
 * permet de creer un CalculerTarifHeure ou un autre type de calcul
 * selon les parametres avec lesquels est appelee la fonction creer.
 *
 * @see IFabriqueCalculerTarif
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class FabriqueCalculerTarif implements IFabriqueCalculerTarif {
    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    /**
     * Methode creer() permettant la creation de la methode
     * de calcul correspondant au nom passe en parametre.
     *
     * @param nom
     *          Nom de la methode de calcul.
     * @return CalculerTarif
     *          Methode de calcul chosit.
     * @throws MethodeNonGeree
     */
    @Override
    public CalculerTarif creer(String nom) throws MethodeNonGeree {
        if (nom == "CalculerTarifHeure") {
            return new CalculerTarifHeure();
        }
        else if (nom == "CalculerTarifPointsFidelite") {
            return new CalculerTarifPointsFidelite();
        }
        else if (nom == "CalculerTarifPreferentiel") {
            return new CalculertarifPreferentiel();
        }
        else {
            throw new MethodeNonGeree();
        }
    } // creer()

} // FabriqueCalculerTarif class
