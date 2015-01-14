package parking.gui;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;
import parking.business.Parking;
import parking.business.Place;

import javax.swing.*;
import java.awt.*;
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
    JFrame fenetre = new JFrame("Gestion Vehicule");

    /**
     *
     */
    JPanel listeVehicule = new JPanel();

    /**
     *
     */
    private JComboBox client;

    /**
     *
     */
    private JComboBox vehicule;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueVehicule, permettant de ...
     */
    public VueVehicule() {
        Parking.addVue(this);
        fenetre.setLocation(0, 0);
        fenetre.setPreferredSize(new Dimension(520,450));
        fenetre.setDefaultCloseOperation(fenetre.DISPOSE_ON_CLOSE);
        BorderLayout borderLayout = new BorderLayout();
        fenetre.setLayout(borderLayout);
        JPanel main = new JPanel();

       /* JPanel bouton_bas = new JPanel();
        JButton ajouter = new JButton();
        ajouter.setText("Ajouter Vehicule");
        ajouter.setPreferredSize(new Dimension(260,40));

        JButton supprimer = new JButton();
        supprimer.setText("Supprimer Vehicule");
        supprimer.setPreferredSize(new Dimension(260,40));

        bouton_bas.add(ajouter);
        bouton_bas.add(supprimer);

        main.add(listeVehicule, BorderLayout.NORTH);
        main.add(bouton_bas, BorderLayout.SOUTH);*/
        main.add(Top(), BorderLayout.NORTH);

        fenetre.setContentPane(main);
        fenetre.pack();

        fenetre.setVisible(true);
    } // Constructeur

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

        JLabel labelTypeVehicule = new JLabel("Véhicule");
        vehicule = new JComboBox();
        vehicule.setPreferredSize(new Dimension(300, 20));
        vehicule.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object item = e.getItem();
                // TODO
            }
        });

        JPanel topVehicule = new JPanel();
        topVehicule.setLayout(new BorderLayout());
        topVehicule.add(labelTypeVehicule, BorderLayout.NORTH);
        topVehicule.add(vehicule, BorderLayout.CENTER);

        top.add(topClient, BorderLayout.NORTH);
        top.add(topVehicule, BorderLayout.CENTER);

        return top;
    } // Top()

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
        for (Place p : Parking.getListePlaces()) {
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
        for (Client c : Parking.getListeClient()) {
            identite = c.getNom() + " " + c.getPrenom();
            client.addItem(identite);

        }
    } // afficherClients()

    /**
     * Methode mettreAJour() permet de ...
     */
    @Override
    public void mettreAJour() {
        listeVehicule = AfficheListeVehicule();

        listeVehicule.revalidate();
        listeVehicule.repaint();
    } // mettreAJour()

} // VueVehicule class
