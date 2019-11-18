package com.fdesign.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InvalidateSessionServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		// Invalidate the session and removes any attribute related to it
		session.invalidate();
		
		// Get an HttpSession related to this request, if no session exist don't
		// create a new one. This is just a check to see after invalidation the
		// session will be null.
		session = req.getSession(false);
		
		resp.getWriter().println("Session : " + session);
		return;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
