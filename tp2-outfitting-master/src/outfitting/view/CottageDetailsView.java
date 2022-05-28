package outfitting.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import outfitting.controller.ICottageDetailsController;
import outfitting.dto.CottageDTOForDisplay;
import outfitting.exception.InvalidIdException;
import util.ViewUtil;

public class CottageDetailsView extends JDialog implements View, ActionListener{
	
	private static final String REMOVE_CONFIRMATION_TITLE = "Supprimer le chalet";
	private static final String REMOVE_CONFIRMATION_MESSAGE = "Voulez-vous vraiment supprimer ce chalet?";
	private static final String LABEL_OUTFITTING_EMAIL = "Courriel : ";
	private static final String LABEL_OUTFITTING_TELEPHONE = "Numéro de téléphone : ";
	private static final String LABEL_OUTFITTING_REGION = "Région : ";
	private static final String LABEL_OUTFITTING_NAME = "Nom de la pourvoirie : ";
	private static final String LABEL_OUTFITTING_ID = "ID de la pourvoirie : ";
	
	private static final String LABEL_COTTAGE_PRICE_PER_NIGHT = "Prix par nuit : ";
	private static final String LABEL_COTTAGE_BEDROOMS = "Nombre de chambres : ";
	private static final String LABEL_COTTAGE_GUESTS = "Nombre maximum d'invités : ";
	private static final String LABEL_COTTAGE_NAME = "Nom : ";
	private static final String LABEL_COTTAGE_ID = "ID : ";
	
	private static final String HEADER_LABEL_COTTAGE_INFO = "Informations du chalet";
	private static final String HEADER_LABEL_OUTFITTING_INFO = "Informations de la pourvoirie";
	
	private static final String VIEW_TITLE = "Informations sur le chalet";
	
	private static final String CLOSE_BUTTON_LABEL = "OK";	
	private static final String ACTION_CLOSE = "CLOSE";
	
	private static final String REMOVE_BUTTON_LABEL = "Supprimer";
	private static final String ACTION_REMOVE = "REMOVE";

	private ICottageDetailsController controller;
	private CottageDTOForDisplay cottage;
	private JPanel infoPanel;
	
	public CottageDetailsView() {
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
		this.addButtonsPanel();
		this.setUpOtherPanels();
	}
	
	private void setUpOtherPanels() {
		this.add(new JPanel(), BorderLayout.EAST);
		this.add(new JPanel(), BorderLayout.WEST);
	}
	
	private void updateCottageDetailsList() throws InvalidIdException{
		this.resetInfoPanel();
		this.infoPanel = new JPanel(new GridLayout(0, 1));
		this.cottage = this.controller.getCottageDetails();
		
		addCottageInfoPanel();
		addOutfittingInfoPanel();
		
		
		this.add(infoPanel, BorderLayout.CENTER);
		this.pack();
	}
	
	private void addOutfittingInfoPanel() {
		JPanel outfittingInfoAndHeaderPanel = new JPanel(new GridLayout(0, 1));
		JPanel outfittingInfoPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		JLabel label = new JLabel(HEADER_LABEL_OUTFITTING_INFO);
		outfittingInfoAndHeaderPanel.add(label);
		
		addInfo(outfittingInfoPanel, LABEL_OUTFITTING_ID, ""+cottage.OUTFITTING_ID);
		addInfo(outfittingInfoPanel, LABEL_OUTFITTING_NAME, cottage.OUTFITTING_NAME);
		addInfo(outfittingInfoPanel, LABEL_OUTFITTING_REGION, cottage.OUTFITTING_REGION.NAME);
		addInfo(outfittingInfoPanel, LABEL_OUTFITTING_TELEPHONE, cottage.OUTFITTING_TELEPHONE);
		addInfo(outfittingInfoPanel, LABEL_OUTFITTING_EMAIL, cottage.OUTFITTING_EMAIL);
		
		outfittingInfoAndHeaderPanel.add(outfittingInfoPanel);
		
		this.infoPanel.add(outfittingInfoAndHeaderPanel);
		
	}

	private void addCottageInfoPanel() {
		JPanel cottageInfoAndHeaderPanel = new JPanel(new GridLayout(0, 1, 0, 0));
		cottageInfoAndHeaderPanel.add(new JLabel(HEADER_LABEL_COTTAGE_INFO));
		JPanel cottageInfoPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		addInfo(cottageInfoPanel, LABEL_COTTAGE_ID, "" + cottage.ID);
		addInfo(cottageInfoPanel, LABEL_COTTAGE_NAME, cottage.NAME);
		addInfo(cottageInfoPanel, LABEL_COTTAGE_GUESTS, "" + cottage.GUESTS);
		addInfo(cottageInfoPanel, LABEL_COTTAGE_BEDROOMS, "" + cottage.BEDROOMS);
		addInfo(cottageInfoPanel, LABEL_COTTAGE_PRICE_PER_NIGHT, cottage.PRICE_PER_NIGHT + "$");
		
		cottageInfoAndHeaderPanel.add(cottageInfoPanel);
		this.infoPanel.add(cottageInfoAndHeaderPanel);
	}
	
	
	private void addInfo(JPanel panel, String label, String info) {
		panel.add(new JLabel(label));
		panel.add(new JLabel(info));
	}
	
	private void resetInfoPanel() {
		if(this.infoPanel != null) {
			this.remove(this.infoPanel);
		}
	}
	
	public void setController(ICottageDetailsController controller) {
		this.controller = controller;		
	}
	
	private void addButtonsPanel() {
		JPanel panel = new JPanel();
		
		ViewUtil.addButton(panel, REMOVE_BUTTON_LABEL, ACTION_REMOVE, this);
		ViewUtil.addButton(panel, CLOSE_BUTTON_LABEL, ACTION_CLOSE, this);
		
		this.add(panel, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case ACTION_CLOSE:
			this.dispose();
			break;
		case ACTION_REMOVE:
			this.removeAction();
			break;
		}
	}

	private void removeAction() {
		String[] options = {"Oui", "Non"};

		int selection = JOptionPane.showOptionDialog(this, REMOVE_CONFIRMATION_MESSAGE, REMOVE_CONFIRMATION_TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		
		if(selection == JOptionPane.YES_OPTION) {
			this.controller.removeCottage();
		}
	}

	@Override
	public void display() {
		this.updateCottageDetailsList();
		this.setVisible(true);		
	}

	@Override
	public void refresh() {
		try {
			this.updateCottageDetailsList();
		}
		catch(InvalidIdException ex) {
			this.dispose();
		}
	}

	

}
