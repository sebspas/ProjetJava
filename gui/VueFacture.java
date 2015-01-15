package parking.gui;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.facture.Facture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class VueFacture qui cree une vue permettant de voir le detail
 * de la facture ainsi que la possibilite de la sauvegarder.
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class VueFacture {
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    /**
     *
     */
    private JPanel panel1, panel;

    /**
     *
     */
    private JTextArea textArea1;

    /**
     *
     */
    private JButton save;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueFacture, permettant de
     *
     * @param facture
     *          La facture a creer.
     */
    public VueFacture(final Facture facture) {
        final JFrame fenetre = new JFrame("Facture n°" + facture.getNumeroFacture());
        fenetre.setResizable(false);
        fenetre.setPreferredSize(new Dimension(450,320));
        fenetre.setDefaultCloseOperation(fenetre.DISPOSE_ON_CLOSE);
        
        panel = new JPanel();
        
        panel .setLayout(new BorderLayout());
        
        save = new JButton("Sauvegarder");
        
        panel.add(save, BorderLayout.SOUTH);
        panel.add(textArea1, BorderLayout.CENTER);

        textArea1.setPreferredSize(new Dimension(400,300));
        textArea1.setText(facture.toString());

        
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
    } // Constructeur

} // VueFacture class
