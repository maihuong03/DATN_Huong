package newone;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.ProcessUtil;
import gui.MainGui;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.ItemSelectable;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class ThemLichLamViec extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel statusLabel;

	private int hour, minute, second, PM_AM;
	private static String link = "text/txt.txt"; // đường dẫn mặc
																									// định

	private JTextField txtHour;
	private JTextField txtMinute;
	private ButtonGroup bg;
	private JRadioButton PMRadio, AMRadio;
	private JLabel notiSuccess;
	private JLabel showLink;
	private JButton deleteButton;
	private JLabel notiInput;
	private JComboBox modeCombo;

	private int[] listOfNum = new int[20];
	private int[] listOfHour = new int[20];
	private int[] listOfMinute = new int[20];
	private int[] listOfPM_AM = new int[20];
	private String[] listOfInFo = new String[20];
	private int[] listOfMode = new int[20];

	private static int sumOfWork = 0;
	private JTextField txtInfo;
	private static int doTime, numDoTime;
	private static int count = 0;

	public static final int STANDARD = 0; // STC chuẩn
	public static final int STANDARD_MODE = 1; // STC full
	public static final int DYNAMIC_MODE = 2; // STC full + dynamic
	public static final int DSGT_MODE = 3; // STC full + DSGT
	public static final int DSGT_DINAYMIC_MODE = 4; // STC full + DSGT + dynamic
	private int currentMode;
	private ItemListener itemListener;
	// chọn mode làm việc
	private String standard = "1.Knowledge Base + STC-Online Standard";
	private String standardModeName = "2.Knowledge Base + STC-full Online ";
	private String dynamicBarierModeName = "3.Knowledge Base + STC-full & Dynamic Barries";
	private String hegemodeName = "4.Knowledge Base + STC-full + VAST ";
	private String hegeDynamicName = "5.Knowledge Base + STC-full + VAST & Knowledge Base";
	private String[] modes = { standard, standardModeName, dynamicBarierModeName, hegemodeName, hegeDynamicName };


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThemLichLamViec frame = new ThemLichLamViec();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws FileNotFoundException
	 */

	public ThemLichLamViec() {

//		sumOfWork = getSumWork(link);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 553, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//		this.setResizable(false);

		JLabel timeLabel = new JLabel("17:03:00 PM");
		timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		timeLabel.setBounds(455, 485, 79, 14);
		contentPane.add(timeLabel);

		JButton getLlink = new JButton("...");
		getLlink.setVerticalAlignment(SwingConstants.BOTTOM);
//		java.io.File filex;
		getLlink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileDialog = new JFileChooser();
				int returnVal = fileDialog.showOpenDialog(contentPane);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					java.io.File filex = fileDialog.getSelectedFile();
					link = filex.toString();
					System.out.println("\nLink:  " + link);
					File file = new File(link);
					int xx = 0;
					if (!file.exists()) {
						try {
							file.createNewFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					statusLabel.setText("Open command cancelled by user.");
				}
				showLink.setText("Link: " + link);
			}
		});

		getLlink.setToolTipText("Click to choose the direct path");
		getLlink.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getLlink.setBounds(440, 9, 70, 23);
		contentPane.add(getLlink);

		JLabel lblNewLabel_1 = new JLabel("Select Path:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(286, 11, 136, 22);
		contentPane.add(lblNewLabel_1);

		JLabel lblThngTinCng = new JLabel("Work Information:");
		lblThngTinCng.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblThngTinCng.setBounds(3, 200, 122, 21);
		contentPane.add(lblThngTinCng);

		JLabel showKetQua = new JLabel("");
		showKetQua.setVerticalAlignment(SwingConstants.TOP);
		showKetQua.setBackground(Color.LIGHT_GRAY);
		showKetQua.setForeground(Color.DARK_GRAY);
		showKetQua.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		showKetQua.setBounds(0, 232, 539, 208);
		contentPane.add(showKetQua);

		JButton reloadButton = new JButton("Reload");
		reloadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showKetQua.setText("");
				File fileX = new File(link);
				int ii = 0;
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
						listOfNum[ii] = Integer.parseInt(stringa);
						listOfMode[ii] = Integer.parseInt(stringb);
						listOfHour[ii] = Integer.parseInt(stringc);
						listOfMinute[ii] = Integer.parseInt(stringd);
						listOfPM_AM[ii] = Integer.parseInt(stringe);
						listOfInFo[ii] = stringf;
						ii++;
					}
					sumOfWork = ii;

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String stringHTML = stringToHTML(listOfNum, listOfMode, listOfHour, listOfMinute, listOfPM_AM, listOfInFo, ii);
				showKetQua.setText(stringHTML);
				System.out.println("số lượng công việc: " + sumOfWork);
				//
				MainGui.setListOfNum(listOfNum);
				MainGui.setListOfMode(listOfMode);
				MainGui.setListOfHour(listOfHour);
				MainGui.setListOfMinute(listOfMinute);
				MainGui.setListOfPM_AM(listOfPM_AM);
				MainGui.setListOfInfo(listOfInFo);
				MainGui.setSumOfWork(sumOfWork);
			}
		});
		reloadButton.setBounds(135, 200, 67, 23);
		contentPane.add(reloadButton);

		txtHour = new JTextField();
		txtHour.setHorizontalAlignment(SwingConstants.CENTER);
		txtHour.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtHour.setBounds(5, 36, 49, 20);
		contentPane.add(txtHour);
		txtHour.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Hour");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(5, 11, 49, 14);
		contentPane.add(lblNewLabel);

		txtMinute = new JTextField();
		txtMinute.setHorizontalAlignment(SwingConstants.CENTER);
		txtMinute.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMinute.setBounds(76, 36, 49, 20);
		contentPane.add(txtMinute);
		txtMinute.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Minute");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(76, 11, 49, 14);
		contentPane.add(lblNewLabel_2);

		JButton getTimeButton = new JButton("Get Time");
		getTimeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showKetQua.setText("");
				
				notiInput.setText("...");
				TimeOb timeob = new TimeOb();
				timeob.setHour(Integer.parseInt(txtHour.getText()));
				timeob.setMinute(Integer.parseInt(txtMinute.getText()));
				timeob.setInfo(txtInfo.getText());
				if (PMRadio.isSelected()) {
					timeob.setPM_AM(1);
				} else {
					timeob.setPM_AM(0);
				}
				
				timeob.setMode(currentMode);

				//setmode
