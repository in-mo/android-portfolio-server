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

String title = request.getParameter("title") == "" ? "제목없음" : request.getParameter("title");
String contents = request.getParameter("contents") == "" ? "내용없음" : request.getParameter("contents");
String reg_id = request.getParameter("reg_id") == null ? "??" : request.getParameter("reg_id").trim();

System.out.println("title: " + title);
System.out.println("contents: " + contents);
System.out.println("reg_id: " + reg_id);

QueryBean.getConnection();

int res = 0;
try {
	res = QueryBean.addContentInfo(title, contents, reg_id);
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