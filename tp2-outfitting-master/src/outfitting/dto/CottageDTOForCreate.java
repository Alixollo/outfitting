package outfitting.dto;

public class CottageDTOForCreate {

	public final String NAME;
	public final int GUESTS;
	public final int BEDROOMS;
	public final float PRICE_PER_NIGHT;
	public final int OUTFITTING_ID;

	public CottageDTOForCreate(String name, int nbGuests, int nbOfBedrooms, float pricePerNight, int outfittingId) {
		this.NAME = name;
		this.GUESTS = nbGuests;
		this.BEDROOMS = nbOfBedrooms;
		this.PRICE_PER_NIGHT = pricePerNight;
		this.OUTFITTING_ID = outfittingId;
	}

}
