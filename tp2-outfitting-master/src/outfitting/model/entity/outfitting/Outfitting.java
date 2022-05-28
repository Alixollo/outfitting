package outfitting.model.entity.outfitting;

import outfitting.exception.InvalidOutfittingException;
import outfitting.model.Region;
import outfitting.model.entity.Entity;
import util.FormatValidationUtil;

public class Outfitting implements Entity {
	
	private int id;
	private String name;
	private Region region;
	private String telephone;
	private String email;
	
	private String privateName;
	private String privateTelephone;
	private String privateEmail;
	
	public Outfitting(String name, Region region, String telephone, String email, String privateName, String privateTelephone, String privateEmail) {
		this.validate(name, region, telephone, email, privateName, privateTelephone, privateEmail);
		this.name = name;
		this.region = region;
		this.telephone = telephone;
		this.email = email;
		
		this.privateName = privateName;
		this.privateTelephone = privateTelephone;
		this.privateEmail = privateEmail;
	}
	
	private void validate(String name, Region region, String telephone, String email, String privateName, String privateTelephone, String privateEmail) {
		this.validateName(name);
		this.validateRegion(region);
		this.validateTelephone(telephone);
		this.validateEmail(email);
		this.validatePrivateInfo(privateName, privateTelephone, privateEmail);
	}

	private void validatePrivateInfo(String privateName, String privateTelephone, String privateEmail) {
		if(!privateName.isEmpty() || !privateTelephone.isEmpty() || !privateEmail.isEmpty()) {
			this.validateContactName(privateName);
			this.validateContactTelephone(privateTelephone);
			this.validateContactEmail(privateEmail);
		}		
	}

	private void validateContactEmail(String email) {
		if(FormatValidationUtil.isNullOrBlank(email)) {
			throw new InvalidOutfittingException(InvalidOutfittingException.INVALID_CONTACT_EMAIL);
		}
		if(!FormatValidationUtil.isValidEmailFormat(email)) {
			throw new InvalidOutfittingException(InvalidOutfittingException.INVALID_CONTACT_EMAIL_FORMAT);
		}		
	}

	private void validateContactTelephone(String telephone) {
		if(FormatValidationUtil.isNullOrBlank(telephone)) {
			throw new InvalidOutfittingException(InvalidOutfittingException.INVALID_CONTACT_TELEPHONE);
		}
		if(!FormatValidationUtil.isValidTelephoneFormat(telephone)) {
			throw new InvalidOutfittingException(InvalidOutfittingException.INVALID_CONTACT_TELEPHONE_FORMAT);
		}		
	}

	private void validateContactName(String name) {
		if(FormatValidationUtil.isNullOrBlank(name)) {
			throw new InvalidOutfittingException(InvalidOutfittingException.INVALID_CONTACT_NAME);
		}		
	}

	private void validateEmail(String email) {
		if(FormatValidationUtil.isNullOrBlank(email)) {
			throw new InvalidOutfittingException(InvalidOutfittingException.INVALID_EMAIL);
		}
		if(!FormatValidationUtil.isValidEmailFormat(email)) {
			throw new InvalidOutfittingException(InvalidOutfittingException.INVALID_EMAIL_FORMAT);
		}		
	}

	private void validateTelephone(String telephone) {
		if(FormatValidationUtil.isNullOrBlank(telephone)) {
			throw new InvalidOutfittingException(InvalidOutfittingException.INVALID_TELEPHONE);
		}
		if(!FormatValidationUtil.isValidTelephoneFormat(telephone)) {
			throw new InvalidOutfittingException(InvalidOutfittingException.INVALID_TELEPHONE_FORMAT);
		}
	}

	private void validateRegion(Region region) {
		if(region == null || region == Region.NO_REGION) {
			throw new InvalidOutfittingException(InvalidOutfittingException.INVALID_REGION);
		}		
	}

	private void validateName(String name) {
		if(FormatValidationUtil.isNullOrBlank(name)) {
			throw new InvalidOutfittingException(InvalidOutfittingException.INVALID_NAME);
		}		
	}

	@Override
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	public Region getRegion() {
		return this.region;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPrivateName() {
		return this.privateName;
	}
	
	public String getPrivateTelephone() {
		return this.privateTelephone;
	}

	public String getPrivateEmail() {
		return this.privateEmail;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}
}
