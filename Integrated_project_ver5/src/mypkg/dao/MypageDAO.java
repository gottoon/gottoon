package mypkg.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import mypkg.vo.MypageVO;

// 다 영규꺼 
public class MypageDAO {
	private DataSource pool;

	public MypageDAO() {
		try {
			InitialContext ctx = new InitialContext();
			pool = (DataSource) ctx.lookup("java:comp/env/jdbc/test_toondb");
			if (pool == null)
				throw new ServletException("pool error!!");
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	public MypageVO getNewOnOff(long users_facebookID) {
		Connection conn = null;
		Statement stmt = null;

		MypageVO onOff = null;
		
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlQuery = "select mypage_recommend_new_onoff from mypage where users_facebookID_fk="
					+ users_facebookID;
			ResultSet rset = stmt.executeQuery(sqlQuery);
			
			while (rset.next()) {
				int new_onoff = rset.getInt("mypage_recommend_new_onoff");
				
				onOff = new MypageVO(new_onoff);
			}
		} catch (SQLException ex) {
			Logger.getLogger(MypageDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(MypageDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
		
		return onOff;
	}

	public void setNewOnOff(long users_facebookID) {
		Connection conn = null;
		Statement stmt = null;
		
		MypageVO onOff = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			int new_onoff = 0;

			String sqlQuery = "select mypage_recommend_new_onoff from mypage where users_facebookID_fk="
					+ users_facebookID;
			ResultSet rset = stmt.executeQuery(sqlQuery);

			while (rset.next()) {
				new_onoff = rset.getInt("mypage_recommend_new_onoff");
			}

			if (new_onoff == 0) {
				sqlQuery = "update mypage set mypage_recommend_new_onoff=1 where users_facebookID_fk="
						+ users_facebookID;
				stmt.executeUpdate(sqlQuery);
			} else if (new_onoff == 1) {
				sqlQuery = "update mypage set mypage_recommend_new_onoff=0 where users_facebookID_fk="
						+ users_facebookID;
				stmt.executeUpdate(sqlQuery);
			}
		} catch (SQLException ex) {
			Logger.getLogger(MypageDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(MypageDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}
	
	public MypageVO getAuthorOnOff(long users_facebookID) {
		Connection conn = null;
		Statement stmt = null;

		MypageVO onOff = null;
		
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlQuery = "select mypage_recommend_author_onoff from mypage where users_facebookID_fk="
					+ users_facebookID;
			ResultSet rset = stmt.executeQuery(sqlQuery);
			
			while (rset.next()) {
				int author_onoff = rset.getInt("mypage_recommend_author_onoff");
				
				onOff = new MypageVO(author_onoff);
			}
		} catch (SQLException ex) {
			Logger.getLogger(MypageDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(MypageDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
		
		return onOff;
	}

	public void setAuthorOnOff(long users_facebookID) {
		Connection conn = null;
		Statement stmt = null;
		
		MypageVO onOff = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			int author_onoff = 0;

			String sqlQuery = "select mypage_recommend_author_onoff from mypage where users_facebookID_fk="
					+ users_facebookID;
			ResultSet rset = stmt.executeQuery(sqlQuery);

			while (rset.next()) {
				author_onoff = rset.getInt("mypage_recommend_author_onoff");
			}

			if (author_onoff == 0) {
				sqlQuery = "update mypage set mypage_recommend_author_onoff=1 where users_facebookID_fk="
						+ users_facebookID;
				stmt.executeUpdate(sqlQuery);
			} else if (author_onoff == 1) {
				sqlQuery = "update mypage set mypage_recommend_author_onoff=0 where users_facebookID_fk="
						+ users_facebookID;
				stmt.executeUpdate(sqlQuery);
			}
		} catch (SQLException ex) {
			Logger.getLogger(MypageDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(MypageDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}
}
