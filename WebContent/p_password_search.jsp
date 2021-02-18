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

String id = request.getParameter("id") == null ? "" : request.getParameter("id");
String name = request.getParameter("name") == null ? "" : request.getParameter("name");
String email = request.getParameter("email") == null ? "" : request.getParameter("email");

QueryBean.getConnection();

String password = "";
try {
	password = QueryBean.searchPassword(id, name, email);
} catch (Exception e) {
	System.out.print(e.toString());
} finally {
	QueryBean.closeConnection();
}

out.print("[");
out.print("{ ");
out.print("\"RESULT_OK\": \"" + password + "\" ");
out.print("} ");
out.print("]");

System.out.println("res: " + password);
%>