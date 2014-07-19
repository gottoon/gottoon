package mypkg.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.control.Command;
import mypkg.control.CommandResult;
import mypkg.dao.GenreDAO;
import mypkg.dao.MySqlDAOFactory;
import mypkg.service.GenreService;
import mypkg.vo.GenreVO;


