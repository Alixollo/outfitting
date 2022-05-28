package outfitting.view;

import javax.swing.JButton;

public class IdButton extends JButton {

	private final int entityId;

	public IdButton(String label, int entityId) {
		super(label);
		this.entityId = entityId;
	}

	public int getIdEntity() {
		return this.entityId;
	}
}