package newone;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.MainGui;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;

public class MoreOption extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup1 = new ButtonGroup();
	private final ButtonGroup buttonGroup2 = new ButtonGroup();
	private final ButtonGroup buttonGroup3 = new ButtonGroup();
	private final ButtonGroup buttonGroup4 = new ButtonGroup();
	private final ButtonGroup buttonGroup5 = new ButtonGroup();
	private final ButtonGroup buttonGroup6 = new ButtonGroup();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PinMenu frame = new PinMenu();
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
	public MoreOption() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 558, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Select your option:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 127, 19);
		contentPane.add(lblNewLabel);

		JRadioButton A4 = new JRadioButton("Young");
		buttonGroup1.add(A4);
		A4.setBounds(110, 37, 111, 23);
		contentPane.add(A4);

		JRadioButton A5 = new JRadioButton("Middle-age");
		buttonGroup1.add(A5);
		A5.setBounds(233, 37, 111, 23);
		contentPane.add(A5);

		JRadioButton A6 = new JRadioButton("Old");
		buttonGroup1.add(A6);
		A6.setBounds(363, 37, 111, 23);
		contentPane.add(A6);

		JRadioButton A7 = new JRadioButton("Music");
		buttonGroup2.add(A7);
		A7.setBounds(110, 67, 111, 23);
		contentPane.add(A7);

		JRadioButton A8 = new JRadioButton("Movie");
		buttonGroup2.add(A8);
		A8.setBounds(233, 67, 111, 23);
		contentPane.add(A8);

		JRadioButton A9 = new JRadioButton("Newspaper");
		buttonGroup2.add(A9);
		A9.setBounds(363, 67, 111, 23);
		contentPane.add(A9);

		JRadioButton A10 = new JRadioButton("Sweetness ");
		buttonGroup3.add(A10);
		A10.setBounds(110, 93, 111, 23);
		contentPane.add(A10);

		JRadioButton A11 = new JRadioButton("Sour");
		buttonGroup3.add(A11);
		A11.setBounds(233, 93, 111, 23);
		contentPane.add(A11);

		JRadioButton A12 = new JRadioButton("Bitterness");
		buttonGroup3.add(A12);
		A12.setBounds(363, 93, 111, 23);
		contentPane.add(A12);

		JLabel lblNewLabel_1 = new JLabel("Age:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(20, 41, 60, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Preference:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(20, 71, 60, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Taste:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setBounds(20, 97, 60, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Health status:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setBounds(20, 122, 70, 14);
		contentPane.add(lblNewLabel_5);

		JRadioButton A13 = new JRadioButton("Good");
		buttonGroup4.add(A13);
		A13.setBounds(110, 119, 111, 23);
		contentPane.add(A13);

		JRadioButton A14 = new JRadioButton("Normal");
		buttonGroup4.add(A14);
		A14.setBounds(233, 119, 111, 23);
		contentPane.add(A14);

		JRadioButton A15 = new JRadioButton("Bad");
		buttonGroup4.add(A15);
		A15.setBounds(363, 119, 111, 23);
		contentPane.add(A15);

		JRadioButton A18 = new JRadioButton("Autumn");
		buttonGroup5.add(A18);
		A18.setBounds(110, 169, 111, 23);
		contentPane.add(A18);

		JRadioButton A17 = new JRadioButton("Summer");
		buttonGroup5.add(A17);
		A17.setBounds(233, 143, 111, 23);
		contentPane.add(A17);

		JRadioButton A16 = new JRadioButton("Spring");
		buttonGroup5.add(A16);
		A16.setBounds(110, 143, 111, 23);
		contentPane.add(A16);

		JLabel lblNewLabel_5_1 = new JLabel("Weather:");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_1.setBounds(20, 146, 70, 14);
		contentPane.add(lblNewLabel_5_1);

		JRadioButton A19 = new JRadioButton("Winter");
		buttonGroup5.add(A19);
		A19.setBounds(233, 169, 111, 23);
		contentPane.add(A19);

		JRadioButton A22 = new JRadioButton("Cake");
		buttonGroup6.add(A22);
		A22.setBounds(363, 195, 111, 23);
		contentPane.add(A22);

		JRadioButton A21 = new JRadioButton("Fried Food");
		buttonGroup6.add(A21);
		A21.setBounds(233, 195, 111, 23);
		contentPane.add(A21);

		JRadioButton A20 = new JRadioButton("Snack");
		buttonGroup6.add(A20);
		A20.setBounds(110, 195, 111, 23);
		contentPane.add(A20);

		JLabel lblNewLabel_5_2 = new JLabel("Foods:");
		lblNewLabel_5_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5_2.setBounds(20, 198, 70, 14);
		contentPane.add(lblNewLabel_5_2);

		JRadioButton A23 = new JRadioButton("Fruits");
		buttonGroup6.add(A23);
		A23.setBounds(110, 221, 111, 23);
		contentPane.add(A23);

		JRadioButton A24 = new JRadioButton("Sweet food");
		buttonGroup6.add(A24);
		A24.setBounds(233, 221, 111, 23);
		contentPane.add(A24);

		//lấy thông tin 
		String[] strings = new String[7];
		for(int i = 0; i < 7; i++) {
			strings[i] = "o";
		}
		System.out.println("truoc khi enter: " + Arrays.toString(MainGui.getOrderArray()));
		JButton optionEnterBtn = new JButton("Enter");
		optionEnterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (A4.isSelected()) {
					strings[0] = "A4";
				} else if (A5.isSelected()) {
					strings[0] = "A5";
				} else if (A6.isSelected()) {
					strings[0] = "A6";
				}
				if (A7.isSelected()) {
					strings[1] = "A7";
				} else if (A8.isSelected()) {
					strings[1] =  "A8";
				} else if (A9.isSelected()) {
					strings[1] = "A9";
				}
				if (A10.isSelected()) {
					strings[2] = "A10";
				} else if (A11.isSelected()) {
					strings[2] = "A11";
				} else if (A12.isSelected()) {
					strings[2] = "A12";
				}
				if (A13.isSelected()) {
					strings[3] = "A13";
				} else if (A14.isSelected()) {
					strings[3] = "A14";
				} else if (A15.isSelected()) {
					strings[3] = "A15";
				}
				if (A16.isSelected()) {
					strings[4] ="A16";
				} else if (A17.isSelected()) {
					strings[4] ="A17";
				} else if (A18.isSelected()) {
					strings[4] ="A18";
				}else if(A19.isSelected()) {
					strings[4] ="A19";
				}
				if (A20.isSelected()) {
					strings[5] = "A20";
				} else if (A21.isSelected()) {
					strings[5] = "A21";
				} else if (A22.isSelected()) {
					strings[5] = "A22";
				}else if(A23.isSelected()) {
					strings[5] = "A23";
				}else if(A24.isSelected()) {
					strings[5] = "A24";
				}
				
				strings[6] = textField.getText();
				
				MainGui.setOrderArray(strings);
				
			}
		});
		optionEnterBtn.setBounds(381, 245, 153, 37);
		contentPane.add(optionEnterBtn);

		JLabel lblNewLabel_6 = new JLabel("Order option:");
		lblNewLabel_6.setBounds(10, 256, 66, 14);
		contentPane.add(lblNewLabel_6);

		textField = new JTextField();
		textField.setBounds(110, 245, 234, 37);
		contentPane.add(textField);
		textField.setColumns(10);

	}
}
