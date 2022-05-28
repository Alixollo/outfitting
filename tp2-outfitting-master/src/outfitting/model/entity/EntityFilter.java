package outfitting.model.entity;

public interface EntityFilter<T extends Entity> {

	boolean isMatching(T entity);
}
