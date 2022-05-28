package outfitting.model;

public class EntityRepositoryObserverMock implements EntityRepositoryObserver {
	
	public boolean notifyHasBeenCalled = false;

	@Override
	public void notify(UpdateType type) {
		this.notifyHasBeenCalled = true;
	}

	
}
