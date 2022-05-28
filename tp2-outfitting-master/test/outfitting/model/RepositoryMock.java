package outfitting.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import outfitting.model.entity.Entity;
import outfitting.model.entity.EntityFilter;

public class RepositoryMock<T extends Entity> implements Repository<T> {
	
	public boolean addMethodHasBeenCalled = false;
	public boolean removeMethodHasBeenCalled = false;
	public Map<Integer, T> repo = new HashMap<Integer, T>();
	public T addedEntity;

	@Override
	public void add(T newEntity) {
		addedEntity = newEntity;
		addMethodHasBeenCalled = true;
	}

	@Override
	public Collection<T> searchAll() {
		return this.repo.values();
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public T getById(int id) {
		return this.repo.get(id);
	}
	
	public void setRepo(Map<Integer, T> entityList) {
		this.repo = entityList;
	}

	@Override
	public void addObservers(EntityRepositoryObserver observer) {
		
	}

	@Override
	public void removeObservers(EntityRepositoryObserver observer) {
		
	}

	@Override
	public Collection<T> search(EntityFilter<T> filter) {
		return this.repo.values();
	}

	@Override
	public void remove(int id) {
		removeMethodHasBeenCalled = true;
	}

}
