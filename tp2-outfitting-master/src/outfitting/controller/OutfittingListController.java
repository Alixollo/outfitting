package outfitting.controller;

import java.util.Collection;

import outfitting.converter.OutfittingConverter;
import outfitting.dto.OutfittingDTOForDisplay;
import outfitting.model.EntityRepositoryObserver;
import outfitting.model.Repository;
import outfitting.model.entity.outfitting.Outfitting;
import outfitting.model.entity.outfitting.OutfittingFilterByName;
import outfitting.view.View;

public class OutfittingListController implements IOutfittingListController, EntityRepositoryObserver {
	
	private Repository<Outfitting> outfittingRepository;
	private IControllerOrchestrator orchestrator;
	private View outfittingListView;
	
	public OutfittingListController(IControllerOrchestrator orchestrator, View outfittingListView, Repository<Outfitting> repository) {
		this.outfittingRepository = repository;
		this.outfittingRepository.addObservers(this);
		this.orchestrator = orchestrator;
		this.outfittingListView = outfittingListView;
	}

	@Override
	public void requestOutfittingList() {
		this.outfittingListView.display();
	}

	@Override
	public Collection<OutfittingDTOForDisplay> getOutfittingList() {
		return new OutfittingConverter().mapToDTO(this.outfittingRepository.searchAll());
	}
	
	@Override
	public Collection<OutfittingDTOForDisplay> searchOutfitting(String name){
		return new OutfittingConverter().mapToDTO(this.outfittingRepository.search(new OutfittingFilterByName(name)));
	}

	@Override
	public void requestOutfittingDetails(int id) {
		this.orchestrator.goToOutfittingDetails(id);
	}

	@Override
	public void notify(UpdateType type) {
		this.outfittingListView.refresh();
	}
	
}
