package warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Released {
	DBConnectionMgr pool;
	public void loadWarehouseOut(String prodCode, String prodName)//출고 테이블 부르기
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<ReleasedBean> vlist=new Vector<ReleasedBean>();
		try {
			con = pool.getConnection();
			sql = "select * from PRODUCT where ? = PROD_CODE OR ? = PROD_NAME";
			pstmt = con.prepareStatement(prodCode);
			pstmt.setString(1, sql);
			pstmt.setString(2, prodName);
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return;
	}
	public void outSearch()//출고 검색
	{}
	public void releasedStart()//출고하기
	{}
}
