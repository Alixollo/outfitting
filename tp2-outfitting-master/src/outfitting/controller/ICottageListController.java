package outfitting.controller;

import java.util.Collection;

import outfitting.dto.CottageDTOForDisplay;
import outfitting.model.Region;

public interface ICottageListController {
	void requestCottageList();
	Collection<CottageDTOForDisplay> getCottages();
	Collection<CottageDTOForDisplay> searchCottagesByRegion(Region region);
	Collection<CottageDTOForDisplay> searchCottagesByRoomAmount(int roomAmount);
	void requestCottageDetails(int id);
}