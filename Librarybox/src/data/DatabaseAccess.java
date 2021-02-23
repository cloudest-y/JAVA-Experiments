package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseAccess {

	private Connection connection;
	private Statement statement;

	public DatabaseAccess() throws SQLException {

		// ͨ�������ļ�������������

		// ��ȡ�����ļ�database.properties
		Properties props = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream("database.properties");
			props.load(in);
			in.close();
		} catch (FileNotFoundException e1) {
			System.out.println("�Ҳ������ݿ����������ļ���database.properties��");
			e1.printStackTrace();
		} catch (IOException e1) {
			// ��ȡ�ļ������쳣
			e1.printStackTrace();
		}

		String drivers = props.getProperty("jdbc.drivers");
		if (drivers != null) {
			// ������ݿ����������Ƿ�������ȷ
			try {
				// ��������JDBC�������࣬����������������ע������ݿ���������
				Class.forName(drivers);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("û�з���JDBC��������" + drivers + "��");
				e.printStackTrace();
			}
			System.setProperty("jdbc.drivers", drivers);
		}

		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");

		// �������ݿ�����
		/**
		 * Gets a connection from the properties specified in the file
		 * database.properties
		 * 
		 * @return the database connection
		 */
		connection = DriverManager.getConnection(url, username, password);
		System.out.println("�ɹ��������ݿ⡣");

		// ������ӳɹ������Ƿ��о�����Ϣ
		SQLWarning warning = connection.getWarnings();
		while (warning != null) {
			System.out.println(warning.getMessage());
			warning = warning.getNextWarning();
		}

		// ����һ������ִ�м�SQL��������
		statement = connection.createStatement();
	}

	public void close() throws SQLException {
		// �ر�����
		if (connection != null) {
			connection.close();
			System.out.println("�ɹ��ر����ݿ����ӡ�");
		}

	}

	/*
	 * ����һ��SQL���ִ�����ݿ��ѯ����������sql��ʾSQL��ѯ��䣬���硰SELECT * FROM student���� ���ز�ѯ�������
	 */
	public ResultSet query(String sql) throws SQLException {
		return statement.executeQuery(sql);
	}

	/*
	 * ����һ��SQL���ִ�����ݿ���²��������롢�޸ĺ�ɾ����������sql��ʾSQL��ѯ��䣬���硰DELETE FROM student����
	 * ����Ӱ�������ļ�¼����
	 */
	public int update(String sql) throws SQLException {
		return statement.executeUpdate(sql);
	}

	public static void main(String args[]) {
		try {
			runTest();
		} catch (SQLException ex) {
			for (Throwable t : ex)
				t.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Runs a test by creating a table, adding a value, showing the table
	 * contents, and removing the table.
	 */
	public static void runTest() throws SQLException, IOException {

		try {

			DatabaseAccess da = new DatabaseAccess();

			da.update("CREATE TABLE Greetings (Message CHAR(20))");
			da.update("INSERT INTO Greetings VALUES ('Hello, MySQL World!')");

			ResultSet result = da.query("SELECT * FROM Greetings");
			if (result.next())
				System.out.println(result.getString(1));
			result.close();
			da.update("DROP TABLE Greetings");
			da.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}

