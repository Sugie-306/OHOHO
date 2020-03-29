package jp.co.eintecs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.eintecs.dao.LoginDAO;
import jp.co.eintecs.filter.Security;

/**
/*ログイン用サーブレット
/*@author sugie
/*
*/

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//変数を用意
		String nextPage;
		int loginType = 0;

		//入力データ文字化け対策
		request.setCharacterEncoding("UTF-8");

		//フォーム入力データ受信
		String username = Security.escape(request.getParameter("userName"));
		String pass = Security.escape(request.getParameter("pass"));

		if (LoginDAO.userNameCheck(username)) {
			loginType = LoginDAO.userLogin(username, pass, request);
		} else {
			nextPage = "loginerror.jsp";
			loginType = 9;
		}

		//遷移先ページを振り分け

		switch (loginType) {

		case 0://一般ユーザ
			nextPage = "TopServlet";
			break;

		case 1://管理者
			nextPage = "admin.jsp";
			break;

		default://登録なし
			nextPage = "loginerror.jsp";
			break;
		}

		//遷移先へ移動
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
