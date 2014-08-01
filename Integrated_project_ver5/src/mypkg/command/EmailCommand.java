package mypkg.command;

import java.io.IOException;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Properties;

import mypkg.control.Command;
import mypkg.control.CommandResult;
import mypkg.service.EmailService;

import org.json.JSONException;

public class EmailCommand implements Command {
	CommandResult commandResult = null;
	EmailService emailService = new EmailService();
	private static final String SMTP_HOST = "gmail-smtp.l.google.com";

	@Override
	public CommandResult execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			JSONException {

		request.setCharacterEncoding("UTF-8");

		System.out.println("EmailCommand execute 시작 ");
		String todo = request.getParameter("todo");
		System.out.println("todo = " + todo);

		if (todo.equals("send")) {
			commandResult = sendMail(request, response);
		}
		return commandResult;
	}

	public CommandResult sendMail(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		String webtoons_title = request.getParameter("webtoons_title");
		String webtoons_summary = request.getParameter("webtoons_summary");
		String authors_id = request.getParameter("authors_id");
		String author_name = request.getParameter("author_name");
		String keywords_id = request.getParameter("keywords_id");
		String keywords_name = request.getParameter("keywords_name");
		String genres_id = request.getParameter("genres_id");
		String genres_name = request.getParameter("genres_name");
		String webtoons_url = request.getParameter("webtoons_url");
		String webtoons_update_days = request
				.getParameter("webtoons_update_days");
		String webtoons_completed = request.getParameter("webtoons_completed");
		String webtoons_viewfree = request.getParameter("webtoons_viewfree");
		String webtoons_professional = request
				.getParameter("webtoons_professional");
		String webtoons_pgrating = request.getParameter("webtoons_pgrating");
		String webtoons_first_update = request
				.getParameter("webtoons_first_update");

		boolean debug = false;
		java.security.Security
				.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		String to = "bjkim.dev@gmail.com";
		String from = "addToon@gottoon.com";
		String subject = "웹툰 추가요청";
		String content = "제목 : " + webtoons_title + "<\n>줄거리 : "
				+ webtoons_summary + "<\n>작가 아디 : " + authors_id
				+ "<\n>작가 이름 : " + author_name + "<\n>키워드 아디 : " + keywords_id
				+ "<\n>키워드 이름 : " + keywords_name + "<\n>장르 아디 : " + genres_id
				+ "<\n>장르 이름 : " + genres_name + "<\n>url : " + webtoons_url
				+ "<\n>연재일 : " + webtoons_update_days + "<\n>연재 : "
				+ webtoons_completed + "<\n>유/무료 여부 : " + webtoons_viewfree
				+ "<\n>프로/아마 : " + webtoons_professional + "<\n>관람가 : "
				+ webtoons_pgrating + "<\n>연재 시작일 : " + webtoons_first_update;
		System.out.println("메일 내용 "+ content);
		
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", SMTP_HOST);
		props.put("mail.smtp.auth", "true");
		Boolean success = false;
		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getDefaultInstance(props, auth);
			session.setDebug(debug);
			Message msg = new MimeMessage(session);
			InternetAddress addressFrom = new InternetAddress(from,
					MimeUtility.encodeText(from, "UTF-8", "B"));
			msg.setFrom(addressFrom);
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setContent(content, "text/plain;charset=UTF-8");
			Transport.send(msg);
			success = true;
		} catch (Exception e) {
			success = false;
			System.out.println(e);
		} finally {
		}
		commandResult = new CommandResult("text/plain", "굿");
		return commandResult;
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			String username = "bjkim.dev@gmail.com";
			String password = "wkfrk331";
			return new PasswordAuthentication(username, password);
		}
	}
}
