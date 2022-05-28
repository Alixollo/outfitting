package outfitting.controller;

import outfitting.dto.OutfittingDTOForDisplay;

public interface IOutfittingDetailsController {

	void requestOutfittingDetails();
	OutfittingDTOForDisplay getOutfittingDetails();
	
}
