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
/*トップページ用サーブレット
/*@author AKARI
 *@author sugie
 *
 */
/* akari
*/

public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//doGet用
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		//文字化け防止コード
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//新着順を表示させるメソッド
		List<BookBean> list = new ArrayList<BookBean>();
		list = BookBeanDAO.getnewBook();

		//取得したDBデータをリクエスト属性に格納
		request.setAttribute("list", list);

		//ランキングを表示させるメソッド
		List<BookBean> rankList = new ArrayList<BookBean>();
		rankList = BookBeanDAO.getBookRank();

		//取得したDBデータをリクエスト属性に格納
		request.setAttribute("rankList", rankList);

		//topページへ移動する
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);

	}

	//doPost用
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		doGet(request, response);
	}
}