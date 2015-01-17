package parking.business;

import parking.business.Parking;

import java.io.*;

/**
 * Created by Naoki on 17/01/2015.
 */
public class Sauvegarde {
    public void sauvegarder(String fichier){
        File flux = new File(fichier);
        ObjectOutputStream oos = null;

        try {
            oos =  new ObjectOutputStream(new FileOutputStream(fichier)) ;
            oos.writeObject(Parking.getInstance());
        }
        catch(EOFException e){
            e.printStackTrace();
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
