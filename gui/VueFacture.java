package parking.gui;

import parking.business.facture.Facture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrateur on 14/01/2015.
 */
public class VueFacture {
    private JTextField textField1;
    private JPanel panel1;

    public VueFacture(final Facture facture) {
        final JFrame fenetre = new JFrame("Facture");
        fenetre.setResizable(false);
        fenetre.setPreferredSize(new Dimension(600,800));
        fenetre.setDefaultCloseOperation(fenetre.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        
        panel .setLayout(new BorderLayout());
        
        JButton save = new JButton("Sauvegarder");
        
        panel.add(save, BorderLayout.SOUTH);
        panel.add(textField1, BorderLayout.CENTER);
        
        textField1.setPreferredSize(new Dimension(450,700));
        textField1.setText(facture.toString());

        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facture.sauvegarder();
                fenetre.dispose();
            }
        });
        
        fenetre.setContentPane(panel);
        fenetre.pack();
        fenetre.setVisible(true);
    }
}
