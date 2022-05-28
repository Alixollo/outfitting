package outfitting.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import outfitting.dto.OutfittingDTOForDisplay;
import outfitting.model.RepositoryMock;
import outfitting.model.entity.outfitting.Outfitting;
import outfitting.model.entity.outfitting.OutfittingMock;
import outfitting.view.ViewMock;

public class OutfittingListControllerTest {
	
	private static final int ANY_ID = 0;
	private static final String ANY_NAME = "";

	@Test
	public void requestOutfittingList_shouldAskOutfittingListViewToDisplay() {
		final ViewMock A_VIEW = new ViewMock();
		OutfittingListController outfittingListController = new OutfittingListController(new ControllerOrchestratorMock(), A_VIEW, new RepositoryMock<Outfitting>());
		
		outfittingListController.requestOutfittingList();
		
		assertTrue(A_VIEW.displayMethodHasBeenCalled);
	}
	
	@Test
	public void getOutfittingList_whenRepositoryIsEmpty_shouldReturnEmptyCollectionOfDTO() {
		OutfittingListController controller = new OutfittingListController(new ControllerOrchestratorMock(), new ViewMock(), new RepositoryMock<Outfitting>());
		
		Collection<OutfittingDTOForDisplay> collection = controller.getOutfittingList();
		
		assertTrue(collection.isEmpty());
	}
	
	@Test
	public void getOutfittingList_whenRepositoryHasValues_shouldReturnCollectionOfDTO() {
		RepositoryMock<Outfitting> repository = new RepositoryMock<Outfitting>();
		Map<Integer, Outfitting> list = new HashMap<Integer, Outfitting>();
		list.put(1, new OutfittingMock());
		repository.setRepo(list);
		OutfittingListController controller = new OutfittingListController(new ControllerOrchestratorMock(), new ViewMock(), repository);
		
		Collection<OutfittingDTOForDisplay> collection = controller.getOutfittingList();
		
		assertFalse(collection.isEmpty());
	}

	@Test
	public void searchOutfitting_whenRepositoryIsEmpty_shouldReturnEmptyCollectionOfDTO() {
		OutfittingListController controller = new OutfittingListController(new ControllerOrchestratorMock(), new ViewMock(), new RepositoryMock<Outfitting>());
		
		Collection<OutfittingDTOForDisplay> collection = controller.searchOutfitting(ANY_NAME);
		
		assertTrue(collection.isEmpty());
	}
	
	@Test
	public void searchOutfitting_whenRepositoryHasValues_shouldReturnCollectionOfDTO() {
		RepositoryMock<Outfitting> repository = new RepositoryMock<Outfitting>();
		Map<Integer, Outfitting> list = new HashMap<Integer, Outfitting>();
		list.put(1, new OutfittingMock());
		repository.setRepo(list);
		OutfittingListController controller = new OutfittingListController(new ControllerOrchestratorMock(), new ViewMock(), repository);
		
		Collection<OutfittingDTOForDisplay> collection = controller.searchOutfitting(ANY_NAME);
		
		assertFalse(collection.isEmpty());
	}
	
	@Test
	public void requestOutfittingDetails_shouldCallGoToOutfittingDetails() {
		ControllerOrchestratorMock orchestrator = new ControllerOrchestratorMock();
		OutfittingListController controller = new OutfittingListController(orchestrator, new ViewMock(), new RepositoryMock<Outfitting>());
		
		controller.requestOutfittingDetails(ANY_ID);
		
		assertTrue(orchestrator.goToOutfittingDetailsHasBeenCalled);
	}
	
	@Test
	public void notify_shouldCallRefreshMethod() {
		final ViewMock A_VIEW = new ViewMock();
		OutfittingListController outfittingListController = new OutfittingListController(new ControllerOrchestratorMock(), A_VIEW, new RepositoryMock<Outfitting>());
		
		outfittingListController.notify(null);
		
		assertTrue(A_VIEW.refreshMethodHasBeenCalled);
	}
	
}
