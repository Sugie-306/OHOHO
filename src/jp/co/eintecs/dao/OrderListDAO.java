package jp.co.eintecs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jp.co.eintecs.beans.BasketBean;
import jp.co.eintecs.beans.OrderBean;

/**
* 注文一覧のDAO
* @author sugie
*
*/

public class OrderListDAO {
	/**
	 * (管理者用)商品名・1注文の合計・送付先・支払方法などを取得する
	 * @author sugie
	 * @param 無し
	 * @return List(OrderListBean型)
	 */
	public static List<OrderBean> getListByOrder() {
		//リストを作成
		List<OrderBean> list = new ArrayList<OrderBean>();
		//DB接続準備
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//コンテキスト取得
			Context context = new InitialContext();
			//データソース指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();
			//Statementオブジェクトの生成
			st = con.createStatement();
			//SQ文Lの作成
			String sql = "SELECT *,SUM(price * count),SUM(count) FROM orderlist l INNER JOIN order_detail d ON l.order_id=d.order_id INNER JOIN product p ON d.book_id=p.book_id INNER JOIN author a ON p.author_id=a.author_id  INNER JOIN payrule r ON r.pay_id=l.pay_id GROUP BY l.order_id";
			//sql実行
			rs = st.executeQuery(sql);
			//データ取得
			while (rs.next()) {
				//1レコードの情報を取得
				int order_id = rs.getInt("order_id");
				String book_name = rs.getString("book_name");
				String image = rs.getString("image");
				String author_name = rs.getString("author_name");
				String total = TaxDAO.CalcTax(rs.getInt("SUM(price * count)"));
				String count = rs.getString("SUM(count)");
				String name = rs.getString("order_name");
				String post = rs.getString("order_post");
				String address = rs.getString("order_address");
				String phone = rs.getString("order_phone");
				String day = rs.getString("order_day");
				String pay_rule = rs.getString("pay_rule");

				//取得したデータを基にBookBeanを作成
				OrderBean order = new OrderBean();

				//bookBeanの中に値をセット
				order.setOrder_id(order_id);
				order.setImage(image);
				order.setBook_Name(book_name);
				order.setAuthor_Name(author_name);
				order.setTotal(total);
				order.setCount(count);
				order.setOrder_Name(name);
				order.setOrder_Post(post);
				order.setOrder_Address(address);
				order.setOrder_Phone(phone);
				order.setOrder_Day(day);
				order.setPay_Rule(pay_rule);
				//リストに追加
				list.add(order);
			}
			//検索結果の返却
			return list;

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	/**
	 * (管理者・マイページ用)orderidから注文一覧の商品詳細を取得する
	 * @author sugie
	 * @param orderId　
	 * @return List(OrderListBean型)
	 */
	public static List<OrderBean> getListByOrderId(String orderId) {
		//リストを作成
		List<OrderBean> list = new ArrayList<OrderBean>();
		//DB接続準備
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//コンテキスト取得
			Context context = new InitialContext();
			//データソース指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "SELECT *,price * count FROM orderlist l INNER JOIN order_detail d ON l.order_id=d.order_id INNER JOIN product p ON d.book_id=p.book_id INNER JOIN author a ON p.author_id=a.author_id  INNER JOIN payrule r ON r.pay_id=l.pay_id WHERE l.order_id =?";
			//PrepareStatementオブジェクトへSQL文を格納
			ps = con.prepareStatement(sql);
			//パラメーターの指定
			ps.setString(1, orderId);
			//sql実行
			rs = ps.executeQuery();
			//データ取得
			while (rs.next()) {
				//1レコードの情報を取得
				int order_id = rs.getInt("order_id");
				String book_id = rs.getString("d.book_id");
				String book_name = rs.getString("book_name");
				String price = rs.getString("price");
				String image = rs.getString("image");
				String author_name = rs.getString("author_name");
				String sub = TaxDAO.CalcTax(rs.getInt("price * count"));
				String count = rs.getString("count");
				String name = rs.getString("order_name");
				String post = rs.getString("order_post");
				String address = rs.getString("order_address");
				String phone = rs.getString("order_phone");
				String day = rs.getString("order_day");
				//取得したデータを基にBookBeanを作成
				OrderBean order = new OrderBean();
				//bookBeanの中に値をセット
				order.setOrder_id(order_id);
				order.setBookid(book_id);
				order.setBook_Name(book_name);
				order.setPrice(price);
				order.setImage(image);
				order.setAuthor_Name(author_name);
				order.setSub(sub);
				order.setCount(count);
				order.setOrder_Name(name);
				order.setOrder_Post(post);
				order.setOrder_Address(address);
				order.setOrder_Phone(phone);
				order.setOrder_Day(day);
				//リストへ追加
				list.add(order);
			}

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}

		//検索結果の返却
		return list;

	}

