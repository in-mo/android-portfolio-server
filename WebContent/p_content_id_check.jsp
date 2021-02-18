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

System.out.println("id: " + id);

QueryBean.getConnection();

int res = 0;
try {
	res = QueryBean.checkId(id);
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