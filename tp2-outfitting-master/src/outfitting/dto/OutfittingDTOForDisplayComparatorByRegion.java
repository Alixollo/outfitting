package outfitting.dto;

import java.util.Comparator;

public class OutfittingDTOForDisplayComparatorByRegion implements Comparator<OutfittingDTOForDisplay> {

	@Override
	public int compare(OutfittingDTOForDisplay o1, OutfittingDTOForDisplay o2) {
		return o1.REGION.NAME.compareToIgnoreCase(o2.REGION.NAME);
	}
	
}
