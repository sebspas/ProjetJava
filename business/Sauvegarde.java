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

    public void lire(String fichier){
        File flux = new File(fichier);
        ObjectInputStream ois = null;
        try {
            ois =  new ObjectInputStream(new FileInputStream(fichier)) ;
            Parking parking = (Parking)ois.readObject();
            Parking.getInstance().setNom(parking.getNom());
            Parking.getInstance().setNumeroFacture(parking.getNumeroFacture());
            Parking.getInstance().setListeVueNotifiable(parking.getListeVueNotifiable());
            Parking.getInstance().setListePlaces(parking.getListePlaces());
            Parking.getInstance().setListeFacture(parking.getListeFacture());
            Parking.getInstance().setListeClients(parking.getListeClients());
            Parking.getInstance().setNbPlacesMax(parking.getNbPlacesMax());
            Parking.getInstance().setTarifParticulier(parking.getTarifParticulier());
            Parking.getInstance().setTarifTransporteur(parking.getTarifTransporteur());
            Parking.getInstance().setAppelInterne(parking.isAppelInterne());
            Parking.getInstance().setNbPlaceOccupees(parking.getNbPlaceOccupees());
        }
        catch(EOFException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
