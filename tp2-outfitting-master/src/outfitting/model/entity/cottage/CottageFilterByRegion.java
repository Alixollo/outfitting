package outfitting.model.entity.cottage;

import outfitting.model.Region;
import outfitting.model.entity.EntityFilter;

public class CottageFilterByRegion implements EntityFilter<Cottage> {
	
	private Region region;
	
	public CottageFilterByRegion(Region region) {
		this.region = region;
	}

	@Override
	public boolean isMatching(Cottage entity) {
		if(entity.getOutfitting().getRegion() == this.region) {
			return true;
		}
		return false;
	}

}
