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

/**
 * 商品管理画面用サーブレット
 * @author akari
 */

public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		//書籍テーブルを全件取得
		List<BookBean> bookList = BookBeanDAO.getBook2();

		//書籍リストをリクエストへセット
		req.setAttribute("list", bookList);

		//遷移先へ移動
		RequestDispatcher rd = req.getRequestDispatcher("admin-product-input.jsp");
		rd.forward(req, res);
	}
}
