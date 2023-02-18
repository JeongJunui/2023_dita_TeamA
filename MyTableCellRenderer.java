package warehouse;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class MyTableCellRenderer extends DefaultTableCellRenderer {
	private JTable table;
	private JTableHeader Header;

	public MyTableCellRenderer(JTable table) {
		this.table = table;
		Header = table.getTableHeader();
		Header.setBackground(Color.LIGHT_GRAY);
		Header.setReorderingAllowed(false);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (!isSelected) {
			if (row % 2 == 0) {
				cell.setBackground(Color.white);
			} else {
				cell.setBackground(Color.LIGHT_GRAY);
			}
		}
		return cell;
	}

}