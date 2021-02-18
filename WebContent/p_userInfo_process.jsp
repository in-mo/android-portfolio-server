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
String password = request.getParameter("password") == null ? "" : request.getParameter("password");
String name = request.getParameter("name") == null ? "" : request.getParameter("name");
String email = request.getParameter("email") == null ? "" : request.getParameter("email");
String type = request.getParameter("type") == null ? "" : request.getParameter("type");

QueryBean.getConnection();

ArrayList userInfo = new ArrayList();

try {
	userInfo = QueryBean.managementUserInfo(id, type, password, name, email);
} catch (Exception e) {
	System.out.print(e.toString());
} finally {
	QueryBean.closeConnection();
}

if (type.equals("get")) {
	out.println("{");
	out.println("\"datas\":[");

	if (userInfo.size() == 0) {

		out.println("]");
		out.println("}");
	} else {

		out.print("{ ");
		out.print("\"ID\": \"" + (String) userInfo.get(0) + "\", ");
		out.print("\"PASSWORD\": \"" + (String) userInfo.get(1) + "\", ");
		out.print("\"NAME\": \"" + (String) userInfo.get(2) + "\", ");
		out.print("\"EMAIL\": \"" + (String) userInfo.get(3) + "\", ");
		out.print("\"REG_DATE\": \"" + (String) userInfo.get(4) + "\" ");
		out.print("} ");

		out.println("]");
		out.println("}");
	}
} else {
	out.print("[");
	out.print("{ ");
	out.print("\"RESULT_OK\": \"" + userInfo.get(0) + "\" ");
	out.print("} ");
	out.print("]");
}
%>