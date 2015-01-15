package parking.gui;

import parking.business.Place;

import javax.swing.*;

/**
 * Created by Administrateur on 15/01/2015.
 */
public class ButtonPlace extends JButton{
    private Place place;
    
    public ButtonPlace(Place place) {
        super();
        this.place = place;
    }

    public Place getPlace() {
        return place;
    }
}
