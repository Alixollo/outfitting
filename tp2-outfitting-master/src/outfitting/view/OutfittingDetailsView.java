package outfitting.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import outfitting.controller.IOutfittingDetailsController;
import outfitting.dto.OutfittingDTOForDisplay;
import util.ViewUtil;

public class OutfittingDetailsView extends JDialog implements View, ActionListener {
	
	private static final String LABEL_OUTFITTING_EMAIL = "Courriel : ";
	private static final String LABEL_OUTFITTING_TELEPHONE = "Numéro de téléphone : ";
	private static final String LABEL_OUTFITTING_REGION = "Région : ";
	private static final String LABEL_OUTFITTING_NAME = "Nom de la pourvoirie : ";
	private static final String LABEL_OUTFITTING_ID = "ID de la pourvoirie : ";
	
	private static final String LABEL_CONTACT_EMAIL = "Courriel: ";
	private static final String LABEL_CONTACT_TELEPHONE = "Numéro de téléphone: ";
	private static final String LABEL_CONTACT_NAME = "Nom: ";
	
	private static final String PERSONAL_INFO_LABEL = "Coordonnées du contact :";
	
	private static final String CLOSE_BUTTON_LABEL = "OK";
	private static final String ACTION_CLOSE = "CLOSE";
	
	private static final String HEADER_LABEL = "Informations de la pourvoirie :";
	private static final String VIEW_TITLE = "Informations sur la pourvoirie";
	
	private IOutfittingDetailsController controller;
	private OutfittingDTOForDisplay outfitting;
	private JPanel infoPanel;
	
	public OutfittingDetailsView() {
		super();
		this.initialize();
		this.setUpComponents();
		this.pack();
	}

	private void initialize() {
		this.setTitle(VIEW_TITLE);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout(10,10));
		this.setModalityType(ModalityType.MODELESS);
	}

	private void setUpComponents() {
		this.addCloseButton();
		this.setUpOtherPanels();
	}

	@Override
	public void display() {
		this.updateDetailsList();
		this.setVisible(true);
	}

	@Override
	public void refresh() {
		this.updateDetailsList();
	}
	
	private void setUpOtherPanels() {
		this.add(new JPanel(), BorderLayout.EAST);
		this.add(new JPanel(), BorderLayout.WEST);
	}
	
	private void updateDetailsList() {
		this.resetInfoPanel();
		this.infoPanel = new JPanel(new GridLayout(0, 1));
		this.outfitting = this.controller.getOutfittingDetails();
		
		this.addGeneralInfo(this.infoPanel);
		this.addPersonalInfo(this.infoPanel);
		
		this.add(infoPanel, BorderLayout.CENTER);
		this.pack();
	}
	
	private void resetInfoPanel() {
		if(this.infoPanel != null) {
			this.remove(this.infoPanel);
		}
	}
	
	private void addInfo(JPanel panel, String label, String info) {
		panel.add(new JLabel(label));
		panel.add(new JLabel(info));
	}
	
	private void addGeneralInfo(JPanel basePanel) {		
		JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
		
		this.addInfo(panel, LABEL_OUTFITTING_ID, "" + this.outfitting.ID);
		this.addInfo(panel, LABEL_OUTFITTING_NAME, this.outfitting.NAME);
		this.addInfo(panel, LABEL_OUTFITTING_REGION, this.outfitting.REGION.NAME);
		this.addInfo(panel, LABEL_OUTFITTING_TELEPHONE, this.outfitting.TELEPHONE);
		this.addInfo(panel, LABEL_OUTFITTING_EMAIL, this.outfitting.EMAIL);
		
		this.addBlockInfo(basePanel, HEADER_LABEL, panel);
	}
	
	private void addPersonalInfo(JPanel basePanel) {
		JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
		
		this.addInfo(panel, LABEL_CONTACT_NAME, this.outfitting.PRIVATE_NAME);
		this.addInfo(panel, LABEL_CONTACT_TELEPHONE, this.outfitting.PRIVATE_TELEPHONE);
		this.addInfo(panel, LABEL_CONTACT_EMAIL, this.outfitting.PRIVATE_EMAIL);
		
		this.addBlockInfo(basePanel, PERSONAL_INFO_LABEL, panel);
	}
	
	private void addBlockInfo(JPanel basePanel, String label, JPanel infoPanel) {
		JPanel privateInfoAndHeaderPanel = new JPanel(new GridLayout(0, 1));
		
		privateInfoAndHeaderPanel.add(new JLabel(label));		
		privateInfoAndHeaderPanel.add(infoPanel);
		
		basePanel.add(privateInfoAndHeaderPanel);
	}
	
	private void addCloseButton() {
		JPanel panel = new JPanel();
		
		ViewUtil.addButton(panel, CLOSE_BUTTON_LABEL, ACTION_CLOSE, this);
		
		this.add(panel, BorderLayout.SOUTH);
	}
	
	public void setController(IOutfittingDetailsController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case ACTION_CLOSE:
			this.dispose();
			break;
		}
	}

}
