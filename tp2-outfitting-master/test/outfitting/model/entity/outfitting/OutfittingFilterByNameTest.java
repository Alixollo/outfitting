package outfitting.model.entity.outfitting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class OutfittingFilterByNameTest {
	
	private final String ANY_NAME = "Un nom";
	
	@Test
	public void isMatching_whenValueMatches_shouldReturnTrue() {
		OutfittingFilterByName filter = new OutfittingFilterByName(ANY_NAME);
		OutfittingMock mock = new OutfittingMock();
		mock.setName(ANY_NAME);
		
		boolean matches = filter.isMatching(mock);
		
		assertTrue(matches);
	}
	
	@Test
	public void isMatching_whenValueDoesntMatches_shouldReturnFalse() {
		OutfittingFilterByName filter = new OutfittingFilterByName(ANY_NAME);
		OutfittingMock mock = new OutfittingMock();
		mock.setName("Mauvais nom");
		
		boolean matches = filter.isMatching(mock);
		
		assertFalse(matches);
	}
}
