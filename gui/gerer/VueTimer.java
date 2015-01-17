package parking.gui.gerer;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import parking.business.Timer;
import parking.gui.Vue;

/**
 * Class VueTimer, qui herite de la classe Vue, qui cree une vue permettant
 * d'afficher le temps selon le fonctionnement de la class Timer.
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class VueTimer extends Vue {
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    /**
     *
     */
    private JFrame fenetre;

    /**
     *
     */
    private JTextField textField1;

    /**
     *
     */
    private Timer timer;

    /**
     *
     */
    private JButton incremente;

    /**
     *
     */
    private JPanel panel;
    /**
     *
     */
    private JTextField textField2;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueTimer, permettant de
     *
     * @param timer
     *          Le temps passe sur le parking.
     */
    public VueTimer(final Timer timer) {
        this.timer = timer;

        fenetre = new JFrame("Timer");
        fenetre.setResizable(false);
        fenetre.setPreferredSize(new Dimension(300,130));
        fenetre.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        incremente = new JButton("Ajouter 1 Heure");
        
        incremente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.setHeures(timer.getHeures() + 1);
            }
        });
        
        panel.add(textField1, BorderLayout.NORTH);
        panel.add(textField2, BorderLayout.CENTER);
        panel.add(incremente, BorderLayout.SOUTH);
        
        fenetre.setContentPane(panel);
        fenetre.pack();
        fenetre.setVisible(true);
    } // Constructeur

    /***************************************************************/
	/*						Setter								   */
    /***************************************************************/
    /**
     * Methode setVisible() permet de
     *
     * @param visible
     */
    @Override
    public void setVisible(boolean visible) {
        fenetre.setVisible(visible);
    } // setVisible()

    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    /**
     * Methode mettreAJour() permet de
     */
    @Override
    public void mettreAJour() {
        textField1.setText("Jour : " + this.timer.getDay() + " jour(s)");
        textField2.setText("Heure : " + this.timer.getHeures() + " : " + this.timer.getMinutes() + " : "  + this.timer.getSecondes() +" sec(s)");
    } // mettreAJour()

} // VueTimer class
