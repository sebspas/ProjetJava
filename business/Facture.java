package parking.business;

/**
 * Created by Administrateur on 12/01/2015.
 */
public class Facture {
    private int numeroFacture;
    private double tarif;
    private Client client;

    public Facture( Place place) {
        tarif = place.getVehicule().getProprietaire().getCalculerTarif().calculerTarif(place);
        client = place.getVehicule().getProprietaire();
    }

    @Override
    public String toString() {
        return "Facture{" +
                "numeroFacture=" + numeroFacture +
                ", tarif=" + tarif +
                ", client=" + client +
                '}';
    }
}
