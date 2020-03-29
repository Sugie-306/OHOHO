package jp.co.eintecs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登録ユーザ用フィルター
 * @author sugie
 *
 */
public class LoginUserFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		try {

			//ユーザー以外はログインページに飛ばす
			HttpSession session = ((HttpServletRequest) req).getSession(false);
			//セッションから情報を取得
			String userId = (String) session.getAttribute("userId");
			String adminFlag = (String) session.getAttribute("adminFlag");

			if (adminFlag != null) {
				if (adminFlag.equals("1")) {
					System.out.println("フィルター：管理者");
					RequestDispatcher rd = req.getRequestDispatcher("TopServlet");
					rd.forward(req, res);
				} else if (userId != null) {
					//Filterを実行
					System.out.println("フィルター：ユーザー");
					chain.doFilter(req, res);
				}
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
				rd.forward(req, res);
			}

		} catch (ServletException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
