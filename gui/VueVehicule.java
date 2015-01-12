package parking.gui;

import parking.business.Parking;
import parking.business.Place;
import parking.business.Vehicule;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Administrateur on 11/01/2015.
 */
public class VueVehicule extends Vue{
    JFrame fenetre = new JFrame("Gestion Vehicule");
    JPanel listeVehicule = new JPanel();

    @Override
    public void mettreAJour() {
        listeVehicule = AfficheListeVehicule();

        listeVehicule.revalidate();
        listeVehicule.repaint();
    }

    public VueVehicule() {
        fenetre.setLocation(0, 0);
        fenetre.setPreferredSize(new Dimension(520,450));
        fenetre.setDefaultCloseOperation(fenetre.DISPOSE_ON_CLOSE);
        BorderLayout borderLayout = new BorderLayout();
        fenetre.setLayout(borderLayout);
        JPanel main = new JPanel();

        JPanel bouton_bas = new JPanel();
        JButton ajouter = new JButton();
        ajouter.setText("Ajouter Vehicule");
        ajouter.setPreferredSize(new Dimension(260,40));

        JButton supprimer = new JButton();
        supprimer.setText("Supprimer Vehicule");
        supprimer.setPreferredSize(new Dimension(260,40));

        bouton_bas.add(ajouter);
        bouton_bas.add(supprimer);

        main.add(listeVehicule, BorderLayout.NORTH);
        main.add(bouton_bas, BorderLayout.SOUTH);

        fenetre.setContentPane(main);
        fenetre.pack();

        fenetre.setVisible(true);
    }

    private JPanel AfficheListeVehicule() {
        listeVehicule.removeAll();
        DefaultListModel dlm = new DefaultListModel();
        JList list = new JList(dlm);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(500, 350));
        list.setVisibleRowCount(10);
        for (Place p : Parking.getListeVehicules()) {
            if (p.getVehicule() != null) {
                dlm.addElement(p.getVehicule());
            }
        }
        listeVehicule.add(listScroller);
        return listeVehicule;
    }
}
