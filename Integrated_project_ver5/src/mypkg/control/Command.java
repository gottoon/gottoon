package mypkg.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public interface Command {
	public CommandResult execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			JSONException;

}
