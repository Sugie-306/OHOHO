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

import jp.co.eintecs.beans.BookBean;

/**
 * 書籍情報をやり取りするDAO。
 * @author sugie
 *
 */
public class BookBeanDAO {

	/**
	 * bookIdから書籍情報を取得
	 * @author sugie
	 * @param bookId get送信から取得
	 * @return BookBean型
	 */
	public static BookBean detailBook(String bookId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookBean book = new BookBean();
		try {
			//コンテキストを取得
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションを取得
			con = ds.getConnection();
			//SQL文を作成
			String sql = "SELECT * FROM product p INNER JOIN author a ON p.author_id=a.author_id INNER JOIN category c ON p.category_id=c.category_id WHERE book_id= ? ";
			//SQL文をPreparedStatementオブジェクトへセット
			ps = con.prepareStatement(sql);
			//パラメーターをセット
			ps.setString(1, bookId);
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
				String price = TaxDAO.CalcTax(Integer.parseInt(rs.getString("price")));
				String stock = rs.getString("stock");
				String explanation = rs.getString("explanation");
				String isbn = rs.getString("isbn");
				//BookBeanのインスタンスに値をセットする
				book = new BookBean(book_id, book_name, author_id, author_name, category_id, category_name,
						image, price, stock, explanation, isbn);
			}

		} catch (

		Exception e) {
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

		return book;

	}

