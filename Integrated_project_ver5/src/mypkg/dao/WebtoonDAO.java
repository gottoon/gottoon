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

import org.json.JSONException;
import org.json.JSONObject;

import mypkg.vo.AuthorVO;
import mypkg.vo.UserWebtoonMapsVO;
import mypkg.vo.WebtoonVO;
import mypkg.vo.Webtoon_keyword_mapsVO;

public class WebtoonDAO {
	private DataSource pool;

	// 생성자
	public WebtoonDAO() {
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

	// 2014.07.15 soo 추천 웹툰 정보 가져오기 (수정)
	public WebtoonVO findWebtoon(int webtoon_id) {
		WebtoonVO webtoon = null;
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "select w.webtoons_title, a.authors_name, w.webtoons_completed, "
					+ "w.webtoons_viewfree, w.webtoons_main_image, w.webtoons_url, "
					+ "w.webtoons_first_update "
					+ "from webtoons as w inner join webtoon_author_maps as wam "
					+ "on w.webtoons_id_pk = wam.webtoons_id_fk "
					+ "inner join authors as a on a.authors_id_pk = wam.authors_id_fk "
					+ "where webtoons_id_pk = " + webtoon_id;

			ResultSet rset = stmt.executeQuery(sql);

			rset.next();
			String webtoons_title = rset.getString("webtoons_title");
			String authors_name = rset.getString("authors_name");
			String webtoons_completed = rset.getString("webtoons_completed");
			boolean webtoons_viewfree = rset.getBoolean("webtoons_viewfree");
			String webtoons_main_image = rset.getString("webtoons_main_image");
			String webtoons_url = rset.getString("webtoons_url");
			String webtoons_first_update = rset
					.getString("webtoons_first_update");

			webtoon = new WebtoonVO(webtoon_id, webtoons_title, authors_name,
					webtoons_completed, webtoons_viewfree, webtoons_main_image,
					webtoons_url, webtoons_first_update);
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

		return webtoon;
	}

	// 2014.07.11 soo 웹툰 상세보기 정보 뽑아오기 (웹툰 본거 or 찜한거 - 별점 가져오기)
	public WebtoonVO getMyWebtoonInfo(long curruntUser_facebookID,
			int webtoon_id) {
		WebtoonVO webtoonInfo = null;
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "select g.genres_name, w.webtoons_title, w.webtoons_summary, "
					+ "w.webtoons_update_days, w.webtoons_completed, w.webtoons_viewfree, "
					+ "w.webtoons_professional, w.webtoons_pgrating, w.webtoons_publisher, "
					+ "w.webtoons_average_rate, w.webtoons_main_image, w.webtoons_url, "
					+ "w.webtoons_first_update, uwm.user_webtoon_rate "
					+ "from webtoons w inner join genres g on w.genre_id_fk = g.genres_id_pk "
					+ "inner join user_webtoon_maps as uwm on uwm.webtoons_id_fk = w.webtoons_id_pk "
					+ "where w.webtoons_id_pk = "
					+ webtoon_id
					+ " and uwm.users_facebookID_fk = "
					+ curruntUser_facebookID;

			// 장르, (중분류), 타이틀, 작가이름, 줄거리, 연재요일, 완결유무, 유/무료,
			// 프로/아마, 관람등급, 제공처, 평균평점, 메인 이미지(07.21 추가), (썸네일)
			// 주소, 연재시작일, (댓글), (좋아요)

			// 중분류, 썸네일, 댓글, 좋아요

			ResultSet rset = stmt.executeQuery(sql);

			rset.next();
			String genres_name = rset.getString("genres_name");
			String webtoons_title = rset.getString("webtoons_title");
			String webtoons_summary = rset.getString("webtoons_summary");
			String webtoons_update_days = rset
					.getString("webtoons_update_days");
			String webtoons_completed = rset.getString("webtoons_completed");
			boolean webtoons_viewfree = rset.getBoolean("webtoons_viewFree");
			boolean webtoons_professional = rset
					.getBoolean("webtoons_professional");
			String webtoons_pgrating = rset.getString("webtoons_pgrating");
			String webtoons_publisher = rset.getString("webtoons_publisher");
			double webtoons_average_rate = rset
					.getDouble("webtoons_average_rate");
			String webtoons_main_image = rset.getString("webtoons_main_image");
			String webtoons_url = rset.getString("webtoons_url");
			String webtoons_first_update = rset
					.getString("webtoons_first_update");
			int user_webtoon_rate = rset.getInt("user_webtoon_rate");
			System.out.println("별점 점" + user_webtoon_rate);
			System.out.println("웹툰 아이디디디" + webtoon_id);
			String webtoon_viewfree = null;
			String webtoon_professional = null;

			if (webtoons_viewfree == true) {
				webtoon_viewfree = "무료";
			} else {
				webtoon_viewfree = "유료";
			}
			if (webtoons_professional == true) {
				webtoon_professional = "프로작가";
			} else {
				webtoon_professional = "아마추어작가";
			}

			webtoonInfo = new WebtoonVO(webtoon_id, genres_name,
					webtoons_title, webtoons_summary, webtoons_update_days,
					webtoons_completed, webtoon_viewfree, webtoon_professional,
					webtoons_pgrating, webtoons_publisher,
					webtoons_average_rate, webtoons_main_image, webtoons_url,
					webtoons_first_update, user_webtoon_rate);
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

		return webtoonInfo;
	}

	// 2014.07.23 soo 걍 웹툰 상세보기 정보 뽑아오기
	public WebtoonVO getWebtoonInfo(int webtoon_id) {
		WebtoonVO webtoonInfo = null;
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "select g.genres_name, w.webtoons_title, w.webtoons_summary, "
					+ "w.webtoons_update_days, w.webtoons_completed, w.webtoons_viewfree, "
					+ "w.webtoons_professional, w.webtoons_pgrating, w.webtoons_publisher, "
					+ "w.webtoons_average_rate, w.webtoons_main_image, w.webtoons_url, "
					+ "w.webtoons_first_update "
					+ "from webtoons w inner join genres g on w.genre_id_fk = g.genres_id_pk "
					+ "where w.webtoons_id_pk = " + webtoon_id;

			// 장르, (중분류), 타이틀, 작가이름, 줄거리, 연재요일, 완결유무, 유/무료,
			// 프로/아마, 관람등급, 제공처, 평균평점, 메인 이미지(07.21 추가), (썸네일)
			// 주소, 연재시작일, (댓글), (좋아요)

			// 중분류, 썸네일, 댓글, 좋아요

			ResultSet rset = stmt.executeQuery(sql);

			rset.next();
			String genres_name = rset.getString("genres_name");
			String webtoons_title = rset.getString("webtoons_title");
			String webtoons_summary = rset.getString("webtoons_summary");
			String webtoons_update_days = rset
					.getString("webtoons_update_days");
			String webtoons_completed = rset.getString("webtoons_completed");
			boolean webtoons_viewfree = rset.getBoolean("webtoons_viewFree");
			boolean webtoons_professional = rset
					.getBoolean("webtoons_professional");
			String webtoons_pgrating = rset.getString("webtoons_pgrating");
			String webtoons_publisher = rset.getString("webtoons_publisher");
			double webtoons_average_rate = rset
					.getDouble("webtoons_average_rate");
			String webtoons_main_image = rset.getString("webtoons_main_image");
			String webtoons_url = rset.getString("webtoons_url");
			String webtoons_first_update = rset
					.getString("webtoons_first_update");
			String webtoon_viewfree = null;
			String webtoon_professional = null;

			if (webtoons_viewfree == true) {
				webtoon_viewfree = "무료";
			} else {
				webtoon_viewfree = "유료";
			}
			if (webtoons_professional == true) {
				webtoon_professional = "프로작가";
			} else {
				webtoon_professional = "아마추어작가";
			}

			webtoonInfo = new WebtoonVO(webtoon_id, genres_name,
					webtoons_title, webtoons_summary, webtoons_update_days,
					webtoons_completed, webtoon_viewfree, webtoon_professional,
					webtoons_pgrating, webtoons_publisher,
					webtoons_average_rate, webtoons_main_image, webtoons_url,
					webtoons_first_update);
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

		return webtoonInfo;
	}

	// 2014.07.14 soo 웹툰 상세보기 정보 뽑아오기
	public boolean updateWebtoonRate(int webtoons_id, double rate) {
		boolean result = false;

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "update webtoons set webtoons_average_rate = " + rate
					+ " where webtoons_id_pk = " + webtoons_id;

			int checkSuccess = stmt.executeUpdate(sql);

			if (checkSuccess != 0) {
				result = true;
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

		return result;
	}

	// 2014.07.17 soo 4점 이상 읽은 웹툰 작가 가져오기
	public String findHighRatedWebtoonsAuthors(long users_facebookID,
			String author_name) {
		String webtoon_title = null;
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "select * from (select w.webtoons_title, a.authors_name from webtoons as w "
					+ "inner join user_webtoon_maps as uw on w.webtoons_id_pk = uw.webtoons_id_fk "
					+ "inner join webtoon_author_maps as wam on w.webtoons_id_pk = wam.webtoons_id_fk "
					+ "inner join authors as a on a.authors_id_pk = wam.authors_id_fk "
					+ "where uw.users_facebookID_fk = "
					+ users_facebookID
					+ " and a.authors_name = '"
					+ author_name
					+ "' and uw.user_webtoon_rate > 3 and uw.user_webtoon_isread = 1 "
					+ "order by uw.user_webtoon_rate desc) as result "
					+ "group by authors_name";

			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				webtoon_title = rset.getString("webtoons_title");
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

		return webtoon_title;
	}

	// 7.18 영규꺼 // 7.22 id 추가
	public List<WebtoonVO> findReadToon(long users_facebookID, String num) {
		Connection conn = null;
		Statement stmt = null;

		ArrayList<WebtoonVO> readToon = new ArrayList<WebtoonVO>();

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlQuery = String.format("select w.webtoons_id_pk as webtoonID ,uwm.user_webtoon_rate as rate,  w.webtoons_title as title, w.webtoons_thumbnail as thumbnail, w.webtoons_url as url "
					+ " from user_webtoon_maps as uwm"
					+ " inner join webtoons as w on w.webtoons_id_pk=uwm.webtoons_id_fk"
					+ " where uwm.users_facebookID_fk="
					+ users_facebookID
					+ " and uwm.user_webtoon_isread=1 limit %s , 10 ", num);

			ResultSet rset = stmt.executeQuery(sqlQuery);
			while (rset.next()) {
				int toon_id = rset.getInt("webtoonID");
				int toon_rate = rset.getInt("rate");
				String toon_title = rset.getString("title");
				String toon_thumbnail = rset.getString("thumbnail");
				String toon_url = rset.getString("url");

				readToon.add(new WebtoonVO(toon_id, toon_title, toon_thumbnail,
						toon_url, toon_rate));
			}

		} catch (SQLException ex) {
			Logger.getLogger(WebtoonDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(WebtoonDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}

		return readToon;
	}

	// 7.18 영규꺼 // 7.22 id 추가 기간 20일로 변경
	public List<WebtoonVO> findNewToon(String num) {
		Connection conn = null;
		Statement stmt = null;

		ArrayList<WebtoonVO> newToon = new ArrayList<WebtoonVO>();

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlQuery = String.format("select webtoons_id_pk as webtoonID, webtoons_title as title, webtoons_update_days as days,"
					+ " webtoons_summary as summary, webtoons_publisher as publisher,"
					+ " webtoons_url as url from webtoons"
					+ " where (webtoons_first_update <= curdate()) and (webtoons_first_update + interval 20 day >= curdate() limit %s, 10 ", num);

			ResultSet rset = stmt.executeQuery(sqlQuery);
			while (rset.next()) {
				int toon_id = rset.getInt("webtoonID");
				String toon_title = rset.getString("title");
				String toon_days = rset.getString("days");
				String toon_summary = rset.getString("summary");
				String toon_publisher = rset.getString("publisher");
				String toon_url = rset.getString("url");

				newToon.add(new WebtoonVO(toon_id, toon_title, toon_days,
						toon_summary, toon_publisher, toon_url));
			}

		} catch (SQLException ex) {
			Logger.getLogger(WebtoonDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(WebtoonDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}

		return newToon;
	}

	// 7.18 영규꺼 // 7.22 id 추가
	public List<WebtoonVO> findWishList(long users_facebookID, String num) {
		Connection conn = null;
		Statement stmt = null;

		ArrayList<WebtoonVO> wishList = new ArrayList<WebtoonVO>();

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlQuery = String.format("select w.webtoons_id_pk as webtoonID, w.webtoons_title as title, w.webtoons_thumbnail as thumbnail, w.webtoons_url as url "
					+ " from user_webtoon_maps as uwm"
					+ " inner join webtoons as w on w.webtoons_id_pk=uwm.webtoons_id_fk"
					+ " where uwm.users_facebookID_fk="
					+ users_facebookID
					+ " and uwm.user_webtoon_isread=0 limit %s, 10 ", num);

			ResultSet rset = stmt.executeQuery(sqlQuery);
			while (rset.next()) {
				int toon_id = rset.getInt("webtoonID");
				String toon_title = rset.getString("title");
				String toon_thumbnail = rset.getString("thumbnail");
				String toon_url = rset.getString("url");

				wishList.add(new WebtoonVO(toon_id, toon_title, toon_thumbnail,
						toon_url));
			}

		} catch (SQLException ex) {
			Logger.getLogger(WebtoonDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(WebtoonDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}

		return wishList;
	}

	// bj 키워드별 웹툰 7.18
	public List<Webtoon_keyword_mapsVO> getWebtoonsByKeywordID(int keywordID) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<Webtoon_keyword_mapsVO> keyword_mapsVOs = new ArrayList<Webtoon_keyword_mapsVO>();
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlStr = "select k.keywords_name, w.webtoons_title, w.webtoons_id_pk, k.keywords_id_pk from webtoon_keyword_maps as wkm inner join webtoons as w on w.webtoons_id_pk = wkm.webtoons_id_fk inner join keywords as k on k.keywords_id_pk = wkm.keywords_id_fk where k.keywords_id_pk = "
					+ keywordID;

			rset = stmt.executeQuery(sqlStr);

			while (rset.next()) {
				int webtoons_id_pk = rset.getInt("webtoons_id_pk");
				int keywords_id_pk = rset.getInt("keywords_id_pk");
				String webtoons_title = rset.getString("webtoons_title");
				String keywords_name = rset.getString("keywords_name");
				Webtoon_keyword_mapsVO keyword_mapsVO = new Webtoon_keyword_mapsVO(
						webtoons_id_pk, keywords_id_pk, webtoons_title,
						keywords_name);

				keyword_mapsVOs.add(keyword_mapsVO);
			}

		} catch (Exception e) {

			// TODO: handle exception
			e.printStackTrace();
		}
		return keyword_mapsVOs;
	}

	// 태균,병진 웹툰 다 가져와 7.18
	public List<WebtoonVO> getAllWebtoons() {
		Connection conn = null;
		Statement stmt = null;
		List<WebtoonVO> allWebtoons = new ArrayList<WebtoonVO>();

		try {

			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlStr = "SELECT *,genres_name FROM webtoons INNER JOIN genres  ON webtoons.genre_id_fk = genres.genres_id_pk order by webtoons_id_pk";

			ResultSet rset = stmt.executeQuery(sqlStr);
			while (rset.next()) {
				int webtoons_id_pk = rset.getInt("webtoons_id_pk");
				int genre_id_fk = rset.getInt("genre_id_fk");
				String webtoons_title = rset.getString("webtoons_title");
				String webtoons_summary = rset.getString("webtoons_summary");
				String webtoons_update_days = rset
						.getString("webtoons_update_days");
				String webtoons_completed = rset
						.getString("webtoons_completed");
				boolean webtoons_viewfree = rset
						.getBoolean("webtoons_viewfree");
				boolean webtoons_professional = rset
						.getBoolean("webtoons_professional");
				String webtoons_pgrating = rset.getString("webtoons_pgrating");
				String webtoons_publisher = rset
						.getString("webtoons_publisher");
				Double webtoons_average_rate = rset
						.getDouble("webtoons_average_rate");
				String webtoons_thumbnail = rset
						.getString("webtoons_thumbnail");
				String webtoons_url = rset.getString("webtoons_url");
				String webtoons_first_update = rset
						.getString("webtoons_first_update"); // 2014.07.11 soo
																// 여기부터
				String genres_name = rset.getString("genres_name");
				WebtoonVO webtoonVO = new WebtoonVO(webtoons_id_pk,
						genre_id_fk, webtoons_title, webtoons_summary,
						webtoons_update_days, webtoons_completed,
						webtoons_viewfree, webtoons_professional,
						webtoons_pgrating, webtoons_publisher,
						webtoons_average_rate, webtoons_thumbnail,
						webtoons_url, webtoons_first_update, genres_name);
				allWebtoons.add(webtoonVO);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return allWebtoons;
	}

	// bj 웹툰 추가하기 7.18
	public void addWebtoon(WebtoonVO webtoonVO) {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			int genre_id_fk = webtoonVO.getGenre_id_fk();
			String webtoons_title = webtoonVO.getWebtoons_title();
			String webtoons_summary = webtoonVO.getWebtoons_summary();
			String webtoons_update_days = webtoonVO.getWebtoons_update_days();
			String webtoons_completed = webtoonVO.getWebtoons_completed();
			boolean webtoons_professional = webtoonVO.isWebtoons_professional();
			String webtoons_pgrating = webtoonVO.getWebtoons_pgrating();
			String webtoons_publisher = "네이버";
			String webtoons_url = webtoonVO.getWebtoons_url();
			String webtoons_first_update = webtoonVO.getWebtoons_first_update();
			String webtoons_viewfree = webtoonVO.getWebtoon_viewfree();
			String authors_name = webtoonVO.getAuthors_name();
			double webtoons_average_rate = 0.0;

			String sqlstr = "insert into webtoons "
					+ "(genre_id_fk,webtoons_title,webtoons_summary,webtoons_update_days,"
					+ "webtoons_completed,webtoons_viewfree,webtoons_professional,"
					+ "webtoons_pgrating,webtoons_publisher,webtoons_average_rate"
					+ "webtoons_url,webtoons_first_update) values ("
					+ genre_id_fk
					+ ",'"
					+ webtoons_title
					+ "','"
					+ webtoons_summary
					+ "','"
					+ webtoons_update_days
					+ "','"
					+ webtoons_completed
					+ "','"
					+ webtoons_viewfree
					+ "',"
					+ webtoons_professional
					+ ",'"
					+ webtoons_pgrating
					+ "','"
					+ webtoons_publisher
					+ "',"
					+ webtoons_average_rate
					+ "'"
					+ webtoons_url + "','" + webtoons_first_update + "')";
			System.out.println(sqlstr);
			stmt.executeUpdate(sqlstr);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// findAuthorToon - bj 7.18 // 7.22 id 추가
	public List<WebtoonVO> findAuthorToon(int authorId) {
		Connection conn = null;
		Statement stmt = null;

		ArrayList<WebtoonVO> authorToon = new ArrayList<WebtoonVO>();

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			// 추천받은 웹툰 DB 테이블로 변경
			String sqlQuery = "select w.webtoons_id_pk as webtoonID, w.webtoons_title as title,"
					+ " w.webtoons_update_days as days, w.webtoons_summary as summary,"
					+ " w.webtoons_publisher as publisher, w.webtoons_url as url"
					+ " from webtoon_author_maps as wam"
					+ " inner join webtoons as w on w.webtoons_id_pk=wam.webtoons_id_fk"
					+ " inner join authors as a on a.authors_id_pk=wam.authors_id_fk"
					+ " where a.authors_id_pk=" + authorId;

			ResultSet rset = stmt.executeQuery(sqlQuery);
			while (rset.next()) {
				int toon_id = rset.getInt("webtoonID");
				String toon_title = rset.getString("title");
				String toon_days = rset.getString("days");
				String toon_summary = rset.getString("summary");
				String toon_publisher = rset.getString("publisher");
				String toon_url = rset.getString("url");

				authorToon.add(new WebtoonVO(toon_id, toon_title, toon_days,
						toon_summary, toon_publisher, toon_url));
			}

		} catch (SQLException ex) {
			Logger.getLogger(WebtoonDAO.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(WebtoonDAO.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}

		return authorToon;
	}
	
	// 7.31 영규 추가
	public int getReadWebtoonCount(long users_facebookID_fk) {
		System.out.println(users_facebookID_fk + " 사용자가 본 웹툰 카운트");
		
		int readWebtoonCount = 0;
		
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sql = "select count(*) as count from user_webtoon_maps where users_facebookID_fk=" + users_facebookID_fk + " and user_webtoon_isread=1";
			
			ResultSet rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				int webtoonCount = rset.getInt("count");

				readWebtoonCount = webtoonCount;
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
		
		return readWebtoonCount;
	}
	
	// 7.31 영규 추가
	public int getAllWebtoonCount() {
		int allWebtoonCount = 0;
		
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sql = "select count(*) as count from webtoons";
			
			ResultSet rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				int webtoonCount = rset.getInt("count");

				allWebtoonCount = webtoonCount;
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
		
		return allWebtoonCount;
	}

}
