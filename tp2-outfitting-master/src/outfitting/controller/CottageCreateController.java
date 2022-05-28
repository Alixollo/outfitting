package outfitting.controller;

import java.util.Collection;

import outfitting.converter.CottageConverter;
import outfitting.converter.OutfittingConverter;
import outfitting.dto.CottageDTOForCreate;
import outfitting.dto.OutfittingDTOForDisplay;
import outfitting.exception.InvalidCottageException;
import outfitting.model.EntityRepositoryObserver;
import outfitting.model.Repository;
import outfitting.model.entity.cottage.Cottage;
import outfitting.model.entity.outfitting.Outfitting;
import outfitting.view.View;

public class CottageCreateController implements ICottageCreateController, EntityRepositoryObserver {
	
	private Repository<Cottage> cottageRepository;
	private Repository<Outfitting> outfittingRepository;
	private IControllerOrchestrator orchestrator;
	private View cottageCreateView;

	public CottageCreateController(IControllerOrchestrator controllerOrchestrator, View cottageCreateView, Repository<Cottage> cottageRepository, Repository<Outfitting> outfittingRepository) {
		this.cottageRepository = cottageRepository;
		this.outfittingRepository = outfittingRepository;
		this.outfittingRepository.addObservers(this);
		this.orchestrator = controllerOrchestrator;
		this.cottageCreateView = cottageCreateView;
	}

	@Override
	public void requestCottageCreate() {
		this.cottageCreateView.display();
	}
	
	@Override
	public Collection<OutfittingDTOForDisplay> getOutfittings() {		
		return new OutfittingConverter().mapToDTO(this.outfittingRepository.searchAll());	
	}
	
	@Override
	public void add(CottageDTOForCreate cottageToCreate) {
		Outfitting outfitting = this.outfittingRepository.getById(cottageToCreate.OUTFITTING_ID);
		if(outfitting == null) {
			throw new InvalidCottageException(InvalidCottageException.INVALID_OUTFITTING_ID);
		}
		Cottage cottage = new CottageConverter().mapFromDTO(cottageToCreate);
		cottage.setOutfitting(outfitting);
		this.cottageRepository.add(cottage);
	}

	@Override
	public void notify(UpdateType type) {
		this.cottageCreateView.refresh();
	}

}
