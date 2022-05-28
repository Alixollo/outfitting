package outfitting.model.entity.cottage;

import outfitting.model.Region;
import outfitting.model.entity.outfitting.Outfitting;
import outfitting.model.entity.outfitting.OutfittingMock;

public class CottageMock extends Cottage {
	
	private OutfittingMock outfitting = new OutfittingMock();
	private int roomAmount = -1;

	public CottageMock() {
		super("Chalet", 1, 0, 0);
	}
	
	public void setRegion(Region region) {
		this.outfitting.setRegion(region);
	}
	
	public void setRoomAmount(int roomAmount) {
		this.roomAmount = roomAmount;
	}
	
	@Override
	public int getNbOfBedrooms() {
		return this.roomAmount;
	}
	
	@Override
	public Outfitting getOutfitting() {
		return this.outfitting;
	}
	
}
