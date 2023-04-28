package common;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cstt.test;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class TestAll extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TestAll frame = new TestAll();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		for(int i  = 0; i < 100; i++) {
			Random rd = new Random();
			System.out.println(rd.nextInt(6) + 1);
		}
	}

	/**
	 * Create the frame.
	 */
	public TestAll() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
//		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\long0\\eclipse-workspace\\RobotNew\\resources\\full.png"));
		lblNewLabel_1.setForeground(Color.CYAN);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel_1.setBounds(10, 25, 511, 158);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Bye");
		getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel.setBounds(103, 78, 153, 58);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

//		JLabel lblNewLabel_1 = new JLabel("Hello");
//		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 37));
//		lblNewLabel_1.setBounds(106, 91, 82, 45);
//		contentPane.add(lblNewLabel_1);	
//		JLabel lb2 = new JLabel("");
//		lb2.setBounds(0, 0, 355, 225);
//		contentPane.add(lb2);
//		lb2.setIcon(new ImageIcon("C:\\Users\\long0\\eclipse-workspace\\RobotNew\\resources\\full.png"));
////		testLabel.setText("Hello");
//
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		JButton btnNewButton = new JButton("New button");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				JLabel lblNewLabel = new JLabel("Hello");
//				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 37));
//				lblNewLabel.setBounds(106, 91, 82, 45);
//				contentPane.add(lblNewLabel);			}
//		});
//		
//		JLabel lblNewLabel_2 = new JLabel("Long");
//		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_2.setBounds(27, 233, 246, 14);
////		contentPane.add(lblNewLabel_2);
//		btnNewButton.setBounds(347, 229, 89, 23);
//		contentPane.add(btnNewButton);
//
		
		

	}
}
