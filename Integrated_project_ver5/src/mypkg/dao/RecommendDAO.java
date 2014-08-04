package mypkg.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import mypkg.vo.RecommendWebtoonVO;

public class RecommendDAO {
	private DataSource pool;

	// 생성자
	public RecommendDAO() {
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
	
	public RecommendWebtoonVO getRecommendWebtoonInfo(long currentUser_facebookID, String myWebtoon_title, 
				int otherWebtoon_id, int reserveValue, int keywordsCount, String recommender_matching_percent, 
				String relative_matching_percent) {
		RecommendWebtoonVO recommendWebtoon = null;
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "select w.webtoons_title, a.authors_name, w.webtoons_completed, "
					+ "w.webtoons_viewfree, w.webtoons_title_image, w.webtoons_url, "
					+ "w.webtoons_first_update "
					+ "from webtoons as w inner join webtoon_author_maps as wam "
					+ "on w.webtoons_id_pk = wam.webtoons_id_fk "
					+ "inner join authors as a on a.authors_id_pk = wam.authors_id_fk "
					+ "where webtoons_id_pk = " + otherWebtoon_id;

			ResultSet rset = stmt.executeQuery(sql);

			rset.next();
			String webtoons_title = rset.getString("webtoons_title");
			String authors_name = rset.getString("authors_name");
			String webtoons_completed = rset.getString("webtoons_completed");
			boolean webtoons_viewfree = rset.getBoolean("webtoons_viewfree");
			String webtoons_main_image = rset.getString("webtoons_title_image");
			String webtoons_url = rset.getString("webtoons_url");
			String webtoons_first_update = rset
					.getString("webtoons_first_update");

			recommendWebtoon = new RecommendWebtoonVO (otherWebtoon_id, webtoons_title, authors_name,
					webtoons_completed, webtoons_viewfree, webtoons_main_image,
					webtoons_url, webtoons_first_update, myWebtoon_title, 
					reserveValue, keywordsCount, recommender_matching_percent,
					relative_matching_percent);
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

		return recommendWebtoon;
	}
	
}
