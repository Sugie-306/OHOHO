package jp.co.eintecs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jp.co.eintecs.beans.BasketBean;

/**
 *カート関連のDAO
 * @author sugie
 *
 */

public class BasketDAO {

	/**
	 * カート情報を取得する
	 * @author maimai
	 * @param userid ユーザー番号
	 * @return BookBean型のリスト
	 */
	public static List<BasketBean> getCartList(String userid) {
		//データベースの取得結果を格納するリスト
		List<BasketBean> list = new ArrayList<BasketBean>();

		//DB接続準備
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データーソースの取得
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "SELECT * FROM cart INNER JOIN product ON cart.book_id=product.book_id INNER JOIN author ON product.author_id=author.author_id WHERE user_id=?";
			//preparedStatementオブジェクトの生成
			ps = con.prepareStatement(sql);
			//パラメーターの指定
			ps.setString(1, userid);
			//SQLの実行
			rs = ps.executeQuery();
			//データの取得
			while (rs.next()) {
				//1のレコード情報を取得
				String uid = rs.getString("user_id");
				String bookid = rs.getString("book_id");
				int count = rs.getInt("count");
				String book_Name = rs.getString("book_Name");
				String author_Name = rs.getString("author_Name");
				int price = rs.getInt("price");

				//取得したデータを基にBasketBeanを作成
				BasketBean book = new BasketBean();
				book.setUid(uid);
				book.setBookid(bookid);
				book.setCount(count);
				book.setBook_Name(book_Name);
				book.setAuthor_Name(author_Name);
				book.setPrice(price);

				//リストに追加
				list.add(book);
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return null;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				//データーベースに接続されていれば切断する
				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
		//リストを返す↓
		return list;

	}

	/**
	 * カートの中身をクリアする
	 * @author maimai
	 * @param userid ユーザー番号
	 * @return 成功したらtrue 失敗したらfalse
	 */
	public static boolean deleteCart(String userid) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "DELETE FROM cart WHERE user_id=? ";
			//preparedStatementオブジェクトの生成
			ps = con.prepareStatement(sql);
			//パラメーターの指定
			ps.setString(1, userid);
			//更新クエリの実行
			int kousin = ps.executeUpdate();
			if (kousin != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				//データーベースに接続されていたら切断する
				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}


	/**
	 * カートへ商品を追加する
	 * @author maimai
	 * @param userid ユーザー番号
	 * @param bookid 書籍番号
	 * @param count 数量
	 * @return 成功したらtrue 失敗したらfalse
	 */
	public static boolean insertCart(String userid, String bookid, String count) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データーソースの指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "INSERT INTO cart VALUES(?,?,?)";
			//preparedStatementオブジェクトの生成
			ps = con.prepareStatement(sql);
			//パラメータセット
			ps.setString(1, userid);
			ps.setString(2, bookid);
			ps.setString(3, count);

			//クエリの実行
			int kousin = ps.executeUpdate();
			if (kousin != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				//データーベースに接続されていたら切断する
				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}


	/**
	 * カートの商品を合算する
	 * @author maimai
	 * @param count 数量
	 * @param userid ユーザー番号
	 * @param bookid 書籍番号
	 * @return 成功したらtrue 失敗したらfalse
	 */

	public static boolean addCart(String count, String userid, String bookid) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データーソースの指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "UPDATE cart SET count = count+? WHERE user_id=? && book_id=?";
			//PreparedStatementオブジェクトを生成
			ps = con.prepareStatement(sql);
			//パラメータセット
			ps.setString(1, count);
			ps.setString(2, userid);
			ps.setString(3, bookid);
			//クエリの実行
			int kousin = ps.executeUpdate();
			if (kousin != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return false;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				//データーベースに接続されていたら切断する
				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
}
