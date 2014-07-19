package mypkg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.json.JSONObject;

import mypkg.vo.GenreVO;
import mypkg.vo.UserGenreMapsVO;
import mypkg.vo.WebtoonVO;

public class User_Genre_MapsDAO {
	private DataSource pool;

	// 생성자
	public User_Genre_MapsDAO() {
		try {
			InitialContext ctx = new InitialContext();
			pool = (DataSource) ctx.lookup("java:comp/env/jdbc/test_toondb");
			if (pool == null)
				throw new ServletException("pool error!!");
		} catch (NamingException e) {
			Logger.getLogger(User_Webtoon_MapsDAO.class.getName()).log(
					Level.SEVERE, null, e);
		} catch (ServletException e) {
			Logger.getLogger(User_Webtoon_MapsDAO.class.getName()).log(
					Level.SEVERE, null, e);
		}
	}

	// soo 웹툰 추천
	public List<UserGenreMapsVO> getAllSelectedGenres(long users_facebookID) {
		List<UserGenreMapsVO> userGenres = new ArrayList<UserGenreMapsVO>();
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "select * from user_genre_maps where users_facebookID_fk ="
					+ users_facebookID;

			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				int genreId = rset.getInt("genres_id_fk");
				UserGenreMapsVO userGenre = new UserGenreMapsVO(users_facebookID, genreId);
				userGenres.add(userGenre);
			}
		} catch (SQLException e) {
			Logger.getLogger(User_Webtoon_MapsDAO.class.getName()).log(
					Level.SEVERE, null, e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				Logger.getLogger(User_Webtoon_MapsDAO.class.getName()).log(
						Level.SEVERE, null, e);
			}
		}
		return userGenres;
	}
}
