<!--  ログインページ -->
<!-- 担当：杉江 -->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="menu.jsp"%>

<!-- ログインフォーム -->
<div class="login">
	<h1>OHOHOにログイン</h1>
	<br>
	<form action="LoginServlet" method="post">
		<span class="black">ユーザーID:</span> <input type="text" name="userName"><br>
		<span class="black">パスワード:</span> <input type="password" name="pass">
		<input type="submit" value="ログイン">
	</form>
	<!-- 新規登録へリンク -->
	<p>
		<a href="customer-input.jsp">新規会員登録はこちら</a>
	</p>
</div>
<%@ include file="footer.jsp"%>