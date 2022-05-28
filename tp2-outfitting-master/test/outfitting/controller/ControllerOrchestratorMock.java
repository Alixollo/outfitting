package outfitting.controller;

public class ControllerOrchestratorMock implements IControllerOrchestrator {

	public boolean goToCottageCreateHasBeenCalled = false;
	public boolean goToCottageListHasBeenCalled = false;
	public boolean goToOutfittingCreateHasBeenCalled = false;
	public boolean goToOutfittingListHasBeenCalled = false;
	public boolean goToOutfittingDetailsHasBeenCalled = false;
	public boolean goToCottageDetailsHasBeenCalled = false;
	
	@Override
	public void goToCottageCreate() {
		this.goToCottageCreateHasBeenCalled = true;		
	}

	@Override
	public void goToCottageList() {
		this.goToCottageListHasBeenCalled = true;		
	}

	@Override
	public void goToOutfittingCreate() {
		this.goToOutfittingCreateHasBeenCalled = true;
		
	}

	@Override
	public void goToOutfittingList() {
		this.goToOutfittingListHasBeenCalled = true;
	}

	@Override
	public void goToOutfittingDetails(int outfittingId) {
		this.goToOutfittingDetailsHasBeenCalled = true;
	}

	@Override
	public void goToCottageDetails(int cottageId) {
		this.goToCottageDetailsHasBeenCalled = true;		
	}
}