	/**
	 * (管理者・マイページ用)orderidから注文合計・送付先・支払方法を取得
	 * @author sugie
	 * @param 無し
	 * @return List(OrderListBean型)
	 */
	public static List<OrderBean> getListByUser2(String orderId) {
		//リストを作成
		List<OrderBean> list = new ArrayList<OrderBean>();

		//DB接続準備
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//コンテキスト取得
			Context context = new InitialContext();
			//データソース指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "SELECT *,SUM(price*count) FROM orderlist l INNER JOIN order_detail d ON l.order_id=d.order_id INNER JOIN product p ON d.book_id=p.book_id INNER JOIN author a ON p.author_id=a.author_id  INNER JOIN payrule r ON r.pay_id=l.pay_id WHERE l.order_id= ?";
			//PreparedStatementオブジェクトへSQLを格納
			ps = con.prepareStatement(sql);
			//パラメーターの指定
			ps.setString(1, orderId);
			//sql実行
			rs = ps.executeQuery();
			//データ取得
			while (rs.next()) {
				//1レコードの情報を取得
				int order_id = rs.getInt("order_id");
				String book_name = rs.getString("book_name");
				String price = rs.getString("price");
				String image = rs.getString("image");
				String author_name = rs.getString("author_name");
				String total = TaxDAO.CalcTax(rs.getInt("SUM(price*count)"));
				String count = rs.getString("count");
				String name = rs.getString("order_name");
				String post = rs.getString("order_post");
				String address = rs.getString("order_address");
				String phone = rs.getString("order_phone");
				String day = rs.getString("order_day");
				String payrule = rs.getString("pay_rule");
				//取得したデータを基にBookBeanを作成
				OrderBean order = new OrderBean();
				//bookBeanの中に値をセット
				order.setOrder_id(order_id);
				order.setBook_Name(book_name);
				order.setPrice(price);
				order.setImage(image);
				order.setAuthor_Name(author_name);
				order.setTotal(total);
				order.setCount(count);
				order.setOrder_Name(name);
				order.setOrder_Post(post);
				order.setOrder_Address(address);
				order.setOrder_Phone(phone);
				order.setOrder_Day(day);
				order.setPay_Rule(payrule);
				list.add(order);
			}
			//検索結果の返却
			return list;

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	/**
	 * 注文一覧の大まかな情報を呼び出す（customer.jsp用)
	 * @author sasaki
	 *  @author sugie
	 * @param userid
	 * @return List(OrderListBean型)
	 */
	public static List<OrderBean> getListByUserId(String userid) {
		//リストを作成
		List<OrderBean> list1 = new ArrayList<OrderBean>();
		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//データベースに接続
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();

			//SQLの作成
			String sql = "SELECT *,SUM(price * count),SUM(count) FROM orderlist l INNER JOIN order_detail d ON l.order_id=d.order_id INNER JOIN product p ON d.book_id=p.book_id INNER JOIN author a ON p.author_id=a.author_id  INNER JOIN payrule r ON r.pay_id=l.pay_id GROUP BY l.order_id HAVING l.user_id = ?";
			//SQL文をPreparedStatementオブジェクトへセット
			ps = con.prepareStatement(sql);
			//パラメーターの指定
			ps.setString(1, userid);

			//SQLの実行
			rs = ps.executeQuery();

			//データを取得してリストにl追加する
			while (rs.next()) {
				//1レコードの情報を取得
				int order_id = rs.getInt("order_id");
				String book_name = rs.getString("book_name");
				String image = rs.getString("image");
				String author_name = rs.getString("author_name");
				String total = TaxDAO.CalcTax(rs.getInt("SUM(price * count)"));
				String count = rs.getString("SUM(count)");
				String name = rs.getString("order_name");
				String post = rs.getString("order_post");
				String address = rs.getString("order_address");
				String phone = rs.getString("order_phone");
				String day = rs.getString("order_day");
				String pay_rule = rs.getString("pay_rule");

				//取得したデータを基にBookBeanを作成
				OrderBean order = new OrderBean();
				//bookBeanの中に値をセット
				order.setOrder_id(order_id);
				order.setImage(image);
				order.setBook_Name(book_name);
				order.setAuthor_Name(author_name);
				order.setTotal(total);
				order.setCount(count);
				order.setOrder_Name(name);
				order.setOrder_Post(post);
				order.setOrder_Address(address);
				order.setOrder_Phone(phone);
				order.setOrder_Day(day);
				order.setPay_Rule(pay_rule);
				list1.add(order);
			}
			return list1;

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (st != null) {
					st.close();
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	/**
	 * 顧客ページ用・商品名・小計などを取得する
	 * @author sasaki
	 * @param userid ユーザー番号
	 * @return List(BasketBean型)
	 */

	public static List<BasketBean> getListOrder(String userid) {
		//データベースに接続
		List<BasketBean> list = new ArrayList<BasketBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//データベースに接続
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();

			//SQLの作成
			String sql = "SELECT * FROM orderlist RIGHT JOIN order_detail ON orderlist.order_id=order_detail.order_id RIGHT JOIN product ON order_detail.book_id=product.book_id RIGHT JOIN author ON author.author_id=product.author_id WHERE orderlist.user_id=?";
			//SQL文をPreparedStatementオブジェクトへセット
			ps = con.prepareStatement(sql);
			//パラメーターの指定
			ps.setString(1, userid);
			//SQLの実行
			rs = ps.executeQuery();

			//データを取得してリストに追加する
			while (rs.next()) {
				//取得したデータを基に単語オブジェクトを作成
				BasketBean book = new BasketBean();
				//1レコードの情報を取得
				book.setBook_Name(rs.getString("book_Name"));
				book.setAuthor_Name(rs.getString("author_Name"));
				book.setPrice(rs.getInt("price"));
				book.setCount(rs.getInt("count"));
				book.setOrder_Day(rs.getString("order_Day"));
				book.setSub(rs.getInt("sub"));
				list.add(book);
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				//データベースに接続されていれば切断する
				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
		return list;
	}

	/**
	 * 顧客ページ用・商品名・小計などを取得するその２
	 * @author sasaki
	 * @param orderid オーダー番号
	 * @return List(BasketBean型)
	 */
	public static List<BasketBean> getListOrder2(String orderid) {

		//リストを作成
		List<BasketBean> list = new ArrayList<BasketBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//データベースに接続
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "SELECT * FROM orderlist RIGHT JOIN order_detail ON orderlist.order_id=order_detail.order_id RIGHT JOIN product ON order_detail.book_id=product.book_id RIGHT JOIN author ON author.author_id=product.author_id WHERE orderlist.order_id=?";
			//prepareStatementオブジェクトを生成
			ps = con.prepareStatement(sql);
			//パラメータの指定
			ps.setString(1, orderid);
			//SQLを実行
			rs = ps.executeQuery();

			//データを取得してリストに追加する
			while (rs.next()) {
				//取得したデータを基に単語オブジェクトを作成
				BasketBean book = new BasketBean();

				rs.getString("book_Name");
				rs.getString("name");
				rs.getString("author_Name");
				rs.getInt("price");
				rs.getInt("count");
				rs.getString("order_Day");
				rs.getInt("sub");

				//1レコードの情報を取得
				book.setBook_Name(rs.getString("book_Name"));
				book.setAuthor_Name(rs.getString("author_Name"));
				book.setPrice(rs.getInt("price"));
				book.setCount(rs.getInt("count"));
				book.setOrder_Day(rs.getString("order_Day"));
				book.setSub(rs.getInt("sub"));
				list.add(book);
			}
			return list;

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				//データベースに接続されていれば切断する
				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	/**
	 * 顧客ページ用・送付先情報・合計値など
	 * @author sasaki
	 * @param userid ユーザー番号
	 * @return List(BasketBean型)
	 */
	public static List<BasketBean> getListOrderDetail(String userid) {

		//リストを作成
		List<BasketBean> list = new ArrayList<BasketBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		//データベースに接続
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();

			//SQLの作成
			String sql = "SELECT *,product.price*order_detail.count sub,SUM(product.price*order_detail.count) total FROM orderlist RIGHT JOIN order_detail ON orderlist.order_id=order_detail.order_id RIGHT JOIN product ON order_detail.book_id=product.book_id RIGHT JOIN author ON author.author_id=product.author_id GROUP BY orderlist.order_id HAVING orderlist.user_id=?";
			//SQL文をPreparedStatementオブジェクトへセット
			ps = con.prepareStatement(sql);
			//パラメーターの指定
			ps.setString(1, userid);
			//SQLの実行
			rs = ps.executeQuery();
			//データを取得してリストに追加する
			while (rs.next()) {
				//取得したデータを基に単語オブジェクトを作成
				BasketBean book = new BasketBean();
				//1レコードの情報を取得
				book.setOrder_Day(rs.getString("order_Day"));
				book.setOrder_Post(rs.getInt("order_Post"));
				book.setOrder_Address(rs.getString("order_Address"));
				book.setOrder_Phone(rs.getString("order_Phone"));
				book.setOrder_Name(rs.getString("order_Name"));
				book.setTotal(rs.getInt("total"));
				list.add(book);
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				//データベースに接続されていれば切断する
				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
		return list;

	}

}
