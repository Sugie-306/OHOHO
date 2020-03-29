package jp.co.eintecs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.eintecs.beans.BookBean;
import jp.co.eintecs.dao.BookBeanDAO;
import jp.co.eintecs.filter.Security;

/**
/*書籍検索用サーブレット
/*@author sugie
/*
*/

public class SearchBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化け対策
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//入力フォームからゲット
		String c_id = Security.escape(request.getParameter("category"));
		String word = Security.escape(request.getParameter("word"));

		//検索結果を取得
		List<BookBean> Booklist = BookBeanDAO.searchBook(word, c_id);

		//検索結果をセット
		request.setAttribute("list", Booklist);

		//検索ワードをセット
		request.setAttribute("word", word);

		//カテゴリをセット
		String c_name = "";
		switch (c_id) {
		case "0":
			c_name = "全ジャンル";
			break;
		case "1":
			c_name = "小説";
			break;
		case "2":
			c_name = "コミック";
			break;
		case "3":
			c_name = "エッセイ";
			break;
		case "4":
			c_name = "オリジナル";
			break;
		}

		//カテゴリネームをリクエストにセット
		request.setAttribute("c_name", c_name);

		//検索結果ページに移動
		RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
		rd.forward(request, response);

	}

}
