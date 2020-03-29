<!-- 管理者画面での注文一覧表示ページ -->
<!-- 担当：杉江 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- JSTLを使用するにはこの一文を記述 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 管理者用ヘッダー -->
<%@ include file="header-admin.jsp"%>
<!-- 管理者用メニューページ -->
<%@ include file="menu-admin.jsp"%>
<!-- ここから記述 -->

<%
	//リクエストからメッセージを取得
	String message = (String) request.getAttribute("message");
%>
<h1>税率変更</h1>

<!-- メッセージを表示 -->
<%=message%>

<!-- 戻るボタン -->
<div class="back">
	<input class="back" type="button" onclick="history.back()" value="戻る">
</div>
<!-- フッター -->
<%@ include file="footer.jsp"%>