package jp.co.eintecs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * 税率関連のDAO
 * @author matsumoto
 * @author sugie
 */
public class TaxDAO {
	/**
	 *@author sugie
	 * 税率を取得する
	 *@return 税率
	 */
	public static double NowTax() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		double tax = 0.0;
		try {
			//コンテキストを取得
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションを取得
			con = ds.getConnection();
			//SQL文を作成
			String sql = "SELECT * FROM tax WHERE tax_id= ? ";
			//SQL文をPreparedStatementオブジェクトへセット
			ps = con.prepareStatement(sql);
			//パラメーターをセット
			ps.setString(1, "3");
			//sql実行
			rs = ps.executeQuery();
			//データ取得
			if (rs.next()) {
				tax = rs.getDouble("taxrate");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0.0;
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

		return tax;
	}

	/**
	 * 税率を変更する
	 *@author sugie
	 *@param tax 入力値
	 *@return 成功したらtrue 失敗したらfalse
	 */

	public static boolean updateTax(String tax) {
		//DB取得結果を格納するリスト
		//List<UserBean> list = new ArrayList<UserBean>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();
			//SQL文の作成
			String sql = "UPDATE tax SET taxrate=? WHERE tax_id= 3 ";
			ps = con.prepareStatement(sql);
			ps.setString(1, tax); //ユーザ名

			//更新クエリの実行
			int update = ps.executeUpdate();
			if (update != 0) {
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
	 * 税込み価格を計算するメソッド
	 *@author 名前
	 *@param price 本の価格
	 *@return 税込み価格
	 */

	public static String CalcTax(int price) {

		double tax = NowTax();
		return String.format("%,.0f", price * tax);
	}
}
