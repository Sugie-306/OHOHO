package jp.co.eintecs.dao;

/**
 *著者情報関連のDAO
 * @author sugie
 *
 */

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * 著者情報を登録する
 * @author akari
 * @param  authorName 名前
 * @return boolean
 */
public class AuthorDAO {
	public static boolean addAuthor(String authorName) {
		Connection con = null;
		PreparedStatement ps = null;
		int ret = -1;
		try {
			//データソースの指定
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションを取得
			con = ds.getConnection();
			//SQL
			String sql = "INSERT INTO author VALUES (null,?)";
			//PREPAREDオブジェクトを生成
			ps = con.prepareStatement(sql);
			//パラメーターをセット
			ps.setString(1, authorName);
			//実行
			ret = ps.executeUpdate();
			//更新クエリの実行
			if (ret != 0) {
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
