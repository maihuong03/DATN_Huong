/**
 * 
 */
package common;

import java.util.Properties;

/**
 * @author HuyLV
 *
 */
public class ProcessUtil {

	private static final String MIDDLE_VALUE = "0.25";
	private static final String VERY_VALUE = "0.35";
	private static final String MORE_VALUE = "0.25";
	private static final String POSSIBLE_VALUE = "0.2";
	private static final String LESS_VALUE = "0.2";
	private static final String STANDARD_MODE_PROCESS_TIME = "150";
	private static final String CURRENT_WORKING_MODE = "0";
	private static final String SHOW_DUST_VALUE = "1";

	private static final String SAMPLE_DATA = "SAMPLE_DATA";

	public static void setCurrentWorkingMode(String value){
		setProperty(CURRENT_WORKING_MODE, value);
	}
	
	public static int getCurrentWorkingMode() { 
		int result = 0;
		try {
			result = Integer.parseInt(CURRENT_WORKING_MODE);
		} catch (Exception e) {
		}
		return result;
	}

	public static int getShowDustValue() {
		int result = 0;
		try {
			result = Integer.parseInt(SHOW_DUST_VALUE);
		} catch (Exception e) {
		}

		return result;
	}

	public static void setSowDustVaue(String value) {
		setProperty(SHOW_DUST_VALUE, value);
	}

	public static int getStandardProcessTime() {
		int result = 500;
		try {
			result = Integer.parseInt(STANDARD_MODE_PROCESS_TIME);
		} catch (Exception e) {
		}

		return result;
	}

	public static void setStandardProcessTime(String value) {
		setProperty(STANDARD_MODE_PROCESS_TIME, value);
	}

	public static void main(String[] args) {

	}

	public static double getMiddleValue() {
		return getDoubleValue(MIDDLE_VALUE);
	}

	public static double getVeryValue() {
		return getDoubleValue(VERY_VALUE);
	}

	public static void setVeryValue(String value) {
		setProperty(VERY_VALUE, value);
	}

	public static double getMoreValue() {
		return getDoubleValue(MORE_VALUE);
	}

	public static void setMoreValue(String value) {
		setProperty(MORE_VALUE, value);
	}

	public static double getPossibleValue() {
		return getDoubleValue(POSSIBLE_VALUE);
	}

	public static void setPossibleValue(String value) {
		setProperty(POSSIBLE_VALUE, value);
	}

	public static double getLessValue() {
		return getDoubleValue(LESS_VALUE);
	}

	public static void setLessValue(String value) {
		setProperty(LESS_VALUE, value);
	}

	private static double getDoubleValue(String valStr) {
		double result = 0.25;
		try {
			result = Double.parseDouble(valStr);
		} catch (Exception e) {
		}

		return result;
	}
	public static String getSampleData() {
		return "Sample Data";
	}
	public static void setMiddleValue(String value) {
		setProperty(MIDDLE_VALUE, value);
	}

	private static void setProperty(String key, String value) {
		Properties prop = new Properties();
		prop.setProperty(key, value);
		System.out.print(prop.getProperty(key) + "-"); //hiển thị giá trị trên console
	}
	public static void setSampleData(String value) {
		setProperty(SAMPLE_DATA, value);
	}
//	public static InputStreamReader getConfigInputStream(String name) {
//		InputStream in = ProcessUtil.class.getClassLoader()
//				.getResourceAsStream(name);
//		try {
//			return new InputStreamReader(in, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//		}
//
//		return null;
//	}
}
