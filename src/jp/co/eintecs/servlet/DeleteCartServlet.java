package jp.co.eintecs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.eintecs.dao.BasketDAO;
import jp.co.eintecs.dao.LoginDAO;

/**
/*カートの中身を全部削除するサーブレット
/*@author maimai
 *
 *
 */
/*@author maimai
*/
public class DeleteCartServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		//ユーザID取得
		String userId = LoginDAO.getUserID(req);

		//カートクリア（全件削除）
		BasketDAO.deleteCart(userId);

		//移動
		RequestDispatcher rd = req.getRequestDispatcher("BasketViewServlet");
		rd.forward(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		//ユーザID取得
		String userId = LoginDAO.getUserID(req);

		//カートクリア（全件削除）
		BasketDAO.deleteCart(userId);

		//移動
		RequestDispatcher rd = req.getRequestDispatcher("BasketViewServlet");
		rd.forward(req, res);

}
}
