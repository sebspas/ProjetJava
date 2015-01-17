package parking.business;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.vehicule.Vehicule;
import parking.exception.business.PlaceLibreException;
import parking.exception.business.PlaceOccupeeException;
import parking.exception.business.PlaceReserverException;

/**
 * Class Place permmettant de creer une nouvelle place au parking avec son numero,
 * son type, le vehicule qui s'y situe, etc..
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class Place{
	/***************************************************************/
	/*						Debut Donnees Membres 				   */
	/***************************************************************/
	/**
	 * Le numero de la place.
	 */
	private int numeroPlace;

	/**
	 * Le vehicule gare sur la place.
	 */
	private Vehicule vehicule;

	/**
	 * Le type de la place (ex: transporteur, particulier)
	 */
	private String type;

	/**
	 * Booleen indiquant si la place est reservee ou non.
	 */
	private boolean Reserver;

	/***************************************************************/
	/*						Constructeur						   */
	/***************************************************************/
	/**
	 * Constructeur de la classe Place, permettant de creer une place a partir
	 * du type specifie en parametre ainsi que les informations utliles pour
	 * une place, celle ci n'est pas reservee pas defaut.
	 *
	 * @param type
	 *			Le type de la place.
	 */
	public Place(String type) {
		// Initialisation des données membres de la place.
		this.type = type;
		this.Reserver = false;
		
		// Ajout de la place au parking.
		Parking.getInstance().ajouterPlace(this);
	}// Constructeur

	/***************************************************************/
	/*						Getter								   */
	/***************************************************************/
	/**
	 * Methode getVehicule() renvoie le Vehicule qui se situe sur la place.
	 *
	 * @return Le vehicule situe sur cette place.
	 */
	 public Vehicule getVehicule() {
		return vehicule;
	}// getVehicule()

	/**
	 * Methode getNumeroPlace() renvoie le numero de la place.
	 *
	 * @return Le numero de la place.
	 */
	 public int getNumeroPlace() {
		return numeroPlace;
	}//getNumeroPlace()

	/**
	 * Methode getType() renvoie le type de place.
	 *
	 * @return le type de la place.
	 */
	 public String getType() {
		return type;
	}//getType()

	/**
	 * Methode getReserver() renvoie une chaine de caracteres explicite indiquant si la place est ou non reserver.
	 *
	 * @return Chaine de caractere indiquant la reservation ou non.
	 */
	 public String getReserver() {
		return Reserver ?  "est réservée." : "n'est pas réservée.";
	} // getReserver()

	/**
	 * Methode getReservation() renvoie le booleen indiquant l'etat de la reservation.
	 *
	 * @return True pour reserver, false pour ne pas reserver.
	 */
	 public boolean getReservation() {
		return Reserver;
	} // getReservation()

	/***************************************************************/
	/*						Setter								   */
	/***************************************************************/
	/**
	 * Methode setVehicule() permet de definir le vehicule present sur cette place.
	 *
	 * @param vehicule
	 *			Le vehicule a garer sur la place.
	 * @throws PlaceOccupeeException
	 * 			Une des exceptions possible de se propager en cas d'erreur.
	 * @throws PlaceReserverException
	 * 			Une autre exception propagee en cas d'erreur.
	 */
	public void setVehicule(Vehicule vehicule) throws PlaceOccupeeException, PlaceReserverException{
		// Si la place possède déja un véhicule ou que le type de se Véhicule n'est pas compatible avec la place on léve une exception.
		if(this.vehicule != null || (vehicule.getType() == "Camion" && type == "Particulier"))
			throw new PlaceOccupeeException();
		// si elle est réservée on lève une exception.
		else if (this.Reserver)
			throw new PlaceReserverException();
		// sinon on définie le véhicule à cette place
		else
			this.vehicule = vehicule;
	}// setVehicule()

	/**
	 * Methode setNumero() permet de definir le numero de la place lors de son insertion dans le parking.
	 *
	 * @param numero
	 * 		Le numero a attribuer a la place de parking.
	 */
	public void setNumero(int numero) {
		numeroPlace = numero;
	} // setNumero()

	/**
	 * Methode setReservation() permet de reserver ou non la place.
	 *
	 * @param Reserver
	 * 		Boolean True pour reserver, false pour dereserver
	 */
	public void setReservation(boolean Reserver) {
		// On définit la place à reserer ou non
		this.Reserver = Reserver;
		
		// Selon si on réserve ou déreserve une place on augmente ou diminue le nombre de place occupées.
		if (Reserver)
			Parking.getInstance().setNbPlaceOccupees(Parking.getInstance().getNbPlaceOccupees() + 1);
		else
			Parking.getInstance().setNbPlaceOccupees(Parking.getInstance().getNbPlaceOccupees() - 1);
		Parking.getInstance().notifier();
	} // setRservation()

	/***************************************************************/
	/*						Methodes							   */
	/***************************************************************/
	/**
	 * Methode toString() affiche toutes les informations de la place.
	 *
	 * @return Une chaine de caracteres contenant les informations spécifiques a une place.
	 */
	@Override
	public String toString() {
		return "Particulier [numeroPlace=" + numeroPlace + ", vehicule="
				+ vehicule + getReserver() + "]";
	}// toString()

	/**
	 * Methode retirerVehicule() permet de retirer le véhicule de la place.
	 * 
	 * @return Vehicule
	 * 			Le vehicule retire.
	 * @throws PlaceLibreException
	 */
	public Vehicule retirerVehicule() throws PlaceLibreException{
		// Si la place est déjà libre on lève une exception.
		if (this.vehicule == null) {
			throw new PlaceLibreException();
		}
		// Sinon on retire le véhicule.
		Vehicule temp = this.vehicule;
		this.vehicule = null;
		// On réorganise le parking.
		Parking.getInstance().reorganiserPlaces();
		// On renvoie le véhicule retiré.
		return temp;
	} // retirerVehicule()

} // Place class
