/**
 * 
 */
package gui;

import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import cstt.SuKienForm;

/**
 * @author HuyLV
 *
 */
public class StatusBar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel message;
	Random r = new Random();

	private int workingTimer = 0;

	private JLabel lbl1;
	private String ruleString = "";

	public StatusBar() {
		super();
		this.setLayout(null);
		Border loweredbevel = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(loweredbevel);
		String lbl1Text = "Robot working at: ";
		lbl1 = new JLabel(lbl1Text);
		lbl1.setBounds(10, 1, 97, 25);
		this.add(lbl1);

		message = new JLabel();
		message.setBounds(111, 1, 900, 25);
		this.add(message);
		resetMessage();
	}

	public void resetMessage() { // Tạo lại Status bar cuối
		workingTimer = 0;
		String lbl1Text = "Total processing time: ";
		message.setText("[ " + "_" + ", " + "_" + " ]   |   " + lbl1Text + "____" + " (miliseconds)");
	}

	public void setMessage(int x, int y, int time) {
		workingTimer += time;
		String lbl1Text = "Total processing time: ";
		int n=0;
		
		while( n<35) n=r.nextInt(105);
//		message.setText("[ " + x + ", " + y + " ]   |   " + lbl1Text + workingTimer + " (miliseconds)" +" | Rule :"+n+" "+SuKienForm.dssk.get(n).getIF()+ ", " +SuKienForm.dssk.get(n).getTHEN());
		message.setText("[ " + x + ", " + y + " ]   |   " + lbl1Text + workingTimer	+ " (miliseconds)");

	}
	
	public void setMessageOrder(String X) {
		message.setText("Order: " + X);
	}
	
//	public void setRuleMessage(String x) {
//		String lbl1Text = "Total processing time: ";
//		message.setText("[ " + "_" + ", " + "_" + " ]   |   " + lbl1Text + "____" + " (miliseconds) | "  + "Rule: "  + x);
//		
//	}

	public int getworkingTime() {
		return workingTimer;
	}
}
