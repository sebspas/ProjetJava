package parking.gui;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/

import parking.business.Client;
import parking.business.Parking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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

    private JComboBox client;
    private JComboBox typeVehicule;

    private JTextField Immatriculation;
    private JTextField Marque;
    private JTextField Modele;
    private JTextField Hauteur;
    private JTextField Tonnage;
    
    public boolean validateData() {
        if (Immatriculation.getText() == null) {
            return false;
        }    
        if (Marque.getText() == null) {
            return false;
        }
        if (Modele.getText() == null) {
            return  false;
        }
        
        if (typeVehicule.getSelectedItem() == "Camion") {
            if (Hauteur.getText() == null) {
                return false;
            }
            if (Tonnage.getText() == null) {
                return false;
            }
        }
        return true;
    }

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    public VueAjouterVehicule() {
        // fenetre
        fenetre.setLocation(0, 0);
        fenetre.setPreferredSize(new Dimension(320,280));
        fenetre.setDefaultCloseOperation(fenetre.DISPOSE_ON_CLOSE);
        BorderLayout borderLayout = new BorderLayout();
        fenetre.setLayout(borderLayout);

        JPanel main = new JPanel();
        main.add(Top(), BorderLayout.NORTH);
        main.add(Center(), BorderLayout.CENTER);
        main.add(Bottom(), BorderLayout.SOUTH);

        fenetre.setContentPane(main);
        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setVisible(true);
    } // Constructeur()


    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/



    private JPanel Top() {

        // Top
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());

        JLabel labelClient = new JLabel("Client");
        client = new JComboBox();
        client.setPreferredSize(new Dimension(300, 20));

        afficherClients();
        fenetre.repaint();

        JPanel topClient = new JPanel();
        topClient.setLayout(new BorderLayout());
        topClient.add(labelClient, BorderLayout.NORTH);
        topClient.add(client, BorderLayout.CENTER);


        JLabel labelTypeVehicule = new JLabel("Type de véhicule");
        typeVehicule = new JComboBox();
        typeVehicule.addItem("Voiture");
        typeVehicule.addItem("Camion");
        typeVehicule.setPreferredSize(new Dimension(300, 20));
        typeVehicule.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object item = e.getItem();
                if ("Camion".equals(item)) {
                    Hauteur.setEnabled(true);
                    Tonnage.setEnabled(true);
                    fenetre.repaint();
                }
                else {
                    Hauteur.setEnabled(false);
                    Tonnage.setEnabled(false);
                    fenetre.repaint();
                }
            }
        });

        JPanel topVehicule = new JPanel();
        topVehicule.setLayout(new BorderLayout());
        topVehicule.add(labelTypeVehicule, BorderLayout.NORTH);
        topVehicule.add(typeVehicule, BorderLayout.CENTER);

        top.add(topClient, BorderLayout.NORTH);
        top.add(topVehicule, BorderLayout.CENTER);

        return top;
    } // Top()


    private JPanel Center() {
        // Center
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());

        JPanel topCenter = new JPanel();
        topCenter.setLayout(new BorderLayout());

        JLabel labelImmatriculation = new JLabel("Immatriculation");
        Immatriculation = new JTextField();
        Immatriculation.setPreferredSize(new Dimension(300, 20));


        topCenter.add(labelImmatriculation, BorderLayout.NORTH);
        topCenter.add(Immatriculation, BorderLayout.CENTER);


        JPanel midCenter = new JPanel();
        midCenter.setLayout(new BorderLayout());

        JPanel midCenterLeft = new JPanel();
        midCenterLeft.setLayout(new BorderLayout());
        JLabel labelMarque = new JLabel("Marque");
        Marque = new JTextField();
        Marque.setPreferredSize(new Dimension(140, 20));
        midCenterLeft.add(labelMarque, BorderLayout.NORTH);
        midCenterLeft.add(Marque, BorderLayout.CENTER);

        JPanel midCenterRight = new JPanel();
        midCenterRight.setLayout(new BorderLayout());
        JLabel labelModele = new JLabel("Modele");
        Modele = new JTextField();
        Modele.setPreferredSize(new Dimension(140, 20));
        midCenterRight.add(labelModele, BorderLayout.NORTH);
        midCenterRight.add(Modele, BorderLayout.CENTER);

        midCenter.add(midCenterLeft, BorderLayout.WEST);
        midCenter.add(midCenterRight, BorderLayout.EAST);

        center.add(topCenter, BorderLayout.NORTH);
        center.add(midCenter, BorderLayout.CENTER);

        return center;
    } // Center()


    private JPanel Bottom() {
        // Bottom
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());

        JPanel topBottom = new JPanel();
        topBottom.setLayout(new BorderLayout());


        JPanel topBottomLeft = new JPanel();
        topBottomLeft.setLayout(new BorderLayout());
        JLabel labelTonnage = new JLabel("Tonnage");
        Tonnage = new JTextField();
        Tonnage.setPreferredSize(new Dimension(140, 20));
        topBottomLeft.add(labelTonnage, BorderLayout.NORTH);
        topBottomLeft.add(Tonnage, BorderLayout.CENTER);

        JPanel topBottomRight = new JPanel();
        topBottomRight.setLayout(new BorderLayout());
        JLabel labelHauteur = new JLabel("Hauteur");
        Hauteur = new JTextField();
        Hauteur.setPreferredSize(new Dimension(140, 20));
        topBottomRight.add(labelHauteur, BorderLayout.NORTH);
        topBottomRight.add(Hauteur, BorderLayout.CENTER);

        topBottom.add(topBottomLeft, BorderLayout.WEST);
        topBottom.add(topBottomRight, BorderLayout.EAST);

        JPanel midBottom = new JPanel();
        midBottom.setLayout(new BorderLayout());

        final JButton Valider = new JButton();
        Valider.setText("Valider");
        Valider.setPreferredSize(new Dimension(140,40));



        final JButton Annuler = new JButton();
        Annuler.setText("Annuler");
        Annuler.setPreferredSize(new Dimension(140,40));
        Annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == Annuler) {
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(fenetre, "Voulez-vous vraiment annuler ?"))
                        fenetre.dispose();
                }

            }
        });

        midBottom.add(Valider, BorderLayout.WEST);
        midBottom.add(Annuler, BorderLayout.EAST);

        bottom.add(topBottom, BorderLayout.NORTH);
        bottom.add(midBottom, BorderLayout.SOUTH);


        Tonnage.setEnabled(false);
        Hauteur.setEnabled(false);

        return bottom;
    } // Bottom()


    public void afficherClients() {
        String identite;
        for (Client c : Parking.getListeClient()) {
            identite = c.getPrenom() + " " + c.getNom();
            client.addItem(identite);

        }
    }

    @Override
    public void mettreAJour() {
        //listeClients = AfficheListeVehicule();

        //listeVehicule.revalidate();
        //listeVehicule.repaint();
    } // mettreAJour()

} // VueAjouterVehicule class
