package business;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import view.DatabaseTableColumn;
import data.DatabaseAccess;

/* ��Student���������ݿ���н�������ɲ��롢ɾ�����޸ĺͲ���ѧ���ȹ��ܡ�
 * ע�⣺�����ȵ���open()�������������ݿ�����ӣ�Ȼ���ٵ�������������
 * ���Ӧ�õ���close()�����ر������ݿ�����ӡ�*/

public class Student {

	// ����ѧ����Ϣ�ı���
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

	// ���������ݿ������
	public void open() {
		try {
			databaseAccess = new DatabaseAccess();
		} catch (SQLException e) {
			System.out.println("���ܴ�student���ݿ⣡");
			e.printStackTrace();
		}
		initializeColumns();
	}

	// �ر����ݿ������
	public void close() {
		try {
			databaseAccess.close();
		} catch (SQLException e) {
			System.out.println("�ر�student���ݿ�ʱ��������");
			e.printStackTrace();
		}
	}

	// ��ʼ�����ݱ���ֶ���Ϣ
	private void initializeColumns() {

		columns = new DatabaseTableColumn[6]; // ����Ҫ��Table����ʾ���ֶ�����������
		try {
			Class StringClass = Class.forName("java.lang.String");
			Class BooleanClass = Class.forName("java.lang.Boolean");
			Class DateClass = Class.forName("java.util.Date");

			columns[0] = new DatabaseTableColumn("ѧ��", COL_ID, StringClass, 10,
					Types.VARCHAR);
			columns[1] = new DatabaseTableColumn("����", COL_NAME, StringClass,
					10, Types.VARCHAR);
			columns[2] = new DatabaseTableColumn("רҵ", COL_DEPARTMENT,
					StringClass, 20, Types.VARCHAR);
			columns[3] = new DatabaseTableColumn("����", COL_DATE, DateClass,
					12, Types.VARCHAR);
			columns[4] = new DatabaseTableColumn("�洢ʱ��", COL_STARTTIME, TimeClass,
					10, Types.TIME);
			columns[4] = new DatabaseTableColumn("ȡ��ʱ��", COL_ENDTIME,
					TimeClass, 20, Types.TIME);
			columns[5] = new DatabaseTableColumn("���",  COL_BOXNUM, StringClass, 10,
					Types.VARCHAR);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// ��ȡ�й����ݿ����У��ֶΣ���Ϣ
	public DatabaseTableColumn[] getColumns() {
		return columns;
	}

	// ��ȡ�ؼ��ֶε���Ϣ
	public String getKeyField() {
		return COL_ID;
	}

	// ����StudentRecord�������Ϣ�������ݿ������һ��ѧ����¼
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

	// ����StudentRecord�������Ϣ�������ݿ��и���һ��ѧ����¼
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

	// ��������ѧ������Ϣ
	// ѧ����Ϣ����ArrayList�С�
	// ����ÿһ��Ԫ��Ϊһ��Object[]��������ĳ�����columns.length��ͬ��
	public ArrayList findAll() throws Exception {
		String sql = "SELECT * FROM " + TABLE_NAME;
		ResultSet rs = null;

		try {
			rs = databaseAccess.query(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// �����ݿ��е�����װ��ArrayList���͵ı���data��
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

	// ��ѧ����¼������һ�����������С�
	// JTable������ģ����Ҫʹ�ö���������ʽ��ŵ�ѧ����Ϣ
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
