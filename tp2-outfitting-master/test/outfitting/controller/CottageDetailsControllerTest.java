package outfitting.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import outfitting.exception.InvalidIdException;
import outfitting.model.EntityRepositoryObserver.UpdateType;
import outfitting.model.RepositoryMock;
import outfitting.model.entity.cottage.Cottage;
import outfitting.model.entity.cottage.CottageMock;
import outfitting.view.ViewMock;

class CottageDetailsControllerTest {

	private static final int ANY_ID = 0;
	
	@Test
	public void requestCottageDetails_shouldAskCottageDetailsViewToDisplay() {
		final ViewMock A_VIEW = new ViewMock();
		CottageDetailsController cottageDetailsController = new CottageDetailsController(new ControllerOrchestratorMock(), A_VIEW, new RepositoryMock<Cottage>(), ANY_ID);
		
		cottageDetailsController.requestCottageDetails();
		
		assertTrue(A_VIEW.displayMethodHasBeenCalled);
	}
	
	@Test
	public void getCottageDetails_whenCottageIsNotInRepository_shouldThrowException() {
		CottageDetailsController controller = new CottageDetailsController(new ControllerOrchestratorMock(), new ViewMock(), new RepositoryMock<Cottage>(), ANY_ID);
		
		assertThrows(InvalidIdException.class, () -> controller.getCottageDetails());
	}
	
	@Test
	public void getCottageDetails_whenCottageIsInRepository_shouldReturnCottageDTOForDisplay() {
		RepositoryMock<Cottage> repository = new RepositoryMock<Cottage>();
		Map<Integer, Cottage> list = new HashMap<Integer, Cottage>();
		list.put(0, new CottageMock());
		repository.setRepo(list);
		
		CottageDetailsController controller = new CottageDetailsController(new ControllerOrchestratorMock(), new ViewMock(), repository, ANY_ID);
		
		assertTrue(controller.getCottageDetails().ID == ANY_ID);
	}
	
	@Test
	public void notify_whenUpdateTypeIsRemove_shouldAskCottageDetailsViewToRefresh() {
		final ViewMock A_VIEW = new ViewMock();
		CottageDetailsController cottageDetailsController = new CottageDetailsController(new ControllerOrchestratorMock(), A_VIEW, new RepositoryMock<Cottage>(), ANY_ID);
		
		cottageDetailsController.notify(UpdateType.REMOVED);
		
		assertTrue(A_VIEW.refreshMethodHasBeenCalled);
	}
	
	@Test
	public void notify_whenUpdateTypeIsNotRemove_shouldNotAskCottageDetailsViewToRefresh() {
		final ViewMock A_VIEW = new ViewMock();
		CottageDetailsController cottageDetailsController = new CottageDetailsController(new ControllerOrchestratorMock(), A_VIEW, new RepositoryMock<Cottage>(), ANY_ID);
		
		cottageDetailsController.notify(UpdateType.ADDED);
		
		assertFalse(A_VIEW.refreshMethodHasBeenCalled);
	}
	
	@Test
	public void removeCottage_shouldAskRepositoryToRemove() {
		final RepositoryMock<Cottage> A_REPOSITORY = new RepositoryMock<Cottage>();
		CottageDetailsController cottageDetailsController = new CottageDetailsController(new ControllerOrchestratorMock(), new ViewMock(), A_REPOSITORY, ANY_ID);
		
		cottageDetailsController.removeCottage();
		
		assertTrue(A_REPOSITORY.removeMethodHasBeenCalled);
	}

}
