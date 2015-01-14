package parking.gui;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Parking;
import parking.business.Place;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class VueParking, herite de la classe Vue, permettant de ...
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class VueParking extends Vue{
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    /**
     *
     */
    private JFrame fenetre = new JFrame("Affichage Parking");

    /**
     *
     */
    private JPanel parking = new JPanel();

    /**
     *
     */
    private JProgressBar progressBar = new JProgressBar();

    /**
     *
     */
    private JPanel panel1;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueParking, permettant de ...
     */
    public VueParking() {
        Parking.addVue(this);
        JPanel main = new JPanel();

        fenetre.setLocation(300, 100);
        fenetre.setPreferredSize(new Dimension(800,600));
        fenetre.setDefaultCloseOperation(fenetre.EXIT_ON_CLOSE);

        fenetre.setLayout(new BorderLayout());
        fenetre.setJMenuBar(barreMenus());

        fenetre.setContentPane(main);

        parking.setPreferredSize(new Dimension(650,600));
        progressBar.setPreferredSize(new Dimension(500,15));

        main.add(new JLabel("Capacité :"), BorderLayout.NORTH);
        main.add(progressBar, BorderLayout.NORTH);

        main.add(parking, BorderLayout.CENTER);

        main.add(legende(), BorderLayout.SOUTH);


        fenetre.pack();

        fenetre.setVisible(true);

    } // Constructeur

    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    /**
     * Methode AffichageParking() permet de ...
     *
     * @return
     */
    private JPanel AffichageParking() {
        parking.removeAll();
        for (Place p : Parking.getListePlaces()) {
            JButton button = new JButton();
            button.setRolloverEnabled(false);

            button.setPreferredSize(new Dimension(200,50));

            if (p.getVehicule() != null) {
                button.setText(p.getVehicule().getType() + " : " + String.valueOf(p.getVehicule().getImmatriculation()));
                button.setBackground(Color.red);
            }
            else if (p.getReservation()) {
                button.setText(p.getType());
                button.setBackground(Color.orange);
            }
            else {
                button.setText(p.getType());
                button.setBackground(Color.green);
            }

            parking.add(button);
        }

        return parking;
    } // AffichageParking()

    /**
     * Methode legende() permet de ...
     *
     * @return Le panneau "legende".
     */
    private JPanel legende() {
        JPanel legende = new JPanel();

        JLabel titre = new JLabel("Legende ");

        legende.add(titre, BorderLayout.NORTH);

        JButton bouton1 = new JButton("Libre");
        JButton bouton2 = new JButton("Réservée");
        JButton bouton3 = new JButton("Occupée");

        bouton1.setBackground(Color.green);
        bouton2.setBackground(Color.orange);
        bouton3.setBackground(Color.red);

        legende.add(bouton1, BorderLayout.CENTER);
        legende.add(bouton2, BorderLayout.CENTER);
        legende.add(bouton3, BorderLayout.CENTER);

        return legende;
    } // legende()

    /**
     * Methode barreMenus() permet de ...
     *
     * @return
     */
    private JMenuBar barreMenus() {
        JMenuBar barre = new JMenuBar();
        barre.add(creerMenuFichier());
        barre.add(creerMenuVehicule());
        return barre;
    } // barreMenus()

    /**
     * Methode creerMenuFichier() permet de ...
     *
     * @return
     */
    private JMenu creerMenuFichier() {
        JMenu menuFichier = new JMenu("Fichier");
        menuFichier.add(creerMenuFichierQuitter());
        return menuFichier;
    } // creerMenuFichier()

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
     * Methode creerMenuVehicule() permet de ...
     *
     * @return
     */
    private JMenu creerMenuVehicule() {
        JMenu menuVehicule = new JMenu("Vehicule");
        menuVehicule.add(creerMenuVehiculeListe());
        menuVehicule.add(creerMenuVehiculeAjouterVehicule());
        return menuVehicule;
    } // creerMenuVehicule()

    /**
     * Methode creerMenuVehiculeListe() permet de ...
     *
     * @return
     */
    private JMenuItem creerMenuVehiculeListe() {
        JMenuItem menu = new JMenuItem("Liste vehicules");
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionMenuVehiculeListe();
            }

        });
        return menu;
    } // creerMenuVehiculeListe()

    /**
     * Methode actionMenuVehiculeListe() permet de ...
     */
    private void actionMenuVehiculeListe() {
        parking.gui.Vue listeVehicule = new parking.gui.VueVehicule();
        listeVehicule.mettreAJour();
    } // actionMenuVehiculeListe()

    /**
     * Methode creerMenuVehiculeAjouterVehicule() permet de ...
     *
     * @return
     */
    private JMenuItem creerMenuVehiculeAjouterVehicule() {
        JMenuItem menu = new JMenuItem("Ajouter vehicule");
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionMenuVehiculeAjouterVoiture();
            }

        });
        return menu;
    } // creerMenuVehiculeAjouterVehicule()

    /**
     * Methode actionMenuVehiculeAjouterVoiture() permet de ...
     */
    private void actionMenuVehiculeAjouterVoiture() {
        parking.gui.Vue AjouterVehicule = new parking.gui.VueAjouterVehicule();
    } // actionMenuVehiculeAjouterVoiture()

    /**
     * Methode mettreAJour() permet de ...
     */
    @Override
    public void mettreAJour() {
        float nbPlacesMax = Parking.getNbPlacesMax();
        float nbrVehicule = Parking.getNbVehicule();
        int pourcentage = (int)((nbrVehicule/nbPlacesMax)*100);
        progressBar.setValue(pourcentage);
        parking = AffichageParking();

        parking.revalidate();
        parking.repaint();
    } // mettreAJour()

} // VueParking class
