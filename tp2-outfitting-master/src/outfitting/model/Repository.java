package outfitting.model;

import java.util.Collection;

import outfitting.model.entity.Entity;
import outfitting.model.entity.EntityFilter;

public interface Repository<T extends Entity> {

	void add(T entity);
	void remove(int id);
	Collection<T> searchAll();
	int size();
	T getById(int id);
	Collection<T> search(EntityFilter<T> filter);

	void addObservers(EntityRepositoryObserver observer);
	void removeObservers(EntityRepositoryObserver observer);
}
