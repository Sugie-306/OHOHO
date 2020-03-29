package jp.co.eintecs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jp.co.eintecs.beans.OrderBean;

/**
 *オーダー関連のDAO
 * @author maimai
 *
 */

public class OrderDAO {
	/**
	 * 注文明細挿入
	 * @author maimai
	 * @param orderid 注文番号
	 * @param bookid 書籍番号
	 * @param count 数量
	 * @return 成功したらtrue 失敗したらfalse
	 */
	public static boolean insertOrderDetail(String orderid,String bookid,String count) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();
			//SQL文をセット
			String sql = ("INSERT INTO order_detail VALUES (?,?,?)");
			//SQL文をPreparedStatementオブジェクトに格納
			ps = con.prepareStatement(sql);
			//パラメーターをセット
			ps.setString(1, orderid);
			ps.setString(2, bookid);
			ps.setString(3, count);
			//更新クエリの実行
			int kousin = ps.executeUpdate();
			//更新件数を判断
			if (kousin != 0) {
				//成功
				return true;
			} else {
				//失敗
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
	 * ユーザIDから最新の注文IDを取得
	 * @author maimai
	 * @param userid ユーザー番号
	 * @return 注文id
	 */
	public static String getOrderId(String userid) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションを取得
			con = ds.getConnection();
			//SQL文をセット
			ps = con.prepareStatement("SELECT * FROM orderlist WHERE user_id =? GROUP BY order_id DESC");
			//パラメータセット
			ps.setString(1, userid);
			//sql実行
			rs = ps.executeQuery();
			//データ取得(一番上のIDを取り出す)
			if (rs.next()) {
				return rs.getString("order_id");
			} else {
				System.err.println("注文ID取得エラー");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * カート登録商品の被りを探す
	 * @author maimai
	 * @param bookid 書籍番号
	 * @param userid ユーザー番号
	 * @return 被りならtrue。無しはfalse
	 */

	public static boolean ChekBook(String bookid, String userid) {

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
			String sql = "SELECT book_id FROM cart WHERE book_id=? AND user_id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, bookid);
			ps.setString(2, userid);
			//SQLの実行
			rs = ps.executeQuery();
			//検索結果をもとに判断
			if(rs.next()) {
				//1件以上あるとき=成功
				return true;
			}else {
				//0件などのとき=失敗
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(con != null) {
					con.close();
				}
				if(ps != null) {
					ps.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		//リストを返す↓
		return false;

	}


	/**
	 * 注文テーブルに書き込む
	 * @author maimai
	 * @param ob 注文内容
	 * @return 成功したらture
	 *
	 */
	public static boolean addOrderlist(OrderBean ob) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
		//コンテキストの取得
		Context context = new InitialContext();
		//データソースの指定
		DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
		//コネクションの取得
		con = ds.getConnection();
		//SQL
		String sql = "INSERT INTO orderlist VALUES (null,?,null,'3',?,?,?,?,?)";
		//SQL文をPrepareStatementオブジェクトにセット
		ps = con.prepareStatement(sql);
		//パラメーターにオブジェクト内の情報をセット
		ps.setString(1, ob.getUser_id());
		ps.setString(2, ob.getOrder_Post());
		ps.setString(3, ob.getOrder_Address());
		ps.setString(4, ob.getOrder_Name());
		ps.setString(5, ob.getOrder_Phone());
		ps.setString(6, ob.getPay_Rule());
		//クエリの実行
		int kousin = ps.executeUpdate();
		//更新件数をもとに判断
		if (kousin != 0) {
			//0件でない場合 成功
			return true;
		} else {
			//0圏の場合  失敗
			return false;
		}
	} catch (Exception ex) {
		System.err.println(ex.getMessage());
		ex.printStackTrace();
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
