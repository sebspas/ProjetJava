package parking.business;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.exception.PlaceLibreException;
import parking.exception.PlaceOccupeeException;
import parking.exception.PlaceReserverException;

/**
 * Class transporteur est une implementation de l'interface Place,
 * elle definit donc une place du type transporteur.
 *
 * @see Place
 * @author Coadalen, Chergui, Corral, Corfa
 */
public class Transporteur implements Place {
	/***************************************************************/
	/*						Debut Donnees Membres 				   */
	/***************************************************************/
	/**
	 * Numero de la place.
	 */
	private int numeroPlace;

	/**
	 * Vehicule garer sur la place.
	 */
	private Vehicule vehicule;

	/**
	 * type de place (ex: transporteur, particulir)
	 */
	private String type;

	/**
	 * Booleen indiquant si la place est ou non reserver.
	 */
	private boolean Reserver;

	/***************************************************************/
	/*						Constructeur						   */
	/***************************************************************/
	/**
	 * Constructeur de la class transporteur, cree une place du type transporteur
	 * et qui n'est pas reserver pas defaut.
	 *
	 */
	public Transporteur() {
			this.type = "Transporteur";
			this.Reserver = false;
	}// Constructeur()


	/***************************************************************/
	/*						Getter								   */
	/***************************************************************/
	/**
	 * Methode renvoyant le Vehicule qui se situe sur la place.
	 *
	 * @return Le vehicule situer sur cette place.
	 */
	public Vehicule getVehicule() {
		return vehicule;
	}// getVehicule()

	/**
	 * Methode renvoyant le numero de la place.
	 *
	 * @return Le vehicule situer sur cette place.
	 */
	public int getNumero() {
		return numeroPlace;
	}//getNumero()

	/**
	 * Methode renvoyant le type de place.
	 *
	 * @return le type de la place.
	 */
	public String getType() {
		return type;
	}//getType()

	/**
	 * Methode renvoyant une chaine de caracteres explicite indiquant si la place est ou non reserver.
	 *
	 * @return Chaine de caractere indiquant la reservation ou non.
	 */
	public String getReserver() {
		return Reserver ?  "est réservée." : "n'est pas réservée.";
	} // getReserver()

	/**
	 * Methode renvoyant le booleen indiquant l'etat de la reservation.
	 *
	 * @return True pour reserver, false pour non reserver.
	 */
	public boolean getReservation() {
		return Reserver;
	} // getReservation()


	/***************************************************************/
	/*						Setter								   */
	/***************************************************************/
	/**
	 * Methode permettant de definir le vehicule present sur cette place.
	 *
	 * @see Parking#park
	 *
	 * @param vehicule
	 * 		Le vehicule a garer sur la place.
	 */
	public void setVehicule(Vehicule vehicule) throws PlaceOccupeeException, PlaceReserverException{
		if(this.vehicule != null)
			throw new PlaceOccupeeException();
		else if (this.Reserver)
			throw new PlaceReserverException();
		else
			this.vehicule = vehicule;
	}// setVehicule()


	/**
	 * Methode permettant de definir le numero de la place lors de son insertion dans le parking.
	 *
	 * @see Parking#ajouterPlace
	 *
	 * @param numero
	 * 		Le numero a attribuer a la place de parking.
	 */
	public void setNumero(int numero) {
		numeroPlace = numero;
	} // setNumero()

	/**
	 * Methode permettant de reserver ou non la place.
	 *
	 * @param Reserver
	 * 		Boolean True pour reserver, false pour dereserver
	 */
	public void setReservation(boolean Reserver) {
		this.Reserver = Reserver;
	} // setRservation()

	/***************************************************************/
	/*						Methodes							   */
	/***************************************************************/
	/**
	 * Methode toString() affichant toutes les informations de la place.
	 *
	 * @return Une chaine de caracteres contenant les informations.
	 */
	@Override
	public String toString() {
		return "Transporteur [numeroPlace=" + numeroPlace + ", vehicule="
				+ vehicule + getReserver() + "]";
	}// toString()

	public Vehicule retirerVehicule() throws PlaceLibreException{
		if (this.vehicule == null) {
			throw new PlaceLibreException();
		}
		Vehicule temp = this.vehicule;
		this.vehicule = null;
		return temp;
	} // retirerVehicule()
}
