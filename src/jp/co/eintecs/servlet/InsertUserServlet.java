package jp.co.eintecs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.eintecs.beans.UserBean;
import jp.co.eintecs.dao.UserDAO;
import jp.co.eintecs.filter.Security;

/**
/*会員登録をするサーブレット
/*@author maimai
 */

public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//変数の格納
		String userid = request.getParameter("userid");
		String username = Security.escape(request.getParameter("username"));
		String userpass = Security.escape(request.getParameter("userpass"));
		String flg = request.getParameter("flg");
		String adminflag = request.getParameter("adminflag");
		String name = Security.escape(request.getParameter("name"));
		String post = Security.escape(request.getParameter("post"));
		String address = Security.escape(request.getParameter("address"));
		String phone = Security.escape(request.getParameter("phone"));
		String mail = Security.escape(request.getParameter("mail"));
		String message;

		//検索メソッドで登録済みか確認
		//List<UserBean> list = UserDAO.getList

		UserBean user = new UserBean();
		//Beanの中に値をセット
		user.setUsername(username);
		user.setUserpass(userpass);
		user.setName(name);
		user.setPost(post);
		user.setAddress(address);
		user.setPhone(phone);
		user.setMail(mail);

		String nextPage = null;
		//検索メソッドで登録済みか確認
		if (UserDAO.checkRegisteredUser(username)) {

			//登録
			if (UserDAO.insertPerson(user)) {
				//登録成功
				//SQL一番下の行の取得結果を受け取る
				String lastid = UserDAO.getLastid();
				//一番下のIDをセット
				user.setUserid(lastid);
				if (UserDAO.insertCustomer(user)) {
					//登録成功
					request.setAttribute("user", user);
					nextPage = "customer-output2.jsp";
				} else {
					message = "登録に失敗しました<br>"
							+ "もう一度試してください。<br>"
							+ "<a href='customer-input.jsp'>戻る</a>";
					request.setAttribute("message", message);
				}
			} else

			{
				//登録失敗
				message = "登録に失敗しました<br>"
						+ "もう一度試してください。<br>"
						+ "<a href='customer-input.jsp'>戻る</a>";
				request.setAttribute("message", message);
			}
		} else {
			System.out.println("ユーザーは登録済みです");
			request.setAttribute("user", user);
			nextPage = "customer-output3.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
