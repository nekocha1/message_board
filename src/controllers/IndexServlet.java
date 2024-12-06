package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import util.DBUtil;

@WebServlet("/index")
public class IndexServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();
		//データベースへ問い合わせる。戻り値はリスト(Messageオブジェクト型)
		List<Message> messages = em.createNamedQuery("getAllMessages",Message.class).getResultList();

		//リストの件数を出力
		response.getWriter().append(Integer.valueOf(messages.size()).toString());
		em.close();
	}
}
