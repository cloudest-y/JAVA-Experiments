package conn;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Store {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Store window = new Store();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Store() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u516C\u5171\u50A8\u7269\u67DC\uFF0C\u8BF7\u8F93\u5165\u60A8\u7684\u4FE1\u606F\u3002");
		lblNewLabel.setFont(new Font("ËÎÌו", Font.BOLD, 24));
		lblNewLabel.setBounds(41, 10, 350, 57);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblId = new JLabel("ID\uFF1A");
		lblId.setBounds(41, 77, 54, 15);
		frame.getContentPane().add(lblId);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(41, 112, 54, 15);
		frame.getContentPane().add(lblName);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setBounds(41, 147, 72, 15);
		frame.getContentPane().add(lblDepartment);
		
		JLabel lblStarttime = new JLabel("StartTime:");
		lblStarttime.setBounds(41, 257, 60, 15);
		frame.getContentPane().add(lblStarttime);
		
		JLabel lblLeavetime = new JLabel("LeaveTime:");
		lblLeavetime.setBounds(41, 293, 60, 15);
		frame.getContentPane().add(lblLeavetime);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(41, 220, 54, 15);
		frame.getContentPane().add(lblDate);
		
		textField = new JTextField();
		textField.setBounds(116, 77, 150, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(116, 109, 150, 21);
		frame.getContentPane().add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(116, 144, 150, 21);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(116, 217, 150, 21);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(116, 254, 150, 21);
		frame.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(116, 290, 150, 21);
		frame.getContentPane().add(textField_6);
		
		JButton button = new JButton("\u5B58");
		button.setFont(new Font("ËÎÌו", Font.PLAIN, 21));
		button.setBounds(171, 357, 72, 35);
		frame.getContentPane().add(button);
	}
}
