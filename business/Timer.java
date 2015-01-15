package parking.business;

import parking.gui.Vue;
import parking.gui.VueTimer;

/**
 * Created by Administrateur on 14/01/2015.
 */
public class Timer extends Thread {
    private int day;
    private int heures;
    private int minutes;
    private int secondes;
    private int vitesse;
    
    private Vue vue;

    public Timer(int day, int heures, int minutes, int secondes,int vitesse) {
        this.day = day;
        this.heures = heures;
        this.minutes = minutes;
        this.secondes = secondes;
        this.vitesse = vitesse;
    }

    public int getDay() {
        return day;
    }

    public int getHeures() {
        return heures;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSecondes() {
        return secondes;
    }

    public void setVue(Vue vue) {
        this.vue = vue;
    }

    public void setHeures(int heures) {
        this.heures = heures;
        vue.mettreAJour();
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Bug du thread...");
                e.printStackTrace();
            }
            this.mettreAJour();
        }
    }

    private void mettreAJour() {
        secondes = secondes + vitesse * 1;
        
        if (secondes == 60) {
            secondes = 0;
            minutes++;
            if (minutes == 60) {
                minutes = 0;
                heures++;
                if (heures == 24) {
                    heures = 0;
                    day++;
                    if (day == 30) {
                        day = 0;
                    }
                }
            }
        }
        
        //System.out.println(heures + ":" + minutes + ":" + secondes);
        vue.mettreAJour();
    }

    public String getTimer() {
        return "Nous sommes le jour numero " + day + " ,il est : " +heures + "h et " + minutes + "min et " + secondes + " sec";
    }
}
