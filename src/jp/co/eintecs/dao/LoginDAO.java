package jp.co.eintecs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * ログイン処理のDAO
 *
 * @author sugie
 *
 */

public class LoginDAO {

	/**
	 * 入力されたログインネームがDBに登録されているか確認する。
	 * @param username ユーザーログインネーム
	 * @return あったらtrue
	 */
	static public boolean userNameCheck(String username) {
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
			//SQL文を作成
			String sql = "SELECT * FROM person WHERE user_name= ?";
			//SQL文をセット
			ps = con.prepareStatement(sql);
			//パラメーターをセット
			ps.setString(1, username);
			//sql実行
			rs = ps.executeQuery();
			//結果があるかif文で判断
			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
		System.out.println("userIDがありません");
		return false;

	}

	/**
	 * ログインする。
	 * @param username ユーザーログインネーム
	 * @param userpass パスワード
	 * @param request リクエスト
	 * @return UserBean型のリスト
	 */
	static public int userLogin(String username, String password, HttpServletRequest request) {
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
			//SQL文を作成
			String sql = "SELECT * FROM person WHERE user_name= ?";
			//SQL文をセット
			ps = con.prepareStatement(sql);
			//パラメーターをセット
			ps.setString(1, username);
			//sql実行
			rs = ps.executeQuery();
			//DB取得用の変数を宣言
			String userId = null;
			String adminFlag = null;
			String passWord = null;

			//DBよりデータ取得
			while (rs.next()) {
				userId = rs.getString("user_id");
				adminFlag = rs.getString("admin_flg");
				passWord = rs.getString("password");
			}

			if (password.equals(passWord)) {
				//セッションを取得
				HttpSession session = request.getSession();
				//セッションにログイン情報を格納
				session.setAttribute("userId", userId);
				session.setAttribute("adminFlag", adminFlag);
				System.out.println("セッションは" + session);
				System.out.println("パスワード一致");

			}else {
				return 2;
			}
			//ユーザータイプ判定
			switch (Integer.parseInt(adminFlag)) {
			//通常ログイン成功時
			case 0:
				System.out.println("ユーザー");
				return 0;
			//管理者ログイン成功時
			case 1:
				System.out.println("管理者");
				return 1;
			//その他
			default:
				System.out.println("デフォルト");
				return 2;

			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
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

	}

	/**ログイン中のユーザIDを取得する
	 *@author maimai
	 * @param req リクエスト
	 * @return ユーザID
	 */
	static public String getUserID(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		return (String) session.getAttribute("userId");

	}
}

