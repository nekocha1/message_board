package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import util.DBUtil;

@WebServlet("/show")
public class ShowServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		// 該当のIDのメッセージ1件のみをデータベースから取得
		Message m = em.find(Message.class,Integer.parseInt(req.getParameter("id")));
		em.close();

		// メッセージデータをリクエストスコープにセットしてshow.jspを呼び出す
		req.setAttribute("message",m);
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/messages/show.jsp");
        rd.forward(req, resp);
	}


}
