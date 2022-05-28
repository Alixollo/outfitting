package outfitting.model.entity.cottage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CottageFilterByRoomAmountTest {
	
	private static final int ANY_ROOM_AMOUNT = 1;
	
	@Test
	public void isMatching_whenValueMatches_shouldReturnTrue() {
		CottageFilterByRoomAmount filter = new CottageFilterByRoomAmount(ANY_ROOM_AMOUNT);
		CottageMock mock = new CottageMock();
		mock.setRoomAmount(ANY_ROOM_AMOUNT);
		
		boolean matches = filter.isMatching(mock);
		
		assertTrue(matches);
	}
	
	@Test
	public void isMatching_whenValueDoesntMatches_shouldReturnFalse() {
		CottageFilterByRoomAmount filter = new CottageFilterByRoomAmount(ANY_ROOM_AMOUNT);
		CottageMock mock = new CottageMock();
		mock.setRoomAmount(5);
		
		boolean matches = filter.isMatching(mock);
		
		assertFalse(matches);
	}
	
}
