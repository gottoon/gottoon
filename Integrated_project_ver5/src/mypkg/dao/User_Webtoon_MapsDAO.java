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
	public boolean addReserve(long users_facebookID_fk, int webtoons_id_fk) {
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
	
	
	//2014.07.20 박태균 웹툰 수정 메소드 
	public void updateReadWebtoon(int webtoons_id_pk, int user_webtoon_rate, long users_facebookID_fk) {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "UPDATE user_webtoon_maps SET user_webtoon_rate = " + user_webtoon_rate
					+ ", user_webtoon_isread = 1"
					+ " where users_facebookID_fk = " + users_facebookID_fk + " and webtoons_id_fk = " + webtoons_id_pk;

			stmt.executeUpdate(sql);

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

	}
	//2014.07.20 박태균 유저가 읽은 웹툰 가져오기
	public List<WebtoonVO> readWebtoon(long users_facebookID_fk) {
		Connection conn = null;
		Statement stmt = null;
		List<WebtoonVO> webtoons = new ArrayList<>();
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "select * from user_webtoon_maps where users_facebookID_fk = " + users_facebookID_fk + ";";
			ResultSet rset = stmt.executeQuery(sql);
			while (rset.next()) {
				int webtoons_id_pk = rset.getInt("webtoons_id_fk");
				System.out.println("사용자가 선택했던 웹툰 아이디" + webtoons_id_pk);
				webtoons.add(new WebtoonVO(webtoons_id_pk));
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

		return webtoons;

	}
	//2014.07.20 박태균 찜한거 제외한 웹툰 가져오기 
	public List<UserWebtoonMapsVO> readWebtoonWithOUtIsRead(long users_facebookID_fk) {
		Connection conn = null;
		Statement stmt = null;
		List<UserWebtoonMapsVO> webtoons = new ArrayList<>();
		int count =0;
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "select user_webtoon_maps.webtoons_id_fk , user_webtoon_maps.user_webtoon_rate , "
					+ " webtoons.webtoons_title from user_webtoon_maps inner join "
					+ " webtoons on user_webtoon_maps.webtoons_id_fk = webtoons.webtoons_id_pk "
					+ " where user_webtoon_maps.users_facebookID_fk = " + users_facebookID_fk + " and user_webtoon_maps.user_webtoon_isread = 1 ; ";
			ResultSet rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				int webtoons_id_fk = rset.getInt("webtoons_id_fk");
				int user_webtoon_rate = rset.getInt("user_webtoon_rate");
				String webtoon_title = rset.getString("webtoons_title");
				System.out.println("readWebtoonWithOUtIsRead의 사용자가 선택했던 웹툰 아이디" + webtoons_id_fk
						+" , 별점 :"+user_webtoon_rate+", 웹툰 타이틀 : "+webtoon_title);
			
				++count;
				webtoons.add(new UserWebtoonMapsVO( webtoons_id_fk, user_webtoon_rate , webtoon_title));
				
			}
			System.out.println("총 갯수 : "+count);
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

		return webtoons;

	}
	//2014.07.20 박태균 카운트 
	public int countWebtoon(long users_facebookID_fk) {

		Connection conn = null;
		Statement stmt = null;
		int count = 0;
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from user_webtoon_maps where users_facebookID_fk = " + users_facebookID_fk + " and user_webtoon_isread = 1;";

			ResultSet rs = stmt.executeQuery(sql);
			rs.last();
			count = rs.getRow();
			rs.beforeFirst();

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
		return count;
	}
	//2014.07.20 박태균 웹툰 추가 
	public void addReadWebtoon(int webtoons_id_pk, int user_webtoon_rate, long users_facebookID_fk) {
		System.out.println("addReadWebtoon 시작 ");

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sql = "INSERT INTO user_webtoon_maps ( webtoons_id_fk , users_facebookID_fk , push_alarms_id_fk , comment_id_fk , user_webtoon_rate, user_webtoon_isread ) SELECT "
					+ webtoons_id_pk
					+ " , "
					+ users_facebookID_fk
					+ " , 1 , 1 , "
					+ user_webtoon_rate
					+ " , 1 FROM DUAL WHERE NOT EXISTS (SELECT * FROM user_webtoon_maps WHERE webtoons_id_fk = "
					+ webtoons_id_pk + " AND users_facebookID_fk = " + users_facebookID_fk + " );";

			stmt.execute(sql);
			System.out.println("유저가 선택한 웹툰아이디  :" + webtoons_id_pk + " , 유저아이디 :" + users_facebookID_fk + ",평가별점 :  "
					+ user_webtoon_rate + "점 추가 완료");
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

	}
	//2014.07.20 박태균 웹툰 삭제 
	public void deleteReadWebtoon(long currentUser_facebookID, int webtoons_id_pk) {
		System.out.println("삭제할 웹툰아이디 : " + webtoons_id_pk);
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sql = "delete From user_webtoon_maps where webtoons_id_fk = " + webtoons_id_pk 
						+ " and users_facebookID_fk = " + currentUser_facebookID;
			
			stmt.executeUpdate(sql);

			// String sql =
			// "delete From user_webtoon_maps (webtoons_id_fk) SELECT"
			// + webtoons_id_pk
			// +"  FROM DUAL WHERE NOT EXISTS  (SELECT * FROM user_webtoon_maps WHERE webtoons_id_fk = "
			// + webtoons_id_pk + ");";

			//
			// String sql =
			// "INSERT INTO user_webtoon_maps ( webtoons_id_fk , users_facebookID_fk , push_alarms_id_fk , comment_id_fk , user_webtoon_rate, user_webtoon_isread ) SELECT "
			// + webtoons_id_pk
			// + " , "
			// + users_facebookID_fk
			// + " , 1 , 1 ,  "
			// + user_webtoon_rate
			// +
			// " , 1 FROM DUAL WHERE NOT EXISTS (SELECT * FROM user_webtoon_maps WHERE webtoons_id_fk = "
			// + webtoons_id_pk + ");";
			//
			//

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
	}
	
	//2014.07.20 박태균 신규가입자의 첫 웹툰 추가 
	public void addFirstReadWebtoon(int webtoons_id_pk, int user_webtoon_rate, long users_facebookID_fk) {
		System.out.println("새로운 유저 insert시작 또는 새로운 웹툰 추가  " + webtoons_id_pk);
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			// INSERT INTO good_guy (NAME, email) VALUES ('oh_yea', 'cool')
			// ON DUPLICATE KEY UPDATE name='oh_yea', email='wow';
			
			String sql = "INSERT INTO user_webtoon_maps (webtoons_id_fk , user_webtoon_rate , users_facebookID_fk , push_alarms_id_fk , comment_id_fk , user_webtoon_isread) VALUES ("
					+ webtoons_id_pk + ", " + user_webtoon_rate + ", " + users_facebookID_fk + ", 1 , 1 ,1)";
			stmt.executeUpdate(sql);
			
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
	}
	
	// 2014.07.23 soo 내가 본 웹툰, 찜한 웹툰 체크
	public boolean checkMyWebtoon(long users_facebookID_pk, int webtoon_id) {
		boolean check = false;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sql = "select * from user_webtoon_maps where users_facebookID_fk = " 
					+ users_facebookID_pk + " and webtoons_id_fk = " + webtoon_id;
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if (rset.next()) {
				check = true;
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
		
		return check;
	}
	
	public int getCheckReserve(long users_facebookID_pk, int webtoon_id) {
		int check = -1;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String sql = "select user_webtoon_isread from user_webtoon_maps where users_facebookID_fk = " 
					+ users_facebookID_pk + " and webtoons_id_fk = " + webtoon_id;
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if (rset.next()) {
				check = rset.getInt("user_webtoon_isread");
			} else {
				check = -1;
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
		
		return check;
	}
}
