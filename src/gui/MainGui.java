package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PublicKey;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.examples.hssf.usermodel.NewLinesInCells;
import org.apache.poi.xdgf.usermodel.shape.exceptions.StopVisitingThisBranch;

import common.ProcessUtil;
import cstt.SuKienForm;
import cstt.suyDien2;
import hcstt.Robot;
import newone.MoreOption;
import newone.PinMenu;
import newone.ThemLichLamViec;
import newone.YesNo;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import javax.swing.JList;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;

public class MainGui extends JFrame implements ActionListener, MouseListener, ItemListener {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// private static final long serialVersionUID = 1L;
	/**
	 * GUI main chính của chương trình
	 */

	// Tab
	private JMenu fileMenu, csttMenu;
	private JMenuItem newRobot, qlCstt;
	private JMenuItem exit;
	private JCheckBoxMenuItem showDustValue;
	JLabel start, initWorkArea, initDust, restart;
	private JLabel areaInputLabel;

	private Robot robot;

	private PlaceholderTextField areaSizeInput; //
	private int areaSize;

	private JComboBox modeList; //

	public static StatusBar statusBar; // Statusbar hiện thị thông tin ở dưới
	private JPanel pane;

	private StandardModePanel standard;
	private StandardModePanel standardModePan;
	private DynamicBarrierPanel dynamicBarrierPan;
	private HegePanel hedgePan; // DSGT
	private HedgeAndBarrierPanel hedgeAndBarrierPan; // DSGT + vật cản di động
	private ReportPanel report;

	public static final int STANDARD = 0; // STC chuẩn
	public static final int STANDARD_MODE = 1; // STC full
	public static final int DYNAMIC_MODE = 2; // STC full + dynamic
	public static final int DSGT_MODE = 3; // STC full + DSGT
	public static final int DSGT_DINAYMIC_MODE = 4; // STC full + DSGT + dynamic
	protected static final String a = null;

	private int currentMode = DSGT_DINAYMIC_MODE;
	private int showDustValueProp = 0;

	private JLabel modeLbl;
	private JPanel menu;
	private JLabel showTime;

	private static int doTime, numDoTime;
	private static int count = 0;
	private static int sumOfWork;
	private static int isStart = 0;

	// time
	private static int hour, minute, second, PM_AM;

	private static int[] listOfNum;
	private static int[] listOfMode;
	private static int[] listOfHour;
	private static int[] listOfMinute;
	private static int[] listOfPM_AM;
	private static String[] listOfInfo;

	private static int[] listOfDustvalue;
	private JLabel showDoBan;
	private static int sumOfDust;
	private static double luongPinTinhToan;
	private static double luongPin = 1000;
	private static double luongPinSetUp = luongPin;
	private static JLabel luongPinBanDau;
	private static boolean isPowerCanRun = true;
	private static JButton btnShowPinImage;
	private static int pinMode = 0;

	private static int modeVatCanInt;
	private static int numOfHuuCo;
	private static int numOfVoCo;
	private static int numOfTaiChe;
	private static int testcellpane;
	private static JLabel labelCount;
	private static int sizeOfMap;
	private static int loaiVoCo, loaiTaiche, loaiHuuCo;
	private static int soundMode = 0;

	private static boolean isWriteToExcel = false;
	private static JRadioButton checkExcel;
	private static String linkFileExcel = "src\\newone\\file1.xlsx";
	private JTextField showLinkExcelTF;

	//
	private static int numOfSoda = 0, numOfCoffe = 0, numOfWater = 0;
	private static int sumOfSoda = 0, sumOfCoffe = 0, sumOfWater = 0;
	private JTextField waterText;
	private JTextField coffeText;
	private JTextField sodaText;
	private static String[] orderArray = { "o", "o", "o", "o", "o", "o", "o" };
	private static String orderString = "Chưa order gì cả :<";
	private static ArrayList<String> saveRule = new ArrayList<>();
	private JLabel labelname2;
	private static int point = 0;
	private static int eAngry = 0;
	private static int eBad = 0;
	private static int eNormal = 0;
	private static int eHappy = 0;
	private static int eImpressive = 0;
	private JPanel BtnPanel;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private static int bgMode = 1;
	JLabel showKetQua, ruleLabel, lblNewLabel_5, lblNewLabel_1, lblNewLabel_2, lblNewLabel, lblNewLabel_3, orderBG;
	JButton reloadButton, getLinkExcelBtn, modeVatCanBtn, soundBtn, loadLabelRuleBtn, btnNewButton_1, btnNewButton;
	JPanel panelOrder;
	JScrollPane scrPane;
	private JPanel panelWork;
	private JLabel bgMain;
	private JLabel workBG;
	private JLabel menuBG;

	public static String getLinkFileExcel() {
		return linkFileExcel;
	}

	public static void setLinkFileExcel(String linkFileExcel) {
		MainGui.linkFileExcel = linkFileExcel;
	}

	public static int getLoaiVoCo() {
		return loaiVoCo;
	}

	public static void setLoaiVoCo(int loaiVoCo) {
		MainGui.loaiVoCo = loaiVoCo;
	}

	public static int getLoaiTaiche() {
		return loaiTaiche;
	}

	public static void setLoaiTaiche(int loaiTaiche) {
		MainGui.loaiTaiche = loaiTaiche;
	}

	public static int getLoaiHuuCo() {
		return loaiHuuCo;
	}

	public static void setLoaiHuuCo(int loaiHuuCo) {
		MainGui.loaiHuuCo = loaiHuuCo;
	}

	public static int getTestcellpane() {
		return testcellpane;
	}

	public static void setTestcellpane(int testcellpane) {
		MainGui.testcellpane = testcellpane;
	}

