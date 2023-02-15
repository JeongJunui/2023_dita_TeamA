package warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Released {
	DBConnectionMgr pool;
	public Vector<ProductBean> loadWarehouseOut(String prodCode, String prodName)//출고 테이블 부르기
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<ProductBean> vlist=new Vector<ProductBean>();
		try {
			con = pool.getConnection();
			sql = "select * from PRODUCT where PROD_CODE = ? OR PROD_NAME = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, prodCode);
			pstmt.setString(2, prodName);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ProductBean bean=new ProductBean(rs.getString("PROD_CODE"), rs.getString("CATEGORY"),
						rs.getString("PROD_NAME"), rs.getString("PROD_SIZE"), rs.getString("PROD_COLOR"),
						rs.getInt("PROD_STOCK"));
				vlist.addElement(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	public Vector<ReleasedBean> outSearch(String keyword)//출고 검색
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<ReleasedBean> vlist=new Vector<ReleasedBean>();
		try {
			con = pool.getConnection();
			sql = "select * from TAKEOUT_LOG where PROD_CODE = ? or MEMBER_IDX = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ReleasedBean bean=new ReleasedBean(rs.getInt("TAKEOUT_IDX"), rs.getString("PRODUCT_CODE"),
						rs.getInt("MEMBER_IDX"), rs.getString("TAKEOUT_DATE"), rs.getInt("TAKEOUT_AMOUNT"),
						rs.getString("OTHER"));
				vlist.addElement(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	public boolean releasedStart(String prodCode, int memberIdx, int takeoutAmount, String other)//출고하기
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag=false;
		try {
			con = pool.getConnection();
			sql = "insert into TAKEOUT_LOG values (?,?,now(),?,?)";//mysql 안에서 현재시간 불러오는 건 now()
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, prodCode);
			pstmt.setInt(2, memberIdx);
			pstmt.setInt(3, takeoutAmount);
			pstmt.setString(4, other);
			int cnt=pstmt.executeUpdate();
			if(cnt==1) flag=true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
}
