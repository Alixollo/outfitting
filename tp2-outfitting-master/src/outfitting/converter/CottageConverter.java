package outfitting.converter;

import java.util.ArrayList;
import java.util.Collection;

import outfitting.dto.CottageDTOForCreate;
import outfitting.dto.CottageDTOForDisplay;
import outfitting.model.entity.cottage.Cottage;

public class CottageConverter {
	
	public CottageDTOForDisplay mapToDTO(Cottage cottage) {
		return new CottageDTOForDisplay(cottage.getId(), cottage.getName(), cottage.getNbOfGuests(), cottage.getNbOfBedrooms(), cottage.getPricePerNight(), cottage.getOutfitting().getId(), cottage.getOutfitting().getName(), cottage.getOutfitting().getRegion(), cottage.getOutfitting().getTelephone(), cottage.getOutfitting().getEmail());
	}
	
	public Collection<CottageDTOForDisplay> mapToDTO(Collection<Cottage> cottages) {
		Collection<CottageDTOForDisplay> cottageListForDisplay = new ArrayList<CottageDTOForDisplay>();
		
		for (Cottage cottage : cottages) {
			cottageListForDisplay.add(this.mapToDTO(cottage));
		}
		
		return cottageListForDisplay;
	}
	
	public Cottage mapFromDTO(CottageDTOForCreate cottageDTO) {
		return new Cottage(cottageDTO.NAME, cottageDTO.GUESTS, cottageDTO.BEDROOMS, cottageDTO.PRICE_PER_NIGHT);
	}
	
	
}
