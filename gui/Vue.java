package parking.gui;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Parking;
import parking.business.Timer;

/**
 * Class Vue permettant de creer une nouvelle vue (generale) contenant uniquement un parking.
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public abstract class Vue {
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    /**
     * Le parking de la vue.
     */
    protected Parking parking;

    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    /**
     * Methode abstraite mettreAJour() servant a mettre a jour les vues.
     */
    public abstract void mettreAJour();

    /**
     * Methode permettant de cacher ou afficher une vue.
     *
     * @param visible
     */
    public abstract void setVisible(boolean visible);

} // Vue class
