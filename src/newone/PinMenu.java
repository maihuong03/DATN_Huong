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
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PinMenu extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JLabel goiY;

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
    public PinMenu() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 375, 163);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Replace the battery to:");
        lblNewLabel.setBounds(10, 11, 137, 44);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(40, 43, 107, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton enter = new JButton("ENTER");
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String a = textField.getText();
                double b = (double) Integer.parseInt(a);
                MainGui.setLuongPin(b);
                MainGui.setLuongPinSetUp(b);
            }
        });
        enter.setBounds(157, 42, 89, 23);
        contentPane.add(enter);
        String html = "<The battery level must be above " + (int) MainGui.getLuongPinTinhToan() + "mah>";
        goiY = new JLabel(html);
        goiY.setForeground(Color.RED);
        goiY.setBackground(Color.RED);
        goiY.setBounds(10, 80, 341, 35);
        contentPane.add(goiY);
    }
}
