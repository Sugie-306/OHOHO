package jp.co.eintecs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.eintecs.beans.OrderBean;
import jp.co.eintecs.beans.UserBean;
import jp.co.eintecs.dao.OrderListDAO;
import jp.co.eintecs.dao.UserDAO;

/**
 *マイページ表示用
 *@author sasaki
 * @author sugie
 *
 */

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//文字化け対策
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		//ユーザーIDを取得
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");

		//----おおまかな注文履歴の表示(customer.jsp)

		//OrderListDAOのメソッド実行
		List<OrderBean> orderlist = OrderListDAO.getListByUserId(userId);

		//リストをsetAttributeに格納
		req.setAttribute("orderlist", orderlist);

		//----会員情報の取得

		//UserDAOのメソッド実行
		UserBean userdata = UserDAO.getUserData(userId);

		//リストをsetAttributeに格納
		req.setAttribute("userdata", userdata);

		//マイページへ
		String nextPage = "customer.jsp";

		//次ページへ
		RequestDispatcher rd = req.getRequestDispatcher(nextPage);
		rd.forward(req, res);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
