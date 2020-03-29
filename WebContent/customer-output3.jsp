<!-- 会員情報の登録が失敗しましたというページ -->
<!-- 担当：まいまい -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--ヘッダページ　-->
<%@ include file="header.jsp"%>
<!--メニューページ -->
<%@ include file="menu.jsp"%>
<!-- ここから記述 -->
<h1>会員登録失敗</h1>
<hr>
<!-- メッセージ -->
使用されているユーザー名です。
<br>
ユーザー名：${user.username }
<br>
<form action="customer-input.jsp" method="get">
	<input type="submit" value="こちらからやり直してください"><br>
</form>

<hr>

<!-- フッター -->
<%@ include file="footer.jsp"%>