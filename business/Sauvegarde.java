package parking.business;

import java.io.*;

/**
 * Created by Naoki on 17/01/2015.
 */
public class Sauvegarde {
    public void sauvegarder(){
        new File("saves").mkdir();
        
        File flux = new File("saves/MonParking.ser");
        ObjectOutputStream oos = null;

        try {
            oos =  new ObjectOutputStream(new FileOutputStream("saves/MonParking.ser")) ;
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
