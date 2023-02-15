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

public class HistoryMgr extends JPanel {
	private JTable table;
	private JScrollPane scrollPane;
	private String[] colNames={ "입고날짜", "카테고리", "제품코드", "입고수량", "고객번호", "비고" };
	private String[] colNames2={ "출고날짜", "카테고리", "제품코드", "출고수량", "고객번호", "비고" };
	private DefaultTableModel model = new DefaultTableModel(colNames, 0);
	private DefaultTableModel model2 = new DefaultTableModel(colNames2, 0);
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DBConnectionMgr pool;
	int reciept_releaseCheck;
	StatisticsAWT statisticsAWT;

	// 입출고 내역 테이블
	public HistoryMgr(StatisticsAWT statisticsAWT, int reciept_releaseCheck) {
		this.reciept_releaseCheck = reciept_releaseCheck;
		this.statisticsAWT = statisticsAWT;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBounds(25, 140, 505, 260);

		if (reciept_releaseCheck == 0) {
			table = new JTable(model);
			scrollPane = new JScrollPane(table);
			add(scrollPane);
			pool = DBConnectionMgr.getInstance();

		} else if (reciept_releaseCheck == 1) {
			table = new JTable(model2);
			scrollPane = new JScrollPane(table);
			add(scrollPane);
			pool = DBConnectionMgr.getInstance();
		}
		select();
	}

	// sql 문
	private void select() {
		String sql = null;

		if (reciept_releaseCheck == 0) { // 입고 내역
			try {
				con = pool.getConnection();
				sql = "select s.STORED_DATE, p.CATEGORY, p.PROD_CODE, s.STORED_STOCK, m.MEMBER_IDX, t.OTHER\r\n"
						+ "from member m, stored_log s, product p, takeout_log t";
				pstmt = con.prepareStatement(sql);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					model.addRow(new Object[] { rs.getString("STORED_DATE"), rs.getString("CATEGORY"),
							rs.getString("PROD_CODE"), rs.getString("STORED_STOCK"), rs.getString("MEMBER_IDX"),
							rs.getString("OTHER") });
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
			statisticsAWT.p2.add(this);
		} else if (reciept_releaseCheck == 1) { // 출고 내역
			try {
				con = pool.getConnection();
				sql = "select t.TAKEOUT_DATE, p.CATEGORY, p.PROD_CODE, t.TAKEOUT_AMOUNT, m.MEMBER_IDX, t.OTHER\r\n"
						+ "from member m, product p, takeout_log t";
				pstmt = con.prepareStatement(sql);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					model2.addRow(new Object[] { rs.getString("TAKEOUT_DATE"), rs.getString("CATEGORY"),
							rs.getString("PROD_CODE"), rs.getString("TAKEOUT_AMOUNT"), rs.getString("MEMBER_IDX"),
							rs.getString("OTHER") });
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
			statisticsAWT.p2.add(this);
		}
	}
}
