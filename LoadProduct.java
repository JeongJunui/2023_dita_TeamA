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


public class LoadProduct extends JPanel implements MouseListener{
	JTable stockinTable;
	static String header[] = {"제품번호", "카테고리", "물품이름", "사이즈", "색상", "제품수량"};
	DefaultTableModel model = new DefaultTableModel(header, 0);
	StockInAWT stockInAWT;
	
	JScrollPane scrollPane;
	private ResultSet rs = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	
	private DBConnectionMgr pool;
	public int row,mrow = 0;
	public int col = 0;
	
	public LoadProduct(StockInAWT stockInAWT) {
		
		this.stockInAWT = stockInAWT;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBounds(0, 0, 505, 230);

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
			sql = "select *\r\n"
				+ "from product p \r\n"
				+ "order by PROD_CODE asc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				model.addRow(
						new Object[] { rs.getString("PROD_CODE"),rs.getString("CATEGORY"), rs.getString("PROD_NAME"),
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
		StockInAWT.pp1.add(this);
	}
	
	public void correct(int row, int col) {
		String sql = null;
		int rs2 = 0;
		DefaultTableModel model2 = (DefaultTableModel)stockinTable.getModel();
		
		
		String[] str = new String[6];
		for(int i = 0; i < 6; i++) {
			str[i] = (String)model2.getValueAt(row, i);
		}
		
		try {
			con = pool.getConnection();
			sql = "UPDATE product SET CATEGORY = '" + str[1] + "', PROD_NAME = '" + str[2] + "',\r\n"
					+ "PROD_SIZE = '" + str[3] + "', PROD_COLOR = '" + str[4] + "', PROD_STOCK = " + Integer.parseInt(str[5]) + "\r\n"
					+ "WHERE PROD_CODE = '" + Integer.parseInt(str[0]) + "'";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			rs2 = pstmt.executeUpdate(sql);
			
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
			sql = "DELETE FROM product \r\n"
				+ "WHERE PROD_CODE = '" + str + "'";
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
		mrow = stockinTable.getSelectedRow();
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
