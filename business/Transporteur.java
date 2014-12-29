package parking.business;

import parking.exception.NombrePlacesMaxException;
import parking.exception.PlaceLibreException;
import parking.exception.PlaceOccupeeException;
import parking.exception.PlaceReserverException;

public class Transporteur implements Place {
	
	private int numeroPlace;
	private Vehicule vehicule;
	private String type;
	private boolean Reserver;
	
	public Transporteur() {
			this.type = "Transporteur";
			this.Reserver = false;
	}// Constructeur()

	@Override
	public String toString() {
		return "Transporteur [numeroPlace=" + numeroPlace + ", vehicule="
				+ vehicule + getReserver() + "]";
	}// toString()
	
	public Vehicule getVehicule() {
		return vehicule;
	}// getVehicule()
	
	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}// setVehicule()

	public int getNumero() { return numeroPlace; }//getNumero()

	public void setNumero(int numero) {
		numeroPlace = numero;
	} // setNumero()

	public String getType() { return type; }//getType()

	public void setReservation(boolean Reserver) { this.Reserver = Reserver; } // setRservation()

	public String getReserver() {
		return Reserver ?  "est réservée." : "n'est pas réservée.";
	} // getReserver()

	public boolean getReservation() {
		return Reserver;
	} // getReservation()

	public void park(Vehicule v) {
		try{
			if(this.vehicule != null)
				throw new PlaceOccupeeException();
			if (this.Reserver)
				throw new PlaceReserverException();
			setVehicule(v);
		}
		catch(PlaceOccupeeException e){
			System.out.println("La place " + numeroPlace + " est d�j� occup�e et/ou n'est pas adapt�e � ce v�hicule");
		}
		catch (PlaceReserverException e) {
			System.out.println("La place " + numeroPlace + " est réservée !");
		}
	}// park()

	public Vehicule unpark() {
		try {
			if(this.vehicule == null)
				throw new PlaceLibreException();
			Vehicule vehicule_renvoyé = this.vehicule;
			setVehicule(null);
			return vehicule_renvoyé;
		}
		catch (PlaceLibreException e) {
			System.out.println("La place " + numeroPlace + "est dèja vide.");
			return null;
		}
	}// unpark()
}
