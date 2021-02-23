package view;

import java.awt.Container;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JTable;

import business.Student;

public class StudentManager extends DatabaseManager {
	
	private Student student = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentManager frame = new StudentManager();
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
	public StudentManager() {
		student = new Student();
		student.open();
	}
	
	public StudentManager(Student mainStudent) {
		if (mainStudent == null){
			student = new Student();
			student.open();
		} else
			student = mainStudent;		
	}
	
	// 创建用于管理学生信息的Table面板
	public void createStudentTablePane(Container ground) throws Exception{
		ArrayList data = student.findAll();
		DatabaseTableColumn[] columns = student.getColumns();
		String keyField = student.getKeyField();
		setModel(new DatabaseTableModel(data,columns,keyField, false));
		creatDatabaseTablePane(ground, this.getModel());
	}

	// 关闭数据库连接
	public void close() {
		// student.close();		
	}
	
	public JTable getTable(){
		return this.table;
	}
}
