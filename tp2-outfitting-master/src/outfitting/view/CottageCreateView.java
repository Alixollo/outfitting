package outfitting.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import outfitting.controller.ICottageCreateController;
import outfitting.dto.CottageDTOForCreate;
import outfitting.dto.OutfittingDTOForDisplay;
import util.ViewUtil;

public class CottageCreateView extends JDialog implements View, ActionListener  {

	private static final String INVALID_GUEST_NB_MESSAGE = "Le nombre de personnes est invalide.";
	private static final String INVALID_BEDROOM_NB_MESSAGE = "Le nombre de chambres est invalide.";
	private static final String INVALID_PRICE_MESSAGE = "Le prix par nuit est invalide.";
	private static final String ERROR_TITLE = "Erreur";
	
	private static final String CONFIRMATION_TITLE = "Confirmation d'un nouveau chalet";
	private static final String CONFIRMATION_MESSAGE = "Le chalet a bien été ajouté";

	private static final String VIEW_TITLE = "Ajout d'un chalet";

	private static final String BTN_ADD_COTTAGE_MESSAGE = "Ajouter";

	private static final String ADD_COTTAGE_ACTION = "ADD_COTTAGE";

	private static final String LABEL_NAME = "Nom du chalet:";
	private static final String LABEL_GUESTS = "Nb de personnes:";
	private static final String LABEL_BEDROOMS = "Nb de chambres:";
	private static final String LABEL_PRICE = "Prix par nuit:";
	private static final String LABEL_OUTFITTING = "Pourvoirie:";
	private static final String ERROR_MESSAGE = "Impossible d'ajouter le chalet \nRaison: ";
	


	private ICottageCreateController controller;
	
	private JTextField name = new JTextField(30);
	private JTextField guests = new JTextField(3);
	private JTextField bedrooms = new JTextField(3);
	private JTextField price = new JTextField(6);
	private JComboBox<OutfittingDTOForDisplay> outfitting = new JComboBox<>();


	public CottageCreateView() {
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

	public void setController(ICottageCreateController cottageCreateController) {
		this.controller = cottageCreateController;
	}

	@Override
	public void display() {
		this.refresh();
		this.setVisible(true);
	}
	
	@Override
	public void refresh() {
		Collection<OutfittingDTOForDisplay> outfittingList = this.controller.getOutfittings();
		this.updateOutfittingComboBox(outfittingList);
	}

	private void updateOutfittingComboBox(Collection<OutfittingDTOForDisplay> outfittingList) {
		this.outfitting.removeAllItems();
		
		outfittingList.forEach(item -> outfitting.addItem(item));		
	}

	private void setUpComponents() {
		this.setUpInputDataPanel();
		this.setUpActionPanel();
		this.setUpOtherPanels();
	}

	private void setUpOtherPanels() {
		this.add(new JPanel(), BorderLayout.NORTH);
		this.add(new JPanel(), BorderLayout.EAST);
		this.add(new JPanel(), BorderLayout.WEST);
	}

	private void setUpInputDataPanel() {
		JPanel inputDataPanel = new JPanel();
		this.add(inputDataPanel);
		inputDataPanel.setLayout(new GridLayout(0, 2));
		ViewUtil.addTextField(inputDataPanel, LABEL_NAME, this.name);
		ViewUtil.addTextField(inputDataPanel, LABEL_GUESTS, this.guests);
		ViewUtil.addTextField(inputDataPanel, LABEL_BEDROOMS, this.bedrooms);
		ViewUtil.addTextField(inputDataPanel, LABEL_PRICE, this.price);
		ViewUtil.addComboBox(inputDataPanel, LABEL_OUTFITTING, this.outfitting);
	}

	private void setUpActionPanel() {
		JPanel actionPanel = new JPanel();
		this.add(actionPanel, BorderLayout.SOUTH);
		ViewUtil.addButton(actionPanel, BTN_ADD_COTTAGE_MESSAGE, ADD_COTTAGE_ACTION, this);
	}
	
	private void closeView() {
		this.dispose();
	}
	
	private void addCottage() throws NumberFormatException{
		controller.add(this.extractCottage());
		this.showConfirmationWindow(name.getText() + "\n" + CONFIRMATION_MESSAGE, CONFIRMATION_TITLE, JOptionPane.INFORMATION_MESSAGE);
		this.closeView();
	}
	
	private CottageDTOForCreate extractCottage() throws NumberFormatException{
		String name = this.extractCottageName();
		int nbGuests = this.extractCottageNbGuests();
		int nbBedrooms = this.extractCottageNbBedrooms();
		float pricePerNight = this.extractCottagePricePerNight();
		int outfittingId = this.extractOutfittingId();
		return new CottageDTOForCreate(name, nbGuests, nbBedrooms, pricePerNight, outfittingId);
	}

	private int extractOutfittingId() {
		OutfittingDTOForDisplay outfittingSelected = (OutfittingDTOForDisplay)this.outfitting.getSelectedItem();
		return outfittingSelected.ID;
	}

	private float extractCottagePricePerNight() {
		try {
			return Float.parseFloat(this.price.getText());
		}
		catch(NumberFormatException ex){
			throw new NumberFormatException(INVALID_PRICE_MESSAGE);
		}
		
	}

	private String extractCottageName() {
		return this.name.getText();
	}

	private int extractCottageNbBedrooms() {		
		try {
			return Integer.parseInt(this.bedrooms.getText());
		}
		catch(NumberFormatException ex){
			throw new NumberFormatException(INVALID_BEDROOM_NB_MESSAGE);
		}
	}

	private int extractCottageNbGuests() throws NumberFormatException {
		try {
			return Integer.parseInt(this.guests.getText());
		}
		catch(NumberFormatException ex){
			throw new NumberFormatException(INVALID_GUEST_NB_MESSAGE);
		}
	}

	private void addCottageAction(){
		try {
			this.addCottage();
		}
		catch(NumberFormatException ex){
			this.showConfirmationWindow(ex.getMessage(), ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
		}
		catch(RuntimeException ex){
			this.showConfirmationWindow(ERROR_MESSAGE + ex.getMessage(), ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
		}	
	}

	private void showConfirmationWindow(String message, String title, int messageType) {
		JOptionPane.showMessageDialog(this, message, title, messageType);
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		switch(e.getActionCommand()) {
		case ADD_COTTAGE_ACTION :
			this.addCottageAction();
			break;
		}
	}
}
