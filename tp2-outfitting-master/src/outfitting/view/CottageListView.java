package outfitting.view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import outfitting.controller.ICottageListController;
import outfitting.dto.CottageDTOForDisplay;
import outfitting.dto.CottageDTOForDisplayComparatorByGuestAmount;
import outfitting.model.Region;
import util.ViewUtil;

public class CottageListView extends JDialog implements View, ActionListener {
	
	private static final String NUMBER_ERROR_TITLE = "Erreur de trie";
	private static final String NUMBER_ERROR_LABEL = "Veuillez spécifier un chiffre valide.";
	private static final String ACTION_DETAILS = "GET_COTTAGE_DETAILS";
	private static final String DETAILS_BUTTON_LABEL = "Détails";
	private static final String ACTION_FILTER_BY_ROOM_AMOUNT = "FILTER_BY_ROOM_AMOUNT";
	private static final String ROOM_AMOUNT_FILTER_LABEL = "Nombre de chambres : ";
	private static final String REGION_FILTER_LABEL = "Région : ";
	private static final String FILTER_BUTTON_LABEL = "Filtrer";
	private static final String ACTION_FILTER_BY_REGION = "FILTER_BY_REGION";
	private static final String DETAILS_LABEL = "DÉTAILS";
	private static final String OUTFITTING_NAME_LABEL = "NOM POURVOIRIE";
	private static final String REGION_LABEL = "REGION";
	private static final String GUEST_NB_LABEL = "NB. PERSONNES";
	private static final String NAME_LABEL = "NOM";
	private static final String ID_LABEL = "ID";
	private static final String ACTION_CLOSE = "OK_ACTION";
	private static final String CLOSE_BUTTON_LABEL = "Fermer";
	private static final String VIEW_TITLE = "Liste des chalets";
	
	private ICottageListController controller;
	private JPanel dataPanel;
	private JTextField roomAmountTxtField = new JTextField(3);
	private JComboBox<Region> regionComboBox = new JComboBox<Region>(Region.values());

	public CottageListView() {
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
		this.setUpFilterInterface();
		this.setUpDataPanel();
		this.setUpActionPanel();
		this.setUpOtherPanels();
		this.pack();
	}
	
	public void setController(ICottageListController cottageListController) {
		this.controller = cottageListController;
	}
	
	private void setUpFilterInterface() {
		JPanel panel = new JPanel();
		JPanel gridPanel = new JPanel(new GridLayout(1, 2, 30, 10));
		
		this.addFilters(gridPanel);
		
		panel.add(gridPanel, BorderLayout.CENTER);
		panel.add(new JPanel(), BorderLayout.NORTH); //Padding
		
		this.add(panel, BorderLayout.NORTH);
	}

	private void addFilters(JPanel panel) {
		JPanel roomAmountPanel = new JPanel(new GridBagLayout());
		ViewUtil.addTextField(roomAmountPanel, ROOM_AMOUNT_FILTER_LABEL, this.roomAmountTxtField);
		ViewUtil.addButton(roomAmountPanel, FILTER_BUTTON_LABEL, ACTION_FILTER_BY_ROOM_AMOUNT, this);
		
		JPanel regionPanel = new JPanel(new GridBagLayout());
		ViewUtil.addComboBox(regionPanel, REGION_FILTER_LABEL, this.regionComboBox);
		ViewUtil.addButton(regionPanel, FILTER_BUTTON_LABEL, ACTION_FILTER_BY_REGION, this);
		
		panel.add(roomAmountPanel);
		panel.add(regionPanel);
	}
	
	private void setUpDataPanel() {
		this.dataPanel = new JPanel(new GridLayout(0, 6, 30, 10));
		
		this.add(dataPanel, BorderLayout.CENTER);
	}
	
	private void updateDataPanel(Collection<CottageDTOForDisplay> cottages) {
		this.dataPanel.removeAll();
		
		this.addListLabels();
		
		cottages = this.sort(cottages, new CottageDTOForDisplayComparatorByGuestAmount());
		
		for (CottageDTOForDisplay cottage : cottages) {
			this.addCottageToList(cottage);
		}
		
		this.pack();
	}
	
	private Collection<CottageDTOForDisplay> sort(Collection<CottageDTOForDisplay> cottages, Comparator<CottageDTOForDisplay> comparator) {
		List<CottageDTOForDisplay> list = new ArrayList<CottageDTOForDisplay>();
		cottages.forEach(cottage -> list.add(cottage));
		
		list.sort(comparator);
		
		return list;
	}
	
	private void addListLabels() {
		this.dataPanel.add(new JLabel(ID_LABEL));
		this.dataPanel.add(new JLabel(NAME_LABEL));
		this.dataPanel.add(new JLabel(GUEST_NB_LABEL));
		this.dataPanel.add(new JLabel(OUTFITTING_NAME_LABEL));
		this.dataPanel.add(new JLabel(REGION_LABEL));
		this.dataPanel.add(new JLabel(DETAILS_LABEL));
	}
	
	private void addCottageToList(CottageDTOForDisplay cottage) {
		this.dataPanel.add(new JLabel(""+cottage.ID));
		this.dataPanel.add(new JLabel(cottage.NAME));
		this.dataPanel.add(new JLabel(""+cottage.GUESTS));
		this.dataPanel.add(new JLabel(cottage.OUTFITTING_NAME));
		this.dataPanel.add(new JLabel(cottage.OUTFITTING_REGION.toString()));
		ViewUtil.addIdButton(this.dataPanel, DETAILS_BUTTON_LABEL, ACTION_DETAILS, this, cottage.ID);
	}
	
	private void setUpOtherPanels() {
		this.add(new JPanel(), BorderLayout.EAST);
		this.add(new JPanel(), BorderLayout.WEST);
	}
	
	private void setUpActionPanel() {
		JPanel actionPanel = new JPanel();
		
		ViewUtil.addButton(actionPanel, CLOSE_BUTTON_LABEL, ACTION_CLOSE, this);
		
		this.add(actionPanel, BorderLayout.SOUTH);
	}
	
	private void returnAction() {
		this.dispose();
	}

	@Override
	public void display() {
		this.setVisible(true);
	}
	
	@Override
	public void refresh() {
		this.updateDataPanel(this.controller.getCottages());
		this.pack();
	}
	
	private void filterByRegion() {
		this.updateDataPanel(this.controller.searchCottagesByRegion((Region)this.regionComboBox.getSelectedItem()));
		this.pack();
	}
	
	private void filterByRoomAmount() {
		if(!this.roomAmountTxtField.getText().isBlank()) {
			try {
				this.updateDataPanel(this.controller.searchCottagesByRoomAmount(Integer.parseInt(this.roomAmountTxtField.getText())));
			}
			catch(NumberFormatException exception) {
				JOptionPane.showMessageDialog(this, NUMBER_ERROR_LABEL, NUMBER_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
			}
			finally {
				this.pack();
			}
		}
		else {
			this.updateDataPanel(this.controller.getCottages());
		}
	}
	
	private void getCottageDetailAction(int idEntity) {
		this.controller.requestCottageDetails(idEntity);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case ACTION_CLOSE :
			this.returnAction();
			break;
		case ACTION_DETAILS:
			IdButton cottageDetailsButton = (IdButton) e.getSource();
			int idEntity = cottageDetailsButton.getIdEntity();
			this.getCottageDetailAction(idEntity);
			break;
		case ACTION_FILTER_BY_REGION:
			this.filterByRegion();
			break;
		case ACTION_FILTER_BY_ROOM_AMOUNT:
			this.filterByRoomAmount();
			break;
		}
	}

}
