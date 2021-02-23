package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class DatabaseManager extends JPanel {
	public DatabaseManager() {
	}

	public class DatabaseTableListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent e) {
			int row = e.getFirstRow();
			int col = e.getColumn();
			int keyCol = model.getKeyFiledColumn();

			if (col < 0)
				col = keyCol;

			// ���Գ��޸�������¼�
			if (e.getType() != TableModelEvent.UPDATE)
				return;

			Object value = model.getValueAt(row, col);

			Object keyId = model.getValueAt(row, keyCol);
			String fieldName = model.getFieldName(col);

			// ���������޸����ݿ�ķ����޸����ݿ��е���Ӧ����
			updateFieldValue(keyId, fieldName, value);
		}
	}

	private DatabaseTableModel model;
	protected boolean modifiable;
	protected JTable table;

	/*
	 * �������ڹ������ݿ����塣 ������ DatabaseTableModel model�����ڴ�������б�������ģ�� boolean
	 * modifiable���Ƿ�����޸����ݿ��е�����
	 */
	public void creatDatabaseTablePane(Container ground,
			DatabaseTableModel model) {

		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new BorderLayout(0, 0));

		this.model = model;
		model.addTableModelListener(new DatabaseTableListener());

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionInterval(0, 0);

		scrollPane.setViewportView(table);

		// ����������������Ҫ���õ�������
		ground.add(scrollPane, BorderLayout.CENTER);

	}

	protected void showRecordDetail(int selectedRow) {
		// TODO Auto-generated method stub

	}

	public void updateFieldValue(Object keyId, String fieldName, Object value) {
		// TODO Auto-generated method stub

	}

	public void addRow(Object[] rowData) {
		model.addRow(rowData);
		int nextRow = model.getRowCount();
		table.setRowSelectionInterval(nextRow - 1, nextRow - 1);
	}

	/**
	 * @return the model
	 */
	public DatabaseTableModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(DatabaseTableModel model) {
		this.model = model;
	}
}
