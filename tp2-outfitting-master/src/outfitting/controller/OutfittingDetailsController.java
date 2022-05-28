package outfitting.controller;

import outfitting.converter.OutfittingConverter;
import outfitting.dto.OutfittingDTOForDisplay;
import outfitting.exception.InvalidIdException;
import outfitting.model.Repository;
import outfitting.model.entity.outfitting.Outfitting;
import outfitting.view.View;

public class OutfittingDetailsController implements IOutfittingDetailsController {
	
	private Repository<Outfitting> outfittingRepository;
	private IControllerOrchestrator orchestrator;
	private View outfittingDetailsView;
	private int outfittingId;

	public OutfittingDetailsController(IControllerOrchestrator orchestrator, View outfittingDetailsView, Repository<Outfitting> repository, int outfittingId) {
		this.outfittingRepository = repository;
		this.orchestrator = orchestrator;
		this.outfittingDetailsView = outfittingDetailsView;
		this.outfittingId = outfittingId;
	}
	
	@Override
	public void requestOutfittingDetails() {
		this.outfittingDetailsView.display();
	}

	@Override
	public OutfittingDTOForDisplay getOutfittingDetails() {
		Outfitting outfitting = this.outfittingRepository.getById(this.outfittingId);
		validateOutfitting(outfitting);
		return new OutfittingConverter().mapToDTO(outfitting);
	}

	private void validateOutfitting(Outfitting outfitting) {
		if(outfitting == null) {
			throw new InvalidIdException(InvalidIdException.INEXISTANT_ID);
		}		
	}

}
