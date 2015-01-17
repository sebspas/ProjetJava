package parking.gui.gerer;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.*;
import parking.business.Timer;
import parking.gui.ButtonPlace;
import parking.gui.Vue;
import parking.gui.ajouter.VueAjouterVehicule;
import parking.gui.ajouter.VueNouveauClient;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Class VueParking, herite de la classe Vue, permettant de creer une
 * nouvelle vue qui correspondra a la fenetre principale de l'application.
 *
 * @see Vue, Serializable
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class VueParking extends Vue implements Serializable{
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    /**
     * La fenetre principale de l'application.
     */
    private JFrame fenetre = new JFrame("Affichage Parking");

    /**
     * Le panneau correspondant au parking.
     */
    private JPanel affichageParking = new JPanel();

    /**
     * Les panneaux main, legende et panel1.
     */
    private JPanel main, legende, panel1;

    /**
     * L'etiquette contenant le titre de cette vue.
     */
    private JLabel titre;

    /**
     * Les "boutons" 1 a 6 (non cliquables).
     */
    private JButton bouton1, bouton2, bouton3, bouton4, bouton5, bouton6;

    /**
     * La barre de progression indiquant la capacite du parking.
     */
    private JProgressBar progressBar = new JProgressBar();

    /**
     * L'image/l'icone de la voiture.
     */
    private ImageIcon icon_voiture;

    /**
     * L'image/l'icone du camion.
     */
    private ImageIcon icon_camion;

    /**
     * L'image/l'icone d'une place reservee.
     */
    private ImageIcon icon_reservee;

    /**
     * L'image/l'icone d'une place disponible.
     */
    private ImageIcon icon_disponible;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueParking, permettant de creer une vue representant
     * le parking avec toutes les fonctionnalites possibles a cette vue.
     */
    public VueParking() {
        try {
            icon_voiture = new ImageIcon(ImageIO.read(getClass().getResource("../ressources/voiture.png")));
            icon_camion = new ImageIcon(ImageIO.read(getClass().getResource("../ressources/camion.png")));
            icon_reservee = new ImageIcon(ImageIO.read(getClass().getResource("../ressources/reservee.png")));
            icon_disponible = new ImageIcon(ImageIO.read(getClass().getResource("../ressources/feu-vert.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        main = new JPanel();
        main.setBackground(new Color(127, 140, 141));
        fenetre.setResizable(false);
        fenetre.setLocation(400, 100);
        fenetre.setPreferredSize(new Dimension(850, 600));
        fenetre.setDefaultCloseOperation(fenetre.EXIT_ON_CLOSE);

        fenetre.setLayout(new BorderLayout());
        fenetre.setJMenuBar(barreMenus());

        fenetre.setContentPane(main);
        affichageParking.setPreferredSize(new Dimension(780, 870));

        JScrollPane scrollPane = new JScrollPane(affichageParking);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 300, 50);
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
     * Methode AffichageParking() permet d'afficher le parking cree prealablement.
     *
     * @return Le parking a afficher
     */
    private JPanel AffichageParking() {
        affichageParking.removeAll();
        for (Place p : Parking.getInstance().getListePlaces()) {
            final ButtonPlace button = new ButtonPlace(p);

            button.setRolloverEnabled(false);
                
            button.setPreferredSize(new Dimension(190,70));
            
            if (p.getType().equals("Transporteur")) {
                button.setBackground(new Color(52, 152, 219));
            }
            if (p.getType().equals("Particulier")) {
                button.setBackground(new Color(22, 160, 133));
            }
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

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (button.getPlace().getVehicule() == null) {
                        if (button.getPlace().getReservation()) {
                            button.getPlace().setReservation(false);
                        }    
                        else {
                            button.getPlace().setReservation(true);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(fenetre,
                                "Place déja occupée ! Ne peut pas être réservée !",
                                "Erreur Place Occupée",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            
            affichageParking.add(button);
        }

        return affichageParking;
    } // AffichageParking()

    /**
     * Methode legende() permet de creer une legende des differentes images utilisees danns le parking.
     *
     * @return Le panneau "legende".
     */
    private JPanel legende() {
        legende = new JPanel();
        legende.setPreferredSize(new Dimension(850,80));
        legende.setBackground(new Color(127, 140, 141));

        bouton1 = new JButton("Libre");
        bouton2 = new JButton("Réservée");
        bouton3 = new JButton("Voiture");
        bouton4 = new JButton("Camion");
        bouton5 = new JButton("Transporteur");
        bouton6 = new JButton("Particulier");

        bouton1.setIcon(icon_disponible);
        bouton2.setIcon(icon_reservee);
        bouton3.setIcon(icon_voiture);
        bouton4.setIcon(icon_camion);
        bouton6.setBackground(new Color(22, 160, 133));
        bouton5.setBackground(new Color(52, 152, 219));
        
        bouton1.setRolloverEnabled(false);
        bouton2.setRolloverEnabled(false);
        bouton3.setRolloverEnabled(false);
        bouton4.setRolloverEnabled(false);
        bouton5.setRolloverEnabled(false);
        bouton6.setRolloverEnabled(false);
        
        legende.add(bouton1, BorderLayout.CENTER);
        legende.add(bouton2, BorderLayout.CENTER);
        legende.add(bouton3, BorderLayout.CENTER);
        legende.add(bouton4, BorderLayout.CENTER);
        legende.add(bouton5, BorderLayout.CENTER);
        legende.add(bouton6, BorderLayout.CENTER);

        return legende;
    } // legende()

    /**
     * Methode barreMenus() permet de creer un menu sous forme de
     * barre horizontale et qui contient plusieurs fonctionnalites.
     *
     * @return La barre de menu.
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
     * Methode creerMenuFichier() permet de creer une section "Fichier" dans la barre de menu.
     *
     * @return Le menu "Fichier".
     */
    private JMenu creerMenuFichier() {
        JMenu menuFichier = new JMenu("Fichier");
        menuFichier.add(creerMenuOuvrir());
        menuFichier.add(creerMenuSauvegarder());
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
                dialogue.setCurrentDirectory(new File( "./saves" ) );
                dialogue.showOpenDialog(null);
                if (dialogue.getSelectedFile() != null) {
                    Gestionnaire gestionnaire = new Gestionnaire();
                    gestionnaire.lire(dialogue.getSelectedFile().toString());
                }
            }
        });
        return menu;
    } // creerMenuOuvrir()

    /**
     * Methode creerMenuSauvegarder() permet de creer un item "Sauvegarder" dans le menu "Fichier".
     *
     * @return L'item "Sauvegarder".
     */
    private JMenuItem creerMenuSauvegarder() {
        JMenuItem menu = new JMenuItem("Sauvegarder");
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gestionnaire save = new Gestionnaire();
                
                save.sauvegarder();
            }
        });
        return menu;
    } // creerMenuSauvegarder()

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
     * Methode creerMenuVehicule() permet de creer une section "Vehicule" dans la barre de menu.
     *
     * @return Le menu "Vehicule".
     */
    private JMenu creerMenuVehicule() {
        JMenu menuVehicule = new JMenu("Vehicule");
        menuVehicule.add(creerMenuVehiculeListe());
        menuVehicule.add(creerMenuVehiculeAjouterVehicule());
        return menuVehicule;
    } // creerMenuVehicule()

    /**
     * Methode creerMenuVehiculeListe() permet de creer un item "Liste vehicules" dans le menu "Vehicule".
     *
     * @return L'item "Liste vehicules".
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
     * Methode actionMenuVehiculeListe() permet d'afficher une liste de vehicule automatiquement mise a jour.
     */
    private void actionMenuVehiculeListe() {
        parking.gui.Vue listeVehicule = new VueVehicule();
        listeVehicule.mettreAJour();
    } // actionMenuVehiculeListe()

    /**
     * Methode creerMenuVehiculeAjouterVehicule() permet de creer un item "Ajouter vehicule" dans le menu "Vehicule".
     *
     * @return L'item "Ajouter vehicule".
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
     * Methode actionMenuVehiculeAjouterVoiture() permet d'ajouter une voiture dans le parking.
     */
    private void actionMenuVehiculeAjouterVoiture() {
        parking.gui.Vue AjouterVehicule = new VueAjouterVehicule();
    } // actionMenuVehiculeAjouterVoiture()

    /**
     * Methode creerMenuClient() permet de creer une section "Client" dans la barre de menu.
     *
     * @return Le menu "Client".
     */
    private JMenu creerMenuClient() {
        JMenu menuClient = new JMenu("Client");
        menuClient.add(creerMenuClientNouveau());
        return menuClient;
    } // creerMenuClient()

    /**
     * Methode creerMenuClientNouveau() permet de creer un item "Nouveau" dans le menu "Client".
     *
     * @return L'item "Nouveau".
     */
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

    /**
     * Methode actionMenuClientNouveau() permet d'ajouter un nouveau client.
     */
    private void actionMenuClientNouveau() {
       parking.gui.Vue NouveauClient = new VueNouveauClient();
    } // actionMenuClientNouveau()

    /**
     * Methode creerMenuTimer() permet de creer une section "Timer" dans la barre de menu.
     *
     * @return Le menu "Timer".
     */
    private JMenu creerMenuTimer() {
        JMenu menuTimer = new JMenu("Timer");
        menuTimer.add(creerMenuTimerAfficher());
        return menuTimer;
    } // creerMenuTimer()

    /**
     * Methode creerMenuTimerAfficher() permet de creer un item "Afficher" dans le menu "Timer".
     *
     * @return L'item "Afficher".
     */
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

    /**
     * Methode actionMenuTimerAfficher() permet de rendre le timer visible.
     */
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
        float nbrVehicule = p.getNbPlaceOccupees();
        int pourcentage = (int)((nbrVehicule/nbPlacesMax)*100);
        progressBar.setValue(pourcentage);
        affichageParking = AffichageParking();

        affichageParking.revalidate();
        affichageParking.repaint();
    } // mettreAJour()

} // VueParking class
