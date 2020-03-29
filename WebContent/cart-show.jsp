<!-- カートの中身を見るページ -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- LIstを使うのでインポート記述が必要 -->
<%@ page import="java.util.*"%>
<!-- Beanを使うのでBeanが入っているパッケージをインポートする -->
<%@ page import="jp.co.eintecs.beans.*"%>
<!--ヘッダページ　-->
<%@ include file="header.jsp"%>
<!--メニューページ -->
<%@ include file="menu.jsp"%>
<!-- ここから記述 -->
<!DOCTYPE html>
<html lang="jp">
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%
		//結果表示を取得
		List<BasketBean> list = (List<BasketBean>) request.getAttribute("list");
		//検索結果表示
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				//out.print(list.get(i).getUid());
				//out.print("<br>");
				//out.print(list.get(i).getBookid());
				//out.print("<br>");
				out.print("商品名：");
				out.print(list.get(i).getBook_Name());
				out.print("　　");
				out.print("著者：");
				out.print(list.get(i).getAuthor_Name());
				out.print("　個数　");
				out.print(list.get(i).getCount());
				out.print("個　　");
				out.print("×　　単価　");
				out.print(list.get(i).getPrice());
				out.print("円");
				out.print("<br>");
			}
			HttpSession ses = request.getSession();
			ses.setAttribute("cartlist", list);

			out.print("<form action='OrderViewServlet' method='post'>");
			out.print("<input type='submit' value='購入手続き'>");
			out.print("　");
			out.print("<input type='submit' formaction='DeleteCartServlet' value='買い物かごクリア'>　");
			out.print("</form>");
			out.print("</tr>");


		} else {
			out.print("カートに商品がありません。");
		}
		HttpSession se = request.getSession();
		se.setAttribute("list", list);
		out.print("</form>");
	%>
	<!-- フッター -->
	<%@ include file="footer.jsp"%>
</body>
</html>