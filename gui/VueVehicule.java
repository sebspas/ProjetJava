package parking.gui;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;
import parking.business.Parking;
import parking.business.Place;
import parking.business.vehicule.Vehicule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Class VueVehicule, herite de la classe Vue, permettant de ...
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class VueVehicule extends Vue{
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    /**
     *
     */
    private JFrame fenetre = new JFrame("Gestion Vehicule");

    /**
     *
     */
    private JPanel top, topClient, topVehicule,
                   center,
                   main;

    private JPanel listeVehicule = new JPanel();

    private JLabel labelClient, labelTypeVehicule;

    /**
     *
     */
    private JComboBox client;

    /**
     *
     */
    private JComboBox vehicule;
    
    private JButton parker_vehicule;
    private JButton unpark_vehicule;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueVehicule, permettant de ...
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
	/*						Methodes							   */
    /***************************************************************/
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
    }

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
    }

    private JPanel Top() {

        // Top
        top = new JPanel();
        top.setLayout(new BorderLayout());

        labelClient = new JLabel("Client");
        client = new JComboBox();
        client.setPreferredSize(new Dimension(300, 20));

        afficherClients();
        fenetre.repaint();
        
        client.setSelectedIndex(0);
        
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

    private JPanel Center() {
        center = new JPanel();
        center.setLayout(new BorderLayout());

        parker_vehicule = new JButton();
        parker_vehicule.setText("Park le vehicule");
        parker_vehicule.setPreferredSize(new Dimension(150, 40));

        unpark_vehicule = new JButton();
        unpark_vehicule.setText("Unpark le vehicule");
        unpark_vehicule.setPreferredSize(new Dimension(150,40));

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
    }

    /**
     * Methode AfficheListeVehicule() permet de ...
     *
     * @return
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
     * Methode afficherClients() permet de ...
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
        listeVehicule = AfficheListeVehicule();

        listeVehicule.revalidate();
        listeVehicule.repaint();
    } // mettreAJour()

    @Override
    public void setVisible(boolean visible) {
        fenetre.setVisible(visible);
    }

} // VueVehicule class
