<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<%
	//*****メニューに関する記述******//
	//****ログイン済みかチェックする部分****//

	//リンク先変数
	String menupage = "";
	String loginout = "";
	// セッションを取得
	String adminFlag = (String) session.getAttribute("adminFlag");
	String userId = (String) session.getAttribute("userId");
	//セッションがあるか判断
	if (adminFlag != null || userId != null) {
		//ログアウト用ページ
		loginout = "ログアウト";
		menupage = "logout-input.jsp";
	} else {
		// ログイン用ページ
		loginout = "ログイン";
		menupage = "login.jsp";
	}
%>
<ul id="gnav">
	<li><a href="TopServlet">トップ</a></li>
	<li><a href="BookListServlet">商品一覧</a></li>
	<li><a href="shop.jsp">会社概要</a></li>
	<li><a href="<%=menupage%>"><%=loginout%></a></li>
</ul>