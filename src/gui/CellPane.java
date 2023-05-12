package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.beans.beancontext.BeanContextServiceAvailableEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;

import com.microsoft.schemas.office.office.ForcedashAttribute;

import common.DustColor;
import common.HedgeAlgebra;
import common.ProcessUtil;
import cstt.suyDien2;
import hcstt.Robot;
import gui.StatusBar;

public class CellPane extends JPanel {
	private static final long serialVersionUID = 1L;
	private int status;
	private Image img;
	private int size;
	private Color free = new Color(255, 255, 255); // ô clean
	private JLabel lbl;
	private Color unCleaned = new Color(211, 211, 211);

	private final int ROBOT_UP = 1, ROBOT_DOWN = 2, ROBOT_LEFT = 3, ROBOT_RIGHT = 4;
	private int repeatCount = 0;
	private String vectorName = "";
	private List<JLabel> vectorImages;
	private String dustValueText;
	private JLabel dustLabel;
	private int dustValue;
	private boolean showDustValue;
	private int timeProcess;
	private int dustInt;
	private int phanLoaiRac;
	private int xCell, yCell;
	private Robot robot;
	private int statusCheck; // status kiểm tra xem có phải là người yêu cầu không
	private JPanel pane = new JPanel();

	private int oldStatus = 0; // status == - 1 là vật cản cố định,
	private int doBan = 0;
	private boolean isClear = false;
	private double pin = 0;
	private int numOfHuuCo, numOfVoco, numOfTaiChe;
	// private int numOfWater = 0, numOfCoffe = 0, numOfSoda = 0;
	private JLabel labelText;
	private String stringShow;
	private String soundName = "resources/sound.wav";
	private AudioInputStream audioInputStream;
	private Clip clip;
	private static int cellCoffe = 0, cellWater = 0, cellSoda = 0; // số lựong nước của cell
	private String linkAvtString = "";
	private String linkEmotion = "";
	private Boolean isSetEmotion = false;
	private int point = 0;
	private int isServed = 0; // 0 chua. 1 roi
	private Timer timer;

	public CellPane() {
	}

	public CellPane(int status) {
		this.status = status;

		if (status == 0) {
			setBackground(unCleaned);
		}
	}

	// thiết lập hiển thị ô khi di chuyển
	public void changeToRobot(int vector) { // Đổi cell thành robot
		if (lbl != null) {
			this.remove(lbl);
			repaint();
		}
		this.status = 1;
		lbl = new JLabel();
		lbl.setBounds(1, 1, size - 2, size - 2);
		switch (vector) {
			case ROBOT_UP:
				changeBackGround("robotX.png"); // up
				vectorName = "D_U";
				break;
			case ROBOT_DOWN:
				changeBackGround("robotX.png");// down
				vectorName = "U_D";
				break;

			case ROBOT_LEFT:
				changeBackGround("robotX.png");// left
				vectorName = "R_L";
				break;

			case ROBOT_RIGHT:
				changeBackGround("robotX.png");// right
				vectorName = "L_R";
				break;

			default:
				break;
		}
		this.add(lbl);
		repaint();
	}

	public CellPane(int status, int size, Robot robot) {
		this.setLayout(null);
		this.size = size;
		this.setBounds(0, 0, size, size);
		this.setLayout(null);
		this.status = status;
		this.robot = robot;
		vectorImages = new ArrayList<>();

		if (status == 0) {
			setBackground(unCleaned);
		} else {
			changeToBarrier();
		}
		// -Xms512M
		// MainGui.setTestcellpane(MainGui.getTestcellpane()+1);
		// System.out.println("testcellpane-107: " + MainGui.getTestcellpane());
		// this.setLayout(null);
	}

	public void changeStatus(int nextVector) {
		this.status++;
		if (!vectorName.equals("")) {
			vectorName += "_";
		}
		switch (nextVector) {
			case ROBOT_UP:
				vectorName += "D_U";
				break;
			case ROBOT_DOWN:
				vectorName += "U_D";
				break;

			case ROBOT_LEFT:
				vectorName += "R_L";
				break;

			case ROBOT_RIGHT:
				vectorName += "L_R";
				break;

			default:
				break;
		}
		resetBackGround(status);
		JLabel lbl = new JLabel();
		lbl.setBounds(1, 1, size - 2, size - 2);
		ImageIcon icon = new ImageIcon("resources/" + vectorName + "_" + repeatCount + ".png");
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		lbl.setIcon(newIcon);
		vectorImages.add(lbl);

		robot.increaseDuplicateSteps(repeatCount);

		vectorName = "";
		repeatCount += 1;
		this.add(lbl);
		repaint();
	}

