<!-- 会員情報を更新しましたというページ -->
<!-- 担当：佐々木 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--ヘッダページ　-->
<%@ include file="header.jsp"%>
<!--メニューページ -->
<%@ include file="menu.jsp"%>
<!-- ここから記述 -->
<!-- メッセージを取得 -->
<%
	//リクエストからメッセージを取得
	String message = (String) request.getAttribute("message");
%>
<h1>会員情報変更</h1>

<!-- メッセージを表示 -->
<%=message%>


<!--  戻るボタン -->
<form action="UserServlet" method="get">
	<input type="submit" value="マイページへ戻る"><br>
</form>

<!-- フッター -->
<%@ include file="footer.jsp"%>