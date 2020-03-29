package jp.co.eintecs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.eintecs.beans.UserBean;
import jp.co.eintecs.dao.UserDAO;
import jp.co.eintecs.filter.Security;

/**
 * 会員更新サーブレット
 * @author sasaki
 */

public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//文字化け対策
		req.setCharacterEncoding("UTF-8");

		//ユーザーIDを取得
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");

		//メッセージ変数を宣言
		String message = "";

		//入力を取得
		String name = Security.escape(req.getParameter("name"));
		String address = Security.escape(req.getParameter("address"));
		String post = Security.escape(req.getParameter("post"));
		String phone = Security.escape(req.getParameter("phone"));
		String mail = Security.escape(req.getParameter("mail"));
		String username = Security.escape(req.getParameter("username"));
		String userpass = Security.escape(req.getParameter("userpass"));
		System.out.println(name + "名前は取得できているか");


		//UserBeanのインスタンスを生成
		UserBean user = new UserBean();

		//UserBeanのインスタンスに値をセット
		user.setName(name);
		user.setAddress(address);
		user.setPost(post);
		user.setPhone(phone);
		user.setMail(mail);
		user.setUsername(username);
		user.setUserpass(userpass);
		user.setUserid(userId);

		//DB更新(person)
		if (UserDAO.updatePerson(user)) {
			//DB更新(customer)
			if (UserDAO.updateCustomer(user)) {
				message = "更新に成功しました。";
				System.out.println("更新に成功しました。");
			} else {
				message = "更新に失敗しました。";
				System.out.println("更新に失敗しました。1");
			}
		} else {
			message = "更新に失敗しました。";
			System.out.println("更新に失敗しました。2");
		}

		//メッセージをsetAttributeに格納
		req.setAttribute("message", message);
		//遷移先へ移動
		RequestDispatcher rd = req.getRequestDispatcher("customer-output.jsp");
		rd.forward(req, res);
	}
}
