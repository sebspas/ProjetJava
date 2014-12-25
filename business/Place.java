package parking.business;

public interface Place {
	
	@Override
	public String toString();
	
	public Vehicule getVehicule();
	
	public void park(Vehicule v);
}
