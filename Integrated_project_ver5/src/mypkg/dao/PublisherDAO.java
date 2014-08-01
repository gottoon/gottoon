package mypkg.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import mypkg.vo.PublisherVO;

public class PublisherDAO {
	private DataSource pool;

	// 생성자
	public PublisherDAO() {
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

	public List<PublisherVO> getAllPublishers() {
		List<PublisherVO> publisherVOs = new ArrayList<PublisherVO>();
		Connection conn = null;
		Statement stmt = null;

		try {

			conn = pool.getConnection();
			stmt = conn.createStatement();

			String sqlStr = "select * from publishers";

			ResultSet rset = stmt.executeQuery(sqlStr);

			while (rset.next()) {

				int publishers_id_pk = rset.getInt("publishers_id_pk");
				String publishers_name = rset.getString("publishers_name");
				publisherVOs.add(new PublisherVO(publishers_id_pk,
						publishers_name));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return publisherVOs;

	}
}