package outfitting.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IdButtonTest {

	@Test
	public void createIdButton_shouldInitializeIdEntity() {
		final String ANY_LABEL = "Bouton";
		final int ANY_ID = 1;
		assertEquals(ANY_ID, new IdButton(ANY_LABEL, ANY_ID).getIdEntity());
	}

}
