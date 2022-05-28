package outfitting.dto;

import outfitting.model.Region;

public class OutfittingDTOForCreate {

	public final String NAME;
	public final Region REGION;
	public final String PHONE_NUMBER;
	public final String EMAIL;
	
	public final String PRIVATE_NAME;
	public final String PRIVATE_TELEPHONE;
	public final String PRIVATE_EMAIL;

	public OutfittingDTOForCreate(String name, Region region, String phoneNumber, String email,
			String privateName, String privateTelephone, String privateEmail) {
		this.NAME = name;
		this.REGION = region;
		this.PHONE_NUMBER = phoneNumber;
		this.EMAIL = email;

		this.PRIVATE_NAME = privateName;
		this.PRIVATE_TELEPHONE = privateTelephone;
		this.PRIVATE_EMAIL = privateEmail;
	}
}
