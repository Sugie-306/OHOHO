<!--  ログインエラー表示 -->
<!-- 担当：杉江 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--ヘッダページ　-->
<%@ include file="header.jsp"%>
<!--メニューページ -->
<%@ include file="menu.jsp"%>
<!-- ここから記述 -->

<div class="login">
	<p class="black loginerror">
		ログインに失敗しました。
	</p>
	<!-- 戻るボタン -->
	<div class="back">
		<input class="back" type="button" onclick="history.back()" value="戻る">
	</div>
</div>



<!-- フッター -->
<%@ include file="footer.jsp"%>