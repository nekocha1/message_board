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

@WebServlet("/edit")
public class EditServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {EntityManager em = DBUtil.createEntityManager();

    // 該当のIDのメッセージ1件のみをデータベースから取得
    Message m = em.find(Message.class, Integer.parseInt(req.getParameter("id")));

    em.close();

    // メッセージ情報とセッションIDをリクエストスコープに登録
    req.setAttribute("message", m);
    req.setAttribute("_token", req.getSession().getId());

    // メッセージIDをセッションスコープに登録
    req.getSession().setAttribute("message_id", m.getId());

    RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/messages/edit.jsp");
    rd.forward(req,resp);

	}

}
