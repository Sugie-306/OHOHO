<!-- 商品一覧 -->
<!-- 担当：佐々木 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="jp.co.eintecs.beans.*"%>

<!--ヘッダーページ　-->
<%@ include file="header.jsp"%>
<!--メニューページ -->
<%@ include file="menu.jsp"%>

<!-- 商品一覧 -->
<div class="pwrapper">
	<%
		//検索結果を取得
		List<BookBean> list = (List<BookBean>) request.getAttribute("list");
		for (int i = 0; i < list.size(); i++) {
			out.print("<div class = 'p-content'>");
			out.println("<div class='p-img'><a href='DetailServlet?bookid=" + list.get(i).getBook_id()
					+ "'><img src='book_images/B" + list.get(i).getIsbn() + ".jpg'></a></div>");
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


<!-- フッター -->
<%@ include file="footer.jsp"%>
