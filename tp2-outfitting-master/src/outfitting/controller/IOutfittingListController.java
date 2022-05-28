package outfitting.controller;

import java.util.Collection;

import outfitting.dto.OutfittingDTOForDisplay;

public interface IOutfittingListController {
	
	void requestOutfittingList();
	Collection<OutfittingDTOForDisplay> getOutfittingList();
	Collection<OutfittingDTOForDisplay> searchOutfitting(String name);
	void requestOutfittingDetails(int id);
}
