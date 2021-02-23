package view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

// �����ʹ�õ�ȴ������ģ�ͣ�����������չ
public class DatabaseTableModel extends AbstractTableModel {

	private DatabaseTableColumn[] columns;
	private int keyCol;

	// ����е����ݣ� ArrayList��Ԫ������ʱObject[]������ΪcolumnNames.length
	private ArrayList data;

	// ָ��ʹ�ø�ģ�͵ı���Ƿ���޸ı��Ԫ��ע��ؼ��ֶ����ڵ������ǲ����޸ĵġ�
	private boolean modifiable;

	public DatabaseTableModel(ArrayList data, DatabaseTableColumn[] columns,
			String keyField, boolean modifiable) {

		this.data = data;
		this.columns = columns;

		// ��ùؼ��ֶεı��������
		keyCol = 0;
		for (int i = 0; i < columns.length; i++) {
			if (keyField.equals(columns[i].getColField())) {
				keyCol = i;
				break;
			}
		}

		this.modifiable = modifiable;
	}

	// ���ر�����
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	// ���ر�����ʾ�ı���
	@Override
	public String getColumnName(int col) {
		return columns[col].getColCaption();
	}

	// ���ر���������������������
	@Override
	public Class getColumnClass(int col) {
		return columns[col].getColType();
	}

	// ���ر��Ԫ�Ƿ���޸�
	@Override
	public boolean isCellEditable(int row, int col) {
		if (col == keyCol)
			// �ؼ��ֶ����ڵ��в��ɱ༭
			return false;
		else
			return modifiable;
	}

	// ���ر������
	@Override
	public int getRowCount() {
		return data.size();
	}

	// ����ĳ�����Ԫ������
	@Override
	public Object getValueAt(int row, int col) {
		Object[] rowData = (Object[]) data.get(row);
		return rowData[col];
	}

	// ����ĳ�����Ԫ������
	@Override
	public void setValueAt(Object value, int row, int col) {
		Object[] rowData = (Object[]) data.get(row);
		rowData[col] = value;
		data.set(row, rowData);

		// ����ģ�ͱ��ı���¼�������Ӧ�ظı������
		fireTableCellUpdated(row, col);
	}

	// ���ر���col������Ӧ�����ݿ��ֶ�����
	public String getFieldName(int col) {
		return columns[col].getColField();
	}

	// �����ֶ��ڱ���е����±�
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

	// ���عؼ��ֶ��ڱ���е����±�
	public int getKeyFiledColumn() {
		return keyCol;
	}

	// ���عؼ��ֶ�ֵΪkeyID�ļ�¼�ڱ���е����±�
	public int getKeyIdRow(Object keyID) {
		for (int i = 0; i < data.size(); i++) {
			Object[] rowData = (Object[]) data.get(i);
			if (rowData[keyCol].equals(keyID))
				return i;
		}
		return -1;
	}

	// ����һ������
	public Object[] getRowData(int row) {
		return (Object[]) data.get(row);
	}

	// ����һ������
	public void addRow(Object[] rowData) {
		data.add(rowData);
		fireTableRowsInserted(data.size(), data.size());
	}

	// ɾ��һ������
	public void deleteRow(Object[] rowData, int row) {
		data.remove(rowData);
		fireTableRowsDeleted(row, row);
	}

	// ����һ������
	public void updateRow(Object[] rowDate, int row) {
		data.set(row, rowDate);
		fireTableRowsUpdated(row, row);
	}

}
