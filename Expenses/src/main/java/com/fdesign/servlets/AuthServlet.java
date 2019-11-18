package com.fdesign.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdesign.daos.UserDao;
import com.fdesign.models.User;

public class AuthServlet extends HttpServlet {

	private UserDao userDao = UserDao.currentImplementation;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Headers",
				"Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");
		super.service(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if ("/Expenses/auth/login".equals(req.getRequestURI())) {
			ObjectMapper om = new ObjectMapper();
			User credentials = (User) om.readValue(req.getReader(), User.class);
			User loggedInUser = userDao.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword());
			if (loggedInUser == null) {
				resp.setStatus(401);
				return;
			} else {
				if (loggedInUser.getUser_role_id() == 1) {
					resp.setStatus(200);
					req.getSession().setAttribute("user", loggedInUser);
					resp.getWriter().write(om.writeValueAsString(loggedInUser));
					return;
				} else {
					resp.setStatus(201);
					req.getSession().setAttribute("user", loggedInUser);
					resp.getWriter().write(om.writeValueAsString(loggedInUser));
					return;
				}
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if ("/Expenses/auth/session-user".equals(req.getRequestURI())) {
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(req.getSession().getAttribute("user"));
			resp.getWriter().write(json);
		}
	}
}
