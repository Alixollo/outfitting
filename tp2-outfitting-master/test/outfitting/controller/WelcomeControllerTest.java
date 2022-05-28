package outfitting.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import outfitting.view.ViewMock;

public class WelcomeControllerTest {

	@Test
	public void requestWelcome_shouldAskWelcomeViewToDisplay() {
		final ViewMock A_VIEW = new ViewMock();
		WelcomeController welcomeControler = new WelcomeController(new ControllerOrchestratorMock(), A_VIEW);
		
		welcomeControler.requestWelcome();
		
		assertTrue(A_VIEW.displayMethodHasBeenCalled);
	}
	
	@Test
	public void requestCottageCreate_shouldFowardRequestToOrchestrator() {
		final ControllerOrchestratorMock AN_ORCHESTRATOR = new ControllerOrchestratorMock();
		WelcomeController welcomeControler = new WelcomeController(AN_ORCHESTRATOR, new ViewMock());
		
		welcomeControler.requestCottageCreate();
		
		assertTrue(AN_ORCHESTRATOR.goToCottageCreateHasBeenCalled);
	}
	
	@Test
	public void requestCottageList_shouldFowardRequestToOrchestrator() {
		final ControllerOrchestratorMock AN_ORCHESTRATOR = new ControllerOrchestratorMock();
		WelcomeController welcomeControler = new WelcomeController(AN_ORCHESTRATOR, new ViewMock());
		
		welcomeControler.requestCottageList();
		
		assertTrue(AN_ORCHESTRATOR.goToCottageListHasBeenCalled);
	}
	
	@Test
	public void requestOutfittingCreate_shouldFowardRequestToOrchestrator() {
		final ControllerOrchestratorMock AN_ORCHESTRATOR = new ControllerOrchestratorMock();
		WelcomeController welcomeControler = new WelcomeController(AN_ORCHESTRATOR, new ViewMock());
		
		welcomeControler.requestOutfittingCreate();
		
		assertTrue(AN_ORCHESTRATOR.goToOutfittingCreateHasBeenCalled);
	}
	
	@Test
	public void requestOutfittingList_shouldFowardRequestToOrchestrator() {
		final ControllerOrchestratorMock AN_ORCHESTRATOR = new ControllerOrchestratorMock();
		WelcomeController welcomeControler = new WelcomeController(AN_ORCHESTRATOR, new ViewMock());
		
		welcomeControler.requestOutfittingList();
		
		assertTrue(AN_ORCHESTRATOR.goToOutfittingListHasBeenCalled);
	}

}
