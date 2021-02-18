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

String board_num = request.getParameter("board_num") == null ? "0" : request.getParameter("board_num");
String reply_num = request.getParameter("reply_num") == null ? "0" : request.getParameter("reply_num");
String isReply = request.getParameter("isReply") == null ? "0" : request.getParameter("isReply");
//String isLike = request.getParameter("isLike") == null ? "0" : request.getParameter("isLike");
String reg_id = request.getParameter("reg_id") == null ? "0" : request.getParameter("reg_id");

System.out.println("board_num: " + board_num);
System.out.println("reply_num: " + reply_num);
System.out.println("isReply: " + isReply);
//System.out.println("isLike: " + isLike);
System.out.println("reg_id: " + reg_id);

QueryBean.getConnection();

String res = "2";

try {
	res = QueryBean.isExistFavorInfo(board_num, reply_num, isReply, reg_id);
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