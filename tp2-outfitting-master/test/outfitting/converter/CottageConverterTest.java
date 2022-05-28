package outfitting.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import outfitting.dto.CottageDTOForCreate;
import outfitting.dto.CottageDTOForDisplay;
import outfitting.model.entity.cottage.Cottage;
import outfitting.model.entity.outfitting.OutfittingMock;

class CottageConverterTest {
	
	private static final String ANY_NAME = "Le Chalet";
	private static final int ANY_NB_GUESTS = 5;
	private static final int ANY_NB_BEDROOMS = 2;
	private static final float ANY_PRICE_PER_NIGHT = 56.99f;
	private static final OutfittingMock ANY_OUTFITTING = new OutfittingMock();
	
	private Cottage aCottage;
	
	@BeforeEach
	public void setUpACottage(){
		aCottage = new Cottage(ANY_NAME, ANY_NB_GUESTS, ANY_NB_BEDROOMS, ANY_PRICE_PER_NIGHT);
		aCottage.setOutfitting(ANY_OUTFITTING);
	}
	
	@Test
	void mapToDTO_whenGivenACottage_shouldReturnCottageDTOForDisplayWithCorrectId() {
		
		CottageDTOForDisplay result = new CottageConverter().mapToDTO(aCottage);
		
		assertEquals(aCottage.getId(), result.ID);
	}
	
	@Test
	void mapToDTO_whenGivenACottage_shouldReturnCottageDTOForDisplayWithCorrectName() {
		
		CottageDTOForDisplay result = new CottageConverter().mapToDTO(aCottage);
		
		assertEquals(ANY_NAME, result.NAME);
	}
	
	@Test
	void mapToDTO_whenGivenACottage_shouldReturnCottageDTOForDisplayWithCorrectGuestAmount() {
		
		CottageDTOForDisplay result = new CottageConverter().mapToDTO(aCottage);
		
		assertEquals(ANY_NB_GUESTS, result.GUESTS);
	}
	
	@Test
	void mapToDTO_whenGivenACottage_shouldReturnCottageDTOForDisplayWithCorrectRoomAmount() {
		
		CottageDTOForDisplay result = new CottageConverter().mapToDTO(aCottage);
		
		assertEquals(ANY_NB_BEDROOMS, result.BEDROOMS);
	}
	
	@Test
	void mapToDTO_whenGivenACottage_shouldReturnCottageDTOForDisplayWithCorrectPrice() {
		
		CottageDTOForDisplay result = new CottageConverter().mapToDTO(aCottage);
		
		assertEquals(ANY_PRICE_PER_NIGHT, result.PRICE_PER_NIGHT);
	}

	@Test
	void mapToDTO_whenGivenCollectionOfCottage_shouldReturnCollectionOfCottageDTOForDisplay() {
		final Collection<Cottage> A_COTTAGE_COLLECTION = new ArrayList<Cottage>();
		
		A_COTTAGE_COLLECTION.add(aCottage);
		
		Collection<CottageDTOForDisplay> result = new CottageConverter().mapToDTO(A_COTTAGE_COLLECTION);
		
		assertEquals(A_COTTAGE_COLLECTION.size(), result.size());
	}

	@Test
	void mapFromDTO_shouldReturnCottageWithCorrectName() {
		final CottageDTOForCreate A_COTTAGE = new CottageDTOForCreate(ANY_NAME, ANY_NB_GUESTS, ANY_NB_BEDROOMS, ANY_PRICE_PER_NIGHT, ANY_OUTFITTING.getId());
		
		Cottage result = new CottageConverter().mapFromDTO(A_COTTAGE);
		
		assertEquals(A_COTTAGE.NAME, result.getName());
	}
	
	@Test
	void mapFromDTO_shouldReturnCottageWithCorrectGuestAmount() {
		final CottageDTOForCreate A_COTTAGE = new CottageDTOForCreate(ANY_NAME, ANY_NB_GUESTS, ANY_NB_BEDROOMS, ANY_PRICE_PER_NIGHT, ANY_OUTFITTING.getId());
		
		Cottage result = new CottageConverter().mapFromDTO(A_COTTAGE);
		
		assertEquals(A_COTTAGE.GUESTS, result.getNbOfGuests());
	}
	
	@Test
	void mapFromDTO_shouldReturnCottageWithCorrectRoomAmount() {
		final CottageDTOForCreate A_COTTAGE = new CottageDTOForCreate(ANY_NAME, ANY_NB_GUESTS, ANY_NB_BEDROOMS, ANY_PRICE_PER_NIGHT, ANY_OUTFITTING.getId());
		
		Cottage result = new CottageConverter().mapFromDTO(A_COTTAGE);
		
		assertEquals(A_COTTAGE.BEDROOMS, result.getNbOfBedrooms());
	}
	
	@Test
	void mapFromDTO_shouldReturnCottageWithCorrectPrice() {
		final CottageDTOForCreate A_COTTAGE = new CottageDTOForCreate(ANY_NAME, ANY_NB_GUESTS, ANY_NB_BEDROOMS, ANY_PRICE_PER_NIGHT, ANY_OUTFITTING.getId());
		
		Cottage result = new CottageConverter().mapFromDTO(A_COTTAGE);
		
		assertEquals(A_COTTAGE.PRICE_PER_NIGHT, result.getPricePerNight());
	}

}
