package jp.co.eintecs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
/*ログアウト用サーブレット
/*@author sugie
/*
*/

public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// セッションを取得.
		HttpSession session = request.getSession(false);

		if (null != session) {

			// セッションを終了.
			session.invalidate();
			System.out.println("ログアウトしました");
		}

		// ログアウト完了ページへ.
		response.sendRedirect("logout-output.jsp");
	}
}
