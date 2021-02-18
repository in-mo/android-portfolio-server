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
String num = request.getParameter("num") == "" ? "0" : request.getParameter("num");
String favor_count = request.getParameter("favor_count") == "" ? "0" : request.getParameter("favor_count");
String favor_kinds = request.getParameter("favor_kinds") == "" ? "" : request.getParameter("favor_kinds");

System.out.println("num: " + num);
System.out.println("favor_count: " + favor_count);
System.out.println("favor_kinds: " + favor_kinds);

QueryBean.getConnection();

int res = 0;
try {
	res = QueryBean.setReplyFavorCount(num, favor_count, favor_kinds);
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