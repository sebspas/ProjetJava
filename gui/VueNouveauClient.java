package parking.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Class VueNouveauClient, qui herite de la classe Vue,
 * qui cree une vue permettant de creer un nouveau client.
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class VueNouveauClient extends Vue {
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    JFrame fenetre = new JFrame("Nouveau Client");


    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueAjouterVehicule(), permettant de
     */
    public VueNouveauClient() {
        fenetre.setLocation(0, 0);
        fenetre.setPreferredSize(new Dimension(320,280));
        fenetre.setDefaultCloseOperation(fenetre.DISPOSE_ON_CLOSE);
        BorderLayout borderLayout = new BorderLayout();
        fenetre.setLayout(borderLayout);

        JPanel main = new JPanel();

        fenetre.setContentPane(main);
        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setVisible(true);
    } // VueNouveauClient()


    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    /**
     * Methode mettreAJour() permet de mettre a jour la vue.
     */
    @Override
    public void mettreAJour() {

    } // mettreAJour()
} // VueNouveauClient
