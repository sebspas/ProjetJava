package parking.gui;

import parking.business.Parking;
import parking.business.Place;
import parking.business.Vehicule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrateur on 10/01/2015.
 */
public class VueParking extends Vue{

    private JFrame fenetre = new JFrame("Affichage Parking");
    private JPanel parking = new JPanel();
    private JProgressBar progressBar = new JProgressBar();
    private JPanel panel1;

    @Override
    public void mettreAJour() {
        float nbPlacesMax = Parking.getNbPlacesMax();
        float nbrVehicule = Parking.getListeVehicules().size();
        int pourcentage = (int)((nbrVehicule/nbPlacesMax)*100);
        progressBar.setValue(pourcentage);
        parking = AffichageParking();

        parking.revalidate();
        parking.repaint();
    }

    public VueParking() {
        JPanel main = new JPanel();

        fenetre.setLocation(300, 100);
        fenetre.setPreferredSize(new Dimension(750,850));
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
    }

    private JPanel AffichageParking() {
        parking.removeAll();
        for (Place p : Parking.getListeVehicules()) {
            JButton button = new JButton();

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
    }

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
    }
    private JMenuBar barreMenus() {
        JMenuBar barre = new JMenuBar();
        barre.add(creerMenuFichier());
        return barre;
    } // barreMenus()

    private JMenu creerMenuFichier() {
        JMenu menuFichier = new JMenu("Fichier");
        menuFichier.add(creerMenuFichierQuitter());
        return menuFichier;
    } // creerMenuFichier()

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

    private void actionMenuFichierQuitter() {
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(fenetre, "Voulez-vous vraiment quitter ?"))
            System.exit(0);
    } // actionMenuFichierQuitter()
}
