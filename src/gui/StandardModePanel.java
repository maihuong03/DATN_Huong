/**
 * 
 */
package gui;

import java.awt.Color;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import common.ProcessUtil;

/**
 * @author Huong
 * @category Mode xử lý cơ bản
 */
public class StandardModePanel extends JPanel implements ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JSlider compW; // thanh thời gian xử lý
	private JTextField timeProcess;
	private static final int MIN_VALUE = 0;
	private static final int MAX_VALUE = 1000;
	private static final int MIDD_VALUE = 500;
	private int initValue;
	TitledBorder titledBorder;
	private JLabel timeProcessLbl;

	public static void main(String[] args) {
		// look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
		}
		JFrame f = new JFrame("STC Online");
		f.setSize(365, 350);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);

		StandardModePanel panelThongTinCheDo = new StandardModePanel();
		panelThongTinCheDo.setBounds(20, 20, 310, 275);
		panelThongTinCheDo.setComponentEnable(false);
		f.add(panelThongTinCheDo);

		f.revalidate();
		f.repaint();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public StandardModePanel() {
		super();
		init(); // Thời gian bắt đầu

		String titleBorderText = "Configure standard mode";
		titledBorder = BorderFactory.createTitledBorder(titleBorderText);
		titledBorder.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		setBorder(titledBorder);
		setBackground(Color.WHITE);
		setLayout(null);

		String timeProcessTxt = "TProcessing Time:";
		timeProcessLbl = new JLabel(timeProcessTxt);
		timeProcessLbl.setBounds(20, 35, 200, 25);
		add(timeProcessLbl);

		compW = new JSlider(JSlider.HORIZONTAL, MIN_VALUE, MAX_VALUE, initValue);
		compW.addChangeListener(this);
		compW.setMajorTickSpacing(50);
		compW.setPaintTicks(true);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
		labelTable.put(MIN_VALUE, new JLabel("0"));
		labelTable.put(MIDD_VALUE, new JLabel("500"));
		labelTable.put(MAX_VALUE, new JLabel("1000"));
		compW.setLabelTable(labelTable);
		compW.setPaintLabels(true);
		compW.setBounds(35, 70, 180, 50);
		compW.setBackground(Color.WHITE);
		add(compW);

		timeProcess = new JTextField();
		timeProcess.setBounds(220, 70, 40, 25);
		timeProcess.setEditable(false);
		timeProcess.setText("" + initValue);
		add(timeProcess);

		JLabel ti = new JLabel("<html><i>(ms)</i></html>");
		ti.setBounds(265, 70, 40, 25);
		add(ti);

		revalidate();
		repaint();
	}

	private void init() {
		initValue = ProcessUtil.getStandardProcessTime();
	}

	public int getStandardWorkingTime() {
		int result = 500;
		String timeStr = timeProcess.getText();
		try {
			result = Integer.parseInt(timeStr);
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if (!source.getValueIsAdjusting()) {
			int value = (int) source.getValue();
			timeProcess.setText("" + value);
			ProcessUtil.setStandardProcessTime("" + value);
		}

	}
	
	public void setComponentEnable(boolean value) {
		compW.setEnabled(value);
		revalidate();
		repaint();
	}

}
