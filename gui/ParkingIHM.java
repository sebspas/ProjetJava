package parking.gui;

import parking.business.Parking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParkingIHM extends JFrame {

    public ParkingIHM() {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Parking");
        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(500, 700));
        setLocation(100, 100);

        setJMenuBar(barreMenus());

        pack();
        setVisible(true);
    } //  ParkingIHM()



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
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment quitter ?"))
            System.exit(0);
    } // actionMenuFichierQuitter()


    public static void main(String[] args) {
        new ParkingIHM();
    } // main()


} // ParkingIHM
