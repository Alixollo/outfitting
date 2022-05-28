package outfitting.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import outfitting.controller.IOutfittingListController;
import outfitting.dto.OutfittingDTOForDisplay;
import outfitting.dto.OutfittingDTOForDisplayComparatorByName;
import outfitting.dto.OutfittingDTOForDisplayComparatorByRegion;
import util.ViewUtil;

public class OutfittingListView extends JDialog implements View, ActionListener {
	
	private static final String ACTION_GET_DETAILS = "GET_OUTFITTING_DETAIL";
	private static final String GET_DETAILS_BUTTON = "Détails";
	private static final String ACTION_CLOSE = "CLOSE_LIST";
	private static final String CLOSE_BUTTON_LABEL = "Fermer";
	private static final String ACTION_SEARCH_OUTFITTING = "SEARCH_OUTFITTING";
	private static final String SEARCH_BUTTON_LABEL = "Rechercher";
	private static final String SEARCH_LABEL = "Recherche";
	private static final String ACTION_SORT_BY_NAME = "SORT_NAME";
	private static final String ACTION_SORT_BY_REGION = "SORT_REGION";
	private static final String SORT_BY_REGION_BUTTON_LABEL = "Région";
	private static final String SORT_BY_NAME_BUTTON_LABEL = "Nom";
	private static final String SORT_LABEL = "Trier par :";
	private static final String VIEW_TITLE = "Liste des pourvoiries";
	
	private static final String ID_LABEL = "ID";
	private static final String NAME_LABEL = "NOM";
	private static final String REGION_LABEL = "RÉGION";
	private static final String TELEPHONE_LABEL = "NUMÉRO DE TÉLÉPHONE";
	private static final String EMAIL_LABEL = "COURRIEL";
	private static final String DETAILS_LABEL = "DÉTAILS";

	private IOutfittingListController controller;
	
	private JTextField filterTextField;
	private Collection<OutfittingDTOForDisplay> outfittingList;
	private JPanel listPanel;
	
	public OutfittingListView() {
		super();
		
		this.filterTextField = new JTextField(20);
		
		this.initialize();
		this.setUpComponents();
		this.pack();
	}
	
	@Override
	public void display() {
		this.setVisible(true);
	}
	
	@Override
	public void refresh() {
		this.outfittingList = this.controller.getOutfittingList();
		this.updateListPanel(this.outfittingList);
	}
	
	public void setController(IOutfittingListController outfittingListController) {
		this.controller = outfittingListController;
	}

	private void initialize() {
		this.setTitle(VIEW_TITLE);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout(10,10));
		this.setModalityType(ModalityType.MODELESS);
	}
	
	private void setUpComponents() {
		this.setUpFilterInterface();
		this.setUpCloseButton();
		this.setUpOtherPanels();
	}
	
	private void setUpOtherPanels() {
		this.add(new JPanel(), BorderLayout.EAST);
		this.add(new JPanel(), BorderLayout.WEST);
	}

	private void setUpFilterInterface() {
		JPanel panel  = new JPanel();
		
		this.addSortButtons(panel);
		this.addFilterBar(panel);
		
		this.add(panel, BorderLayout.NORTH);
	}

	private void addSortButtons(JPanel panel) {
		JPanel sortPanel = new JPanel();
		JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
		
		sortPanel.add(new JLabel(SORT_LABEL), BorderLayout.NORTH);
		
		ViewUtil.addButton(buttonPanel, SORT_BY_NAME_BUTTON_LABEL, ACTION_SORT_BY_NAME, this);
		ViewUtil.addButton(buttonPanel, SORT_BY_REGION_BUTTON_LABEL, ACTION_SORT_BY_REGION, this);
		
		sortPanel.add(buttonPanel, BorderLayout.CENTER);
		panel.add(sortPanel, BorderLayout.EAST);
	}
	
	private void addFilterBar(JPanel panel) {
		JPanel filterPanel = new JPanel();
		
		ViewUtil.addTextField(filterPanel, SEARCH_LABEL, this.filterTextField);
		ViewUtil.addButton(filterPanel, SEARCH_BUTTON_LABEL, ACTION_SEARCH_OUTFITTING, this);
		
		panel.add(filterPanel, BorderLayout.CENTER);
	}
	
	private void updateListPanel(Collection<OutfittingDTOForDisplay> outfittingList) {
		this.resetListPanel();
		
		JPanel panel = new JPanel(new GridLayout(0, 6, 30, 10));
		
		this.addListLabels(panel);
		
		for(OutfittingDTOForDisplay outfitting : outfittingList) {
			this.addOutfittingToList(panel, outfitting);
		}
		
		this.listPanel = panel;
		this.add(this.listPanel, BorderLayout.CENTER);
		this.pack();
	}

	private void resetListPanel() {
		if(this.listPanel != null) {
			this.remove(this.listPanel);
		}
	}
	
	private void addListLabels(JPanel panel) {
		panel.add(new JLabel(ID_LABEL, JLabel.CENTER));
		panel.add(new JLabel(NAME_LABEL));
		panel.add(new JLabel(REGION_LABEL));
		panel.add(new JLabel(TELEPHONE_LABEL));
		panel.add(new JLabel(EMAIL_LABEL));
		panel.add(new JLabel(DETAILS_LABEL));
	}

	private void addOutfittingToList(JPanel panel, OutfittingDTOForDisplay outfitting) {
		panel.add(new JLabel("" + outfitting.ID, JLabel.CENTER));
		panel.add(new JLabel(outfitting.NAME));
		panel.add(new JLabel(outfitting.REGION.toString()));
		panel.add(new JLabel(outfitting.TELEPHONE));
		panel.add(new JLabel(outfitting.EMAIL));
	
		ViewUtil.addIdButton(panel, GET_DETAILS_BUTTON, ACTION_GET_DETAILS, this, outfitting.ID);	
	}
	
	private void setUpCloseButton() {
		JPanel panel = new JPanel();
		
		ViewUtil.addButton(panel, CLOSE_BUTTON_LABEL, ACTION_CLOSE, this);
		
		this.add(panel, BorderLayout.SOUTH);
	}
	
	private void sort(Comparator<OutfittingDTOForDisplay> comparator) {
		List<OutfittingDTOForDisplay> list = new ArrayList<OutfittingDTOForDisplay>();
		this.outfittingList.forEach(outfitting -> list.add(outfitting));
		
		list.sort(comparator);
		
		this.updateListPanel(list);
	}
	
	private void searchOutfitting() {
		this.outfittingList = this.controller.searchOutfitting(this.filterTextField.getText());
		
		this.updateListPanel(this.outfittingList);
	}
	
	private void getOutfittingDetailAction(int idEntity) {
		this.controller.requestOutfittingDetails(idEntity);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case ACTION_CLOSE:
			this.dispose();
			break;
		case ACTION_SORT_BY_NAME:
			this.sort(new OutfittingDTOForDisplayComparatorByName());
			break;
		case ACTION_SORT_BY_REGION:
			this.sort(new OutfittingDTOForDisplayComparatorByRegion());
			break;
		case ACTION_SEARCH_OUTFITTING:
			this.searchOutfitting();
			break;
		case ACTION_GET_DETAILS:
			IdButton outfittingDetailsButton = (IdButton) e.getSource();
			int idEntity = outfittingDetailsButton.getIdEntity();
			this.getOutfittingDetailAction(idEntity);
			break;
		}
		
	}
}
