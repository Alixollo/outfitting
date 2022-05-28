package outfitting.dto;

import java.util.Comparator;

public class CottageDTOForDisplayComparatorByGuestAmount implements Comparator<CottageDTOForDisplay> {

	@Override
	public int compare(CottageDTOForDisplay o1, CottageDTOForDisplay o2) {
		if(o1.GUESTS > o2.GUESTS) {
			return 1;
		}
		else if(o1.GUESTS < o2.GUESTS) {
			return -1;
		}
		return 0;
	}

}
