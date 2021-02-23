package business;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import view.DatabaseTableColumn;
import data.DatabaseAccess;

/* 类Student负责与数据库进行交互，完成插入、删除、修改和查找学生等功能。
 * 注意：必须先调用open()方法建立与数据库的连接，然后再调用其他方法，
 * 最后应该调用close()方法关闭与数据库的连接。*/

public class Student {

	// 设置学生信息的表名
	private final static String TABLE_NAME = "studentinfo";

	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String  COL_DEPARTMENT= "departmen";
	public static final String COL_DATE = "date";
	public static final String COL_STARTTIME = "starttime";
	public static final String COL_ENDTIME = "endtime";
	public static final String COL_BOXNUM= "boxnum";

	private DatabaseAccess databaseAccess = null;
	private DatabaseTableColumn[] columns = null;

	// 建立与数据库的连接
	public void open() {
		try {
			databaseAccess = new DatabaseAccess();
		} catch (SQLException e) {
			System.out.println("不能打开student数据库！");
			e.printStackTrace();
		}
		initializeColumns();
	}

	// 关闭数据库的连接
	public void close() {
		try {
			databaseAccess.close();
		} catch (SQLException e) {
			System.out.println("关闭student数据库时发生错误！");
			e.printStackTrace();
		}
	}

	// 初始化数据表的字段信息
	private void initializeColumns() {

		columns = new DatabaseTableColumn[6]; // 根据要在Table中显示的字段数创建数组
		try {
			Class StringClass = Class.forName("java.lang.String");
			Class BooleanClass = Class.forName("java.lang.Boolean");
			Class DateClass = Class.forName("java.util.Date");

			columns[0] = new DatabaseTableColumn("学号", COL_ID, StringClass, 10,
					Types.VARCHAR);
			columns[1] = new DatabaseTableColumn("姓名", COL_NAME, StringClass,
					10, Types.VARCHAR);
			columns[2] = new DatabaseTableColumn("专业", COL_DEPARTMENT,
					StringClass, 20, Types.VARCHAR);
			columns[3] = new DatabaseTableColumn("日期", COL_DATE, DateClass,
					12, Types.VARCHAR);
			columns[4] = new DatabaseTableColumn("存储时间", COL_STARTTIME, TimeClass,
					10, Types.TIME);
			columns[4] = new DatabaseTableColumn("取出时间", COL_ENDTIME,
					TimeClass, 20, Types.TIME);
			columns[5] = new DatabaseTableColumn("柜号",  COL_BOXNUM, StringClass, 10,
					Types.VARCHAR);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 获取有关数据库表的列（字段）信息
	public DatabaseTableColumn[] getColumns() {
		return columns;
	}

	// 获取关键字段的信息
	public String getKeyField() {
		return COL_ID;
	}

	// 根据StudentRecord对象的信息，在数据库中添加一条学生记录
	public boolean add(StudentRecord student) {
		boolean result = false;

		String sql = "INSERT INTO " + TABLE_NAME
				+ "(id, name, department, date, starttime, endtime, boxnum) " + "VALUES ('"
				+ student.getId() + "','" + student.getName() + "', '"
				+ student.getDepartment() + "', '"
				+ student.getDate() + "','" + student.getStartTime()+ "','"
				+student.getDate() +"','" + student.getEndTime()
				+ "')";

		try {
			if (databaseAccess.update(sql) > 0)
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// 根据StudentRecord对象的信息，在数据库中更新一条学生记录
	public boolean update(StudentRecord student) {
		boolean result = false;

		String sql = "UPDATE studentinfo SET " + "name = '" + student.getName()
				+ "'," + "sex = " + String.valueOf(student.getSex()) + ","
				+ "birthday = '" + student.getBirthday() + "'," + "class = '"
				+ student.getStuClass() + "'," + "department = '"
				+ student.getDepartment() + "' " + "WHERE id = '"
				+ student.getId().trim() + "'";

		try {
			if (databaseAccess.update(sql) > 0)
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean deleteStudent(String stuID) {
		boolean result = false;

		String sql = "DELETE FROM studentinfo WHERE id = '" + stuID.trim()
				+ "'";

		try {

			if (databaseAccess.update(sql) > 0)
				result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public StudentRecord searchStudent(String stuID) {

		StudentRecord result = null;

		String sql = "SELECT * FROM studentinfo WHERE id = '" + stuID.trim()
				+ "'";

		try {

			ResultSet rs = databaseAccess.query(sql);

			if (rs.next()) {

				result = new StudentRecord();

				// studentinfo(id, name, class, sex, birthday, department)
				result.setId(rs.getString("id"));
				result.setName(rs.getString("name"));
				result.setStartTime(rs.getInt("starttime"));
				result.setBirthday(rs.getDate("date"));
				result.setStuBoxnum(rs.getString("boxnum"));
				result.setEndTime(rs.getString("endtime"));
				result.setDepartment(rs.getString("department"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	// 查找所有学生的信息
	// 学生信息放在ArrayList中。
	// 其中每一个元素为一个Object[]，该数组的长度与columns.length相同。
	public ArrayList findAll() throws Exception {
		String sql = "SELECT * FROM " + TABLE_NAME;
		ResultSet rs = null;

		try {
			rs = databaseAccess.query(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 将数据库中的数据装入ArrayList类型的变量data。
		ArrayList data = new ArrayList();
		while (rs.next()) {
			Object[] record = new Object[columns.length];
			for (int i = 0; i < columns.length; i++) {
				record[i] = rs.getObject(columns[i].getColField());
			}
			data.add(record);
		}
		rs.close();
		return data;
	}

	// 将学生记录放置在一个对象数组中。
	// JTable的数据模型需要使用对象数组形式存放的学生信息
	public Object[] recordToArray(StudentRecord record) {
		Object[] array = new Object[columns.length];
		for (int i = 0; i < columns.length; i++) {
			String fieldName = columns[i].getColField();
			if (fieldName.equals(COL_ID))
				array[i] = record.getId();
			else if (fieldName.equals(COL_NAME))
				array[i] = record.getName();
			else if (fieldName.equals(COL_STARTTIME)) 
					array[i] = Time.valueOf(false);
			else if (fieldName.equals(COL_ENDTIME)) 
					array[i] = Time.valueOf(false);
			else if (fieldName.equals(COL_DATE))
				array[i] = Date.valueOf(record.getBirthday());
			else if (fieldName.equals(COL_BOXNUM))
				array[i] = record.getBoxnum();
			else if (fieldName.equals(COL_DEPARTMENT))
				array[i] = record.getDepartment();
		}
		return array;
	}

}
