package mypkg.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import mypkg.vo.GenreVO;

public class GenreDAO {
	DataSource pool;

	// 생성자
	public GenreDAO() {
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

	// bj 장르 추가 7.18
	public int addGenre(String genre) {
		System.out.println("addGenre DAO 시작 ");
		System.out.println(genre);
		Connection conn = null;
		Statement stmt = null;
		int rset = 0;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlStr = "insert into genres (genres_name) select '"
					+ genre
					+ "' from dual where not exists (select * from keywords where keywords_name = '"
					+ genre + "')";

			rset = stmt.executeUpdate(sqlStr);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rset;
	}

	// findAllGenre -bj 7.18  // 희철 사용장르 불러오기
	public List<GenreVO> findAllGenre() {
		Connection conn = null;
		Statement stmt = null;

		ArrayList<GenreVO> GenreList = new ArrayList<GenreVO>();

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlStr = "SELECT * FROM genres";
			ResultSet rset = stmt.executeQuery(sqlStr);

			while (rset.next()) {
				int genres_id = rset.getInt("genres_id_pk");
				String genres_name = rset.getString("genres_name");

				GenreList.add(new GenreVO(genres_id, genres_name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
		return GenreList;
	}

}
