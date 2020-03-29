<!-- トップページ用ヘッダーページ-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/style.css">
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
	rel="stylesheet">
<title>OHOHO (おほほ）| ニッチな本の販売専門サイト</title>

<!--ファビコン-->
<link rel="Shortcut Icon" href="images/favicon.ico">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Serif+JP:300,400,500,700|Pacifico&display=swap"
	rel="stylesheet">
<%
	//変数を宣言
	String icon_msg = "";
	String icon_tag = "";
	//セッションを取得
	String adminFlag2 = (String) session.getAttribute("adminFlag");
	String userId2 = (String) session.getAttribute("userId");
	//セッションをもとにユーザー判断
	if (adminFlag2 != null) {
		;
		if (adminFlag2.equals("1")) {//管理者
			icon_msg = "管理者ページ";
			icon_tag = "<a href='admin.jsp'><i class='fas fa-user-cog  menuicon'></i></a>";
		} else if (userId2 != null) {//ユーザー
			icon_msg = "マイページ";
			icon_tag = "<a href='UserServlet'><i class='fas fa-smile  menuicon'></i></a>";
		}
	} else {//ログアウト時
		icon_msg = "新規会員登録";
		icon_tag = "<a href='customer-input.jsp'><i class='fas fa-pen-nib  menuicon'></i></a>";
	}
%>
</head>
<!-- bodyスタート -->
<body>
	<!-- TOP写真 ここから-->
	<img class="topimg" src="images/top1.jpg">
	<div class="top text1">Ohoho</div>
	<div class="top text2">思わず微笑む書店へようこそ</div>
	<!-- TOP写真 ここまで -->
	<div id="wrapper">
		<div id="header">
			<div id="headerInner">
				<!-- ロゴ小 -->
				<div class="logoWrapper">
					<a href="TopServlet"><span class="logo">Ohoho</span></a>
					<p class="toptext">逆から読んでもOhoho。おっと、ホントに、おもしろい。</p>
				</div>
				<!-- 検索フォーム -->
				<div class="menu-search">
					<form action="SearchBookServlet" method="post">
						<input type="search" name="word" placeholder="キーワードを入力してください。"
							style="width: 240px;"> <select name="category"
							style="height: 22.4px;">
							<option value="0">全ジャンル</option>
							<option value="1">小説</option>
							<option value="2">コミック</option>
							<option value="3">エッセイ</option>
							<option value="4">オリジナル</option>
						</select> <input type="submit" name="submit" value="検索">
					</form>
				</div>
				<!-- アイコンメニュー -->
				<ul class="head-sub">
					<!-- カート -->
					<li><a href="BasketViewServlet"> <i
							class="fas fa-shopping-cart menuicon"></i></a>
						<p>カート</p></li>
					<!-- マイページボタンの変数 -->
					<li><%=icon_tag%>
						<p><%=icon_msg%></p></li>
				</ul>
			</div>
		</div>
	</div>
<body>