package jp.co.eintecs.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 更新用検索サーブレット（Android）
 * */
public class AndroidSearchBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字コードセット
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		System.out.println("Start! AndroidSearch!!");

		//入力ワード取得
		String word = request.getParameter("bookname");
		System.out.println("word:"+word);

		//json準備
		String json = null;

		//jacksonのオブジェクトマッパー作成（json変換機能があります。)
		ObjectMapper mapper = new ObjectMapper();

		//検索データ取得
		List<AndroidBookBean> lab = AndroidDAO.searchBook(word, "0");

		//beanのarraylistをjsonに変換するパターン
		json = mapper.writeValueAsString(lab);

		//jsonの場合下記を記入
		response.setContentType("application/json");
		//responseに書き込むprintwriter作成
		PrintWriter out = response.getWriter();

		//jsonを送信する場合はjsonを引数に指定
		out.println(json);
		//テスト出力
		System.out.println(json);
		//書き込み！
		out.flush();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("hoge");
	}
}
