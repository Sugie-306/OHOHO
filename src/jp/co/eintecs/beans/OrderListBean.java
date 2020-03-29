package jp.co.eintecs.beans;

import java.io.Serializable;

/**
 * 注文情報詳細を入れるコンテナ（注文一覧用)
 * @author sasaki
 */

public class OrderListBean implements Serializable {
	//フィールド
	private static final long serialVersionUID = 1L;
	private String order_id = null;
	private String count = null;
	private String book_Name = null;
	private String price = null;

	//コンストラクタ
	public OrderListBean() {
	}

	public OrderListBean(String order_id, String count, String book_Name, String price) {
		super();
		this.order_id = order_id;
		this.count = count;
		this.book_Name = book_Name;
		this.price = price;
	}

	//メソッド
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getBook_Name() {
		return book_Name;
	}

	public void setBook_Name(String book_Name) {
		this.book_Name = book_Name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
