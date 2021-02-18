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

// buffer.append("contents").append("=").append(strings[0]);
// buffer.append("&reg_id").append("=").append(strings[1]);
// buffer.append("&reg_gnum").append("=").append(strings[2]);
String num = request.getParameter("num") == null ? "0" : request.getParameter("num");
String contents = request.getParameter("contents") == null ? "" 
		: request.getParameter("contents");

QueryBean.getConnection();

int res = 0;
try {
	res = QueryBean.updateReplyInfo(num, contents);
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