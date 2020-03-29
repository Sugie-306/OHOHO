<!-- 管理者画面での注文詳細表示ページ -->
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
<h1>注文詳細</h1>
<div class="wrapper0">
	<div id="container" class="containar">
		<!-- 購入者情報 -->
		<div id="itemA" class="conteinar__item">
			<h3>購入者情報</h3>
			<c:forEach var="v" items="${list2}" varStatus="st">
				<dl class="item__list">
					<dt>氏名</dt>
					<dd>${v.order_Name}</dd>
				</dl>
				<dl class="item__list">
					<dt>郵便番号</dt>
					<dd>${v.order_Post}</dd>
				</dl>
				<dl class="item__list">
					<dt>住所</dt>
					<dd>${v.order_Address}</dd>
				</dl>
				<dl class="item__list">
					<dt>電話番号</dt>
					<dd>${v.order_Phone}</dd>
				</dl>
			</c:forEach>
		</div>
		<!-- 商品詳細 -->
		<div id="itemB" class="conteinar__item">
			<ul class="itemB__list">
				<c:forEach var="v" items="${list}" varStatus="st">
					<li><a href='DetailServlet?bookid=${v.bookid}' target="_blank"></a>
						<div class="itemB__list__img">
							<img src="book_images/B${v.image}.jpg">
						</div>
						<div>
							<h3>${v.book_Name}</h3>
							<p>${v.author_Name }</p>
							<div class="itemB__list__price">￥${v.price} × ${v.count}
								￥${v.sub}</div>
						</div></li>
				</c:forEach>
			</ul>
		</div>
		<!-- 注文情報 -->
		<div id="itemC" class="conteinar__item">

			<c:forEach var="v" items="${list2}" varStatus="st">
				<h3>注文情報</h3>
				<dl class="item__list">
					<dt>注文ID</dt>
					<dd>${v.order_id}</dd>
				</dl>
				<dl class="item__list">
					<dt>注文日</dt>
					<dd>${v.order_Day}</dd>
				</dl>
				<dl class="item__list">
					<dt>合計（税込)</dt>
					<dd>￥${v.total}</dd>
				</dl>
				<dl class="item__list">
					<dt>支払方法</dt>
					<dd>${v.pay_Rule}</dd>
				</dl>
			</c:forEach>
		</div>
	</div>

</div>
<!-- 戻るボタン -->
<div class="back">
	<input class="back" type="button" onclick="history.back()" value="戻る">
</div>
<!-- フッター -->
<%@ include file="footer.jsp"%>