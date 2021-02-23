package setup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conn.ConnectDB;

 class SetupStudentDB {

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
		Connection conn = ConnectDB.getConnection();
		try {
			Statement stat = conn.createStatement();

			// 如果表studentinfo不存在则创建
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS studentinfo  (id CHAR(9) NOT NULL, name VARCHAR(20) NOT NULL, Date DATE, Department VARCHAR(50), Starttime TIME,Endtime TIME,Boxnum CHAR(2),PRIMARY KEY (id))");
			System.out.println("Create table studentinfo successfully.");
			// 删除所有记录
			stat.executeUpdate("DELETE FROM studentinfo");

			// 插入一条新纪录
			stat.executeUpdate("INSERT INTO studentinfo(id, name, class, department,date,starttime,endtime,boxnum) "
					+ "VALUES ('090750101','Mary', '0901', '0','Information Management','1990-01-01','00:00:00','00:00:00','99')");

			ResultSet result = stat.executeQuery("SELECT * FROM studentinfo");
			if (result.next())
				System.out.println("Insert a new record with id="
						+ result.getString(1).trim() + ".");
			result.close();
			// stat.executeUpdate("DROP TABLE studentinfo");

			// 如果表users不存在则创建
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS users (username VARCHAR(10) NOT NULL, password VARCHAR(50), PRIMARY KEY (username))");
			System.out.println("Create table users successfully.");

			// 删除所有记录
			stat.executeUpdate("DELETE FROM users");

			// 插入一条新纪录
			stat.executeUpdate("INSERT INTO users(username, password) "
					+ "VALUES ('admin','admin')");

			result = stat.executeQuery("SELECT * FROM users");
			if (result.next())
				System.out.println("Insert a new record with username="
						+ result.getString(1).trim() + ".");
			result.close();

		} finally {
			conn.close();
		}
	}

}
