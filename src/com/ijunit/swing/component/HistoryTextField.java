package com.ijunit.swing.component;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import com.ijunit.common.Logger;
import com.ijunit.swing.model.HistoryModel;

/**
*
* @author vcjain
*/
public class HistoryTextField extends JTextField implements DocumentListener,
		FocusListener, ActionListener, MouseListener {

	static Logger logger = Logger.getLogger(HistoryTextField.class);
	private HistoryModel history = null;
	private JPopupMenu popupMenu = null;
	private String currentText = "";
	private String selectedText = "";
	private String tempText = null;
	private JMenuItem menuItem = null;
	private int popupEntrySize = 0;
	private int maxPopupEntrySize = HistoryFactory.MAX_POPUP_ITEM;
	private int maxHistorySize = HistoryFactory.MAX_HISTORY_ITEM;
	private boolean isToFireActionPerformed = true;
	private boolean isEnterSaveHistory = true;

	/**
	 * Constructor
	 */
	public HistoryTextField() {
		super();
		init();
	}

	/*
	 * Constructs
	 * @param doc - document
	 * @param text-  dafault text
	 * @param columns - no of columns
	 */
	public HistoryTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		init();
	}

	/*
	 * Constructs
	 * @param columns - numbers of columns 
	 */
	public HistoryTextField(int columns) {
		super(columns);
		init();
	}

	/*
	 * Constructs
	 * @param text - default text
	 * @since 1.0
	 */
	public HistoryTextField(String text) {
		super(text);
		init();
	}

	/*
	 * Constructs 
	 * 
	 * @param text - default text
	 * @param columns - number of columns
	 */
	public HistoryTextField(String text, int columns) {
		super(text, columns);
		init();
	}

	public HistoryTextField(HistoryModel history, int columns){
		super(columns);
		this.history = history;  
		init();
	}
	
	public HistoryTextField(HistoryModel history){
		super();
		this.history = history; 
		init();
	}
	
	/*
	 * This method initialize the HistoryTextField
	 */
	public void init() {
		popupMenu = new JPopupMenu();
		popupMenu.setInvoker(this);
		popupMenu.setLightWeightPopupEnabled(true);
		addMouseListener(this);
		getDocument().addDocumentListener(this);
		addFocusListener(this);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello");
				
			}
		});
	}

	/*
	 * Setter method for maximum history cache size
	 * @param maxHistorySize -  maxHistorySize value
	 */
	public void setMaxHistorySize(int maxHistorySize) {
		this.maxHistorySize = maxHistorySize;
	}

	/*
	 * Setter for maximum number of JMenuItems should be shown
	 * 
	 * @param maxPopupEntrySize - maxPopupEntrySize value
	 */
	public void setMaxPopupEntrySize(int maxPopupEntrySize) {
		this.maxPopupEntrySize = maxPopupEntrySize;
	}

	/*
	 * Getter for maximum history cache size
	 * 
	 * @param maxHistorySize - size
	 * 
	 */
	public int getMaxHistorySize(int maxHistorySize) {
		return maxHistorySize;
	}

	/*
	 * Get maximum number of JMenuItems should be shown, default is 5
	 * 
	 * @param maxPopupEntrySize Description of the Parameter
	 * @return The maxPopupEntrySize value
	 */
	public int getMaxPopupEntrySize(int maxPopupEntrySize) {
		return maxPopupEntrySize;
	}

	/**
	 * Method to clear history cache
	 */
	public void clearHistory() {
		history.clear();
	}

	/**
	 * This method will show popup menu
	 */
	public void showHistory() {
		currentText = getText();
		// reset popupMenu
		for (int i = popupEntrySize - 1; i > -1; i--) {
			popupMenu.remove(i);
		}
		popupEntrySize = 0;
		if (!history.isEmpty() && !currentText.equals("")
				&& !currentText.equals(selectedText)) {
			for (Iterator it = history.iterator(); it.hasNext();) {
				tempText = (String) it.next();
				if ((tempText.startsWith(currentText))
						&& (!tempText.equals(currentText))) {
					popupEntrySize++;
					menuItem = popupMenu.add(tempText);
					menuItem.addActionListener(this);
					menuItem.addMouseListener(this);
					menuItem.setBackground(getBackground());
					if (popupEntrySize == maxPopupEntrySize) {
						break;
					}
				}
			}
		}

		// To show or not to show
		if (popupEntrySize > 0) {
			popupMenu.setLocation(getLocationOnScreen().x, getHeight()
					+ getLocationOnScreen().y);
			popupMenu.setBackground(getBackground());
			popupMenu.setFocusable(false);
			popupMenu.setVisible(false);
			popupMenu.repaint();
			popupMenu.setVisible(true);
		} else {
			popupMenu.setFocusable(false);
			popupMenu.repaint();
			popupMenu.setVisible(false);
		}

		requestFocus();
	}

	/*
	 * Metthod to hide popupmenu
	 */
	public void hideHistory() {
		if (popupMenu.isVisible()) {
			popupMenu.setFocusable(false);
			popupMenu.setVisible(false);
		}
	}

	/**
	 * Invoked when the text is modified
	 * @param e - document event
	 */
	public void changedUpdate(DocumentEvent e) {
		showHistory();
		logger.debug(" In HistoryTextField.changedUpdate : ");
	}

	/**
	 * Invoked when the text is added
	 * @param e - document event
	 */
	public void insertUpdate(DocumentEvent e) {
		showHistory();
		logger.debug(" In HistoryTextField.insertUpdate : ");
	}

	/**
	 * Invoked when the text is removed
	 * @param e - document event
	 */
	public void removeUpdate(DocumentEvent e) {
		showHistory();
		logger.debug(" In HistoryTextField.removeUpdate : ");
	}

	
	public void focusGained(FocusEvent e) {
		showHistory();
		logger.debug(" In HistoryTextField.focusGained : ");
	}

	public void focusLost(FocusEvent e) {
		if (!getText().equals("")) {
			if (history.contains(getText())) {
				if (history.size() == maxHistorySize) {
					history.remove(getText());
				}
			} 
			else {
				history.remove(getText());
			}
			history.add(getText());
		}
		hideHistory();
		logger.debug(" In HistoryTextField.focusLost : ");
	}

	/*
	 * Invoked when one of the JMenuItem(s) in JPopupMenu is selected
	 * @param e Description of the Parameter
	 * @since 1.0
	 */
	public void actionPerformed(ActionEvent e) {
		menuItem = (JMenuItem) e.getSource();
		setText(selectedText = menuItem.getText());
		HistoryTextField.this.history.addFrequentHistory(menuItem.getText());
		if(isToFireActionPerformed()){
			HistoryTextField.this.fireActionPerformed();
		}
	}


	/*
	 * Invoked when the mouse enters one of the JMenuItem(s) in JPopupmenu.
	 * @param e Description of the Parameter
	 * @since 1.0
	 */
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			JMenuItem item = (JMenuItem)e.getSource();
			item.setBackground(Color.YELLOW);
		}
	}

	
	public void mouseExited(MouseEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			JMenuItem item = (JMenuItem)e.getSource();
			item.setBackground(getBackground());
		}
	}

	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseClicked(MouseEvent e) {
	}


	
	/**
	 * @return the isToFireActionPerformed
	 */
	public boolean isToFireActionPerformed() {
		return isToFireActionPerformed;
	}

	/**
	 * @param isToFireActionPerformed the isToFireActionPerformed to set
	 */
	public void setToFireActionPerformed(boolean isToFireActionPerformed) {
		this.isToFireActionPerformed = isToFireActionPerformed;
	}

	/** 
	 * @return the isEnterSaveHistory
	 */
	public boolean isEnterSaveHistory() {
		return isEnterSaveHistory;
	}

	/**
	 * @param isEnterSaveHistory the isEnterSaveHistory to set
	 */
	public void setEnterSaveHistory(boolean isEnterSaveHistory) {
		this.isEnterSaveHistory = isEnterSaveHistory;
	}
	/**
	 * @return the history
	 */
	public HistoryModel getHistory() {
		return history;
	}
	
	public void addToHistory(JTextField txtField, String value){
    	logger.debug("In HistoryTextField adding value to history: "+value);
    	history.addToHistory(value);
    }
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		ArrayList list = new ArrayList();
		list.add("com.mbi.ejb.insurance.groupcode.GroupCodeToGroupStrategyFrequencyDataBuildServer");
		list.add("paric");
		list.add("patrick");
		list.add("patient");
		list.add("patientData");
		
		final HistoryTextField txt = new HistoryTextField(HistoryFactory.getInstance(HistoryFactory.TESTCLASS_HISTORY),25);
		panel.add(new JLabel("History Text Demo"));
		panel.add(txt);
		panel.add(new JButton("Close"));
		frame.add(panel);
		frame.setSize(300,300);
		frame.setLocation(200,400);
		frame.addWindowListener(new WindowAdapter() {
		 	@Override
			public void windowClosing(WindowEvent e) {
				//txt.popup.setVisible(false); 
				System.exit(0);
			}
		 });
		frame.pack();
		frame.show();
	}

}
