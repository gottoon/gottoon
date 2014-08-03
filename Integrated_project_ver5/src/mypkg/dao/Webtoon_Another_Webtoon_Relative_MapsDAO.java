package mypkg.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public class Webtoon_Another_Webtoon_Relative_MapsDAO {
	private DataSource pool;
	
	// 생성자
	public Webtoon_Another_Webtoon_Relative_MapsDAO() {
		try {
			InitialContext ctx = new InitialContext();
			pool = (DataSource)ctx.lookup("java:comp/env/jdbc/test_toondb");
			if (pool == null) throw new ServletException("pool error!!");
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	// soo 웹툰 추천 연관성
	public int getWebtoonsRelativeRate(int webtoonId, int anotherWebtoonId) {
		int relativeRate = 0;
		Connection conn = null;
		Statement stmt = null;
		String sql = null;
		
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			if (webtoonId < anotherWebtoonId) {
				sql = "select * from webtoon_another_webtoon_relative_maps "
						+ "where webtoons_id_fk = " + webtoonId
						+ " and another_webtoons_id_fk = " + anotherWebtoonId;
			} else {
				sql = "select * from webtoon_another_webtoon_relative_maps "
						+ "where webtoons_id_fk = " + anotherWebtoonId
						+ " and another_webtoons_id_fk = " + webtoonId;
			}

			ResultSet rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				relativeRate = rset.getInt("relative_rate");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return relativeRate;
	}
}
