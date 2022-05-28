package outfitting.model.entity.cottage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import outfitting.model.Region;

public class CottageFilterByRegionTest {
	
	@Test
	public void isMatching_whenValueMatches_shouldReturnTrue() {
		CottageFilterByRegion filter = new CottageFilterByRegion(Region.ABITIBI_TEMISCAMINGUE);
		CottageMock mock = new CottageMock();
		mock.setRegion(Region.ABITIBI_TEMISCAMINGUE);
		
		boolean matches = filter.isMatching(mock);
		
		assertTrue(matches);
	}
	
	@Test
	public void isMatching_whenValueDoesntMatches_shouldReturnFalse() {
		CottageFilterByRegion filter = new CottageFilterByRegion(Region.ABITIBI_TEMISCAMINGUE);
		CottageMock mock = new CottageMock();
		mock.setRegion(Region.CAPITALE_NATIONALE);
		
		boolean matches = filter.isMatching(mock);
		
		assertFalse(matches);
	}

}
