package outfitting.model;

public interface EntityRepositoryObserver {
	
	public static enum UpdateType {ADDED, REMOVED};
	
	void notify(UpdateType type);
}
