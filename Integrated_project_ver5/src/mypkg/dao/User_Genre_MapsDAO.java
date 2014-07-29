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
			Logger.getLogger(User_Webtoon_MapsDAO.class.getName()).log(Level.SEVERE, null, e);
		} catch (ServletException e) {
			Logger.getLogger(User_Webtoon_MapsDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	// 2014.07.20 박태균
	public List<WebtoonVO> findToonByUserSelectedGenre(List<UserGenreMapsVO> genresList, long user_facebookID_fk, String num) {

		Connection conn = null;
		Statement stmt = null;

		System.out.println("findToonByUserSelectedGenre 시작");

		List<WebtoonVO> webtoons = new ArrayList<WebtoonVO>();
		String selectedGenres = null;
		boolean button = true;

		try {

			conn = pool.getConnection();
			stmt = conn.createStatement();

			Iterator<UserGenreMapsVO> genresIter = genresList.iterator();

			while (genresIter.hasNext()) {

				UserGenreMapsVO genres = genresIter.next();
//				if (button) {
//					selectedGenres = " genre_id_fk = " + genres.getGenres_id_fk();
//					button = false;
//				}
				if (genresList.size() != 1) {
					String secondSelectedGenres = " or genre_id_fk = " + genres.getGenres_id_fk() + " ";
					selectedGenres += secondSelectedGenres;
				}
			}
System.out.println("sql문 확인 테스트 "+selectedGenres);
//sql문 확인 테스트  genre_id_fk = 8or genre_id_fk = 8 or genre_id_fk = 9 

			String sql = String.format("select * from webtoons where webtoons_id_pk  not in "
					+ "(select webtoons_id_pk from webtoons where webtoons_id_pk = "
					+ "any(select webtoons_id_fk from user_webtoon_maps where " + "users_facebookID_fk = " + user_facebookID_fk
					+ " AND user_webtoon_isread = 1 )) " + "or " + selectedGenres + " limit %s , 6 ", num);
			ResultSet webtoonOfSelectedGenre = stmt.executeQuery(sql);

			while (webtoonOfSelectedGenre.next()) {

				String webtoonsTitle = webtoonOfSelectedGenre.getString("webtoons_title");
				String publisher = webtoonOfSelectedGenre.getString("webtoons_publisher");
				int webtoonId = webtoonOfSelectedGenre.getInt("webtoons_id_pk");
				String url = webtoonOfSelectedGenre.getString("webtoons_url");
				System.out.println("웹툰제목 : " + webtoonOfSelectedGenre.getString("webtoons_title"));
				webtoons.add(new WebtoonVO(webtoonsTitle, publisher, webtoonId, url));

			}
		} catch (SQLException ex) {
			Logger.getLogger(WebtoonDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(WebtoonDAO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return webtoons;

	}

	// public List<WebtoonVO> findToonByUserSelectedGenre(
	// List<UserGenreMapsVO> genresList, long user_facebookID_fk , String num) {
	//
	// Connection conn = null;
	// Statement stmt = null;
	//
	// System.out.println("findToonByUserSelectedGenre 시작");
	//
	// List<WebtoonVO> webtoons = new ArrayList<WebtoonVO>();
	//
	// try {
	//
	// conn = pool.getConnection();
	// stmt = conn.createStatement();
	//
	// Iterator<UserGenreMapsVO> genresIter = genresList.iterator();
	// while (genresIter.hasNext()) {
	//
	// UserGenreMapsVO genres = genresIter.next();
	// String sql =
	// String.format("select * from webtoons where webtoons_id_pk  not in "
	// + "(select webtoons_id_pk from webtoons where webtoons_id_pk = "
	// + "any(select webtoons_id_fk from user_webtoon_maps where "
	// + "users_facebookID_fk = "
	// + user_facebookID_fk
	// + " AND user_webtoon_isread = 1 )) "
	// + "AND genre_id_fk = " + genres.getGenres_id_fk() + " limit %s , 6 ",
	// num);
	// ResultSet webtoonOfSelectedGenre = stmt.executeQuery(sql);
	//
	// while (webtoonOfSelectedGenre.next()) {
	//
	// String webtoonsTitle = webtoonOfSelectedGenre
	// .getString("webtoons_title");
	// String publisher = webtoonOfSelectedGenre
	// .getString("webtoons_publisher");
	// int webtoonId = webtoonOfSelectedGenre
	// .getInt("webtoons_id_pk");
	// System.out.println("웹툰제목 : "+webtoonOfSelectedGenre.getString("webtoons_title"));
	// webtoons.add(new WebtoonVO(webtoonsTitle, publisher,
	// webtoonId));
	//
	// }
	// }
	// } catch (SQLException ex) {
	// Logger.getLogger(WebtoonDAO.class.getName()).log(Level.SEVERE,
	// null, ex);
	// } finally {
	// try {
	// if (stmt != null)
	// stmt.close();
	// if (conn != null)
	// conn.close();
	// } catch (SQLException ex) {
	// Logger.getLogger(WebtoonDAO.class.getName()).log(Level.SEVERE,
	// null, ex);
	// }
	// }
	// return webtoons;
	//
	// }

	// 2014.07.20 박태균 선택한 장르의 웹툰
	public List<UserGenreMapsVO> findSelectedGenres(long users_facebookID) {
		Connection conn = null;
		Statement stmt = null;
		List<UserGenreMapsVO> genres = new ArrayList<UserGenreMapsVO>();

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String usersId = "SELECT * FROM user_genre_maps where users_facebookID_fk = '" + users_facebookID + "'";

			ResultSet selectedGenres = stmt.executeQuery(usersId);

			while (selectedGenres.next()) {
				int genresId = selectedGenres.getInt("genres_id_fk");
				genres.add(new UserGenreMapsVO(genresId));
			}
		} catch (SQLException ex) {
			Logger.getLogger(User_Webtoon_MapsDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(User_Webtoon_MapsDAO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return genres;
	}

	// soo 웹툰 추천
	public List<UserGenreMapsVO> getAllSelectedGenres(long users_facebookID) {
		List<UserGenreMapsVO> userGenres = new ArrayList<UserGenreMapsVO>();
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "select * from user_genre_maps where users_facebookID_fk =" + users_facebookID;

			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				int genreId = rset.getInt("genres_id_fk");
				UserGenreMapsVO userGenre = new UserGenreMapsVO(users_facebookID, genreId);
				userGenres.add(userGenre);
			}
		} catch (SQLException e) {
			Logger.getLogger(User_Webtoon_MapsDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				Logger.getLogger(User_Webtoon_MapsDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return userGenres;
	}

	// 유저가 선택한 장르 DB에 입력 7.20 희철
	public int UserGenreMapsSet(UserGenreMapsVO ugm) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			// String sqlStr =
			// "insert into user_genre_maps (users_facebookID_fk, genres_id_fk) "
			// + "values (" + ugm.getuUsers_facebookID_fk()
			// + "," + ugm.getGenres_id_fk() + ")";

			String sql = "INSERT INTO user_genre_maps (users_facebookID_fk ,genres_id_fk)" + "SELECT " + ugm.getuUsers_facebookID_fk()
					+ " , " + ugm.getGenres_id_fk() + " FROM dual WHERE NOT EXISTS "
					+ "(SELECT * FROM user_genre_maps WHERE genres_id_fk = " + ugm.getGenres_id_fk() + " and users_facebookID_fk = "
					+ ugm.getuUsers_facebookID_fk() + ")";

			result = stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			Logger.getLogger(User_Webtoon_MapsDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(User_Webtoon_MapsDAO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return result;
	}

	// 유저가 입력한거 삭제 빠염 7.17 희철
	public void DelUserGenre(long users_facebookID) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "delete from user_genre_maps where users_facebookID_fk=" + users_facebookID;

			result = stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			Logger.getLogger(User_Webtoon_MapsDAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(User_Webtoon_MapsDAO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	// 유저가 선택한 장르 불러오기 7.17 희철
	public List<UserGenreMapsVO> findAllGenreMaps(long users_facebookID) {
		Connection conn = null;
		Statement stmt = null;
		System.out.println("99999999999");
		ArrayList<UserGenreMapsVO> GenreMapsList = new ArrayList<UserGenreMapsVO>();

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlStr = "SELECT * FROM user_genre_maps where users_facebookID_fk = '" + users_facebookID + "'";
			ResultSet rset = stmt.executeQuery(sqlStr);

			while (rset.next()) {
				// long users_facebookID = rset.getLong("users_facebookID_fk");
				int genres_id = rset.getInt("genres_id_fk");

				GenreMapsList.add(new UserGenreMapsVO(genres_id));
				System.out.println(genres_id);
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
		return GenreMapsList;
	}
}
