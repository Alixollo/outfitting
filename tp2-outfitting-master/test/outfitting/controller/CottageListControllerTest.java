package outfitting.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import outfitting.dto.CottageDTOForDisplay;
import outfitting.model.Region;
import outfitting.model.RepositoryMock;
import outfitting.model.entity.cottage.Cottage;
import outfitting.model.entity.cottage.CottageMock;
import outfitting.model.entity.outfitting.OutfittingMock;
import outfitting.view.ViewMock;

class CottageListControllerTest {
	
	private static final Region ANY_REGION = Region.ABITIBI_TEMISCAMINGUE;
	private static final int ANY_ROOM_AMOUNT = 5;

	@Test
	public void requestCottageList_shouldAskCottageListViewToDisplay() {
		final ViewMock A_VIEW = new ViewMock();
		CottageListController cottageListController = new CottageListController(new ControllerOrchestratorMock(), A_VIEW, new RepositoryMock<Cottage>());
		
		cottageListController.requestCottageList();
		
		assertTrue(A_VIEW.displayMethodHasBeenCalled);
	}
	
	@Test
	public void requestCottageDetails_shouldCallGoToCottageDetails() {
		ControllerOrchestratorMock orchestrator = new ControllerOrchestratorMock();
		CottageListController controller = new CottageListController(orchestrator, new ViewMock(), new RepositoryMock<Cottage>());
		final int ANY_ID = 4;
		
		controller.requestCottageDetails(ANY_ID);
		
		assertTrue(orchestrator.goToCottageDetailsHasBeenCalled);
	}

	@Test
	public void getCottages_whenRepositoryIsEmpty_shouldReturnEmptyCollectionOfDTO() {
		CottageListController controller = new CottageListController(new ControllerOrchestratorMock(), new ViewMock(), new RepositoryMock<Cottage>());
		
		Collection<CottageDTOForDisplay> collection = controller.getCottages();
		
		assertTrue(collection.isEmpty());
	}
	
	@Test
	public void getCottages_whenRepositoryHasValues_shouldReturnCollectionOfDTO() {
		RepositoryMock<Cottage> repository = new RepositoryMock<Cottage>();
		Map<Integer, Cottage> list = new HashMap<Integer, Cottage>();
		CottageMock aCottage = new CottageMock();
		aCottage.setOutfitting(new OutfittingMock());
		list.put(1, aCottage);
		repository.setRepo(list);
		CottageListController controller = new CottageListController(new ControllerOrchestratorMock(), new ViewMock(), repository);
		
		Collection<CottageDTOForDisplay> collection = controller.getCottages();
		
		assertFalse(collection.isEmpty());
	}
	
	@Test
	public void notify_shouldCallRefreshMethod() {
		final ViewMock A_VIEW = new ViewMock();
		CottageListController cottageListController = new CottageListController(new ControllerOrchestratorMock(), A_VIEW, new RepositoryMock<Cottage>());
		
		cottageListController.notify(null);
		
		assertTrue(A_VIEW.refreshMethodHasBeenCalled);
	}
	
	@Test
	public void searchCottagesByRegion_whenRepositoryIsEmpty_shouldReturnEmptyCollectionOfDTO() {
		CottageListController controller = new CottageListController(new ControllerOrchestratorMock(), new ViewMock(), new RepositoryMock<Cottage>());
		
		Collection<CottageDTOForDisplay> collection = controller.searchCottagesByRegion(ANY_REGION);
		
		assertTrue(collection.isEmpty());
	}
	
	@Test
	public void searchCottagesByRegion_whenRegionIsNO_REGIONshouldReturnAllCottages() {
		RepositoryMock<Cottage> repository = new RepositoryMock<Cottage>();
		Map<Integer, Cottage> list = new HashMap<Integer, Cottage>();
		CottageMock aCottage = new CottageMock();
		list.put(1, aCottage);
		repository.setRepo(list);
		CottageListController controller = new CottageListController(new ControllerOrchestratorMock(), new ViewMock(), repository);
		
		Collection<CottageDTOForDisplay> collection = controller.searchCottagesByRegion(Region.NO_REGION);
		
		assertFalse(collection.isEmpty());
	}
	
	@Test
	public void searchCottagesByRegion_whenRepositoryHasValues_shouldReturnCollectionOfDTO() {
		RepositoryMock<Cottage> repository = new RepositoryMock<Cottage>();
		Map<Integer, Cottage> list = new HashMap<Integer, Cottage>();
		CottageMock aCottage = new CottageMock();
		list.put(1, aCottage);
		repository.setRepo(list);
		CottageListController controller = new CottageListController(new ControllerOrchestratorMock(), new ViewMock(), repository);
		
		Collection<CottageDTOForDisplay> collection = controller.searchCottagesByRegion(ANY_REGION);
		
		assertFalse(collection.isEmpty());
	}
	
	@Test
	public void searchCottagesByRoomAmount_whenRepositoryIsEmpty_shouldReturnEmptyCollectionOfDTO() {
		CottageListController controller = new CottageListController(new ControllerOrchestratorMock(), new ViewMock(), new RepositoryMock<Cottage>());
		
		Collection<CottageDTOForDisplay> collection = controller.searchCottagesByRoomAmount(ANY_ROOM_AMOUNT);
		
		assertTrue(collection.isEmpty());
	}
	
	@Test
	public void searchCottagesByRoomAmount_whenRepositoryHasValues_shouldReturnCollectionOfDTO() {
		RepositoryMock<Cottage> repository = new RepositoryMock<Cottage>();
		Map<Integer, Cottage> list = new HashMap<Integer, Cottage>();
		CottageMock aCottage = new CottageMock();
		list.put(1, aCottage);
		repository.setRepo(list);
		CottageListController controller = new CottageListController(new ControllerOrchestratorMock(), new ViewMock(), repository);
		
		Collection<CottageDTOForDisplay> collection = controller.searchCottagesByRoomAmount(ANY_ROOM_AMOUNT);
		
		assertFalse(collection.isEmpty());
	}

}
