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
/*管理側　注文一覧用サーブレット
/*@author sugie
/*
*/

public class OrderListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化け対策
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//検索結果を取得
		List<OrderBean> list1 = OrderListDAO.getListByOrder();

		//検索結果をセット
		request.setAttribute("list1", list1);

		//検索結果ページに移動
		RequestDispatcher rd = request.getRequestDispatcher("admin-orderlist.jsp");
		rd.forward(request, response);

	}

}
