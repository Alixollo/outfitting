package outfitting.dto;

import java.util.Comparator;

public class OutfittingDTOForDisplayComparatorByName implements Comparator<OutfittingDTOForDisplay> {

	@Override
	public int compare(OutfittingDTOForDisplay o1, OutfittingDTOForDisplay o2) {
		return o1.NAME.compareToIgnoreCase(o2.NAME);
	}

}
