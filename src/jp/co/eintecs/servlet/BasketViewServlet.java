package jp.co.eintecs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.eintecs.beans.BasketBean;
import jp.co.eintecs.dao.BasketDAO;

/**
/*カートの中を見るサーブレット
/*@author maimai
/*
*/
public class BasketViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {

		//文字化け対策
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		//返却用メッセージ
		String message;

		//セッションを取得する
		HttpSession session = req.getSession();

		//ユーザーIDの取得
		String Uid = (String) session.getAttribute("userId");
		//表示用メソッドを呼び出し結果を受け取る
		List<BasketBean> list = BasketDAO.getCartList(Uid);

		//表示結果によってメッセージを変更する
		if (list.size() == 0) {
			//0件のとき
			message = "買い物かごには現在何も入っていません。";
		} else {
			//取得件数を格納
			message = "検索結果は" + list.size() + "件です。";
		}

		//表示用メッセージをリクエスト属性にセット
		req.setAttribute("message", message);
		//結果をリクエスト属性にセット
		req.setAttribute("list", list);

		//遷移先を指定
		String nextPage = "cart-show.jsp";
		//フォワード
		RequestDispatcher rd = req.getRequestDispatcher(nextPage);
		rd.forward(req, res);
	}

	protected void doGet(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {

		//文字化け対策
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		//返却用メッセージ
		String message;

		//セッションを取得する
		HttpSession session = req.getSession();

		//ユーザーIDの取得
		String Uid = (String) session.getAttribute("userId");

		//表示用メソッドを呼び出し結果を受け取る
		List<BasketBean> list = BasketDAO.getCartList(Uid);

		//表示結果によってメッセージを変更する
		if (list.size() == 0) {
			//0件のとき
			message = "買い物かごには現在何も入っていません。";
		} else {
			//取得件数を格納
			message = "検索結果は" + list.size() + "件です。";
		}

		//表示用メッセージをリクエスト属性にセット
		req.setAttribute("message", message);
		//結果をリクエスト属性にセット
		req.setAttribute("list", list);

		//遷移先
		String nextPage = "cart-show.jsp";

		RequestDispatcher rd = req.getRequestDispatcher(nextPage);
		rd.forward(req, res);
	}
}
