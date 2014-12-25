package parking.business;

public class Vehicule {
	protected String immatriculation, marque, modele, proprietaire;
	
	public Vehicule(String immatriculation, String marque, String modele, String proprietaire) {
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.proprietaire = proprietaire;
	} //Constructeur

	@Override
	public String toString() {
		return "Vehicule [immatriculation=" + immatriculation + ", marque="
				+ marque + ", modele=" + modele + ", proprietaire="
				+ proprietaire + "]";
	}// toString()
	
	
}
