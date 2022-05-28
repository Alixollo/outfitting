package outfitting.exception;

public class InvalidIdException extends IllegalArgumentException{
	public static final String INEXISTANT_ID = "Aucun objet ne poss�de cet identifiant.";
	
	public InvalidIdException(String message) {
		super(message);
	}
}
