package outfitting.exception;

public class InvalidOutfittingException extends IllegalArgumentException {
	public static final String INVALID_NAME = "Le nom de la pourvoirie est invalide";
	public static final String INVALID_REGION = "La r�gion de la pourvoirie est invalide";
	public static final String INVALID_TELEPHONE = "Le num�ro de t�l�phone de la pourvoirie est invalide";
	public static final String INVALID_EMAIL = "Le courriel de la pourvoirie est invalide";
	public static final String INVALID_TELEPHONE_FORMAT = "Le num�ro de t�l�phone de la pourvoirie n'est pas au bon format (ex: 111-111-1111 ou 1-111-111-1111)";
	public static final String INVALID_EMAIL_FORMAT = "Le courriel de la pourvoirie n'est pas au bon format";
	public static final String INVALID_CONTACT_NAME = "Le nom du contact est invalide";
	public static final String INVALID_CONTACT_TELEPHONE = "Le num�ro de t�l�phone du contact est invalide";
	public static final String INVALID_CONTACT_EMAIL = "Le courriel de la pourvoirie est invalide";
	public static final String INVALID_CONTACT_TELEPHONE_FORMAT = "Le num�ro de t�l�phone du contact n'est pas au bon format (ex: 111-111-1111 ou 1-111-111-1111)";
	public static final String INVALID_CONTACT_EMAIL_FORMAT = "Le courriel du contact n'est pas au bon format";

	public InvalidOutfittingException(String message) {
		super(message);
	}
}
