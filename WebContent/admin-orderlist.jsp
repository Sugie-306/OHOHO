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
<h1>注文一覧</h1>

<!-- 注文一覧 -->
<c:forEach var="v" items="${list1}" varStatus="st">
	<div class="orderlist-wrap">
		<a href="OrderListDetailServlet?id=${v.order_id}"></a>

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

<!-- 戻るボタン -->
<div class="back">
	<input class="back" type="button" onclick="history.back()" value="戻る">
</div>
<!-- フッター -->
<%@ include file="footer.jsp"%>