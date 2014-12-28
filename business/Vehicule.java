package parking.business;

public class Vehicule {
	protected String immatriculation, marque, modele, proprietaire, type;
	
	public Vehicule(String immatriculation, String marque, String modele, String proprietaire, String type) {
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.proprietaire = proprietaire;
		this.type = type;
	} //Constructeur

	public String getType() { return type; } // getType()

	public String getImmatriculation() {
		return immatriculation;
	} // getImmatriculation()

	@Override
	public String toString() {
		return "Vehicule [immatriculation=" + immatriculation + ", marque="
				+ marque + ", modele=" + modele + ", proprietaire="
				+ proprietaire + "]";
	}// toString()
	
	
}
