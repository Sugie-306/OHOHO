<!-- 商品登録が完了しましたというページ -->
<!-- 担当：あかり -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 管理者用ヘッダー -->
<%@ include file="header-admin.jsp"%>
<!-- 管理者用メニューページ -->
<%@ include file="menu-admin.jsp"%>
<!-- ここから記述 -->

<%
	//リクエストからメッセージを取得
	String message = (String) request.getAttribute("message");
%>
<h1>商品情報変更</h1>

<!-- メッセージを表示 -->
<%=message%>

<!-- 商品ページに戻る -->
<div class="back">
<form action="UpdateProductServlet" method="get">
	<input type="submit" value="商品管理ページに戻る"><br>
</form>
</div>

<!-- フッター -->
<%@ include file="footer.jsp"%>