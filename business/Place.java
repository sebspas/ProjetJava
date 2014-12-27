package parking.business;

public interface Place {
	
	@Override
	public String toString();
	
	public Vehicule getVehicule();

	public int getNumero();
	
	public void park(Vehicule v);

	public Vehicule unpark();
}
