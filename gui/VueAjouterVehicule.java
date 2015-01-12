package parking.gui;

import parking.business.Parking;
import parking.business.Place;
import parking.business.Vehicule;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by 2570P on 12/01/2015.
 */
public class VueAjouterVehicule extends Vue {

    JFrame fenetre = new JFrame("Ajouter Vehicule");
    //JPanel listeVehicule = new JPanel();

    //private JComboBox client;
    //private JPanel panel1;

    @Override
    public void mettreAJour() {
        //listeVehicule = AfficheListeVehicule();

        //listeVehicule.revalidate();
        //listeVehicule.repaint();
    }

    public VueAjouterVehicule() {
        fenetre.setLocation(0, 0);
        fenetre.setPreferredSize(new Dimension(320,450));
        fenetre.setDefaultCloseOperation(fenetre.DISPOSE_ON_CLOSE);
        BorderLayout borderLayout = new BorderLayout();
        fenetre.setLayout(borderLayout);
        JPanel main = new JPanel();

        JLabel labelClient = new JLabel("Client");
        JComboBox client = new JComboBox();
        client.setPreferredSize(new Dimension(300, 20));

        JLabel labelTypeVehicule = new JLabel("Type de véhicule");
        JComboBox typeVehicule = new JComboBox();
        typeVehicule.addItem("Voiture");
        typeVehicule.addItem("Camion");
        typeVehicule.setPreferredSize(new Dimension(300, 20));

        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());

            JPanel topClient = new JPanel();
            topClient.setLayout(new BorderLayout());
            topClient.add(labelClient, BorderLayout.NORTH);
            topClient.add(client, BorderLayout.CENTER);

            JPanel topVehicule = new JPanel();
            topVehicule.setLayout(new BorderLayout());
            topVehicule.add(labelTypeVehicule, BorderLayout.NORTH);
            topVehicule.add(typeVehicule, BorderLayout.CENTER);

            top.add(topClient, BorderLayout.NORTH);
            top.add(topVehicule, BorderLayout.CENTER);

        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());

        


        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());

            JButton Valider = new JButton();
            Valider.setText("Valider");
            Valider.setPreferredSize(new Dimension(150,40));
            bottom.add(Valider, BorderLayout.WEST);

            JButton Annuler = new JButton();
            Annuler.setText("Annuler");
            Annuler.setPreferredSize(new Dimension(150,40));
            bottom.add(Annuler, BorderLayout.EAST);

        main.add(top, BorderLayout.NORTH);
        main.add(bottom, BorderLayout.SOUTH);
        //this.setContentPane(container);



        fenetre.setContentPane(main);
        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setVisible(true);
    }




} // VueAjouterVehicule
