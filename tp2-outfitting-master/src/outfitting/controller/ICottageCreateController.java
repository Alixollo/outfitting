package outfitting.controller;

import java.util.Collection;

import outfitting.dto.CottageDTOForCreate;
import outfitting.dto.OutfittingDTOForDisplay;

public interface ICottageCreateController {

	void requestCottageCreate();
	void add(CottageDTOForCreate cottageToCreate);
	Collection<OutfittingDTOForDisplay> getOutfittings();
}
