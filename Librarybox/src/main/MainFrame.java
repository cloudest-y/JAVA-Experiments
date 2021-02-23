package main;

import info.Login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {
	
	/**
	 * @return the login
	 */
	public Login getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	private JPanel contentPane;
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnStudentInfo;
	private JMenuItem mntmLogin;
	private JMenuItem mntmLogout;
	private JMenuItem mntmExit;
	private JMenuItem mntmStore;
	private JMenuItem mntmEdit;
	private JMenuItem mntmDelete;
	private Login login;
	private JMenuItem mntmTodayRecord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					Login userLogin = new Login(window);

					window.setLogin(userLogin);
					window.setMenuItems(false);
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
	public MainFrame() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u516C\u5171\u50A8\u7269\u67DC\u7CFB\u7EDF");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login.setVisible(true);
			}
		});
		mnFile.add(mntmLogin);
		mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMenuItems(false);
				login.resetFields();
			}
		});
		mnFile.add(mntmLogin);

		mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMenuItems(false);
				login.resetFields();
			}
		});
		mnFile.add(mntmLogout);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		mnStudentInfo = new JMenu("Student Info");
		menuBar.add(mnStudentInfo);
		
		mntmStore = new JMenuItem("Store");
		mntmStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudent addStudent = new AddStudent();
				addStudent.setVisible(true);
			}
		});
		mnStudentInfo.add(mntmStore);
		
		mntmEdit = new JMenuItem("Edit ");
		mntmEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditStudent editStudent = new EditStudent();
				editStudent.setVisible(true);
			}
		});
		mnStudentInfo.add(mntmEdit);
		
		mntmDelete = new JMenuItem("Delete ");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteStudent delStudent = new DeleteStudent();
				delStudent.setVisible(true);
			}
		});
		mnStudentInfo.add(mntmDelete);
		
		mntmTodayRecord = new JMenuItem("TodayRecord");
		mnStudentInfo.add(mntmTodayRecord);
		
	}
	
	public void setMenuItems(boolean isEnable) {
	
		mnStudentInfo.setEnabled(isEnable);
	
	}

}
