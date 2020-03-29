package jp.co.eintecs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.eintecs.beans.BookBean;
import jp.co.eintecs.dao.AuthorDAO;
import jp.co.eintecs.dao.BookBeanDAO;
import jp.co.eintecs.filter.Security;

/**
/*商品登録用サーブレット
/*@author akari
/*
*/
public class InsertBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		//文字化け対策
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		//著者名から著者IDを取得
		String authorName = req.getParameter("author");
		int author_id = BookBeanDAO.isAuthor(authorName);
		if (author_id == 0) { //著者登録がないとき
			//著者登録が無いので新規登録
			if (AuthorDAO.addAuthor(authorName)) {
				System.out.println("addAuthor成功");
				//登録した著者IDを取得
				author_id = BookBeanDAO.isAuthor(authorName);
			} else {
				System.out.println("addAuthor失敗");
			}
		} else {
			System.out.println("登録済みのAuthorです。進む。");
		}

		//フォームからの入力情報をbeanに格納
		BookBean book = new BookBean();
		book.setBook_name(Security.escape(req.getParameter("name")));
		book.setAuthor_id(String.valueOf(author_id));
		book.setCategory_id(Security.escape(req.getParameter("category")));
		book.setImage(Security.escape(req.getParameter("image")));
		book.setIsbn(Security.escape(req.getParameter("isbn")));
		book.setStock(Security.escape(req.getParameter("stock")));
		book.setPrice(Security.escape(req.getParameter("price")));
		book.setExplanation(Security.escape(req.getParameter("contents")));

		//商品挿入(登録)
		if (BookBeanDAO.addBook(book)) {
			//成功時
			System.out.println("成功！");
			//結果表示ページに遷移
			RequestDispatcher rd = req.getRequestDispatcher("admin-product-output.jsp");
			rd.forward(req, res);

		} else {
			//失敗時
			System.out.println("失敗...");
			//結果表示ページに遷移
			RequestDispatcher rd = req.getRequestDispatcher("admin-product-output2.jsp");
			rd.forward(req, res);

		}

	}
}
