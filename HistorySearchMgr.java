package warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import net.DBConnectionMgr;

public class HistorySearchMgr extends JPanel {
	private JTable table;
	private JScrollPane scrollPane;
	private String[] colNames = { "입고날짜", "카테고리", "제품코드", "입고수량", "고객번호" };
	private String[] colNames2 = { "출고날짜", "카테고리", "제품코드", "출고수량", "고객번호", "비고" };
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DBConnectionMgr pool;
	int reciept_releaseCheck;
	String cbText, tfText;
	StatisticsAWT statisticsAWT;

	// 입출고 내역 테이블
	public HistorySearchMgr(StatisticsAWT statisticsAWT, int reciept_releaseCheck, String cbText, String tfText) {
		this.reciept_releaseCheck = reciept_releaseCheck;
		this.statisticsAWT = statisticsAWT;
		this.cbText = cbText;
		this.tfText = tfText;

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBounds(25, 5, 505, 260);

		if (reciept_releaseCheck == 0) {
			model = new DefaultTableModel(colNames, 0) { // 테이블 수정 불가
				@Override
				public boolean isCellEditable(int row, int column) {
					if (column >= 0) {
						return false;
					} else {
						return true;
					}
				}
			};

			table = new JTable(model);
	
			TableCellRenderer renderer = new MyTableCellRenderer(table);
			try {
				table.setDefaultRenderer(Class.forName("java.lang.Object"), renderer);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			scrollPane = new JScrollPane(table);
			add(scrollPane);
			pool = DBConnectionMgr.getInstance();

		} else if (reciept_releaseCheck == 1) {
			model2 = new DefaultTableModel(colNames2, 0) { // 테이블 수정 불가
				@Override
				public boolean isCellEditable(int row, int column) {
					if (column >= 0) {
						return false;
					} else {
						return true;
					}
				}
			};
			
			table = new JTable(model2);
			TableCellRenderer renderer = new MyTableCellRenderer(table);
			try {
				table.setDefaultRenderer(Class.forName("java.lang.Object"), renderer);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			scrollPane = new JScrollPane(table);
			add(scrollPane);
			pool = DBConnectionMgr.getInstance();
		}
		select();
	}

	// 조회하기
	public void select() {
		String sql = null;

		if (reciept_releaseCheck == 0) { // 입고 내역
			try {
				con = pool.getConnection();
				sql = "SELECT s.STORED_DATE, p.CATEGORY, s.PROD_CODE, s.STORED_STOCK, s.MEMBER_IDX\r\n"
						+ "FROM stored_log s, product p, member m\r\n" + "where s.PROD_CODE = p.PROD_CODE\r\n"
						+ "	and s.MEMBER_IDX = m.MEMBER_IDX and";

				if (cbText.equals("제품코드")) {
					pstmt = con.prepareStatement(
							sql + " s.PROD_CODE LIKE '" + tfText + "%'" + "ORDER BY s.STORED_DATE DESC");
				} else if (cbText.equals("고객번호")) {
					int tfText2 = Integer.parseInt(tfText);
					pstmt = con
							.prepareStatement(sql + " m.MEMBER_IDX='" + tfText2 + "'" + "ORDER BY s.STORED_DATE DESC");
				}
				rs = pstmt.executeQuery();

				while (rs.next()) {
					model.addRow(new Object[] { rs.getDate("STORED_DATE"), rs.getString("CATEGORY"),
							rs.getString("PROD_CODE"), rs.getInt("STORED_STOCK"), rs.getInt("MEMBER_IDX") });
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
			statisticsAWT.p3.add(this);
		} else if (reciept_releaseCheck == 1) { // 출고 내역
			try {
				con = pool.getConnection();
				sql = "SELECT t.TAKEOUT_DATE, p.CATEGORY, p.PROD_CODE, t.TAKEOUT_AMOUNT, t.MEMBER_IDX, t.OTHER\r\n"
						+ "FROM takeout_log t, product p, member m\r\n" + "where t.PROD_CODE = p.PROD_CODE\r\n"
						+ "	and t.MEMBER_IDX = m.MEMBER_IDX and";

				if (cbText.equals("제품코드")) {
					pstmt = con.prepareStatement(
							sql + " t.PROD_CODE LIKE '" + tfText + "%'" + "ORDER BY t.TAKEOUT_DATE DESC");
				} else if (cbText.equals("고객번호")) {
					int tfText2 = Integer.parseInt(tfText);
					pstmt = con
							.prepareStatement(sql + " m.MEMBER_IDX='" + tfText2 + "'" + "ORDER BY t.TAKEOUT_DATE DESC");
				}
				rs = pstmt.executeQuery();

				while (rs.next()) {
					model2.addRow(new Object[] { rs.getDate("TAKEOUT_DATE"), rs.getString("CATEGORY"),
							rs.getString("PROD_CODE"), rs.getInt("TAKEOUT_AMOUNT"), rs.getInt("MEMBER_IDX"),
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
			statisticsAWT.p3.add(this);
		}
	}
}
