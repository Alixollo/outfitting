package outfitting.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import outfitting.dto.OutfittingDTOForCreate;
import outfitting.dto.OutfittingDTOForDisplay;
import outfitting.model.Region;
import outfitting.model.entity.outfitting.Outfitting;

class OutfittingConverterTest {
	
	private static final String ANY_NAME = "La pourvoirie";
	private static final Region ANY_REGION = Region.ABITIBI_TEMISCAMINGUE;
	private static final String ANY_PHONE_NUMBER = "333-333-3333";
	private static final String ANY_EMAIL = "coolOutfitting@gmail.com";
	

	@Test
	void mapToDTO_whenGivenAnOutfitting_shouldReturnOutfittingDTOForDisplayWithCorrectData() {
		final Outfitting AN_OUTFITTING = new Outfitting(ANY_NAME, ANY_REGION, ANY_PHONE_NUMBER, ANY_EMAIL, ANY_NAME, ANY_PHONE_NUMBER, ANY_EMAIL);
		
		OutfittingDTOForDisplay result = new OutfittingConverter().mapToDTO(AN_OUTFITTING);
		
		assertEquals(AN_OUTFITTING.getId(), result.ID);
		assertEquals(ANY_NAME, result.NAME);
		assertEquals(ANY_REGION, result.REGION);
		assertEquals(ANY_PHONE_NUMBER, result.TELEPHONE);
		assertEquals(ANY_EMAIL, result.EMAIL);
		assertEquals(ANY_NAME, result.PRIVATE_NAME);
		assertEquals(ANY_PHONE_NUMBER, result.PRIVATE_TELEPHONE);
		assertEquals(ANY_EMAIL, result.PRIVATE_EMAIL);
	}

	@Test
	void mapToDTO_whenGivenCollectionOfOutfitting_shouldReturnCollectionOfOutfittingDTOForDisplay() {
		final Outfitting AN_OUTFITTING = new Outfitting(ANY_NAME, ANY_REGION, ANY_PHONE_NUMBER, ANY_EMAIL, ANY_NAME, ANY_PHONE_NUMBER, ANY_EMAIL);
		final Collection<Outfitting> AN_OUTFITTING_COLLECTION = new ArrayList<Outfitting>();
		AN_OUTFITTING_COLLECTION.add(AN_OUTFITTING);
		
		Collection<OutfittingDTOForDisplay> result = new OutfittingConverter().mapToDTO(AN_OUTFITTING_COLLECTION);
		
		assertEquals(AN_OUTFITTING_COLLECTION.size(), result.size());
	}

	@Test
	void mapFromDTO_shouldReturnCottageWithCorrectData() {
		final OutfittingDTOForCreate AN_OUTFITTING = new OutfittingDTOForCreate(ANY_NAME, ANY_REGION, ANY_PHONE_NUMBER, ANY_EMAIL, ANY_NAME, ANY_PHONE_NUMBER, ANY_EMAIL);
		
		Outfitting result = new OutfittingConverter().mapFromDTO(AN_OUTFITTING);
		
		assertEquals(AN_OUTFITTING.NAME, result.getName());
		assertEquals(AN_OUTFITTING.REGION, result.getRegion());
		assertEquals(AN_OUTFITTING.PHONE_NUMBER, result.getTelephone());
		assertEquals(AN_OUTFITTING.EMAIL, result.getEmail());
		assertEquals(AN_OUTFITTING.PRIVATE_NAME, result.getPrivateName());
		assertEquals(AN_OUTFITTING.PRIVATE_TELEPHONE, result.getPrivateTelephone());
		assertEquals(AN_OUTFITTING.PRIVATE_EMAIL, result.getPrivateEmail());
	}

}
