package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import common.CustomFileFilter;
import common.ProcessUtil;

public class HedgeAndBarrierPanel extends JPanel
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
	private JTabbedPane tabbedPane;
	private JTable table;

	Object[][] data = null;

	JLabel addBarrier;

	private int barrierCount = 0;

	public HedgeAndBarrierPanel(MainGui main) {
		super();

		setVisible(true);
		setLayout(null);
		init();

		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 0, 310, 350);
		tabbedPane.setBackground(Color.WHITE);

		JPanel hedgePanel = createHedgeTab();
		String tab1Lbl ="VAST";
		tabbedPane.add(tab1Lbl, hedgePanel);

		JPanel barrierPanel = createDynamicBarrierTab();
		String tab2Lbl = "Dynamic Barrier";
		tabbedPane.add(tab2Lbl, barrierPanel);
		tabbedPane.setEnabledAt(1, false);

		add(tabbedPane);
		this.mainGui = main;

		setBackground(Color.WHITE);

		revalidate();
		repaint();
	}

	public void enableComponents(boolean value) {
		tabbedPane.setEnabledAt(1, value);
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

	private JPanel createDynamicBarrierTab() {
		JPanel barrierPanel = new JPanel();
		barrierPanel.setBackground(Color.WHITE);
		barrierPanel.setLayout(null);
		barrierPanel.setBounds(0, 0, 310, 350);
		String stt = "#";
		String pos = "Location";
		String speed = "Speed";
		String vector = "Original direction";

		String[] columnNames = { stt, pos, speed, vector };

		if (data == null) {
			data = new String[0][4];
		}
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(columnNames);
		table = new JTable();
		table.setModel(dtm);
		table.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		TableColumn column1 = table.getColumnModel().getColumn(0);

		column1.setPreferredWidth(30);
		TableColumn column2 = table.getColumnModel().getColumn(1);
		column2.setPreferredWidth(50);
		TableColumn column3 = table.getColumnModel().getColumn(2);
		column3.setPreferredWidth(50);

		table.setRowHeight(25);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table
				.getTableHeader().getDefaultRenderer();
		headerRenderer.setHorizontalAlignment(0);
		for (int i = 0; i < columnNames.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(i)
					.setHeaderRenderer(headerRenderer);
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getVerticalScrollBar()
				.setPreferredSize(new Dimension(10, 0));
		scrollPane.setAutoscrolls(true);
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		table.setFillsViewportHeight(true);

		scrollPane.setBounds(0, 0, 305, 190);
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		scrollPane.setBorder(BorderFactory.createEmptyBorder(1, 0, 1, 0));
		barrierPanel.add(scrollPane);

		String addBarrierTxt = "Add new";
		addBarrier = new JLabel(addBarrierTxt, SwingConstants.CENTER);
		addBarrier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		addBarrier.addMouseListener(this);
		addBarrier.setBounds(105, 210, 100, 25);
		addBarrier.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		barrierPanel.add(addBarrier);

		return barrierPanel;
	}

	private JPanel createHedgeTab() {
		JPanel hedgePanel = new JPanel();
		hedgePanel.setBackground(Color.WHITE);
		hedgePanel.setLayout(null);
		hedgePanel.setBounds(0, 0, 310, 350);

		middleValueLbl = new JLabel("Intermediaries");
		middleValueLbl.setBounds(20, 5, 200, 25);
		hedgePanel.add(middleValueLbl);

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
		compW.setBounds(80, 5, 150, 50);
		compW.setBackground(Color.WHITE);
		hedgePanel.add(compW);

		JSeparator s = new JSeparator();
		s.setBounds(20, 60, 270, 25);
		hedgePanel.add(s);

		String html = "<html><i><b>\u00B5</b> (GIATU)<i>:</html>";

		String lessText = html.replace("GIATU", "Little");
		lessLbl = new JLabel(lessText, SwingConstants.RIGHT);
		lessLbl.setBounds(10, 75, 70, 25);
		hedgePanel.add(lessLbl);
		lessField = new JTextField();
		lessField.setBounds(85, 75, 40, 25);
		lessField.addKeyListener(this);
		lessField.setText("" + less);
		lessField.setHorizontalAlignment(JTextField.CENTER);
		hedgePanel.add(lessField);

		String possibleText = html.replace("GIATU",
				"Ability");
		possibleLbl = new JLabel(possibleText, SwingConstants.RIGHT);
		possibleLbl.setBounds(10, 105, 70, 25);
		hedgePanel.add(possibleLbl);
		possibleField = new JTextField();
		possibleField.setBounds(85, 105, 40, 25);
		possibleField.addKeyListener(this);
		possibleField.setText("" + possible);
		possibleField.setHorizontalAlignment(JTextField.CENTER);
		hedgePanel.add(possibleField);

		String moreText = html.replace("GIATU", "Than");
		moreLbl = new JLabel(moreText, SwingConstants.RIGHT);
		moreLbl.setBounds(175, 75, 70, 25);
		hedgePanel.add(moreLbl);
		moreField = new JTextField();
		moreField.setBounds(250, 75, 40, 25);
		moreField.setText("" + more);
		moreField.setHorizontalAlignment(JTextField.CENTER);
		moreField.addKeyListener(this);
		hedgePanel.add(moreField);

		String veryText = html.replace("GIATU", "Very");
		verLbl = new JLabel(veryText, SwingConstants.RIGHT);
		verLbl.setBounds(175, 105, 70, 25);
		hedgePanel.add(verLbl);
		veryField = new JTextField();
		veryField.setBounds(250, 105, 40, 25);
		veryField.setText("" + very);
		veryField.setHorizontalAlignment(JTextField.CENTER);
		veryField.addKeyListener(this);
		hedgePanel.add(veryField);

		middleValueField = new JTextField();
		middleValueField.setBounds(250, 5, 40, 25);
		middleValueField.setText("" + middleVal);
		middleValueField.setEditable(false);
		middleValueField.setHorizontalAlignment(JTextField.CENTER);
		hedgePanel.add(middleValueField);

		JSeparator s1 = new JSeparator();
		s1.setBounds(20, 145, 270, 25);
		hedgePanel.add(s1);
		
		//lấy đường link của dữ liệu mẫu
		sampleDataLbl = new JLabel(
				"Sample data",
				SwingConstants.RIGHT);
		sampleDataLbl.setBounds(15, 160, 65, 25);
		hedgePanel.add(sampleDataLbl);

		sampleDataField = new JTextField();
		sampleDataField.setBounds(85, 160, 150, 25);
		sampleDataField.setText(sampleData);
		hedgePanel.add(sampleDataField);

		//lấy link trực tiếp 
		browser = new JLabel("...", SwingConstants.CENTER);
		browser.setBounds(250, 160, 40, 25);
		browser.setBorder(BorderFactory.createLineBorder(Color.RED));
		browser.addMouseListener(this);
		browser.setBackground(Color.WHITE);
		hedgePanel.add(browser);
		
		
		String btnText ="Update";
		update = new JLabel(btnText, SwingConstants.CENTER);
		update.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JSeparator s2 = new JSeparator();
		s2.setBounds(20, 195, 270, 25);
		hedgePanel.add(s2);

		update.setBounds(105, 210, 100, 25);
		update.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		update.addMouseListener(this);
		update.setBackground(Color.WHITE);
		hedgePanel.add(update);

		return hedgePanel;
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

		if (e.getSource() == addBarrier) {
			new AdDynamicBarrierPanel(mainGui, this);
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
			showValueInvalidError("Very");
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
			showValueInvalidError("Ít");
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
		ProcessUtil.setSampleData(sampleDataField.getText());

		JOptionPane.showMessageDialog(mainGui, successMessage);
	}

	private void showValueInvalidError(String name) {
		String warningTitle = "Warning";
		String message = "<html>The value <i><b>\u00B5</b>(GIATU)</i> illegal</html>";
		String veryErrorMessage = message.replace("GIATU", name);
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
		} else if (e.getSource() == addBarrier) {
			component = addBarrier;
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
		} else if (e.getSource() == addBarrier) {
			component = addBarrier;
		}
		if (component != null) {
			component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			component.setOpaque(true);
			component.setBackground(Color.WHITE);
		}
	}

	public void setComponentEnable(boolean value) {
		lessField.setEditable(value);
		possibleField.setEditable(value);
		moreField.setEditable(value);
		veryField.setEditable(value);
		sampleDataField.setEditable(value);

		compW.setEnabled(value);
		browser.setEnabled(value);
		update.setEnabled(value);
		addBarrier.setEnabled(value);
		if (value) {
			update.addMouseListener(this);
			browser.addMouseListener(this);
			addBarrier.addMouseListener(this);
		} else {
			update.removeMouseListener(this);
			browser.removeMouseListener(this);
			addBarrier.removeMouseListener(this);
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

	public int addDynamicBarrier(int x, int y, int speed, int direction) {
		if (x >= mainGui.getAreaSize() || y > mainGui.getAreaSize()) {
			return 1;
		}
		String sample = "[" + x + ", " + y + "]";
		for (int i = 0; i < barrierCount; i++) {
			String test = table.getModel().getValueAt(i, 1).toString();
			if (sample.equals(test)) {
				return 2;
			}
		}
		boolean result = mainGui.addDynamicBarrier(x, y, speed, direction);
		if (!result) {
			return 1;
		}
		barrierCount += 1;

		TableModel tableModel = table.getModel();
		DefaultTableModel model = (DefaultTableModel) tableModel;
		String leftRight = "Horizontal";
		String upDown = "Vertical";
		String directStr = "Horizontal";
		if (direction == 1) {
			directStr = upDown + "(1)";
		} else {
			directStr = leftRight + "(0)";
		}
		model.addRow(new Object[] { barrierCount, "[" + x + ", " + y + "]",
				speed, directStr });

		return 0;
		// 0: OK
		// 1: vi tri khong hop le
		// 2: vi tri da ton tai
		// 3: khong xac dinh
	}

	public void enabledComponent() {
		tabbedPane.setEnabledAt(1, true);
	}
}
