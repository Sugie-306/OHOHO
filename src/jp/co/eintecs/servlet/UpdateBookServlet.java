package jp.co.eintecs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.eintecs.beans.BookBean;
import jp.co.eintecs.dao.BookBeanDAO;
import jp.co.eintecs.filter.Security;

/**
*管理側　書籍情報を更新するサーブレット
*@author akari
*
*/

public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		//文字化け対策
		req.setCharacterEncoding("UTF-8");

		String message = "";
		//入力を取得
		String book_name = (Security.escape(req.getParameter("book_name")));
		String author_id = (Security.escape(req.getParameter("author_id")));
		String price = (Security.escape(req.getParameter("price")));
		String category_id = (Security.escape(req.getParameter("category_id")));
		String isbn = (Security.escape(req.getParameter("isbn")));
		String stock = (Security.escape(req.getParameter("stock")));
		String image = (Security.escape(req.getParameter("image")));
		String contents = (Security.escape(req.getParameter("contents")));
		String book_id = (Security.escape(req.getParameter("book_id")));

		//BookBeanのインスタンスを生成
		BookBean book = new BookBean();

		//BookBeanのインスタンスに値をセット
		book.setBook_name(book_name);
		book.setAuthor_id(author_id);
		book.setPrice(price);
		book.setCategory_id(category_id);
		book.setIsbn(isbn);
		book.setStock(stock);
		book.setImage(image);
		book.setExplanation(contents);
		book.setBook_id(book_id);

		//DB更新
		if (BookBeanDAO.updateBook(book)) {
			message = "更新に成功しました。";
			System.out.println("更新に成功しました。");
		} else {
			req.setAttribute("message", message);
			message = "更新に失敗しました。";
			System.out.println("更新に失敗しました。");
		}

		//メッセージをリクエストに格納
		req.setAttribute("message", message);
		//遷移先へ移動
		RequestDispatcher rd = req.getRequestDispatcher("admin-product-output4.jsp");
		rd.forward(req, res);
	}
}
