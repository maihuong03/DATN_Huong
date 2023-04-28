package common;

import java.awt.Color;

public class DustColor {
	private static Color c1 = new Color(160, 160, 160);
	private static Color c2 = new Color(157, 157, 157);
	private static Color c3 = new Color(151, 151, 151);
	private static Color c4 = new Color(145, 145, 145);
	private static Color c5 = new Color(140, 140, 140);
	private static Color c6 = new Color(134, 134, 134);
	private static Color c7 = new Color(128, 128, 128);
	private static Color c8 = new Color(122, 122, 122);
	private static Color c9 = new Color(117, 117, 117);
	private static Color c10 = new Color(111, 111, 111);
	private static Color c11 = new Color(99, 99, 99);
	private static Color c12 = new Color(94, 94, 94);
	private static Color c13 = new Color(88, 88, 88);
	private static Color c14 = new Color(82, 82, 82);
	private static Color c15 = new Color(76, 76, 76);
	private static Color c16 = new Color(71, 71, 71);
	private static Color c17 = new Color(65, 65, 65);
	private static Color c18 = new Color(59, 59, 59);
	private static Color c19 = new Color(54, 54, 54); 
//	private static Color c1 = new Color(255, 0, 0);
//	private static Color c2 = new Color(157, 157, 157);
//	private static Color c3 = new Color(151, 151, 151);
//	private static Color c4 = new Color(145, 145, 145);
	

	private static Color[] colors = { c1, c2, c3, c4, c5, c6, c7, c8, c9, c10,
			c11, c12, c13, c14, c15, c16, c17, c18, c19 };
//	private static Color[] colors = { c1, c2, c3, c4 };

	private static int[] steps = { 5, 10, 20, 25, 30, 35, 40, 45, 50, 55, 60,
			65, 70, 75, 80, 85, 90,95 };
//	private static int[] steps = { 1, 2, 3, 4};
	
	//set color 
	public static Color getColor(int dustValue) {
		for(int i = 0; i < steps.length; i++){
			if(steps[i] > dustValue){
				return colors[i];
			}
		}
		return new Color(255, 255, 255);
	}
//	public static Color getColor(int dustValue) {
//		for(int i = 0; i < 4; i++){
//			if(steps[i] > dustValue){
//				return colors[i];
//			}
//		}
//		return new Color(255, 255, 255);
//	}
}
