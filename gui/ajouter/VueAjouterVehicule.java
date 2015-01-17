package parking.gui.ajouter;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;
import parking.business.Parking;
import parking.business.vehicule.Fabrique.FabriqueVehicule;
import parking.business.vehicule.Fabrique.IFabriqueVehicule;
import parking.gui.Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Class VueAjouterVehicule, qui herite de la classe Vue,
 * qui cree une vue permettant d'ajouter un vehicule.
 *
 * @see Vue
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class VueAjouterVehicule extends Vue {
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    /**
     * La fenetre nommee "Ajouter Vehicule".
     */
    private JFrame fenetre = new JFrame("Ajouter Vehicule");
    //JPanel listeVehicule = new JPanel();

    //private JComboBox client;

    /**
     * Les differents panneaux correspondant à leur noms.
     */
    private JPanel top, topClient, topVehicule,
                   center, topCenter, midCenter, midCenterLeft, midCenterRight, midBottom,
                   bottom, topBottom, topBottomLeft, topBottomRight,
                   main;

    /**
     * Les differentes etiquettes correspondant à leur noms.
     */
    private JLabel labelClient, labelTypeVehicule,
                   labelImmatriculation, labelMarque, labelModele,
                   labelTonnage, labelHauteur;

    /**
     * Le bouton "Valider".
     */
    private final JButton Valider = new JButton();

    /**
     * Le bouton "Annuler".
     */
    private final JButton Annuler = new JButton();

    /**
     * Chaque ligne cliquable correspondant a un client.
     */
    private JComboBox client;

    /**
     * Chaque ligne cliquable correspondant a un type de vehicule.
     */
    private JComboBox typeVehicule;

    /**
     * Le champ de la plaque d'immatriculation.
     */
    private JTextField Immatriculation;

    /**
     * Le champ de la marque du vehicule.
     */
    private JTextField Marque;

    /**
     * Le champ du modele du vehicule.
     */
    private JTextField Modele;

    /**
     * Le champ de la hauteur du vehicule (pour les camions seulement).
     */
    private JTextField Hauteur;

    /**
     * Le champ du tonnage du vehicule (pour les camions seulement).
     */
    private JTextField Tonnage;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueAjouterVehicule, permettant de
     * creer une vue servant a ajouter un vehicule (voiture ou camion).
     */
    public VueAjouterVehicule() {
        // fenetre
        fenetre.setLocation(0, 0);
        fenetre.setPreferredSize(new Dimension(320,280));
        fenetre.setDefaultCloseOperation(fenetre.DISPOSE_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());

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
	/*						Setter								   */
    /***************************************************************/
    /**
     * Methode setVisible() permet d'indiquer si la fenetre doit etre visible ou non.
     *
     * @param visible
     *          Booleen affichant la fenetre si il vaut true, et
     *          ne rendant pas la fenetre visible si il vaut false.
     */
    @Override
    public void setVisible(boolean visible) {
        fenetre.setVisible(visible);
    } // setVisible()

    /***************************************************************/
	/*						Getter								   */
    /***************************************************************/
    /**
     * Methode getClient() renvoie le client correspondant au nom
     * et au prenom indiques dans les parametres.
     *
     * @param nomprenom
     *          Le nom et prenom du client.
     * @return Le client.
     */
    public Client getClient(String nomprenom) {
        String[] splited = nomprenom.split("\\s+");
        for (Client c : Parking.getInstance().getListeClient()) {
            String Nom = splited[0];
            String Prenom = splited[1];
            if (Nom.equals(c.getNom()) && Prenom.equals(c.getPrenom())) {
                return c;
            }
        }
        System.out.println("Echec");
        return null;
    } // getClient()

    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
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

        labelClient = new JLabel("Client");
        client = new JComboBox();
        client.setPreferredSize(new Dimension(300, 20));

        afficherClients();
        fenetre.repaint();

        topClient = new JPanel();
        topClient.setLayout(new BorderLayout());
        topClient.add(labelClient, BorderLayout.NORTH);
        topClient.add(client, BorderLayout.CENTER);


        labelTypeVehicule = new JLabel("Type de véhicule");
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

        topVehicule = new JPanel();
        topVehicule.setLayout(new BorderLayout());
        topVehicule.add(labelTypeVehicule, BorderLayout.NORTH);
        topVehicule.add(typeVehicule, BorderLayout.CENTER);

        top.add(topClient, BorderLayout.NORTH);
        top.add(topVehicule, BorderLayout.CENTER);

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

        labelImmatriculation = new JLabel("Immatriculation");
        Immatriculation = new JTextField();
        Immatriculation.setPreferredSize(new Dimension(300, 20));


        topCenter.add(labelImmatriculation, BorderLayout.NORTH);
        topCenter.add(Immatriculation, BorderLayout.CENTER);


        midCenter = new JPanel();
        midCenter.setLayout(new BorderLayout());

        midCenterLeft = new JPanel();
        midCenterLeft.setLayout(new BorderLayout());
        labelMarque = new JLabel("Marque");
        Marque = new JTextField();
        Marque.setPreferredSize(new Dimension(140, 20));
        midCenterLeft.add(labelMarque, BorderLayout.NORTH);
        midCenterLeft.add(Marque, BorderLayout.CENTER);

        midCenterRight = new JPanel();
        midCenterRight.setLayout(new BorderLayout());
        labelModele = new JLabel("Modele");
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


        topBottomLeft = new JPanel();
        topBottomLeft.setLayout(new BorderLayout());
        labelTonnage = new JLabel("Tonnage");
        Tonnage = new JTextField();
        Tonnage.setPreferredSize(new Dimension(140, 20));
        topBottomLeft.add(labelTonnage, BorderLayout.NORTH);
        topBottomLeft.add(Tonnage, BorderLayout.CENTER);

        topBottomRight = new JPanel();
        topBottomRight.setLayout(new BorderLayout());
        labelHauteur = new JLabel("Hauteur");
        Hauteur = new JTextField();
        Hauteur.setPreferredSize(new Dimension(140, 20));
        topBottomRight.add(labelHauteur, BorderLayout.NORTH);
        topBottomRight.add(Hauteur, BorderLayout.CENTER);

        topBottom.add(topBottomLeft, BorderLayout.WEST);
        topBottom.add(topBottomRight, BorderLayout.EAST);

        midBottom = new JPanel();
        midBottom.setLayout(new BorderLayout());


        Valider.setText("Valider");
        Valider.setPreferredSize(new Dimension(140, 40));
        Valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateData()) {
                    IFabriqueVehicule fabriqueVehicule = new FabriqueVehicule();
                    if (typeVehicule.getSelectedItem() == "Voiture") {;
                        fabriqueVehicule.creer(
                                Immatriculation.getText(),
                                Marque.getText(),
                                Modele.getText(),
                                getClient(client.getSelectedItem().toString())
                        );

                    } else if (typeVehicule.getSelectedItem() == "Camion") {
                        fabriqueVehicule.creer(
                                Immatriculation.getText(),
                                Marque.getText(),
                                Modele.getText(),
                                getClient(client.getSelectedItem().toString()),
                                new Integer(Tonnage.getText()),
                                new Integer(Hauteur.getText())
                        );
                    }


                    JOptionPane.showMessageDialog(fenetre,
                            "Véhicule " + Marque.getText() + " " + Modele.getText() + " ajouté avec succès !",
                            "Success",
                            JOptionPane.PLAIN_MESSAGE);

                    fenetre.dispose();
                } else {
                    JOptionPane.showMessageDialog(fenetre,
                            "Enregistrement impossible !",
                            "Inane error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });


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

    /**
     * Methode validateData() permet d'indiquer si les donnees ont ete valide ou non a l'aide d'un booleen.
     *
     * @return Booleen indiquant si les donnees sont validees (true) ou non (false).
     */
    public boolean validateData() {
        if (Immatriculation.getText().isEmpty()) {
            return false;
        }
        if (!Immatriculation.getText().matches("[-a-zA-Z0-9]{5,15}")) {
            JOptionPane.showMessageDialog(fenetre,
                    "Immatriculation incorrecte ! Espacer avec \"-\"",
                    "Erreur immatriculation",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (Marque.getText().isEmpty()) {
            return false;
        }
        if (!Marque.getText().matches("[a-zA-Z0-9\\s]{2,20}")) {
            JOptionPane.showMessageDialog(fenetre,
                    "Marque incorrecte !",
                    "Erreur marque",
                    JOptionPane.ERROR_MESSAGE);
            return false;            
        }
        if (Modele.getText().isEmpty()) {
            return  false;
        }
        if (!Modele.getText().matches("[a-zA-Z0-9\\s]{2,20}")) {
            JOptionPane.showMessageDialog(fenetre,
                    "Modele incorrect !",
                    "Erreur modele",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (typeVehicule.getSelectedItem() == "Camion") {
            if (Hauteur.getText().isEmpty()) {
                return false;
            }
            if (!Hauteur.getText().matches("[0-9.]{0,2}")) {
                JOptionPane.showMessageDialog(fenetre,
                        "Hauteur incorrecte !",
                        "Erreur hauteur",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (Tonnage.getText().isEmpty()) {
                return false;
            }
            if (!Tonnage.getText().matches("[0-9.]{0,2}")) {
                JOptionPane.showMessageDialog(fenetre,
                        "Tonnage incorrect !",
                        "Erreur tonnage",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        return true;
    } // validateData()

    /**
     * Methode afficherClients() permet d'afficher tous les clients par leur nom et prenom.
     */
    public void afficherClients() {
        String identite;
        for (Client c : Parking.getInstance().getListeClient()) {
            identite = c.getNom() + " " + c.getPrenom();
            client.addItem(identite);
        }
    } // afficherClients()

    /**
     * Methode mettreAJour() permet de mettre a jour la vue.
     */
    @Override
    public void mettreAJour() {
        return;
    } // mettreAJour()

} // VueAjouterVehicule class
