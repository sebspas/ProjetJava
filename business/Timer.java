package parking.business;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.gui.Vue;
import parking.gui.VueTimer;

/**
 * Class Timer qui cree une vue permettant de simuler le temps passe sur le
 * parking pour chaque vehicule et determinant donc le tarif de sa facture.
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class Timer extends Thread {
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    /**
     * La vue.
     */
    private Vue vue;

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
     * Constructeur de la classe Timer, permettant de
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
     * Methode getInstance() renvoie
     *
     * @return
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
    public Vue getVue() {
        return vue;
    } // getVue()

    /**
     * Methode getTimer() renvoie
     *
     * @return
     */
    public String getTimer() {
        return "Nous sommes le jour numero " + day + " ,il est : " +heures + "h et " + minutes + "min et " + secondes + " sec";
    } // getTimer()

    /**
     * Methode getDay() renvoie
     *
     * @return
     */
    public int getDay() {
        return day;
    } //getDay()

    /**
     * Methode getHeures() renvoie
     *
     * @return
     */
    public int getHeures() {
        return heures;
    } // getHeure()

    /**
     * Methode getMinutes() renvoie
     *
     * @return
     */
    public int getMinutes() {
        return minutes;
    } // getMinutes()

    /**
     * Methode getSecondes() renvoie
     *
     * @return
     */
    public int getSecondes() {
        return secondes;
    } // getSecondes()

    /***************************************************************/
	/*						Setter								   */
    /***************************************************************/
    /**
     * Methode setVue() permet de
     *
     * @param vue
     *          La vue du timer.
     */
    public void setVue(Vue vue) {
        this.vue = vue;
    } // setVue()

    /**
     * Methode setHeures() permet de
     *
     * @param heures
     *          Le nombre d'heure(s) du timer.
     */
    public void setHeures(int heures) {
        this.heures = heures;
        this.mettreAJour();
    } // setHeures()

    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/
    /**
     * Methode run() permet de
     */
    @Override
    public void run() {
        while (true) {
            try {
                this.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.mettreAJour();
        }
    } // run()

    /**
     * Methode mettreAJour() permet de
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

        vue.mettreAJour();
    } //  mettreAJour()

} // Timer class
