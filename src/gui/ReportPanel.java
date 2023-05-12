/**
 * 
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import newone.ThaoTacFileExcel;
import newone.DataExcel;
import newone.ThaoTacFile;

public class ReportPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private MainGui mainGui;
	private JLabel timeProcessLbl, numOfStepsLbl, duplicateStepsLbl, coverageAreaLbl;
	private TitledBorder border;

	private JLabel timeprocess, numOfSteps, duplicateSteps, coverageArea, phanloairac, emotionLabel;

	public ReportPanel(MainGui main) {
		super();

		String title = "Statistics";
		setTitle(title);

		setSize(400, 497);
		setVisible(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		setBackground(Color.WHITE);
		JPanel pane0 = new JPanel();
		pane0.setLayout(null);
		pane0.setBackground(Color.WHITE);
		pane0.setBounds(0, 0, getWidth(), getHeight());
		getContentPane().add(pane0);

		this.mainGui = main;
		setLocationRelativeTo(mainGui);

		JPanel pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.WHITE);
		pane.setBounds(10, 11, getWidth() - 25, getHeight() - 50);

		String result = "Statistics";
		border = BorderFactory.createTitledBorder(result);
		border.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		pane.setBorder(border);
		pane0.add(pane);

		String timeProcessLblTxt = "Processing Time:";
		timeProcessLbl = new JLabel(timeProcessLblTxt, SwingConstants.RIGHT);
		timeProcessLbl.setBounds(33, 20, 100, 25);
		pane.add(timeProcessLbl);

		Font valueFont = new Font("Tahoma", Font.BOLD, 12);

		timeprocess = new JLabel("58000", SwingConstants.LEFT);
		timeprocess.setFont(valueFont);
		timeprocess.setBounds(150, 20, 100, 25);
		pane.add(timeprocess);

		JLabel note1 = new JLabel("(miliseconds)");
		note1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		note1.setBounds(210, 20, 100, 25);
		pane.add(note1);

		JSeparator s1 = new JSeparator();
		s1.setBounds(60, 45, 250, 25);
		pane.add(s1);

		String numOfStepsLblTxt = "Number Of Step:";
		numOfStepsLbl = new JLabel(numOfStepsLblTxt, SwingConstants.RIGHT);
		numOfStepsLbl.setBounds(29, 69, 100, 25);
		pane.add(numOfStepsLbl);

		numOfSteps = new JLabel("101", SwingConstants.LEFT);
		numOfSteps.setFont(valueFont);
		numOfSteps.setBounds(150, 70, 100, 25);
		pane.add(numOfSteps);

		JSeparator s2 = new JSeparator();
		s2.setBounds(60, 95, 250, 25);
		pane.add(s2);

		String duplicateStepsLblTxt = "Number Of Repeat Step:";
		duplicateStepsLbl = new JLabel(duplicateStepsLblTxt, SwingConstants.RIGHT);
		duplicateStepsLbl.setBounds(1, 117, 125, 25);
		pane.add(duplicateStepsLbl);

		duplicateSteps = new JLabel("4", SwingConstants.LEFT);
		duplicateSteps.setFont(valueFont);
		duplicateSteps.setBounds(150, 120, 100, 25);
		pane.add(duplicateSteps);

		JSeparator s3 = new JSeparator();
		s3.setBounds(60, 145, 250, 25);
		pane.add(s3);

		String coverageAreaLblTxt = "Rate is covered:";
		coverageAreaLbl = new JLabel(coverageAreaLblTxt, SwingConstants.RIGHT);
		coverageAreaLbl.setBounds(26, 169, 100, 25);
		pane.add(coverageAreaLbl);

		coverageArea = new JLabel("65.66", SwingConstants.LEFT);
		coverageArea.setFont(valueFont);
		coverageArea.setBounds(150, 170, 100, 25);
		pane.add(coverageArea);

		JLabel note2 = new JLabel("(%)");
		note2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		note2.setBounds(210, 170, 100, 25);
		pane.add(note2);

		JSeparator s4 = new JSeparator();
		s4.setBounds(60, 195, 250, 25);
		pane.add(s4);

		JLabel lblNewLabel = new JLabel("Served:");
		lblNewLabel.setBounds(92, 231, 37, 14);
		pane.add(lblNewLabel);

		phanloairac = new JLabel("New label");
		phanloairac.setVerticalAlignment(SwingConstants.TOP);
		phanloairac.setBounds(146, 231, 137, 66);
		pane.add(phanloairac);

		JLabel lblNewLabel_1 = new JLabel("Emotions:");
		lblNewLabel_1.setBounds(76, 317, 50, 14);
		pane.add(lblNewLabel_1);

		emotionLabel = new JLabel("New label");
		emotionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		emotionLabel.setVerticalAlignment(SwingConstants.TOP);
		emotionLabel.setBounds(150, 317, 137, 119);
		pane.add(emotionLabel);

		revalidate();
		repaint();
	}

	// hiển thị kết quả sau khi chạt
	// , int numOfHuuCo,int numOfVoCo, int numOfTaiChe
	public void showReport(int time, int steps, int duplicateSteps, double coverage, int sumOfWater, int sumOfCoffe,
			int sumOfSoda, boolean isWriteToExcel, int im, int happy, int normal, int bad, int angry, int point)
			throws IOException {
		String html = "<html>-Water: " + sumOfWater + "<br>-Coffe: " + sumOfCoffe + "<br>-Soda: " + sumOfSoda
				+ "</html>";
		timeprocess.setText("" + time);
		numOfSteps.setText("" + steps);
		phanloairac.setText("" + html);
		emotionLabel.setText("<html>-Empessive: " + im + "<br>-Happy: " + happy + "<br>-Normal: " + normal
				+ "<br>-Sad: " + bad + "<br>-Angry: " + angry + "<br>  => total points: " + point + "</html>");
		this.duplicateSteps.setText("" + duplicateSteps);
		this.coverageArea.setText("" + coverage);
		if (isWriteToExcel) {
			DataExcel dataExcel = new DataExcel(MainGui.getSizeOfMap(), time, steps, duplicateSteps, coverage,
					sumOfWater, sumOfCoffe, sumOfSoda);
			DataExcel dataExcel2 = new DataExcel(MainGui.getPoint(), im, happy, normal, bad, angry,
					MainGui.saveRuleToString(MainGui.getSaveRule()));
			System.out.println("rule in reprot panel: " + MainGui.saveRuleToString(MainGui.getSaveRule()));
			ThaoTacFileExcel thaoTacExcel = new ThaoTacFileExcel(MainGui.getLinkFileExcel(), dataExcel, dataExcel2);
			System.out.println("Đã ghi vào file excel");
			MainGui.resetOrder();
			System.out.println("A : " + im);
			System.out.println("B : " + happy);
			System.out.println("C : " + normal);
			System.out.println("D : " + bad);
			System.out.println("E : " + angry);

		}

		revalidate();
		repaint();
		setVisible(true);
	}
}
