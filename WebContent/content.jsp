<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="QueryBean" scope="page" class="AppDB.QueryBean" />
<jsp:setProperty name="QueryBean" property="*" />
<%
	response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

request.setCharacterEncoding("UTF-8");
String num = request.getParameter("num");

QueryBean.getConnection();

ArrayList resArr = new ArrayList();

try {
	resArr = QueryBean.getContent(num);
} catch (SQLException e) {
	System.out.print(e.toString());
} finally {
	QueryBean.closeConnection();
}

out.println("{");
out.println("\"datas\":[");

if (resArr.size() == 0) {

	out.println("]");
	out.println("}");
} else {

	out.print("{ ");
	out.print("\"NUM\": \"" + (String) resArr.get(0) + "\", ");
	out.print("\"TITLE\": \"" + (String) resArr.get(1) + "\", ");
	out.print("\"CONTENTS\": \"" + (String) resArr.get(2) + "\", ");
	out.print("\"READCOUNT\": \"" + (String) resArr.get(3) + "\", ");
	out.print("\"LIKECOUNT\": \"" + (String) resArr.get(4) + "\", ");
	out.print("\"HATECOUNT\": \"" + (String) resArr.get(5) + "\", ");
	out.print("\"REG_ID\": \"" + (String) resArr.get(6) + "\", ");
	out.print("\"REG_DATE\": \"" + (String) resArr.get(7) + "\" ");
	out.print("} ");

	out.println("]");
	out.println("}");
}
%>