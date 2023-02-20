package warehouse;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class MyTableCellRenderer extends DefaultTableCellRenderer {
	private JTable table;
	private JTableHeader Header;

	public MyTableCellRenderer(JTable table) {
		this.table = table;
		this.setHorizontalAlignment(SwingConstants.CENTER); // 테이블 중앙 정렬
		
		table.setIntercellSpacing(new Dimension(0,0));

	    table.setShowGrid(false);
		table.setShowVerticalLines(false); // 셀 세로줄 안보이게
		table.setShowHorizontalLines(false); // 셀 가로줄 안보이게
		table.getTableHeader().setReorderingAllowed(false); // 테이블 컬럼 이동 방지
		
		Header = table.getTableHeader();
		Header.setPreferredSize(new Dimension(0,22));
		Header.setBackground(new Color(188,215,238));
		Header.setForeground(new Color(82,82,82));
		Header.setFont(new Font("", Font.PLAIN,14));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (!isSelected) {
			if (row % 2 == 0) {
				cell.setFont(new Font("", Font.PLAIN,13));
				cell.setBackground(Color.white);
			} else {
				cell.setBackground(new Color(222,235,247));
				cell.setFont(new Font("", Font.PLAIN,13));
			}
		}
		return cell;
	}

}
