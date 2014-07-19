package mypkg.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mypkg.dao.MySqlDAOFactory;
import mypkg.dao.WebtoonDAO;
import mypkg.vo.WebtoonVO;

public class ManagerService {
	
	// doAddWebtoon - bj 7.18 
	public void doAddWebtoon(HttpServletRequest request) {
		int genre_id_fk = Integer.parseInt(request.getParameter("genre_id_fk"));
		String webtoons_title = request.getParameter("webtoons_title");
		String webtoons_summary = request.getParameter("webtoons_summary");
		String webtoons_update_days = request
				.getParameter("webtoons_update_days");
		String webtoons_completed = request.getParameter("webtoons_completed");
		boolean webtoons_viewfree = Boolean.parseBoolean(request
				.getParameter("webtoons_viewfree"));
		boolean webtoons_professional = Boolean.parseBoolean(request
				.getParameter("webtoons_professional"));
		String webtoons_pgrating = request.getParameter("webtoons_pgrating");
		String webtoons_publisher = request.getParameter("webtoons_publisher");
		Double webtoons_average_rate = Double.parseDouble(request
				.getParameter("webtoons_average_rate"));
		String webtoons_mail_image = request
				.getParameter("webtoons_mail_image");
		String webtoons_thumbnail = request.getParameter("webtoons_thumbnail");
		String webtoons_url = request.getParameter("webtoons_url");

		WebtoonVO webtoonVO = new WebtoonVO(genre_id_fk, webtoons_title,
				webtoons_summary, webtoons_update_days, webtoons_completed,
				webtoons_viewfree, webtoons_professional, webtoons_pgrating,
				webtoons_publisher, webtoons_average_rate, webtoons_mail_image,
				webtoons_thumbnail, webtoons_url);

		addWebtoon(webtoonVO);

	}

	// addWebtoon- bj 7.18 
	public void addWebtoon(WebtoonVO webtoonVO) {
		MySqlDAOFactory mySqlDAOFactory = new MySqlDAOFactory();
		WebtoonDAO webtoonDAO = mySqlDAOFactory.getWebtoonsDAO();

		webtoonDAO.addWebtoon(webtoonVO);
	}

	

}
