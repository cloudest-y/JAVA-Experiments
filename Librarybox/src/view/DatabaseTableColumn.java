package view;

// 描述数据库字段（数据表中的列）信息
public class DatabaseTableColumn {

	private String colCaption;			// 表格列名（列标题）
	private String colField;			// 表列所对应的数据库表中的字段名
	private Class colType;				// 表列的类型所属的类
	private int width;					// 表列的宽度
	private int sqlType;				// 表列类型在SQL中的编码值
	
	
	public DatabaseTableColumn(String name, String field, Class type, int width, int sqlType){
		colCaption = name;
		colField = field;
		colType = type;
		this.width = width;
		this.sqlType = sqlType;
	}


	/**
	 * @return the colCaption
	 */
	public String getColCaption() {
		return colCaption;
	}


	/**
	 * @return the colField
	 */
	public String getColField() {
		return colField;
	}


	/**
	 * @return the colType
	 */
	public Class getColType() {
		return colType;
	}


	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}


	/**
	 * @return the sqlType
	 */
	public int getSqlType() {
		return sqlType;
	}
	
	
}
