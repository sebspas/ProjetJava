package parking.business;

public class Camion extends Vehicule{

	private int tonnage, hauteur;
	
	public Camion(String immatriculation, String marque, String modele,
			String proprietaire, int tonnage, int hauteur) {
		super(immatriculation, marque, modele, proprietaire);
		this.tonnage = tonnage;
		this.hauteur = hauteur;
	}// Constructeur()

	@Override
	public String toString() {
		return "Camion [immatriculation=" + immatriculation + ", marque="
				+ marque + ", modele=" + modele + ", proprietaire=" + proprietaire
				+ ", tonnage=" + tonnage + ", hauteur=" + hauteur + "]";
	}// toString()
	
}
