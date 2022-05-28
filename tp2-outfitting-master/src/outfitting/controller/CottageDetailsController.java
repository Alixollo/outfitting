package outfitting.controller;

import outfitting.converter.CottageConverter;
import outfitting.dto.CottageDTOForDisplay;
import outfitting.exception.InvalidIdException;
import outfitting.model.EntityRepositoryObserver;
import outfitting.model.Repository;
import outfitting.model.entity.cottage.Cottage;
import outfitting.view.View;

public class CottageDetailsController implements ICottageDetailsController, EntityRepositoryObserver{

	private Repository<Cottage> cottageRepository;
	private IControllerOrchestrator orchestrator;
	private View cottageDetailsView;
	private int cottageId;

	public CottageDetailsController(IControllerOrchestrator orchestrator, View cottageDetailsView, Repository<Cottage> repository, int cottageId) {
		this.cottageRepository = repository;
		this.orchestrator = orchestrator;
		this.cottageDetailsView = cottageDetailsView;
		this.cottageId = cottageId;
		
		this.cottageRepository.addObservers(this);
	}

	@Override
	public void requestCottageDetails() {
		this.cottageDetailsView.display();
	}

	@Override
	public CottageDTOForDisplay getCottageDetails() throws InvalidIdException {
		try {
			return new CottageConverter().mapToDTO(this.cottageRepository.getById(this.cottageId));
		}
		catch(RuntimeException e) {
			throw new InvalidIdException(InvalidIdException.INEXISTANT_ID);			
		}
		
	}

	@Override
	public void removeCottage() {
		this.cottageRepository.remove(cottageId);
	}
	
	@Override
	public void notify(UpdateType type) {
		if(type == UpdateType.REMOVED) {
			this.cottageDetailsView.refresh();
		}
	}
	
}
