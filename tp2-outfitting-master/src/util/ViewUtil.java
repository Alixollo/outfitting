package util;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import outfitting.view.IdButton;

public abstract class ViewUtil {

	public static void addTextField(JPanel panel, String labelTxt, JTextField txtField) {
		JLabel lbl = new JLabel(labelTxt);
		panel.add(lbl);
		panel.add(txtField);
	}
	
	public static void addButton(JPanel panel, String btnMessage, String btnAction, ActionListener actionListener) {
		JButton button = new JButton(btnMessage);
		button.setActionCommand(btnAction);
		button.addActionListener(actionListener);		
		panel.add(button);
	}
	
	public static void addIdButton(JPanel panel, String btnMessage, String btnAction, ActionListener actionListener, int entityId) {
		IdButton button = new IdButton(btnMessage, entityId);
		button.setActionCommand(btnAction);
		button.addActionListener(actionListener);
		panel.add(button);
	}
	
	public static void addComboBox(JPanel panel, String labelTxt, JComboBox<?> comboBox) {
		JLabel lbl = new JLabel(labelTxt);
		panel.add(lbl);
		panel.add(comboBox);
	}

}
