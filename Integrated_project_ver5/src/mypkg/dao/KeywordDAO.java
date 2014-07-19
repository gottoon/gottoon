package mypkg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import mypkg.vo.KeywordsVO;
import mypkg.vo.User_keyword_mapsVO;
import mypkg.vo.Webtoon_keyword_mapsVO;

import org.json.JSONObject;


public class KeywordDAO {
	private DataSource pool;

	public KeywordDAO() {
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

	public List<KeywordsVO> getAllKeywords() {
		System.out.println("getAllKeywords 시작 ");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<KeywordsVO> keywords = new ArrayList<KeywordsVO>();

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlStr = "select * from keywords";

			rset = stmt.executeQuery(sqlStr);

			while (rset.next()) {
				int keywords_id_pk = rset.getInt("keywords_id_pk");
				String keywords_name = rset.getString("keywords_name");
				KeywordsVO keywordVO = new KeywordsVO(keywords_id_pk,
						keywords_name);
				keywords.add(keywordVO);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return keywords;
	}

	public int addKeyword(String keyword) {
		System.out.println("addKeyword DAO 시작 ");
		System.out.println(keyword);
		Connection conn = null;
		Statement stmt = null;
		int rset = 0;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlStr = "insert into keywords (keywords_id_pk,keywords_name) select 801,'"
					+ keyword
					+ "' from dual where not exists (select * from keywords where keywords_name = '"
					+ keyword + "')";

			rset = stmt.executeUpdate(sqlStr);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rset;
	}

	public List<Webtoon_keyword_mapsVO> getKeywordByWebtoonID(int webtoonID) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<Webtoon_keyword_mapsVO> Webtoon_keyword_mapsVOs = new ArrayList<Webtoon_keyword_mapsVO>();
		try {

			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlStr = "select w.webtoons_title,k.keywords_name,wkm.* from webtoon_keyword_maps as wkm inner join webtoons as w on wkm.webtoons_id_fk = w.webtoons_id_pk  inner join keywords as k on wkm.keywords_id_fk = k.keywords_id_pk where w.webtoons_id_pk ="
					+ webtoonID;
			System.out.println(sqlStr);
			rset = stmt.executeQuery(sqlStr);

			while (rset.next()) {
				int webtoons_id_fk = rset.getInt("webtoons_id_fk");
				int keywords_id_fk = rset.getInt("keywords_id_fk");
				String webtoons_title = rset.getString("webtoons_title");
				String keywords_name = rset.getString("keywords_name");
				Webtoon_keyword_mapsVO webtoon_keyword_mapsVO = new Webtoon_keyword_mapsVO(
						webtoons_id_fk, keywords_id_fk, webtoons_title,
						keywords_name);
				Webtoon_keyword_mapsVOs.add(webtoon_keyword_mapsVO);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Webtoon_keyword_mapsVOs;
	}

	public List<User_keyword_mapsVO> getKeywordByUserID(long userID) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<User_keyword_mapsVO> User_keyword_mapsVOs = new ArrayList<User_keyword_mapsVO>();

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlStr = "select k.keywords_name,u.users_name,k.keywords_id_pk,w.webtoons_title,COUNT(*) from user_webtoon_maps as uwm inner join users as u on uwm.users_facebookID_fk = u.users_facebookID_pk inner join webtoons as w on uwm.webtoons_id_fk = w.webtoons_id_pk inner join webtoon_keyword_maps as wkm on w.webtoons_id_pk = wkm.webtoons_id_fk inner join keywords as k on k.keywords_id_pk = wkm.keywords_id_fk where u.users_facebookID_pk = "
					+ userID + " group by k.keywords_name";
			System.out.println(sqlStr);

			rset = stmt.executeQuery(sqlStr);

			while (rset.next()) {
				int keywords_id_pk = rset.getInt("keywords_id_pk");
				String keywords_name = rset.getString("keywords_name");
				int count = rset.getInt("COUNT(*)");
				String users_name = rset.getString("users_name");
				String webtoons_title = rset.getString("webtoons_title");

				User_keyword_mapsVO keyword_mapsVO = new User_keyword_mapsVO(
						keywords_id_pk, keywords_name, count, users_name,
						webtoons_title);
				User_keyword_mapsVOs.add(keyword_mapsVO);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return User_keyword_mapsVOs;
	}

}