	public static void main(String[] args) {
		MainGui gui = new MainGui();
		try {
			SuKienForm suKienForm = new SuKienForm();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gui.validate();
	}

	// frame chính
	/**
	 * 
	 */
	public MainGui() {
		super("ROBOT"); // tên app
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.width;
		int screenHeight = (int) screenSize.height;
		setBackground(Color.WHITE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
		}

		// Icon
		this.setIconImage(new ImageIcon("resources/icon.png").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
//		this.setSize(1297, 674);
		this.setSize(1031, 700);
		this.setResizable(false); // có thay đổi size hay k
		setVisible(true);
		init();
		this.setJMenuBar(createMenu());
		this.setLocationRelativeTo(null);
		Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
		
	    Image bgImage = Toolkit.getDefaultToolkit().createImage("./sources/bg.png");


		// 1) chinh mau panel ben phai
		menu = new JPanel();
		menu.setBounds(659, 33, 351, 607);
		menu.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		menu.setLayout(null);
		menu.setBackground(Color.WHITE);
//		menu.setVisible(false);
		//
		String modeLblText = "Working Mode:";
		modeLbl = new JLabel(modeLblText);
		modeLbl.setBounds(20, 11, 310, 25);
		menu.add(modeLbl);

		//
		String startText = "START";
		start = new JLabel(startText, SwingConstants.CENTER);
		start.setFont(new Font("Tahoma", Font.PLAIN, 12));
		start.setBounds(6, 469, 164, 35);
		start.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		start.setEnabled(false);
		menu.add(start);

		String restartText = "RESTART";
		restart = new JLabel(restartText, SwingConstants.CENTER);
		restart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		restart.setBounds(177, 469, 164, 35);
		restart.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		restart.setEnabled(false);
		menu.add(restart);

		// 3) nhập kích thước vùng làm việc
		String areaInputLblText = "Working area size:";
		areaInputLabel = new JLabel(areaInputLblText);
		areaInputLabel.setBounds(20, 61, 310, 25);
		menu.add(areaInputLabel);

		// 4) nhập kích thước bản đồ
		areaSizeInput = new PlaceholderTextField();
		String areaSizeInputText = "Size...";
		areaSizeInput.setPlaceholder(areaSizeInputText);
		areaSizeInput.setBounds(20, 86, 310, 25);
		areaSizeInput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ENTER) {
					initMap();
				}
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		menu.add(areaSizeInput);

		// 5) khởi tạo bản đồ
		String initWorkAreaText = "Create Map";
		initWorkArea = new JLabel(initWorkAreaText, SwingConstants.CENTER);
		initWorkArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		initWorkArea.setBounds(20, 116, 95, 25);
		initWorkArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		initWorkArea.addMouseListener(this);
		menu.add(initWorkArea);

		// 6) khởi tạo bản đồ bẩn
		String initDustText = "Make";
		initDust = new JLabel(initDustText, SwingConstants.CENTER);
		initDust.setFont(new Font("Tahoma", Font.PLAIN, 12));
		initDust.setBounds(126, 116, 95, 25);
		initDust.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		initDust.setEnabled(false);
		menu.add(initDust);
		int posY = 150;
		standard = new StandardModePanel();
		standard.setBounds(20, posY, 312, 300);

		standardModePan = new StandardModePanel();
		standardModePan.setBounds(20, posY, 312, 300);

		dynamicBarrierPan = new DynamicBarrierPanel(this);
		dynamicBarrierPan.setBounds(20, posY, 310, 300);

		hedgePan = new HegePanel(this);
		hedgePan.setBounds(20, posY, 310, 300);

		hedgeAndBarrierPan = new HedgeAndBarrierPanel(this);
		hedgeAndBarrierPan.setBounds(20, posY, 310, 300);

		switch (currentMode) {
		case STANDARD:
			menu.add(standard);
			break;
		case STANDARD_MODE:
			menu.add(standardModePan);
			break;
		case DYNAMIC_MODE:
			menu.add(dynamicBarrierPan);
			break;
		case DSGT_MODE:
			menu.add(hedgePan);
			break;
		case DSGT_DINAYMIC_MODE:
			menu.add(hedgeAndBarrierPan);
			break;
		default:
			break;
		}

		// 2) chế độ làm việc
		String standard = "1.Knowledge Base + STC-Online Standard";
		String standardModeName = "2.Knowledge Base + STC-full Online ";
		String dynamicBarierModeName = "3.Knowledge Base + STC-full & Dynamic Barries";
		String hegemodeName = "4.Knowledge Base + STC-full + VAST ";
		String hegeDynamicName = "5.Knowledge Base + STC-full + VAST & Knowledge Base";
		String[] modes = { standard, standardModeName, dynamicBarierModeName, hegemodeName, hegeDynamicName };

		modeList = new JComboBox(modes);
		modeList.setBounds(20, 36, 310, 25);
		modeList.addItemListener(this);
		modeList.setSelectedIndex(4); // chọn mặc định option là 5
		menu.add(modeList);
		ImageIcon i = new ImageIcon("resources/robotGif2.gif");
		
				// set màn hình chính
				pane = new JPanel();
				pane.setBounds(4, 2, 638, 638);
				pane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
				JLabel gif = new JLabel();
				gif.setIcon(i);
				gif.setBounds(0, 0, 584, 584);
				pane.add(gif);
				getContentPane().add(pane);

		panelOrder = new JPanel();
		panelOrder.setBounds(659, 33, 351, 607);
		getContentPane().add(panelOrder);
		panelOrder.setBorder(null);
		panelOrder.setLayout(null);
		panelOrder.setVisible(false);

		lblNewLabel = new JLabel("Water:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 70, 80, 14);
		panelOrder.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Coffe:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 29, 80, 14);
		panelOrder.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Soda:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 111, 80, 14);
		panelOrder.add(lblNewLabel_2);
		// orderLabelTest.setVisible(false);

		btnNewButton = new JButton("Enter");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Integer.parseInt(waterText.getText()) >= 0) {
					MainGui.setNumOfWater(Integer.parseInt(waterText.getText()));
				} else {
					MainGui.setNumOfWater(0);
				}
				if (Integer.parseInt(coffeText.getText()) >= 0) {
					MainGui.setNumOfCoffe(Integer.parseInt(coffeText.getText()));
				} else {
					MainGui.setNumOfCoffe(0);
				}
				if (Integer.parseInt(sodaText.getText()) >= 0) {
					MainGui.setNumOfSoda(Integer.parseInt(sodaText.getText()));
				} else {
					MainGui.setNumOfSoda(0);
				}

			}
		});
		btnNewButton.setBounds(230, 22, 111, 112);
		panelOrder.add(btnNewButton);

		waterText = new JTextField();
		waterText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		waterText.setText("0");
		waterText.setBounds(100, 63, 120, 30);
		panelOrder.add(waterText);
		waterText.setColumns(10);

		coffeText = new JTextField();
		coffeText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		coffeText.setText("0");
		coffeText.setBounds(100, 22, 120, 30);
		panelOrder.add(coffeText);
		coffeText.setColumns(10);

		sodaText = new JTextField();
		sodaText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sodaText.setText("0");
		sodaText.setBounds(100, 104, 120, 30);
		panelOrder.add(sodaText);
		sodaText.setColumns(10);

		lblNewLabel_3 = new JLabel("More: ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 162, 80, 17);
		panelOrder.add(lblNewLabel_3);

		JButton moreOptionBtn = new JButton("More");
		moreOptionBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		moreOptionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MoreOption moreOption = new MoreOption();
				moreOption.setVisible(true);
			}
		});
		moreOptionBtn.setBounds(100, 155, 120, 30);
		panelOrder.add(moreOptionBtn);

		loadLabelRuleBtn = new JButton("Reload");
		loadLabelRuleBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loadLabelRuleBtn.setBounds(100, 218, 120, 30);
		panelOrder.add(loadLabelRuleBtn);

		labelname2 = new JLabel("Target:");
		labelname2.setBounds(10, 225, 45, 17);
		panelOrder.add(labelname2);
		labelname2.setFont(new Font("Tahoma", Font.PLAIN, 14));

		ruleLabel = new JLabel("");
		ruleLabel.setVerticalAlignment(SwingConstants.TOP);
		ruleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		ruleLabel.setBounds(1020, 332, 250, 300);
		ruleLabel.setBorder(border);

		scrPane = new JScrollPane(ruleLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrPane.setBounds(10, 259, 331, 337);
		panelOrder.add(scrPane);
		
		orderBG = new JLabel("");
		orderBG.setForeground(Color.BLACK);
		orderBG.setBounds(0, 0, 351, 607);
		panelOrder.add(orderBG);
		

		ruleLabel.setVisible(true);
		loadLabelRuleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ruleLabel.setText(stringToHTML(saveRule));
			}
		});

		panelWork = new JPanel();
		panelWork.setBounds(659, 33, 351, 607);
		getContentPane().add(panelWork);
		panelWork.setLayout(null);
		panelWork.setVisible(false);

		showKetQua = new JLabel("");
		showKetQua.setBounds(10, 72, 331, 450);
		panelWork.add(showKetQua);
		showKetQua.setVerticalAlignment(SwingConstants.TOP);
		showKetQua.setBorder(border);

		reloadButton = new JButton("Reload");
		reloadButton.setBounds(93, 38, 89, 23);
		panelWork.add(reloadButton);

		lblNewLabel_5 = new JLabel("Mission:");
		lblNewLabel_5.setBounds(10, 38, 73, 23);
		panelWork.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));

		showDoBan = new JLabel("Total Dust Value:");
		showDoBan.setBounds(20, 533, 208, 25);
		panelWork.add(showDoBan);

		labelCount = new JLabel("Current amount of trash:  -Organic: 0 -Inorganic: 0 -Recycle: 0");
		labelCount.setBounds(10, 576, 331, 20);
		panelWork.add(labelCount);

		luongPinBanDau = new JLabel("Power: 1000mah");
		luongPinBanDau.setBounds(222, 533, 119, 25);
		panelWork.add(luongPinBanDau);
		luongPinBanDau.setHorizontalAlignment(SwingConstants.RIGHT);

		soundBtn = new JButton("Sound");
		soundBtn.setBounds(252, 38, 89, 23);
		panelWork.add(soundBtn);
		soundBtn.setToolTipText("Click here to change sound mode");
		
		workBG = new JLabel("");
		workBG.setBounds(0, 0, 351, 607);
		panelWork.add(workBG);
		soundBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (soundMode == 0) {
					soundMode = 1;
					soundBtn.setText("Mute");
				} else if (soundMode == 1) {
					soundMode = 0;
					soundBtn.setText("Sound");
				}
			}
		});
		labelCount.setVisible(false);
		showDoBan.setVisible(false);
		reloadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showKetQua.setText("");
				ThemLichLamViec llv = new ThemLichLamViec();
				String link = llv.getLink();
				File fileX = new File(link);
				int xx = 0;
				try {
					Scanner sc = new Scanner(fileX);
					while (sc.hasNextLine()) {
						String data = sc.nextLine();
						System.out.println("nextline : " + data);
						String[] datas = data.split("\\:");
						String stringa = datas[0];
						String stringb = datas[1];
						String stringc = datas[2];
						String stringd = datas[3];
						String stringe = datas[4];
						String stringf = datas[5];
						listOfNum[xx] = Integer.parseInt(stringa);
						listOfMode[xx] = Integer.parseInt(stringb);
						listOfHour[xx] = Integer.parseInt(stringc);
						listOfMinute[xx] = Integer.parseInt(stringd);
						listOfPM_AM[xx] = Integer.parseInt(stringe);
						listOfInfo[xx] = stringf;
						xx++;
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String stringHTML = stringToHTML(listOfNum, listOfMode, listOfHour, listOfMinute, listOfPM_AM,
						listOfInfo, xx);
				showKetQua.setText(stringHTML);
				System.out.println("Number of Work: " + xx);
				sumOfWork = xx;
			}
		});
		getContentPane().add(menu);

		// thay đổi giữa chọn vật cản và chọn địa điểm thu vứt rác

		modeVatCanBtn = new JButton("Barrier");
		modeVatCanBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (modeVatCanInt == 1) {
					modeVatCanInt = 0;
					modeVatCanBtn.setText("Barrier");
				} else if (modeVatCanInt == 0) {
					modeVatCanInt = 1;
					modeVatCanBtn.setText("Set Order");
				}
			}
		});
		modeVatCanBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		modeVatCanBtn.setBounds(235, 116, 95, 25);
		menu.add(modeVatCanBtn);

		checkExcel = new JRadioButton("Save the results to Excel");
		checkExcel.setBackground(Color.WHITE);
		checkExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		checkExcel.setBounds(6, 524, 147, 23);
		menu.add(checkExcel);

		showLinkExcelTF = new JTextField();
		showLinkExcelTF.setText(linkFileExcel);
		showLinkExcelTF.setBounds(159, 525, 139, 20);
		menu.add(showLinkExcelTF);
		showLinkExcelTF.setColumns(10);

		// lấy link từ cửa sổ
		getLinkExcelBtn = new JButton("...");
		getLinkExcelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileDialog = new JFileChooser();
				FileNameExtensionFilter excel = new FileNameExtensionFilter("File Excel", "xlsx", "xls");
				fileDialog.setFileFilter(excel);
				fileDialog.setMultiSelectionEnabled(false);
				int returnVal = fileDialog.showOpenDialog(menu);
				java.io.File filex = fileDialog.getSelectedFile();
				linkFileExcel = filex.toString();
				System.out.println("\nLink:  " + linkFileExcel);
				File file = new File(linkFileExcel);
				int xx = 0;
				if (!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				showLinkExcelTF.setText(linkFileExcel);
			}
		});
		getLinkExcelBtn.setBounds(308, 524, 31, 23);
		menu.add(getLinkExcelBtn);
		
		menuBG = new JLabel("");
		menuBG.setBounds(0, 0, 351, 607);
		menu.add(menuBG);

		BtnPanel = new JPanel();
		BtnPanel.setBackground(Color.LIGHT_GRAY);
		BtnPanel.setBounds(659, 0, 351, 33);
		getContentPane().add(BtnPanel);
		BtnPanel.setLayout(null);

		btnNewButton_1 = new JButton("Menu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu.setVisible(true);
				panelOrder.setVisible(false);
				panelWork.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(0, 0, 90, 33);
		BtnPanel.add(btnNewButton_1);

		btnNewButton_2 = new JButton("Order");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu.setVisible(false);
				panelOrder.setVisible(true);
				panelWork.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(90, 0, 90, 33);
		BtnPanel.add(btnNewButton_2);

		btnNewButton_3 = new JButton("Work");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu.setVisible(false);
				panelOrder.setVisible(false);
				panelWork.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(180, 0, 90, 33);
		BtnPanel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("BG Mode");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(bgMode == 1) {
					//main background
					ImageIcon imgIcon2 = new ImageIcon("resources/bg1.png");
					Image imgIconTMP2 =  imgIcon2.getImage();
					Image newIconImgTmpImage2 = imgIconTMP2.getScaledInstance(bgMain.getWidth(), bgMain.getHeight(), Image.SCALE_SMOOTH);
					ImageIcon finalImgIcon2 = new ImageIcon(newIconImgTmpImage2);
					bgMain.setIcon(finalImgIcon2);
					//order panel background
					ImageIcon imgIcon1 = new ImageIcon("resources/bg.png");
					Image imgIconTMP1 =  imgIcon1.getImage();
					Image newIconImgTmpImage1 = imgIconTMP1.getScaledInstance(orderBG.getWidth(), orderBG.getHeight(), Image.SCALE_SMOOTH);
					ImageIcon finalImgIcon1 = new ImageIcon(newIconImgTmpImage1);
					orderBG.setIcon(finalImgIcon1);
					//menu background
					ImageIcon imgIcon3 = new ImageIcon("resources/bg.png");
					Image imgIconTMP3 =  imgIcon3.getImage();
					Image newIconImgTmpImage3 = imgIconTMP3.getScaledInstance(menuBG.getWidth(), menuBG.getHeight(), Image.SCALE_SMOOTH);
					ImageIcon finalImgIcon3 = new ImageIcon(newIconImgTmpImage3);
					menuBG.setIcon(finalImgIcon1);
					//work background
					ImageIcon imgIcon4 = new ImageIcon("resources/bg.png");
					Image imgIconTMP4 =  imgIcon4.getImage();
					Image newIconImgTmpImage4 = imgIconTMP4.getScaledInstance(workBG.getWidth(), workBG.getHeight(), Image.SCALE_SMOOTH);
					ImageIcon finalImgIcon4 = new ImageIcon(newIconImgTmpImage4);
					workBG.setIcon(finalImgIcon4);
					bgMode =2;
				}
				else if(bgMode == 2) {
					//main background
					String pathString = "resources/bgGray.png";
					ImageIcon imgIcon2 = new ImageIcon(pathString);
					Image imgIconTMP2 =  imgIcon2.getImage();
					Image newIconImgTmpImage2 = imgIconTMP2.getScaledInstance(bgMain.getWidth(), bgMain.getHeight(), Image.SCALE_SMOOTH);
					ImageIcon finalImgIcon2 = new ImageIcon(newIconImgTmpImage2);
					bgMain.setIcon(finalImgIcon2);
					//order panel background 
					ImageIcon imgIcon1 = new ImageIcon(pathString);
					Image imgIconTMP1 =  imgIcon1.getImage();
					Image newIconImgTmpImage1 = imgIconTMP1.getScaledInstance(orderBG.getWidth(), orderBG.getHeight(), Image.SCALE_SMOOTH);
					ImageIcon finalImgIcon1 = new ImageIcon(newIconImgTmpImage1);
					orderBG.setIcon(finalImgIcon1);
					//menu background
					ImageIcon imgIcon3 = new ImageIcon(pathString);
					Image imgIconTMP3 =  imgIcon3.getImage();
					Image newIconImgTmpImage3 = imgIconTMP3.getScaledInstance(menuBG.getWidth(), menuBG.getHeight(), Image.SCALE_SMOOTH);
					ImageIcon finalImgIcon3 = new ImageIcon(newIconImgTmpImage3);
					menuBG.setIcon(finalImgIcon1);
					//work background
					ImageIcon imgIcon4 = new ImageIcon(pathString);
					Image imgIconTMP4 =  imgIcon4.getImage();
					Image newIconImgTmpImage4 = imgIconTMP4.getScaledInstance(workBG.getWidth(), workBG.getHeight(), Image.SCALE_SMOOTH);
					ImageIcon finalImgIcon4 = new ImageIcon(newIconImgTmpImage4);
					workBG.setIcon(finalImgIcon4);
					bgMode = 1;
				}
			}
		});
		btnNewButton_4.setBounds(270, 0, 81, 33);
		BtnPanel.add(btnNewButton_4);
		
		bgMain = new JLabel("");
		bgMain.setBounds(0, 0, 1027, 651);
		
		getContentPane().add(bgMain);

		// hiển thị đồng hồ thời gian thực
		new Thread() {
			public void run() {
				while (true) {
					Calendar ca = new GregorianCalendar();
					hour = ca.get(Calendar.HOUR);
					minute = ca.get(Calendar.MINUTE);
					second = ca.get(Calendar.SECOND);
					PM_AM = ca.get(Calendar.AM_PM);
					String day_night;
					if (PM_AM == 1) {
						day_night = "PM";
					} else {
						day_night = "AM";
					}
					String time = hour + ":" + minute + ":" + second + " " + day_night;
					showTime.setText(time);

					for (int i = 0; i < getSumOfWork(); i++) {
						if (hour == listOfHour[i] && minute == listOfMinute[i] && PM_AM == listOfPM_AM[i]) {
							numDoTime = i + 1;
							doTime = 99;
//							modeHienTaiTuFile = listOfMode[i];
						}
					}

					if (doTime == 99 && count < numDoTime) {
//						pinLabel.setText("Thực Hiện Nhiệm Vụ " + numDoTime + ">>>");

						System.out.println("Doing Work No." + numDoTime + " >>>");
						count++;
						System.out.println("count: " + count);
						//
						if (isStart == 0) {
							initWorkArea.setEnabled(false);
							initDust.setEnabled(false);
							areaSizeInput.setEditable(false);

							dynamicBarrierPan.enableComponents(false);
							hedgeAndBarrierPan.enableComponents(false);

							if (robot.getWorkStatus() == 0) {
								String pauseText = "STOP";
								start.setText(pauseText);
								robot.start(currentMode);
								restart.setEnabled(true);
							} else {
								String continueText = "CONTINUE";
								start.setText(continueText);
								robot.stop();
							}
							isStart = 1;
						}

						else if (isStart == 1) {
							initWorkArea.setEnabled(false);
							initDust.setEnabled(false);
							areaSizeInput.setEditable(false);
							String pauseText = "STOP";
							start.setText(pauseText);
							start.setEnabled(true);
							robot.restart(currentMode);
							dynamicBarrierPan.enableComponents(false);
							hedgeAndBarrierPan.enableComponents(false);
						}
					}
					DecimalFormat df = new DecimalFormat("#.0");
					luongPinBanDau.setText("Power: " + df.format(getLuongPin()) + " mah.");
					if (pinMode == 0) {
						if (getLuongPin() / getLuongPinSetUp() < 0.25) {
							changeBackGround("verry_low.png");
						} else if (getLuongPin() / getLuongPinSetUp() >= 0.25
								&& getLuongPin() / getLuongPinSetUp() < 0.5) {
							changeBackGround("low.png");
						} else if (getLuongPin() / getLuongPinSetUp() >= 0.5
								&& getLuongPin() / getLuongPinSetUp() < 0.75) {
							changeBackGround("medium.png");

						} else {
							changeBackGround("full.png");

						}
					} else if (pinMode == 1) {
//						showPinImage = new JLabel();

						String stringX = (int) ((getLuongPin() / getLuongPinSetUp()) * 100) + "%";
						btnShowPinImage.setText(stringX);
//							System.out.println(df.format((int) (getLuongPin() / getLuongPinSetUp()) * 100) + "%");
					}

				}
			}
		}.start();

		//
		report = new ReportPanel(this);
		this.setVisible(true);
		repaint();
	}

	private void initMap() {// Khởi tạo map
		String inputValue = areaSizeInput.getText();
		sizeOfMap = Integer.parseInt(inputValue);
		String warningTitle = "Warning";
		if (inputValue == null || inputValue.equals("")) {
			String sizeText = "Enter working area size first.";
			JOptionPane.showMessageDialog(this, sizeText, warningTitle, JOptionPane.WARNING_MESSAGE);
			start.removeMouseListener(this);
			start.setEnabled(false);
			restart.removeMouseListener(this);
			restart.setEnabled(false);
		} else {
			areaSize = Integer.parseInt(inputValue); // kích thước bản đồ

			if (areaSize < 2) {
				String sizeText = "Working area size not less than 2";
				JOptionPane.showMessageDialog(this, sizeText, warningTitle, JOptionPane.WARNING_MESSAGE);
				start.removeMouseListener(this);
				start.setEnabled(false);
				restart.removeMouseListener(this);
				restart.setEnabled(false);
			} else {
				if (robot != null) {
					this.remove(robot);
					start.removeMouseListener(this);
					restart.removeMouseListener(this);
					this.repaint();
				}
				this.remove(pane);
				robot = new Robot(areaSize, this);
				robot.setBounds(2, 2, 640, 640);
				robot.init();
				getContentPane().add(robot);

				String startText = "START";
				start.setText(startText);
				restart.setEnabled(false);

				initDust.setEnabled(true);
				initDust.addMouseListener(this);

				revalidate();
				this.repaint();
			}
		}
	}

	private void init() {
		currentMode = ProcessUtil.getCurrentWorkingMode();
		showDustValueProp = ProcessUtil.getShowDustValue();
	}

	private JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();

		String fileMenuText = "File";
		fileMenu = new JMenu(fileMenuText);
		fileMenu.setMnemonic(KeyEvent.VK_F);

		menuBar.add(fileMenu);
		String newRobotText = "Refresh Working Area";
		newRobot = new JMenuItem(newRobotText);
		newRobot.setIcon(new ImageIcon("resources/reset.png"));
		newRobot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newRobot.addActionListener(this);
		fileMenu.add(newRobot);

		String showDustValueTxt = "Show Dust Value";
		showDustValue = new JCheckBoxMenuItem(showDustValueTxt);
		showDustValue.addActionListener(this);
		if (showDustValueProp == 0) {
			showDustValue.setSelected(false);
		} else {
			showDustValue.setSelected(true);
		}
		fileMenu.add(showDustValue);

		fileMenu.addSeparator();
		String exitText = "EXIT";
		exit = new JMenuItem(exitText);
		exit.setIcon(new ImageIcon("resources/close.png"));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		exit.addActionListener(this);
		fileMenu.add(exit);
		//
		String cstt = "Knowledge Base System";
		csttMenu = new JMenu(cstt);
		csttMenu.setMnemonic(KeyEvent.VK_E);
		menuBar.add(csttMenu);

		String titleCstt = "Knowledge Base System Management";
		qlCstt = new JMenuItem(titleCstt);
		qlCstt.addActionListener(this);
		csttMenu.add(qlCstt);

		// item cài thêm thời gian
		JMenu addTimeToDo = new JMenu("AddTime");
		menuBar.add(addTimeToDo);

		JMenuItem addTime1 = new JMenuItem("Create Work");
		addTime1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemLichLamViec lamviec = new ThemLichLamViec();
				lamviec.setVisible(true);
			}
		});
		addTime1.setHorizontalAlignment(SwingConstants.LEFT);
		addTimeToDo.add(addTime1);

		JMenuItem pinItem = new JMenuItem("Setup Battery");
		pinItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PinMenu pinmenu = new PinMenu();
				pinmenu.setVisible(true);
			}
		});
		addTimeToDo.add(pinItem);

		// tạo nơi hiển thị thời gian
		showTime = new JLabel("17:03:00 AM");
		menuBar.add(showTime);
		showTime.setHorizontalAlignment(SwingConstants.RIGHT);

		btnShowPinImage = new JButton();
		menuBar.add(btnShowPinImage);
		btnShowPinImage.setBackground(new Color(255, 255, 255));
		btnShowPinImage.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnShowPinImage.setOpaque(false);
		btnShowPinImage.setContentAreaFilled(false);
		btnShowPinImage.setBorderPainted(false);

		// Status Bar
		statusBar = new StatusBar();
		statusBar.setBackground(new Color(255, 255, 255));
		statusBar.setBorder(null);
		menuBar.add(statusBar);
		btnShowPinImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pinMode == 0) {
					pinMode = 1;
//				btnShowPinImage = new JButton("");
					changeBackGround("clear.png");
					btnShowPinImage.setIcon(null);
				} else if (pinMode == 1) {
					pinMode = 0;
					btnShowPinImage.setText("");
				}
				System.out.println("Pinmode: " + pinMode);
			}
		});
		return menuBar;
	}

	public int getAreaSize() {
		return areaSize;
	}

	public boolean addDynamicBarrier(int x, int y, int speed, int direction) {
		if (x == 0 && y == 0) {
			return false;
		}
		return !robot.isBarrier(x, y, speed, direction);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		clearModePanel();
		int index = modeList.getSelectedIndex();

		switch (index) {
		case STANDARD:
			currentMode = STANDARD;
			menu.add(standard);
			break;

		case STANDARD_MODE:
			currentMode = STANDARD_MODE;
			menu.add(standardModePan);
			break;

		case DYNAMIC_MODE:
			currentMode = DYNAMIC_MODE;
			menu.add(dynamicBarrierPan);
			break;
		case DSGT_MODE:
			currentMode = DSGT_MODE;
			menu.add(hedgePan);
			break;
		case DSGT_DINAYMIC_MODE:
			currentMode = DSGT_DINAYMIC_MODE;
			menu.add(hedgeAndBarrierPan);
			break;
		default:
			break;
		}
//		System.out.print(currentMode);
		ProcessUtil.setCurrentWorkingMode("Mode: " + currentMode + "-");
		revalidate();
		repaint();
	}

	private void clearModePanel() {
		if (dynamicBarrierPan != null) {
			menu.remove(dynamicBarrierPan);
		}
		if (standardModePan != null) {
			menu.remove(standardModePan);
		}
		if (standard != null) {
			menu.remove(standard);
		}
		if (hedgePan != null) {
			menu.remove(hedgePan);
		}
		if (hedgeAndBarrierPan != null) {
			menu.remove(hedgeAndBarrierPan);
		}
	}

	public int getCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(int currentMode) {
		this.currentMode = currentMode;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == initWorkArea) {
			initMap();
		}

		// Click Bắt đầu
		if (e.getSource() == start) {
			initWorkArea.removeMouseListener(this);
			initWorkArea.setEnabled(false);
			initDust.removeMouseListener(this);
			initDust.setEnabled(false);
			areaSizeInput.setEditable(false);

			dynamicBarrierPan.enableComponents(false);
			hedgeAndBarrierPan.enableComponents(false);

			if (robot.getWorkStatus() == 0) {
				String pauseText = "STOP";
				start.setText(pauseText);
				robot.start(currentMode);
				restart.setEnabled(true);
				restart.addMouseListener(this);
			} else {
				String continueText = "CONTINUE";
				start.setText(continueText);
				robot.stop();
			}
			showDoBan.setText("Total Dust Value: xxx" + getSumOfDust());
		}

		// Click restart
		if (e.getSource() == restart) {
			setLaiRac();
			initWorkArea.removeMouseListener(this);
			initWorkArea.setEnabled(false);
			initDust.removeMouseListener(this);
			initDust.setEnabled(false);
			areaSizeInput.setEditable(false);
			String pauseText = "STOP";
			start.setText(pauseText);
			start.setEnabled(true);
			robot.restart(currentMode);

			dynamicBarrierPan.enableComponents(false);
			hedgeAndBarrierPan.enableComponents(false);
			showDoBan.setText("Total Dust Value: " + getSumOfDust());
		}

		// Khởi tạo độ bẩn
		if (e.getSource() == initDust && initDust.isEnabled()) {
			System.out.println("Make Dust");
			setLaiRac();
			robot.initDust();
			start.setEnabled(true);
			start.addMouseListener(this);
			initWorkArea.removeMouseListener(this);
			initWorkArea.setEnabled(false);
			dynamicBarrierPan.enableComponents(true);
			showDoBan.setText("Total Dust Value: xxx" + getSumOfDust());
			double abcd = 0.8 * getLuongPin();
			System.out.println("Power Low is: " + abcd);
			if (getLuongPinTinhToan() > abcd) {
				YesNo yesNo = new YesNo();
				yesNo.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "The Current Energy Can Run.");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) { // Sau khi ấn chuột kiểm tra các
												// thay đổi
		if (e.getSource() == start && start.isEnabled()) {
			start.setCursor(new Cursor(Cursor.HAND_CURSOR));
			start.setOpaque(true);
			start.setBackground(new Color(228, 237, 244));
		}
		if (e.getSource() == restart && restart.isEnabled()) {
			restart.setCursor(new Cursor(Cursor.HAND_CURSOR));
			restart.setOpaque(true);
			restart.setBackground(new Color(228, 237, 244));
		}
		if (e.getSource() == initWorkArea && initWorkArea.isEnabled()) {
			initWorkArea.setCursor(new Cursor(Cursor.HAND_CURSOR));
			initWorkArea.setOpaque(true);
			initWorkArea.setBackground(new Color(228, 237, 244));
		}
		if (e.getSource() == initDust && initDust.isEnabled()) {
			initDust.setCursor(new Cursor(Cursor.HAND_CURSOR));
			initDust.setOpaque(true);
			initDust.setBackground(new Color(228, 237, 244));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {// Sau khi ấn chuột kiểm tra các thay
											// đổi
		if (e.getSource() == start && start.isEnabled()) {
			start.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			start.setOpaque(true);
			start.setBackground(Color.WHITE);
		}
		if (e.getSource() == restart && restart.isEnabled()) {
			restart.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			restart.setOpaque(true);
			restart.setBackground(Color.WHITE);
		}

		if (e.getSource() == initWorkArea && initWorkArea.isEnabled()) {
			initWorkArea.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			initWorkArea.setOpaque(true);
			initWorkArea.setBackground(Color.WHITE);
		}
		if (e.getSource() == initDust && initDust.isEnabled()) {
			initDust.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			initDust.setOpaque(true);
			initDust.setBackground(Color.WHITE);
		}
	}

	public int getStandardWorkingTime() {
		return standardModePan.getStandardWorkingTime();
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
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == exit) {
			System.exit(0);
		}

		if (e.getSource() == qlCstt) {
			SuKienForm form;
			try {
				form = new SuKienForm();
				form.setVisible(true);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		// Tạo mới bản đồ
		if (e.getSource() == newRobot) {
			setLaiRac();
			showDoBan.setText("Total Dust Value: 0");
			isStart = 0; // khi khởi tạo lại bản đồ thì reset lại giá trị isstart
			if (robot != null) {
				robot.stop();
				this.remove(robot);
				start.removeMouseListener(this);
				restart.removeMouseListener(this);
				this.repaint();
			}
			this.remove(pane);
			robot = new Robot(areaSize, this);
			robot.setBounds(1, 0, 549, 550);
			robot.init();
			getContentPane().add(robot);

			start.setEnabled(true);
			String startText = "Start";
			start.setText(startText);
			restart.setEnabled(false);
			start.addMouseListener(this);

			initWorkArea.addMouseListener(this);
			initWorkArea.setEnabled(true);
			areaSizeInput.setEditable(true);

			statusBar.resetMessage();

			revalidate();
			this.repaint();
		}

		if (e.getSource() == showDustValue) {
			if (showDustValue.isSelected()) {
				ProcessUtil.setSowDustVaue("" + 1);
				showDustValue.setSelected(true);
				robot.switchShowDustValueType(true);
			} else {
				ProcessUtil.setSowDustVaue("" + 0);
				showDustValue.setSelected(false);
				robot.switchShowDustValueType(false);
			}
		}

	}

	public void reset() {
		setLaiRac();
		areaSizeInput.setText("");
		areaSizeInput.setEditable(true);
		initWorkArea.addMouseListener(this);
		initWorkArea.setEnabled(true);
		start.setEnabled(false);

		int time = statusBar.getworkingTime();
		int numOfSteps = robot.getNumOfSteps();
		int duplicateSteps = robot.getDuplicateSteps();

		int areaWorked = numOfSteps - 1 - duplicateSteps;

		double coverage = areaWorked * 1.0 / (areaSize * areaSize - robot.getNumOfBarrier());

		DecimalFormat df = new DecimalFormat("##.##");
		String str = df.format(coverage);
		coverage = 100 * Double.parseDouble(str);

		try {
			report.showReport(time, numOfSteps, duplicateSteps, coverage, getSumOfWater(), getSumOfCoffe(),
					getSumOfSoda(), checkExcel.isSelected(), geteImpressive(), geteHappy(), geteNormal(), geteBad(),
					geteAngry(), getPoint());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		statusBar.resetMessage();

		repaint();
	}

	public void changeStatusBar(int x, int y, int time) {
		statusBar.setMessage(x, y, time);
	}

	public static void changeStatusBar(String x) {
		statusBar.setMessageOrder(x);
	}

	public static int[] getListOfNum() {
		return listOfNum;
	}

	public static void setListOfNum(int[] listOfNum) {
		MainGui.listOfNum = listOfNum;
	}

	public static int[] getListOfHour() {
		return listOfHour;
	}

	public static void setListOfHour(int[] listOfHour) {
		MainGui.listOfHour = listOfHour;
	}

	public static int[] getListOfMinute() {
		return listOfMinute;
	}

	public static void setListOfMinute(int[] listOfMinute) {
		MainGui.listOfMinute = listOfMinute;
	}

	public static int[] getListOfPM_AM() {
		return listOfPM_AM;
	}

	public static void setListOfPM_AM(int[] listOfPM_AM) {
		MainGui.listOfPM_AM = listOfPM_AM;
	}

	public static String[] getListOfInfo() {
		return listOfInfo;
	}

	public static void setListOfInfo(String[] listOfInfo) {
		MainGui.listOfInfo = listOfInfo;
	}

	public static int getSumOfWork() {
		return sumOfWork;
	}

	public static void setSumOfWork(int sumOfWork) {
		MainGui.sumOfWork = sumOfWork;
	}

	public static int[] getListOfMode() {
		return listOfMode;
	}

	public static void setListOfMode(int[] listOfMode) {
		MainGui.listOfMode = listOfMode;
	}

	public static void setSumOfDust(int sumOfDust) {
		MainGui.sumOfDust = sumOfDust;
	}

	public static int getSumOfDust() {
		return sumOfDust;
	}

	public static double getLuongPinTinhToan() {
		return luongPinTinhToan;
	}

	public static void setLuongPinTinhToan(double luongPinTinhToan) {
		MainGui.luongPinTinhToan = luongPinTinhToan;
	}

	public static double getLuongPin() {
		return luongPin;
	}

	public static void setLuongPin(double luongPin) {
		MainGui.luongPin = luongPin;
	}

	static String stringToHTML(int[] listOfNum, int[] mode, int[] listOfHour, int[] listOfMinute, int[] listOfPM_AM,
			String[] listOfInfo, int xx) {
		String stringhtml = "<html>";
		for (int i = 0; i < xx; i++) {
			if (listOfPM_AM[i] == 1) {
				stringhtml += "Work " + listOfNum[i] + ": mode " + mode[i] + " - " + listOfHour[i] + ":"
						+ listOfMinute[i] + " PM  => Work Info: " + listOfInfo[i] + "<br>";
			} else {
				stringhtml += "Work " + listOfNum[i] + ": mode " + mode[i] + " - " + listOfHour[i] + ":"
						+ listOfMinute[i] + " AM  => Work Info: " + listOfInfo[i] + "<br>";
			}
		}
		stringhtml += "</html>";
		return stringhtml;
	}

	public static String stringToHTML(ArrayList<String> strings) {
		String stringhtml = "<html>";
		for (int i = 0; i < strings.size(); i++) {
			stringhtml += "<br>" + strings.get(i);
		}
		stringhtml += "</html>";
		return stringhtml;
	}

	public static String saveRuleToString(ArrayList<String> strings) {
		String resultString = "";
		for (int i = 0; i < strings.size(); i++) {
			resultString += strings.get(i);
		}
		return resultString;
	}

	public double powerLow() {
		return 0.2 * getLuongPin();
	}

	public static boolean isPowerCanRun() {
		return isPowerCanRun;
	}

	public static void setIsPowerCanRun(boolean isPowerCanRun) {
		MainGui.isPowerCanRun = isPowerCanRun;
	}

	public static int getModeVatCanInt() {
		return modeVatCanInt;
	}

	public static void setModeVatCanInt(int modeVatCanInt) {
		MainGui.modeVatCanInt = modeVatCanInt;
	}

	public static int getNumOfHuuCo() {
		return numOfHuuCo;
	}

	public static void setNumOfHuuCo(int numOfHuuCo) {
		MainGui.numOfHuuCo = numOfHuuCo;
	}

	public static int getNumOfVoCo() {
		return numOfVoCo;
	}

	public static void setNumOfVoCo(int numOfVoCo) {
		MainGui.numOfVoCo = numOfVoCo;
	}

	public static int getNumOfTaiChe() {
		return numOfTaiChe;
	}

	public static void setNumOfTaiChe(int numOfTaiChe) {
		MainGui.numOfTaiChe = numOfTaiChe;
	}

	public static void settextlabelcount() {

		labelCount.setText("Current amount of trash:  -Organic: " + getLoaiHuuCo() + " -Inorganic: " + getLoaiVoCo()
				+ " -Recycle: " + getLoaiTaiche());
	}

	public static int getSizeOfMap() {
		return sizeOfMap;
	}

	public static void setSizeOfMap(int sizeOfMap) {
		MainGui.sizeOfMap = sizeOfMap;
	}

	public void setLaiRac() {
//		if(getLuongPinSetUp() == 0) {
//		setLuongPin(getLuongPin());
//		}
//		else {
//			setLuongPin(getLuongPinSetUp());
//		}
//		setLuongPin(getLuongPin());
//		setLuongPinSetUp(getLuongPin());
		setNumOfHuuCo(0);
		setNumOfTaiChe(0);
		setNumOfVoCo(0);
		setLoaiHuuCo(0);
		setLoaiTaiche(0);
		setLoaiVoCo(0);
	}

	public static void resetOrder() {
		MainGui.setSumOfCoffe(0);
		MainGui.setSumOfSoda(0);
		MainGui.setSumOfWater(0);
//		saveRule = new ArrayList<>();
		eAngry = 0;
		eBad = 0;
		eNormal = 0;
		eHappy = 0;
		eImpressive = 0;
		MainGui.setPoint(0);
		orderString = "Chưa order gì cả :<";
	}

	public static double getLuongPinSetUp() {
		return luongPinSetUp;
	}

	// set lượng pin do người dùng đăt
	public static void setLuongPinSetUp(double luongPinSetUp) {
		MainGui.luongPinSetUp = luongPinSetUp;
	}

	private static void changeBackGround(String backName) {
		ImageIcon icon = new ImageIcon("resources/" + backName);
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(30, 15, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		btnShowPinImage.setIcon(newIcon);
	}

	public static int getSoundMode() {
		return soundMode;
	}

	public static void setSoundMode(int soundMode) {
		MainGui.soundMode = soundMode;
	}

	public static int getNumOfSoda() {
		return numOfSoda;
	}

	public static void setNumOfSoda(int numOfSoda) {
		MainGui.numOfSoda = numOfSoda;
	}

	public static int getNumOfCoffe() {
		return numOfCoffe;
	}

	public static void setNumOfCoffe(int numOfCoffe) {
		MainGui.numOfCoffe = numOfCoffe;
	}

	public static int getNumOfWater() {
		return numOfWater;
	}

	public static void setNumOfWater(int numOfWater) {
		MainGui.numOfWater = numOfWater;
	}

	public static int getSumOfSoda() {
		return sumOfSoda;
	}

	public static void setSumOfSoda(int sumOfSoda) {
		MainGui.sumOfSoda = sumOfSoda;
	}

	public static int getSumOfCoffe() {
		return sumOfCoffe;
	}

	public static void setSumOfCoffe(int sumOfCoffe) {
		MainGui.sumOfCoffe = sumOfCoffe;
	}

	public static int getSumOfWater() {
		return sumOfWater;
	}

	public static void setSumOfWater(int sumOfWater) {
		MainGui.sumOfWater = sumOfWater;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	// phần HCSTT mới

	public static String[] getOrderArray() {
		return orderArray;
	}

	public static void setOrderArray(String[] orderPStrings) {
		System.out.println("array" + Arrays.toString(orderPStrings));
		MainGui.orderArray = orderPStrings;
	}

	public static String getOrderString() {
		return orderString;
	}

	public static void setOrderString(String oderString) {
		MainGui.orderString = oderString;
	}

	public static ArrayList<String> getSaveRule() {
		return saveRule;
	}

	public static void setSaveRule(ArrayList<String> saveRule) {
		MainGui.saveRule = saveRule;
	}

	public static void addRule(String ruleX) {
		MainGui.getSaveRule().add(ruleX);
	}

	public static int geteAngry() {
		return eAngry;
	}

	public static void seteAngry(int eAngry) {
		MainGui.eAngry = eAngry;
	}

	public static int geteBad() {
		return eBad;
	}

	public static void seteBad(int eBad) {
		MainGui.eBad = eBad;
	}

	public static int geteNormal() {
		return eNormal;
	}

	public static void seteNormal(int eNormal) {
		MainGui.eNormal = eNormal;
	}

	public static int geteHappy() {
		return eHappy;
	}

	public static void seteHappy(int eHappy) {
		MainGui.eHappy = eHappy;
	}

	public static int geteImpressive() {
		return eImpressive;
	}

	public static void seteImpressive(int eImpressive) {
		MainGui.eImpressive = eImpressive;
	}

	public static int getPoint() {
		return point;
	}

	public static void setPoint(int point) {
		MainGui.point = point;
	}
}
