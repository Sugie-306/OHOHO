package jp.co.eintecs.beans;

import java.io.Serializable;

/**
 * 注文情報を入れるコンテナ
 * @author sasaki
 * @author sugie
 *
 */
public class OrderBean implements Serializable{

	private String user_id;
	private String bookid;
	private String book_Name;
	private String image;
	private String author_Name;
	private String order_Day;
	private String count;
	private String price;
	private String sub;
	private String total;
	private String order_Post;
	private String order_Phone;
	private String order_Address;
	private String order_Name;
	private int order_id;
	private String pay_Rule;

	public OrderBean() {
	}

	public OrderBean(String user_id, String bookid, String book_Name, String image, String author_Name,
			String order_Day, String count, String price, String sub, String total, String order_Post,
			String order_Phone, String order_Address, String order_Name, int order_id, String pay_Rule) {
		super();
		this.user_id = user_id;
		this.bookid = bookid;
		this.book_Name = book_Name;
		this.image = image;
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
		this.pay_Rule = pay_Rule;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getOrder_Post() {
		return order_Post;
	}

	public void setOrder_Post(String order_Post) {
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

	public String getPay_Rule() {
		return pay_Rule;
	}

	public void setPay_Rule(String pay_Rule) {
		this.pay_Rule = pay_Rule;
	}

}