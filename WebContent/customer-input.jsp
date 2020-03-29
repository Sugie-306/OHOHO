<!-- 会員登録inputページ -->
<!-- 担当：まいまい -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--ヘッダページ　-->
<%@ include file="header.jsp"%>
<!--メニューページ -->
<%@ include file="menu.jsp"%>
<!-- ここから記述 -->

<div class="login new">
	<h1>新規会員登録</h1>
	<!-- 入力フォーム　ここから -->
	<form action="InsertUserServlet" method="post">
		<table>
			<tr>
				<td><span class="black">お名前</span></td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td><span class="black">郵便番号</span></td>
				<td><input type="text" name="post"></td>
			</tr>
			<tr>
				<td><span class="black">ご住所</span></td>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<td><span class="black">電話番号</span></td>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td><span class="black">アドレス</span></td>
				<td><input type="text" name="mail"></td>
			</tr>
			<tr>
				<td><span class="black">ユーザーID名</span></td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td><span class="black">ユーザーパスワード</span></td>
				<td><input type="text" name="userpass"></td>
			</tr>
		</table>

		<center><input type="submit" value="登録"></center><br>
	</form>
	<!-- 入力フォーム　ここまで -->
</div>


<!-- フッター -->
<%@ include file="footer.jsp"%>