package jp.co.eintecs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.eintecs.beans.BasketBean;
import jp.co.eintecs.beans.OrderBean;
import jp.co.eintecs.beans.UserBean;
import jp.co.eintecs.dao.OrderListDAO;
import jp.co.eintecs.dao.UserDAO;

/**
/*注文確認用サーブレット
/*@author sugie
 *@author maimai
 *
 */
/*@author maimai
*/

public class OrderViewServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {
		//変数を先遣
		String nextPage = "";
		//文字化け対策
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		//bookIdを取得
		String orderId = req.getParameter("id");

		//検索結果を格納
		List<OrderBean> list = OrderListDAO.getListByOrderId(orderId);
		List<OrderBean> list2 = OrderListDAO.getListByUser2(orderId);

		//検索結果をリクエストにセット
		req.setAttribute("list", list);
		req.setAttribute("list2", list2);

		//カートの内容をセッション情報から取得
		HttpSession session = req.getSession();
		List<BasketBean> cartlist = (List<BasketBean>) session.getAttribute("cartlist");

		//userIdから顧客の情報を取得
		String userId = (String) session.getAttribute("userId");
		UserBean userList = UserDAO.getUserData(userId);

		//カートの中身が０件だったとき
		if (cartlist.size() == 0) {
			//カートを見るサーブレットへ遷移先を設定
			nextPage = "BasketViewServlet";
		} else {
			//検索結果をリクエストへ格納
			req.setAttribute("list", cartlist);
			req.setAttribute("userlist", userList);
		}

		//注文確認画面へ遷移
		nextPage = "purchase-input.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(nextPage);
		rd.forward(req, res);

	}
}
