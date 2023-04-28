package cstt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.print.CancelablePrintJob;
import javax.security.auth.kerberos.KerberosTicket;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.util.IntList;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;

import gui.MainGui;
import gui.StatusBar;

public class suyDien2 {

	public static ArrayList<Rule> dssk = new ArrayList<>();
	public static ArrayList<Rule> dsskSub1 = new ArrayList<>();
	public static ArrayList<Rule> dsskSub2 = new ArrayList<>();
	public static ArrayList<Rule> dsskSub3 = new ArrayList<>();
	public static ArrayList<Rule> dsskSub4 = new ArrayList<>();
	public static ArrayList<Rule> dsskSub5 = new ArrayList<>();
	public static ArrayList<Rule> dsskSub6 = new ArrayList<>();
	public static ArrayList<Rule> saveResultArrayList = new ArrayList<>(); // lưu tập luật

	public static String linkString = "tapluat2.dat";
	
	public static String[] orderStrings = MainGui.getOrderArray();
	public static String resultString = "";
	public static Random rd = new Random();


	public static void docDuLieu(String fileName) throws IOException, FileNotFoundException, ClassNotFoundException {
		dssk = Rule.docsk(fileName);
	}

	public static void timLuat(ArrayList<Rule> array, String[] ordesStrings) {
		System.out.println("bat dau tim luat");
		int numOfCoffee = MainGui.getNumOfCoffe();
		int numOfWater = MainGui.getNumOfWater();
		int numOfSoda = MainGui.getNumOfSoda();
		int orderStringsLength = orderStrings.length - 1; // trừ 1 vì phàna tử cuối cùng của mảng là lưu tuỳ chọn\
		int dsskLength = array.size();
		ArrayList<Rule> luatDuocTimThay;
		dsskSub1.clear();
		dsskSub2.clear();
		dsskSub3.clear();
		dsskSub4.clear();
		dsskSub5.clear();
		dsskSub6.clear();
		resultString = "||";

		if (numOfCoffee > 0) {
			int index = rd.nextInt(8); // 0-7
			resultString += ", " + dssk.get(index).getTHEN();
		}
		if (numOfWater > 0) {
			int index = rd.nextInt(3) + 8;
			resultString += ", " + dssk.get(index).getTHEN();
		}
		if (numOfSoda > 0) {
			int index = rd.nextInt(8) + 12;
			resultString += ", " + dssk.get(index).getTHEN();
		}

		for (int i = 0; i < orderStringsLength; i++) {
			for (int j = 19; j < dsskLength; j++) {
				if (orderStrings[i].equals(array.get(j).getCodeIf()[0])) {
					// tìm tháy luật
					if (i == 0) {
						dsskSub1.add(array.get(j));
					} else if (i == 1) {
						dsskSub2.add(array.get(j));
					} else if (i == 2) {
						dsskSub3.add(array.get(j));
					} else if (i == 3) {
						dsskSub4.add(array.get(j));
					} else if (i == 4) {
						dsskSub5.add(array.get(j));
					} else if (i == 5) {
						dsskSub6.add(array.get(j));
					}
				}
			}
		}

		for (int i = 0; i < 6; i++) {
			if (i == 0) {
				Boolean isAdd = rd.nextBoolean();
				int length = dsskSub1.size();
				if (isAdd && length > 0) {
					int index = 0;
					if (length > 1) {
						index = randInt(0, length - 1);
					}
					resultString += ", " + dsskSub1.get(index).getTHEN();
					saveResultArrayList.add(dsskSub1.get(index));
				}
			} else if (i == 1) {
				Boolean isAdd = rd.nextBoolean();
				int length = dsskSub2.size();
				if (isAdd && length > 0) {
					int index = 0;
					if (length > 1) {
						index = randInt(0, length - 1);
					}
					resultString += ", " + dsskSub2.get(index).getTHEN();
					saveResultArrayList.add(dsskSub2.get(index));
				}
			} else if (i == 2) {
				Boolean isAdd = rd.nextBoolean();
				int length = dsskSub3.size();
				if (isAdd && length > 0) {
					int index = 0;
					if (length > 1) {
						index = randInt(0, length - 1);
					}
					resultString += ", " + dsskSub3.get(index).getTHEN();
					saveResultArrayList.add(dsskSub3.get(index));
				}
			} else if (i == 3) {
				Boolean isAdd = rd.nextBoolean();
				int length = dsskSub4.size();
				if (isAdd && length > 0) {
					int index = 0;
					if (length > 1) {
						index = randInt(0, length - 1);
					}
					resultString += ", " + dsskSub4.get(index).getTHEN();
					saveResultArrayList.add(dsskSub4.get(index));
				}
			} else if (i == 4) {
				Boolean isAdd = rd.nextBoolean();
				int length = dsskSub5.size();
				if (isAdd && length > 0) {
					int index = 0;
					if (length > 1) {
						index = randInt(0, length - 1);
					}
					resultString += ", " + dsskSub5.get(index).getTHEN();
					saveResultArrayList.add(dsskSub5.get(index));
				}
			} else if (i == 5) {
				Boolean isAdd = rd.nextBoolean();
				int length = dsskSub6.size();
				if (isAdd && length > 0) {
					int index = 0;
					if (length > 1) {
						index = randInt(0, length - 1);
					}
					resultString += ", " + dsskSub6.get(index).getTHEN();
					saveResultArrayList.add(dsskSub6.get(index));
				}
			}
		}

		resultString += ", Yêu cầu khác: " + orderStrings[orderStringsLength];
		MainGui.setOrderString(resultString);
		System.out.println("ket qua: " + resultString);
		MainGui.changeStatusBar(resultString);
		MainGui.addRule(resultString);
	}

	public static void startX() {
		try {
			docDuLieu(linkString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timLuat(dssk, orderStrings);
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt(max) + min;
		return randomNum;
	}

}
