package com.stadiumbooking.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stadiumbooking.exception.NotFound;
import com.stadiumbooking.logger.Logger;
import com.stadiumbooking.model.User;
import com.stadiumbooking.service.impl.MatchServiceImpl;
import com.stadiumbooking.service.impl.UserServiceImpl;


@WebServlet("/loginServe")
public class UserLoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final UserServiceImpl userService=new UserServiceImpl();

	static final MatchServiceImpl matchService = new MatchServiceImpl();

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) {

		/* User Login Details */

		String userName = req.getParameter("username").trim();
		String passWord = req.getParameter("password").trim();


		try {
			boolean flag;

			flag = userService.checkUser(userName, passWord);
			if (flag) {

				List<User> userList = userService.validateUser(userName, passWord);
				String role = userList.get(0).getRole();
				int userID = userList.get(0).getUserid();
				HttpSession session = req.getSession();
				session.setAttribute("error", null);
				session.setAttribute("id", userID);
				session.setAttribute("role", role);
				LocalDate today = matchService.getDate();
				session.setAttribute("today", today);
				session.setAttribute("SomthingWentWrong", null);
				session.setAttribute("RegisterSuccessful", null);
				if (role.equals("Admin")) {

					req.setAttribute("admin", "admin");
					RequestDispatcher rd = req.getRequestDispatcher("adminHome.jsp");
					rd.forward(req, res);

				} else if (role.equals("User")) {

					req.setAttribute("user", "user");
					RequestDispatcher rd = req.getRequestDispatcher("userHome.jsp");
					rd.forward(req, res);
				}

			} else {
				throw new NotFound();
			}
		} catch (SQLException e) {

			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} catch (NotFound e) {

			try {
				HttpSession session = req.getSession();
				session.setAttribute("SomthingWentWrong", null);
				session.setAttribute("RegisterSuccessful", null);
				session.setAttribute("error", e.getMessage());
				res.sendRedirect("index.jsp");

			} catch (IOException e1) {

				Logger.printStackTrace(e1);
				Logger.runTimeException(e1.getMessage());
			}
		} catch (IOException e2) {

			Logger.printStackTrace(e2);
			Logger.runTimeException(e2.getMessage());
		} catch (ServletException e3) {
			Logger.printStackTrace(e3);
			Logger.runTimeException(e3.getMessage());
		}

	}

}
