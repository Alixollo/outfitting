package outfitting.model.entity.cottage;

import outfitting.exception.InvalidCottageException;
import outfitting.model.entity.Entity;
import outfitting.model.entity.outfitting.Outfitting;
import util.FormatValidationUtil;

public class Cottage implements Entity {

	private int id;
	private String name;
	private int maxOfGuests;
	private int nbOfBedrooms;
	private float pricePerNight;
	private Outfitting outfitting;


	public Cottage(String name, int maxOfGuest, int nbOfBedrooms, float pricePerNight) {
		this.validate(name, maxOfGuest, nbOfBedrooms, pricePerNight);
		this.name = name;
		this.maxOfGuests = maxOfGuest;
		this.nbOfBedrooms = nbOfBedrooms;
		this.pricePerNight = pricePerNight;
	}
	
	private void validate(String name, int maxOfGuest, int nbOfBedrooms, float pricePerNight) {
		this.validateName(name);
		this.validateNbGuests(maxOfGuest);
		this.validateNbBedrooms(nbOfBedrooms);
		this.validatePricePerNight(pricePerNight);
	}

	private void validatePricePerNight(float pricePerNight) {
		if(pricePerNight < 0) {
			throw new InvalidCottageException(InvalidCottageException.INVALID_PRICE_PER_NIGHT);
		}
		
	}

	private void validateNbBedrooms(int nbOfBedrooms) {
		if(nbOfBedrooms < 0) {
			throw new InvalidCottageException(InvalidCottageException.INVALID_NUMBER_OF_BEDROOMS);
		}
	}

	private void validateNbGuests(int maxOfGuest) {
		if(maxOfGuest <= 0) {
			throw new InvalidCottageException(InvalidCottageException.INVALID_NUMBER_OF_GUESTS);
		}
	}
	
	private void validateName(String name) {
		if(FormatValidationUtil.isNullOrBlank(name)) {
			throw new InvalidCottageException(InvalidCottageException.INVALID_NAME);
		}
	}
	
	@Override
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public int getNbOfGuests() {
		return this.maxOfGuests;
	}

	public int getNbOfBedrooms() {
		return this.nbOfBedrooms;
	}

	public float getPricePerNight() {
		return this.pricePerNight;
	}

	public Outfitting getOutfitting() {
		return this.outfitting;
	}
	
	public void setOutfitting(Outfitting outfitting) {
		this.outfitting = outfitting;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}
}