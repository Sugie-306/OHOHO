package jp.co.eintecs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.eintecs.dao.TaxDAO;


/**
/*税率変更画面のサーブレット
/*@author sugie
/*
*/

public class AdminEtcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String tax = String.format("%.1f",TaxDAO.NowTax());
		req.setAttribute("tax", tax);
		RequestDispatcher rd = req.getRequestDispatcher("etc.jsp");
		rd.forward(req, res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
