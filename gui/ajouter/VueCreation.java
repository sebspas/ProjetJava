package parking.gui.ajouter;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;
import parking.business.Gestionnaire;
import parking.business.Parking;
import parking.business.Place;
import parking.business.facture.Calcul.CalculerTarifHeure;
import parking.business.facture.Calcul.CalculerTarifPointsFidelite;
import parking.business.vehicule.Fabrique.FabriqueVehicule;
import parking.business.vehicule.Fabrique.IFabriqueVehicule;
import parking.business.vehicule.Vehicule;
import parking.gui.Vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Class VueCreation permettant de creer une vue qui sera la toute
 * premiere fenetre de l'application et qui aura pour but de creer un
 * parking type selon differents criteres comme son nom, sa capacite maximum
 * de places, son nombre de place de type particulier ou de type transporteur.
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class VueCreation {
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    /**
     *  La fenetre de cette vue nommee "Nouveau parking".
     */
    private JFrame fenetre = new JFrame("Nouveau parking");

    /**
     *  Les differents panneaux permettant de decomposer la fenetre en plusieurs parties.
     *  Chaque partie etant plus ou moins bien appelee en fonction de sa position dans la fenetre.
     */
    private JPanel top, top1, top2, top2Left, top2Right,
            center, topCenter, midCenter, midCenterLeft, midCenterRight, midBottom,
            bottom, topBottom, topBottomLeft, topBottomRight,
            main;

    /**
     * L'image/l'icone bien connue d'un parking represente par un "P"
     */
    private ImageIcon img = new ImageIcon(getClass().getResource("../ressources/parking.png"));

    /**
     * Les differentes etiquettes servant a donner un renseignement sur chaque champ de cette vue.
     */
    private JLabel JLimg, labelPlacesMax, labelTarifParticulier, labelTarifTransporteur,
                   labelNomParking, labelNbPlacesParticulier, labelNbPlacesTransporteur;

    /**
     * Les differents champs de texte de la fenetre dont leur label a ete precise ci-dessus.
     */
    private JTextField PlacesMax, TarifParticulier, TarifTransporteur,
                       NomParking, NbPlacesParticulier, NbPlacesTransporteur;

    /**
     * Le bouton "Valider" de cette vue.
     */
    private final JButton Valider = new JButton();


    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueCreation, permettant de creer une nouvelle
     * vue representant la creation d'un parking ainsi que la possibilite
     * de lui donner des valeurs afin de le parametrer comme on le souhaite.
     */
    public VueCreation() {
        // fenetre
        fenetre.setLocation(0, 0);
        fenetre.setPreferredSize(new Dimension(320,320));
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
     * Methode barreMenus() permet de creer un menu sous forme de
     * barre horizontale et qui contient plusieurs fonctionnalites.
     *
     * @return La barre de menu.
     */
    private JMenuBar barreMenus() {
        JMenuBar barre = new JMenuBar();
        barre.add(creerMenuFichier());
        return barre;
    } // barreMenus()

    /**
     * Methode creerMenuFichier() permet de creer une section "Fichier" dans la barre de menu.
     *
     * @return Le menu "Fichier".
     */
    private JMenu creerMenuFichier() {
        JMenu menuFichier = new JMenu("Fichier");
        menuFichier.add(creerMenuOuvrir());
        menuFichier.add(creerMenuFichierQuitter());
        return menuFichier;
    } // creerMenuFichier()

    /**
     * Methode creerMenuOuvrir() permet de creer un item "Ouvrir" dans le menu "Fichier".
     *
     * @return L'item "Ouvrir".
     */
    private JMenuItem creerMenuOuvrir() {
        JMenuItem menu = new JMenuItem("Ouvrir");
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser dialogue = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Fichiers ser.", "ser");
                dialogue.addChoosableFileFilter(filter);
                dialogue.setAcceptAllFileFilterUsed(false);
                dialogue.setCurrentDirectory(new File("./saves"));
                dialogue.showOpenDialog(null);
                if (dialogue.getSelectedFile() != null) {
                    Parking.getInstance();
                    Gestionnaire gestionnaire = new Gestionnaire();
                    gestionnaire.lire(dialogue.getSelectedFile().toString());
                    fenetre.setVisible(false);
                }
            }
        });
        return menu;
    }

    /**
     * Methode creerMenuFichierQuitter() permet de creer un item "Quitter" dans le menu "Fichier".
     *
     * @return L'item "Quitter".
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
     * Methode actionMenuFichierQuitter() permet d'afficher un message
     * de confirmation lors de la fermeture de la fenetre principale.
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

        JLimg = new JLabel(img, JLabel.CENTER);

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


        top.add(JLimg, BorderLayout.NORTH);
        top.add(top1, BorderLayout.CENTER);
        top.add(top2, BorderLayout.SOUTH);

        return top;
    } // Top()

    /**
     * Methode Center() permet de creer un panneau afin de
     * ne s'occuper que de la partie centrale de la fenetre.
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
        Valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateData()) {
                    Parking p = Parking.getInstance();
                    p.setNom(NomParking.getText());
                    p.setNbPlacesMax(new Integer(PlacesMax.getText()));

                    double tarifParticulier = new Double(TarifParticulier.getText());
                    double tarifTransporteur = new Double(TarifTransporteur.getText());

                    p.setTarifParticulier(tarifParticulier);
                    p.setTarifTransporteur(tarifTransporteur);

                    int nbParticulier = new Integer(NbPlacesParticulier.getText());
                    int nbTransporteur = new Integer(NbPlacesTransporteur.getText());
                    // Creation des places Particulier //
                    for (int i = 0; i < nbParticulier; ++i) {
                        new Place("Particulier");
                    }

                    // Creation des places Transporteur //
                    for (int i = 0; i < nbTransporteur; ++i) {
                        new Place("Transporteur");
                    }
                    
                    if (NomParking.getText().equals("default")) {
                        // Creation des clients //
                        Client client1 = new Client("Jean", "Carna", "2, Rue des Concepts", new CalculerTarifHeure());
                        Client client2 = new Client("Tom", "Galendo", "44, Chemin de Chateau Roi", new CalculerTarifHeure());
                        Client client3 = new Client("Claude", "Hamari", "57 Avenue de la Garde", new CalculerTarifPointsFidelite());
                        Client client4 = new Client("Kevin", "Alama", "29 Traverse des Buissons", new CalculerTarifHeure());
                        Client client5 = new Client("Michel", "Bernard", "5, Avenue de la Republique", new CalculerTarifHeure());
                        Client client6 = new Client("Nathan", "Delamard", "149, Route des Mirages", new CalculerTarifHeure());

                        // Creation des vehicules //
                        IFabriqueVehicule fabriqueVehicule = new FabriqueVehicule();
                        fabriqueVehicule.creer("AB-531-MT-13", "Citroen", "C3", client1);
                        fabriqueVehicule.creer("BT-640-AE-83", "Peugeot", "306", client2);
                        fabriqueVehicule.creer("AN-155-GT-45", "Renault", "Scenic", client3);
                        fabriqueVehicule.creer("CD-294-ZE-69", "Audi", "SWAG", client3);
                        fabriqueVehicule.creer("BG-951-KC-29", "GMC", "Savana", client4, 15, 355);
                        fabriqueVehicule.creer("AP-735-LP-84", "International", "CF600", client5, 15, 355);
                        fabriqueVehicule.creer("BR-848-MM-75", "Chevrolet", "Express", client5, 15, 355);
                        fabriqueVehicule.creer("CZ-609-OK-31", "Ford", "E350", client6, 15, 355);
                    }
                    fenetre.setVisible(false);
                }
            }
        });
        
        bottom.add(Valider, BorderLayout.NORTH);
        
        return bottom;
    } // Bottom()

    /**
     * Methode validateData() permet d'indiquer si les donnees ont ete valide ou non a l'aide d'un booleen.
     *
     * @return Booleen indiquant si les donnees sont validees (true) ou non (false).
     */
    public boolean validateData() {
        if (NomParking.getText().isEmpty()) {
            return false;
        }
        if (!NomParking.getText().matches("[-a-zA-Z0-9]{5,15}")) {
            JOptionPane.showMessageDialog(fenetre,
                    "NomParking incorrect !",
                    "Erreur NomParking",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (TarifParticulier.getText().isEmpty()) {
            return false;
        }
        if (!TarifParticulier.getText().matches("[0-9.]{1,4}")) {
            JOptionPane.showMessageDialog(fenetre,
                    "TarifParticulier incorrect !",
                    "Erreur TarifParticulier",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (TarifTransporteur.getText().isEmpty()) {
            return false;
        }
        if (!TarifTransporteur.getText().matches("[0-9.]{1,4}")) {
            JOptionPane.showMessageDialog(fenetre,
                    "TarifTransporteur incorrect !",
                    "Erreur TarifTransporteur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (NbPlacesTransporteur.getText().isEmpty()) {
            return false;
        }
        if (!NbPlacesTransporteur.getText().matches("[0-9]{1,4}")) {
            JOptionPane.showMessageDialog(fenetre,
                    "NbPlacesTransporteur incorrect !",
                    "Erreur NbPlacesTransporteur",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (NbPlacesParticulier.getText().isEmpty()) {
            return false;
        }
        if (!NbPlacesParticulier.getText().matches("[0-9]{1,4}")) {
            JOptionPane.showMessageDialog(fenetre,
                    "NbPlacesParticulier incorrect !",
                    "Erreur NbPlacesParticulier",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (PlacesMax.getText().isEmpty()) {
            return false;
        }
        if (!PlacesMax.getText().matches("[0-9]{1,4}")) {
            JOptionPane.showMessageDialog(fenetre,
                    "PlacesMax incorrectes !",
                    "Erreur PlacesMax",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int NbPlaceP = new Integer(NbPlacesParticulier.getText());
        int NbPlaceT = new Integer(NbPlacesTransporteur.getText());
        int NbPlaceMax = new Integer(PlacesMax.getText());
        
        if (NbPlaceMax < (NbPlaceP + NbPlaceT ) || (NbPlaceMax != (NbPlaceP + NbPlaceT))) {
            JOptionPane.showMessageDialog(fenetre,
                    "PlacesMax incorrectes !",
                    "Erreur PlacesMax",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    } // validateData()

} // VueCreation class
