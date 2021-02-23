package view;

// �������ݿ��ֶΣ����ݱ��е��У���Ϣ
public class DatabaseTableColumn {

	private String colCaption;			// ����������б��⣩
	private String colField;			// ��������Ӧ�����ݿ���е��ֶ���
	private Class colType;				// ���е�������������
	private int width;					// ���еĿ��
	private int sqlType;				// ����������SQL�еı���ֵ
	
	
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
