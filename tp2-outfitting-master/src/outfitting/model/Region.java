package outfitting.model;

public enum Region {

	NO_REGION("---------"),
	BAS_SAINT_LAURENT("Bas-Saint-Laurent"),
	SAGUENAY("Saguenay–Lac-Saint-Jean"),
	CAPITALE_NATIONALE("Capitale-Nationale"),
	MAURICIE("Mauricie"),
	ESTRIE("Estrie"),
	MONTREAL("Montréal"),
	OUTAOUAIS("Outaouais"),
	ABITIBI_TEMISCAMINGUE("Abitibi-Témiscamingue"),
	COTE_NORD("Côte-Nord"),
	NORD_QUEBEC("Nord-du-Québec"),
	GASPESIE("Gaspésie–Îles-de-la-Madeleine"),
	CHAUDIERE_APPALACHES("Chaudière-Appalaches"),
	LAVAL("Laval"),
	LANAUDIERE("Lanaudière"),
	LAURENTIDES("Laurentides"),
	MONTEREGIE("Montérégie"),
	CENTRE_QUEBEC("Centre-du-Québec");
	
	public final String NAME;

    Region(String name) {
        this.NAME = name;
    }
    
    @Override
    public String toString() {
    	return this.NAME;
    }

}
