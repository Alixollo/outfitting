package outfitting.model.entity.outfitting;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import outfitting.exception.InvalidOutfittingException;
import outfitting.model.Region;

public class OutfittingTest {
	private static final String ANY_NAME = "La pourvoirie";
	private static final Region ANY_REGION = Region.CENTRE_QUEBEC;
	private static final String ANY_TELEPHONE = "333-333-3333";
	private static final String ANY_EMAIL = "coolOutfitting@gmail.com";
	
	private Outfitting anOutfitting;
	
	@BeforeEach
	public void setUpOutfitting(){
		anOutfitting = new Outfitting(ANY_NAME, ANY_REGION, ANY_TELEPHONE, ANY_EMAIL, ANY_NAME, ANY_TELEPHONE, ANY_EMAIL);
	}
	
	@Test
	public void createOutfitting_shouldInitializeOutfittingName() {
		assertEquals(ANY_NAME, anOutfitting.getName());
	}
	
	@Test
	public void createOutfitting_shouldInitializeOutfittingRegion() {
		assertEquals(ANY_REGION, anOutfitting.getRegion());
	}
	
	@Test
	public void createOutfitting_shouldInitializeOutfittingTelephone() {
		assertEquals(ANY_TELEPHONE, anOutfitting.getTelephone());
	}
	
	@Test
	public void createOutfitting_shouldInitializeOutfittingEmail() {
		assertEquals(ANY_EMAIL, anOutfitting.getEmail());
	}
	
	@Test
	public void createOutfitting_shouldInitializeOutfittingContactName() {
		assertEquals(ANY_NAME, anOutfitting.getPrivateName());
	}
	
	@Test
	public void createOutfitting_shouldInitializeOutfittingContactTelephone() {
		assertEquals(ANY_TELEPHONE, anOutfitting.getPrivateTelephone());
	}
	
	@Test
	public void createOutfitting_shouldInitializeOutfittingContactEmail() {
		assertEquals(ANY_EMAIL, anOutfitting.getPrivateEmail());
	}
	
	@Test
	public void setId_shouldSetOutfittingId() {
		final int ANY_ID = 4;
		
		anOutfitting.setId(ANY_ID);
		
		assertEquals(ANY_ID, anOutfitting.getId());
	}
	
	@Test
	public void createOutfitting_whenNameIsEmpty_shouldRaiseException() {
		InvalidOutfittingException exception = assertThrows(InvalidOutfittingException.class,
				() -> new Outfitting("", ANY_REGION, ANY_TELEPHONE, ANY_EMAIL, ANY_NAME, ANY_TELEPHONE, ANY_EMAIL));

		assertEquals(InvalidOutfittingException.INVALID_NAME, exception.getMessage());
	}
	
	@Test
	public void createOutfitting_whenNameIsNull_shouldRaiseException() {
		InvalidOutfittingException exception = assertThrows(InvalidOutfittingException.class,
				() -> new Outfitting(null, ANY_REGION, ANY_TELEPHONE, ANY_EMAIL, ANY_NAME, ANY_TELEPHONE, ANY_EMAIL));

		assertEquals(InvalidOutfittingException.INVALID_NAME, exception.getMessage());
	}
	
	@Test
	public void createOutfitting_whenRegionIsNull_shouldRaiseException() {
		InvalidOutfittingException exception = assertThrows(InvalidOutfittingException.class,
				() -> new Outfitting(ANY_NAME, null, ANY_TELEPHONE, ANY_EMAIL, ANY_NAME, ANY_TELEPHONE, ANY_EMAIL));

		assertEquals(InvalidOutfittingException.INVALID_REGION, exception.getMessage());
	}
	
	@Test
	public void createOutfitting_whenTelephoneIsEmpty_shouldRaiseException() {
		InvalidOutfittingException exception = assertThrows(InvalidOutfittingException.class,
				() -> new Outfitting(ANY_NAME, ANY_REGION, "", ANY_EMAIL, ANY_NAME, ANY_TELEPHONE, ANY_EMAIL));

		assertEquals(InvalidOutfittingException.INVALID_TELEPHONE, exception.getMessage());
	}
	
	@Test
	public void createOutfitting_whenTelephoneIsNull_shouldRaiseException() {
		InvalidOutfittingException exception = assertThrows(InvalidOutfittingException.class,
				() -> new Outfitting(ANY_NAME, ANY_REGION, null, ANY_EMAIL, ANY_NAME, ANY_TELEPHONE, ANY_EMAIL));

		assertEquals(InvalidOutfittingException.INVALID_TELEPHONE, exception.getMessage());
	}
	
