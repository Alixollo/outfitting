package outfitting.converter;

import java.util.ArrayList;
import java.util.Collection;

import outfitting.dto.OutfittingDTOForCreate;
import outfitting.dto.OutfittingDTOForDisplay;
import outfitting.model.entity.outfitting.Outfitting;

public class OutfittingConverter {

	public OutfittingDTOForDisplay mapToDTO(Outfitting outfitting) {
		
		return new OutfittingDTOForDisplay(outfitting.getId(), outfitting.getName(), outfitting.getRegion(), outfitting.getTelephone(),
				outfitting.getEmail(), outfitting.getPrivateName(), outfitting.getPrivateTelephone(), outfitting.getPrivateEmail());
	}
	
	public Collection<OutfittingDTOForDisplay> mapToDTO(Collection<Outfitting> outfittings) {
		Collection<OutfittingDTOForDisplay> outfittingListForDisplay = new ArrayList<OutfittingDTOForDisplay>();
		
		for (Outfitting outfitting : outfittings) {
			outfittingListForDisplay.add(this.mapToDTO(outfitting));
		}
		
		return outfittingListForDisplay;
	}
	
	public Outfitting mapFromDTO(OutfittingDTOForCreate outfittingDTO) {
		return new Outfitting(outfittingDTO.NAME, outfittingDTO.REGION, outfittingDTO.PHONE_NUMBER, outfittingDTO.EMAIL,
				outfittingDTO.PRIVATE_NAME, outfittingDTO.PRIVATE_TELEPHONE, outfittingDTO.PRIVATE_EMAIL);
	}
}
