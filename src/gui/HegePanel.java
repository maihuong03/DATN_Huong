package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import common.CustomFileFilter;
import common.ProcessUtil;

public class HegePanel extends JPanel
		implements ChangeListener, KeyListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5657755763146042383L;

	private static final int MIN_VALUE = 0;
	private static final int MAX_VALUE = 100;
	private static final int MIDD_VALUE = 50;
	private int initValue;

	// Gia tri trung gian cua dai so gia tu
	private JTextField middleValueField, lessField, possibleField, moreField,
			veryField, sampleDataField;
	private JSlider compW;

	private JLabel update, browser;
	private MainGui mainGui;

	private double middleVal, more, very, possible, less;
	private String sampleData;

	JLabel middleValueLbl, lessLbl, possibleLbl, moreLbl, verLbl, sampleDataLbl;
	TitledBorder titledBorder;

	public HegePanel(MainGui main) {
		super();

		this.mainGui = main;
		init();

		setVisible(true);
		titledBorder = BorderFactory
				.createTitledBorder("VAST");
		titledBorder.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		setBorder(titledBorder);
		setLayout(null);	

		middleValueLbl = new JLabel("Intermediaries");
		middleValueLbl.setBounds(20, 20, 200, 25);
		add(middleValueLbl);

		compW = new JSlider(JSlider.HORIZONTAL, MIN_VALUE, MAX_VALUE,
				initValue);
		compW.addChangeListener(this);
		compW.setMajorTickSpacing(5);
		compW.setPaintTicks(true);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
		labelTable.put(MIN_VALUE, new JLabel("0"));
		labelTable.put(MIDD_VALUE, new JLabel("0.5"));
		labelTable.put(MAX_VALUE, new JLabel("1"));
		compW.setLabelTable(labelTable);
		compW.setPaintLabels(true);
		compW.setBounds(80, 15, 150, 50);
		compW.setBackground(Color.WHITE);
		add(compW);

		JSeparator s = new JSeparator();
		s.setBounds(20, 80, 270, 25);
		add(s);

		String html = "<html><i><b>\u00B5</b> (GIATU)<i>:</html>";

		String lessText = html.replace("GIATU", "Ít");
		lessLbl = new JLabel(lessText, SwingConstants.RIGHT);
		lessLbl.setBounds(10, 100, 70, 25);
		add(lessLbl);
		lessField = new JTextField();
		lessField.setBounds(85, 100, 40, 25);
		lessField.addKeyListener(this);
		lessField.setText("" + less);
		lessField.setHorizontalAlignment(JTextField.CENTER);
		add(lessField);

		String possibleText = html.replace("GIATU",
				"Khả năng");
		possibleLbl = new JLabel(possibleText, SwingConstants.RIGHT);
		possibleLbl.setBounds(10, 130, 70, 25);
		add(possibleLbl);
		possibleField = new JTextField();
		possibleField.setBounds(85, 130, 40, 25);
		possibleField.addKeyListener(this);
		possibleField.setText("" + possible);
		possibleField.setHorizontalAlignment(JTextField.CENTER);
		add(possibleField);

		String moreText = html.replace("GIATU", "Than");
		moreLbl = new JLabel(moreText, SwingConstants.RIGHT);
		moreLbl.setBounds(175, 100, 70, 25);
		add(moreLbl);
		moreField = new JTextField();
		moreField.setBounds(250, 100, 40, 25);
		moreField.setText("" + more);
		moreField.setHorizontalAlignment(JTextField.CENTER);
		moreField.addKeyListener(this);
		add(moreField);

		String veryText = html.replace("GIATU", "Verry");
		verLbl = new JLabel(veryText, SwingConstants.RIGHT);
		verLbl.setBounds(175, 130, 70, 25);
		add(verLbl);
		veryField = new JTextField();
		veryField.setBounds(250, 130, 40, 25);
		veryField.setText("" + very);
		veryField.setHorizontalAlignment(JTextField.CENTER);
		veryField.addKeyListener(this);
		add(veryField);

		middleValueField = new JTextField();
		middleValueField.setBounds(250, 20, 40, 25);
		middleValueField.setText("" + middleVal);
		middleValueField.setEditable(false);
		middleValueField.setHorizontalAlignment(JTextField.CENTER);
		add(middleValueField);

		JSeparator s1 = new JSeparator();
		s1.setBounds(20, 170, 270, 25);
		add(s1);

		sampleDataLbl = new JLabel("Sample data", 
				SwingConstants.RIGHT);
		sampleDataLbl.setBounds(15, 185, 65, 25);
		add(sampleDataLbl);

		sampleDataField = new JTextField();
		sampleDataField.setBounds(85, 185, 150, 25);
		sampleDataField.setText(sampleData);
		add(sampleDataField);

		browser = new JLabel("...", SwingConstants.CENTER);
		browser.setBounds(250, 185, 40, 25);
		browser.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		browser.addMouseListener(this);
		browser.setBackground(Color.WHITE);
		add(browser);

		String btnText = "Update";
		update = new JLabel(btnText, SwingConstants.CENTER);
		update.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JSeparator s2 = new JSeparator();
		s2.setBounds(20, 220, 270, 25);
		add(s2);

		update.setBounds(105, 235, 100, 25);
		update.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		update.addMouseListener(this);
		update.setBackground(Color.WHITE);
		add(update);

		setBackground(Color.WHITE);

		revalidate();
		repaint();
	}

	private void init() {
		middleVal = ProcessUtil.getMiddleValue();
		initValue = (int) Math.round(100 * middleVal);
		more = ProcessUtil.getMoreValue();
		very = ProcessUtil.getVeryValue();
		possible = ProcessUtil.getPossibleValue();
		less = ProcessUtil.getLessValue();
		sampleData = ProcessUtil.getSampleData();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if (!source.getValueIsAdjusting()) {
			int value = (int) source.getValue();
			middleValueField.setText("" + value * 1.0 / 100);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char ch = e.getKeyChar();

		if (!isNumber(ch) && !validatePoint(ch) && ch != '\b') {
			e.consume();
		}
	}

	private boolean isNumber(char ch) {
		return ch >= '0' && ch <= '9';
	}

	private boolean validatePoint(char ch) {
		if (ch != '.') {
			return false;
		}

		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == update) {
			updateData();
		}

		if (e.getSource() == browser) {
			selectDataFile();
		}
	}

	private void selectDataFile() {
		final JFileChooser fc = new JFileChooser();
		String[] extensions = { "gt" };
		fc.setFileFilter(
				new CustomFileFilter(extensions, "Sample Data (*.gt)"));
		int returnVal = fc.showOpenDialog(mainGui);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String dataDir = file.getAbsolutePath();
			sampleDataField.setText(dataDir);
		} else {
			fc.setVisible(false);
		}
	}

	private void updateData() {
		middleVal = Double.parseDouble(middleValueField.getText());
		String middValueMessage = "Intermediate values ​​must be in the range (0, 1)";
		String warningTitle = "Warning";
		if (middleVal <= 0.0 || middleVal >= 1.0) {
			JOptionPane.showMessageDialog(mainGui, middValueMessage,
					warningTitle, JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			very = Double.parseDouble(veryField.getText());
		} catch (Exception ex) {
			showValueInvalidError("Verry");
			return;
		}

		try {
			more = Double.parseDouble(moreField.getText());
		} catch (Exception ex) {
			showValueInvalidError("Than");
			return;
		}

		try {
			possible = Double.parseDouble(possibleField.getText());
		} catch (Exception ex) {
			showValueInvalidError("Ability");
			return;
		}

		try {
			less = Double.parseDouble(lessField.getText());
		} catch (Exception ex) {
			showValueInvalidError("Little");
			return;
		}

		String message = "<html>Total value‹ <i><b>\u00B5</b></i> must be equal 1.0</html>";
		if ((less + more + possible + very) != 1.0) {
			JOptionPane.showMessageDialog(mainGui, message, warningTitle,
					JOptionPane.WARNING_MESSAGE);

			return;
		}
		sampleData = sampleDataField.getText();
		File sampleDataFile = new File(sampleData);
		String sampleDataDirError = "Sample data file path is not valid";
		if (sampleDataField.getText() == "" || !sampleDataFile.exists()) {
			JOptionPane.showMessageDialog(mainGui, sampleDataDirError,
					warningTitle, JOptionPane.WARNING_MESSAGE);
			return;
		}

		String successMessage = "The update is complete";
		ProcessUtil.setMiddleValue("" + middleVal);
		ProcessUtil.setLessValue("" + less);
		ProcessUtil.setPossibleValue("" + possible);
		ProcessUtil.setMoreValue("" + more);
		ProcessUtil.setVeryValue("" + very);

		JOptionPane.showMessageDialog(mainGui, successMessage);
	}

	private void showValueInvalidError(String name) {
		String warningTitle = "Warning";
		String message = "<html>The value <i><b>\u00B5</b>(GIATU)</i> illegal</html>";
		String veryErrorMessage = message.replace("GIATU",
				"Little");
		JOptionPane.showMessageDialog(mainGui, veryErrorMessage, warningTitle,
				JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel component = null;
		if (e.getSource() == update) {
			component = update;
		} else if (e.getSource() == browser) {
			component = browser;
		}
		if (component != null) {
			component.setCursor(new Cursor(Cursor.HAND_CURSOR));
			component.setOpaque(true);
			component.setBackground(new Color(228, 237, 244));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel component = null;
		if (e.getSource() == update) {
			component = update;
		} else if (e.getSource() == browser) {
			component = browser;
		}
		if (component != null) {
			component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			component.setOpaque(true);
			component.setBackground(Color.WHITE);
		}
	}

	public void setComponentEnable(boolean value) {
//		middleValueField.setEditable(value);
		lessField.setEditable(value);
		possibleField.setEditable(value);
		moreField.setEditable(value);
		veryField.setEditable(value);
		sampleDataField.setEditable(value);
		
		compW.setEnabled(value);
		browser.setEnabled(value);
		update.setEnabled(value);
		if(value){
			update.addMouseListener(this);
			browser.addMouseListener(this);
		} else{
			update.removeMouseListener(this);
			browser.removeMouseListener(this);
		}
		
		this.revalidate();
		this.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
