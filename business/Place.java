package parking.business;

public interface Place {
	
	@Override
	public String toString();
	
	public Vehicule getVehicule();

	public int getNumero();

	public String getType();
	
	public void park(Vehicule v);

	public Vehicule unpark();

	public void setReservation(boolean Reserver);

	public String getReserver(); // rnvoie une string explicite surla réservation de la place

	public boolean getReservation(); // renoie un boolean indiquant si la place est ou non réservée
}
