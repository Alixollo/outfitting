package outfitting.model;

import outfitting.model.entity.EntityFilter;
import outfitting.model.entity.EntityMock;

public class EntityFilterMock implements EntityFilter<EntityMock> {

	@Override
	public boolean isMatching(EntityMock entity) {
		return true;
	}

}
