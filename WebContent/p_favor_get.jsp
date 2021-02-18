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

String board_num = request.getParameter("board_num") == null ? "0" : request.getParameter("board_num");
String reply_num = request.getParameter("reply_num") == null ? "0" : request.getParameter("reply_num");
String isReply = request.getParameter("isReply") == null ? "" : request.getParameter("isReply");
String reg_id = request.getParameter("reg_id") == null ? "" : request.getParameter("reg_id");
QueryBean.getConnection();

ArrayList resArr = new ArrayList();

try {
	resArr = QueryBean.getFavorInfos(board_num, reply_num, isReply, reg_id);
} catch (Exception e) {
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
	out.print("\"BOARD_NUM\": \"" + (String) resArr.get(0) + "\", ");
	out.print("\"REPLY_NUM\": \"" + (String) resArr.get(1) + "\", ");
	out.print("\"ISREPLY\": \"" + (String) resArr.get(2) + "\", ");
	out.print("\"ISLIKE\": \"" + (String) resArr.get(3) + "\", ");
	out.print("\"REG_ID\": \"" + (String) resArr.get(4) + "\" ");
	out.print("} ");

	// 	for (int i = 5; i < resArr.size(); i += 5) {
	// 		out.print(",");
	// 		out.print("{ ");
	// 		out.print("		\"BOARD_NUM\": \"" + (String) resArr.get(i) + "\", ");
	// 		out.print("		\"reply_num\": \"" + (String) resArr.get(i + 1) + "\", ");
	// 		out.print("		\"ISREPLY\": \"" + (String) resArr.get(i + 2) + "\", ");
	// 		out.print("		\"ISLIKE\": \"" + (String) resArr.get(i + 3) + "\", ");
	// 		out.print("		\"REG_ID\": \"" + (String) resArr.get(i + 4) + "\" ");
	// 		out.print("} ");
	// 	}
	out.println("]");
	out.println("}");
}
%>