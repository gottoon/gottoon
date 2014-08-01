package mypkg.service;

import java.util.ArrayList;
import java.util.List;

import mypkg.dao.MySqlDAOFactory;
import mypkg.dao.PublisherDAO;
import mypkg.vo.PublisherVO;

import com.google.gson.Gson;

public class PublisherService {
	public String doGetAllPublishersJson() {
		List<PublisherVO> publisherVOs = this.getAllPublisher();

		Gson gson = new Gson();

		String publishers = gson.toJson(publisherVOs);
		return publishers;
	}

	// 모든 제공처 가져오기 - bj

	public List<PublisherVO> getAllPublisher() {
		MySqlDAOFactory mysqlFactory = new MySqlDAOFactory();
		PublisherDAO publisherDAO = mysqlFactory.getPublisherDAO();

		return publisherDAO.getAllPublishers();
	}
}
