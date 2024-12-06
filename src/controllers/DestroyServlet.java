package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import util.DBUtil;

@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String _token = req.getParameter("_token");
		if(_token != null && _token.equals(req.getSession().getId())) {
			EntityManager em = DBUtil.createEntityManager();

			// セッションスコープからメッセージのIDを取得して
            // 該当のIDのメッセージ1件のみをデータベースから取得
			Message m = em.find(Message.class,(Integer)req.getSession().getAttribute("message_id"));

			em.getTransaction().begin();
			em.remove(m);					// データ削除
			em.getTransaction().commit();
			req.getSession().setAttribute("flush", "削除が完了しました");
			em.close();

			// セッションスコープ上の不要になったデータを削除
			req.getSession().removeAttribute("message_id");

			// indexページへリダイレクト
			resp.sendRedirect(req.getContextPath()+"/index");
		}
	}

}
