package jp.co.eintecs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.eintecs.dao.BasketDAO;
import jp.co.eintecs.dao.OrderDAO;

/**
/*カートに追加するサーブレット
/*@author maimai
/*
*/

public class BasketAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req,
			HttpServletResponse res)throws ServletException, IOException {

		//文字化け対策
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		//ユーザーID取得
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userId");

		//前ページから（リザルト）取得
		String bookid = req.getParameter("bookid");
		String count = req.getParameter("count");

		//選択された商品がカートに登録されているか
		if(OrderDAO.ChekBook(bookid, userId)) {
			BasketDAO.addCart(count, userId, bookid);	//合算;
		}else {
			BasketDAO.insertCart(userId, bookid, count);
			}

		//移動先
				String nextPage = "BasketViewServlet";
				RequestDispatcher rd = req.getRequestDispatcher(nextPage);
				rd.forward(req, res);

		}


}
