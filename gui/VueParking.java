package parking.gui;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.*;
import parking.business.Timer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
    private JPanel affichageParking = new JPanel();
    private JPanel main, legende;

    private JLabel titre;
    private JButton bouton1, bouton2, bouton3, bouton4;

    /**
     *
     */
    private JProgressBar progressBar = new JProgressBar();

    /**
     *
     */
    private JPanel panel1;

    private ImageIcon icon_voiture;
    private ImageIcon icon_camion;
    private ImageIcon icon_reservee;
    private ImageIcon icon_disponible;

    
    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueParking, permettant de ...
     */
    public VueParking() {
        try {
            icon_voiture = new ImageIcon(ImageIO.read(getClass().getResource("ressources/voiture.png")));
            icon_camion = new ImageIcon(ImageIO.read(getClass().getResource("ressources/camion.png")));
            icon_reservee = new ImageIcon(ImageIO.read(getClass().getResource("ressources/reservee.png")));
            icon_disponible = new ImageIcon(ImageIO.read(getClass().getResource("ressources/feu-vert.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        main = new JPanel();
        main.setBackground(new Color(127, 140, 141));
        fenetre.setResizable(false);
        fenetre.setLocation(300, 100);
        fenetre.setPreferredSize(new Dimension(830, 600));
        fenetre.setDefaultCloseOperation(fenetre.EXIT_ON_CLOSE);

        fenetre.setLayout(new BorderLayout());
        fenetre.setJMenuBar(barreMenus());

        fenetre.setContentPane(main);

        affichageParking.setPreferredSize(new Dimension(780, 470));

        JScrollPane scrollPane = new JScrollPane(affichageParking);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //3scrollPane.setBounds(50, 30, 300, 50);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        
        progressBar.setForeground(new Color(46, 204, 113));
        progressBar.setStringPainted(true);
        progressBar.setBorderPainted(false);
        progressBar.setPreferredSize(new Dimension(500,15));


        main.add(new JLabel("Capacité :"), BorderLayout.NORTH);
        main.add(progressBar, BorderLayout.NORTH);

        main.add(scrollPane, BorderLayout.CENTER);

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
        affichageParking.removeAll();
        for (Place p : Parking.getInstance().getListePlaces()) {
            JButton button = new JButton();
            button.setRolloverEnabled(false);
            button.setBackground(new Color(189, 195, 199));
                
            button.setPreferredSize(new Dimension(190,70));

            if (p.getVehicule() != null) {
                button.setText(String.valueOf(p.getVehicule().getImmatriculation()));
                if (p.getVehicule().getType().equals("Camion")) {
                    button.setIcon(icon_camion);
                }
                else {
                    button.setIcon(icon_voiture);
                }
            }
            else if (p.getReservation()) {
                button.setText(p.getType());
                button.setIcon(icon_reservee);
            }
            else {
                button.setIcon(icon_disponible);
                button.setText(p.getType());
            }

            affichageParking.add(button);
        }

        return affichageParking;
    } // AffichageParking()

    /**
     * Methode legende() permet de ...
     *
     * @return Le panneau "legende".
     */
    private JPanel legende() {
        legende = new JPanel();
        legende.setPreferredSize(new Dimension(800,80));
        legende.setBackground(new Color(127, 140, 141));

        bouton1 = new JButton("Libre");
        bouton2 = new JButton("Réservée");
        bouton3 = new JButton("Voiture");
        bouton4 = new JButton("Camion");


        bouton1.setIcon(icon_disponible);
        bouton2.setIcon(icon_reservee);
        bouton3.setIcon(icon_voiture);
        bouton4.setIcon(icon_camion);


        legende.add(bouton1, BorderLayout.CENTER);
        legende.add(bouton2, BorderLayout.CENTER);
        legende.add(bouton3, BorderLayout.CENTER);
        legende.add(bouton4, BorderLayout.CENTER);

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
        barre.add(creerMenuClient());
        barre.add(creerMenuTimer());
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


    private JMenu creerMenuClient() {
        JMenu menuClient = new JMenu("Client");
        menuClient.add(creerMenuClientNouveau());
        return menuClient;
    } // creerMenuClient()

    private JMenuItem creerMenuClientNouveau() {
        JMenuItem menu = new JMenuItem("Nouveau");
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionMenuClientNouveau();
            }

        });
        return menu;
    } // creerMenuClientNouveau()


    private void actionMenuClientNouveau() {
       parking.gui.Vue NouveauClient = new parking.gui.VueNouveauClient();
    } // actionMenuClientNouveau()


    private JMenu creerMenuTimer() {
        JMenu menuTimer = new JMenu("Timer");
        menuTimer.add(creerMenuTimerAfficher());
        return menuTimer;
    } // creerMenuTimer()

    private JMenuItem creerMenuTimerAfficher() {
        JMenuItem menu = new JMenuItem("Afficher");
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionMenuTimerAfficher();
            }

        });
        return menu;
    } // creerMenuTimerAfficher()


    private void actionMenuTimerAfficher() {
        Timer.getInstance().getVue().setVisible(true);
    } // actionMenuTimerAfficher()


    /**
     * Methode mettreAJour() permet de mettre a jour la vue.
     */
    @Override
    public void mettreAJour() {
        Parking p = Parking.getInstance();
        float nbPlacesMax = p.getNbPlacesMax();
        float nbrVehicule = p.getNbVehicule();
        int pourcentage = (int)((nbrVehicule/nbPlacesMax)*100);
        progressBar.setValue(pourcentage);
        affichageParking = AffichageParking();

        affichageParking.revalidate();
        affichageParking.repaint();
    } // mettreAJour()

    @Override
    public void setVisible(boolean visible) {
        fenetre.setVisible(visible);
    }

} // VueParking class
