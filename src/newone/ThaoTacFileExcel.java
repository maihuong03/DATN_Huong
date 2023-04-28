package newone;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import gui.MainGui;


public class ThaoTacFileExcel {

	public ThaoTacFileExcel(String link, DataExcel newOne, DataExcel newOne2) throws IOException{
		System.out.println("thao tac voi file excel");
		List<DataExcel> listData = getListDataExcel(link, newOne);
		List<DataExcel> listData2 = getListDataExcel2(link, newOne2);
		try {
			writeExcel(listData, listData2, link);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Workbook getWorkBook(String excelFilePath) throws IOException {
		Workbook workbook;
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook();
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}
		return workbook;
	}

	public static void writeExcel(List<DataExcel> listDataExcel, List<DataExcel> listDataExcel2, String excelFilePath) throws IOException {
		Workbook workbook = getWorkBook(excelFilePath);
		Sheet sheet = workbook.createSheet();
		createHeaderRow(sheet);
		int rowCount = 0;
		
		for (DataExcel dataExcel : listDataExcel) {
			Row row = sheet.createRow(++rowCount);
			writeDataExcel(dataExcel, row);
		}

		Sheet sheet2 = workbook.createSheet();
		createHeaderRow2(sheet2);
		int rowCount2 = 0;
		
		for (DataExcel dataExcel2 : listDataExcel2) {
			Row row = sheet2.createRow(++rowCount2);
			writeDataExcel2(dataExcel2, row);
		}

		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		}
	}


	public static void writeDataExcel2(DataExcel dataExcel, Row row) {
		Cell cell = row.createCell(0);
		System.out.println(" getpoint: " + dataExcel.getPoint());
		cell.setCellValue(dataExcel.getPoint());
		
		cell = row.createCell(1);
		System.out.println("getIm: " +dataExcel.getImpressive());
		cell.setCellValue(dataExcel.getImpressive());

		cell = row.createCell(2);
		System.out.println("getHappy: "+ dataExcel.getHappy());
		cell.setCellValue(dataExcel.getHappy());

		cell = row.createCell(3);
		System.out.println("getNor: " + dataExcel.getNormal());
		cell.setCellValue(dataExcel.getNormal());

		cell = row.createCell(4);
		System.out.println("getBad: " + dataExcel.getBad());
		cell.setCellValue(dataExcel.getBad());

		cell = row.createCell(5);
		System.out.println("getAngry: " + dataExcel.getAngry());
		cell.setCellValue(dataExcel.getAngry());

		cell = row.createCell(6);
		cell.setCellValue(dataExcel.getRule());
	}
	
	public static void writeDataExcel(DataExcel dataExcel, Row row) {
		Cell cell = row.createCell(0);
		cell.setCellValue(dataExcel.getSize());
		
		cell = row.createCell(1);
		cell.setCellValue(dataExcel.getTime());

		cell = row.createCell(2);
		cell.setCellValue(dataExcel.getStep());

		cell = row.createCell(3);
		cell.setCellValue(dataExcel.getReapeatStep());

		cell = row.createCell(4);
		cell.setCellValue(dataExcel.getRage());

		cell = row.createCell(5);
		cell.setCellValue(dataExcel.getnumOfCoffee());

		cell = row.createCell(6);
		cell.setCellValue(dataExcel.getnumOfWater());

		cell = row.createCell(7);
		cell.setCellValue(dataExcel.getnumOfSoda());
	}

	public static void createHeaderRow(Sheet sheet) {

		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		Font font = sheet.getWorkbook().createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 16);
		cellStyle.setFont(font);

		Row row = sheet.createRow(0);
		
		Cell cellSize = row.createCell(0);
		cellSize.setCellStyle(cellStyle);
		cellSize.setCellValue("Size");
		
		Cell cellTime = row.createCell(1);
		cellTime.setCellStyle(cellStyle);
		cellTime.setCellValue("Time");

		Cell cellStep = row.createCell(2);
		cellStep.setCellStyle(cellStyle);
		cellStep.setCellValue("Step");

		Cell cellRepeate = row.createCell(3);
		cellRepeate.setCellStyle(cellStyle);
		cellRepeate.setCellValue("Repeat Step");

		Cell cellRage = row.createCell(4);
		cellRage.setCellStyle(cellStyle);
		cellRage.setCellValue("Rage");

