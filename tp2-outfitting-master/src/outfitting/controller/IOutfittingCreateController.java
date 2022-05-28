package outfitting.controller;

import outfitting.dto.OutfittingDTOForCreate;

public interface IOutfittingCreateController {
	void requestOutfittingCreate();
	void add(OutfittingDTOForCreate outfittingToCreate);
}
