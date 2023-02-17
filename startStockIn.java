package warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;

public class startStockIn {

	StockInAWT stockInAWT;
	private ResultSet rs2 = null;
	private Connection con = null;
	private PreparedStatement pstmt,pstmt1,pstmt2 = null;
	
	private DBConnectionMgr pool;
	
	public startStockIn(DefaultTableModel model, String[] str){
		pool = DBConnectionMgr.getInstance();
		insert(model,str);
	}
	
	@SuppressWarnings("null")
	int index() {
		String indexsql = null;
		int indexarr[] = null;
		int index = 0;
		
		try {
			con = pool.getConnection();
			
			indexsql = "select STORED_IDX\r\n"
					+ "from stored_log";
			pstmt2 = con.prepareStatement(indexsql);
			rs2 = pstmt2.executeQuery(indexsql);
			
			for (int i = 0; i < rs2.getInt(1); i++) {
				indexarr[i] = rs2.getInt(1);
			}
			Arrays.sort(indexarr);
			System.out.println(indexarr);
			index = indexarr[indexarr.length];
			
			
			while (rs2.next()) {
				System.out.print("\t" + rs2.getInt(1));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs2.close();
//				pstmt.close();
//				con.close();
			} catch (Exception e2) {
			}
		}
		return index;
	}
	
	void insert(DefaultTableModel model, String[] str){
		String productsql,storedsql= null;
		
		try {
			con = pool.getConnection();
			
			productsql = "insert into product\r\n"
					+ " VALUES( '" + str[0] + "', '" + str[1] + "' , '" + str[2] + "' , '" + str[3] + "' , '" + str[4] + "' , '" + str[5] + "') ";
			storedsql = "insert into stored_log\r\n"
					+ " VALUES( " + index() + ", '"+ str[0] + "',"+ 1 + " , '2023-02-17' , '" + str[5] + "') ";
			
			pstmt = con.prepareStatement(productsql);
			pstmt1 = con.prepareStatement(storedsql);
			
			int rs = pstmt.executeUpdate(productsql);
			int rs1 = pstmt1.executeUpdate(storedsql);
			
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
}