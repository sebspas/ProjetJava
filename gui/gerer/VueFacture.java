package parking.gui.gerer;

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
     * Le panneau "panel" accueillant la facture.
     */
    private JPanel panel;

    /**
     * Le champ prevu pour afficher la facture.
     */
    private JTextArea textAreaFacture;

    /**
     * Le bouton permettant de sauvegarder la facture.
     */
    private JButton save;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueFacture, permettant de creer une vue
     * affichant la facture d'un client avec differentes informations
     * telles que le tarif, l'heure d'arrivee, l'heure de depart, etc.
     *
     * @param facture
     *          La facture a creer.
     */
    public VueFacture(final Facture facture) {
        final JFrame fenetre = new JFrame("Facture n°" + facture.getNumeroFacture());
        fenetre.setResizable(false);
        fenetre.setPreferredSize(new Dimension(450,400));
        fenetre.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textAreaFacture = new JTextArea();
        save = new JButton("Sauvegarder");
        
        panel.add(save, BorderLayout.SOUTH);
        panel.add(textAreaFacture, BorderLayout.CENTER);

        textAreaFacture.setPreferredSize(new Dimension(400,300));
        textAreaFacture.setText(facture.toString());

        
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
