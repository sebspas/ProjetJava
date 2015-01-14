package parking.gui;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/

import javax.swing.*;
import java.awt.*;

/**
 * Created by 2570P on 12/01/2015.
 */
public class VueAjouterVehicule extends Vue {
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    JFrame fenetre = new JFrame("Ajouter Vehicule");
    //JPanel listeVehicule = new JPanel();

    //private JComboBox client;
    //private JPanel panel1;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    public VueAjouterVehicule() {
        fenetre.setLocation(0, 0);
        fenetre.setPreferredSize(new Dimension(320,250));
        fenetre.setDefaultCloseOperation(fenetre.DISPOSE_ON_CLOSE);
        BorderLayout borderLayout = new BorderLayout();
        fenetre.setLayout(borderLayout);
        JPanel main = new JPanel();


        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());

            JLabel labelClient = new JLabel("Client");
            JComboBox client = new JComboBox();
            client.setPreferredSize(new Dimension(300, 20));

            JPanel topClient = new JPanel();
            topClient.setLayout(new BorderLayout());
            topClient.add(labelClient, BorderLayout.NORTH);
            topClient.add(client, BorderLayout.CENTER);


            JLabel labelTypeVehicule = new JLabel("Type de véhicule");
            JComboBox typeVehicule = new JComboBox();
            typeVehicule.addItem("Voiture");
            typeVehicule.addItem("Camion");
            typeVehicule.setPreferredSize(new Dimension(300, 20));

            JPanel topVehicule = new JPanel();
            topVehicule.setLayout(new BorderLayout());
            topVehicule.add(labelTypeVehicule, BorderLayout.NORTH);
            topVehicule.add(typeVehicule, BorderLayout.CENTER);

            top.add(topClient, BorderLayout.NORTH);
            top.add(topVehicule, BorderLayout.CENTER);

        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());

            JPanel topCenter = new JPanel();
            topCenter.setLayout(new BorderLayout());

                JLabel labelImmatriculation = new JLabel("Immatriculation");
                JTextField Immatriculation = new JTextField();
                Immatriculation.setPreferredSize(new Dimension(300, 20));



            topCenter.add(labelImmatriculation, BorderLayout.NORTH);
            topCenter.add(Immatriculation, BorderLayout.CENTER);


        JPanel midCenter = new JPanel();
        midCenter.setLayout(new BorderLayout());

            JPanel midCenterLeft = new JPanel();
            midCenterLeft.setLayout(new BorderLayout());
                JLabel labelMarque = new JLabel("Marque");
                JTextField Marque = new JTextField();
                Marque.setPreferredSize(new Dimension(140, 20));
            midCenterLeft.add(labelMarque, BorderLayout.NORTH);
            midCenterLeft.add(Marque, BorderLayout.CENTER);

            JPanel midCenterRight = new JPanel();
            midCenterRight.setLayout(new BorderLayout());
                JLabel labelModele = new JLabel("Modele");
                JTextField Modele = new JTextField();
                Modele.setPreferredSize(new Dimension(140, 20));
            midCenterRight.add(labelModele, BorderLayout.NORTH);
            midCenterRight.add(Modele, BorderLayout.CENTER);

        midCenter.add(midCenterLeft, BorderLayout.WEST);
        midCenter.add(midCenterRight, BorderLayout.EAST);

        center.add(topCenter, BorderLayout.NORTH);
        center.add(midCenter, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());

            JButton Valider = new JButton();
            Valider.setText("Valider");
            Valider.setPreferredSize(new Dimension(140,40));
            bottom.add(Valider, BorderLayout.WEST);

            JButton Annuler = new JButton();
            Annuler.setText("Annuler");
            Annuler.setPreferredSize(new Dimension(140,40));
            bottom.add(Annuler, BorderLayout.EAST);

        main.add(top, BorderLayout.NORTH);
        main.add(center, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.SOUTH);
        //this.setContentPane(container);


        fenetre.setContentPane(main);
        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setVisible(true);
    } // Constructeur()

    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    @Override
    public void mettreAJour() {
        //listeVehicule = AfficheListeVehicule();

        //listeVehicule.revalidate();
        //listeVehicule.repaint();
    } // mettreAJour()

} // VueAjouterVehicule class
