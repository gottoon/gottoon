package mypkg.service;

import mypkg.dao.MySqlDAOFactory;
import mypkg.dao.MypageDAO;
import mypkg.vo.MypageVO;

// 7.18 다 영규꺼
public class MypageService {
	public MypageVO getNewOnOff(long CurruntUser_facebookID) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		MypageDAO mypageDAO = mysqlFactory.getMypageDAO();

		return mypageDAO.getNewOnOff(CurruntUser_facebookID);
	}

	public void setNewOnOff(long user_facebookID_pk) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		MypageDAO mypageDAO = mysqlFactory.getMypageDAO();

		mypageDAO.setNewOnOff(user_facebookID_pk);
	}

	public MypageVO getAuthorOnOff(long user_facebookID_pk) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		MypageDAO mypageDAO = mysqlFactory.getMypageDAO();

		return mypageDAO.getAuthorOnOff(user_facebookID_pk);
	}

	public void setAuthorOnOff(long user_facebookID_pk) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		MypageDAO mypageDAO = mysqlFactory.getMypageDAO();

		mypageDAO.setAuthorOnOff(user_facebookID_pk);
	}
}
