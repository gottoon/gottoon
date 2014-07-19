package mypkg.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import mypkg.dao.MySqlDAOFactory;
import mypkg.dao.User_Genre_MapsDAO;
import mypkg.vo.UserGenreMapsVO;
import mypkg.vo.WebtoonVO;

public class UserGenreMapsService {

	int genresId = 0;

	public void UserGenreMapsSet(HttpServletRequest request,
			long CurrentUser_facebookID) {

		String[] userGenreMaps = request.getParameterValues("genrechek");

		for (String userGenre : userGenreMaps) {
			genresId = Integer.parseInt(userGenre);
			UserGenreMapsVO ugm = new UserGenreMapsVO(CurrentUser_facebookID,
					genresId);

			this.doAddUserSelectedGenre(ugm);
		}
	}

	public User_Genre_MapsDAO getUserGenreMaps() {
		MySqlDAOFactory factory = new MySqlDAOFactory();
		return factory.getUser_Genre_MapsDAO();
	}
}
