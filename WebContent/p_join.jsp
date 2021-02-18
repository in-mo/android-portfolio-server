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

String id = request.getParameter("id") == null ? "" : request.getParameter("id").trim();
String password = request.getParameter("password") == null ? "" : request.getParameter("password").trim();
String name = request.getParameter("name") == null ? "" : request.getParameter("name").trim();
String email = request.getParameter("email") == null ? "" : request.getParameter("email").trim();

System.out.println("id: " + id);
System.out.println("name: " + password);
System.out.println("phone: " + name);
System.out.println("grade: " + email);

QueryBean.getConnection();

int res = 0;
try {
	res = QueryBean.addUserInfo(id, password, name, email);
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