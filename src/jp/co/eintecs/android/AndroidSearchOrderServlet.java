package jp.co.eintecs.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * android版 【注文一覧】
 * @author onatsu
 *
 **/
public class AndroidSearchOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//リクエストで受信した文字をUTF-8文字コードで受信する
		request.setCharacterEncoding("UTF-8");
		//リクエストパラメータを受け取る
		String orderId = request.getParameter("orderId");

		//ステータス確認用文字列message
		String message = null;
		//データベース接続用
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs=null;
		List<AndroidOrderListBean> list = new ArrayList<AndroidOrderListBean>();

		try{
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();
			//インサート文作成
			String sql = "SELECT order_name,order_post,order_address,order_phone,orderlist.order_day,orderlist.order_id,product.book_name,order_detail.count,product.price FROM orderlist LEFT JOIN order_detail ON orderlist.order_id=order_detail.order_id LEFT JOIN product ON order_detail.book_id=product.book_id WHERE orderlist.order_id=?";
			st = con.prepareStatement(sql);
			st.setString(1, orderId);

			//SQL文を実行
			rs = st.executeQuery();

			//androidBeanにデータをセット
			int price;
			int count;
			int sum;
			int goukei=0;
			while (rs.next()) {
				AndroidOrderListBean books = new AndroidOrderListBean();
				books.setName(rs.getString("order_name"));
				books.setPost(rs.getString("order_post"));
				books.setAddress(rs.getString("order_address"));
				books.setPhone(rs.getString("order_phone"));
				books.setOrderId(rs.getInt("order_id"));
				books.setBookName(rs.getString("book_name"));
				books.setOrderDay(rs.getString("order_day"));

				count=rs.getInt("count");
				books.setCount(count);

				price=rs.getInt("price");
				books.setPrice(price);

				sum=price*count;
				books.setSum(sum);

				goukei=goukei+sum;
				books.setTotal(goukei);

				list.add(books);
			}
			message = "success!";
		}catch(Exception e){
			//エラーメッセージをセット
			message = "データベース接続エラー or insert文エラー";
			e.printStackTrace();
		}finally{
			try{
				//データベースに接続されていれば切断する
				if(con != null)	con.close();
			}catch(Exception e){
				message = "connection close エラー";
			}
		}
		//ステータス表示
		System.out.println(message);


		//json作成 jsonはルールに基づいたtextです。
		String json = null;

		//jacksonのオブジェクトマッパー作成（json変換機能があります。)
		ObjectMapper mapper = new ObjectMapper();

		 //beanのarraylistをjsonに変換するパターン
		json = mapper.writeValueAsString(list);

		 //レスポンスに値を書き込む
		 //文字コードセット
		  response.setCharacterEncoding("UTF-8");
		  //jsonの場合下記を記入
		  response.setContentType("application/json");
		  //responseに書き込むprintwriter作成
		  PrintWriter out = response.getWriter();
		  //文字のみを送信する場合直接out.printlnの引数に指定すればOK
		  //out.println("おはようございます");
		  //out.println("こんにちは");
		  //out.println("こんばんは");

		  //jsonを送信する場合はjsonを引数に指定
		  out.println(json);
		  System.out.println(json);
		  //書き込み！
		  out.flush();
	}

}