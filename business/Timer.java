package parking.business;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.gui.gerer.VueTimer;

import java.io.Serializable;

/**
 * Class Timer qui cree un timer permettant de simuler le temps passe sur
 * le parking et permet de determiner le tarif de la facture des vehicules.
 *
 * @see Thread, Serializable
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class Timer extends Thread implements Serializable {
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    /**
     * La vue du timer.
     */
    private VueTimer vue;

    /**
     * Le nombre de jour du timer.
     */
    private int day;

    /**
     * Le nombre d'heure du timer.
     */
    private int heures;

    /**
     * Le nombre de minute du timer.
     */
    private int minutes;

    /**
     * Le nombre de seconde du timer.
     */
    private int secondes;

    /**
     * La vitesse du temps donne au timer.
     */
    private int vitesse;

    /**
     * Le timer.
     */
    private static Timer timer;

    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe Timer, permettant d'initialiser un timer au
     * jour, heure, minute, seconde et vitesse definient dans les parametres.
     *
     * @param day
     *          Le nombre de jour(s).
     * @param heures
     *          Le nombre d'heure(s).
     * @param minutes
     *          Le nombre de minute(s).
     * @param secondes
     *          Le nombre de seconde(s).
     * @param vitesse
     *          La vitesse a laquelle le temps passe.
     */
    private Timer(int day, int heures, int minutes, int secondes,int vitesse) {
        // Initialise le timer
        this.day = day;
        this.heures = heures;
        this.minutes = minutes;
        this.secondes = secondes;
        this.vitesse = vitesse;
    } // Constructeur

    /***************************************************************/
	/*						Getter								   */
    /***************************************************************/
    /**
     * Methode getInstance() renvoie l'unique instance du timer si elle n'est pas deja definit.
     *
     * @return Timer
     *          Le timer unique de l'application.
     */
    public static Timer getInstance() {
        if (timer != null) {
            return timer;
        }
        timer = new Timer(0,10,10,0,4);
        return timer;
    } // getInstance()

    /**
     * Methode getVue() renvoie la vue du timer.
     *
     * @return La vue du timer.
     */
    public VueTimer getVue() {
        return vue;
    } // getVue()

    /**
     * Methode getTimer() renvoie une string contenant les informations du Timer.
     *
     * @return String
     *      La chaine de caracteres contenant les infos du Timer.
     */
    public String getTimer() {
        return "Nous sommes le jour numero " + day + " ,il est : " +heures + "h et " + minutes + "min et " + secondes + " sec";
    } // getTimer()

    /**
     * Methode getDay() renvoie le jour.
     *
     * @return int
     *      Le jour actuel du timer.
     */
    public int getDay() {
        return day;
    } //getDay()

    /**
     * Methode getHeures() renvoie l'heure.
     *
     * @return int
     *      L'heure actuelle du timer.
     */
    public int getHeures() {
        return heures;
    } // getHeure()

    /**
     * Methode getMinutes() renvoie les minutes.
     *
     * @return int
     *      Les minutes actuelles du timer.
     */
    public int getMinutes() {
        return minutes;
    } // getMinutes()

    /**
     * Methode getSecondes() renvoie les secondes.
     *
     * @return int
     *      Les secondes actuelles du timer.
     */
    public int getSecondes() {
        return secondes;
    } // getSecondes()

    /***************************************************************/
	/*						Setter								   */
    /***************************************************************/
    /**
     * Methode setVue() permet de definir la vue associee au timer.
     *
     * @param vue
     *          La vue du timer.
     */
    public void setVue(VueTimer vue) {
        this.vue = vue;
    } // setVue()

    /**
     * Methode setHeures() permet de definir l'heure du timer.
     *
     * @param heures
     *          Le nombre d'heure(s) du timer.
     */
    public void setHeures(int heures) {
        this.heures = heures;
        
        // Met à jour la vue du timer, et applique les modifcations si besoin.
        this.mettreAJour();
    } // setHeures()

    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    /**
     * Methode run() permet de lancer le timer en parrallele de l'application.
     */
    @Override
    public void run() {
        // On boucle de maniere constante
        while (true) {
            try {
                // On attend une seconde
                this.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // et on met à jour le timer et donc la vue.
            this.mettreAJour();
        }
    } // run()

    /**
     * Methode mettreAJour() permet de mettre a jour l'affichage du timer.
     */
    private void mettreAJour() {
        secondes = secondes + vitesse * 1;
        
        if (secondes == 60) {
            secondes = 0;
            minutes++;
        }
        if (minutes == 60) {
            minutes = 0;
            heures++;
        }
        if (heures == 24) {
            heures = 0;
            day++;
        }
        if (day == 30) {
            day = 0;
        }

        // Met à jour la vue timer.
        vue.mettreAJour();
    } //  mettreAJour()

} // Timer class
