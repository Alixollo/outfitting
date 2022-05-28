package outfitting.dto;

import outfitting.model.Region;

public class OutfittingDTOForDisplay {

	public final int ID;
	public final String NAME;
	public final Region REGION;
	public final String TELEPHONE;
	public final String EMAIL;
	
	public final String PRIVATE_NAME;
	public final String PRIVATE_TELEPHONE;
	public final String PRIVATE_EMAIL;
	
	public OutfittingDTOForDisplay(int id, String name, Region region, String telephone, String email, String privateName, String privateTelephone, String privateEmail) {
		this.ID = id;
		this.NAME = name;
		this.REGION = region;
		this.TELEPHONE = telephone;
		this.EMAIL = email;
		
		this.PRIVATE_NAME = privateName;
		this.PRIVATE_TELEPHONE = privateTelephone;
		this.PRIVATE_EMAIL = privateEmail;
	}

	@Override
	public String toString() {
		return ID + " - " + NAME;
	}
	
	

}
