<!-- 商品登録が完了しましたというページ -->
<!-- 担当：あかり -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 管理者用ヘッダー -->
<%@ include file="header-admin.jsp"%>
<!-- 管理者用メニューページ -->
<%@ include file="menu-admin.jsp"%>
<!-- ここから記述 -->
商品登録が失敗しました。<br>
・ISBNコードまたは画像番号がすでに存在しています。

<form action="UpdateProductServlet" method="get">
	<input type="submit" value="商品管理ページに戻る"><br>
</form>

<!-- フッター -->
<%@ include file="footer.jsp"%>