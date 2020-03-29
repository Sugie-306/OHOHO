package jp.co.eintecs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.eintecs.beans.BookBean;
import jp.co.eintecs.dao.BookBeanDAO;

/**
/*書籍の詳細を表示するサーブレット
/*@author sugie
/*
*/
public class DetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化け対策
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//bookIdを取得
		String bookId = request.getParameter("bookid");
		//検索結果を格納
		BookBean book = BookBeanDAO.detailBook(bookId);
		//リクエストにセット
		request.setAttribute("book",book);
		//遷移先
		RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
		rd.forward(request,response);

	}
}
