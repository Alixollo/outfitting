package outfitting.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import outfitting.exception.IllegalSearchException;
import outfitting.exception.InvalidIdException;
import outfitting.model.EntityRepositoryObserver.UpdateType;
import outfitting.model.entity.Entity;
import outfitting.model.entity.EntityFilter;

public class MemoryRepository<T extends Entity> implements Repository<T> {
	
	private static int nextId = 1;
	
	private Map<Integer, T> entities;
	private Set<EntityRepositoryObserver> observers;

	public MemoryRepository() {
		this.entities = new HashMap<Integer, T>();
		this.observers = new HashSet<EntityRepositoryObserver>();
	}

	@Override
	public void add(T entity) {
		this.setEntityId(entity);
		this.entities.put(entity.getId(), entity);
		this.notifyObservers(UpdateType.ADDED);
	}
	
	@Override
	public void remove(int id) {
		if(!this.entities.containsKey(id)) {
			throw new InvalidIdException(InvalidIdException.INEXISTANT_ID);
		}
		this.entities.remove(id);
		this.notifyObservers(UpdateType.REMOVED);
	}
	
	private void setEntityId(T entity) {
		entity.setId(nextId);
		nextId++;
	}

	private void notifyObservers(EntityRepositoryObserver.UpdateType type) {
		this.observers.forEach(observer -> observer.notify(type));
	}

	@Override
	public Collection<T> searchAll() {
		return this.entities.values();
	}

	@Override
	public int size() {
		return this.entities.size();
	}

	@Override
	public T getById(int id) {
		if(!this.entities.containsKey(id)) {
			throw new InvalidIdException(InvalidIdException.INEXISTANT_ID);
		}
		return this.entities.get(id);
	}

	@Override
	public void addObservers(EntityRepositoryObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObservers(EntityRepositoryObserver observer) {
		this.observers.remove(observer);
	}

	@Override
	public Collection<T> search(EntityFilter<T> filter) {
		this.validateSearch(filter);
		Collection<T> searchResults = new HashSet<T>();
		
		this.entities.forEach((id, entity) -> {
			if(filter.isMatching(entity)) {
				searchResults.add(entity);
			}
		});
		
		return searchResults;
	}

	private void validateSearch(EntityFilter<T> filter) {
		if(filter == null) {
			throw new IllegalSearchException();
		}		
	}

}
