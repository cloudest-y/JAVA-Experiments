package conn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentInfo {
	private String id;
	private String name;
	private String stuClass;
	private int sex; // 0 - Female, 1 - Male
	private String birthday;
	private String department;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the stdClass
	 */
	public String getStuClass() {
		return stuClass;
	}

	/**
	 * @param stdClass
	 *            the stdClass to set
	 */
	public void setStuClass(String stdClass) {
		this.stuClass = stdClass;
	}

	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	
	private static Connection conn;

	public static boolean addStudent(StudentInfo student) {

		boolean result = false;

		String sql = "INSERT INTO studentinfo(id, name, class, sex, birthday, department) "
				+ "VALUES ('"
				+ student.getId()
				+ "','"
				+ student.getName()
				+ "', '"
				+ student.getStuClass()
				+ "', '"
				+ String.valueOf(student.getSex())
				+ "','"
				+ student.getBirthday()
				+ "','"
				+ student.getDepartment()
				+ "')";

		try {
			conn = ConnectDB.getConnection();

			Statement stat = conn.createStatement();

			int r = stat.executeUpdate(sql);

			if (r > 0)
				result = true;
			
			stat.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public static boolean deleteStudent(String stuID){
		boolean result = false;

		String sql = "DELETE FROM studentinfo WHERE id = '" + stuID.trim() +"'";

		try {
			conn = ConnectDB.getConnection();

			Statement stat = conn.createStatement();

			int r = stat.executeUpdate(sql);

			if (r > 0)
				result = true;
			
			stat.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public static StudentInfo searchStudent(String stuID){
		
		StudentInfo result = null;

		String sql = "SELECT * FROM studentinfo WHERE id = '" + stuID.trim() +"'";

		try {
			conn = ConnectDB.getConnection();

			Statement stat = conn.createStatement();

			ResultSet rs = stat.executeQuery(sql);

			
			if (rs.next()){
				
				result = new StudentInfo();
			
				// studentinfo(id, name, class, sex, birthday, department)
				result.setId(rs.getString("id"));
				result.setName(rs.getString("name"));
				result.setSex(rs.getInt("sex"));
				result.setBirthday(rs.getString("birthday"));
				result.setStuClass(rs.getString("class"));
				result.setDepartment(rs.getString("department"));				
				
			}

			
			stat.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
		
	}

	public static boolean updateStudent(StudentInfo stu) {
		boolean result = false;

		String sql = "UPDATE studentinfo SET " +
				"name = '" + stu.getName() + "'," +
				"sex = " + String.valueOf(stu.getSex()) + "," +
				"birthday = '" + stu.getBirthday() + "'," +
				"class = '" + stu.getStuClass() + "'," +
				"department = '" +stu.getDepartment() + "' " +
				"WHERE id = '" + stu.getId().trim() +"'";
		
		try {
			conn = ConnectDB.getConnection();

			Statement stat = conn.createStatement();

			int r = stat.executeUpdate(sql);

			if (r > 0)
				result = true;
			
			stat.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	
	
}
