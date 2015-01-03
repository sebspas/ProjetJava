package parking.business;

import parking.exception.PlaceLibreException;
import parking.exception.PlaceOccupeeException;
import parking.exception.PlaceReserverException;

/**
 * Interface definissant les methodes requise pour implementer une place de parking.
 *
 * @see Transporteur
 * @see Particulier
 *
 * @author Coadalen, Chergui, Corral, Corfa
 */
public interface Place {
	/***************************************************************/
	/*						Getter								   */
	/***************************************************************/
	/**
	 * Methode devant renvoyer le Vehicule qui se situe sur la place.
	 *
	 * @return Le vehicule situer sur cette place.
	 */
	public Vehicule getVehicule();

	/**
	 * Methode devant renvoyer le numero de la place.
	 *
	 * @return Le numero de la place.
	 */
	public int getNumero();

	/**
	 * Methode devant renvoyer le type de la place.
	 *
	 * @return Le type de la place.
	 */
	public String getType();

	/**
	 * Methode devant renvoyer une chaine de caracteres explicite indiquant si la place est ou non reserver.
	 *
	 * @return Chaine de caractere indiquant la reservation ou non.
	 */
	public String getReserver();

	/**
	 * Methode devant renvoyer le booleen indiquant l'etat de la reservation.
	 *
	 * @return True pour reserver, false pour non reserver.
	 */
	public boolean getReservation();


	/***************************************************************/
	/*						Setter								   */
	/***************************************************************/
	/**
	 * Methode permettant de reserver ou non la place.
	 *
	 * @param Reserver
	 * 		Boolean True pour reserver, false pour dereserver
	 */
	public void setReservation(boolean Reserver);

	/**
	 * Methode permettant de definir le numero de la place lors de son insertion dans le parking.
	 *
	 * @see Parking#ajouterPlace
	 *
	 * @param numero
	 * 		Le numero a attribuer a la place de parking.
	 */
	public void setNumero(int numero);

	public void setVehicule(Vehicule vehicule) throws PlaceOccupeeException, PlaceReserverException;

	/***************************************************************/
	/*						Methodes							   */
	/***************************************************************/
	/**
	 * Methode toString() affichant toutes les informations de la place.
	 *
	 * @return Une chaine de caracteres contenant les informations.
	 */
	@Override
	public String toString();

	public Vehicule retirerVehicule() throws PlaceLibreException;
}
