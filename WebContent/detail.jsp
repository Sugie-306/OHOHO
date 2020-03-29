<!-- 商品詳細ページ -->
<!-- 担当：杉江 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="jp.co.eintecs.beans.*"%>
<!--ヘッダページ　-->
<%@ include file="header.jsp"%>
<!--メニューページ -->
<%@ include file="menu.jsp"%>
<!-- ここから記述 -->

<%
	//変数
	String button = "";
	//リクエストのbookを受け取る
	BookBean book = (BookBean) request.getAttribute("book");
	//Beanの中からストックを取得
	int stock = Integer.parseInt(book.getStock());
	//セレクトの中身を
	//Stockをもとにボタンの表示とセレクトを変更
	if (stock == 0) {
		button = "<input class='cartbutton' type='button' value='現在お取扱いがありません'>";
	} else {
		button = "<input class='cartbutton' type='submit' value='カートに追加'>";
	}
%>

<!-- 商品詳細 ここから-->
<div class="detail-wrap">
	<div class="detail-content-img">
		<img src="book_images/B${book.image}.jpg">
	</div>
	<div class="detail-content-text">
		<form action="BasketAddServlet" method="post">
			<input type="hidden" name="bookid" value="${book.book_id }">
			<p class="colum">${book.category_name}</p>
			<h1 class="sttitle">${book.book_name}</h1>
			<span>著者 :</span><span><a>${book.author_name}</a></span>
			<div class="pstext">${book.explanation}</div>
			<div class="bottom">
				<div class="select">
					<select name="count">
						<%
							for (int i = 1; i <= stock; i++) {
								out.print("<option value='" + i + "'>" + i + "</option>");
							}
						%>
					</select> <span>在庫数 : ${book.stock}</span>
				</div>
				<div class="price">
					<span>(税込)</span><span class="spprice">${book.price}円</span>
				</div>
				<%=button%>
			</div>
		</form>
	</div>
</div>
<!-- 商品詳細 ここまで-->

<!-- 戻るボタン -->
<div class="back">
	<input class="back" type="button" onclick="history.back()" value="戻る">
</div>
<!-- フッター -->
<%@ include file="footer.jsp"%>