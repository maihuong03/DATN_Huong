package hcstt;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.CellEditor;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import common.HedgeAlgebra;
import gui.CellPane;
import gui.MainGui;
import gui.StatusBar;

/**
 * @author HuyLV
 *
 */

public class Robot extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int size; // Kích thước vùng làm việc
	private Timer timer;
	private int DELAY = 250; // delay giữa cac step
	private List<CellPane> listCells;
	private List<Boolean> listCellStatus;
	private List<Integer> listWorkingTime;
	private MegaCell current;
	private int x = -1, y = -1, xPrev = -1, yPrev = -1;
	private int moveFlag = 0;
	private int moveTrend;
	private int workFirst = 0;
	List<DynamicBarrier> listDynamicBarriers;
	private int workStatus = 0; // Làm việc hoặc tạm dừng
	private int workingMode; // Lua chon che do lam viec cua robot
	private int numOfBarrier = 0;
	private static int numOfHuuCo = 0;
	private static int numOfVoCo = 0;
	private static int numOfTaiChe = 0;

	// Luu so buoc di chuyen cua robot
	private int numOfSteps = 0;
	private int duplicateSteps = 0;
	private boolean isFinalStep = false;
	private StatusBar statusBar;
	private int[] listOfDustValue;
	private static int sumOfDust = 0;
	private static int timeToServe = 2000;
	private static String linkIMG = "";
	// lấy thời gian DELAY

	public Robot(int size, MainGui f) {
		this.container = f;
		this.size = size;
		listCellStatus = new ArrayList<>();
		for (int i = 0; i < size * size; i++) {
			listCellStatus.add(true);
		}
	}

	// lấy thời gian
