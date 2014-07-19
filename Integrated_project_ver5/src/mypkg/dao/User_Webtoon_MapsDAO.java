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

import mypkg.vo.AverageRateVO;
import mypkg.vo.UserWebtoonMapsVO;
import mypkg.vo.WebtoonVO;

public class User_Webtoon_MapsDAO {
	private DataSource pool;

	// 생성자
	public User_Webtoon_MapsDAO() {
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
	
	// soo 읽은 웹툰 다 가져오기
	public List<UserWebtoonMapsVO> getAllReadWebtoons(long users_facebookID) {
		List<UserWebtoonMapsVO> readWebtoons = new ArrayList<UserWebtoonMapsVO>();
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			// String sql = "select * from user_webtoon_maps";
			String sql = "select uw.users_facebookID_fk, uw.webtoons_id_fk, w.genre_id_fk, "
					+ "uw.user_webtoon_isread, uw.user_webtoon_rate " + "from user_webtoon_maps as uw "
					+ "inner join webtoons as w on w.webtoons_id_pk = uw.webtoons_id_fk "
					+ "where uw.users_facebookID_fk = " + users_facebookID + " and uw.user_webtoon_isread = 1";

			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				int webtoonId = rset.getInt("webtoons_id_fk");
				int genreId = rset.getInt("genre_id_fk");
				boolean webtoonIsRead = rset.getBoolean("user_webtoon_isread");
				int webtoonRate = rset.getInt("user_webtoon_rate");

				UserWebtoonMapsVO readWebtoon = new UserWebtoonMapsVO(users_facebookID, webtoonId, genreId,
						webtoonIsRead, webtoonRate);

				readWebtoons.add(readWebtoon);
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

		return readWebtoons;
	}

	// soo 읽은 웹툰 중 별점4점 이상 가져오기
	public List<UserWebtoonMapsVO> findHighRatedWebtoons(long users_facebookID) {
		List<UserWebtoonMapsVO> readWebtoons = new ArrayList<UserWebtoonMapsVO>();
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			// String sql =
			// "select * from user_webtoon_maps where users_id_fk =" + userId
			// + " and user_webtoon_rate > 3";
			String sql = "select uw.webtoons_id_fk, w.genre_id_fk, " + "uw.user_webtoon_isread, uw.user_webtoon_rate "
					+ "from user_webtoon_maps as uw "
					+ "inner join webtoons as w on w.webtoons_id_pk = uw.webtoons_id_fk "
					+ "where uw.users_facebookID_fk = " + users_facebookID + " and uw.user_webtoon_rate > 3 "
					+ "and uw.user_webtoon_isread = 1";

			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				int webtoonId = rset.getInt("webtoons_id_fk");
				int genreId = rset.getInt("genre_id_fk");
				boolean webtoonIsRead = rset.getBoolean("user_webtoon_isread");
				int webtoonRate = rset.getInt("user_webtoon_rate");

				UserWebtoonMapsVO readWebtoon = new UserWebtoonMapsVO(users_facebookID, webtoonId, genreId,
						webtoonIsRead, webtoonRate);

				readWebtoons.add(readWebtoon);
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

		return readWebtoons;
	}

	// soo 읽은 웹툰 중 별점3점 이하 가져오기
	public List<UserWebtoonMapsVO> findLowRatedWebtoons(long users_facebookID) {
		List<UserWebtoonMapsVO> readWebtoons = new ArrayList<UserWebtoonMapsVO>();
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "select uw.webtoons_id_fk, w.genre_id_fk, " + "uw.user_webtoon_isread, uw.user_webtoon_rate "
					+ "from user_webtoon_maps as uw "
					+ "inner join webtoons as w on w.webtoons_id_pk = uw.webtoons_id_fk "
					+ "where uw.users_facebookID_fk = " + users_facebookID + " and uw.user_webtoon_rate < 4 "
					+ "and uw.user_webtoon_isread = 1";

			ResultSet rset = stmt.executeQuery(sql);

			while (rset.next()) {
				int webtoonId = rset.getInt("webtoons_id_fk");
				int genreId = rset.getInt("genre_id_fk");
				boolean webtoonIsRead = rset.getBoolean("user_webtoon_isread");
				int webtoonRate = rset.getInt("user_webtoon_rate");

				UserWebtoonMapsVO readWebtoon = new UserWebtoonMapsVO(users_facebookID, webtoonId, genreId,
						webtoonIsRead, webtoonRate);

				readWebtoons.add(readWebtoon);
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

		return readWebtoons;
	}

	// 2014.07.10 soo 찜한 웹툰 넣는 DAO
	public boolean addReserveWebtoon(long users_facebookID_fk, int webtoons_id_fk) {
		boolean result = false;

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "insert into user_webtoon_maps (users_facebookID_fk, "
					+ "webtoons_id_fk, push_alarms_id_fk, comment_id_fk, user_webtoon_isread) " + "select "
					+ users_facebookID_fk + ", " + webtoons_id_fk + ", 1, 1, 0 from dual "
					+ "where not exists (select * from user_webtoon_maps where users_facebookID_fk = "
					+ users_facebookID_fk + " and webtoons_id_fk = " + webtoons_id_fk + ")";

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
	
	// 2014.07.14 soo 해당 웹툰 별점 가져오기 DAO
	public AverageRateVO getRateOfReadWebtoon (int webtoon_id_fk) {
		AverageRateVO averageRateVO = null;
		
		Connection conn = null;
		Statement stmt = null;
		System.out.println("test");
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sql = "select count(users_facebookID_fk), sum(user_webtoon_rate) "
					+ "from user_webtoon_maps where webtoons_id_fk = "
					+ webtoon_id_fk + " and user_webtoon_isread = 1";
			
			ResultSet rset = stmt.executeQuery(sql);
			
			rset.next();
			int countUsers = rset.getInt("count(users_facebookID_fk)");
			int sumRate = rset.getInt("sum(user_webtoon_rate)");
			
			averageRateVO = new AverageRateVO(countUsers, sumRate);
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
		
		return averageRateVO;
	}
	
	// 7.18 영규꺼
	public int getWebtoonCount(long users_facebookID_fk) {
		System.out.println(users_facebookID_fk + "사용자가 본 웹툰 카운트");
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
}
