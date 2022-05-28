package outfitting.controller;

import outfitting.converter.OutfittingConverter;
import outfitting.dto.OutfittingDTOForCreate;
import outfitting.model.Repository;
import outfitting.model.entity.outfitting.Outfitting;
import outfitting.view.View;

public class OutfittingCreateController implements IOutfittingCreateController{
	
	private Repository<Outfitting> outfittingRepository;
	private IControllerOrchestrator orchestrator;
	private View outfittingCreateView;

	public OutfittingCreateController(IControllerOrchestrator controllerOrchestrator, View outfittingCreateView, Repository<Outfitting> outfittingRepository) {
		this.outfittingRepository = outfittingRepository;
		this.orchestrator = controllerOrchestrator;
		this.outfittingCreateView = outfittingCreateView;
	}
	@Override
	public void requestOutfittingCreate() {
		this.outfittingCreateView.display();
		
	}

	@Override
	public void add(OutfittingDTOForCreate outfittingToCreate) {
		Outfitting outfitting = new OutfittingConverter().mapFromDTO(outfittingToCreate);
		this.outfittingRepository.add(outfitting);
	}

}
