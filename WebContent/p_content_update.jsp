<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="QueryBean" scope="page" class="AppDB.QueryBean" />
<jsp:setProperty name="QueryBean" property="*" />
<%
	response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

request.setCharacterEncoding("UTF-8");
String num = request.getParameter("num") == "" ? "999" : request.getParameter("num");
String title = request.getParameter("title") == "" ? "내용없음" : request.getParameter("title");
String contents = request.getParameter("contents") == null ? "??" : request.getParameter("contents");

System.out.println("num: " + num);
System.out.println("title: " + title);
System.out.println("contents: " + contents);

QueryBean.getConnection();

int res = 0;
try {
	res = QueryBean.updateContent(num, title, contents);
} catch (Exception e) {
	System.out.print(e.toString());
} finally {
	QueryBean.closeConnection();
}

out.print("[");
out.print("{ ");
out.print("\"RESULT_OK\": \"" + res + "\" ");
out.print("} ");
out.print("]");

System.out.println("res: " + res);
%>