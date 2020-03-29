package jp.co.eintecs.beans;

import java.io.Serializable;

/**
 * カート情報を入れるコンテナ
 * @author sugie
 * @author tak
 * @author sasaki
 */

public class BasketBean implements Serializable {
	//フィールド
	private static final long serialVersionUID = 1L;

	private String uid;
	private String bookid;
	private String book_Name;
	private String author_Name;
	private String order_Day;
	private int count;
	private int price;
	private int sub;
	private int total;
	private int order_Post;
	private String order_Phone;
	private String order_Address;
	private String order_Name;
	private int order_id;

	//コンストラクタ
	public BasketBean() {
	}

	public BasketBean(String bookid, String book_Name, String author_Name, String order_Day, int count, int price,
			int sub, int total, int order_Post, String order_Phone, String order_Address, String order_Name,
			int order_id) {
		super();
		this.bookid = bookid;
		this.book_Name = book_Name;
		this.author_Name = author_Name;
		this.order_Day = order_Day;
		this.count = count;
		this.price = price;
		this.sub = sub;
		this.total = total;
		this.order_Post = order_Post;
		this.order_Phone = order_Phone;
		this.order_Address = order_Address;
		this.order_Name = order_Name;
		this.order_id = order_id;
	}

	//メソッド
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public String getBook_Name() {
		return book_Name;
	}

	public void setBook_Name(String book_Name) {
		this.book_Name = book_Name;
	}

	public String getAuthor_Name() {
		return author_Name;
	}

	public void setAuthor_Name(String author_Name) {
		this.author_Name = author_Name;
	}

	public String getOrder_Day() {
		return order_Day;
	}

	public void setOrder_Day(String order_Day) {
		this.order_Day = order_Day;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSub() {
		return sub;
	}

	public void setSub(int sub) {
		this.sub = sub;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getOrder_Post() {
		return order_Post;
	}

	public void setOrder_Post(int order_Post) {
		this.order_Post = order_Post;
	}

	public String getOrder_Phone() {
		return order_Phone;
	}

	public void setOrder_Phone(String order_Phone) {
		this.order_Phone = order_Phone;
	}

	public String getOrder_Address() {
		return order_Address;
	}

	public void setOrder_Address(String order_Address) {
		this.order_Address = order_Address;
	}

	public String getOrder_Name() {
		return order_Name;
	}

	public void setOrder_Name(String order_Name) {
		this.order_Name = order_Name;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

}
