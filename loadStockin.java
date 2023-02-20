package warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class loadStockin extends JPanel{
	JTable stockinTable;
	static String header[] = {"입고번호","물품코드","카테고리", "물품이름", "사이즈", "색상", "입고수량"};
	DefaultTableModel model = new DefaultTableModel(header, 0);
	StockInAWT stockInAWT;
	
	JScrollPane scrollPane;
	private ResultSet rs = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	
	private DBConnectionMgr pool;
	
	public loadStockin(StockInAWT stockInAWT) {
		this.stockInAWT = stockInAWT;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBounds(25, 160, 505, 280);

		stockinTable.addMouseListener(null);
		stockinTable = new JTable(model);
		scrollPane = new JScrollPane(stockinTable);
		add(scrollPane);
		pool = DBConnectionMgr.getInstance();
		select();
	}
	
	private void select() {
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "SELECT l.STORED_IDX, p.PROD_CODE, p.CATEGORY, p.PROD_NAME, p.PROD_SIZE, p.PROD_COLOR, l.STORED_STOCK\r\n"
					+ "FROM stored_log l, product p\r\n"
					+ "WHERE l.PROD_CODE = p.PROD_CODE\r\n"
					+ "ORDER BY l.STORED_IDX";
			pstmt = con.prepareStatement(sql);
		
			rs = pstmt.executeQuery();

			while (rs.next()) {
				model.addRow(
						new Object[] { rs.getString("STORED_IDX"), rs.getString("PROD_CODE"), rs.getString("CATEGORY"), rs.getString("PROD_NAME"),
								rs.getString("PROD_SIZE"), rs.getString("PROD_COLOR"), rs.getInt("STORED_STOCK") });
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
		StockInAWT.p4.add(this);
	}
}
