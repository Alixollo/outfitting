package outfitting.model.entity.outfitting;

import outfitting.model.Region;

public class OutfittingMock extends Outfitting {
	
	private String name;
	private Region region;

	public OutfittingMock() {
		super("La pourvoirie", Region.CENTRE_QUEBEC, "333-333-3333", "coolOutfitting@gmail.com", "", "", "");
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setRegion(Region region) {
		this.region = region;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Region getRegion() {
		return this.region;
	}
}
