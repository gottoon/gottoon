package mypkg.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.HTTP;

import mypkg.control.Command;
import mypkg.control.CommandResult;
import mypkg.service.UserGenreMapsService;
import mypkg.vo.MypageVO;
import mypkg.vo.UserGenreMapsVO;

