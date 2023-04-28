/**
 * 
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 * @author HuyLV
 *
 */
public class DynamicBarrierPanel extends JPanel implements ChangeListener, KeyListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MainGui mainGui;

	// JLabel middleValueLbl, lessLbl, possibleLbl, moreLbl, verLbl,
	// sampleDataLbl;

	JLabel addBarrier;

	private int barrierCount = 0;
	private JTabbedPane tabbedPane; // Panel Dynamic
	private JTable table; // table vật cản di động
	Object[][] data = null; // Đối tượng của bảng

	public static void main(String[] args) {
		// Lock feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
		}
		JFrame f = new JFrame("Dynamic Barrier");
		f.setSize(365, 350);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);

		DynamicBarrierPanel dynamicBarrierPanel = new DynamicBarrierPanel(null);
		dynamicBarrierPanel.setBounds(20, 20, 310, 275);
		f.add(dynamicBarrierPanel);
		dynamicBarrierPanel.enableComponents(true);

		f.revalidate();
		f.repaint();
	}

	public DynamicBarrierPanel(MainGui main) {
		super();
		setVisible(true);
		setLayout(null);
		// Tabpanel chính
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 0, 310, 275);
		tabbedPane.setBackground(Color.WHITE);

		JPanel barrierPanel = createDynamicBarrierTab(); // tạo panel vật cản di động
		String tab2Lbl = "Dynamic Barrier";
		tabbedPane.add(tab2Lbl, barrierPanel);
		add(tabbedPane);
		this.mainGui = main;

		setBackground(Color.WHITE);

		revalidate();
		repaint();
	}

	private JPanel createDynamicBarrierTab() {
		JPanel barrierPanel = new JPanel();
		barrierPanel.setBackground(Color.WHITE);
		barrierPanel.setLayout(null);
		barrierPanel.setBounds(0, 0, 310, 275);
		String stt = "#";
		String pos = "Location";
		String speed = "Speed";
		String vector = "Direction";

		String[] columnNames = { stt, pos, speed, vector };

		if (data == null) {
			data = new String[0][4];
		}
		// Tạo bảng
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

		DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader()
				.getDefaultRenderer();
		headerRenderer.setHorizontalAlignment(0);
		for (int i = 0; i < columnNames.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		scrollPane.setAutoscrolls(true);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		table.setFillsViewportHeight(true);

		scrollPane.setBounds(0, 0, 305, 190);
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		scrollPane.setBorder(BorderFactory.createEmptyBorder(1, 0, 1, 0));
		barrierPanel.add(scrollPane);

		// Thêm barrier
		String addBarrierTxt = "Add New";
		addBarrier = new JLabel(addBarrierTxt, SwingConstants.CENTER);
		addBarrier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		addBarrier.addMouseListener(this);
		addBarrier.setBounds(105, 210, 100, 25);
		addBarrier.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		barrierPanel.add(addBarrier);

		return barrierPanel;
	}

	public void enableComponents(boolean value) {
		tabbedPane.setEnabledAt(0, value);
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

	// Thêm dynamic barrier trên panel
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
		boolean result = mainGui.addDynamicBarrier(x, y, speed, direction); // Thêm vật cản và kiểm tra vịt rí thêm có vật cản di động
		
		if (!result) {
			return 1; // Nếu có vật cản di động trả về 1
		}
		
		barrierCount += 1;

		//Thêm đối tượng vào bảng
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
		model.addRow(new Object[] { barrierCount, "[" + x + ", " + y + "]", speed, directStr });

		return 0;
		// 0: OK Thêm mới vật cản di động thành công
		// 1: Không thể thêm vật cản di động tại vị trí này
		// 2: vi tri da ton tai
		// 3: Không thêm được vật cản di động. Lỗi không xác định
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == addBarrier) {
			new AdDynamicBarrierPanel(mainGui, this);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		char ch = e.getKeyChar();

		if (!isNumber(ch) && !validatePoint(ch) && ch != '\b') {
			e.consume();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

}
