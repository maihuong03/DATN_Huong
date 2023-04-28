package newone;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.MainGui;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class YesNo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YesNo frame = new YesNo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public YesNo() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 304, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton noButton = new JButton("NO");
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGui.setIsPowerCanRun(false);
//				System.exit(0);
			}
		});
		noButton.addActionListener(e -> this.dispose());
		noButton.setBounds(165, 45, 125, 36);
		contentPane.add(noButton);
		
		JButton yesButton = new JButton("YES");
		yesButton.setToolTipText("If you still want to continue running, you must replace the battery.");
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGui.setIsPowerCanRun(true);
				PinMenu pinmenu  = new PinMenu();
				pinmenu.setVisible(true);
			}
		});
		yesButton.addActionListener(e -> this.dispose());
		yesButton.setBounds(8, 45, 125, 36);
		contentPane.add(yesButton);
		String html = "<html>The current battery level is not enough.<br>\r\n"
				+ "Do you want to continue with the mission?</html>";
		JLabel lblLngPinHin = new JLabel(html);
		lblLngPinHin.setBounds(8, 10, 258, 33);
		contentPane.add(lblLngPinHin);
		String x = "<html><Maybe><br>Battery charger for robot.</html>";
		JLabel label2 = new JLabel("");
		label2.setBounds(8, 92, 49, 14);
		contentPane.add(label2);
	}
}
