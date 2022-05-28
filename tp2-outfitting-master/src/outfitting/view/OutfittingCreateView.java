package outfitting.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import outfitting.controller.IOutfittingCreateController;
import outfitting.dto.OutfittingDTOForCreate;
import outfitting.exception.InvalidOutfittingException;
import outfitting.model.Region;
import util.ViewUtil;

public class OutfittingCreateView extends JDialog implements View, ActionListener{

	private static final String VIEW_TITLE = "Ajout d'une pourvoirie";
	
	private static final String LABEL_NAME = "Nom de la pourvoirie:";
	private static final String LABEL_REGION = "Région:";
	private static final String LABEL_PHONE = "Téléphone:";
	private static final String LABEL_EMAIL = "Courriel:";
	
	private static final String LABEL_NAME_CONTACT = "Nom du contact:";
	private static final String LABEL_PHONE_CONTACT = "Téléphone du contact:";
	private static final String LABEL_EMAIL_CONTACT = "Courriel du contact:";
	
	private static final String BTN_ADD_OUTFITTING_MESSAGE = "Ajouter";

	private static final String ADD_OUTFITTING_ACTION = "ADD_OUTFITTING";
	
	private static final String CONFIRMATION_TITLE = "Confirmation d'une nouvelle pourvoirie";
	private static final String CONFIRMATION_MESSAGE = "La pourvoirie a bien été ajouté";
	
	private static final String ERROR_TITLE = "Erreur d'ajout d'une nouvelle pourvoirie";
	private static final String ERROR_VALIDITY_TITLE = "Pourvoirie invalide";
	private static final String ERROR_MESSAGE = "Impossible d'ajouter la pourvoirie \nRaison: ";
	
	private IOutfittingCreateController controller;
	
	private JTextField name = new JTextField(30);
	private JComboBox<Region> region = new JComboBox<Region>(Region.values());
	private JTextField phoneNumber = new JTextField(30);
	private JTextField email = new JTextField(30);
	
	private JTextField contactName = new JTextField(30);
	private JTextField contactPhoneNumber = new JTextField(30);
	private JTextField contactEmail = new JTextField(30);
	
	public OutfittingCreateView() {
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
	
	public void setController(IOutfittingCreateController outfittingCreateController) {
		this.controller = outfittingCreateController;
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
		ViewUtil.addComboBox(inputDataPanel, LABEL_REGION, this.region);
		ViewUtil.addTextField(inputDataPanel, LABEL_PHONE, this.phoneNumber);
		ViewUtil.addTextField(inputDataPanel, LABEL_EMAIL, this.email);
		ViewUtil.addTextField(inputDataPanel, LABEL_NAME_CONTACT, this.contactName);
		ViewUtil.addTextField(inputDataPanel, LABEL_PHONE_CONTACT, this.contactPhoneNumber);
		ViewUtil.addTextField(inputDataPanel, LABEL_EMAIL_CONTACT, this.contactEmail);
	}

	private void setUpActionPanel() {
		JPanel actionPanel = new JPanel();
		this.add(actionPanel, BorderLayout.SOUTH);
		ViewUtil.addButton(actionPanel, BTN_ADD_OUTFITTING_MESSAGE, ADD_OUTFITTING_ACTION, this);
	}
	
	private void addOutfittingAction(){
		this.addOutfitting();
	}
	
	private void addOutfitting() throws InvalidOutfittingException{
		try {
			controller.add(this.extractOutfitting());
			JOptionPane.showMessageDialog(this, CONFIRMATION_MESSAGE, CONFIRMATION_TITLE, JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}
		catch(InvalidOutfittingException ex){
			this.showConfirmationWindow(ERROR_MESSAGE + ex.getMessage(), ERROR_VALIDITY_TITLE, JOptionPane.ERROR_MESSAGE);
		}
		catch(RuntimeException ex){
			this.showConfirmationWindow(ERROR_MESSAGE + ex.getMessage(), ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private OutfittingDTOForCreate extractOutfitting() {
		String name = this.extractOutfittingName();
		Region region = this.extractOutfittingRegion();
		String phoneNumber = this.extractOutfittingPhoneNumber();
		String email = this.extractOutfittingEmail();
		String privateName = this.extractOutfittingContactName();
		String privateNumber = this.extractOutfittingContactPhoneNumber();
		String privateEmail = this.extractOutfittingContactEmail();
		return new OutfittingDTOForCreate(name, region, phoneNumber, email, privateName, privateNumber, privateEmail);
	}

	private String extractOutfittingContactEmail() {
		return this.contactEmail.getText();
	}

	private String extractOutfittingContactPhoneNumber() {
		return this.contactPhoneNumber.getText();
	}

	private String extractOutfittingContactName() {
		return this.contactName.getText();
	}

	private String extractOutfittingEmail() {
		return this.email.getText();
	}

	private String extractOutfittingPhoneNumber() {
		return this.phoneNumber.getText();
	}

	private Region extractOutfittingRegion() {
		return (Region) this.region.getSelectedItem();
	}

	private String extractOutfittingName() {
		return this.name.getText();
	}

	private void showConfirmationWindow(String message, String title, int messageType) {
		JOptionPane.showMessageDialog(this, message, title, messageType);
	}
	
	@Override
	public void display() {
		this.setVisible(true);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case ADD_OUTFITTING_ACTION :
			this.addOutfittingAction();
			break;
		}
		
	}

	@Override
	public void refresh() {
		
	}

}
