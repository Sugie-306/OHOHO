<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OHOHO| 黒歴史本販売専門サイト</title>
<link rel="stylesheet" href="css/style.css">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Serif+JP:300,400,500,700|Pacifico&display=swap"
	rel="stylesheet">
<!--ファビコン-->
<link rel="Shortcut Icon" href="images/favicon.ico">
<%
	//変数を宣言
	String icon_msg = "";
	String icon_tag = "";
	//セッションを取得
	String adminFlag2 = (String) session.getAttribute("adminFlag");
	String userId2 = (String) session.getAttribute("userId");
	//セッションがあるか判断
	if (adminFlag2 != null) {
		if (adminFlag2.equals("1")) {
			icon_msg = "管理者ページ";
			icon_tag = "<a href='admin.jsp'><i class='fas fa-user-cog  menuicon'></i></a>";
		} else if (userId2 != null) {
			icon_msg = "マイページ";
			icon_tag = "<a href='UserServlet'><i class='fas fa-smile  menuicon'></i></a>";
		}
	} else {
		icon_msg = "新規会員登録";
		icon_tag = "<a href='customer-input.jsp'><i class='fas fa-pen-nib  menuicon'></i></a>";
	}
%>
</head>
<body>
<div class="band">現の中に微睡む夢の望みの全てを叶えます。</div>
<div id="wrapper">
	<div id="header">
		<div id="headerInner">
			<div class="logoWrapper">
				<a href="TopServlet"><span class="logo">Ohoho</span></a>
				<p class="toptext">逆から読んでもOhoho。おっと、ホントに、おもしろい。</p>
			</div>

			<!-- 検索を入れる javaおねしゃす -->
			<div class="menu-search">
				<form action="SearchBookServlet" method="post">
					<input type="search" name="word" placeholder="キーワードを入力してください。"
						style="width: 240px;"> <select name="category"
						style="height: 22.4px;">
						<option value="0">全ジャンル</option>
						<option value="1">小説</option>
						<option value="2">コミック</option>
						<option value="3">エッセイ</option>

					</select> <input type="submit" name="submit" value="検索">
				</form>

			</div>

			<!-- アイコンメニュー -->
			<ul class="head-sub">
				<!-- カート -->
				<li><a href="BasketViewServlet"> <i
						class="fas fa-shopping-cart menuicon"></i></a>
					<p>カート</p></li>

				<!-- マイページ -->
				<li><%= icon_tag %>
					<p><%=icon_msg%></p></li>
			</ul>
		</div>
	</div>
</div>