	public int getTimeProcess() {
		int result = timeProcess;
		timeProcess = 0;
		return result;
	}

	public void changeToNormal() {
		this.status = 0;
		resetBackGround(status);
		repeatCount = 0;
		timeProcess = dustValue;
		if (dustLabel != null) {
			add(dustLabel);
		}

		for (JLabel lbl : vectorImages) {
			this.remove(lbl);
		}
		repaint();
	}

	public void changeToBarrier() { // Thay đổi thành vật cản
		this.oldStatus = status;
		this.status = -1;
		setStatusCheck(0);
		lbl = new JLabel();
		lbl.setBounds(1, 1, size - 2, size - 2);
		changeBackGround("back_01.png");// Thay ô hiển thị = vật cản
		cellCoffe = 0;
		cellWater = 0;
		cellSoda = 0;
		labelText = new JLabel();
		labelText.setHorizontalAlignment(SwingConstants.CENTER);
		labelText.setForeground(Color.BLACK);
		labelText.setBounds(1, 1, size - 2, size - 2);
		lbl.add(labelText);
		this.add(lbl);

		if (dustLabel != null) {
			remove(dustLabel);
		}

		repaint();
	}

	public void changeToOrder() {
		System.out.println("thay doi thanh order");
		suyDien2 suyDien = new suyDien2();
		linkAvtString = includeLinkImage();
		suyDien.startX();
		this.oldStatus = status;
		this.status = -1;
		setStatusCheck(1);
		lbl = new JLabel();
		lbl.setBounds(1, 1, size - 2, size - 2);
		changeBackGround(linkAvtString);// Thay ô hiển thị = vật cản
		cellWater = MainGui.getNumOfWater();
		cellCoffe = MainGui.getNumOfCoffe();
		cellSoda = MainGui.getNumOfSoda();
		if (MainGui.getSizeOfMap() <= 10) {
			if (cellWater > 0 && cellCoffe > 0 && cellSoda == 0) {
				labelText = new JLabel("<html><br><p style=\"text-align:center;\">Coffee: " + cellCoffe + "<br>Water: "
						+ cellWater + "</html>");
			} else if (cellWater > 0 && cellCoffe == 0 && cellSoda > 0) {
				labelText = new JLabel("<html><br><p style=\"text-align:center;\">Water: " + cellWater + "<br>Soda: "
						+ cellSoda + "</html>");
			} else if (cellWater == 0 && cellCoffe > 0 && cellSoda > 0) {
				labelText = new JLabel("<html><br><p style=\"text-align:center;\">Coffee: " + cellCoffe + "<br>Soda: "
						+ cellSoda + "</html>");
			} else if (cellCoffe > 0 && cellWater == 0 && cellSoda == 0) {
				labelText = new JLabel("<html><br><p style=\"text-align:center;\">Coffee: " + cellCoffe + "</html>");
			} else if (cellCoffe == 0 && cellWater > 0 && cellSoda == 0) {
				labelText = new JLabel("<html><br><p style=\"text-align:center;\">Water: " + cellWater + "</html>");
			} else if (cellCoffe == 0 && cellWater == 0 && cellSoda > 0) {
				labelText = new JLabel("<html><br><p style=\"text-align:center;\">Soda: " + cellSoda + "</html>");
			} else if (cellCoffe == 0 && cellWater == 0 && cellSoda == 0) {
				labelText = new JLabel("");
			} else if (cellCoffe > 0 && cellWater > 0 && cellSoda > 0) {
				labelText = new JLabel("<html><br><p style=\"text-align:center;\">Coffee: " + cellCoffe + "<br>Water: "
						+ cellWater + "<br>Soda: " + cellSoda + "</html>");
			}
		} else {
			labelText = new JLabel("");
		}
		labelText.setHorizontalAlignment(SwingConstants.CENTER);
		labelText.setForeground(Color.BLACK);
		labelText.setBounds(1, 1, size - 2, size - 2);
		lbl.add(labelText);
		this.add(lbl);

		if (dustLabel != null) {
			remove(dustLabel);
		}
		// sử dụng tập luật vào đây
		// changeStatusBar(5, 4, 400);
		repaint();
	}

	////////////////////

	//

