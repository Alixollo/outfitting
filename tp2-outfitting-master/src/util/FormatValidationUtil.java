package util;

import java.util.regex.Pattern;

public abstract class FormatValidationUtil {
	public static final String TELEPHONE_FORMAT = "([0-9]{1,3}-)?[0-9]{3}-[0-9]{3}-[0-9]{4}";
	public static final String EMAIL_FORMAT = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	
	public static boolean isValidEmailFormat(String email) {
		return Pattern.matches(EMAIL_FORMAT, email);
	}
	
	public static boolean isValidTelephoneFormat(String telephone) {
		return Pattern.matches(TELEPHONE_FORMAT, telephone);
	}
	
	public static boolean isNullOrBlank(String txt) {
		return (txt == null || txt.isBlank());
	}
}
