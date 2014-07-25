package mypkg.control;

import mypkg.command.ChartCommand;
import mypkg.command.GenreCommand;
import mypkg.command.MainCommand;
import mypkg.command.ManagerCommand;
import mypkg.command.MypageCommand;
import mypkg.command.RecommendCommand;
import mypkg.command.UserCommand;
import mypkg.command.UserGenreMapsCommand;
import mypkg.command.UserWebtoonMapsCommand;
import mypkg.command.WebtoonCommand;

public class CommandFactory {
	public static Command createCommand(String pathName) {
		Command target = null;

		switch (pathName) {

		case "/main":
			target = new MainCommand();
			break;

		case "/user":
			target = new UserCommand();
			break;

		case "/webtoon":
			target = new WebtoonCommand();
			break;

		case "/userWebtoon":
			target = new UserWebtoonMapsCommand();
			break;

		case "/recommend":
			target = new RecommendCommand();
			break;
		case "/mypageReadWebtoon":
			target = new MypageCommand();
			break;

		case "/mypageRecommendWebtoon":
			target = new MypageCommand();
			break;

//		case "/mypageSetting":
//			target = new MypageCommand();
//			break;

		case "/chart":
			target = new ChartCommand();
			break;

		case "/manager":
			target = new ManagerCommand();
			break;

		// case "/push":
		// target = new PushCommand();
		// break;

		// case "/author":
		// target = new AuthorCommand();
		// break;

		 case "/userGenre":
		 target = new UserGenreMapsCommand();
		 break;
		 
		 case "/genre":
		 target = new GenreCommand();
		 break;
		}
		return target;
	}
}
