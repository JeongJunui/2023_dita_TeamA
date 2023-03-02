package warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class CheckMemberIdx {

	StockInAWT stockInAWT;
	private ResultSet rs = null;
	private Connection con = null;
	private PreparedStatement pstmt,pstmt1,pstmt2 = null;
	
	private DBConnectionMgr pool;
	
	public CheckMemberIdx(){
		pool = DBConnectionMgr.getInstance();
	}
	
	public boolean check(String idx) {
		String sql2 = null;
		List<String> checkList = new ArrayList<String>();
		System.out.println("½ÇÇà--------------------------------------");
		try {
			con = pool.getConnection();
			sql2 = "SELECT member_idx\r\n"
					+ "FROM member";
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				checkList.add(rs.getString("MEMBER_IDX"));
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
		System.out.println(checkList);
		
		if(checkList.contains(idx))
			return true;
		else				
			return false;
	}
}
