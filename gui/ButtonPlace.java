package parking.gui;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Place;

import javax.swing.*;

/**
 * Class ButtonPlace, qui herite de la classe JButton, cree un bouton
 * avec la particularite de definir sa place dans le meme temps.
 *
 * @see JButton
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class ButtonPlace extends JButton{
    /**
     * La place que prendra le bouton.
     */
    private Place place;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe ButtonPlace, utilise le constructeur de la classe JButton,
     * et permet donc d'ajouter un bouton a l'endroit souhaite grace au parametre place.
     *
     * @param place
     *          La place que prendra le bouton.
     */
    public ButtonPlace(Place place) {
        // On construit un JButton
        super();
        
        // On définit la place associée au ButtonPlace
        this.place = place;
    } // Constructeur

    /***************************************************************/
	/*						Getter								   */
    /***************************************************************/
    /**
     * Methode getPlace() renvoie la place du bouton.
     *
     * @return La place du bouton.
     */
    public Place getPlace() {
        return place;
    } // getPlace()

} // ButtonPlace class
