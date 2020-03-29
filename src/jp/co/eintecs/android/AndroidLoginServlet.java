package jp.co.eintecs.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ログインチェック（Android）
 * @author matsumoto
 */
public class AndroidLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		//端末名
		final String terminalName = "angel";

		//フラグ
		boolean flag = false;

		System.out.println("Start!! AndroidLogin!!");
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//端末コード取得
		if(terminalName.equals(request.getParameter("terminal"))) {
			flag = true;
		}

		//レスポンスに値を書き込む
		response.setContentType("application/json");

		//responseに書き込むprintwriter作成
		PrintWriter out = response.getWriter();

		System.out.println("ログインチェック:"+flag);
		//登録可否をセット
		out.print(flag);

		//書き込み！
		out.flush();
	}

}
