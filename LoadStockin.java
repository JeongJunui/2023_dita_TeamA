package warehouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class LoadStockin extends JPanel implements MouseListener{
	JTable stockinTable;
	static String header[] = {"입고번호", "물품코드","카테고리", "물품이름", "사이즈", "색상", "입고수량"};
	String[] title ={"l.STORED_IDX", "p.PROD_CODE", "p.CATEGORY", "p.PROD_NAME", "p.PROD_SIZE", "p.PROD_COLOR", "l.STORED_STOCK"};
	
	DefaultTableModel model = new DefaultTableModel(header, 0){
	    @Override
	    public boolean isCellEditable(int row, int column) {
	        return column == 6; // 첫 번째 열만 수정 가능하도록 함
	    }
	};
	
	StockInAWT stockInAWT;
	private TableRowSorter<TableModel> sorter;
	MyTableCellRenderer myTableCellRenderer;
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
		myTableCellRenderer = new MyTableCellRenderer(stockinTable);
		try {
			stockinTable.setDefaultRenderer(Class.forName("java.lang.Object"), myTableCellRenderer);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//myTableCellRenderer.getTableCellRendererComponent(stockinTable, stockInAWT, getFocusTraversalKeysEnabled(), isFocusOwner(), mrow, col);
		
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
				model.rowsRemoved(null);
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
		String sql,sql2 = null;
		int rs1,rs2 = 0;
		DefaultTableModel model2 = (DefaultTableModel)stockinTable.getModel();
		int str2 = Integer.parseInt((String) model2.getValueAt(row, 6));
		String str3 = (String)model2.getValueAt(row, 0);
		String str4 = (String)model2.getValueAt(row, 1);
		
		try {
			con = pool.getConnection();
			
			sql = "UPDATE product \r\n"
					+ "SET PROD_STOCK = PROD_STOCK + " + str2 + "- \r\n"
					+ "(select STORED_STOCK\r\n"
					+ "from stored_log\r\n"
					+ "where STORED_IDX = " + str3 + "\r\n"
					+ ")\r\n"
					+ "WHERE PROD_CODE = '" + str4 +"'";
			pstmt = con.prepareStatement(sql);
			rs1 = pstmt.executeUpdate(sql);
			
			sql2 = "UPDATE stored_log SET STORED_STOCK = " + str2 + "\r\n"
					+ "WHERE STORED_IDX = '" + str3 + "'";
			pstmt = con.prepareStatement(sql2);
			rs2 = pstmt.executeUpdate(sql2);

			
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
		String sql, updatesql = null;
		String str = (String)model.getValueAt(row, 0);
		String str2 = (String)model.getValueAt(row, 1);
		int stock = Integer.parseInt(String.valueOf(model.getValueAt(row, 6)));
		int rs2 = 0;
		
		try {
			con = pool.getConnection();
			

			updatesql = "UPDATE product SET PROD_STOCK = PROD_STOCK - " + stock + "\r\n"
					+ "WHERE PROD_CODE = '" + str2 + "'";
			pstmt = con.prepareStatement(updatesql);
			int rs = pstmt.executeUpdate(updatesql);
			System.out.println(updatesql + "\nstock : " + stock);
			
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
	
	public void search(String string2) {
		
		sorter = new TableRowSorter<TableModel>(model);
        stockinTable.setRowSorter(sorter);
        
        if (string2.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            try {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + string2));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
	}
//		String sql = null;
//		
//        for (int i = 0; i < header.length; i++) {
//			if(string.equals(header[i])) {
//				System.out.println("콤보박스 일치" + string);
//				
//				try {
//					con = pool.getConnection();
//					sql = "SELECT l.stored_idx, p.PROD_CODE, p.CATEGORY, p.PROD_NAME, p.PROD_SIZE, p.PROD_COLOR, l.STORED_STOCK\r\n"
//							+ "FROM stored_log l, product p\r\n"
//							+ "WHERE l.PROD_CODE = p.PROD_CODE AND'"+ title[i] +"'='"+ string2 +"'\r\n"
//							+ "ORDER BY stored_idx DESC";
//					pstmt = con.prepareStatement(sql);
//				
//					rs = pstmt.executeQuery();
//					while (rs.next()) {
//						model.addRow(
//								new Object[] { rs.getString("STORED_IDX"),rs.getString("PROD_CODE"), rs.getString("CATEGORY"), rs.getString("PROD_NAME"),
//										rs.getString("PROD_SIZE"), rs.getString("PROD_COLOR"), rs.getInt("STORED_STOCK") });
//					}
//				} catch (Exception e) {
//					System.out.println(e.getMessage());
//				} finally {
//					try {
//						rs.close();
//						pstmt.close();
//						con.close();
//					} catch (Exception e2) {
//
//					}
//
//				}
//			}
//		}
//    }
	
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
