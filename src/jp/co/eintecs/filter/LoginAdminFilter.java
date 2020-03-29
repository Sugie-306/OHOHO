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
 * 管理者ログインか確認するフィルター
 * @author sugie
 * */
public class LoginAdminFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		try {

			//管理者以外はTOPページに飛ばす
			HttpSession session = ((HttpServletRequest) req).getSession(false);
			String adminFlag = (String)session.getAttribute("adminFlag");

			if (adminFlag == null || !adminFlag.equals("1")) {
				RequestDispatcher rd = req.getRequestDispatcher("TopServlet");
				rd.forward(req, res);
			}
			//Filterを実行
			chain.doFilter(req, res);

		} catch (ServletException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig arg0) {
	}

}
