package jp.co.eintecs.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.eintecs.filter.Security;

/**
 * 商品登録用サーブレット（Android）
 * @author matsumoto
 */
public class AndroidInsertBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Start!! AndroidInsert!!");
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		//フォームからの入力情報をbeanに格納
		//同時にエスケープ処理を行う
		AndroidBookBean book = new AndroidBookBean();
		book.setBook_name(Security.escape(request.getParameter("name")));
		book.setCategory_id(Security.escape(request.getParameter("category")));
		book.setImage(Security.escape(request.getParameter("image")));
		book.setIsbn(Security.escape(request.getParameter("isbn")));
		book.setStock(Security.escape(request.getParameter("stock")));
		book.setPrice(Security.escape(request.getParameter("price")));
		book.setExplanation(Security.escape(request.getParameter("exp")));

		//System.out.println("name:" + request.getParameter("author"));

		//著者名から著者IDを取得
		String authorName = request.getParameter("author");
		int author_id = AndroidDAO.isAuthor(authorName);

		if (author_id == 0) { //著者登録がないとき
			//著者登録が無いので新規登録
			AndroidDAO.addAuthor(authorName);
			//登録した著者IDを取得
			author_id = AndroidDAO.isAuthor(authorName);
		}

		//author_idをbeanにセット
		book.setAuthor_id(String.valueOf(author_id));

		//DAOを呼び出して商品登録
		boolean flag = AndroidDAO.addBook(book);

		//レスポンスに値を書き込む
		response.setContentType("application/json");

		//responseに書き込むprintwriter作成
		PrintWriter out = response.getWriter();

		//登録可否をセット
		out.print(flag);
		System.out.println("InsertFlag:" + flag);

		//書き込み！
		out.flush();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get");
	}
}
