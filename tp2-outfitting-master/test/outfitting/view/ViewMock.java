package outfitting.view;

public class ViewMock implements View {  //ViewSpy

	public boolean displayMethodHasBeenCalled = false;
	public boolean refreshMethodHasBeenCalled = false;

	@Override
	public void display() {
		this.displayMethodHasBeenCalled = true;
	}
	
	@Override
	public void refresh() {
		this.refreshMethodHasBeenCalled = true;
	}

}
