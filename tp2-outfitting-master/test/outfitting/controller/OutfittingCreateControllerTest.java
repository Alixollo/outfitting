package outfitting.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import outfitting.dto.OutfittingDTOForCreate;
import outfitting.model.Region;
import outfitting.model.RepositoryMock;
import outfitting.model.entity.outfitting.Outfitting;
import outfitting.view.ViewMock;

class OutfittingCreateControllerTest {

	@Test
	public void requestOutfittingCreate_shouldAskOutffitingCreateViewToDisplay() {
		final ViewMock A_VIEW = new ViewMock();
		OutfittingCreateController outfittingCreateController = new OutfittingCreateController(new ControllerOrchestratorMock(), A_VIEW, new RepositoryMock<Outfitting>());
		
		outfittingCreateController.requestOutfittingCreate();
		
		assertTrue(A_VIEW.displayMethodHasBeenCalled);
	}
	
	@Test
	public void add_shouldCall_addMethodOfRepository() {
		RepositoryMock<Outfitting> outfittingRepository = new RepositoryMock<Outfitting>();
		OutfittingCreateController controller = new OutfittingCreateController(new ControllerOrchestratorMock(), new ViewMock(), outfittingRepository);
		
		controller.add(new OutfittingDTOForCreate("La pourvoirie", Region.CAPITALE_NATIONALE, "555-555-5555", "la_pourvoirie@email.com", "", "", ""));
		
		assertTrue(outfittingRepository.addMethodHasBeenCalled);
	}
	
}
