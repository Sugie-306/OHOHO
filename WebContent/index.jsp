<!-- トップページ -->
<!-- 担当:あかり・杉江 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.Math"%>
<%@ page import="jp.co.eintecs.beans.*"%>
<!--ヘッダページ　-->
<%@ include file="header-top.jsp"%>
<!--メニューページ -->
<%@ include file="menu.jsp"%>
<!-- ここから記述 -->
<!-- ------------新着順4件------------ -->
<h2>
	<img class="icon" src="images/book1+.png">新着一覧
</h2>
<div class='pwrapper'>
	<%
		//リストを取得
		List<BookBean> list = (List<BookBean>) request.getAttribute("list");
		//リストの表示
		for (int i = 0; i < list.size(); i++) {
			out.print("<div class = 'p-content'>");
			out.println("<div class='p-img'><a href='DetailServlet?bookid=" + list.get(i).getBook_id()
					+ "'><image src='book_images/B" + list.get(i).getImage() + ".jpg'></a></div>");
			out.print("<h3>");
			out.print(list.get(i).getBook_name());
			out.println("</h3>");
			out.println(list.get(i).getAuthor_name());
			out.print("<br>");
			out.print(list.get(i).getPrice());
			out.println("円(税込)");
			out.print("</div>");
		}
	%>
</div>
<!-- ------------ランキング4件------------ -->
<h2>
	<img class="icon" src="images/book1+.png">売れ筋ランキング
</h2>
<div class='pwrapper'>
	<div class='pwrapper'>
		<%
			//リストを取得
			List<BookBean> ranklist = (List<BookBean>) request.getAttribute("rankList");
			//リストの表示
			for (int i = 0; i < ranklist.size(); i++) {
				out.print("<div class = 'p-content'>");
				out.println("<div class='p-img'><a href='DetailServlet?bookid=" + ranklist.get(i).getBook_id()
						+ "'><image src='book_images/B" + ranklist.get(i).getImage() + ".jpg'></a></div>");
				out.print("<h3>");
				out.print(ranklist.get(i).getBook_name());
				out.println("</h3>");
				out.println(ranklist.get(i).getAuthor_name());
				out.print("<br>");
				out.print(ranklist.get(i).getPrice());
				out.println("円(税込)");
				out.print("</div>");
			}
		%>
	</div>
</div>
<!-- レコメンド -->
<%@ include file="recomend.jsp"%>
<!-- フッター -->
<%@ include file="footer.jsp"%>