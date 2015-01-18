package parking.gui.gerer;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;
import parking.business.Parking;
import parking.business.Place;
import parking.business.vehicule.Vehicule;
import parking.gui.Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;

/**
 * Class VueVehicule, herite de la classe Vue, qui cree une vue permettant
 * de gerer les vehicules avec la possibilites de les "parker" ou de
 * les "deparker" en choisisant parmi les vehicules de chaque client.
 *
 * @see Vue, Serializable
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class VueVehicule extends Vue implements Serializable{
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    /**
     * La fenetre nommee "Gestion Vehicule".
     */
    private JFrame fenetre = new JFrame("Gestion Vehicule");

    /**
     * Les panneaux correspondant a leur nomenclature.
     */
    private JPanel top, topClient, topVehicule,
                   center,
                   main;

    /**
     * Le panneau permettant de choisir un vehicule parmi une liste.
     */
    private JPanel listeVehicule = new JPanel();

    /**
     * Les etiquettes concernant un client ainsi que le type d'un vehicule.
     */
    private JLabel labelClient, labelTypeVehicule;

    /**
     * Chaque ligne cliquable correspondant a un client.
     */
    private JComboBox client;

    /**
     * Chaque ligne cliquable correspondant a un vehicule.
     */
    private JComboBox vehicule;

    /**
     * Le bouton permettant de "parker" un vehicule.
     */
    private JButton parker_vehicule;

    /**
     * Le bouton permettant de "deparker" un vehicule.
     */
    private JButton unpark_vehicule;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueVehicule, permettant de creer une vue
     * donnant acces aux boutons park_vehicule et unpark_vehicule sur un vehicule.
     */
    public VueVehicule() {
        Parking.getInstance().addVue(this);
        fenetre.setLocation(0, 0);
        fenetre.setPreferredSize(new Dimension(320,180));
        fenetre.setDefaultCloseOperation(fenetre.DISPOSE_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());
        main = new JPanel();


        main.add(Top(), BorderLayout.NORTH);
        main.add(Center(), BorderLayout.SOUTH);

        fenetre.setContentPane(main);
        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setVisible(true);
    } // Constructeur

    /***************************************************************/
	/*						Getter								   */
    /***************************************************************/
    /**
     * Methode getClient() renvoie le client passe en parametre.
     *
     * @param nomprenom
     *          Le nom et prenom du client.
     * @return Le client attendu.
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

    /**
     * Methode getVehicule() renvoie le vehicule passe en parametre.
     *
     * @param vehicule
     *          Le vehicule d'un client.
     * @return Le vehicule attendu.
     */
    public Vehicule getVehicule(String vehicule) {
        Client c1 = getClient(client.getSelectedItem().toString());
        String[] splited = vehicule.split("\\s+");
        for (Vehicule v : c1.getListeVehicule()) {
            if (v.getImmatriculation().equals(splited[3])) {
                return v;
            }
        }
        System.out.println("Echec");
        return null;
    } // getVehicule()

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

        labelTypeVehicule = new JLabel("Véhicule");
        vehicule = new JComboBox();
        vehicule.setPreferredSize(new Dimension(300, 20));
        vehicule.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (vehicule.getItemCount() != 0) {
                    Vehicule v = getVehicule(vehicule.getSelectedItem().toString());
                    if (Parking.getInstance().vehiculeGare(v)) {
                        parker_vehicule.setEnabled(false);
                        unpark_vehicule.setEnabled(true);
                    }
                    else {
                        parker_vehicule.setEnabled(true);
                        unpark_vehicule.setEnabled(false);
                    }
                }
            }
        });

        client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicule.removeAllItems();
                Client client1 = getClient(client.getSelectedItem().toString());
                if(!client1.getListeVehicule().isEmpty()) {
                    for (Vehicule v1 : client1.getListeVehicule()) {
                        vehicule.addItem(v1);
                    }
                    vehicule.setSelectedIndex(0);
                }
                else {
                    parker_vehicule.setEnabled(false);
                    unpark_vehicule.setEnabled(false);
                }
            }
        });
        
        topVehicule = new JPanel();
        topVehicule.setLayout(new BorderLayout());
        topVehicule.add(labelTypeVehicule, BorderLayout.NORTH);
        topVehicule.add(vehicule, BorderLayout.CENTER);

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
        center = new JPanel();
        center.setLayout(new BorderLayout());

        parker_vehicule = new JButton();
        parker_vehicule.setText("Park le vehicule");
        parker_vehicule.setPreferredSize(new Dimension(150, 40));

        unpark_vehicule = new JButton();
        unpark_vehicule.setText("Unpark le vehicule");
        unpark_vehicule.setPreferredSize(new Dimension(150, 40));

        parker_vehicule.setEnabled(false);
        unpark_vehicule.setEnabled(false);

        center.add(parker_vehicule, BorderLayout.WEST);
        parker_vehicule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vehicule v = getVehicule(vehicule.getSelectedItem().toString());
                Parking.getInstance().park(v);
                parker_vehicule.setEnabled(false);
                unpark_vehicule.setEnabled(true);
            }
        });

        center.add(unpark_vehicule, BorderLayout.EAST);
        unpark_vehicule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vehicule v = getVehicule(vehicule.getSelectedItem().toString());
                Parking.getInstance().retirerVehicule(v.getImmatriculation());
                parker_vehicule.setEnabled(true);
                unpark_vehicule.setEnabled(false);
            }
        });

        return center;
    } // Center()

    /**
     * Methode afficherClients() permet d'afficher la liste des clients.
     */
    public void afficherClients() {
        String identite;
        for (Client c : Parking.getInstance().getListeClient()) {
            identite = c.getNom() + " " + c.getPrenom();
            client.addItem(identite);
        }

    } // afficherClients()

    /**
     * Methode AfficheListeVehicule() permet d'afficher la liste des vehicules.
     *
     * @return La liste des vehicules.
     */
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
        for (Place p : Parking.getInstance().getListePlaces()) {
            if (p.getVehicule() != null) {
                dlm.addElement(p.getVehicule());
            }
        }
        listeVehicule.add(listScroller);
        return listeVehicule;
    } // AfficheListeVehicule()

    /**
     * Methode mettreAJour() permet de mettre a jour la vue.
     */
    @Override
    public void mettreAJour() {
        listeVehicule = AfficheListeVehicule();

        listeVehicule.revalidate();
        listeVehicule.repaint();
    } // mettreAJour()

} // VueVehicule class
