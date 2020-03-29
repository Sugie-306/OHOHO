package jp.co.eintecs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.eintecs.dao.TaxDAO;
import jp.co.eintecs.filter.Security;

/**
/*税率を変更するサーブレット
/*@author sugie
/*
*/

public class UpdateTaxServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		//文字化け対策
		req.setCharacterEncoding("UTF-8");

		//メッセージ変数を宣言
		String message = "";

		//入力値を取得
		String taxrate = Security.escape(req.getParameter("tax"));

			if (TaxDAO.updateTax(taxrate)) {
				message = "更新に成功しました。";
				System.out.println("更新に成功しました。");
			} else {
				message = "更新に失敗しました。";
				System.out.println("更新に失敗しました。1");
			}

		//メッセージをsetAttributeに格納
		req.setAttribute("message", message);
		//遷移先へ移動
		RequestDispatcher rd = req.getRequestDispatcher("etc-output.jsp");
		rd.forward(req, res);

	}

}
