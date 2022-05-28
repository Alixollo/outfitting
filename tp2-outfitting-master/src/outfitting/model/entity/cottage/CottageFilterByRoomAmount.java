package outfitting.model.entity.cottage;

import outfitting.model.entity.EntityFilter;

public class CottageFilterByRoomAmount implements EntityFilter<Cottage> {
	
	private int roomAmount;

	public CottageFilterByRoomAmount(int roomAmount) {
		this.roomAmount = roomAmount;
	}
	
	@Override
	public boolean isMatching(Cottage entity) {
		if(entity.getNbOfBedrooms() == this.roomAmount) {
			return true;
		}
		return false;
	}

}
