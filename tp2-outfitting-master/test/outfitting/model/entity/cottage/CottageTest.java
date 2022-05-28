package outfitting.model.entity.cottage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import outfitting.exception.InvalidCottageException;
import outfitting.model.entity.outfitting.OutfittingMock;

public class CottageTest {
	
	private static final String ANY_NAME = "Le Chalet";
	private static final int ANY_GUESTS_NUMBER = 5;
	private static final int ANY_BEDROOMS_NUMBER = 2;
	private static final float ANY_PRICE_PER_NIGHT = 56.99f;
	private static final OutfittingMock ANY_OUTFITTING = new OutfittingMock();
	
	private Cottage aCottage;
	
	@BeforeEach
	public void setUpACottage(){
		aCottage = new Cottage(ANY_NAME, ANY_GUESTS_NUMBER, ANY_BEDROOMS_NUMBER, ANY_PRICE_PER_NIGHT);
	}
	
	@Test
	public void createCottage_shouldInitializeCottageName() {
		assertEquals(ANY_NAME, aCottage.getName());
	}
	
	@Test
	public void createCottage_shouldInitializeMaxNumberOfGuests() {
		assertEquals(ANY_GUESTS_NUMBER, aCottage.getNbOfGuests());
	}
	
	@Test
	public void createCottage_shouldInitializeNumberOfBedrooms() {
		assertEquals(ANY_BEDROOMS_NUMBER, aCottage.getNbOfBedrooms());
	}
	
	@Test
	public void createCottage_shouldInitializePricePerNight() {
		assertEquals(ANY_PRICE_PER_NIGHT, aCottage.getPricePerNight());
	}
	
	@Test
	public void setOutfitting_shouldSetOutfitting() {
		aCottage.setOutfitting(ANY_OUTFITTING);
		
		assertEquals(ANY_OUTFITTING, aCottage.getOutfitting());
	}
	
	@Test
	public void createCottage_whenNameIsEmpty_shouldRaiseException() {
		InvalidCottageException exception = assertThrows(InvalidCottageException.class,
				() -> new Cottage("", ANY_GUESTS_NUMBER, ANY_BEDROOMS_NUMBER, ANY_PRICE_PER_NIGHT));

		assertEquals(InvalidCottageException.INVALID_NAME, exception.getMessage());
	}
	
	@Test
	public void createCottage_whenNameIsNull_shouldRaiseException() {
		InvalidCottageException exception = assertThrows(InvalidCottageException.class,
				() -> new Cottage(null, ANY_GUESTS_NUMBER, ANY_BEDROOMS_NUMBER, ANY_PRICE_PER_NIGHT));

		assertEquals(InvalidCottageException.INVALID_NAME, exception.getMessage());
	}
	
	@Test
	public void createCottage_whenMaxOfGuestIsLessThanZero_shouldRaiseException() {
		InvalidCottageException exception = assertThrows(InvalidCottageException.class,
				() -> new Cottage(ANY_NAME, -1, ANY_BEDROOMS_NUMBER, ANY_PRICE_PER_NIGHT));

		assertEquals(InvalidCottageException.INVALID_NUMBER_OF_GUESTS, exception.getMessage());
	}
	
	@Test
	public void createCottage_whenMaxOfGuestIsZero_shouldRaiseException() {
		InvalidCottageException exception = assertThrows(InvalidCottageException.class,
				() -> new Cottage(ANY_NAME, 0, ANY_BEDROOMS_NUMBER, ANY_PRICE_PER_NIGHT));

		assertEquals(InvalidCottageException.INVALID_NUMBER_OF_GUESTS, exception.getMessage());
	}
	
	@Test
	public void createCottage_whenNbOfBedroomsIsLessThanZero_shouldRaiseException() {
		InvalidCottageException exception = assertThrows(InvalidCottageException.class,
				() -> new Cottage(ANY_NAME, ANY_GUESTS_NUMBER, -1, ANY_PRICE_PER_NIGHT));

		assertEquals(InvalidCottageException.INVALID_NUMBER_OF_BEDROOMS, exception.getMessage());
	}
	
	@Test
	public void createCottage_whenPricePerNightIsLessThanZero_shouldRaiseException() {
		InvalidCottageException exception = assertThrows(InvalidCottageException.class,
				() -> new Cottage(ANY_NAME, ANY_GUESTS_NUMBER, ANY_BEDROOMS_NUMBER, -0.01f));

		assertEquals(InvalidCottageException.INVALID_PRICE_PER_NIGHT, exception.getMessage());
	}

}
