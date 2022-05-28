package outfitting.exception;

public class InvalidCottageException extends IllegalArgumentException {

	public static final String INVALID_NAME = "Le nom du chalet est invalide";
	public static final String INVALID_NUMBER_OF_GUESTS = "Le nombre maximum d'invité du chalet doit être plus grand que 0";
	public static final String INVALID_NUMBER_OF_BEDROOMS = "Le nombre de chambres du chalet est invalide";
	public static final String INVALID_PRICE_PER_NIGHT = "Le prix par nuit du chalet est invalide";
	public static final String INVALID_OUTFITTING_ID = "La pourvoirie du chalet est invalide";

	public InvalidCottageException(String message) {
		super(message);
	}

}
