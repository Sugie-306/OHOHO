package jp.co.eintecs.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.eintecs.beans.BookBean;
import jp.co.eintecs.dao.BookBeanDAO;

/**
/*書籍一覧を表示させるサーブレット
/*@author sasaki
/*
*/
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字化け対策
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//BookBean型のArrayListを宣言
		List<BookBean> list = new ArrayList<BookBean>();

		//メソッドを呼び出し結果を受け取る
		list = BookBeanDAO.getBook();

		//結果をリクエスト属性にセット
		request.setAttribute("list", list);

		//結果表示ページに遷移
		RequestDispatcher rd = request.getRequestDispatcher("books.jsp");
		rd.forward(request, response);
	}
}
