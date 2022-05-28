package outfitting.model;

public enum Region {

	NO_REGION("---------"),
	BAS_SAINT_LAURENT("Bas-Saint-Laurent"),
	SAGUENAY("Saguenay�Lac-Saint-Jean"),
	CAPITALE_NATIONALE("Capitale-Nationale"),
	MAURICIE("Mauricie"),
	ESTRIE("Estrie"),
	MONTREAL("Montr�al"),
	OUTAOUAIS("Outaouais"),
	ABITIBI_TEMISCAMINGUE("Abitibi-T�miscamingue"),
	COTE_NORD("C�te-Nord"),
	NORD_QUEBEC("Nord-du-Qu�bec"),
	GASPESIE("Gasp�sie��les-de-la-Madeleine"),
	CHAUDIERE_APPALACHES("Chaudi�re-Appalaches"),
	LAVAL("Laval"),
	LANAUDIERE("Lanaudi�re"),
	LAURENTIDES("Laurentides"),
	MONTEREGIE("Mont�r�gie"),
	CENTRE_QUEBEC("Centre-du-Qu�bec");
	
	public final String NAME;

    Region(String name) {
        this.NAME = name;
    }
    
    @Override
    public String toString() {
    	return this.NAME;
    }

}
