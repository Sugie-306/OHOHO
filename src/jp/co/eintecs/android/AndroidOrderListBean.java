package jp.co.eintecs.android;

/** android版【注文一覧】
	 * @author onatsu
	 *
**/

	import java.io.Serializable;

	public class AndroidOrderListBean implements Serializable {

		//フィールド
		private static final long serialVersionUID = 1L; // 適切なバージョン番号を設定
		private int orderId;
		private String bookName;
		private int count;
		private int price;
		private String orderDay;
		private int sum;
		private String userid;
		private String name;
		private String address;
		private String post;
		private String phone;
		private String mail;
		private int total ;

		//コンストラクタ------------------------------------
		AndroidOrderListBean(){}
		AndroidOrderListBean(int orderId,String bookName,int count,int price,String orderDay,int sum,String userid,
				String name,String address,String post,String phone,String mail,int total){
			this.orderId=orderId;
			this.bookName=bookName;
			this.count=count;
			this.price=price;
			this.orderDay=orderDay;
			this.sum=sum;
			this.userid=userid;
			this.name=name;
			this.address=address;
			this.post=post;
			this.phone=phone;
			this.mail=mail;
			this.total=total;


		}

		//メソッド-----------------------------------------
		public int getOrderId() {
			return orderId;
		}
		public String getBookName() {
			return bookName;
		}
		public int getCount() {
			return count;
		}
		public int getPrice() {
			return price;
		}
		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}
		public void setBookName(String bookName) {
			this.bookName = bookName;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public String getOrderDay() {
			return orderDay;
		}
		public void setOrderDay(String orderDay) {
			this.orderDay = orderDay;
		}
		public int getSum() {
			return sum;
		}
		public void setSum(int sum) {
			this.sum = sum;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPost() {
			return post;
		}
		public void setPost(String post) {
			this.post = post;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getMail() {
			return mail;
		}
		public void setMail(String mail) {
			this.mail = mail;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}



	}