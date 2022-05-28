package outfitting.dto;

import outfitting.model.Region;

public class CottageDTOForDisplay {

	public final int ID;
	public final String NAME;
	public final int GUESTS;
	public final int BEDROOMS;
	public final float PRICE_PER_NIGHT;

	public final int OUTFITTING_ID;
	public final String OUTFITTING_NAME;
	public final Region OUTFITTING_REGION;
	public final String OUTFITTING_TELEPHONE;
	public final String OUTFITTING_EMAIL;

	public CottageDTOForDisplay(int id, String name, int nbGuests, int nbOfBedrooms, float pricePerNight, int outfittingId, String outfittingName, Region outfittingRegion, String outfittingTelephone, String outfittingEmail) {
		this.ID = id;
		this.NAME = name;
		this.GUESTS = nbGuests;
		this.BEDROOMS = nbOfBedrooms;
		this.PRICE_PER_NIGHT = pricePerNight;
		this.OUTFITTING_ID = outfittingId;
		this.OUTFITTING_NAME = outfittingName;
		this.OUTFITTING_REGION = outfittingRegion;
		this.OUTFITTING_TELEPHONE = outfittingTelephone;
		this.OUTFITTING_EMAIL = outfittingEmail;
	}
}
