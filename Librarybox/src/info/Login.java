package info;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import java.awt.Font;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

import main.MainFrame;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import conn.ConnectDB;

public class Login extends JFrame {
	private static MainFrame mainFrame;
	private JPanel contentPane;
	private JLabel lblManagerID;
	private JLabel lblPassword;
	private JButton btnLogin;
	private JButton btnCancel;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private boolean isPassed = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login(new MainFrame());
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
	public Login(MainFrame frm) {
		// 设置登录窗口标题
		setTitle("\u516C\u5171\u50A8\u7269\u67DC\u7CFB\u7EDF");
		MainFrame mainFrame = frm;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblManagerID = new JLabel("ManagerID:");
		lblManagerID.setFont(new Font("宋体", Font.PLAIN, 15));
		lblManagerID.setBounds(73, 86, 87, 22);
		contentPane.add(lblManagerID);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("宋体", Font.PLAIN, 15));
		lblPassword.setBounds(73, 131, 87, 18);
		contentPane.add(lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isValidUser(txtUsername.getText(), txtPassword.getText())) {
					JOptionPane.showMessageDialog(null, "用户名或密码不正确，请重新输入！");
					isPassed = false;
				} else {
					isPassed = true;
				}

				mainFrame.setMenuItems(isPassed);
			}
		});
		btnLogin.setBounds(89, 217, 89, 23);
		contentPane.add(btnLogin);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(247, 217, 89, 23);
		contentPane.add(btnCancel);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(181, 87, 133, 21);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(183, 130, 131, 21);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
	}
	protected boolean isValidUser(String username, String password) {
		// TODO Auto-generated method stub
		boolean isValid = false;

		Connection conn = null;

		try {
			conn = (Connection) ConnectDB.getConnection();
			Statement stat = (Statement) conn.createStatement();

			ResultSet result = stat
					.executeQuery("SELECT * FROM users WHERE username='"
							+ username.trim() + "'");
			if (result.next()
					&& password.trim().equals(result.getString(2).trim())) {
				isValid = true;
			}

			result.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return isValid;
	}

	/**
	 * @return the isPassed
	 */
	public boolean isPassed() {
		return isPassed;
	}

	/**
	 * @param isPassed
	 *            the isPassed to set
	 */
	public void setPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}
	public void resetFields(){
		txtUsername.setText(null);
		txtPassword.setText(null);
	}
}