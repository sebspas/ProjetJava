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
 * @see Vue
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class VueTimer extends Vue {
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    /**
     * La fenetre du timer.
     */
    private JFrame fenetre;

    /**
     * Le panneau principal de la fenetre.
     */
    private JPanel panel;

    /**
     * Les differents champs "textField1" et "textField2".
     */
    private JTextField textField1, textField2;

    /**
     * Le bouton permettant d'ajouter une heure au timer.
     */
    private JButton incremente;

    /**
     * Le timer.
     */
    private Timer timer;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueTimer, permettant de creer une vue affichant
     * un timer afin de simuler le nombre d'heure passe d'un vehicule sur
     * le parking et ainsi connaitre le tarif approprie au temps passe.
     *
     * @param timer
     *          L'heure actuelle du parking.
     */
    public VueTimer(final Timer timer) {
        this.timer = timer;

        fenetre = new JFrame("Timer");
        fenetre.setResizable(false);
        fenetre.setLocation(15,100);
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
     * Methode mettreAJour() permet de mettre a jour la vue.
     */
    @Override
    public void mettreAJour() {
        textField1.setText("Jour : " + this.timer.getDay() + " jour(s)");
        textField2.setText("Heure : " + this.timer.getHeures() + " : " + this.timer.getMinutes() + " : "  + this.timer.getSecondes() +" sec(s)");
    } // mettreAJour()

} // VueTimer class