		Cell cellOr = row.createCell(5);
		cellOr.setCellStyle(cellStyle);
		cellOr.setCellValue("Coffe");

		Cell cellInor = row.createCell(6);
		cellInor.setCellStyle(cellStyle);
		cellInor.setCellValue("Water");

		Cell cellRec = row.createCell(7);
		cellRec.setCellStyle(cellStyle);
		cellRec.setCellValue("Soda");

	}
	public static void createHeaderRow2(Sheet sheet) {

		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		Font font = sheet.getWorkbook().createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 16);
		cellStyle.setFont(font);

		Row row = sheet.createRow(0);
		
		Cell cellA = row.createCell(0);
		cellA.setCellStyle(cellStyle);
		cellA.setCellValue("Points");
		
		Cell cellB = row.createCell(1);
		cellB.setCellStyle(cellStyle);
		cellB.setCellValue("Impressive");
		
		Cell cellC = row.createCell(2);
		cellC.setCellStyle(cellStyle);
		cellC.setCellValue("Happy");

		Cell cellD = row.createCell(3);
		cellD.setCellStyle(cellStyle);
		cellD.setCellValue("Normal");

		Cell cellE = row.createCell(4);
		cellE.setCellStyle(cellStyle);
		cellE.setCellValue("Bad");

		Cell cellF = row.createCell(5);
		cellF.setCellStyle(cellStyle);
		cellF.setCellValue("Angry");

		Cell cellG = row.createCell(6);
		cellG.setCellStyle(cellStyle);
		cellG.setCellValue("Rule");

	}

	//đọc nội dung file êxel trước đó
	public static List<DataExcel> getListDataExcel(String excelFilePath, DataExcel addNewOne)  throws IOException{
		List<DataExcel> listDataExcel = new ArrayList<>();
		 
        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));
 
        // Get workbook
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        
        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);
        // Get all rows
        Iterator<Row> iterator = sheet.iterator();

        while (iterator.hasNext()) {

            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
//            System.out.println("test");

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();
 
            // Read cells and set value for book object
            DataExcel dataExcel = new DataExcel();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                case 0:
                	dataExcel.setSize(new BigDecimal((double) cellValue).intValue());
                	break;
                case 1:
                	dataExcel.setTime(new BigDecimal((double) cellValue).intValue());
                    break;
                case 2:
                    dataExcel.setStep(new BigDecimal((double) cellValue).intValue());
                    break;
                case 3:
                    dataExcel.setReapeatStep(new BigDecimal((double) cellValue).intValue());
                    break;
                case 4:
                    dataExcel.setRage(new BigDecimal((double) cellValue).intValue());
                    break;
                case 5:
                    dataExcel.setnumOfCoffee(new BigDecimal((double) cellValue).intValue());
                    break;
                case 6: 
                    dataExcel.setnumOfWater(new BigDecimal((double) cellValue).intValue());
                	break;
                case 7: 
                    dataExcel.setnumOfSoda(new BigDecimal((double) cellValue).intValue());
                	break;
                default:
                    break;
                }
 
            }
            listDataExcel.add(dataExcel);
        }
        
        listDataExcel.add(addNewOne);
        
        workbook.close();
        inputStream.close();
 
		return listDataExcel;
	}
	
	public static List<DataExcel> getListDataExcel2(String excelFilePath, DataExcel addNewOne)  throws IOException{
		 
        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));
        addNewOne.setAngry(MainGui.geteAngry());
        addNewOne.setBad(MainGui.geteBad());
        addNewOne.setNormal(MainGui.geteNormal());
        addNewOne.setHappy(MainGui.geteHappy());
        addNewOne.setImpressive(MainGui.geteImpressive());
        System.out.println("im - add: " + addNewOne.getImpressive());
        System.out.println("happy - add: " + addNewOne.getHappy());
        System.out.println("nor - add: " + addNewOne.getNormal());
        System.out.println("bad - add: " + addNewOne.getBad());
        System.out.println("ang - add: " + addNewOne.getAngry());
        System.out.println("String - add " + addNewOne.getRule());
        // Get workbook
        Workbook workbook = WorkbookFactory.create(new File(excelFilePath));
        
        ///////
		List<DataExcel> listDataExcel2 = new ArrayList<>();
		try {
				workbook.getSheetAt(1);
				Sheet sheet2 = workbook.getSheetAt(1);
				Iterator<Row> iterator2 = sheet2.iterator();
				
				while (iterator2.hasNext()) {
					
					Row nextRow = iterator2.next();
					if (nextRow.getRowNum() == 0) {
						// Ignore header
						continue;
					}
					
					// Get all cells
					Iterator<Cell> cellIterator2 = nextRow.cellIterator();
					
					// Read cells and set value for book object
					DataExcel dataExcel2 = new DataExcel();
					while (cellIterator2.hasNext()) {
						//Read cell
						Cell cell = cellIterator2.next();
						Object cellValue = getCellValue(cell);
						if (cellValue == null || cellValue.toString().isEmpty()) {
							continue;
						}
						// Set value for book object
						int columnIndex = cell.getColumnIndex();
						switch (columnIndex) {
						case 0:
							dataExcel2.setPoint((new BigDecimal((double) cellValue).intValue()));
							break;
						case 1:
		                    dataExcel2.setImpressive((new BigDecimal((double) cellValue).intValue()));
							break;
						case 2:
							dataExcel2.setHappy((new BigDecimal((double) cellValue).intValue()));
							break;
						case 3:
							dataExcel2.setNormal((new BigDecimal((double) cellValue).intValue()));
							break;
						case 4:
							dataExcel2.setBad((new BigDecimal((double) cellValue).intValue()));
							break;
						case 5:
							dataExcel2.setAngry((new BigDecimal((double) cellValue).intValue()));
							break;
						case 6:
							dataExcel2.setRule((String) getCellValue(cell));
							break;
						default:
							break;
						}
						
					}
					listDataExcel2.add(dataExcel2);
				}
				listDataExcel2.add(addNewOne);
			
		} catch (Exception e) {
			Sheet sheet2;
			sheet2 = workbook.createSheet();
			Iterator<Row> iterator2 = sheet2.iterator();
			
			while (iterator2.hasNext()) {
				
				Row nextRow = iterator2.next();
				if (nextRow.getRowNum() == 0) {
					// Ignore header
					continue;
				}
				
				// Get all cells
				Iterator<Cell> cellIterator2 = nextRow.cellIterator();
				
				// Read cells and set value for book object
				DataExcel dataExcel2 = new DataExcel();
				while (cellIterator2.hasNext()) {
					//Read cell
					Cell cell = cellIterator2.next();
					Object cellValue = getCellValue(cell);
					if (cellValue == null || cellValue.toString().isEmpty()) {
						continue;
					}
					// Set value for book object
					int columnIndex = cell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						dataExcel2.setPoint(new BigDecimal((double) cellValue).intValue());
						break;
					case 1:
						dataExcel2.setImpressive(new BigDecimal((double) cellValue).intValue());
						break;
					case 2:
						dataExcel2.setHappy(new BigDecimal((double) cellValue).intValue());
						break;
					case 3:
						dataExcel2.setNormal(new BigDecimal((double) cellValue).intValue());
						break;
					case 4:
						dataExcel2.setBad(new BigDecimal((double) cellValue).intValue());
						break;
					case 5:
						dataExcel2.setAngry(new BigDecimal((double) cellValue).intValue());
						break;
					case 6:
						dataExcel2.setRule((String) getCellValue(cell));
						break;
					default:
						break;
					}
					
				}
				listDataExcel2.add(dataExcel2);
			}
			listDataExcel2.add(addNewOne);
		}
		workbook.close();
		inputStream.close();
		return listDataExcel2;
	}
	

	 public static Object getCellValue(Cell cell) {
	        CellType cellType = cell.getCellType();
	        Object cellValue = null;
	        switch (cellType) {
	        case BOOLEAN:
	            cellValue = cell.getBooleanCellValue();
	            break;
	        case FORMULA:
	            Workbook workbook = cell.getSheet().getWorkbook();
	            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
	            cellValue = evaluator.evaluate(cell).getNumberValue();
	            break;
	        case NUMERIC: 
	            cellValue = cell.getNumericCellValue();
	            break;
	        case STRING:
	            cellValue = cell.getStringCellValue();
	            break;
	        case _NONE:
	        case BLANK:
	        case ERROR:
	            break;
	        default:
	            break;
	        }
	 
	        return cellValue;
	    }
}
