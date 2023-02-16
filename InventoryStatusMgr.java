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
	private String colNames[] = { "제품코드", "카테고리", "제품명", "제품사이즈", "제품색상", "재고수량" };
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
		setBounds(25, 30, 505, 275);

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
				sql = "SELECT PROD_CODE, CATEGORY, PROD_NAME, PROD_SIZE, PROD_COLOR, PROD_STOCK\r\n"
						+ "FROM product\r\n"
						+ "ORDER BY PROD_CODE DESC";
				pstmt = con.prepareStatement(sql);
			
				rs = pstmt.executeQuery();

				while (rs.next()) {
					model.addRow(
							new Object[] { rs.getString("PROD_CODE"), rs.getString("CATEGORY"), rs.getString("PROD_NAME"),
									rs.getString("PROD_SIZE"), rs.getString("PROD_COLOR"), rs.getInt("PROD_STOCK") });
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
			statisticsAWT.p5.add(this);
		}
	
}
