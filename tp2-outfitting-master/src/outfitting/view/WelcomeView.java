package outfitting.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import outfitting.controller.IWelcomeController;
import util.ImageUtil;
import util.ViewUtil;

public class WelcomeView extends JFrame implements View, ActionListener {
	
	private static final String ACTION_LIST_OUTFITTING = "LIST_OUTFITTING";
	private static final String BTN_LIST_OUTFITTING_MESSAGE = "Liste des pourvoiries...";
	private static final String VIEW_TITLE = "Nos chalets";
	private static final String WELCOME_MESSAGE = "Bienvenue !";
	private static final String WELCOME_PICTURE = "../resource/Cottage.jpg";
	private static final String BTN_ADD_COTTAGE_MESSAGE = "Ajouter un chalet...";
	private static final String BTN_LIST_COTTAGE_MESSAGE = "Liste des chalets...";
	private static final String BTN_ADD_OUTFITTING_MESSAGE = "Ajouter une pourvoirie...";
	
	private static final String ACTION_ADD_COTTAGE = "ADD_COTTAGE";
	private static final String ACTION_LIST_COTTAGE = "LIST_COTTAGE";
	
	private static final String ACTION_ADD_OUTFITTING = "ADD_OUTFITTING";
	
	private static final Dimension DEFAULT_SIZE = new Dimension(575, 530);

	private IWelcomeController controller;

	public WelcomeView() {
		super();
		this.initialize();
		this.setUpComponents();
	}

	private void initialize() {
		this.setTitle(VIEW_TITLE);
		this.setSize(DEFAULT_SIZE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setController(IWelcomeController welcomeController) {
		this.controller = welcomeController;
	}

	@Override
	public void display() {
		this.setVisible(true);
	}
	
	@Override
	public void refresh() {
	}

	private void setUpComponents() {
		this.setUpWelcomePanel();
		this.setUpActionPanel();
	}

	private void setUpWelcomePanel() {
		JPanel welcomePanel = new JPanel();
		this.add(welcomePanel, BorderLayout.CENTER);

		welcomePanel.setLayout(new BorderLayout());
		this.addWelcomePicture(welcomePanel);
		this.addWelcomeMessage(welcomePanel);
	}

	private void addWelcomeMessage(JPanel welcomePanel) {
		JLabel welcomeMessage = new JLabel(WELCOME_MESSAGE, SwingConstants.CENTER);
		welcomePanel.add(welcomeMessage, BorderLayout.NORTH);
	}

	private void addWelcomePicture(JPanel welcomePanel) {
		ImageIcon image = ImageUtil.getImageIcon(this, WELCOME_PICTURE);
		JLabel welcomePicture = new JLabel(image);
		welcomePanel.add(welcomePicture);
	}

	private void setUpActionPanel() {
		JPanel actionPanel = new JPanel(new GridLayout(0, 4, 10, 0));
		this.add(actionPanel, BorderLayout.SOUTH);
		this.addButtons(actionPanel);
	}
	
	private void addButtons(JPanel actionPanel) {
		ViewUtil.addButton(actionPanel, BTN_ADD_COTTAGE_MESSAGE, ACTION_ADD_COTTAGE, this);
		
		ViewUtil.addButton(actionPanel, BTN_LIST_COTTAGE_MESSAGE, ACTION_LIST_COTTAGE, this);
		
		ViewUtil.addButton(actionPanel, BTN_ADD_OUTFITTING_MESSAGE, ACTION_ADD_OUTFITTING, this);
		
		ViewUtil.addButton(actionPanel, BTN_LIST_OUTFITTING_MESSAGE, ACTION_LIST_OUTFITTING, this);
	}
	
	private void addCottageRequest() {
		this.controller.requestCottageCreate();
	}
	
	private void listCottageRequest() {
		this.controller.requestCottageList();
	}
	
	private void addOutfittingRequest() {
		this.controller.requestOutfittingCreate();
	}
	
	private void listOutfittingRequest() {
		this.controller.requestOutfittingList();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case ACTION_ADD_COTTAGE:
			this.addCottageRequest();
			break;
		case ACTION_LIST_COTTAGE:
			this.listCottageRequest();
			break;
		case ACTION_ADD_OUTFITTING:
			this.addOutfittingRequest();
			break;
		case ACTION_LIST_OUTFITTING:
			this.listOutfittingRequest();
			break;
		}
	}
}
