package parking.business;

import parking.exception.NombrePlacesMaxException;
import parking.exception.PlaceOccupeeException;

public class Transporteur implements Place {
	
	private int numeroPlace;
	private Vehicule vehicule;
	
	public Transporteur() {
		try{
			if(Parking.getNbPlacesMax() == Parking.getNumeroPlace())
				throw new NombrePlacesMaxException();
			this.numeroPlace = Parking.getNumeroPlace();
			Parking.setNumeroPlace(Parking.getNumeroPlace()+1);			
		}
		catch(NombrePlacesMaxException e){
			System.out.println("Le parking a atteint le nombre maximal de places");
		}
	}// Constructeur()

	@Override
	public String toString() {
		return "Transporteur [numeroPlace=" + numeroPlace + ", vehicule="
				+ vehicule + "]";
	}// toString()
	
	public Vehicule getVehicule() {
		return vehicule;
	}// getVehicule()
	
	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}// setVehicule()

	public void park(Vehicule v) {
		try{
			if(this.vehicule != null)
				throw new PlaceOccupeeException();
			setVehicule(v);
		}
		catch(PlaceOccupeeException e){
			System.out.println("La place " + numeroPlace + " est déjà occupée et/ou n'est pas adaptée à ce véhicule");
		}
	}// park()

	
}
