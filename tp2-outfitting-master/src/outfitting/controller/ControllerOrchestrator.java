package outfitting.controller;

import outfitting.model.Repository;
import outfitting.model.entity.cottage.Cottage;
import outfitting.model.entity.outfitting.Outfitting;
import outfitting.view.CottageCreateView;
import outfitting.view.CottageDetailsView;
import outfitting.view.CottageListView;
import outfitting.view.OutfittingCreateView;
import outfitting.view.OutfittingDetailsView;
import outfitting.view.OutfittingListView;
import outfitting.view.WelcomeView;

public class ControllerOrchestrator implements IControllerOrchestrator {

	private Repository<Cottage> cottageRepository;
	private Repository<Outfitting> outfittingRepository;
	
	private IWelcomeController welcomeController;
	private WelcomeView welcomeView;
	
	private ICottageCreateController cottageCreateController;
	private CottageCreateView cottageCreateView;
	
	private ICottageListController cottageListController;
	private CottageListView cottageListView;
	
	private IOutfittingCreateController outfittingCreateController;
	private OutfittingCreateView outfittingCreateView;
	
	private IOutfittingListController outfittingListController;
	private OutfittingListView outfittingListView;

	public ControllerOrchestrator(Repository<Cottage> cottageRepository, Repository<Outfitting> outfittingRepository) {
		this.cottageRepository = cottageRepository;
		this.outfittingRepository = outfittingRepository;
		
		this.initWelcome();
		this.initCottageCreate();
		this.initCottageList();
		this.initOutfittingCreate();
		this.initOutfittingList();
		
		this.goToWelcome();
	}

	private void initWelcome() {
		this.welcomeView = new WelcomeView();
		this.welcomeController = new WelcomeController(this, this.welcomeView);
		this.welcomeView.setController(this.welcomeController);
	}
	
	private void initCottageCreate() {
		this.cottageCreateView = new CottageCreateView();

		this.cottageCreateController = new CottageCreateController(this, this.cottageCreateView, this.cottageRepository, this.outfittingRepository);

		this.cottageCreateView.setController(this.cottageCreateController);
	}
	
	private void initCottageList() {
		this.cottageListView = new CottageListView();
		this.cottageListController = new CottageListController(this, this.cottageListView, this.cottageRepository);
		this.cottageListView.setController(this.cottageListController);
		this.cottageListView.refresh();
	}
	
	private void initCottageDetails(int cottageId) {
		CottageDetailsView view = new CottageDetailsView();
		ICottageDetailsController controller = new CottageDetailsController(this, view, this.cottageRepository, cottageId);
		view.setController(controller);
		view.refresh();
		
		controller.requestCottageDetails();
	}
	
	private void initOutfittingCreate() {
		this.outfittingCreateView = new OutfittingCreateView();
		this.outfittingCreateController = new OutfittingCreateController(this, this.outfittingCreateView, this.outfittingRepository);
		this.outfittingCreateView.setController(this.outfittingCreateController);
	}
	
	private void initOutfittingList() {
		this.outfittingListView = new OutfittingListView();
		this.outfittingListController = new OutfittingListController(this, this.outfittingListView, this.outfittingRepository);
		this.outfittingListView.setController(this.outfittingListController);
		this.outfittingListView.refresh();
	}
	
	private void initOutfittingDetails(int outfittingId) {
		OutfittingDetailsView view = new OutfittingDetailsView();
		IOutfittingDetailsController controller = new OutfittingDetailsController(this, view, this.outfittingRepository, outfittingId);
		view.setController(controller);
		view.refresh();
		
		controller.requestOutfittingDetails();
	}
	
	private void goToWelcome() {
		this.welcomeController.requestWelcome();
	}

	@Override
	public void goToCottageCreate() {
		this.cottageCreateController.requestCottageCreate();
	}

	@Override
	public void goToCottageList() {
		this.cottageListController.requestCottageList();		
	}

	@Override
	public void goToOutfittingCreate() {
		this.outfittingCreateController.requestOutfittingCreate();
	}

	@Override
	public void goToOutfittingList() {
		this.outfittingListController.requestOutfittingList();
	}

	@Override
	public void goToOutfittingDetails(int outfittingId) {
		this.initOutfittingDetails(outfittingId);
	}

	@Override
	public void goToCottageDetails(int cottageId) {
		this.initCottageDetails(cottageId);
		
	}

}
