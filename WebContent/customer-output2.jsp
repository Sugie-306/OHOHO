<!-- 会員情報を登録しましたというページ -->
<!--  担当：まいまい -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--ヘッダページ　-->
<%@ include file="header.jsp"%>
<!--メニューページ -->
<%@ include file="menu.jsp"%>
<!-- ここから記述 -->
<h1>会員登録完了</h1>
<hr>

<!-- 登録情報の表示 -->
次の会員情報を登録しました
<br>
会員名：${user.name }
<br>
郵便番号：${user.post }
<br>
住所：${user.address }
<br>
電話番号：${user.phone }
<br>
アドレス：${user.mail }
<br>
ログイン名：${user.username }
<br>
ログインパスワード：${user.userpass }
<br>

<!--  ログインページへ遷移 -->
<form action="login.jsp" method="get">
	<input type="submit" value="ログインページはこちらです"><br>
</form>

<hr>

<!-- フッター -->
<%@ include file="footer.jsp"%>