	/**
	 * 書籍一覧を取得
	 * @author sasaki
	 * @param なし
	 * @return BookBean型のlist
	 */
	public static List<BookBean> getBook() {
		List<BookBean> list = new ArrayList<BookBean>();

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
			//Statementオブジェクトを生成
			st = con.createStatement();
			//SQLの作成
			String sql = "SELECT * FROM product LEFT JOIN author ON product.author_id = author.author_id LEFT JOIN category ON product.category_id = category.category_id";
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
				String price = TaxDAO.CalcTax(Integer.parseInt(rs.getString("price")));
				String image = rs.getString("image");
				String stock = rs.getString("stock");
				String explanation = rs.getString("explanation");
				String isbn = rs.getString("isbn");

				//取得したデータを基にBookBeanを作成
				BookBean book = new BookBean();
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
	 * TOPページの書籍一覧を取得
	 * @author akari
	 * @param なし
	 * @return BookBean型のlist
	 */
	public static List<BookBean> getnewBook() {
		List<BookBean> list = new ArrayList<BookBean>();

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
			//Statementオブジェクトを生成
			st = con.createStatement();
			//SQLの作成
			String sql = "SELECT * FROM product RIGHT JOIN author ON product.author_id = author.author_id RIGHT JOIN category ON product.category_id = category.category_id ORDER BY book_id DESC LIMIT 4";
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
				String price = TaxDAO.CalcTax(Integer.parseInt(rs.getString("price")));
				String image = rs.getString("image");
				String stock = rs.getString("stock");
				String explanation = rs.getString("explanation");
				String isbn = rs.getString("isbn");

				//取得したデータを基にBookBeanを作成
				BookBean book = new BookBean();
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
	 * ランキングを取得する(売上回数順)
	 * @author AKARI
	 * @param なし
	 * @return BookBean型のlist
	 */
	public static List<BookBean> getBookRank() {
		List<BookBean> rankList = new ArrayList<BookBean>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//コンテキストの取得
			Context context = new InitialContext();
			//データソースの指定
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			//コネクションの取得
			con = ds.getConnection();
			st = con.createStatement();
			//SQLの作成
			String sql = "SELECT *,SUM(count) FROM order_detail INNER JOIN product ON order_detail.book_id = product.book_id INNER JOIN author ON product.author_id = author.author_id GROUP BY book_name ORDER BY SUM(count) DESC LIMIT 4";
			//クエリの実行
			rs = st.executeQuery(sql);

			//データの取得
			while (rs.next()) {
				//レコード情報の取得
				String book_id = rs.getString("book_id");
				String book_name = rs.getString("book_name");
				String author_id = rs.getString("author_id");
				String author_name = rs.getString("author_name");
				String price = TaxDAO.CalcTax(Integer.parseInt(rs.getString("price")));
				String image = rs.getString("image");
				String stock = rs.getString("stock");
				String explanation = rs.getString("explanation");
				String isbn = rs.getString("isbn");
				//オブジェクトを作成する
				BookBean book = new BookBean();
				book.setBook_id(book_id);
				book.setBook_name(book_name);
				book.setAuthor_id(author_id);
				book.setAuthor_name(author_name);
				book.setPrice(price);
				book.setImage(image);
				book.setStock(stock);
				book.setExplanation(explanation);
				book.setIsbn(isbn);
				rankList.add(book);
			}
			return rankList;

		} catch (Exception ex) {
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
				System.out.println(ex.getMessage());
				ex.printStackTrace();
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
	public static List<BookBean> searchBook(String word, String c_id) {
		List<BookBean> list = new ArrayList<BookBean>();
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
				String price = TaxDAO.CalcTax(Integer.parseInt(rs.getString("price")));
				String stock = rs.getString("stock");
				String explanation = rs.getString("explanation");
				String isbn = rs.getString("isbn");
				//BookBeanのインスタンスに値をセットする
				BookBean book = new BookBean(book_id, book_name, author_id, author_name, category_id, category_name,
						image, price, stock, explanation, isbn);
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
	 * @author akari
	 * @param book 入力した書籍情報
	 * @return 成功したらtrue 失敗はfalse
	 */
	public static boolean addBook(BookBean book) {
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
			String sql = "INSERT INTO product VALUES(null,?,?,?,?,?,?,?,?,null)";
			//pareparedオブジェクトを生成
			ps = con.prepareStatement(sql);
			ps.setString(1, book.getBook_name());
			ps.setString(2, book.getAuthor_id());
			ps.setString(3, book.getCategory_id());
			ps.setString(4, book.getImage());
			ps.setString(5, book.getPrice());
			ps.setString(6, book.getStock());
			ps.setString(7, book.getExplanation());
			ps.setString(8, book.getIsbn());
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

	/**
	 * 著者が登録されているかを確認する
	 * @author akari
	 * @param authorName 著者ID
	 * @return 著者ID、未登録なら0
	 */
	static public int isAuthor(String authorName) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int authorid = -1;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/angel");
			con = ds.getConnection();

			//SQL文をセット
			String sql = "SELECT author_id FROM author WHERE author_name =?";
			//Preparedオブジェクトを生成
			ps = con.prepareStatement(sql);
			//パラメータをセット
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
		return authorid;
	}

	/**
		 * 書籍を更新する
		 * @author akari
		 * @param book 入力した書籍情報
		 * @return 成功したらtrue 失敗はfalse
		 */
	public static boolean updateBook(BookBean book) {
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
			String sql = "UPDATE product SET book_name=?,author_id=?,category_id=?,image=?,price=?,stock=?,explanation=?,isbn=? WHERE book_id=?";
			//SQLをPreparedStatementへセット
			ps = con.prepareStatement(sql);
			//パラメーターをセット
			ps.setString(1, book.getBook_name());
			ps.setString(2, book.getAuthor_id());
			ps.setString(3, book.getCategory_id());
			ps.setString(4, book.getImage());
			ps.setString(5, book.getPrice());
			ps.setString(6, book.getStock());
			ps.setString(7, book.getExplanation());
			ps.setString(8, book.getIsbn());
			ps.setString(9, book.getBook_id());

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
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 書籍一覧を取得
	 * @author sasaki
	 * @param なし
	 * @return BookBean型のlist
	 */
	public static List<BookBean> getBook2() {
		List<BookBean> list = new ArrayList<BookBean>();

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
			//Statementオブジェクトを生成
			st = con.createStatement();
			//SQLの作成
			String sql = "SELECT * FROM product LEFT JOIN author ON product.author_id = author.author_id LEFT JOIN category ON product.category_id = category.category_id";
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
				String price = rs.getString("price");
				String image = rs.getString("image");
				String stock = rs.getString("stock");
				String explanation = rs.getString("explanation");
				String isbn = rs.getString("isbn");

				//取得したデータを基にBookBeanを作成
				BookBean book = new BookBean();
				//bookBeanの中に値をセット
				book.setBook_id(book_id);
				book.setBook_name(book_name);
				book.setAuthor_id(author_id);
				book.setAuthor_name(author_name);
				book.setCategory_id(category_id);
				book.setPrice(price);
				book.setImage(image);
				book.setStock(stock);
				book.setExplanation(explanation);
				book.setIsbn(isbn);
				//リストへ追加
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
	 * 現在庫を購入在庫でマイナスする
	 * @author 名前
	 * @param stock 購入数
	 * @param bookid 書籍番号
	 * @return 成功したらtrue 失敗はfalse
	 */
	public static boolean updateStock(String stock, String bookid) {

		//↓仮
		return true;
	}

	/**
	 * 販売後に在庫がマイナスにならないか検査する
	 * @author 名前
	 * @param count 購入数
	 * @param bookid 書籍番号
	 * @return 整数ならtrue（０含む）、負数になったらfalse
	 */
	static public boolean checkStock(String count, String bookid) {

		return true;
	}
}
