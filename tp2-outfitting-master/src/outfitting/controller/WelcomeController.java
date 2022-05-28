package outfitting.controller;

import outfitting.view.View;

public class WelcomeController implements IWelcomeController {

	private IControllerOrchestrator orchestrator;
	private View welcomeView;

	public WelcomeController(IControllerOrchestrator controllerOrchestrator, View welcomeView) {
		this.orchestrator = controllerOrchestrator;
		this.welcomeView = welcomeView;
	}

	@Override
	public void requestWelcome() {
		this.welcomeView.display();
	}

	@Override
	public void requestCottageCreate() {
		this.orchestrator.goToCottageCreate();
	}

	@Override
	public void requestCottageList() {
		this.orchestrator.goToCottageList();		
	}
	
	@Override
	public void requestOutfittingCreate() {
		this.orchestrator.goToOutfittingCreate();
	}

	@Override
	public void requestOutfittingList() {
		this.orchestrator.goToOutfittingList();
	}

}
