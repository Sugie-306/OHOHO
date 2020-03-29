<!-- 管理者用　商品管理画面 -->
<!-- 担当：あかり -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="jp.co.eintecs.beans.*"%>

<!-- 管理者用ヘッダー -->
<%@ include file="header-admin.jsp"%>
<!-- 管理者用メニューページ -->
<%@ include file="menu-admin.jsp"%>

<!-- 商品登録 -->
<table>
	<form action="InsertBookServlet" method="post">
		<input type="hidden" name="command" value="insert">
		<h2 id="reload">
			<img class="icon" src="images/book1+.png">新規商品登録
		</h2>
		<tr>
			<td>商品名</td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>作者名</td>
			<td><input type="text" name="author"></td>
		</tr>
		<tr>
			<td>カテゴリー</td>
			<td><select name="category" size="1">
					<option value="1" selected="selected">小説</option>
					<option value="2">コミック</option>
					<option value="3">エッセイ</option>
					<option value="4">オリジナル</option>

			</select></td>
		</tr>
		<tr>
			<td>価格</td>
			<td><input type="number" name="price" min=0></td>
		</tr>
		<tr>
			<td>ISBN</td>
			<td><input type="number" name="isbn"></td>
		</tr>
		<tr>
			<td>在庫</td>
			<td><input type="number" name="stock" min=0></td>
		</tr>
		<tr>
			<td>画像（※番号を入力してください）</td>
			<td><input type="text" name="image"></td>
		</tr>
		<tr>
			<td>説明文</td>
			<td><textarea name="contents" rows="3" cols="50" wrap="hard">説明文を入力</textarea></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="商品を登録する"></td>
		</tr>
	</form>
</table>
<!-- 商品情報修正 -->

<h2 id="reload">
	<img class="icon" src="images/book1+.png">商品情報修正
</h2>
<%
	//検索結果を取得
	List<BookBean> list = (List<BookBean>) request.getAttribute("list");
	for (int i = 0; i < list.size(); i++) {
		//カテゴリの判断
		String tag = "";
		switch(list.get(i).getCategory_id()) {
		case "1":
			tag="<select name='category_id' size='1'><option value= '1' selected='selected'>小説</option><option value='2'>コミック</option><option value='3'>エッセイ</option><option value='4'>オリジナル</option>";
			break;
		case "2":
			tag="<select name='category_id' size='1'><option value= '1' >小説</option><option value='2' selected='selected'>コミック</option><option value='3'>エッセイ</option><option value='4'>オリジナル</option>";
			break;
		case "3":
			tag="<select name='category_id' size='1'><option value= '1' >小説</option><option value='2' >コミック</option><option value='3' selected='selected'>エッセイ</option><option value='4'>オリジナル</option>";
			break;
		case "4":
			tag="<select name='category_id' size='1'><option value= '1' >小説</option><option value='2' >コミック</option><option value='3'>エッセイ</option><option value='4' selected='selected'>オリジナル</option>";
			break;
		}
		out.print("<table>");
		out.print("<form action='UpdateBookServlet' method='post'>");
		out.print("<input type='hidden' name='author_id'  value='" + list.get(i).getAuthor_id() + "'");
		out.print("<tr><h2>商品番号：" + list.get(i).getBook_id() + "<input type='hidden' name='book_id' value='"
				+ list.get(i).getBook_id() + "'>");
		out.print("</h2><tr></tr><td>書籍名：<input type='text' name='book_name' value='"
				+ list.get(i).getBook_name() + "'>");
		out.print("<tr></tr></td><td>著者名：<input type='text' name='author' value='"
				+ list.get(i).getAuthor_name() + "'>");
		out.print("</td><td>&ensp; 価格：<input type='text' name='price' value='" + list.get(i).getPrice() + "'>");
		out.print(
				"<td>&ensp; カテゴリ："+ tag);

		out.print("<tr></tr></td><td>  &ensp;ISBN：<input type='number' name='isbn' value='"
				+ list.get(i).getIsbn() + "'>");
		out.print("</td><td>&ensp; 在庫：<input type='text' name='stock' value='" + list.get(i).getStock() + "'>");
		out.print("<td>&ensp; 画像：<input type='text' name='image' value='" + list.get(i).getImage()
				+ "'style='width: 30px;''rows='2'> .jpg←登録した画像番号を入力してください。");
		out.print(
				"</table><br> &emsp;詳細：<input type='text' name='contents' value='"
						+ list.get(i).getExplanation() + "'style='width:800px; height:30px;'>");
		out.print("&ensp;<input type='submit' value='更新'>");
		out.print("</form>");
		out.print("<br>");
		out.print("<br>");
	}
%>

<!-- 戻るボタン -->
<div class="back">
	<input class="back" type="button" onclick="history.back()" value="戻る">
</div>

<!-- フッター -->
<%@ include file="footer.jsp"%>

