package jp.co.eintecs.beans;

import java.io.Serializable;

/**
 * 書籍情報を入れるコンテナ
 * @author sasaki
 *
 */
public class BookBean implements Serializable {

	//フィールド
	private static final long serialVersionUID = 1L;
	private String book_id = null;
	private String book_name = null;
	private String author_id = null;
	private String author_name = null;
	private String category_id = null;
	private String category_name = null;
	private String image = null;
	private String price = null;
	private String stock = null;
	private String explanation = null;
	private String isbn = null;

	//コンストラクタ
	public BookBean() {
	}

	public BookBean(String book_id, String book_name, String author_id, String author_name, String category_id,
			String category_name, String image, String price, String stock, String explanation, String isbn) {
		super();
		this.book_id = book_id;
		this.book_name = book_name;
		this.author_id = author_id;
		this.author_name = author_name;
		this.category_id = category_id;
		this.category_name = category_name;
		this.image = image;
		this.price = price;
		this.stock = stock;
		this.explanation = explanation;
		this.isbn = isbn;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}