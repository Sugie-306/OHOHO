<!-- 管理者トップ -->
<!-- 担当：杉江 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 管理者用ヘッダー -->
<%@ include file="header-admin.jsp"%>
<!-- 管理者用メニューページ -->
<%@ include file="menu-admin.jsp"%>
<!-- 主なメニュー --->
<h1 class="admin-title">管理者ページ</h1>
<div class="admin_manag">
	<div class="admin_box box1">
		<a href="OrderListServlet" class="button1"><div class="admin_text">
				<img src="images/icon_1.png" width="50px"><br>注文一覧
			</div></a>
	</div>
	<div class="admin_box box2">
		<a href="UpdateProductServlet" class="button2"><div class="admin_text">
				<img src="images/icon_2.png" width="50px"><br>商品管理
			</div></a>
	</div>
	<div class="admin_box box3">
		<a href="AdminEtcServlet" class="button３"><div class="admin_text">
				<img src="images/icon_3.png" width="50px"><br>その他の管理
			</div></a>
	</div>
</div>

<!-- フッター -->
<%@ include file="footer.jsp"%>
