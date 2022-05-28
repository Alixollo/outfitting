package outfitting.controller;

import java.util.Collection;

import outfitting.converter.CottageConverter;
import outfitting.dto.CottageDTOForDisplay;
import outfitting.model.EntityRepositoryObserver;
import outfitting.model.Region;
import outfitting.model.Repository;
import outfitting.model.entity.cottage.Cottage;
import outfitting.model.entity.cottage.CottageFilterByRegion;
import outfitting.model.entity.cottage.CottageFilterByRoomAmount;
import outfitting.view.View;

public class CottageListController implements ICottageListController, EntityRepositoryObserver {

	private Repository<Cottage> cottageRepository;
	private IControllerOrchestrator orchestrator;
	private View cottageListView;
	
	public CottageListController(IControllerOrchestrator controllerOrchestrator, View cottageListView, Repository<Cottage> cottageRepository) {
		this.orchestrator = controllerOrchestrator;
		
		this.cottageRepository = cottageRepository;
		this.cottageRepository.addObservers(this);		
		
		this.cottageListView = cottageListView;
	}

	@Override
	public void requestCottageList() {
		this.cottageListView.display();

	}

	@Override
	public Collection<CottageDTOForDisplay> getCottages() {		
		return new CottageConverter().mapToDTO(this.cottageRepository.searchAll());	
	}

	@Override
	public Collection<CottageDTOForDisplay> searchCottagesByRegion(Region region) {
		if(region == Region.NO_REGION) {
			return new CottageConverter().mapToDTO(this.cottageRepository.searchAll());
		}
		return new CottageConverter().mapToDTO(this.cottageRepository.search(new CottageFilterByRegion(region)));
	}

	@Override
	public Collection<CottageDTOForDisplay> searchCottagesByRoomAmount(int roomAmount) {
		return new CottageConverter().mapToDTO(this.cottageRepository.search(new CottageFilterByRoomAmount(roomAmount)));
	}

	@Override
	public void requestCottageDetails(int id) {
		this.orchestrator.goToCottageDetails(id);		
	}

	@Override
	public void notify(UpdateType type) {
		this.cottageListView.refresh();
	}

}
