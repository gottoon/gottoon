package mypkg.dao;

public class MySqlDAOFactory {
	public UserDAO getUserDAO() {
		return new UserDAO();
	}
	
	public GenreDAO getGenreDAO() {
		return new GenreDAO();
	}
	
	public WebtoonDAO getWebtoonsDAO() {
		return new WebtoonDAO();
	}
	
	public User_Webtoon_MapsDAO getUser_Webtoon_MapsDAO() {
		return new User_Webtoon_MapsDAO();
	}
	
	public User_Genre_MapsDAO getUser_Genre_MapsDAO() {
		return new User_Genre_MapsDAO();

	}
	
	public Webtoon_Another_Webtoon_Relative_MapsDAO getWebtoon_another_webtoon_relative_mapsDAO() {
		return new Webtoon_Another_Webtoon_Relative_MapsDAO();
	}
	
	public MypageDAO getMypageDAO() {
		return new MypageDAO();
	}
	
	public AuthorDAO getAuthorDAO() {
		return new AuthorDAO();
	}
	public KeywordDAO getKeywordDAO(){
		return new KeywordDAO();
	}
	
	public PublisherDAO getPublisherDAO(){
		return new PublisherDAO();
	}

	public RecommendDAO getRecommendDAO(){
		return new RecommendDAO();
	}
}
