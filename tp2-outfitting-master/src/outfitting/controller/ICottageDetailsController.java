package outfitting.controller;

import outfitting.dto.CottageDTOForDisplay;

public interface ICottageDetailsController {

	void requestCottageDetails();
	CottageDTOForDisplay getCottageDetails();
	void removeCottage();
}
