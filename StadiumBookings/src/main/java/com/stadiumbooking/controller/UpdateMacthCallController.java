package com.stadiumbooking.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stadiumbooking.daoimpl.MatchDaoImpl;
import com.stadiumbooking.model.Match;
import com.stadiumbooking.model.StadiumDetalis;

@WebServlet("/updateMatchCall")
public class UpdateMacthCallController extends HttpServlet {

	final MatchDaoImpl matchDao=new MatchDaoImpl();
	
	@Override
	public void service(HttpServletRequest req,HttpServletResponse res) {
	
	
		
		int matchId=Integer.parseInt(req.getParameter("matchId"));
		try {
			
			List<Match> match = matchDao.getMatchByMatchId(matchId);
			req.setAttribute("singleMatch", match);
			
		      RequestDispatcher rd = req.getRequestDispatcher("updateMatch.jsp");			
					rd.forward(req, res);
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		} catch (IOException e2) {
		
			e2.printStackTrace();
		} catch (ServletException e1) {
					e1.printStackTrace();
		}
	}
}