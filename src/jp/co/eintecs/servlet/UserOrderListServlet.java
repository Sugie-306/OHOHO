package jp.co.eintecs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.eintecs.beans.OrderBean;
import jp.co.eintecs.dao.OrderListDAO;

/**
 *マイページ・注文の詳細ページ表示用
 * @author sugie
 *
 */

public class UserOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		//文字化け対策
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		//bookIdを取得
		String orderId = req.getParameter("id");

		//検索結果を変数へ格納
		List<OrderBean> list = OrderListDAO.getListByOrderId(orderId);
		List<OrderBean> list2 = OrderListDAO.getListByUser2(orderId);

		//検索結果をリクエストへセット
		req.setAttribute("list", list);
		req.setAttribute("list2", list2);

		//遷移先へ異動
		RequestDispatcher rd = req.getRequestDispatcher("user-orderdetail.jsp");
		rd.forward(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
