<!-- マイページ用のページです -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTLを使用するにはこの一文を記述 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="jp.co.eintecs.beans.*"%>
<!--ヘッダページ　-->
<%@ include file="header.jsp"%>
<!--メニューページ -->
<%@ include file="menu.jsp"%>
<!-- ここから記述 -->
<!-- 顧客ページ -->
<h1>購入履歴</h1>
<!-- 注文一覧 -->
<c:forEach var="v" items="${orderlist}" varStatus="st">
	<div class="orderlist-wrap">
		<a href="UserOrderListServlet?id=${v.order_id}"></a>
		<div class="orderlist-text">
			<span class="ol s1">OrderNo:${v.order_id}</span> <span class="ol s2">${v.order_Name}</span>
			<span class="ol s3">¥${v.total} （${v.count}点）</span> <span
				class="ol s4">${v.order_Day}</span>
		</div>
		<div class="orderlist-img">
			<img src="book_images/B${v.image}.jpg">
		</div>
	</div>
	<br>
</c:forEach>



<!-- 会員情報変更 -->
<h1>会員情報の変更</h1>
<form action="UpdateUserServlet" method="post">

	<div class="customer-input">
		<table>
			<tr>
				<td>お名前</td>
				<td><input type="text" name="name" size="35"
					value="${userdata.name}"></td>
			</tr>
			<tr>
				<td>郵便番号</td>
				<td><input type="text" name="post" size="35"
					value="${userdata.post}"></td>
			</tr>
			<tr>
				<td>ご住所</td>
				<td><input type="text" name="address" size="35"
					value="${userdata.address}"></td>
			</tr>
			<tr>
				<td>電話番号</td>
				<td><input type="text" name="phone" size="35"
					value="${userdata.phone}"></td>
			</tr>
			<tr>
				<td>メール</td>
				<td><input type="text" name="mail" size="35"
					value="${userdata.mail}"></td>
			</tr>
			<tr>
				<td>ログイン名</td>
				<td><input type="text" name="username" size="35"
					value="${userdata.username}"></td>
			</tr>
			<tr>
				<td>パスワード</td>
				<td><input type="password" name="userpass" size="35"
					value="${userdata.userpass}"></td>
			</tr>
		</table>


		<!--  <input type="submit" value="更新" > -->
		<input type="submit" value="更新">
	</div>
</form>



<!-- フッター -->
<%@ include file="footer.jsp"%>