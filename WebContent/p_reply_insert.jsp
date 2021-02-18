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

String contents = request.getParameter("contents") == null ? "" 
		: request.getParameter("contents");
int like_count = 0;
int hate_count = 0;
String reg_id = request.getParameter("reg_id") == null ? "" 
		: request.getParameter("reg_id");
int reg_gnum = request.getParameter("reg_gnum") == null ? Integer.parseInt("0")
		: Integer.parseInt(request.getParameter("reg_gnum"));

QueryBean.getConnection();

int res = 0;
try {
	res = QueryBean.addReplyInfo(contents, like_count, hate_count, reg_id, reg_gnum);
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