	@Test
	public void createOutfitting_whenTelephoneIsWrongFormat_shouldRaiseException() {
		InvalidOutfittingException exception = assertThrows(InvalidOutfittingException.class,
				() -> new Outfitting(ANY_NAME, ANY_REGION, "8956 6464 646a", ANY_EMAIL, ANY_NAME, ANY_TELEPHONE, ANY_EMAIL));

		assertEquals(InvalidOutfittingException.INVALID_TELEPHONE_FORMAT, exception.getMessage());
	}
	
	@Test
	public void createOutfitting_whenTelephoneIsValidFormat_shouldNotRaiseException() {
		assertDoesNotThrow(() -> new Outfitting(ANY_NAME, ANY_REGION, "1-800-555-5555", ANY_EMAIL, ANY_NAME, ANY_TELEPHONE, ANY_EMAIL));
	}
	
	@Test
	public void createOutfitting_whenEmailIsNull_shouldRaiseException() {
		InvalidOutfittingException exception = assertThrows(InvalidOutfittingException.class,
				() -> new Outfitting(ANY_NAME, ANY_REGION, ANY_TELEPHONE, null, ANY_NAME, ANY_TELEPHONE, ANY_EMAIL));

		assertEquals(InvalidOutfittingException.INVALID_EMAIL, exception.getMessage());
	}
	
	@Test
	public void createOutfitting_whenEmailIsWrongFormat_shouldRaiseException() {
		InvalidOutfittingException exception = assertThrows(InvalidOutfittingException.class,
				() -> new Outfitting(ANY_NAME, ANY_REGION, ANY_TELEPHONE, "bob@", ANY_NAME, ANY_TELEPHONE, ANY_EMAIL));

		assertEquals(InvalidOutfittingException.INVALID_EMAIL_FORMAT, exception.getMessage());
	}
	
	@Test
	public void createOutfitting_whenOutfittingHasEmptyContactInfo_shouldNotRaiseException() {
		assertDoesNotThrow(() -> new Outfitting(ANY_NAME, ANY_REGION, "1-800-555-5555", ANY_EMAIL, "", "", ""));
	}
	
	@Test
	public void createOutfitting_whenOutfittingHasContactInfo_AndContactNameIsEmpty_shouldRaiseException() {
		InvalidOutfittingException exception = assertThrows(InvalidOutfittingException.class,
				() -> new Outfitting(ANY_NAME, ANY_REGION, ANY_TELEPHONE, ANY_EMAIL, "", ANY_TELEPHONE, ANY_EMAIL));

		assertEquals(InvalidOutfittingException.INVALID_CONTACT_NAME, exception.getMessage());
	}
	
	@Test
	public void createOutfitting_whenOutfittingHasContactInfo_AndContactTelephoneIsEmpty_shouldRaiseException() {
		InvalidOutfittingException exception = assertThrows(InvalidOutfittingException.class,
				() -> new Outfitting(ANY_NAME, ANY_REGION, ANY_TELEPHONE, ANY_EMAIL, ANY_NAME, "", ANY_EMAIL));

		assertEquals(InvalidOutfittingException.INVALID_CONTACT_TELEPHONE, exception.getMessage());
	}
	
	@Test
	public void createOutfitting_whenOutfittingHasContactInfo_AndContactTelephoneIsWrongFormat_shouldRaiseException() {
		InvalidOutfittingException exception = assertThrows(InvalidOutfittingException.class,
				() -> new Outfitting(ANY_NAME, ANY_REGION, ANY_TELEPHONE, ANY_EMAIL, ANY_NAME, "4rftf4", ANY_EMAIL));

		assertEquals(InvalidOutfittingException.INVALID_CONTACT_TELEPHONE_FORMAT, exception.getMessage());
	}
	
	@Test
	public void createOutfitting_whenOutfittingHasContactInfo_AndContactEmailIsEmpty_shouldRaiseException() {
		InvalidOutfittingException exception = assertThrows(InvalidOutfittingException.class,
				() -> new Outfitting(ANY_NAME, ANY_REGION, ANY_TELEPHONE, ANY_EMAIL, ANY_NAME, ANY_TELEPHONE, ""));

		assertEquals(InvalidOutfittingException.INVALID_CONTACT_EMAIL, exception.getMessage());
	}
	
	@Test
	public void createOutfitting_whenOutfittingHasContactInfo_AndContactEmailIsWrongFormat_shouldRaiseException() {
		InvalidOutfittingException exception = assertThrows(InvalidOutfittingException.class,
				() -> new Outfitting(ANY_NAME, ANY_REGION, ANY_TELEPHONE, ANY_EMAIL, ANY_NAME, ANY_TELEPHONE, "r4frvfr"));

		assertEquals(InvalidOutfittingException.INVALID_CONTACT_EMAIL_FORMAT, exception.getMessage());
	}
	
}