	public void orderDone() {
		setIsServed(1);
		setCellCoffe(0);
		setCellWater(0);
		setCellSoda(0);
		// kiem tra xem da set emotion chua va set diem
		if (isSetEmotion == false) {
			lbl.setVisible(false);
			lbl = new JLabel();
			lbl.setBounds(1, 1, size - 2, size - 2);
			labelText = new JLabel();
			labelText.setHorizontalAlignment(SwingConstants.CENTER);
			labelText.setForeground(Color.BLACK);
			labelText.setBounds(1, 1, size - 2, size - 2);
			changeBackGround(linkAvtString);
			linkEmotion = includeLinkEmotion();
			point = setPoint(linkEmotion);
			if (point == 0) {
				MainGui.seteAngry(MainGui.geteAngry() + 1);
			} else if (point == 3) {
				MainGui.seteBad(MainGui.geteBad() + 1);
			} else if (point == 5) {
				MainGui.seteNormal(MainGui.geteNormal() + 1);
			} else if (point == 7) {
				MainGui.seteHappy(MainGui.geteHappy() + 1);
			} else if (point == 10) {
				MainGui.seteImpressive(MainGui.geteImpressive() + 1);
			}
			MainGui.setPoint(MainGui.getPoint() + point);
			ImageIcon icon = new ImageIcon("resources/" + linkEmotion);
			Image imageX = icon.getImage();
			int sizeIcon = (int) (size * 0.3);
			Image newimg = imageX.getScaledInstance(sizeIcon, sizeIcon, java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newimg);
			labelText.setBounds(1, 1, sizeIcon, sizeIcon);
			labelText.setIcon(newIcon);
			lbl.add(labelText);
			isSetEmotion = true;
			this.add(lbl);

		}
		System.out.println("Point: " + point);

		repaint();
		// Toolkit.getDefaultToolkit().beep();
		if (MainGui.getSoundMode() == 0) {

			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				// Thread.sleep(500);
				// clip.close();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	///////////////////////////////////

	public void changeToDynamicBarrier() {
		this.oldStatus = status;
		this.status = -1;

		lbl = new JLabel();
		lbl.setBounds(1, 1, size - 2, size - 2);
		changeBackGround("dynamic_barrier.png");
		this.add(lbl);

		if (dustLabel != null) {
			remove(dustLabel);
		}
		repaint();
	}

	public void changeToOldStatus() { // Thay đổi thành trạng thái cũ
		this.status = oldStatus;
		if (lbl != null) {
			remove(lbl);
		}

		if (status == 0) {
			add(dustLabel);
		}

		revalidate();
		repaint();
	}

	public void changeBackGround(String backName) {
		ImageIcon icon = new ImageIcon("resources/" + backName);
		img = icon.getImage();
		Image newimg = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		lbl.setIcon(newIcon);
		// lbl.getGraphics().drawString("Hello", 1, 1);
	}

	public void changeBackgroundAndHold(String backName) {
		ImageIcon icon = new ImageIcon("resources/" + backName);
		img = icon.getImage();
		Image newimg = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		lbl.setIcon(newIcon);
		timer.setDelay(2000);
	}

	public void addTextToImage() {

	}

	public void resetBackGround(int status) { // Khởi tạo lại background
		if (lbl != null) {
			this.remove(lbl);
			repaint();
		}

		if (status == 0) {
			setBackground(unCleaned);
		} else {
			setBackground(free);
			if (dustLabel != null) {
				remove(dustLabel);
			}
		}

		repaint();
	}

	// set độ bẩn
	public void setDustValueText(String dustValueText, String phanLoaiRac) {
		// btn Hiển thị độ bẩn

		this.dustValueText = dustValueText;
		int showDustValueProp = ProcessUtil.getShowDustValue();
		if (showDustValueProp == 0) {
			showDustValue = false;
		} else {
			showDustValue = true;
		}
		HedgeAlgebra hedge = new HedgeAlgebra();
		dustValue = hedge.getLanguageValue(dustValueText);

		timeProcess = 10 * dustValue;
		unCleaned = DustColor.getColor(dustValue);
		setBackground(unCleaned);

		if (dustLabel != null) {
			remove(dustLabel);
		}

		String html = "<html><center>VALUE<br>LOAIRAC</center></html>";
		if (Integer.parseInt(dustValueText) < 70) {
			html = html.replace("VALUE", "");
		} else {
			html = html.replace("VALUE", dustValueText);
		}
		html = html.replace("LOAIRAC", phanLoaiRac);
		dustLabel = new JLabel(html);
		dustLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dustLabel.setForeground(Color.BLACK);
		dustLabel.setBounds(1, 1, size - 2, size - 2);

		this.add(dustLabel);
		// dustLabel.setVisible(showDustValue);
		dustLabel.setVisible(false);
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		if (size > 0) {

			return new Dimension(size, size);
		} else {

			return new Dimension(20, 20);
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void switchShowDustValueType(boolean value) {
		this.showDustValue = value;
		dustLabel.setVisible(value);
		repaint();
	}

	public String getDustValueText() {
		return dustValueText;
	}

	public int getDustInt() {
		return dustInt;
	}

	public void setDustInt(int dustInt) {
		this.dustInt = dustInt;
	}

	public int getPhanLoaiRac() {

		// if (getDustInt() >= 70 && getDustInt() < 80) {
		// phanLoaiRac = 1;
		// } else if (getDustInt() >= 80 && getDustInt() < 90) {
		// phanLoaiRac = 2;
		// } else if (getDustInt() >= 90 && getDustInt() <= 100) {
		// phanLoaiRac = 3;
		// }
		return phanLoaiRac;
	}

	public void setPhanLoaiRac(int phanLoaiRac) {
		this.phanLoaiRac = phanLoaiRac;
	}

	public int getxCell() {
		return xCell;
	}

	public void setxCell(int xCell) {
		this.xCell = xCell;
	}

	public int getyCell() {
		return yCell;
	}

	public void setyCell(int yCell) {
		this.yCell = yCell;
	}

	public int getStatusCheck() {
		return statusCheck;
	}

	public void setStatusCheck(int statusCheck) {
		this.statusCheck = statusCheck;
	}

	public int getDoBan() {
		return doBan;
	}

	public void setDoBan(int doBan) {
		this.doBan = doBan;
	}

	public boolean isClear() {
		return isClear;
	}

	public void setClear(boolean isClear) {
		this.isClear = isClear;
	}

	public double getPin() {
		double a = (double) getDustInt() / 10 + 1;
		return a;
	}

	public int getNumOfHuuCo() {
		return numOfHuuCo;
	}

	public void setNumOfHuuCo(int numOfHuuCo) {
		this.numOfHuuCo = numOfHuuCo;
	}

	public int getNumOfVoco() {
		return numOfVoco;
	}

	public void setNumOfVoco(int numOfVoco) {
		this.numOfVoco = numOfVoco;
	}

	public int getNumOfTaiChe() {
		return numOfTaiChe;
	}

	public void setNumOfTaiChe(int numOfTaiChe) {
		this.numOfTaiChe = numOfTaiChe;
	}

	public String getStringShow() {
		return stringShow;
	}

	public void setStringShow(String stringShow) {
		this.stringShow = stringShow;
	}

	public static int getCellCoffe() {
		return cellCoffe;
	}

	public static void setCellCoffe(int cellCoffe) {
		CellPane.cellCoffe = cellCoffe;
	}

	public static int getCellWater() {
		return cellWater;
	}

	public static void setCellWater(int cellWater) {
		CellPane.cellWater = cellWater;
	}

	public static int getCellSoda() {
		return cellSoda;
	}

	public static void setCellSoda(int cellSoda) {
		CellPane.cellSoda = cellSoda;
	}

	public static String includeLinkImage() {
		String linkString = "";
		String[] listStrings = new String[50];
		for (int i = 1; i <= 50; i++) {
			listStrings[i - 1] = Integer.toString(i) + ".png";
		}
		Random rdRandom = new Random();
		int index = rdRandom.nextInt(50);
		linkString = listStrings[index];
		return linkString;
	}

	public static String includeLinkEmotion() {
		String linkString = "";
		Random rdRandom = new Random();
		int index = rdRandom.nextInt(100); // 0-99
		if (index < 10) {
			linkString = "angry.png";
		} else if (index >= 10 && index < 25) {
			linkString = "sad.png";
		} else if (index >= 25 && index < 45) {
			linkString = "normal.png";
		} else if (index >= 45 && index < 70) {
			linkString = "happy.png";
		} else if (index >= 70 && index < 100) {
			linkString = "impressive.png";
		}
		return linkString;
	}

	public static int setPoint(String linkString) {
		int point = 0;
		if (linkString.equals("angry.png")) {
			point = 0;
		} else if (linkString.equals("sad.png")) {
			point = 3;
		} else if (linkString.equals("normal.png")) {
			point = 5;
		} else if (linkString.equals("happy.png")) {
			point = 7;
		} else if (linkString.equals("impressive.png")) {
			point = 10;
		}
		return point;
	}

	public int getIsServed() {
		return isServed;
	}

	public void setIsServed(int isServed) {
		this.isServed = isServed;
	}

}
