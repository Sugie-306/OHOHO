package jp.co.eintecs.android;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Android用DAO。
 * @author sasaki
 * @author matsumoto
 *
 */
public class AndroidDAO {

	/**
	 * 書籍一覧を取得
	 * @author sasaki
	 * @param なし
	 * @return BookBean型のlist
	 */
	public static List<AndroidBookBean> getBook() {
		List<AndroidBookBean> list = new ArrayList<AndroidBookBean>();

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
			st = con.createStatement();
			//SQLの作成
			String sql = "SELECT * FROM product RIGHT JOIN author ON product.author_id = author.author_id RIGHT JOIN category ON product.category_id = category.category_id";
			//sql実行
			rs = st.executeQuery(sql);
			//データ取得
			while (rs.next()) {
				//1レコードの情報を取得
				String book_id = rs.getString("book_id");
				String book_name = rs.getString("book_name");
				String author_id = rs.getString("author_id");
				String author_name = rs.getString("author_name");
				String category_id = rs.getString("category_id");
				String category_name = rs.getString("category_name");
				String price = rs.getString("price");
				String image = rs.getString("image");
				String stock = rs.getString("stock");
				String explanation = rs.getString("explanation");
				String isbn = rs.getString("isbn");

				//取得したデータを基にBookBeanを作成
				AndroidBookBean book = new AndroidBookBean();
				//bookBeanの中に値をセット
				book.setBook_id(book_id);
				book.setBook_name(book_name);
				book.setAuthor_id(author_id);
				book.setAuthor_name(author_name);
				book.setCategory_id(category_id);
				book.setCategory_name(category_name);
				book.setPrice(price);
				book.setImage(image);
				book.setStock(stock);
				book.setExplanation(explanation);
				book.setIsbn(isbn);
				list.add(book);
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
	 * 検索結果を表示する(新着順)
	 * @author sugie
	 * @param word 入力キーワード
	 * @param c_id カテゴリーid
	 * @return BookBean型のlist
	 */
	public static List<AndroidBookBean> searchBook(String word, String c_id) {
		List<AndroidBookBean> list = new ArrayList<AndroidBookBean>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			//コンテキストを取得
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションを取得
			con = ds.getConnection();
			//カテゴリ選択の判断
			if (c_id.equals("0")) {
				//▼"全ジャンル"の場合
				//SQL文を作成
				String sql = "SELECT * FROM product INNER JOIN author ON product.author_id=author.author_id INNER JOIN category ON product.category_id=category.category_id WHERE book_name LIKE ? ";
				//SQL文をセット
				ps = con.prepareStatement(sql);
				//パラメーターをセット
				ps.setString(1, "%" + word + "%");

			} else {
				//▼"小説""コミック""エッセイ""オリジナル"の場合
				//SQL文を作成
				String sql = "SELECT * FROM product INNER JOIN author ON product.author_id=author.author_id INNER JOIN category ON product.category_id=category.category_id WHERE book_name LIKE ? AND product.category_id= ?";
				//SQL文をセット
				ps = con.prepareStatement(sql);
				//パラメーターをセット
				ps.setString(1, "%" + word + "%");
				ps.setString(2, c_id);
			}

			//sql実行
			rs = ps.executeQuery();

			//データ取得
			while (rs.next()) {
				String book_id = rs.getString("book_id");
				String book_name = rs.getString("book_name");
				String author_id = rs.getString("author_id");
				String author_name = rs.getString("author_name");
				String category_id = rs.getString("category_id");
				String category_name = rs.getString("category_name");
				String image = rs.getString("image");
				String price = rs.getString("price");
				String stock = rs.getString("stock");
				String explanation = rs.getString("explanation");
				String isbn = rs.getString("isbn");
				String arrival_day = rs.getString("arrival_day");
				//BookBeanのインスタンスに値をセットする
				AndroidBookBean book = new AndroidBookBean(book_id, book_name, author_id, author_name, category_id, category_name,
						image, price, stock, explanation, isbn, arrival_day);
				//Listに一行分のBookBeanを追加する
				list.add(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
		return list;
	}

	/**
	 * 書籍を登録する
	 * @author 名前
	 * @param book 入力した書籍情報
	 * @return 成功したらtrue 失敗はfalse
	 */
	public static boolean addBook(AndroidBookBean book) {

		String sql = "INSERT INTO product VALUES (null,?,?,?,?,?,?,?,?,null)";

		//パラメータセット
		List<String> par = new ArrayList<String>();
		par.add(book.getBook_name());
		par.add(book.getAuthor_id());
		par.add(book.getCategory_id());
		par.add(book.getImage());
		par.add(book.getPrice());
		par.add(book.getStock());
		par.add(book.getExplanation());
		par.add(book.getIsbn());

		//登録実行
		return updateDB(sql, par);

	}

	/**
	 * 著者が登録されているかを確認する
	 * @author 名前
	 * @param authorName 著者ID
	 * @return 著者ID、未登録なら0
	 */
	static public int isAuthor(String authorName) {
		String sql = "SELECT author_id From author Where author_name =?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			con = ds.getConnection();

			//SQL文をセット
			ps = con.prepareStatement(sql);
			ps.setString(1, authorName);

			//sql実行
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("author_id");
			} else {
				return 0;
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
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * 書籍を更新する
	 * @author 名前
	 * @param book 入力した書籍情報
	 * @return 成功したらtrue 失敗はfalse
	 */
	public static boolean updateBook(AndroidBookBean book) {
		String sql = "UPDATE product SET book_name=?,author_id=?,category_id=?,image=?,price=?,stock=?,explanation=?,isbn=? WHERE book_id=?";
		//パラメータセット
		List<String> par = new ArrayList<String>();
		par.add(book.getBook_name());
		par.add(book.getAuthor_id());
		par.add(book.getCategory_id());
		par.add(book.getImage());
		par.add(book.getPrice());
		par.add(book.getStock());
		par.add(book.getExplanation());
		par.add(book.getIsbn());
		par.add(book.getBook_id());

		return updateDB(sql, par);
	}

	/**汎用INSERT,UPDATE,DELETEメソッド
	 * SQLとパラメータをセットしたListを引数に実行。
	 * 便利だけどロールバック非対応
	 *@param sql 実行するSQL文
	 *@param par 必要パラメータのリスト
	 *@return SQL実行結果
	 */
	public static boolean updateDB(String sql, List<String> par) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			//DB接続用準備
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			con = ds.getConnection();
			ps = con.prepareStatement(sql);

			//sql文にパラメータをセット
			for (int i = 0; i < par.size(); i++) {
				ps.setString(i + 1, par.get(i));
			}

			//sql実行
			ps.executeUpdate();
			return true;

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
	}

	/**
	 * 著者名を登録する
	 * @param authorName 著者名
	 * @return 成功ならtrue,失敗ならfalse
	 * */
	public static boolean addAuthor(String authorName) {
		//SQL
		String sql = "INSERT INTO author VALUES (null,?)";

		//パラメータセット
		List<String> par = new ArrayList<String>();
		par.add(authorName);

		//SQL実行
		return updateDB(sql, par);
	}
}
