package outfitting.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import outfitting.dto.CottageDTOForCreate;
import outfitting.model.RepositoryMock;
import outfitting.exception.InvalidCottageException;
import outfitting.model.entity.cottage.Cottage;
import outfitting.model.entity.outfitting.Outfitting;
import outfitting.model.entity.outfitting.OutfittingMock;
import outfitting.view.ViewMock;

class CottageCreateControllerTest {

	@Test
	public void requestCottageCreate_shouldAskCottageCreateViewToDisplay() {
		final ViewMock A_VIEW = new ViewMock();
		CottageCreateController cottageCreateController = new CottageCreateController(new ControllerOrchestratorMock(), A_VIEW, new RepositoryMock<Cottage>(), new RepositoryMock<Outfitting>());
		
		cottageCreateController.requestCottageCreate();
		
		assertTrue(A_VIEW.displayMethodHasBeenCalled);
	}
	
	@Test
	public void add_shouldCall_addMethodOfRepository() {
		final OutfittingMock ANY_OUTFITTING = new OutfittingMock();
		RepositoryMock<Cottage> cottageRepository = new RepositoryMock<Cottage>();
		RepositoryMock<Outfitting> outfittingRepository = new RepositoryMock<Outfitting>();
		Map<Integer, Outfitting> repo = new HashMap<Integer, Outfitting>();
		repo.put(ANY_OUTFITTING.getId(), ANY_OUTFITTING);
		outfittingRepository.setRepo(repo);
		CottageCreateController controller = new CottageCreateController(new ControllerOrchestratorMock(), new ViewMock(), cottageRepository, outfittingRepository);
		
		controller.add(new CottageDTOForCreate("Le chalet", 5, 3, 40f, ANY_OUTFITTING.getId()));
		
		assertTrue(cottageRepository.addMethodHasBeenCalled);
	}
	
	@Test
	public void add_whenOutfittingIdIsNotInRepository_shouldRaiseException() {
		RepositoryMock<Cottage> cottageRepository = new RepositoryMock<Cottage>();
		CottageCreateController controller = new CottageCreateController(new ControllerOrchestratorMock(), new ViewMock(), cottageRepository, new RepositoryMock<Outfitting>());
		
		InvalidCottageException exception = assertThrows(InvalidCottageException.class,
				() -> controller.add(new CottageDTOForCreate("Le chalet", 5, 3, 40f, -1)));

		assertEquals(InvalidCottageException.INVALID_OUTFITTING_ID, exception.getMessage());
	}
	
	@Test
	public void getOutfittings_shouldGetAllDataFromOutfittingRepository() {
		final OutfittingMock ANY_OUTFITTING = new OutfittingMock();
		RepositoryMock<Cottage> cottageRepository = new RepositoryMock<Cottage>();
		RepositoryMock<Outfitting> outfittingRepository = new RepositoryMock<Outfitting>();
		outfittingRepository.add(ANY_OUTFITTING);
		CottageCreateController controller = new CottageCreateController(new ControllerOrchestratorMock(), new ViewMock(), cottageRepository, outfittingRepository);
		
		assertEquals(outfittingRepository.repo.size(), controller.getOutfittings().size());
	}
	
	@Test
	public void notify_shouldCallRefreshMethod() {
		final ViewMock A_VIEW = new ViewMock();
		CottageCreateController cottageCreateController = new CottageCreateController(new ControllerOrchestratorMock(), A_VIEW, new RepositoryMock<Cottage>(), new RepositoryMock<Outfitting>());
		
		cottageCreateController.notify(null);
		
		assertTrue(A_VIEW.refreshMethodHasBeenCalled);
	}

}
