package com.fdesign.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdesign.daos.ReimbursementDao;
import com.fdesign.daos.UserDao;
import com.fdesign.models.Reimbursement;
import com.fdesign.models.User;

public class ReimbursementServlet extends HttpServlet {

	private ReimbursementDao reimbursementDao = ReimbursementDao.currentImplementation;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ObjectMapper om = new ObjectMapper();
		String auth = req.getParameter("author");
		String stat = req.getParameter("statusId");
		List<Reimbursement> reimbursements = new ArrayList<>();
		if (auth != null && stat != null) {
			int author = Integer.parseInt(auth);
			int statusId = Integer.parseInt(stat);
			reimbursements = reimbursementDao.findByAuthorAndStatus(author, statusId);
		} else if (auth != null) {
			int author = Integer.parseInt(auth);
			reimbursements = reimbursementDao.findReimbursementByUserAuthor(author);
		} else if (stat != null) {
			int statusId = Integer.parseInt(stat);
			reimbursements = reimbursementDao.findReimbursementByUserStatus(statusId);
		} else {
			reimbursements = reimbursementDao.findAll();
		}

		String json = om.writeValueAsString(reimbursements);

		resp.addHeader("content-type", "application/json");
		resp.getWriter().write(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		Reimbursement r = (Reimbursement) om.readValue(req.getReader(), Reimbursement.class);

		reimbursementDao.save(r);

		String json = om.writeValueAsString(r);

		resp.getWriter().write(json);
		resp.setStatus(201); // created status code
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		Reimbursement ruling = (Reimbursement) om.readValue(req.getReader(), Reimbursement.class);

		reimbursementDao.updateReimbursement(ruling.getReimbursement_resolution(), ruling.getReimbursement_author(), ruling.getReimbursement_status_id(), ruling.getReimbursement_id());

		String json = om.writeValueAsString(ruling);
		resp.getWriter().write(json);
		resp.setStatus(201); // created status code
	}

}
