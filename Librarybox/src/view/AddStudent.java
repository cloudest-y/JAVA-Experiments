package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import main.StudentInfoManagement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import business.Student;
import business.StudentRecord;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddStudent extends JFrame {

	private JPanel contentPane;
	private JLabel lblAddNewStudent;
	private JLabel lblId;
	private JLabel lblName;
	private JLabel lblSex;
	private JLabel lblBirthday;
	private JLabel lblClass;
	private JLabel lblDepartment;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtBirthday;
	private JTextField txtClass;
	private JTextField txtDepartment;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private Student student;
	private StudentInfoManagement mainFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent frame = new AddStudent(null, null);
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
	public AddStudent(StudentInfoManagement parent, Student mainStudent) {
		if (mainStudent != null)
			this.student = mainStudent;

		if (parent != null)
			mainFrame = parent;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAddNewStudent = new JLabel("Add new student");
		lblAddNewStudent.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddNewStudent.setBounds(39, 11, 255, 34);
		getContentPane().add(lblAddNewStudent);
		
		lblId = new JLabel("ID:");
		lblId.setFont(new Font("宋体", Font.BOLD, 12));
		lblId.setBounds(34, 79, 54, 15);
		getContentPane().add(lblId);
		
		lblName = new JLabel("Name:");
		lblName.setFont(new Font("宋体", Font.BOLD, 12));
		lblName.setBounds(34, 122, 54, 15);
		getContentPane().add(lblName);
		
		lblSex = new JLabel("Sex:");
		lblSex.setFont(new Font("宋体", Font.BOLD, 12));
		lblSex.setBounds(34, 165, 54, 15);
		getContentPane().add(lblSex);
		
		lblBirthday = new JLabel("Birthday:");
		lblBirthday.setFont(new Font("宋体", Font.BOLD, 12));
		lblBirthday.setBounds(34, 209, 85, 15);
		getContentPane().add(lblBirthday);
		
		lblClass = new JLabel("Class:");
		lblClass.setFont(new Font("宋体", Font.BOLD, 12));
		lblClass.setBounds(34, 251, 61, 15);
		getContentPane().add(lblClass);
		
		lblDepartment = new JLabel("Department:");
		lblDepartment.setFont(new Font("宋体", Font.BOLD, 12));
		lblDepartment.setBounds(34, 298, 85, 15);
		getContentPane().add(lblDepartment);
		
		txtID = new JTextField();
		txtID.setBounds(111, 76, 98, 21);
		getContentPane().add(txtID);
		txtID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(111, 119, 98, 21);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtBirthday = new JTextField();
		txtBirthday.setBounds(129, 206, 98, 21);
		getContentPane().add(txtBirthday);
		txtBirthday.setColumns(10);
		
		txtClass = new JTextField();
		txtClass.setBounds(129, 248, 98, 21);
		getContentPane().add(txtClass);
		txtClass.setColumns(10);
		
		txtDepartment= new JTextField();
		txtDepartment.setBounds(129, 295, 140, 21);
		getContentPane().add(txtDepartment);
		txtDepartment.setColumns(10);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(94, 161, 54, 23);
		getContentPane().add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(173, 161, 78, 23);
		getContentPane().add(rdbtnFemale);
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnMale);
		group.add(rdbtnFemale);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentRecord stu = new StudentRecord();
				stu.setId(txtID.getText().trim());
				stu.setName(txtName.getText().trim());
				if (rdbtnMale.isSelected())
					stu.setSex(1);
				else
					stu.setSex(0);
				stu.setBirthday(txtBirthday.getText().trim());
				stu.setStuClass(txtClass.getText().trim());
				stu.setDepartment(txtDepartment.getText().trim());

				if ((student != null) && student.add(stu)) {
					if (mainFrame != null) {

						mainFrame.addStudent(stu);
					}

					JOptionPane.showMessageDialog(null, "学生（ID=" + stu.getId()
							+ ", Name=" + stu.getName() + "）添加成功！");
				} else {
					JOptionPane.showMessageDialog(null, "学生（ID=" + stu.getId()
							+ ", Name=" + stu.getName() + "）添加失败！");
				}
			}
		});
		btnAdd.setBounds(41, 351, 89, 23);
		contentPane.add(btnAdd);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtID.setText(null);
				txtName.setText(null);
				txtBirthday.setText(null);
				txtClass.setText(null);
				txtDepartment.setText(null);
				rdbtnMale.setSelected(true);
			}
		});
		btnReset.setBounds(171, 351, 89, 23);
		contentPane.add(btnReset);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(301, 351, 89, 23);
		contentPane.add(btnClose);
	}
}