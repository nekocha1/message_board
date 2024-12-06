package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import util.DBUtil;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID=1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String _token = req.getParameter("_token");

		if(_token != null && _token.equals(req.getSession().getId())) {//CSRF対策セッションIDが等しいか
			EntityManager em = DBUtil.createEntityManager();
			em.getTransaction().begin();

			Message m = new Message();
			String title=req.getParameter("title");
			m.setTitle(title);
			String content = req.getParameter("content");
			m.setContent(content);

			Timestamp currentTime = new Timestamp(System.currentTimeMillis());//現在日時の情報を取得
			m.setCreated_at(currentTime);
			m.setUpdated_at(currentTime);

			em.persist(m);					//データベースをセーブ
			em.getTransaction().commit();	//コミット
			em.close();						//データベースを閉じる

			resp.sendRedirect(req.getContextPath()+"/index");//リダイレクト

		}
	}

}
