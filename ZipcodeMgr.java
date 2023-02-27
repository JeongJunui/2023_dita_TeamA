package warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class ZipcodeMgr {
	DBConnectionMgr pool;
	public ZipcodeMgr()
	{
		pool=DBConnectionMgr.getInstance();
	}
	public Vector<String> getSearchedResult(String keyword)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<String> vlist=new Vector<String>();
		try {
			con = pool.getConnection();
			sql = "select * from tblzipcode where area1 like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				String resS=rs.getString(1).trim();
				vlist.addElement(resS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
}
