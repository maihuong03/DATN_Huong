package newone;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ThaoTacFile {
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private PrintWriter printWriter;
	private Scanner scanner;

	// mở file

	public void openFileToWrite(String fileName) {
		try {
			fileWriter = new FileWriter(fileName, true); // true ở đây là có cho thêm vào file
			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openFileToClear(String fileName) {
		try {
			fileWriter = new FileWriter(fileName); // true ở đây là có cho thêm vào file
			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// đóng file
	public void closeFileAfterWrite(String fileName) {
		try {
			printWriter.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openFileToRead(String fileName) {
		try {
			scanner = new Scanner(Paths.get(fileName), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeFileAfterRead(String fileName) {
		try {
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// writeDataToFile và createDataFromFile

	// ghi data vào file *********
	public void writeTimeToFile(int index, int mode, int h, int m, int pm_am, String info, String link) {
		openFileToWrite(link);
		printWriter.println(index + ":" + mode + ":" + h + ":" + m + ":" + pm_am + ":-" + info);// định dạng nội dung
																								// ghi vào file
		closeFileAfterWrite(link);

	}

	// xóa dữ liệu trong file

	// đọc data từ file *********
//	 public ArrayList<TimeOb> readTimeFromFile(String fileName){
//		 openFileToRead(fileName);
//		 ArrayList<TimeOb>	times = new ArrayList<>();
//		 while(scanner.hasNextLine()) {
//			 String data = scanner.nextLine();
//			 TimeOb nv = createTimeFromData(data);
//			 times.add(nv);
//		 }
//		 closeFileAfterRead(fileName);
//		 return times; 
//	 }
	public ArrayList<String> readTimeFromFile(String link) throws FileNotFoundException {
		File fileX = new File(link);
		scanner = new Scanner(fileX);
		ArrayList<String> list = new ArrayList<>();
		while (scanner.hasNextLine()) {
			String w = scanner.nextLine();
			list.add(w);
		}
		return list;
	}

//	 public void readTimeFromFile(String link, JLabel labelX) throws IOException {
//		  File file = new File(link);
//	        InputStream inputStream = new FileInputStream(file);
//	        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//	        BufferedReader reader = new BufferedReader(inputStreamReader);
//	 
//	        String line = "";
//	        while((line = reader.readLine()) != null){
//	        	labelX.setText(line);
//	        }
//	 }
	// tạo đối tượng
	public TimeOb createTimeFromData(String data) {
		String[] datas = data.split("\\:");
		// 1:6:5:1 <=> nhiệm vụ 1: 6h5p AM
		TimeOb t = new TimeOb(Integer.parseInt(datas[0]),Integer.parseInt(datas[1]),
				Integer.parseInt(datas[2]),Integer.parseInt(datas[3]),Integer.parseInt(datas[4]), datas[5]);
		// TODO Auto-generated method stub
		return t;
	}

	// xóa nội dung trong file
	public void clearTimeFromFile(String link) {
		openFileToClear(link);
		printWriter.print("");
		closeFileAfterWrite(link);

	}

	
	// tạo data từ file
//	 public ThemLichLamViec createDataFromFile(String data) {
////	        String[] datas = data.split("\\:");
////	        ThemLichLamViec doituong = new ThemLichLamViec(Integer.parseInt(datas[0]), datas[1], datas[2], datas[3]);
////	        return doituong;
//	    }

}
