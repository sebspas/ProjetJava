package parking.gui;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;
import parking.business.Parking;
import parking.business.vehicule.Camion;
import parking.business.vehicule.Voiture;

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
 * @author Chergui, Coadalen, Corfa, Corral
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

    /**
     * Methode validateData() permet de
     *
     * @return
     */
    public boolean validateData() {
        if (Immatriculation.getText().isEmpty()) {
            return false;
        }    
        if (Marque.getText().isEmpty()) {
            return false;
        }
        if (Modele.getText().isEmpty()) {
            return  false;
        }
        
        if (typeVehicule.getSelectedItem() == "Camion") {
            if (Hauteur.getText().isEmpty()) {
                return false;
            }
            if (Tonnage.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    } // validateData()

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueAjouterVehicule(), permettant de
     */
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
	/*						Getter								   */
    /***************************************************************/
    /**
     * Methode getClient() renvoie ...
     *
     * @param nomprenom
     *          Le nom et prenom du client.
     * @return Le client.
     */
    public Client getClient(String nomprenom) {
        String[] splited = nomprenom.split("\\s+");
        for (Client c : Parking.getListeClient()) {
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
     * Methode Top() permet de ...
     *
     * @return Le panneau en haut de la fenetre.
     */
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

    /**
     * Methode Center() permet de
     *
     * @return Le panneau au centre de la fenetre.
     */
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

    /**
     * Methode Bottom() permet de
     *
     * @return Le panneau en bas de la fenetre.
     */
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
        Valider.setPreferredSize(new Dimension(140, 40));
        Valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateData()) {
                    if (typeVehicule.getSelectedItem() == "Voiture") {;
                        new Voiture(
                                Immatriculation.getText(),
                                Marque.getText(),
                                Modele.getText(),
                                getClient(client.getSelectedItem().toString())
                        );

                    } else if (typeVehicule.getSelectedItem() == "Camion") {
                        new Camion(
                                Immatriculation.getText(),
                                Marque.getText(),
                                Modele.getText(),
                                getClient(client.getSelectedItem().toString()),
                                new Integer(Tonnage.getText()),
                                new Integer(Hauteur.getText())
                        );
                    }

                    fenetre.dispose();
                } else {
                    JOptionPane.showMessageDialog(fenetre,
                            "Vous devez remplir tous les champs !",
                            "Inane error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });


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

    /**
     * Methode afficherClients() permet de
     */
    public void afficherClients() {
        String identite;
        for (Client c : Parking.getListeClient()) {
            identite = c.getNom() + " " + c.getPrenom();
            client.addItem(identite);
        }
    } // afficherClients()

    /**
     * Methode mettreAJour() permet de
     */
    @Override
    public void mettreAJour() {
        //listeClients = AfficheListeVehicule();

        //listeVehicule.revalidate();
        //listeVehicule.repaint();
    } // mettreAJour()

} // VueAjouterVehicule class
