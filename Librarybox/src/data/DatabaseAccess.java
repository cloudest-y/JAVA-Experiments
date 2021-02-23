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

		// 通过配置文件设置连接属性

		// 读取属性文件database.properties
		Properties props = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream("database.properties");
			props.load(in);
			in.close();
		} catch (FileNotFoundException e1) {
			System.out.println("找不到数据库连接属性文件：database.properties！");
			e1.printStackTrace();
		} catch (IOException e1) {
			// 读取文件发生异常
			e1.printStackTrace();
		}

		String drivers = props.getProperty("jdbc.drivers");
		if (drivers != null) {
			// 检查数据库驱动程序是否配置正确
			try {
				// 查找用于JDBC驱动的类，并向驱动器管理器注册该数据库驱动程序
				Class.forName(drivers);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("没有发现JDBC驱动程序：" + drivers + "！");
				e.printStackTrace();
			}
			System.setProperty("jdbc.drivers", drivers);
		}

		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");

		// 建立数据库连接
		/**
		 * Gets a connection from the properties specified in the file
		 * database.properties
		 * 
		 * @return the database connection
		 */
		connection = DriverManager.getConnection(url, username, password);
		System.out.println("成功连接数据库。");

		// 如果连接成功则检测是否有警告信息
		SQLWarning warning = connection.getWarnings();
		while (warning != null) {
			System.out.println(warning.getMessage());
			warning = warning.getNextWarning();
		}

		// 创建一个用于执行简单SQL的语句对象
		statement = connection.createStatement();
	}

	public void close() throws SQLException {
		// 关闭连接
		if (connection != null) {
			connection.close();
			System.out.println("成功关闭数据库连接。");
		}

	}

	/*
	 * 利用一条SQL语句执行数据库查询操作。参数sql表示SQL查询语句，例如“SELECT * FROM student”， 返回查询结果集。
	 */
	public ResultSet query(String sql) throws SQLException {
		return statement.executeQuery(sql);
	}

	/*
	 * 利用一条SQL语句执行数据库更新操作（插入、修改和删除）。参数sql表示SQL查询语句，例如“DELETE FROM student”，
	 * 返回影响结果集的记录数。
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

