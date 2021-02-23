package view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

// 表格所使用的却是数据模型，可以自行扩展
public class DatabaseTableModel extends AbstractTableModel {

	private DatabaseTableColumn[] columns;
	private int keyCol;

	// 表格中的数据， ArrayList的元素类型时Object[]，长度为columnNames.length
	private ArrayList data;

	// 指明使用该模型的表格是否可修改表格单元，注意关键字段所在的列总是不可修改的。
	private boolean modifiable;

	public DatabaseTableModel(ArrayList data, DatabaseTableColumn[] columns,
			String keyField, boolean modifiable) {

		this.data = data;
		this.columns = columns;

		// 获得关键字段的表格列索引
		keyCol = 0;
		for (int i = 0; i < columns.length; i++) {
			if (keyField.equals(columns[i].getColField())) {
				keyCol = i;
				break;
			}
		}

		this.modifiable = modifiable;
	}

	// 返回表列数
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	// 返回表列显示的标题
	@Override
	public String getColumnName(int col) {
		return columns[col].getColCaption();
	}

	// 返回表列数据所属的数据类型
	@Override
	public Class getColumnClass(int col) {
		return columns[col].getColType();
	}

	// 返回表格单元是否可修改
	@Override
	public boolean isCellEditable(int row, int col) {
		if (col == keyCol)
			// 关键字段所在的列不可编辑
			return false;
		else
			return modifiable;
	}

	// 返回表的行数
	@Override
	public int getRowCount() {
		return data.size();
	}

	// 返回某个表格单元的数据
	@Override
	public Object getValueAt(int row, int col) {
		Object[] rowData = (Object[]) data.get(row);
		return rowData[col];
	}

	// 设置某个表格单元的数据
	@Override
	public void setValueAt(Object value, int row, int col) {
		Object[] rowData = (Object[]) data.get(row);
		rowData[col] = value;
		data.set(row, rowData);

		// 触发模型被改变的事件，以相应地改变表格外观
		fireTableCellUpdated(row, col);
	}

	// 返回表格第col列所对应的数据库字段名称
	public String getFieldName(int col) {
		return columns[col].getColField();
	}

	// 返回字段在表格中的列下标
	public int getFieldColumn(String field) {
		int col = -1;
		for (int i = 0; i < columns.length; i++) {
			if (field.equals(columns[i].getColField())) {
				col = i;
				break;
			}
		}
		return col;
	}

	// 返回关键字段在表格中的列下标
	public int getKeyFiledColumn() {
		return keyCol;
	}

	// 返回关键字段值为keyID的记录在表格中的行下标
	public int getKeyIdRow(Object keyID) {
		for (int i = 0; i < data.size(); i++) {
			Object[] rowData = (Object[]) data.get(i);
			if (rowData[keyCol].equals(keyID))
				return i;
		}
		return -1;
	}

	// 返回一行数据
	public Object[] getRowData(int row) {
		return (Object[]) data.get(row);
	}

	// 增加一行数据
	public void addRow(Object[] rowData) {
		data.add(rowData);
		fireTableRowsInserted(data.size(), data.size());
	}

	// 删除一行数据
	public void deleteRow(Object[] rowData, int row) {
		data.remove(rowData);
		fireTableRowsDeleted(row, row);
	}

	// 更新一行数据
	public void updateRow(Object[] rowDate, int row) {
		data.set(row, rowDate);
		fireTableRowsUpdated(row, row);
	}

}
