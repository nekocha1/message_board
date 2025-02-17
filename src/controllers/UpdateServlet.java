package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import models.validators.MessageValidator;
import util.DBUtil;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String _token = req.getParameter("_token");
		if(_token != null && _token.equals(req.getSession().getId())) {
			EntityManager em = DBUtil.createEntityManager();

			// セッションスコープからメッセージのIDを取得して
            // 該当のIDのメッセージ1件のみをデータベースから取得
			Message m = em.find(Message.class,(Integer)(req.getSession().getAttribute("message_id")));

			// フォームの内容を各フィールドに上書き
			String title=req.getParameter("title");
			m.setTitle(title);

			String content = req.getParameter("content");
			m.setContent(content);

			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			m.setUpdated_at(currentTime);// 更新日時のみ上書き

			// バリデーションを実行してエラーがあったら新規登録のフォームに戻る
            List<String> errors = MessageValidator.validate(m);
            if(errors.size() > 0) {
                em.close();

                // フォームに初期値を設定、さらにエラーメッセージを送る
                req.setAttribute("_token", req.getSession().getId());
                req.setAttribute("message", m);
                req.setAttribute("errors", errors);

                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/messages/new.jsp");
                rd.forward(req, resp);
            } else {
				// データベースを更新
				em.getTransaction().begin();
				em.getTransaction().commit();
				req.getSession().setAttribute("flush","更新が完了しました");
				em.close();

				// セッションスコープ上の不要になったデータを削除
				req.getSession().removeAttribute("message_id");

				// indexページへリダイレクト
				resp.sendRedirect(req.getContextPath()+"/index");
            }
		}
	}

}
