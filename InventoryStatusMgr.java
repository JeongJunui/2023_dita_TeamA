package warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.DBConnectionMgr;

public class InventoryStatusMgr extends JPanel {
	private JTable table;
	private JScrollPane scrollPane;
	private String colNames[] = { "카테고리", "제품코드", "제품명", "재고수량", "고객번호", "비고" };
	private DefaultTableModel model = new DefaultTableModel(colNames, 0);
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DBConnectionMgr pool;
	StatisticsAWT statisticsAWT;
	//재고 현황 테이블 
	public InventoryStatusMgr(StatisticsAWT statisticsAWT) {
		this.statisticsAWT = statisticsAWT;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBounds(25, 160, 505, 280);

		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		add(scrollPane);
		pool = DBConnectionMgr.getInstance();
		select();
		
	}
	// sql 문
		private void select() {
			String sql = null;
			try {
				con = pool.getConnection();
				sql = "select p.CATEGORY, p.PROD_CODE, p.PROD_NAME, p.PROD_STOCK, m.MEMBER_IDX, t.OTHER\r\n"
						+ "from product p,member m,takeout_log t ";
				pstmt = con.prepareStatement(sql);
			
				rs = pstmt.executeQuery();

				while (rs.next()) {
					model.addRow(
							new Object[] { rs.getString("CATEGORY"), rs.getString("PROD_CODE"), rs.getString("PROD_NAME"),
									rs.getString("PROD_STOCK"), rs.getString("MEMBER_IDX"), rs.getString("OTHER") });
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					rs.close();
					pstmt.close();
					con.close();
				} catch (Exception e2) {

				}

			}
			statisticsAWT.p4.add(this);
		}
	
}
