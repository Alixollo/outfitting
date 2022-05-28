package outfitting.model.entity.outfitting;

import outfitting.model.entity.EntityFilter;

public class OutfittingFilterByName implements EntityFilter<Outfitting> {
	
	private String name;

	public OutfittingFilterByName(String name) {
		this.name = name.toLowerCase();
	}
	
	@Override
	public boolean isMatching(Outfitting entity) {
		if(entity.getName().toLowerCase().contains(name)) {
			return true;
		}
		return false;
	}

}
