/**
 * 
 */
package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author HuyLV
 *
 */
public class AdDynamicBarrierPanel extends JFrame implements MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainGui mainGui;
	private DynamicBarrierPanel containerPane;
	private HedgeAndBarrierPanel containerPane2;
	private boolean check = false;
	private JTextField xField, yField, speedField;
	private JLabel xFieldLbl, speedFieldLbl, directionFieldLbl;
	private JComboBox<String> directionList;
	private JLabel addBarrier;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
		}
	}

	/**
	 * @wbp.parser.constructor
	 */
	public AdDynamicBarrierPanel(MainGui main, DynamicBarrierPanel con) {
		super();
		this.mainGui = main;
		this.containerPane = con;
		check =false;
		getContentPane().setLayout(null);
		setSize(325, 270);
		setLocationRelativeTo(mainGui);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBackground(Color.WHITE);
		JPanel pane = new JPanel();
		pane.setBounds(10, 10, 300, 185);
		pane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		pane.setBackground(Color.WHITE);
		pane.setLayout(null);
		String posTxt = "Vị trí:";
		xFieldLbl = new JLabel(posTxt);
		xFieldLbl.setBounds(20, 15, 200, 25);
		pane.add(xFieldLbl);
		JLabel xName = new JLabel("X = ");
		xName.setBounds(60, 40, 20, 25);
		pane.add(xName);
		xField = new JTextField();
		xField.setBounds(85, 40, 40, 25);
		xField.addKeyListener(this);
		xField.requestFocus();
		pane.add(xField);
		JLabel yName = new JLabel("Y = ");
		yName.setBounds(170, 40, 20, 25);
		pane.add(yName);
		yField = new JTextField();
		yField.setBounds(195, 40, 40, 25);
		yField.addKeyListener(this);
		pane.add(yField);

		JSeparator s1 = new JSeparator();
		s1.setBounds(20, 75, 250, 25);
		pane.add(s1);

		String speedTxt = "Tốc độ = ";
		speedFieldLbl = new JLabel(speedTxt);
		speedFieldLbl.setBounds(20, 90, 60, 25);
		speedFieldLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		pane.add(speedFieldLbl);
		speedField = new JTextField();
		speedField.setBounds(85, 90, 150, 25);
		speedField.addKeyListener(this);
		pane.add(speedField);

		JSeparator s2 = new JSeparator();
		s2.setBounds(20, 130, 250, 25);
		pane.add(s2);

		String leftRight = "Horizontal";
		String upDown = "Vertical";
		String[] directionListName = { leftRight, upDown };
//		directionList = new JComboBox<>(directionListName);

		String directionFieldTxt = "Direction = ";
		directionFieldLbl = new JLabel(directionFieldTxt);
		directionFieldLbl.setBounds(20, 145, 60, 25);
		directionFieldLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		pane.add(directionFieldLbl);
		directionList.setBounds(85, 145, 150, 25);
		pane.add(directionList);

		String addBarrierTxt = "Add new";
		addBarrier = new JLabel(addBarrierTxt, SwingConstants.CENTER);
		addBarrier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		addBarrier.addMouseListener(this);
		addBarrier.setBounds(105, 205, 100, 25);
		addBarrier.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		JPanel container = new JPanel();
		container.setBounds(0, 0, 320, 275);
		container.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		container.setBackground(Color.WHITE);
		container.setLayout(null);
		container.add(pane);
		container.add(addBarrier);

		getContentPane().add(container);

		String title = "Add Dynamic Barrier";
		setTitle(title);

		revalidate();
		repaint();
	}
	public AdDynamicBarrierPanel(MainGui main, HedgeAndBarrierPanel con) {
		super();

		this.mainGui = main;
		this.containerPane2 = con;
		check =true;
		getContentPane().setLayout(null);
		setSize(325, 270);
		setLocationRelativeTo(mainGui);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBackground(Color.WHITE);
		JPanel pane = new JPanel();
		pane.setBounds(10, 10, 300, 185);
		pane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		pane.setBackground(Color.WHITE);
		pane.setLayout(null);
		String posTxt = "Vị trí";
		xFieldLbl = new JLabel(posTxt);
		xFieldLbl.setBounds(20, 15, 200, 25);
		pane.add(xFieldLbl);
		JLabel xName = new JLabel("X = ");
		xName.setBounds(60, 40, 20, 25);
		pane.add(xName);
		xField = new JTextField();
		xField.setBounds(85, 40, 40, 25);
		xField.addKeyListener(this);
		xField.requestFocus();
		pane.add(xField);
		JLabel yName = new JLabel("Y = ");
		yName.setBounds(170, 40, 20, 25);
		pane.add(yName);
		yField = new JTextField();
		yField.setBounds(195, 40, 40, 25);
		yField.addKeyListener(this);
		pane.add(yField);

		JSeparator s1 = new JSeparator();
		s1.setBounds(20, 75, 250, 25);
		pane.add(s1);

		String speedTxt = "Speed";
		speedFieldLbl = new JLabel(speedTxt);
		speedFieldLbl.setBounds(20, 90, 60, 25);
		speedFieldLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		pane.add(speedFieldLbl);
		speedField = new JTextField();
		speedField.setBounds(85, 90, 150, 25);
		speedField.addKeyListener(this);
		pane.add(speedField);

		JSeparator s2 = new JSeparator();
		s2.setBounds(20, 130, 250, 25);
		pane.add(s2);

		String leftRight = "Horizontal";
		String upDown = "Vertical";
		String[] directionListName = { leftRight, upDown };
		directionList = new JComboBox<>(directionListName);

		String directionFieldTxt = "Direction = ";
		directionFieldLbl = new JLabel(directionFieldTxt);
		directionFieldLbl.setBounds(20, 145, 60, 25);
		directionFieldLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		pane.add(directionFieldLbl);
		directionList.setBounds(85, 145, 150, 25);
		pane.add(directionList);

		String addBarrierTxt = "Add new";
		addBarrier = new JLabel(addBarrierTxt, SwingConstants.CENTER);
		addBarrier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		addBarrier.addMouseListener(this);
		addBarrier.setBounds(105, 205, 100, 25);
		addBarrier.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		JPanel container = new JPanel();
		container.setBounds(0, 0, 320, 275);
		container.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		container.setBackground(Color.WHITE);
		container.setLayout(null);
		container.add(pane);
		container.add(addBarrier);

		getContentPane().add(container);

		String title = "Add Dynamic Barrier";
		setTitle(title);

		revalidate();
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		char ch = e.getKeyChar();

		if (!isNumber(ch) && ch != '\b') {
			e.consume();
		}
	}

	private boolean isNumber(char ch) {
		return ch >= '0' && ch <= '9';
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == addBarrier) {
			adddynamicBarrier();
		}
	}

	
	// Thêm vật cản di dộng giao diện
	private void adddynamicBarrier() {
		String xStr = xField.getText();
		int x = parseInputValue(xStr);
		if (xStr == null || "".equals(xStr) || x == -1) {
			showValueInvalidError("x");
			return;
		}

		String yStr = yField.getText();
		int y = parseInputValue(yStr);
		if (yStr == null || "".equals(yStr) || y == -1) {
			showValueInvalidError("y");
			return;
		}

		String speedStr = speedField.getText();
		int speed = parseInputValue(speedStr);
		if (speedStr == null || "".equals(speedStr) || speed <= 0) {
			showValueInvalidError("speed");
			return;
		}

		int direction = directionList.getSelectedIndex();
		int result ;
		if(check==false){
		result = containerPane.addDynamicBarrier(x, y, speed, direction);
		}else {
			result = containerPane2.addDynamicBarrier(x, y, speed, direction);
		}
			
		switch (result) {
		case 0:
			String message = "Successfully Added New Dynamic Barrier";
			this.dispose();
			JOptionPane.showMessageDialog(mainGui, message);
			break;
		case 1:
			String message1 = "Failly Added New Dynamic Barrier At This Location";
			showResultError(message1);
			break;
		case 2:
			String message2 = "This position already exists";
			showResultError(message2);
			break;
		case 3:
			String message3 = "Failly Added New Dynamic Barrier. An Unspecified Error";
			showResultError(message3);
			break;

		default:
			break;
		}
	}
	
	private void showResultError(String message) {
		String warningTitle = "Warning";
		JOptionPane.showMessageDialog(mainGui, message, warningTitle, JOptionPane.WARNING_MESSAGE);
	}

	private int parseInputValue(String input) {
		int result = -1;
		try {
			result = Integer.parseInt(input);
		} catch (Exception e) {
		}
		return result;
	}

	private void showValueInvalidError(String name) {
		String warningTitle = "Warning";
		String message = "Input Value's Not Correct";
		String veryErrorMessage = message.replace("<i><b>\u00B5</b>(GIATU)</i>", "<b>" + name + "</b>");
		JOptionPane.showMessageDialog(mainGui, veryErrorMessage, warningTitle, JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel component = null;
		if (e.getSource() == addBarrier) {
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
		if (e.getSource() == addBarrier) {
			component = addBarrier;
		}
		if (component != null) {
			component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			component.setOpaque(true);
			component.setBackground(Color.WHITE);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
