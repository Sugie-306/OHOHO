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
/*管理側　注文詳細一覧用サーブレット
/*@author sugie
/*
*/

public class OrderListDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字化け対策
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//bookIdを取得
		String orderId = request.getParameter("id");

		//検索結果を変数へ格納
		List<OrderBean> list = OrderListDAO.getListByOrderId(orderId);
		List<OrderBean> list2 = OrderListDAO.getListByUser2(orderId);

		//リクエストにセット
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);

		//遷移先へ移動
		RequestDispatcher rd = request.getRequestDispatcher("admin-orderdetail.jsp");
		rd.forward(request, response);
	}

}
