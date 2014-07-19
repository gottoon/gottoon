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

import mypkg.vo.AuthorVO;

public class AuthorDAO {
	DataSource pool;

	// 생성자
	public AuthorDAO() {
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

	//2014.07.11 soo 웹툰 상세정보 - 작가 이름 가져오기
	public List<AuthorVO> getAuthors(int webtoon_id) {
		List<AuthorVO> authors = new ArrayList<AuthorVO>();
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sql = "select a.authors_name from authors a "
					+ "inner join webtoon_author_maps wam "
					+ "on wam.authors_id_fk = a.authors_id_pk "
					+ "inner join webtoons w on wam.webtoons_id_fk = w.webtoons_id_pk "
					+ "where webtoons_id_pk = " + webtoon_id;
			
			ResultSet rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				String author = rset.getString("authors_name");
				AuthorVO authorVO = new AuthorVO(author);
				authors.add(authorVO);
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
		
		return authors;
	}
	
	// findAuthor - bj 7.18 
	public List<AuthorVO> findAuthor() {
		Connection conn = null;
		Statement stmt = null;
		
		ArrayList<AuthorVO> authors = new ArrayList<AuthorVO>();

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			// 평가한 웹툰 중 내림차순의 정렬로 가장 많은 표본의 작가 추출
			// "select 컬럼1, count(컬럼1) from 테이블명 group by 컬럼1 order by count(컬럼1) desc"
			// 이중 상위 몇개만?
			// 추천된 웹툰 중 추출한 작가와 비교하여 같으면 특정 표시
			String sqlStr = "SELECT DISTINCT * FROM authors";
			
			ResultSet rset = stmt.executeQuery(sqlStr);
			
			while (rset.next()) {
				int authorId = rset.getInt("authors_id_pk");
				String authorName = rset.getString("authors_name");
				
				authors.add(new AuthorVO(authorId, authorName));
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
		
		
		return authors;
	}
}