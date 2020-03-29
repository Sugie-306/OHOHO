package jp.co.eintecs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.eintecs.beans.BasketBean;
import jp.co.eintecs.beans.OrderBean;
import jp.co.eintecs.dao.BasketDAO;
import jp.co.eintecs.dao.LoginDAO;
import jp.co.eintecs.dao.OrderDAO;
import jp.co.eintecs.filter.Security;

/**
/*注文実行用サーブレット
/*@author maimai
/*
*/

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		//文字化け対策
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		//ユーザーIDを取り出す
		String userid = LoginDAO.getUserID(req);

		//入力内容をBeanにセットする
		OrderBean ob = new OrderBean();
		ob.setUser_id(userid);
		ob.setOrder_Post(Security.escape(req.getParameter("post")));
		ob.setOrder_Address(Security.escape(req.getParameter("address")));
		ob.setOrder_Name(Security.escape(req.getParameter("name")));
		ob.setOrder_Phone(Security.escape(req.getParameter("phone")));
		ob.setPay_Rule(Security.escape(req.getParameter("payrule")));

		//注文テーブルに書き込む
		OrderDAO.addOrderlist(ob);

		//カート情報を取り出す
		List<BasketBean> list = BasketDAO.getCartList(userid);

		//オーダーID取得　
		String order_id = OrderDAO.getOrderId(userid);

		//カート情報を注文明細に書き込む
		for (int i = 0; i < list.size(); i++) {

			//購入数を取得
			String count = String.valueOf(list.get(i).getCount());
			//ブックIDを取得
			String bookid = list.get(i).getBookid();

			//注文明細更新
			OrderDAO.insertOrderDetail(order_id, bookid, count);

		}

		//購入決定したらカートの情報消す
		BasketDAO.deleteCart(userid);

		RequestDispatcher rd = req.getRequestDispatcher("purchase-output.jsp");
		rd.forward(req, res);

	}
}
