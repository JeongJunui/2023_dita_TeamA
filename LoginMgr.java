package warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginMgr {
	private DBConnectionMgr pool;
	
	public LoginMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	// 회원가입 
	public boolean insert(MemberBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert into member (joined_date,name,tel,address)values(now(),?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getTel());
			pstmt.setString(3, bean.getAddress());
			
			int cnt = pstmt.executeUpdate();
			if (cnt == 1) flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	// 회원가입 중복확인
	public  boolean  signUpChk(MemberBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = true;
		try {
			con = pool.getConnection();
			sql = "select tel from member "
					+ "where tel = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getTel());
			rs = pstmt.executeQuery();
			if(rs.next())
				flag = false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

	// 로그인 체크
	public boolean loginChk(String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select tel from member "
					+ "where tel = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pwd);
			rs = pstmt.executeQuery();
			if(rs.next())
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
}
