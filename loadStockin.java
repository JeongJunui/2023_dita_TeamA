package warehouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import ch08.interfaceEx2;


public class LoadStockin extends JPanel implements MouseListener{
	JTable stockinTable;
	static String header[] = {"입고번호", "물품코드","카테고리", "물품이름", "사이즈", "색상", "입고수량"};
	DefaultTableModel model = new DefaultTableModel(header, 0);
	StockInAWT stockInAWT;
	
	JScrollPane scrollPane;
	private ResultSet rs = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	
	private DBConnectionMgr pool;
	public int row,mrow = 0;
	public int col = 0;
	
	public LoadStockin(StockInAWT stockInAWT) {
		
		this.stockInAWT = stockInAWT;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBounds(25, 160, 505, 280);

		stockinTable = new JTable(model);
		
		stockinTable.getModel().addTableModelListener(new TableModelListener() {
			 
	        @Override
	        public void tableChanged(TableModelEvent tme) { 
	        	row = stockinTable.getSelectedRow();
	        	col = stockinTable.getSelectedColumn();
	        }
		});
		
		stockinTable.addMouseListener(this);
		scrollPane = new JScrollPane(stockinTable);
		add(scrollPane);
		pool = DBConnectionMgr.getInstance();
		
		select();
	}
	
	public void select() {
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "SELECT l.stored_idx, p.PROD_CODE, p.CATEGORY, p.PROD_NAME, p.PROD_SIZE, p.PROD_COLOR, l.STORED_STOCK\r\n"
					+ "FROM stored_log l, product p\r\n"
					+ "WHERE l.PROD_CODE = p.PROD_CODE\r\n"
					+ "ORDER BY stored_idx DESC";
			pstmt = con.prepareStatement(sql);
		
			rs = pstmt.executeQuery();

			while (rs.next()) {
				model.addRow(
						new Object[] { rs.getString("STORED_IDX"),rs.getString("PROD_CODE"), rs.getString("CATEGORY"), rs.getString("PROD_NAME"),
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
	
	public void correct(int row, int col) {
		String sql = null;
		int rs1 = 0;
		DefaultTableModel model2 = (DefaultTableModel)stockinTable.getModel();
		String str1 = (String)model2.getValueAt(row, 1);
		int str2 = Integer.parseInt((String) model2.getValueAt(row, 6));
		String str3 = (String)model2.getValueAt(row, 0);
		System.out.println(str1 + str2 + str3);
		
		try {
			con = pool.getConnection();
			sql = "UPDATE stored_log SET PROD_CODE = '" + str1 + "', STORED_STOCK = " + str2 + "\r\n"
					+ "WHERE STORED_IDX = '" + str3 + "'";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			rs1 = pstmt.executeUpdate(sql);

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				//rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {

			}

		}
	}
	
	public void delete(int row) {
		String sql = null;
		String str = (String)model.getValueAt(row, 0);
		int rs2 = 0;
		try {
			con = pool.getConnection();
			sql = "DELETE FROM stored_log \r\n"
				+ "WHERE STORED_IDX = '" + str + "'";
			pstmt = con.prepareStatement(sql);
			rs2 = pstmt.executeUpdate(sql);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {

			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
//		JTable stockinTable = (JTable)e.getSource();
		mrow = stockinTable.getSelectedRow();
//		System.out.println("액션" + row);
//		col = stockinTable.getSelectedColumn();
//		
//		System.out.println("dlrp" + model.getValueAt(row,col));
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
		
}