//				ItemListener itemListener = new ItemListener() {
//					public void itemStateChanged(ItemEvent itemEvent) {
//						int index = modeCombo.getSelectedIndex();
//
//						switch (index) {
//						case STANDARD:
//							timeob.setMode(0);
//							break;
//
//						case STANDARD_MODE:
//							timeob.setMode(1);
//							break;
//						case DYNAMIC_MODE:
//							timeob.setMode(2);
//							break;
//						case DSGT_MODE:
//							timeob.setMode(3);
//							break;
//						case DSGT_DINAYMIC_MODE:
//							timeob.setMode(4);
//							break;
//						default:
//							break;
//						}
//						System.out.print(timeob.getMode());
//						revalidate();
//						repaint();
//					}
//				};
			
				System.out.println("Mode: " + timeob.getMode());
				// xét điều kiện
				File fileX = new File(link);
				int xx = 0;
				try {
					Scanner sc = new Scanner(fileX);
					while (sc.hasNextLine()) {
						String data = sc.nextLine();
						xx++;
					}
					sumOfWork = xx;
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				System.out.println("số lượng công việc: " + xx);

				// check xem có nhiệm vụ mới có trùng thời gian so với lịch đã có sẵn trong file
				// hay k
				int numrepeat = 0;
				for (int i = 0; i < sumOfWork; i++) {
					if (timeob.getHour() == listOfHour[i] && timeob.getMinute() == listOfMinute[i]
							&& timeob.getPM_AM() == listOfPM_AM[i]) {
						numrepeat++;
					}
				}

				System.out.println("Check repeat: " + numrepeat);
				if (timeob.getHour() >= 0 && timeob.getHour() < 12 && timeob.getMinute() >= 0
						&& timeob.getMinute() < 60) {

					// ghi vào file
					if (numrepeat == 0) {
						ThaoTacFile x = new ThaoTacFile();
						sumOfWork++;
						int kk = sumOfWork;
						System.out.println("Sum = " + sumOfWork);
						x.writeTimeToFile(sumOfWork, timeob.getMode(), timeob.getHour(), timeob.getMinute(),
								timeob.getPM_AM(), timeob.getInfo(), link);
						MainGui.setListOfNum(listOfNum);
						MainGui.setListOfMode(listOfMode);
						MainGui.setListOfHour(listOfHour);
						MainGui.setListOfMinute(listOfMinute);
						MainGui.setListOfPM_AM(listOfPM_AM);
						MainGui.setListOfInfo(listOfInFo);
						MainGui.setSumOfWork(sumOfWork);
						if (timeob.getPM_AM() == 1) {
							notiSuccess.setText("Add Work no." + kk + ":  mode " + timeob.getMode() + " - "
									+ timeob.getHour() + ":" + timeob.getMinute() + " PM");
						} else {
							notiSuccess.setText("Add Work no." + kk + ":  mode " + timeob.getMode() + " - "
									+ timeob.getHour() + ":" + timeob.getMinute() + " AM");
						}
						notiInput.setText("Success");
						notiInput.setForeground(Color.BLUE);
						String stringHTML = stringToHTML(listOfNum, listOfMode, listOfHour, listOfMinute, listOfPM_AM, listOfInFo,
								xx);
						showKetQua.setText(stringHTML);
					} else {
						notiInput.setText("Repeated");
						notiInput.setForeground(Color.YELLOW);
					}
				} else {
					notiInput.setText("Fail");
					notiInput.setForeground(Color.RED);

					
				}

//				MainGui	newmain = new MainGui();

			}
		});

		getTimeButton.setToolTipText("Click To Get Time");
		getTimeButton.setBounds(192, 10, 80, 40);
		contentPane.add(getTimeButton);

		AMRadio = new JRadioButton("AM");
		AMRadio.setBounds(134, 11, 55, 23);
		contentPane.add(AMRadio);

		PMRadio = new JRadioButton("PM");
		PMRadio.setBounds(134, 36, 55, 23);
		contentPane.add(PMRadio);
		bg = new ButtonGroup();
		bg.add(AMRadio);
		bg.add(PMRadio);

		notiSuccess = new JLabel("");
		notiSuccess.setBounds(298, 93, 212, 24);
		contentPane.add(notiSuccess);

		showLink = new JLabel("Default path: " + link);
		showLink.setBounds(3, 482, 437, 22);
		contentPane.add(showLink);

		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sumOfWork = 0;
				ThaoTacFile thaotac = new ThaoTacFile();
				thaotac.clearTimeFromFile(link);
				showKetQua.setText("");
				notiInput.setText("...");
			}
		});
		deleteButton.setBounds(440, 44, 70, 23);
		contentPane.add(deleteButton);

		JLabel lblNewLabel_3 = new JLabel("Delete the content in the file");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(286, 41, 161, 22);
		contentPane.add(lblNewLabel_3);

		txtInfo = new JTextField();
		txtInfo.setToolTipText("Enter work information");
		txtInfo.setBounds(54, 67, 218, 20);
		contentPane.add(txtInfo);
		txtInfo.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Info:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setToolTipText("Enter work information");
		lblNewLabel_4.setBounds(5, 67, 49, 18);
		contentPane.add(lblNewLabel_4);

		notiInput = new JLabel("...");
		notiInput.setHorizontalAlignment(SwingConstants.CENTER);
		notiInput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		notiInput.setBounds(212, 203, 57, 14);
		contentPane.add(notiInput);

		JLabel timeToWork = new JLabel("");
		timeToWork.setForeground(Color.GREEN);
		timeToWork.setFont(new Font("Tahoma", Font.PLAIN, 30));
		timeToWork.setBounds(3, 439, 519, 35);
		contentPane.add(timeToWork);

		// chọn mode làm việc
		String standard = "1.Knowledge Base + STC-Online Standard";
		String standardModeName = "2.Knowledge Base + STC-full Online ";
		String dynamicBarierModeName = "3.Knowledge Base + STC-full & Dynamic Barries";
		String hegemodeName = "4.Knowledge Base + STC-full + VAST ";
		String hegeDynamicName = "5.Knowledge Base + STC-full + VAST & Knowledge Base";
		String[] modes = { standard, standardModeName, dynamicBarierModeName, hegemodeName, hegeDynamicName };

		modeCombo = new JComboBox(modes);
		modeCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentMode = modeCombo.getSelectedIndex();
			}
		});
		modeCombo.setSelectedIndex(4);
		modeCombo.setBounds(44, 100, 228, 22);
		contentPane.add(modeCombo);

		JLabel lblNewLabel_5 = new JLabel("MODE:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(4, 103, 49, 14);
		contentPane.add(lblNewLabel_5);
		modeCombo.addItemListener(itemListener);


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
					timeLabel.setText(time);

					File fileX = new File(link);
					int xx = 0;
					try {
						Scanner sc = new Scanner(fileX);
						while (sc.hasNextLine()) {
							String data = sc.nextLine();
							xx++;
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					sumOfWork = xx;
					for (int i = 0; i < sumOfWork; i++) {
						if (hour == listOfHour[i] && minute == listOfMinute[i] && PM_AM == listOfPM_AM[i]) {
							numDoTime = i + 1;
							doTime = 99;
						}
					}

					if (doTime == 99 && count < numDoTime) {
						timeToWork.setText("Complete Work " + numDoTime + ">>>");
						System.out.println("Complete Work " + numDoTime + ">>>");
						count++;
						System.out.println("count: " + count);
					}
				}
			}
		}.start();
	}

	static String stringToHTML(int[] listOfNum, int[] listOfMode, int[] listOfHour, int[] listOfMinute, int[] listOfPM_AM,
			String[] listOfInfo, int xx) {
		String stringhtml = "<html>";
		for (int i = 0; i < xx; i++) {
			if (listOfPM_AM[i] == 1) {
				stringhtml += "Work " + listOfNum[i] + ": mode " + listOfMode[i] + " - " + listOfHour[i] + ":" + listOfMinute[i]
						+ " PM  => Work information: " + listOfInfo[i] + "<br>";
			} else {
				stringhtml += "Nhiệm vụ " + listOfNum[i] + ": mode " + listOfMode[i] + " - " + listOfHour[i] + ":" + listOfMinute[i]
						+ " AM  => Work information: " + listOfInfo[i] + "<br>";
			}
		}
		stringhtml += "</html>";
		return stringhtml;
	}

	public int[] getListOfHour() {
		return listOfHour;
	}

	public int[] getListOfMinute() {
		return listOfMinute;
	}

	public int[] getListOfPM_AM() {
		return listOfPM_AM;
	}

	public int[] getListOfNum() {
		return listOfNum;
	}

	public String[] getListOfInFo() {
		return listOfInFo;
	}

	public String getLink() {
		return link;
	}

}
