package mypkg.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import mypkg.vo.UserVO;

public class UserDAO {
	private DataSource pool;

	// 생성자
	public UserDAO() {
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

	// soo 웹툰 추천
	public List<UserVO> getExceptBlacklistAllUsers() {
		List<UserVO> users = new ArrayList<UserVO>();

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "select * from users where users_blacklist = 0";

			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				long users_facebookID = rset.getLong("users_facebookID_pk");
				String userName = rset.getString("users_name");
				String userEmail = rset.getString("users_email");
				String userGrade = rset.getString("users_grade");
				boolean isBlacklist = false;

				UserVO user = new UserVO(users_facebookID, userName, userEmail,
						userGrade, isBlacklist);

				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return users;
	}

	// 7.18 영규꺼
	public UserVO findUserGrade(long users_facebookID) {
		Connection conn = null;
		Statement stmt = null;

		UserVO userGrade = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlQuery = "select users_grade from users where users_facebookID_pk="
					+ users_facebookID;
			ResultSet rset = stmt.executeQuery(sqlQuery);

			while (rset.next()) {
				String user_grade = rset.getString("users_grade");

				userGrade = new UserVO(user_grade);
			}
		} catch (SQLException ex) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
		return userGrade;
	}

	// 7.18 영규꺼
	public void setUserGrade(long users_facebookID, int SetGrade) {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlQuery = "update users set users_grade=" + SetGrade
					+ " where users_facebookID_pk=" + users_facebookID;

			stmt.executeUpdate(sqlQuery);

		} catch (SQLException ex) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}

	// soo 챠트용 7.18
	public List<UserVO> getAllUsers() {
		System.out.println("getAllUsers 시작");
		List<UserVO> users = new ArrayList<UserVO>();

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "select * from users";

			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				long users_facebookID = rset.getLong("users_facebookID_pk");
				String userPhoto = rset.getString("users_photo");
				String userName = rset.getString("users_name");
				String userEmail = rset.getString("users_email");
				String userGrade = rset.getString("users_grade");
				boolean isBlacklist = rset.getBoolean("users_blacklist");
				// if (rset.getInt("users_blacklist") == 1) {
				// isBlacklist = true;
				// }

				UserVO user = new UserVO(users_facebookID, userPhoto, userName,
						userEmail, userGrade, isBlacklist);

				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return users;
	}

	// bj 유저 추가 7.18
	public boolean addUser(long users_facebookID, String name, String email,
			String photoUrl) {
		System.out.println("addUser 시작 ");
		boolean checkSuccess = false;
		// String userName = "";
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlstr = "insert into users (users_facebookID_pk,users_photo,users_name,users_email,users_grade,users_blacklist) values ("
					+ users_facebookID
					+ ",'"
					+ photoUrl
					+ "','"
					+ name
					+ "','"
					+ email + "','1', false)";
			System.out.println(sqlstr);

			int rset = stmt.executeUpdate(sqlstr);

			if (rset == 1) {
				checkSuccess = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return checkSuccess;
	}

}
