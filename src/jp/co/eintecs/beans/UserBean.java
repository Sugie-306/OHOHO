package jp.co.eintecs.beans;

import java.io.Serializable;

/**
 * ユーザ情報を入れるコンテナ
 * @author sugie
 * @author tak
 * @author sasaki
 */

public class UserBean implements Serializable {
	//フィールド
	private static final long serialVersionUID = 1L;//バージョン番号
	private String userid;
	private String name;
	private String address;
	private String post;
	private String phone;
	private String mail;
	private String username;
	private String userpass;
	private String flg;
	private String adminflag;

	//コンストラクタ
	public UserBean() {
	}

	public UserBean(String userid, String name, String address, String post, String phone, String mail, String username,
			String userpass, String flg, String adminflag) {
		super();
		this.userid = userid;
		this.name = name;
		this.address = address;
		this.post = post;
		this.phone = phone;
		this.mail = mail;
		this.username = username;
		this.userpass = userpass;
		this.flg = flg;
		this.adminflag = adminflag;
	}

	//メソッド
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

	public String getAdminflag() {
		return adminflag;
	}

	public void setAdminflag(String adminflag) {
		this.adminflag = adminflag;
	}

}
