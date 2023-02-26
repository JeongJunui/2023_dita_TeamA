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
	private JTableHeader header;
	public MyTableCellRenderer(JTable table) {
		setHorizontalAlignment(SwingConstants.CENTER); // 테이블 중앙 정렬
		table.setIntercellSpacing(new Dimension(0,0));
	    table.setShowGrid(false);
		table.setShowVerticalLines(false); // 셀 세로줄 안보이게
		table.setShowHorizontalLines(false); // 셀 가로줄 안보이게
		table.getTableHeader().setReorderingAllowed(false); // 테이블 컬럼 이동 방지
			
		header = table.getTableHeader();
		header.setPreferredSize(new Dimension(0,25));
		header.setForeground(new Color(82,82,82));	
		header.setFont(new Font("", Font.PLAIN,14));
		
	}
	// 테이블 짝수, 홀수 라인마다 글꼴 및 색상 변경
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
