<!-- 検索結果を表示するjsp -->
<!-- 担当：杉江 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="jp.co.eintecs.beans.*"%>
<!-- JSTLを使用するにはこの一文を記述 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--ヘッダートップページ　-->
<%@ include file="header.jsp"%>
<%@ include file="menu.jsp"%>
<!-- ここから記述 -->

<%
	//リクエストの中の検索ワードとカテゴリネームを受け取る
	String word = (String) request.getAttribute("word");
	String c_name = (String) request.getAttribute("c_name");
%>
検索ワード:<%=word%>
カテゴリ:<%=c_name%>

<!-- 検索結果表示 -->
<div class="pwrapper">
	<c:forEach var="v" items="${list}" varStatus="st">
		<div class="p-content">
			<div class="p-img">
				<a href='DetailServlet?bookid=${v.book_id}'><img
					src="book_images/B${v.image}.jpg"></a>
			</div>
			<a href="DetailServlet?bookid=${v.book_id}">${v.book_name}</a><br>
			${v.price}円<br> <br>

		</div>
	</c:forEach>
</div>
<!--フッターページ -->
<%@ include file="footer.jsp"%>