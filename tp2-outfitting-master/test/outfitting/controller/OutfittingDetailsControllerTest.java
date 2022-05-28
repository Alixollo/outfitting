package outfitting.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import outfitting.dto.OutfittingDTOForDisplay;
import outfitting.exception.InvalidIdException;
import outfitting.model.RepositoryMock;
import outfitting.model.entity.outfitting.Outfitting;
import outfitting.model.entity.outfitting.OutfittingMock;
import outfitting.view.ViewMock;

public class OutfittingDetailsControllerTest {
	
	private static final int ANY_ID = 0;

	@Test
	public void requestOutfittingDetails_shouldAskOutfittingDetailsViewToDisplay() {
		final ViewMock A_VIEW = new ViewMock();
		OutfittingDetailsController outfittingDetailsController = new OutfittingDetailsController(new ControllerOrchestratorMock(), A_VIEW, new RepositoryMock<Outfitting>(), ANY_ID);
		
		outfittingDetailsController.requestOutfittingDetails();
		
		assertTrue(A_VIEW.displayMethodHasBeenCalled);
	}
	
	@Test
	public void getOutfittingDetails_whenRepositoryIsEmpty_shouldThrowException() {
		OutfittingDetailsController controller = new OutfittingDetailsController(new ControllerOrchestratorMock(), new ViewMock(), new RepositoryMock<Outfitting>(), ANY_ID);
		
		assertThrows(InvalidIdException.class, () -> controller.getOutfittingDetails());
	}
	
	@Test
	public void getOutfittingDetails_whenRepositoryHasValues_shouldReturnDTO() {
		RepositoryMock<Outfitting> repository = new RepositoryMock<Outfitting>();
		Map<Integer, Outfitting> list = new HashMap<Integer, Outfitting>();
		list.put(0, new OutfittingMock());
		repository.setRepo(list);
		OutfittingDetailsController controller = new OutfittingDetailsController(new ControllerOrchestratorMock(), new ViewMock(), repository, ANY_ID);
		
		OutfittingDTOForDisplay dto = controller.getOutfittingDetails();
		
		assertEquals(ANY_ID, dto.ID);
	}

}
