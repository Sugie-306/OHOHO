package jp.co.eintecs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jp.co.eintecs.beans.UserBean;

/**
 * ユーザー情報関連DAO
 * @author sasaki
 */
public class UserDAO {
	/**
	 * ユーザ情報の更新（ customerテーブル）
	 * @author sasaki
	 * @param user 入力パラメーターをセットしたUserBean型のインスタンス
	 * @return 成功したらtrue
	 */

	public static Boolean updateCustomer(UserBean user) {
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
			String sql = "UPDATE customer SET name=?, post=?, address=?, phone=?, mail=? WHERE user_id=?";
			//SQL文をPreparedStatementオブジェクトへセット
			ps = con.prepareStatement(sql);
			//Beanの中身をパラメーターへセット
			ps.setString(1, user.getName()); //ユーザ氏名
			ps.setString(2, user.getPost()); //郵便番号
			ps.setString(3, user.getAddress()); //住所
			ps.setString(4, user.getPhone()); //電話番号
			ps.setString(5, user.getMail()); //メールアドレス
			ps.setString(6, user.getUserid()); //ユーザ番号

			//更新クエリの実行
			int update = ps.executeUpdate();
			//更新件数をもとに判断
			if (update != 0) {
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
	 * ユーザ情報の更新（ユーザ情報の更新（personテーブル））
	 * @author sasaki
	 * @param user 入力パラメーターをセットしたUserBean型のインスタンス
	 * @return 成功したらtrue 失敗する
	 */
	public static boolean updatePerson(UserBean user) {
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
			String sql = "UPDATE person SET user_name=? , password=? WHERE user_id=? ";
			//SQL文をPreparedStatementオブジェクトへセット
			ps = con.prepareStatement(sql);
			//Beanの中身をパラメーターへセット
			ps.setString(1, user.getUsername()); //ユーザ名
			ps.setString(2, user.getUserpass()); //パスワード
			ps.setString(3, user.getUserid()); //ユーザ番号
			//更新クエリの実行
			int update = ps.executeUpdate();
			//更新件数をもとに判断
			if (update != 0) {
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
	 * ユーザ情報の登録（personテーブル）
	 * @author maimai
	 * @param user 入力パラメーターをセットしたUserBean型のインスタンス
	 * @return 成功したらtrue
	 */
	public static boolean insertPerson(UserBean user) {
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
			String sql = "INSERT INTO person VALUES(null,?,?,0,0)";
			//SQL文をPreparedStatementオブジェクトへセット
			ps = con.prepareStatement(sql);
			//Beanの中身をパラメーターへセット
			ps.setString(1, user.getUsername()); //会員名（ローマ字表記）
			ps.setString(2, user.getUserpass()); //パスワード
			//更新クエリの実行
			int kousin = ps.executeUpdate();
			//更新件数をもとに判断
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
	 * 一番最新のuser_idを取得する
	 * @author maimai
	 * @param なし
	 * @return  String型 一番下の行のデーターを取り出す
	 */

	public static String getLastid() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null; //結果を格納する変数sqlを書くときにほぼ必須
		String userid = "";
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "SELECT * FROM person ORDER BY user_id DESC LIMIT 1;";
			//Statementオブジェクトを生成
			st = con.createStatement();
			//クエリの実行
			rs = st.executeQuery(sql);
			while (rs.next()) {
				//結果を格納
				userid = rs.getString("user_id");
			}
			//データーベースに接続されていたら切断する
			if (con != null) {
				con.close();
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return userid;
	}

	/**
	 * ユーザ情報の登録（customerテーブル）
	 * @author maimai
	 * @param user 入力パラメーターをセットしたUserBean型のインスタンス
	 * @return 成功したらtrue
	 */
	public static boolean insertCustomer(UserBean user) {
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
			String sql = "INSERT INTO customer VALUES(?,?,?,?,?,?)";
			//SQL文をPreparedStatementオブジェクトへセット
			ps = con.prepareStatement(sql);
			//Beanの中身をパラメーターへセット
			ps.setString(1, user.getUserid());//ユーザーID
			ps.setString(2, user.getName()); //会員名（漢字表記）
			ps.setString(3, user.getPost()); //郵便番号
			ps.setString(4, user.getAddress()); //住所
			ps.setString(5, user.getPhone()); //電話番号
			ps.setString(6, user.getMail()); //メールアドレス
			//更新クエリの実行
			int kousin = ps.executeUpdate();
			//更新件数をもとに判断
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
	 * 登録ユーザーかチェックする
	 * @author maimai
	 * @param loginName ユーザーログインネーム
	 * @return 登録ユーザならfalse、未登録ならTrue
	 */
	public static boolean checkRegisteredUser(String loginName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();
			//SQLの作成
			String sql = "SELECT * FROM person WHERE user_name=?"; //登録済みか検索するsql
			//SQL文をPreparedStatementオブジェクトへセット
			ps = con.prepareStatement(sql);
			//パラメーターをセット
			ps.setString(1, loginName);
			//SQL文を実行
			rs = ps.executeQuery();
			//ユーザーが登録済みかどうか判断
			if (rs.next()) {
				//登録ありなので、falseを返す
				return false;
			} else {
				//登録なしなので、trueを返す
				return true;
			}

		} catch (Exception ex) {
			System.err.println(ex.getMessage());

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				//データーベースに接続されていたら切断する
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
		return true;
	}

	/**
	 * ユーザー番号から顧客の情報を探す
	 * @author sasaki
	 * @param userId ユーザー番号
	 * @return 、顧客の情報全部(UserBean型)。無ければnull
	 */
	public static UserBean getUserData(String userId) {
		//UesrBeanを作成
		UserBean user1 = new UserBean();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();
			//SQL文を作成
			String sql = "SELECT * FROM customer INNER JOIN person ON customer.user_id = person.user_id  WHERE person.user_id = ?";
			//prepareStatementオブジェクトの生成
			ps = con.prepareStatement(sql);
			//パラメータ指定
			ps.setString(1, userId);
			//SQL文の実行
			rs = ps.executeQuery();
			//データの取得
			while (rs.next()) {
				//情報を取得
				String name = rs.getString("name");
				String address = rs.getString("address");
				String post = rs.getString("post");
				String phone = rs.getString("phone");
				String mail = rs.getString("mail");
				String username = rs.getString("user_name");
				String userpass = rs.getString("password");
				//UesrBeanの中に値をセット
				user1.setName(name);
				user1.setAddress(address);
				user1.setPost(post);
				user1.setPhone(phone);
				user1.setMail(mail);
				user1.setUsername(username);
				user1.setUserpass(userpass);
				System.out.println(user1 + "DAOの中");
			}
			//結果の返却
			return user1;

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
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
}
