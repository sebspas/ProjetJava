package parking.business.facture.Fabrique;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.facture.Calcul.CalculerTarif;
import parking.exception.business.MethodeNonGeree;

/**
 * Class IFabriqueCalculerTarif, fait appel au constructeur de CalculerTarifHeure ou
 * un autre type de calcul selon les parametres avec lesquels est appelee la fonction creer.
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public interface IFabriqueCalculerTarif {
    /***************************************************************/
	/*						Profil methodes						   */
    /***************************************************************/
    /**
     * Profil de la methode creer(), le corps de cette
     * methode se trouve dans la classe FabriqueCalculerTarif.
     *
     */
    public CalculerTarif creer(String nom) throws MethodeNonGeree;

} // IFabriqueCalculerTarif interface
