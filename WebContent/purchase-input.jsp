<!-- 購入確認のページ -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- LIstを使うのでインポート記述が必要 -->
<%@ page import="java.util.*"%>
<!-- Beanを使うのでBeanが入っているパッケージをインポートする -->
<%@ page import="jp.co.eintecs.beans.*"%>
<%@ page import="jp.co.eintecs.dao.*"%>
<!--ヘッダページ　-->
<%@ include file="header.jsp"%>
<!--メニューページ -->
<%@ include file="menu.jsp"%>
<!-- ここから記述 -->

<H1>ご注文内容の確認</H1>
	ご注文内容の確認・変更
	<hr>
		<b>お届け先住所</b>
		<br>

		<form action="OrderServlet" method="post">

		<br>
		<table>
			<%
			//結果表示を取得
			UserBean userlist = (UserBean) request.getAttribute("userlist");
		out.print("<tr><td>お名前</td><td><input type='text' name='name' value='"+userlist.getName() + "'></td></tr>");
		out.print("<tr><td>郵便番号</td><td><input type='text' name='post' value='"+userlist.getPost() + "'></td></tr>");
		out.print("<tr><td>ご住所</td><td><input type='text' name='address' value='"+userlist.getAddress() + "'></td></tr>");
		out.print("<tr><td>電話番号</td><td><input type='text' name='phone' value='"+userlist.getPhone() + "'></td></tr>");
		out.print("<tr><td>メール</td><td><input type='text' name='mail' value='"+userlist.getMail() + "'></td></tr>");


%>
</table>
	<br>
<%
	double tax = 0.0;
	tax = TaxDAO.NowTax();


		//結果表示を取得
		List<BasketBean> list = (List<BasketBean>) request.getAttribute("list");
		//検索結果表示
		if (list.size() != 0) {
			int sum = 0;
			int sum2 =0;
			for (int i = 0; i < list.size(); i++) {
				out.print(list.get(i).getBook_Name());
				out.print("　");
				out.print(list.get(i).getAuthor_Name());
				out.print("　");
				out.print(list.get(i).getCount());
				out.print("個");
				out.print("　");
				out.print("単価");
				out.print(list.get(i).getPrice());
				out.print("円");
				out.print("<br>");
				sum += (list.get(i).getPrice() * list.get(i).getCount()*tax);
				out.print("　　小計：" + list.get(i).getPrice() * list.get(i).getCount() + "円");
				out.print("　　消費税："+String.format("%,.0f",list.get(i).getPrice() * list.get(i).getCount()*tax - list.get(i).getPrice() * list.get(i).getCount())+"円");
				out.print("<br>");
				out.print("<br>");

			}
			HttpSession ses = request.getSession();
			ses.setAttribute("cartlist", list);

			out.print("合計:"+ sum + "円");
			out.print("<br>");
			out.print("</tr>");

		} else {
			out.print("エラーが発生しました。初めからやり直してください");
		}
		%>
	<p>内容をご確認いただき、注文を確定してください</p>
	<br>お支払い方法：<select name="payrule" size="1">
		<option value="1" selected="selected">銀行振込</option>
		<option value="2">代金引換</option>
		<option value="3">クレジットカード</option>
	</select> <input type="submit" value="注文確定をする">
</form>
<br>
<br>

<!-- フッター -->
<%@ include file="footer.jsp"%>