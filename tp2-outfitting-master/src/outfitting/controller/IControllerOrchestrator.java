package outfitting.controller;


public interface IControllerOrchestrator {
	void goToCottageCreate();
	void goToCottageList();
	void goToCottageDetails(int cottageId);
	void goToOutfittingCreate();
	void goToOutfittingList();
	void goToOutfittingDetails(int outfittingId);
}
