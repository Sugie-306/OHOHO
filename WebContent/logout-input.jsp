<!-- ログアウトぺージ -->
<!-- 担当:杉江 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--ヘッダページ　-->
<%@ include file="header.jsp"%>
<!--メニューページ -->
<%@ include file="menu.jsp"%>
<!-- ここから記述 -->

<body>
<div class="logout">
	<form action="LogoutServlet" method="get">
	<h1>NOHOHONからログアウト</h1><br>
	  <p class="black">ログアウトしますか？</p><br>
	  <input type="submit" value="ログアウトする">
	  </form>

</div>
</body>

<!-- フッター -->
<%@ include file="footer.jsp"%>