//	public int getDELAY() {
//		return DELAY;
//	}
//
//	public void setDELAY(int dELAY) {
//		DELAY = dELAY;
//	}

	// thiết lập ma trận để làm việc
	public void init() {
		workStatus = 0;
		current = new MegaCell();
		current.setParent(null);
		current.setStatus(true);
		current.setX(0);
		current.setY(0);
		moveFlag = 0;

		int robotSize = this.getWidth() / size; // Kích thước robot khoảng cách
												// chia độ rộng

		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		listCells = new ArrayList<>();
		listWorkingTime = new ArrayList<>();

		// thiết lập hiển thị ma trận
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				gbc.gridx = col;
				gbc.gridy = row;

				CellPane cellPane = new CellPane(0, robotSize, this);
				cellPane.addMouseListener(this);
				listCells.add(cellPane);
				Border border = null;
				border = new MatteBorder(1, 1, 0, 0, Color.GRAY); // màu sắc giữa các ô
				cellPane.setBorder(border);
				add(cellPane, gbc);

				// double r = Math.random() * 10;
				int workingTimeInit = (int) 250;
				listWorkingTime.add(workingTimeInit);
			}
		}
		listCells.get(0).changeToRobot(4);
		listDynamicBarriers = new ArrayList<>();

		timer = new Timer(DELAY, this);
		timer.start();
	}

	// Tăng số bước đi bị lặp
	public void increaseDuplicateSteps(int value) {
		duplicateSteps += value;
	}

	// Lấy số bước đi bị lặp
	public int getDuplicateSteps() {
		return this.duplicateSteps;
	}

	private MainGui container;

	// bắt đầu
	public void start(int workingMode) {
		numOfHuuCo = 0;
		numOfVoCo = 0;
		numOfTaiChe = 0;
		switch (workingMode) {
		case MainGui.STANDARD:
			DELAY = container.getStandardWorkingTime();
			break;
		case MainGui.STANDARD_MODE:
			DELAY = container.getStandardWorkingTime();
			break;
		case MainGui.DYNAMIC_MODE:
			DELAY = 1000;
			break;
		case MainGui.DSGT_MODE:
			DELAY = 0;
			break;
		case MainGui.DSGT_DINAYMIC_MODE:
			DELAY = 0;
			break;
		default:
			break;
		}
		this.workStatus = 1;
		this.workingMode = workingMode;
		for (int i = 0; i < listCells.size(); i++) {
			listCells.get(i).removeMouseListener(this);
		}
	}

	// restart
	public void restart(int workingMode) {
		numOfHuuCo = 0;
		numOfVoCo = 0;
		numOfTaiChe = 0;
		timer.stop();
		this.workStatus = 0;
		moveFlag = 0;
		isFinalStep = false;
		workFirst = 0;
		numOfSteps = 0;
		duplicateSteps = 0;
		for (int i = 0; i < listCells.size(); i++) {
			CellPane cell = listCells.get(i);
			if (cell.getStatus() != -1) {
				cell.changeToNormal();
				listCellStatus.set(i, true);
			}
		}

		this.workingMode = workingMode;

		current = new MegaCell();
		current.setParent(null);
		current.setStatus(true);
		current.setX(0);
		current.setY(0);
		x = current.getX();
		y = current.getY();

		this.workStatus = 1;
		timer.start();
	}

	// set độ bẩn , tính toán lượng pin tổng, tính toán được tổng số nước order
	public void initDust() {
		sumOfDust = 0;
		int ix = 0;
		double luongPinTinhToan = 0;
		MainGui.resetOrder();
		for (CellPane cell : listCells) {
			if (cell.getStatus() != -1) {
				String a = randomDust();
				int b = Integer.parseInt(a);
				double c;
				if (b >= 70) {
					c = (double) b / 10 + 1; // lượng pin tiêu hao tương ứng với độ bẩn
				} else {
					c = 1;
				}
				String phanLoaiRac;
				if (b >= 0 && b < 70) {
					phanLoaiRac = "";
					cell.setPhanLoaiRac(0);
				} else if (b >= 70 && b < 80) {
					numOfVoCo++;
					cell.setPhanLoaiRac(1);
					phanLoaiRac = "Inorganic";

				} else if (b >= 80 && b < 90) {
					numOfTaiChe++;
					cell.setPhanLoaiRac(2);
					phanLoaiRac = "Recycle";
				} else {
					numOfHuuCo++;
					cell.setPhanLoaiRac(3);
					phanLoaiRac = "Organic";
				}
				cell.setDustValueText(a, phanLoaiRac);
				cell.setDustInt(b);
				cell.setDoBan(b);
//			listOfDustValue[ix] = cell.getDustInt();
				System.out.println("Độ bận hiện tại là: " + a + " -- " + c + "\nLoại rác: " + cell.getPhanLoaiRac());
//			cell.setDustValueText(randomDust());
//			System.out.println("Độ bẩn hiện tại: "+ i + " --" + randomDust());
				cell.removeMouseListener(this);
				sumOfDust += Integer.parseInt(a);
				ix++;
				luongPinTinhToan += c;
			} else if (cell.getStatus() == -1) {
				MainGui.setSumOfCoffe(MainGui.getSumOfCoffe() + cell.getCellCoffe());
				MainGui.setSumOfSoda(MainGui.getSumOfSoda() + cell.getCellSoda());
				MainGui.setSumOfWater(MainGui.getSumOfWater() + cell.getCellWater());
			}
		}
		setXYForCell();
		MainGui.setLuongPinTinhToan(luongPinTinhToan);
//		System.out.println("Độ bẩn trong initDust - Robot là: " + getSumOfDust());
		System.out.println("Lượng pin tính toán được là: " + luongPinTinhToan);
	}

	public void setXYForCell() {
		int xCell = 0;
		int yCell = 0;
		for (int i = 0; i < listCells.size(); i++) {
			listCells.get(i).setxCell(xCell++);
			listCells.get(i).setyCell(yCell);
			if (xCell == MainGui.getSizeOfMap()) {
				xCell = 0;
				yCell++;
			}
		}
		System.out.println("show XY of listcell: ");
		for (int i = 0; i < listCells.size(); i++) {
			System.out.print("-" + listCells.get(i).getxCell() + "." + listCells.get(i).getyCell());
		}
	}

	// chuyển độ bẩn thành chữ
	public void switchShowDustValueType(boolean value) {
		for (CellPane cell : listCells) {
			cell.switchShowDustValueType(value);
		}
	}

	// trả về số bước di chuyển
	public int getNumOfSteps() {
		return numOfSteps;
	}

	// random độ bẩn
	private String randomDust() {
		String result = "0";
		long ran = (long) (Math.random() * 100);

//		if (ran > 70) {
//			int index = (int) (7 * Math.random());
//			result = HedgeAlgebra.langValuesVi[index];
//		} else {
		result = "" + (int) (100 * Math.random());
//		}

		return result;
	}

	// trả về trạng thái làm việc
	public int getWorkStatus() {
		return workStatus;
	}

	// thay đỏi cell thành vật cản di động
	public void changeToDynamicBarrier(int x, int y) {
		listCells.get(x + y * size).changeToDynamicBarrier(); // Thay đổi cell
																// thành
		// vật cản di động
	}

	// thay đổi thành cell ban đầu
	public void changeToNormal(int x, int y) {
		listCells.get(x + y * size).changeToOldStatus(); // thay đổi cell thành
															// trạng thái ban
															// đầu

	}

	public int getAreaSize() { // Kích thước vùng lm việc
		return size;
	}

	// kiểm tra có free hay k
	public boolean isFree(int x, int y) {
		int cellStatus = listCells.get(x + y * size).getStatus();
		return (cellStatus == 0 || cellStatus > 1);
	}

	public void stop() {
		this.workStatus = 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < listCells.size(); i++) { // đặt vật cản cố định
			CellPane cell = listCells.get(i);
			if (e.getSource() == cell) {
				if (MainGui.getModeVatCanInt() == 0) {
					if (cell.getStatus() == 0) {
						listCells.get(i).changeToBarrier();
						numOfBarrier++;
					} else {
						listCells.get(i).changeToNormal();
						numOfBarrier--;
					}
				} else if (MainGui.getModeVatCanInt() == 1) {
					if (cell.getStatus() == 0) {
						listCells.get(i).changeToOrder();
						numOfBarrier++;
					} else {
						listCells.get(i).changeToNormal();
						numOfBarrier--;
					}
				}
			}
		}
	}

	// overide thôi
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
	// hết override

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (getWorkStatus() == 1) {
			this.timer.stop();
			this.timer.setInitialDelay(DELAY);
			this.timer.setDelay(DELAY);
			this.timer.restart();
			if (workingMode == MainGui.STANDARD) {
				this.stc();
			} else {
				this.stc_full();
			}
		}
	}

	private void stc() {
		current.setStatus(false);
		setOldMegacell(current);

		if (moveFlag == 0) {
			MegaCell neightboor = findFreeeNeighboor(current);
			if (neightboor != null) {
				x = current.getX();
				y = current.getY();
				current = neightboor;
			} else {
				if (!isFinalStep) {
					x = current.getX();
					y = current.getY();
					isFinalStep = true;
				} else {
					finalStep();
				}
			}
		} else {
			robotWork(x, y);
		}
	}

	private void robotWork(int x, int y) {
		if (isRobotHere(x, y)) { // Kiểm tra vật cản di động có cùng megacell
			return;
		}

		if (moveTrend == 1) {
			moveUP();

			return;
		}

		if (moveTrend == 2) {
			moveLEFT();

			return;
		}

		if (moveTrend == 3) {
			moveDOWN();

			return;
		}

		if (moveTrend == 4) {
			moveRIGHT();

			return;
		}
	}

	public boolean isRobotHere(int x, int y) { // Kiềm tra vật cản di động trong
												// megacell

		for (DynamicBarrier barrier : listDynamicBarriers) {
			int x0 = (x / 2) * 2;
			int y0 = (y / 2) * 2;
			System.out.print("tao do barrier hien tai " + barrier.getX() + "," + barrier.getY() + "và x0=" + x0 + " y0="
					+ y0 + "\n");
			if ((Math.abs(barrier.getX() - x0) < 2) && Math.abs(y0 - barrier.getY()) < 2) {
				System.out.print("OK");
				return true;
			}
		}
//		System.out.print("flase");
		return false;
	}

	private MegaCell findFreeeNeighboor(MegaCell current) {
		// up
		MegaCell up = new MegaCell();
		up.setParent(current);
		up.setX(((current.getX()) / 2) * 2);
		up.setY(((current.getY() - 2) / 2) * 2);
		if ((up.getY() >= 0) && isFree(up)) {
			up.setX(up.getX() + 1);
			up.setY(up.getY() + 1);
			up.setStatus(true);
			moveTrend = 1;
			moveFlag = 1;
			return up;
		}

		// left
		MegaCell left = new MegaCell();
		left.setParent(current);
		left.setX(((current.getX() - 2) / 2) * 2);
		left.setY(((current.getY()) / 2) * 2);
		if ((left.getX() >= 0) && isFree(left)) {
			left.setX(left.getX() + 1);
			left.setStatus(true);
			moveTrend = 2;
			moveFlag = 1;

			return left;
		}
		// down
		MegaCell down = new MegaCell();
		down.setParent(current);
		down.setX(((current.getX()) / 2) * 2);
		down.setY(((current.getY() + 2) / 2) * 2);
		if ((down.getY() < (size - 1)) && isFree(down)) {
			down.setStatus(true);
			moveTrend = 3;
			moveFlag = 1;

			return down;
		}
		// right
		MegaCell right = new MegaCell();
		right.setParent(current);
		right.setX(((current.getX() + 2) / 2) * 2);
		right.setY(((current.getY()) / 2) * 2);
		if ((right.getX() < (size - 1)) && isFree(right)) {
			right.setY(right.getY() + 1);
			right.setStatus(true);
			moveTrend = 4;
			moveFlag = 1;

			return right;
		}
		if (current.getParent() != null) {

			if (current.getParent().isEqual(up)) {
				current.getParent().setX(up.getX() + 1);
				current.getParent().setY(up.getY() + 1);
				moveTrend = 1;
			} else if (current.getParent().isEqual(left)) {
				current.getParent().setX(left.getX() + 1);
				current.getParent().setY(left.getY());
				moveTrend = 2;
			} else if (current.getParent().isEqual(down)) {
				current.getParent().setX(down.getX());
				current.getParent().setY(down.getY());
				moveTrend = 3;
			} else if (current.getParent().isEqual(right)) {
				current.getParent().setX(right.getX());
				current.getParent().setY(right.getY() + 1);
				moveTrend = 4;
			}
			moveFlag = 1;
		}

		return current.getParent();
	}

	private void finalStep() {
		if (x == 0 && y == 0) {
			timer.stop();
			container.reset();
		} else {
			moveLEFT();
		}
	}

	private void moveUP() {
		if (workFirst == 0) {
			workingAndChangeStatus(x, y);
			workFirst = 1;

			return;
		} else {
			xPrev = x;
			yPrev = y;
		}

		if (x < ((x / 2) * 2 + 1) && y < ((y / 2) * 2 + 1)) {
			y++;
			workingAndChangeStatus(x, y);

			return;
		}

		if (x < ((x / 2) * 2 + 1)) {
			x++;
			workingAndChangeStatus(x, y);

			return;
		}

		if (y > (y / 2) * 2) {
			y--;
			workingAndChangeStatus(x, y);

			return;
		} else {
			workFirst = 0;
			moveFlag = 0;
		}
	}

	private void moveDOWN() {
		if (workFirst == 0) {
			workingAndChangeStatus(x, y);
			workFirst = 1;

			return;
		} else {
			xPrev = x;
			yPrev = y;
		}

		if (x > ((x / 2) * 2) && y > (y / 2) * 2) {
			y--;
			workingAndChangeStatus(x, y);

			return;
		}

		if (x > ((x / 2) * 2)) {
			x--;
			workingAndChangeStatus(x, y);

			return;
		}
		if (y < ((y / 2) * 2 + 1)) {
			y++;
			workingAndChangeStatus(x, y);

			return;
		} else {
			workFirst = 0;

			moveFlag = 0;
		}
	}

	private void moveLEFT() {
		if (workFirst == 0) {
			workingAndChangeStatus(x, y);
			workFirst = 1;

			return;
		} else {
			xPrev = x;
			yPrev = y;
		}

		if (y > ((y / 2) * 2) && x < ((x / 2) * 2 + 1)) {
			x++;
			workingAndChangeStatus(x, y);

			return;
		}
		if (y > ((y / 2) * 2)) {
			y--;
			workingAndChangeStatus(x, y);

			return;
		}

		if (x > ((x / 2) * 2)) {
			x--;
			workingAndChangeStatus(x, y);

			return;
		} else {
			workFirst = 0;

			moveFlag = 0;
		}
	}

	private void moveRIGHT() {
		if (workFirst == 0) {
			workingAndChangeStatus(x, y);
			workFirst = 1;

			return;
		} else {
			xPrev = x;
			yPrev = y;
		}

		if (y < ((y / 2) * 2 + 1) && x > ((x / 2) * 2)) {
			x--;
			workingAndChangeStatus(x, y);

			return;
		}
		if (y < ((y / 2) * 2 + 1)) {
			y++;
			workingAndChangeStatus(x, y);

			return;
		}

		if (x < ((x / 2) * 2) + 1) {
			x++;
			workingAndChangeStatus(x, y);

			return;
		} else {
			workFirst = 0;

			moveFlag = 0;
		}
	}

	private boolean isFree(MegaCell mega) {
//		int x = (mega.getX() / 2) * 2;
//		int y = (mega.getY() / 2) * 2;
		int x = mega.getX();
		int y = mega.getY();

		int index0 = x + y * size;
		int index1 = x + 1 + y * size;
		int index2 = x + (y + 1) * size;
		int index3 = x + 1 + (y + 1) * size;

		if (listCells.get(index0).getStatus() == 0 && listCells.get(index1).getStatus() == 0
				&& listCells.get(index2).getStatus() == 0 && listCells.get(index3).getStatus() == 0
				&& listCellStatus.get(x + y * size) == true) {

			return true;
		}
		return false;
	}

	// thay đổi trạng thái làm việc của ô
	private void workingAndChangeStatus(int x, int y) {
		int workingTime = getWorkTime(x, y);
		// đếm số bước đi
		numOfSteps += 1;
		if (x > xPrev) {
			listCells.get(x + y * size).changeToRobot(4);
//			container.setTestcellpane(listCells.get(x + y * size).getDustInt());
			if ((xPrev + yPrev * size) >= 0) {
				listCells.get(xPrev + yPrev * size).changeStatus(4);
			}
		} else if (x < xPrev) {
			listCells.get(x + y * size).changeToRobot(3);
//			container.setTestcellpane(listCells.get(x + y * size).getDustInt());

			if ((xPrev + yPrev * size) >= 0) {
				listCells.get(xPrev + yPrev * size).changeStatus(3);
			}
		} else if (y > yPrev) {
			listCells.get(x + y * size).changeToRobot(2);
//			container.setTestcellpane(listCells.get(x + y * size).getDustInt());

			if ((xPrev + yPrev * size) >= 0) {
				listCells.get(xPrev + yPrev * size).changeStatus(2);
			}
		} else if (y < yPrev) {
			listCells.get(x + y * size).changeToRobot(1);
//			container.setTestcellpane(listCells.get(x + y * size).getDustInt());

			if ((xPrev + yPrev * size) >= 0) {
				listCells.get(xPrev + yPrev * size).changeStatus(1);
			}
		}
		System.out.println("current: x - " + current.getX() + " - y - " + current.getY());
		container.changeStatusBar(x, y, workingTime);
		//
		int dustFromListCell = 0;
		//
		int iindex = 0;
		CellPane cellX = new CellPane();
		for (int i = 0; i < listCells.size(); i++) {
			// lấy giá trị tọa độ của cell hiện tại
			if (!listCells.get(i).isClear() && x == listCells.get(i).getxCell() && y == listCells.get(i).getyCell()) {
				cellX = listCells.get(i);
				iindex = i;
				if (listCells.get(i).getPhanLoaiRac() == 1) {// check xem ô hiện tại là loại rác nào thì tăng loại rác
																// đó lên
					MainGui.setLoaiVoCo(MainGui.getLoaiVoCo() + 1);
					MainGui.setNumOfVoCo(MainGui.getNumOfVoCo() + 1);
					MainGui.setLuongPin(MainGui.getLuongPin() - listCells.get(i).getPin());
//					listCells.get(i).changeBackGround("warning.png");
				} else if (listCells.get(i).getPhanLoaiRac() == 2) {
					MainGui.setLoaiTaiche(MainGui.getLoaiTaiche() + 1);
					MainGui.setNumOfTaiChe(MainGui.getNumOfTaiChe() + 1);
					MainGui.setLuongPin(MainGui.getLuongPin() - listCells.get(i).getPin());
//					listCells.get(i).changeBackGround("warning.png");

				} else if (listCells.get(i).getPhanLoaiRac() == 3) {
					MainGui.setLoaiHuuCo(MainGui.getLoaiHuuCo() + 1);
					MainGui.setNumOfHuuCo(MainGui.getNumOfHuuCo() + 1);
					MainGui.setLuongPin(MainGui.getLuongPin() - listCells.get(i).getPin());
//					listCells.get(i).changeBackGround("warning.png");
				}
//				else {
//					listCells.get(i).changeBackGround("robotX.png");
//				}
				listCells.get(i).setClear(true);
				break;
			}
		}

		System.out.println("vi tri cua cell hien tai trong listcell: " + iindex);
		CellPane cellRight = new CellPane();
		CellPane cellLeft = new CellPane();
		CellPane cellUp = new CellPane();
		CellPane cellDown = new CellPane();
		int xiindex = listCells.get(iindex).getxCell();
		int yiindex = listCells.get(iindex).getyCell();
		for (int i = 0; i < listCells.size(); i++) {
			// ví dụ như trong dấu cộng, cell đang đứng là cell có vị trí là iindex, sau đó
			// tìm 4 cell up, down, left, right. nếu mà 4 cell lân cận kia là thùng rác thì
			// bỏ rác tương ứng vào thùng, tức là sẽ set sô rác tương ứng = 0
			int xi = listCells.get(i).getxCell();
			int yi = listCells.get(i).getyCell();

			if (xi == xiindex && yi == yiindex - 1 && yi > 0) {
				cellUp = listCells.get(i); // UP
				System.out.println("co cell Up");
//				listCells.get(iindex).changeBackGround("warning.png");
			}
			if (xi == xiindex + 1 && yi == yiindex && xi < MainGui.getSizeOfMap() - 1) {
				cellRight = listCells.get(i);// RIGHT
				System.out.println("co cellRight");
//				listCells.get(iindex).changeBackGround("warning.png");
			}
			if (xi == xiindex && yi == yiindex + 1 && yi < MainGui.getSizeOfMap() - 1) {
				cellDown = listCells.get(i); // DOWN
				System.out.println("co cellDown");
//				listCells.get(iindex).changeBackGround("warning.png");
			}
			if (xi == xiindex - 1 && yi == yiindex && xi > 0) {
				cellLeft = listCells.get(i); // LEFT
				System.out.println("co cellLeft");
//				listCells.get(iindex).changeBackGround("warning.png");
//			}else {
//				listCells.get(iindex).changeBackGround("robotX.png");
//
			}
		}

		boolean isSetImage = false;
		// check trạng thái của 4 cell xung quanh nếu là -1 thì là vật cản cố định, kiểu
		// tra status check
//		DELAY = 250;
		if (cellUp.getStatus() == -1 && cellUp.getIsServed() == 0) {
			int tr = cellUp.getStatusCheck();
			if (tr == 1 ) {
					cellUp.orderDone();
					System.out.println("Da phuc vu xong phia tren");
					isSetImage = true;
//					cellUp.changeBackgroundAndHold("robotX.png");
					timer.setDelay(2000);
					DELAY = 2000;
			}
		}

		if (cellRight.getStatus() == -1 && cellRight.getIsServed() == 0) {
			int tr = cellRight.getStatusCheck();
			if (tr == 1 ) {
					cellRight.orderDone();
					System.out.println("Da phuc vu xong phia phai");					
//					cellRight.changeBackgroundAndHold("robotX.png");
					isSetImage = true;
					timer.setDelay(2000);
					DELAY = 2000;

			}
		}

		if (cellDown.getStatus() == -1 && cellDown.getIsServed() == 0) {
			int tr = cellDown.getStatusCheck();
			if (tr == 1 ) {
					cellDown.orderDone();
					System.out.println("Da phuc vu xong phia duoi");				
//					cellDown.changeBackgroundAndHold("robotX.png");

					isSetImage = true;
					timer.setDelay(2000);
					DELAY = 2000;

			}
		}

		if (cellLeft.getStatus() == -1 && cellLeft.getIsServed() == 0) {
			int tr = cellLeft.getStatusCheck();
			if (tr == 1) {
					cellLeft.orderDone();
					System.out.println("Da phuc vu xong phia trai");					
//					cellLeft.changeBackgroundAndHold("robotX.png");
					isSetImage = true;
					timer.setDelay(2000);
					DELAY = 2000;

			}
		}

		if (listCells.get(iindex).getStatus() != -1 && isSetImage) {
			listCells.get(iindex).changeBackGround("robotXServed.png");
//			listCells.get(iindex).changeBackgroundToGif("loading.gif");
//			timer.setDelay(2000);
//			timer.stop();
//			timer.setDelay(2000);
//			timer.start();

		}
		else {
			timer.setDelay(2000);
		}
		
		System.out.println("Lam viec tai: " + x + "-" + y);
		MainGui.settextlabelcount();
	}

	public void changeStatusBar(int x, int y, int time) {
		statusBar.setMessage(x, y, time);
	}

	// trả về thời gian làm việc tại các mode
	private int getWorkTime(int x, int y) {
		switch (workingMode) {
		case MainGui.STANDARD_MODE:
			DELAY = container.getStandardWorkingTime();
			break;
		case MainGui.DYNAMIC_MODE:
			DELAY = listCells.get(x + y * size).getTimeProcess();
			break;
		case MainGui.STANDARD:
			DELAY = container.getStandardWorkingTime();
			break;
		case MainGui.DSGT_MODE:
			DELAY = listCells.get(x + y * size).getTimeProcess();
			break;
		case MainGui.DSGT_DINAYMIC_MODE:
			DELAY = listCells.get(x + y * size).getTimeProcess();
			break;
		default:
			break;
		}
		listWorkingTime.set(x + y * size, 100);

		return DELAY;
	}

	private void setOldMegacell(MegaCell mega) {
		listCellStatus.set((mega.getX() / 2) * 2 + (mega.getY() / 2) * 2 * size, false);
	}

	// kiểm tra vật cản cố định tại vị trí này và thêm vật cản cổ định
	public boolean isBarrier(int x, int y, int speed, int direction) {
		boolean result = (listCells.get(x + y * size).getStatus() == -1);
		if (!result) {
			listDynamicBarriers.add(new DynamicBarrier(x, y, speed, direction, this));
		}
		return result;
	}

	// trả về số lượng vật cản
	public int getNumOfBarrier() {
		return numOfBarrier;
	}

	public void stc_full() {
		current.setStatus(false);
		setOldMegacell_full(current);

		if (moveFlag == 0) {
			MegaCell neightboor = findNewNeighboor(current);
			if (neightboor != null) {
				x = current.getX();
				y = current.getY();
				current = neightboor;
			} else {
				if (!isFinalStep) {
					x = current.getX();
					y = current.getY();
					isFinalStep = true;
				} else {
					finalStep();
				}
			}
		} else {
			robotWork_full(x, y);
		}
	}

	// tìm cha
	public MegaCell findNewNeighboor(MegaCell current) {

		// up
		MegaCell up = new MegaCell();
		up.setParent(current);
		up.setX(((current.getX()) / 2) * 2);
		up.setY(((current.getY() - 2) / 2) * 2);
		System.out.println("- up: getx = " + up.getX() + " gety: " + up.getY());
		if ((up.getY() >= 0) && isNewUp(current)) {
			up.setX(up.getX() + 1);
			up.setY(up.getY() + 1);
			up.setStatus(true);
			moveTrend = 1;
			moveFlag = 1;
			return up;
		}

		// left
		MegaCell left = new MegaCell();
		left.setParent(current);
		left.setX(((current.getX() - 2) / 2) * 2);
		left.setY(((current.getY()) / 2) * 2);
		System.out.println("- left: getx = " + left.getX() + " gety: " + left.getY());
		if ((left.getX() >= 0) && isNewLeft(current)) {
			left.setX(left.getX() + 1);
			left.setStatus(true);
			moveTrend = 2;
			moveFlag = 1;

			return left;
		}
		// down
		MegaCell down = new MegaCell();
		down.setParent(current);
		down.setX(((current.getX()) / 2) * 2);
		down.setY(((current.getY() + 2) / 2) * 2);
		System.out.println("- down getx =" + down.getX() + " gety = " + down.getY());
		if ((down.getY() < (size - 1)) && isNewDown(current)) {
			down.setStatus(true);
			moveTrend = 3;
			moveFlag = 1;

			return down;
		}
		// right
		MegaCell right = new MegaCell();
		right.setParent(current);
		right.setX(((current.getX() + 2) / 2) * 2);
		right.setY(((current.getY()) / 2) * 2);
		System.out.println("- right getX:  " + right.getX() + " gety:" + right.getY());
		if ((right.getX() < (size - 1)) && isNewRight(current)) {
			right.setY(right.getY() + 1);
			right.setStatus(true);
			moveTrend = 4;
			moveFlag = 1;

			return right;
		}

		// tim huong thang cha nam o dau?????
		if (current.getParent() != null) {

			if (current.getParent().isEqual(up)) {
				current.getParent().setX(up.getX() + 1);
				current.getParent().setY(up.getY() + 1);
				moveTrend = 1;
			} else if (current.getParent().isEqual(left)) {
				current.getParent().setX(left.getX() + 1);
				current.getParent().setY(left.getY());
				moveTrend = 2;
			} else if (current.getParent().isEqual(down)) { //
				current.getParent().setX(down.getX());
				current.getParent().setY(down.getY());
				moveTrend = 3;
			} else if (current.getParent().isEqual(right)) {
				current.getParent().setX(right.getX());
				current.getParent().setY(right.getY() + 1);
				moveTrend = 4;
			}
			moveFlag = 1;
		}
		return current.getParent();

	}

	// cac MegaCell khi khoi tao ban do chua duoc dinh nghia ve status
	public Boolean isNewUp(MegaCell mega) {
		int x = (mega.getX() / 2) * 2; // toa do x cua megacell hien tai
		int y = (mega.getY() / 2) * 2; // toa do y cua megacell hien tai
		int x_cell = mega.getX(); // toa do x cua cell hien tai
		int y_cell = mega.getY(); // toa do y cua cell hien tai
		if (y >= 2) {
			if (listCellStatus.get(x + (y - 2) * size) == true) { // kiem tra
																	// xem da
				// tung duyet
				// qua chua
				// kiem tra xem co bi chan duong di len tren hay khong???
				if ((listCells.get(x + (y - 1) * size).getStatus() == -1
						&& listCells.get(x + 1 + (y - 1) * size).getStatus() == -1)
						|| (listCells.get(x + (y - 1) * size).getStatus() == -1
								&& listCells.get(x + 1 + y * size).getStatus() == -1)
						|| (listCells.get(x + 1 + (y - 1) * size).getStatus() == -1
								&& listCells.get(x + y * size).getStatus() == -1)) {
					return false;
				}
				if (y_cell > (y_cell / 2) * 2) { // neu la o o duoi cua megacell
					if (listCells.get(x + y * size).getStatus() == -1
							&& listCells.get(x + 1 + y * size).getStatus() == -1)
						return false;
					if (x_cell == (x_cell / 2) * 2) { // neu la o trai duoi
						if (listCells.get(x + y * size).getStatus() == -1
								&& listCells.get(x + 1 + (y + 1) * size).getStatus() == -1)
							return false;
					} else { // neu la o phai duoi
						if (listCells.get(x + 1 + y * size).getStatus() == -1
								&& listCells.get(x + (y + 1) * size).getStatus() == -1)
							return false;
					}
				}
				return true;
			} else { // da tung duyet r
				if ((listCells.get(x + (y - 2) * size).getStatus() == -1
						&& listCells.get(x + 1 + (y - 1) * size).getStatus() == -1
						&& listCells.get(x + (y - 1) * size).getStatus() == 0)
						|| (listCells.get(x + (y - 1) * size).getStatus() == -1
								&& listCells.get(x + 1 + (y - 2) * size).getStatus() == -1
								&& listCells.get(x + 1 + (y - 1) * size).getStatus() == 0)) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public Boolean isNewLeft(MegaCell mega) {
		int x = (mega.getX() / 2) * 2; // toa do x cua megacell hien tai
		int y = (mega.getY() / 2) * 2;// toa do y cua megacell hien tai
		int x_cell = mega.getX(); // toa do x cua cell hien tai
		int y_cell = mega.getY(); // toa do y cua cell hien tai

		if (x >= 2) {
			if (listCellStatus.get(x - 2 + y * size) == true) { // kiem tra xem
																// da
				// tung
				// duyet qua chua
				// kiem tra xem co bi chan duong di sang trai hay khong???
				if ((listCells.get(x - 1 + y * size).getStatus() == -1
						&& listCells.get(x - 1 + (y + 1) * size).getStatus() == -1)
						|| (listCells.get(x - 1 + y * size).getStatus() == -1
								&& listCells.get(x + (y + 1) * size).getStatus() == -1)
						|| (listCells.get(x - 1 + (y + 1) * size).getStatus() == -1
								&& listCells.get(x + y * size).getStatus() == -1)) {
					return false;
				}

				if (x_cell > (x_cell / 2) * 2) { // neu la o ben phai
					if (listCells.get(x + y * size).getStatus() == -1
							&& listCells.get(x + (y + 1) * size).getStatus() == -1)
						return false;
					if (y_cell == (y_cell / 2) * 2) { // neu la o phai tren
						if (listCells.get(x + 1 + y * size).getStatus() == -1
								&& listCells.get(x + (y + 1) * size).getStatus() == -1)
							return false;
					} else { // neu la o phai duoi
						if (listCells.get(x + 1 + y * size).getStatus() == -1
								&& listCells.get(x + (y + 1) * size).getStatus() == -1)
							return false;
					}
				}
				return true;
			} else {// da tung duyet r
				if ((listCells.get(x - 2 + y * size).getStatus() == -1
						&& listCells.get(x - 1 + (y + 1) * size).getStatus() == -1
						&& listCells.get(x - 1 + y * size).getStatus() == 0)
						|| (listCells.get(x - 2 + (y + 1) * size).getStatus() == -1
								&& listCells.get(x - 1 + y * size).getStatus() == -1
								&& listCells.get(x - 1 + (y + 1) * size).getStatus() == 0)) {
					return true;
				} else {
					return false;
				}

			}
		}
		return false;
	}

	public Boolean isNewDown(MegaCell mega) {
		int x = (mega.getX() / 2) * 2; // toa do x cua megacell hien tai
		int y = (mega.getY() / 2) * 2; // toa do y cua megacell hien tai
		int x_cell = mega.getX(); // toa do x cua cell hien tai
		int y_cell = mega.getY(); // toa do y cua cell hien tai
		if (y + 2 < size) {
			if (listCellStatus.get(x + (y + 2) * size) == true) { // kiem tra
																	// xem da
				// tung duyet
				// qua chua
				// kiem tra xem co bi chan duong di xuong duoi hay khong???
				if ((listCells.get(x + (y + 2) * size).getStatus() == -1
						&& listCells.get(x + 1 + (y + 2) * size).getStatus() == -1)
						|| (listCells.get(x + (y + 1) * size).getStatus() == -1
								&& listCells.get(x + 1 + (y + 2) * size).getStatus() == -1)
						|| (listCells.get(x + (y + 2) * size).getStatus() == -1
								&& listCells.get(x + 1 + (y + 1) * size).getStatus() == -1)) {
					return false;
				}

				if (y_cell == (y_cell / 2) * 2) { // neu la o ben tren
					if (listCells.get(x + (y + 1) * size).getStatus() == -1
							&& listCells.get(x + 1 + (y + 1) * size).getStatus() == -1)
						return false;
					if (x_cell == (x_cell / 2) * 2) { // neu la o trai tren
						if (listCells.get(x + 1 + y * size).getStatus() == -1
								&& listCells.get(x + (y + 1) * size).getStatus() == -1)
							return false;
					} else { // neu la o phai tren
						if (listCells.get(x + y * size).getStatus() == -1
								&& listCells.get(x + 1 + (y + 1) * size).getStatus() == -1)
							return false;
					}
				}
				return true;
			} else {// da tung duyet r
				if ((listCells.get(x + (y + 2) * size).getStatus() == -1
						&& listCells.get(x + 1 + (y + 3) * size).getStatus() == -1
						&& listCells.get(x + 1 + (y + 2) * size).getStatus() == 0)
						|| (listCells.get(x + (y + 3) * size).getStatus() == -1
								&& listCells.get(x + 1 + (y + 2) * size).getStatus() == -1
								&& listCells.get(x + (y + 2) * size).getStatus() == 0)) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public Boolean isNewRight(MegaCell mega) {
		int x = (mega.getX() / 2) * 2; // toa do x cua megacell hien tai
		int y = (mega.getY() / 2) * 2; // toa do y cua megacell hien tai
		int x_cell = mega.getX(); // toa do x cua cell hien tai
		int y_cell = mega.getY(); // toa do y cua cell hien tai

		if (x + 2 < size) {
			if (listCellStatus.get(x + 2 + y * size) == true) { // kiem tra xem
																// da
				// tung
				// duyet qua chua
				// kiem tra xem co bi chan duong di sang phai hay khong???
				if ((listCells.get(x + 2 + y * size).getStatus() == -1
						&& listCells.get(x + 2 + (y + 1) * size).getStatus() == -1)
						|| (listCells.get(x + 1 + y * size).getStatus() == -1
								&& listCells.get(x + 2 + (y + 1) * size).getStatus() == -1)
						|| (listCells.get(x + 1 + (y + 1) * size).getStatus() == -1
								&& listCells.get(x + 2 + y * size).getStatus() == -1)) {
					return false;
				}

				if (x_cell == (x_cell / 2) * 2) { // neu la o ben trai
					if (listCells.get(x + 1 + y * size).getStatus() == -1
							&& listCells.get(x + 1 + (y + 1) * size).getStatus() == -1)
						return false;
					if (y_cell == (y_cell / 2) * 2) { // neu la o trai tren
						if (listCells.get(x + 1 + y * size).getStatus() == -1
								&& listCells.get(x + (y + 1) * size).getStatus() == -1)
							return false;
					} else { // neu la o trai duoi
						if (listCells.get(x + y * size).getStatus() == -1
								&& listCells.get(x + 1 + (y + 1) * size).getStatus() == -1)
							return false;
					}
				}
				return true;
			} else {// da tung duyet r
				if ((listCells.get(x + 2 + y * size).getStatus() == -1
						&& listCells.get(x + 3 + (y + 1) * size).getStatus() == -1
						&& listCells.get(x + 2 + (y + 1) * size).getStatus() == 0)
						|| (listCells.get(x + 2 + (y + 1) * size).getStatus() == -1
								&& listCells.get(x + 3 + y * size).getStatus() == -1
								&& listCells.get(x + 2 + y * size).getStatus() == 0)) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public void robotWork_full(int x, int y) {
		if (isRobotHere(x, y)) {
			return;
		}

		if (moveTrend == 1) {
			moveUP_full();
			System.out.println("Len Tren");
			return;
		} else if (moveTrend == 2) {
			moveLEFT_full();
			System.out.println("Sang trai");
			return;
		} else if (moveTrend == 3) {
			moveDOWN_full();
			System.out.println("Xuong duoi");
			return;
		} else if (moveTrend == 4) {
			moveRIGHT_full();
			System.out.println("Sang phai");
			return;
		}
	}

	private void moveUP_full() {
		if (workFirst == 0) {
			workingAndChangeStatus(x, y);
			workFirst = 1;
			return;
		}

		// trai duoi
		if (x == (x / 2) * 2 && y > (y / 2) * 2) {
			// neu trc do la megacell duoi
			if (yPrev / 2 != y / 2) {
				// neu up la trai tren thi ket thuc hoac phai duoi thi ket thuc
				if ((current.getX() == x && current.getY() == y - 1)
						|| (current.getX() == x + 1 && current.getY() == y)) {
					xPrev = x;
					yPrev = y;
					workFirst = 0;
					moveFlag = 0;
					return;
				} else { // neu up ko la trai tren thi len tren hoac phai duoi
					xPrev = x;
					yPrev = y;
					y--;
					workingAndChangeStatus(x, y);
					return;
				}
			} else { // neu truoc do ko la megacell duoi
						// neu truoc do la trai tren
				if (xPrev == x && yPrev == y - 1) {
					// neu phai duoi khong co vat can thi sang phai
					if (listCells.get(x + 1 + y * size).getStatus() != -1) {
						xPrev = x;
						yPrev = y;
						x++;
						workingAndChangeStatus(x, y);
						return;
					} else {// neu phai duoi co vat can thi sang len tren
						xPrev = x;
						yPrev = y;
						y--;
						workingAndChangeStatus(x, y);
						return;
					}
				} else { // truoc do ko la trai tren
							// neu trc do la phai duoi thi len tren
					if (xPrev == x + 1 && yPrev == y) {
						xPrev = x;
						yPrev = y;
						y--;
						workingAndChangeStatus(x, y);
						return;
					} else { // truoc do khong la phai duoi cung khong la trai
								// tren
								// neu phai duoi ko co vat can thi sang phai
						if (listCells.get(x + 1 + y * size).getStatus() != -1) {
							xPrev = x;
							yPrev = y;
							x++;
							workingAndChangeStatus(x, y);
							return;
						} else { // neu phai duoi co vat can thi len tren
							xPrev = x;
							yPrev = y;
							y--;
							workingAndChangeStatus(x, y);
							return;
						}
					}
				}
			}

		}

		// phai duoi
		if (x > (x / 2) * 2 && y > (y / 2) * 2) {
			// neu phai tren ko co vat can thi len tren
			if (listCells.get(x + (y - 1) * size).getStatus() != -1) {
				xPrev = x;
				yPrev = y;
				y--;
				workingAndChangeStatus(x, y);
				return;
			} else { // neu phai tren co vat can thi sang trai
				xPrev = x;
				yPrev = y;
				x--;
				workingAndChangeStatus(x, y);
				return;
			}
		}

		// trai tren
		if (x < ((x / 2) * 2 + 1) && y < ((y / 2) * 2 + 1)) {
			// neu o up la phai tren thi ket thuc
			if (current.getX() == x + 1 && current.getY() == y) {
				xPrev = x;
				yPrev = y;
				workFirst = 0;
				moveFlag = 0;
				return;
			} else {// neu o up ko la phai tren
					// neu trc do la trai duoi
				if (xPrev == x && yPrev == y + 1) {
					// neu phai tren ko co vat can
					if (listCells.get(x + 1 + y * size).getStatus() != -1) {
						// sang phai
						xPrev = x;
						yPrev = y;
						x++;
						workingAndChangeStatus(x, y);
						return;
					} else { // phai tren co vat can
								// neu o up khong co vat can thi len tren
						if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
							xPrev = x;
							yPrev = y;
							y--;
							workingAndChangeStatus(x, y);
							return;
						} else {
							// neu o up co vat can
							// neu o cheo voi o up co vat can thi dung lai va
							// chuyen up sang o ben trai
							if (listCells.get(x + (y - 2) * size).getStatus() == -1) {
								current.setX(current.getX() - 1);
								xPrev = x;
								yPrev = y;
								workFirst = 0;
								moveFlag = 0;
								return;
							} else {// neu o cheo vs o up ko co vat can
									// neu o tren o up co vat can thi di len
									// tren va chuyen o up sang o cheo vs up
								if (listCells.get(x + 1 + (y - 2) * size).getStatus() == -1) {
									current.setX(current.getX() - 1);
									current.setY(current.getY() - 1);
									xPrev = x;
									yPrev = y;
									y--;
									workingAndChangeStatus(x, y);
									return;
								} else {// neu o tren o up k co vat can thi len
										// tren va chuyen up len tren
									current.setY(current.getY() - 1);
									xPrev = x;
									yPrev = y;
									y--;
									workingAndChangeStatus(x, y);
									return;
								}
							}
						}
					}

				} else {// neu truoc do khong la trai duoi
					// neu truoc do la phai tren
					if (xPrev == x + 1 && yPrev == y) {
						// neu o up ko co vat can thi sang phai
						if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
							xPrev = x;
							yPrev = y;
							x++;
							workingAndChangeStatus(x, y);
							return;
						} else {// neu o up co vat can
								// neu o cheo voi o up co vat can thi dung lai
								// va
								// chuyen up sang o ben trai va ket thuc
							if (listCells.get(x + (y - 2) * size).getStatus() == -1) {
								current.setX(current.getX() - 1);
								xPrev = x;
								yPrev = y;
								workFirst = 0;
								moveFlag = 0;
								return;
							} else {// neu o cheo vs o up ko co vat can
									// neu o tren o up co vat can thi di len
									// tren va chuyen o up sang o cheo vs up
								if (listCells.get(x + 1 + (y - 2) * size).getStatus() == -1) {
									current.setX(current.getX() - 1);
									current.setY(current.getY() - 1);
									xPrev = x;
									yPrev = y;
									y--;
									workingAndChangeStatus(x, y);
									return;
								} else {// neu o tren o up k co vat can thi len
										// tren va chuyen up len tren
									current.setY(current.getY() - 1);

									xPrev = x;
									yPrev = y;
									y--;
									workingAndChangeStatus(x, y);
									return;
								}
							}
						}

					} else {// truoc do ko la phai tren cung k la trai duoi
							// neu trai duoi ko co vat can thi xuong duoi
						if (listCells.get(x + (y + 1) * size).getStatus() != -1) {
							xPrev = x;
							yPrev = y;
							y++;
							workingAndChangeStatus(x, y);
							return;
						} else {// neu trai duoi co vat can
								// neu phai tren ko co vat can thi sang phai
							if (listCells.get(x + 1 + y * size).getStatus() != -1) {
								xPrev = x;
								yPrev = y;
								x++;
								workingAndChangeStatus(x, y);
								return;
							} else { // neu phai tren co vat can
										// neu o up khong co vat can thi len
										// tren
								if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
									xPrev = x;
									yPrev = y;
									y--;
									workingAndChangeStatus(x, y);
									return;
								} else {
									// neu o up co vat can
									// neu o cheo voi o up co vat can thi dung
									// lai
									// va
									// chuyen up sang o ben trai
									if (listCells.get(x + (y - 2) * size).getStatus() == -1) {
										current.setX(current.getX() - 1);
										xPrev = x;
										yPrev = y;
										workFirst = 0;
										moveFlag = 0;
										return;
									} else {// neu o cheo vs o up ko co vat can
											// neu o tren o up co vat can thi di
											// len
											// tren va chuyen o up sang o cheo
											// vs up
										if (listCells.get(x + 1 + (y - 2) * size).getStatus() == -1) {
											current.setX(current.getX() - 1);
											current.setY(current.getY() - 1);
											xPrev = x;
											yPrev = y;
											y--;
											workingAndChangeStatus(x, y);
											return;
										} else {// neu o tren o up k co vat can
												// thi
												// len
												// tren va chuyen up len tren
											current.setY(current.getY() - 1);

											xPrev = x;
											yPrev = y;
											y--;
											workingAndChangeStatus(x, y);
											return;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		// phai tren
		if (x > (x / 2) * 2 && y == (y / 2) * 2) {
			// neu truoc do la trai tren
			if (xPrev == x - 1 && yPrev == y) {
				// neu duoi phai khong co vat can thi xuong duoi
				if (listCells.get(x + (y + 1) * size).getStatus() != -1) {
					xPrev = x;
					yPrev = y;
					y++;
					workingAndChangeStatus(x, y);
					return;
				} else {// neu phai duoi co vat can
						// neu o up ko co vat can thi ket thuc
					if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
						xPrev = x;
						yPrev = y;
						workFirst = 0;
						moveFlag = 0;
						return;
					} else { // neu o up co vat can thi sang trai
						xPrev = x;
						yPrev = y;
						x--;
						workingAndChangeStatus(x, y);
						return;
					}
				}
			} else { // truoc do ko la trai tren
						// neu o up ko co vat can thi ket thuc
				if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
					xPrev = x;
					yPrev = y;
					workFirst = 0;
					moveFlag = 0;
					return;
				} else { // neu o up co vat can thi sang trai
					xPrev = x;
					yPrev = y;
					x--;
					workingAndChangeStatus(x, y);
					return;
				}
			}
		}
	}

	private void moveLEFT_full() {
		if (workFirst == 0) {
			workingAndChangeStatus(x, y);
			workFirst = 1;
			return;
		}

		// phai tren
		if (x > (x / 2) * 2 && y == (y / 2) * 2) {
			if (listCells.get(x - 1 + y * size).getStatus() != -1) {
				// neu trai tren ko co vat can thi di sang trai
				xPrev = x;
				yPrev = y;
				x--;
				workingAndChangeStatus(x, y);
				return;
			} else { // neu co vat can thi xuong duoi
				xPrev = x;
				yPrev = y;
				y++;
				workingAndChangeStatus(x, y);
				return;
			}
		}

		// phai duoi
		if (x > (x / 2) * 2 && y > (y / 2) * 2) {
			// neu truoc do la megacell ben phai
			if (xPrev / 2 != x / 2) {
				// neu left o phai tren hoac trai duoi thi ket thuc
				if ((current.getX() == x && current.getY() == y - 1)
						|| (current.getX() == x - 1 && current.getY() == y)) {
					xPrev = x;
					yPrev = y;
					workFirst = 0;
					moveFlag = 0;
					return;
				} else { // neu left ko la phai tren hoac trai duoi thi sang
							// trai
					xPrev = x;
					yPrev = y;
					x--;
					workingAndChangeStatus(x, y);
					return;
				}
			} else { // neu truoc do ko la megacell khac
						// neu truoc do la trai duoi
				if (xPrev == x - 1 && yPrev == y) {
					// neu phai tren ko co vat can thi len tren
					if (listCells.get(x + (y - 1) * size).getStatus() != -1) {
						xPrev = x;
						yPrev = y;
						y--;
						workingAndChangeStatus(x, y);
						return;
					} else {// neu phai tren co vat can thi sang trai
						xPrev = x;
						yPrev = y;
						x--;
						workingAndChangeStatus(x, y);
						return;
					}
				} else {// neu truoc do ko la trai duoi
						// neu truoc do la phai tren thi sang trai
					if (xPrev == x && yPrev == y - 1) {
						xPrev = x;
						yPrev = y;
						x--;
						workingAndChangeStatus(x, y);
						return;
					} else { // truoc do khong la phai tren, ko la trai duoi
								// neu phai tren ko co vat can thi len tren
						if (listCells.get(x + (y - 1) * size).getStatus() != -1) {
							xPrev = x;
							yPrev = y;
							y--;
							workingAndChangeStatus(x, y);
							return;
						} else {// neu phai tren co vat can thi sang trai
							xPrev = x;
							yPrev = y;
							x--;
							workingAndChangeStatus(x, y);
							return;
						}
					}
				}
			}
		}

		// trai tren
		if (x < ((x / 2) * 2 + 1) && y < ((y / 2) * 2 + 1)) {
			// neu truoc do la trai duoi
			if (xPrev == x && yPrev == y + 1) {
				// neu phai tren ko co vat can thi sang phai
				if (listCells.get(x + 1 + y * size).getStatus() != -1) {
					xPrev = x;
					yPrev = y;
					x++;
					workingAndChangeStatus(x, y);
					return;
				} else { // neu phai tren co vat can
							// neu o left ko co vat can thi ket thuc
					if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
						xPrev = x;
						yPrev = y;
						workFirst = 0;
						moveFlag = 0;
						return;
					} else {// neu o left co vat can thi xuong duoi
						xPrev = x;
						yPrev = y;
						y++;
						workingAndChangeStatus(x, y);
						return;
					}
				}
			} else {// truoc do khong la trai duoi
					// neu o left ko co vat can thi ket thuc
				if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
					xPrev = x;
					yPrev = y;
					workFirst = 0;
					moveFlag = 0;
					return;
				} else {// neu o left co vat can thi xuong duoi
					xPrev = x;
					yPrev = y;
					y++;
					workingAndChangeStatus(x, y);
					return;
				}
			}
		}

		// trai duoi
		if (x == (x / 2) * 2 && y > (y / 2) * 2) {
			// neu o left la trai tren thi ket thuc
			if (current.getX() == x && current.getY() == y - 1) {
				xPrev = x;
				yPrev = y;
				workFirst = 0;
				moveFlag = 0;
				return;
			} else {// neu o left ko la trai tren
					// neu truoc do la phai duoi
				if (xPrev == x + 1 && yPrev == y) {
					// neu trai tren ko co vat can thi len tren
					if (listCells.get(x + (y - 1) * size).getStatus() != -1) {
						xPrev = x;
						yPrev = y;
						y--;
						workingAndChangeStatus(x, y);
						return;
					} else {// neu trai tren co vat can
							// neu o left ko co vat can thi sang trai
						if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
							xPrev = x;
							yPrev = y;
							x--;
							workingAndChangeStatus(x, y);
							return;
						} else {// neu o left co vat can
							// neu o cheo voi o left co vat can thi chuyen sang
							// o ben duoi va dung lai
							if (listCells.get(x - 2 + y * size).getStatus() == -1) {
								current.setY(current.getY() + 1);
								xPrev = x;
								yPrev = y;
								workFirst = 0;
								moveFlag = 0;
								return;
							} else { // neu o cheo ko co vat can
										// neu o ben trai co vat can thi chuyen
										// sang o cheo va sang trai
								if (listCells.get(x - 2 + (y - 1) * size).getStatus() == -1) {
									current.setX(current.getX() - 1);
									current.setY(current.getY() + 1);
									xPrev = x;
									yPrev = y;
									x--;
									workingAndChangeStatus(x, y);
									return;
								} else {// neu o ben trai k co vat can thi
										// chuyen sang o ben trai va sang trai
									current.setX(current.getX() - 1);
									xPrev = x;
									yPrev = y;
									x--;
									workingAndChangeStatus(x, y);
									return;
								}
							}
						}
					}
				} else { // neu truoc do khong la phai duoi
							// neu truoc do la trai tren
					if (xPrev == x && yPrev == y - 1) {
						// neu left ko co vat can thi len tren
						if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
							xPrev = x;
							yPrev = y;
							y--;
							workingAndChangeStatus(x, y);
							return;
						} else {// neu left co vat can
								// neu o cheo voi o left co vat can thi chuyen
								// sang
								// o ben duoi va dung lai
							if (listCells.get(x - 2 + y * size).getStatus() == -1) {
								current.setY(current.getY() + 1);
								xPrev = x;
								yPrev = y;
								workFirst = 0;
								moveFlag = 0;
								return;
							} else { // neu o cheo ko co vat can
										// neu o ben trai co vat can thi chuyen
										// sang
										// o cheo va sang trai
								if (listCells.get(x - 2 + (y - 1) * size).getStatus() == -1) {
									current.setX(current.getX() - 1);
									current.setY(current.getY() + 1);
									xPrev = x;
									yPrev = y;
									x--;
									workingAndChangeStatus(x, y);
									return;
								} else {// neu o ben trai k co vat can thi
										// chuyen
										// sang o ben trai va sang trai
									current.setX(current.getX() - 1);
									xPrev = x;
									yPrev = y;
									x--;
									workingAndChangeStatus(x, y);
									return;
								}
							}
						}
					} else {// neu khong la trai tren, ko la phai duoi
							// neu phai duoi ko co vat can thi sang phai
						if (listCells.get(x + 1 + y * size).getStatus() != -1) {
							xPrev = x;
							yPrev = y;
							x++;
							workingAndChangeStatus(x, y);
							return;
						} else {// phai duoi co vat can
								// neu trai tren ko co vat can thi len tren
							if (listCells.get(x + (y - 1) * size).getStatus() != -1) {
								xPrev = x;
								yPrev = y;
								y--;
								workingAndChangeStatus(x, y);
								return;
							} else {// neu trai tren co vat can
									// neu o left ko co vat can thi sang trai
								if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
									xPrev = x;
									yPrev = y;
									x--;
									workingAndChangeStatus(x, y);
									return;
								} else {// neu o left co vat can
									// neu o cheo voi o left co vat can thi
									// chuyen sang
									// o ben duoi va dung lai
									if (listCells.get(x - 2 + y * size).getStatus() == -1) {
										current.setY(current.getY() + 1);
										xPrev = x;
										yPrev = y;
										workFirst = 0;
										moveFlag = 0;
										return;
									} else { // neu o cheo ko co vat can
												// neu o ben trai co vat can thi
												// chuyen sang o cheo va sang
												// trai
										if (listCells.get(x - 2 + (y - 1) * size).getStatus() == -1) {
											current.setX(current.getX() - 1);
											current.setY(current.getY() + 1);
											xPrev = x;
											yPrev = y;
											x--;
											workingAndChangeStatus(x, y);
											return;
										} else {// neu o ben trai k co vat can
												// thi chuyen sang o ben trai va
												// sang trai
											current.setX(current.getX() - 1);
											xPrev = x;
											yPrev = y;
											x--;
											workingAndChangeStatus(x, y);
											return;
										}
									}
								}
							}
						}
					}
				}
			}

		}
	}

	private void moveDOWN_full() {
		if (workFirst == 0) {
			workingAndChangeStatus(x, y);
			workFirst = 1;
			return;
		}

		// phai tren
		if (x > (x / 2) * 2 && y == (y / 2) * 2) {
			// neu trc do la megacell tren
			if ((yPrev / 2) != (y / 2)) {
				// neu left la trai tren or phai duoi thi ket thuc
				if ((current.getX() == x - 1 && current.getY() == y)
						|| (current.getX() == x && current.getY() == y + 1)) {
					xPrev = x;
					yPrev = y;
					workFirst = 0;
					moveFlag = 0;
					return;
				} else {// neu left ko la trai tren or phai duoi thi xuong duoi
					xPrev = x;
					yPrev = y;
					y++;
					workingAndChangeStatus(x, y);
					return;
				}
			} else {// neu truoc do ko la megacell khac
					// neu truoc do la phai duoi
				if (xPrev == x && yPrev == y + 1) {
					// neu trai tren ko co vat can thi sang trai
					if (listCells.get(x - 1 + y * size).getStatus() != -1) {
						xPrev = x;
						yPrev = y;
						x--;
						workingAndChangeStatus(x, y);
						return;
					} else {// neu trai tren co vat can thi xuong duoi
						xPrev = x;
						yPrev = y;
						y++;
						workingAndChangeStatus(x, y);
						return;
					}
				} else {// neu truoc do khong la phai duoi
						// neu truoc do la trai tren thi di xuong
					if (xPrev == x - 1 && yPrev == y) {
						xPrev = x;
						yPrev = y;
						y++;
						workingAndChangeStatus(x, y);
						return;
					} else {// neu truoc do khong la trai tren, khong la phai
							// duoi
							// neu trai tren ko co vat can thi sang trai
						if (listCells.get(x - 1 + y * size).getStatus() != -1) {
							xPrev = x;
							yPrev = y;
							x--;
							workingAndChangeStatus(x, y);
							return;
						} else {// neu trai tren co vat can thi xuong duoi
							xPrev = x;
							yPrev = y;
							y++;
							workingAndChangeStatus(x, y);
							return;
						}
					}
				}
			}

		}

		// trai tren
		if (x < ((x / 2) * 2 + 1) && y < ((y / 2) * 2 + 1)) {
			if (listCells.get(x + (y + 1) * size).getStatus() != -1) {
				// neu trai duoi ko co vat can thi di xuong
				xPrev = x;
				yPrev = y;
				y++;
				workingAndChangeStatus(x, y);
				return;
			} else {
				// neu trai duoi co vat can thi sang phai
				xPrev = x;
				yPrev = y;
				x++;
				workingAndChangeStatus(x, y);
				return;
			}
		}

		// phai duoi
		if (x > (x / 2) * 2 && y > (y / 2) * 2) {
			// neu down o trai duoi thi ket thuc
			if (current.getX() == x - 1 && current.getY() == y) {
				xPrev = x;
				yPrev = y;
				workFirst = 0;
				moveFlag = 0;
				return;
			} else {// neu down k la trai duoi
					// neu truoc do la phai tren
				if (xPrev == x && yPrev == y - 1) {
					// neu trai duoi ko co vat can thi sang trai
					if (listCells.get(x - 1 + y * size).getStatus() != -1) {
						xPrev = x;
						yPrev = y;
						x--;
						workingAndChangeStatus(x, y);
						return;
					} else {// neu trai duoi co vat can
							// neu o down ko co vat can thi xuong duoi
						if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
							xPrev = x;
							yPrev = y;
							y++;
							workingAndChangeStatus(x, y);
							return;
						} else {// neu o down co vat can
							// neu cheo down co vat can thi chuyen down sang
							// phai va ket thuc
							if (listCells.get(x + (y + 2) * size).getStatus() == -1) {
								current.setX(current.getX() + 1);
								xPrev = x;
								yPrev = y;
								workFirst = 0;
								moveFlag = 0;
								return;
							} else {// neu o cheo down k co vat can
									// neu duoi down co vat can thi down chuyen
									// sang o cheo va xuong duoi
								if (listCells.get(x - 1 + (y + 2) * size).getStatus() == -1) {
									current.setX(current.getX() + 1);
									current.setY(current.getY() + 1);
									xPrev = x;
									yPrev = y;
									y++;
									workingAndChangeStatus(x, y);
									return;
								} else {// neu duoi down k co vat can thi chuyen
										// down xuong duoi va xuong duoi
									current.setY(current.getY() + 1);
									xPrev = x;
									yPrev = y;
									y++;
									workingAndChangeStatus(x, y);
									return;
								}
							}
						}
					}
				} else {// neu truoc do k la phai tren
						// neu truoc do la trai duoi
					if (xPrev == x - 1 && yPrev == y) {
						// neu o down ko co vat can thi sang trai
						if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
							xPrev = x;
							yPrev = y;
							x--;
							workingAndChangeStatus(x, y);
							return;
						} else { // neu o down co vat can
									// neu cheo down co vat can thi chuyen down
									// sang
									// phai va ket thuc
							if (listCells.get(x + (y + 2) * size).getStatus() == -1) {
								current.setX(current.getX() + 1);
								xPrev = x;
								yPrev = y;
								workFirst = 0;
								moveFlag = 0;
								return;
							} else {// neu o cheo down k co vat can
									// neu duoi down co vat can thi down chuyen
									// sang o cheo va xuong duoi
								if (listCells.get(x - 1 + (y + 2) * size).getStatus() == -1) {
									current.setX(current.getX() + 1);
									current.setY(current.getY() + 1);
									xPrev = x;
									yPrev = y;
									y++;
									workingAndChangeStatus(x, y);
									return;
								} else {// neu duoi down k co vat can thi chuyen
										// down xuong duoi va xuong duoi
									current.setY(current.getY() + 1);
									xPrev = x;
									yPrev = y;
									y++;
									workingAndChangeStatus(x, y);
									return;
								}
							}
						}
					} else {// truoc do ko la trai duoi,ko la phai tren
							// neu phai tren ko co vat can thi len tren
						if (listCells.get(x + (y - 1) * size).getStatus() != -1) {
							xPrev = x;
							yPrev = y;
							y--;
							workingAndChangeStatus(x, y);
							return;
						} else { // neu phai tren co vat can
									// neu trai duoi ko co vat can thi sang trai
							if (listCells.get(x - 1 + y * size).getStatus() != -1) {
								xPrev = x;
								yPrev = y;
								x--;
								workingAndChangeStatus(x, y);
								return;
							} else {// neu trai duoi co vat can
									// neu o down ko co vat can thi xuong duoi
								if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
									xPrev = x;
									yPrev = y;
									y++;
									workingAndChangeStatus(x, y);
									return;
								} else {// neu o down co vat can
									// neu cheo down co vat can thi chuyen down
									// sang
									// phai va ket thuc
									if (listCells.get(x + (y + 2) * size).getStatus() == -1) {
										current.setX(current.getX() + 1);
										xPrev = x;
										yPrev = y;
										workFirst = 0;
										moveFlag = 0;
										return;
									} else {// neu o cheo down k co vat can
											// neu duoi down co vat can thi down
											// chuyen
											// sang o cheo va xuong duoi
										if (listCells.get(x - 1 + (y + 2) * size).getStatus() == -1) {
											current.setX(current.getX() + 1);
											current.setY(current.getY() + 1);
											xPrev = x;
											yPrev = y;
											y++;
											workingAndChangeStatus(x, y);
											return;
										} else {// neu duoi down k co vat can
												// thi chuyen
												// down xuong duoi va xuong duoi
											current.setY(current.getY() + 1);
											xPrev = x;
											yPrev = y;
											y++;
											workingAndChangeStatus(x, y);
											return;
										}
									}
								}
							}
						}
					}
				}
			}

		}

		// trai duoi
		if (x == (x / 2) * 2 && y > (y / 2) * 2) {
			// neu truoc do la phai duoi
			if (xPrev == x + 1 && yPrev == y) {
				// neu trai tren ko co vat can thi len tren
				if (listCells.get(x + (y - 1) * size).getStatus() != -1) {
					xPrev = x;
					yPrev = y;
					y--;
					workingAndChangeStatus(x, y);
					return;
				} else {// trai tren co vat can
						// neu o down ko co vat can thi ket thuc
					if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
						xPrev = x;
						yPrev = y;
						workFirst = 0;
						moveFlag = 0;
						return;
					} else {// neu o down co vat can thi sang phai
						xPrev = x;
						yPrev = y;
						x++;
						workingAndChangeStatus(x, y);
						return;
					}
				}
			} else { // neu truoc do khong la phai duoi
						// neu o down ko co vat can thi ket thuc
				if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
					xPrev = x;
					yPrev = y;
					workFirst = 0;
					moveFlag = 0;
					return;
				} else {// neu o down co vat can thi sang phai
					xPrev = x;
					yPrev = y;
					x++;
					workingAndChangeStatus(x, y);
					return;
				}
			}
		}
	}

	private void moveRIGHT_full() {
		if (workFirst == 0) {
			workingAndChangeStatus(x, y);
			workFirst = 1;
			return;
		}

		// phai tren
		if (x > (x / 2) * 2 && y == (y / 2) * 2) {
			// neu phai duoi la right thi ket thuc
			if (current.getX() == x && current.getY() == y + 1) {
				xPrev = x;
				yPrev = y;
				workFirst = 0;
				moveFlag = 0;
				return;
			} else { // neu phai duoi ko la right
						// neu truoc do la trai tren
				if (xPrev == x - 1 && yPrev == y) {
					// neu phai duoi ko co vat can thi xuong duoi
					if (listCells.get(x + (y + 1) * size).getStatus() != -1) {
						xPrev = x;
						yPrev = y;
						y++;
						workingAndChangeStatus(x, y);
						return;
					} else {// neu phai duoi co vat can
							// neu o right ko co vat can thi sang phai
						if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
							xPrev = x;
							yPrev = y;
							x++;
							workingAndChangeStatus(x, y);
							return;
						} else {// neu o right co vat can
							// neu o cheo right co vat can thi chuyen right len
							// tren va ket thuc
							if (listCells.get(x + 2 + y * size).getStatus() == -1) {
								current.setY(current.getY() - 1);
								xPrev = x;
								yPrev = y;
								workFirst = 0;
								moveFlag = 0;
								return;
							} else {// neu cheo phai ko co vat can
									// neu ben phai right co vat can thi chuyen
									// right len cheo tren va sang phai
								if (listCells.get(x + 2 + (y + 1) * size).getStatus() == -1) {
									current.setY(current.getY() - 1);
									current.setX(current.getX() + 1);
									xPrev = x;
									yPrev = y;
									x++;
									workingAndChangeStatus(x, y);
									return;
								} else {// neu phai right k co vat can thi
										// chuyen right sang phai r sang phai
									current.setX(current.getX() + 1);
									xPrev = x;
									yPrev = y;
									x++;
									workingAndChangeStatus(x, y);
									return;
								}
							}
						}
					}
				} else {// neu truoc do ko la trai tren
						// neu truoc do la phai duoi
					if (xPrev == x && yPrev == y + 1) {
						// neu right ko co vat can thi xuong duoi
						if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
							xPrev = x;
							yPrev = y;
							y++;
							workingAndChangeStatus(x, y);
							return;
						} else {// neu right co vat can
								// neu o cheo right co vat can thi chuyen right
								// len tren
								// va ket thuc
							if (listCells.get(x + 2 + y * size).getStatus() == -1) {
								current.setY(current.getY() - 1);
								xPrev = x;
								yPrev = y;
								workFirst = 0;
								moveFlag = 0;
								return;
							} else {// neu cheo phai ko co vat can
									// neu ben phai right co vat can thi chuyen
									// right len cheo tren va sang phai
								if (listCells.get(x + 2 + (y + 1) * size).getStatus() == -1) {
									current.setY(current.getY() - 1);
									current.setX(current.getX() + 1);
									xPrev = x;
									yPrev = y;
									x++;
									workingAndChangeStatus(x, y);
									return;
								} else {// neu phai right k co vat can thi
										// chuyen
										// right sang phai r sang phai
									current.setX(current.getX() + 1);
									xPrev = x;
									yPrev = y;
									x++;
									workingAndChangeStatus(x, y);
									return;
								}
							}
						}
					} else {// neu trc do ko la phai duoi,cung k la trai tren
							// neu trai tren ko co vat can thi sang trai
						if (listCells.get(x - 1 + y * size).getStatus() != -1) {
							xPrev = x;
							yPrev = y;
							x--;
							workingAndChangeStatus(x, y);
							return;
						} else {// neu trai tren co vat can
								// neu phai duoi ko co vat can thi xuong duoi
							if (listCells.get(x + (y + 1) * size).getStatus() != -1) {
								xPrev = x;
								yPrev = y;
								y++;
								workingAndChangeStatus(x, y);
								return;
							} else {// neu phai duoi co vat can
									// neu o right ko co vat can thi sang phai
								if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
									xPrev = x;
									yPrev = y;
									x++;
									workingAndChangeStatus(x, y);
									return;
								} else {// neu o right co vat can
									// neu o cheo right co vat can thi chuyen
									// right len tren va ket thuc
									if (listCells.get(x + 2 + y * size).getStatus() == -1) {
										current.setY(current.getY() - 1);
										xPrev = x;
										yPrev = y;
										workFirst = 0;
										moveFlag = 0;
										return;
									} else {// neu cheo phai ko co vat can
											// neu ben phai right co vat can thi
											// chuyen right len cheo tren va
											// sang phai
										if (listCells.get(x + 2 + (y + 1) * size).getStatus() == -1) {
											current.setY(current.getY() - 1);
											current.setX(current.getX() + 1);
											xPrev = x;
											yPrev = y;
											x++;
											workingAndChangeStatus(x, y);
											return;
										} else {// neu phai right k co vat can
												// thi chuyen right sang phai r
												// sang phai
											current.setX(current.getX() + 1);
											xPrev = x;
											yPrev = y;
											x++;
											workingAndChangeStatus(x, y);
											return;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		// phai duoi
		if (x > (x / 2) * 2 && y > (y / 2) * 2) {
			// neu truoc do la phai tren
			if (xPrev == x && yPrev == y - 1) {
				// neu trai duoi ko co vat can thi sang trai
				if (listCells.get(x - 1 + y * size).getStatus() != -1) {
					xPrev = x;
					yPrev = y;
					x--;
					workingAndChangeStatus(x, y);
					return;
				} else {// neu trai duoi co vat can
						// neu o right ko co vat can thi ket thuc
					if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
						xPrev = x;
						yPrev = y;
						workFirst = 0;
						moveFlag = 0;
						return;
					} else {// neu o right co vat can thi len tren
						xPrev = x;
						yPrev = y;
						y--;
						workingAndChangeStatus(x, y);
						return;
					}
				}
			} else {// neu truoc do ko la phai tren
					// neu o right ko co vat can thi ket thuc
				if (listCells.get(current.getX() + current.getY() * size).getStatus() != -1) {
					xPrev = x;
					yPrev = y;
					workFirst = 0;
					moveFlag = 0;
					return;
				} else {// neu o right co vat can thi len tren
					xPrev = x;
					yPrev = y;
					y--;
					workingAndChangeStatus(x, y);
					return;
				}
			}
		}

		// trai tren
		if (x < ((x / 2) * 2 + 1) && y < ((y / 2) * 2 + 1)) {
			// neu trc do la megacell trai
			if (xPrev / 2 != x / 2) {
				// neu trai duoi hoac phai tren la right thi ket thuc
				if ((current.getX() == x && current.getY() == y + 1)
						|| (current.getX() == x + 1 && current.getY() == y)) {
					xPrev = x;
					yPrev = y;
					workFirst = 0;
					moveFlag = 0;
					return;
				} else {// neu trai duoi hoac phai tren ko la right thi sang
						// phai
					xPrev = x;
					yPrev = y;
					x++;
					workingAndChangeStatus(x, y);
					return;
				}
			} else {
				// neu truoc do la trai duoi thi sang phai
				if (xPrev == x && yPrev == y + 1) {
					xPrev = x;
					yPrev = y;
					x++;
					workingAndChangeStatus(x, y);
					return;
				} else { // neu truoc do ko la trai duoi
							// neu trai duoi ko co vat can thi xuong duoi
					if (listCells.get(x + (y + 1) * size).getStatus() != -1) {
						xPrev = x;
						yPrev = y;
						y++;
						workingAndChangeStatus(x, y);
						return;
					} else {// trai duoi co vat can thi sang phai
						xPrev = x;
						yPrev = y;
						x++;
						workingAndChangeStatus(x, y);
						return;
					}
				}
			}
		}

		// trai duoi
		if (x == (x / 2) * 2 && y > (y / 2) * 2) {
			// neu phai duoi ko co vat can thi sang phai
			if (listCells.get(x + 1 + y * size).getStatus() != -1) {
				xPrev = x;
				yPrev = y;
				x++;
				workingAndChangeStatus(x, y);
				return;
			} else {// neu phai duoi co vat can thi len tren
				xPrev = x;
				yPrev = y;
				y--;
				workingAndChangeStatus(x, y);
				return;
			}
		}
	}

	private void setOldMegacell_full(MegaCell mega) {
		listCellStatus.set((mega.getX() / 2) * 2 + (mega.getY() / 2) * 2 * size, false);
	}

	public int[] getListOfDustValue() {
		return listOfDustValue;
	}

	public int getSumOfDust() {
		return sumOfDust;
	}

}
