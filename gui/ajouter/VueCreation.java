package parking.gui.ajouter;

import parking.gui.Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jonathan on 17/01/2015.
 */
public class VueCreation {

    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    /**
     *
     */
    private JFrame fenetre = new JFrame("Nouveau parking");

    private JPanel top, top1, top2, top2Left, top2Right,
            center, topCenter, midCenter, midCenterLeft, midCenterRight, midBottom,
            bottom, topBottom, topBottomLeft, topBottomRight,
            main;

    private JLabel labelPlacesMax, labelTarifParticulier, labelTarifTransporteur,
                   labelNomParking, labelNbPlacesParticulier, labelNbPlacesTransporteur;

    private JTextField PlacesMax, TarifParticulier, TarifTransporteur,
                       NomParking, NbPlacesParticulier, NbPlacesTransporteur;

    private final JButton Valider = new JButton();


    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueAjouterVehicule, permettant de
     */
    public VueCreation() {
        // fenetre
        fenetre.setLocation(0, 0);
        fenetre.setPreferredSize(new Dimension(320,260));
        fenetre.setDefaultCloseOperation(fenetre.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());
        fenetre.setJMenuBar(barreMenus());

        main = new JPanel();
        main.add(Top(), BorderLayout.NORTH);
        main.add(Center(), BorderLayout.CENTER);
        main.add(Bottom(), BorderLayout.SOUTH);

        fenetre.setContentPane(main);
        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setVisible(true);
    } // Constructeur


    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/


    /**
     * Methode barreMenus() permet de ...
     *
     * @return
     */
    private JMenuBar barreMenus() {
        JMenuBar barre = new JMenuBar();
        barre.add(creerMenuFichier());
        return barre;
    } // barreMenus()

    /**
     * Methode creerMenuFichier() permet de ...
     *
     * @return
     */
    private JMenu creerMenuFichier() {
        JMenu menuFichier = new JMenu("Fichier");
        menuFichier.add(creerMenuOuvrir());
        menuFichier.add(creerMenuFichierQuitter());
        return menuFichier;
    } // creerMenuFichier()

    private JMenuItem creerMenuOuvrir() {
        JMenuItem menu = new JMenuItem("Ouvrir");
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return menu;
    }

    /**
     * Methode creerMenuFichierQuitter() permet de ...
     *
     * @return
     */
    private JMenuItem creerMenuFichierQuitter() {
        JMenuItem menu = new JMenuItem("Quitter");
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionMenuFichierQuitter();
            }

        });
        return menu;
    } // creerMenuFichierQuitter()

    /**
     * Methode actionMenuFichierQuitter() permet de ...
     */
    private void actionMenuFichierQuitter() {
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(fenetre, "Voulez-vous vraiment quitter ?"))
            System.exit(0);
    } // actionMenuFichierQuitter()



    /**
     * Methode Top() permet de creer un panneau afin de ne
     * s'occuper que de la partie superieure de la fenetre.
     *
     * @return Le panneau en haut de la fenetre.
     */
    private JPanel Top() {

        // Top
        top = new JPanel();
        top.setLayout(new BorderLayout());

        labelPlacesMax = new JLabel("Nombre de places Max");
        PlacesMax = new JTextField();
        PlacesMax.setPreferredSize(new Dimension(300, 20));


        top1 = new JPanel();
        top1.setLayout(new BorderLayout());
        top1.add(labelPlacesMax, BorderLayout.NORTH);
        top1.add(PlacesMax, BorderLayout.CENTER);



        labelTarifParticulier = new JLabel("Tarif Particulier");
        TarifParticulier = new JTextField();
        TarifParticulier.setPreferredSize(new Dimension(140, 20));

        top2Left = new JPanel();
        top2Left.setLayout(new BorderLayout());
        top2Left.add(labelTarifParticulier, BorderLayout.NORTH);
        top2Left.add(TarifParticulier, BorderLayout.SOUTH);

        labelTarifTransporteur = new JLabel("Tarif Transporteur");
        TarifTransporteur = new JTextField();
        TarifTransporteur.setPreferredSize(new Dimension(140, 20));

        top2Right = new JPanel();
        top2Right.setLayout(new BorderLayout());
        top2Right.add(labelTarifTransporteur, BorderLayout.NORTH);
        top2Right.add(TarifTransporteur, BorderLayout.SOUTH);

        top2 = new JPanel();
        top2.setLayout(new BorderLayout());
        top2.add(top2Left, BorderLayout.WEST);
        top2.add(top2Right, BorderLayout.EAST);


        top.add(top1, BorderLayout.CENTER);
        top.add(top2, BorderLayout.SOUTH);

        return top;
    } // Top()

    /**
     * Methode Center() permet de creer un panneau afin de ne
     * s'occuper que de la partie centrale de la fenetre.
     *
     * @return Le panneau au centre de la fenetre.
     */
    private JPanel Center() {
        // Center
        center = new JPanel();
        center.setLayout(new BorderLayout());

        topCenter = new JPanel();
        topCenter.setLayout(new BorderLayout());


        labelNomParking = new JLabel("Nom Parking");
        NomParking = new JTextField();
        NomParking.setPreferredSize(new Dimension(300, 20));

        topCenter.add(labelNomParking, BorderLayout.NORTH);
        topCenter.add(NomParking, BorderLayout.CENTER);


        midCenter = new JPanel();
        midCenter.setLayout(new BorderLayout());

        midCenterLeft = new JPanel();
        midCenterLeft.setLayout(new BorderLayout());

        labelNbPlacesParticulier = new JLabel("Nb Places Particulier");
        NbPlacesParticulier = new JTextField();
        NbPlacesParticulier.setPreferredSize(new Dimension(140, 20));

        midCenterLeft.add(labelNbPlacesParticulier, BorderLayout.NORTH);
        midCenterLeft.add(NbPlacesParticulier, BorderLayout.CENTER);

        midCenterRight = new JPanel();
        midCenterRight.setLayout(new BorderLayout());

        labelNbPlacesTransporteur = new JLabel("Nb Places Transporteur");
        NbPlacesTransporteur = new JTextField();
        NbPlacesTransporteur.setPreferredSize(new Dimension(140, 20));

        midCenterRight.add(labelNbPlacesTransporteur, BorderLayout.NORTH);
        midCenterRight.add(NbPlacesTransporteur, BorderLayout.CENTER);

        midCenter.add(midCenterLeft, BorderLayout.WEST);
        midCenter.add(midCenterRight, BorderLayout.EAST);

        center.add(topCenter, BorderLayout.NORTH);
        center.add(midCenter, BorderLayout.CENTER);

        return center;
    } // Center()

    /**
     * Methode Bottom() permet de creer un panneau afin de ne
     * s'occuper que de la partie inferieure de la fenetre.
     *
     * @return Le panneau en bas de la fenetre.
     */
    private JPanel Bottom() {
        // Bottom
        bottom = new JPanel();
        bottom.setLayout(new BorderLayout());

        topBottom = new JPanel();
        topBottom.setLayout(new BorderLayout());




        Valider.setText("Valider");
        Valider.setPreferredSize(new Dimension(140, 40));
        /*Valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

        });*/




        bottom.add(Valider, BorderLayout.NORTH);


        return bottom;
    } // Bottom()

} // VueCreation
