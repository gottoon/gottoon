package mypkg.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mypkg.dao.MySqlDAOFactory;
import mypkg.dao.UserDAO;
import mypkg.vo.UserVO;


public class UserService {
	
	// 7.18 영규꺼
	public UserVO getUserGrade(long user_facebookID_pk) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		UserDAO userDAO = mysqlFactory.getUserDAO();

		return userDAO.findUserGrade(user_facebookID_pk);
	}

	// 유저 등급 저장 7.18 영규꺼
	public void doSetUserGrade(long CurrentUser_facebookID, int count) {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		UserDAO userDAO = mysqlFactory.getUserDAO();

		int setGrade = 0;

		if (count >= 1 && count <= 19) {
			setGrade = 2;
		} else if (count >= 20 && count <= 39) {
			setGrade = 3;
		} else if (count >= 40 && count <= 79) {
			setGrade = 4;
		} else if (count >= 80 && count <= 119) {
			setGrade = 5;
		} else if (count >= 120 && count <= 179) {
			setGrade = 6;
		} else if (count >= 180 && count <= 239) {
			setGrade = 7;
		} else if (count >= 240 && count <= 319) {
			setGrade = 8;
		} else if (count >= 320) {
			setGrade = 9;
		} 
		
		userDAO.setUserGrade(CurrentUser_facebookID, setGrade);
	}
	
	//doAddUser -bj 7.18 
	public boolean doAddUser(HttpServletRequest request) {
		long user_facebookID = Long.parseLong(request
				.getParameter("CurruntUser_facebookID"));
		String name = request.getParameter("curruntUserName");
		String email = request.getParameter("curruntUserEmail");

		return this.addUser(user_facebookID, name, email);
	}

	// doGetUserGrade - bj 7.18 
	public String doGetUserGrade(long user_facebookID_pk) {
		String userGrade = "";

		UserVO userVO = getUserGrade(user_facebookID_pk);

		userGrade = userVO.getUsers_grade();
		System.out.println("유저 등급 " + userGrade);
		return userGrade;
	}

	//doCheckUser - bj 7.18 
	public boolean doCheckUser(HttpServletRequest request) {
		System.out.println("doCheckUser 시작 ");
		long CurruntUser_facebookID = Long.parseLong(request
				.getParameter("CurruntUser_facebookID"));
		boolean isUser = false;
		List<UserVO> usersList = getAllUsers();

		for (int i = 0; i < usersList.size(); i++) {

			if (usersList.get(i).getUsers_facebookID_pk() == CurruntUser_facebookID) {
				isUser = true;

				break;
			}

		}

		return isUser;
	}

	// addUser - bj 7.18 
	public boolean addUser(long user_facebookID, String name, String email) {

		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		UserDAO userDAO = mysqlDAOFactory.getUserDAO();

		return userDAO.addUser(user_facebookID, name, email);
	}

	public List<UserVO> getAllUsers() {
		MySqlDAOFactory mysqlDAOFactory = new MySqlDAOFactory();
		UserDAO userDAO = mysqlDAOFactory.getUserDAO();

		return userDAO.getAllUsers();

	}
}