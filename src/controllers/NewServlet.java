package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;

@WebServlet("/new")
public class NewServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//CSRF対策
		req.setAttribute("_token", req.getSession().getId());

		//
		req.setAttribute("message", new Message());

		//new.jspにフォワード
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/messages/new.jsp");
        rd.forward(req, resp);
	}

}
