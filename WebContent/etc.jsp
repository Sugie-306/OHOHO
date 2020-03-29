<!-- このページは税率変更（管理者用)ページ -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--ヘッダページ　-->
<%@ include file="header-top.jsp"%>
<!--メニューページ -->
<%@ include file="menu.jsp"%>
<!-- ここから記述 -->

<!--税率変更 --->
<br>
【税率変更】
<br>
<form action="UpdateTaxServlet" method="get">
	税率：
	<%
	out.print(
			"<input type='number' step='0.1' min='0.0' name='tax' value=" + request.getAttribute("tax") + ">");
%>
	<input type="submit" value="税率変更">
</form>
<hr>
<!-- 戻るボタン -->
<div class="back">
	<input class="back" type="button" onclick="history.back()" value="戻る">
</div>

<!-- フッター -->
<%@ include file="footer.jsp"%>