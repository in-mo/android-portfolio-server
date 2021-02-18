package AppDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryBean {

	Connection conn;
	Statement stmt;
	ResultSet rs;

	public QueryBean() {
		conn = null;
		stmt = null;
		rs = null;
	}

	public void getConnection() {
		try {
			conn = DBConnection.getConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 특정 게시물 가져오기
	public ArrayList getContent(String num) throws Exception {
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" 	* ");
		sb.append(" FROM ");
		sb.append(" 	content_info ");
		sb.append(" where ");
		sb.append(" 	num = '" + num + "'");

		rs = stmt.executeQuery(sb.toString());

		ArrayList res = new ArrayList();
		while (rs.next()) {

			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			res.add(rs.getString(4));
			res.add(rs.getString(5));
			res.add(rs.getString(6));
			res.add(rs.getString(7));
			res.add(rs.getString(8));
		}
		System.out.println(sb.toString());

		return res;
	}

	// 댓글 목록 가져오기
	public ArrayList getReplysList(String num, String reply_num) throws Exception {
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" 	* ");
		sb.append(" FROM ");
		sb.append(" 	reply_info ");
		sb.append(" where ");
		sb.append(" 	reg_gnum = '" + num + "'");
		if (!reply_num.equals("0")) {
			sb.append("	and num = '" + reply_num + "'");
		}
		sb.append(" ORDER BY ");
		sb.append(" 	reg_date desc");

		rs = stmt.executeQuery(sb.toString());

		ArrayList res = new ArrayList();
		while (rs.next()) {

			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			res.add(rs.getString(4));
			res.add(rs.getString(5));
			res.add(rs.getString(6));
			res.add(rs.getString(7));
		}
		System.out.println(sb.toString());

		return res;
	}

	// 게시물 목록 가져오기
	public ArrayList getContentsList(String search_num, String search_contents) throws Exception {

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT ");
		sb.append(" 	* ");
		sb.append(" FROM ");
		sb.append(" 	content_info ");
		if (!search_contents.equals("")) {
			if (search_num.equals("0")) {
				sb.append(" where title = '" + search_contents + "'");
			} else if (search_num.equals("1")) {
				sb.append(" where num = '" + search_contents + "'");
			} else if (search_num.equals("2")) {
				sb.append(" where reg_id = '" + search_contents + "'");
			}
		}

		sb.append(" ORDER BY ");
		sb.append(" 	reg_date desc");

		rs = stmt.executeQuery(sb.toString());

		ArrayList res = new ArrayList();
		while (rs.next()) {

			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			res.add(rs.getString(4));
			res.add(rs.getString(5));
			res.add(rs.getString(6));
			res.add(rs.getString(7));
			res.add(rs.getString(8));
		}
		System.out.println(sb.toString());

		return res;
	}

	// 게시물 좋아요 싫어요 값 설정
	public int setContentFavorCount(String num, String favor_count, String favor_kinds) {
		int result = 0;

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;

		sb.append(" update content_info ");
		if (favor_kinds.equals("like"))
			sb.append(" 	SET like_count = like_count + '" + favor_count + "' ");
		else if (favor_kinds.equals("hate"))
			sb.append(" 	SET hate_count = hate_count + '" + favor_count + "' ");
		sb.append(" where num = '" + num + "'");

		System.out.println(sb.toString());

		try {
			pstmt = conn.prepareStatement(sb.toString());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 댓글 좋아요 싫어요 값 설정
	public int setReplyFavorCount(String num, String favor_count, String favor_kinds) {
		int result = 0;

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;

		sb.append(" update reply_info ");
		if (favor_kinds.equals("like"))
			sb.append(" 	SET like_count = like_count + '" + favor_count + "' ");
		else if (favor_kinds.equals("hate"))
			sb.append(" 	SET hate_count = hate_count + '" + favor_count + "' ");
		sb.append(" where num = '" + num + "'");

		System.out.println(sb.toString());

		try {
			pstmt = conn.prepareStatement(sb.toString());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 회원 관리
	public ArrayList managementUserInfo(String id, String type, String password, String name, String email)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;
		ArrayList res = new ArrayList();

		// 회원 정보 가져오기
		if (type.equals("get")) {
			sb.append(" SELECT ");
			sb.append(" 	* ");
			sb.append(" FROM ");
			sb.append(" 	board_member ");
			sb.append(" WHERE ");
			sb.append(" 	id = '" + id + "' ");

			rs = stmt.executeQuery(sb.toString());

			while (rs.next()) {

				res.add(rs.getString(1));
				res.add(rs.getString(2));
				res.add(rs.getString(3));
				res.add(rs.getString(4));
				res.add(rs.getString(5));
			}
			System.out.println(sb.toString());
		}

		// 회원 정보 수정하기
		else if (type.equals("update")) {
			int result = 0;

			sb.append(" update ");
			sb.append(" 	board_member ");
			sb.append(" set password = '" + password + "', ");
			sb.append(" 	name = '" + name + "', ");
			sb.append(" 	email = '" + email + "' ");
			sb.append(" WHERE ");
			sb.append(" 	id = '" + id + "' ");

			pstmt = conn.prepareStatement(sb.toString());
			result = pstmt.executeUpdate();
			System.out.println(sb.toString());

			res.add(result);
		}

		// 회원 탈퇴
		else if (type.equals("delete")) {
			int result = 0;

			sb.append(" delete ");
			sb.append(" 	from board_member ");
			sb.append(" WHERE ");
			sb.append(" 	id = '" + id + "' ");

			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sb.toString());
			result = pstmt.executeUpdate();
			System.out.println(sb.toString());

			pstmt.close();
			sb.setLength(0);

			// 좋/싫 정보 삭제
			sb.append(" delete ");
			sb.append(" 	from favor_info ");
			sb.append(" WHERE ");
			sb.append(" 	reg_id = '" + id + "' ");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.executeUpdate();
			System.out.println(sb.toString());
			
			pstmt.close();
			sb.setLength(0);
			
			// 게시글 삭제
			sb.append(" delete ");
			sb.append(" 	from content_info ");
			sb.append(" WHERE ");
			sb.append(" 	reg_id = '" + id + "' ");
		
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.executeUpdate();
			System.out.println(sb.toString());
			
			pstmt.close();
			sb.setLength(0);
			
			// 댓글 삭제
			sb.append(" delete ");
			sb.append(" 	from reply_info ");
			sb.append(" WHERE ");
			sb.append(" 	reg_id = '" + id + "' ");
		
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.executeUpdate();
			System.out.println(sb.toString());
			
			conn.commit();
			conn.setAutoCommit(true);
			
			res.add(result);
		}

		return res;
	}

	// 아이디 찾기
	public String searchId(String name, String email) throws Exception {
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" 	id ");
		sb.append(" FROM ");
		sb.append(" 	board_member ");
		sb.append(" WHERE ");
		sb.append(" 	name = '" + name + "' ");
		sb.append(" and ");
		sb.append(" 	email = '" + email + "' ");

		rs = stmt.executeQuery(sb.toString());

		String res = new String();
		while (rs.next()) {

			res = rs.getString(1);
		}
		System.out.println(sb.toString());

		return res;
	}

	// 비밀번호 찾기
	public String searchPassword(String id, String name, String email) throws Exception {
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" 	password ");
		sb.append(" FROM ");
		sb.append(" 	board_member ");
		sb.append(" WHERE ");
		sb.append(" 	id = '" + id + "' ");
		sb.append(" and ");
		sb.append(" 	email = '" + email + "' ");
		sb.append(" and ");
		sb.append(" 	name = '" + name + "' ");

		rs = stmt.executeQuery(sb.toString());

		String res = new String();
		while (rs.next()) {
			res = rs.getString(1);
		}
		System.out.println(sb.toString());

		return res;
	}

	// 특정 게시물 삭제
	public int contentDeleteByNum(String num) throws Exception {
		int result = 0;

		PreparedStatement pstmt = null;

		StringBuffer sb = new StringBuffer();
		try {

			sb.append(" DELETE ");
			sb.append(" 	FROM ");
			sb.append(" 		content_info ");
			sb.append(" WHERE ");
			sb.append(" 	num = ? ");

			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.clearParameters();
			pstmt.setString(1, num);

			result = pstmt.executeUpdate();

			pstmt.close();
			sb.setLength(0);

			sb.append(" DELETE ");
			sb.append(" 	FROM ");
			sb.append(" 		reply_info ");
			sb.append(" WHERE ");
			sb.append(" 	reg_gnum = ? ");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.clearParameters();
			pstmt.setString(1, num);

			pstmt.executeUpdate();

			conn.commit();

			conn.setAutoCommit(true);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 댓글 추가
	public int addReplyInfo(String contents, int like_count, int hate_count, String reg_id, int reg_gnum) {
		int result = 0;

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;

		sb.append(" insert into ");
		sb.append(" 	reply_info (contents, like_count, hate_count, reg_id, reg_gnum, reg_date)");
		sb.append(" VALUES ");
		sb.append(" 	(?, ?, ?, ?, ?, now())");

		System.out.println(sb.toString());

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.clearParameters();
			pstmt.setString(1, contents);
			pstmt.setInt(2, like_count);
			pstmt.setInt(3, hate_count);
			pstmt.setString(4, reg_id);
			pstmt.setInt(5, reg_gnum);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 댓글 수정
	public int updateReplyInfo(String num, String contents) {
		int result = 0;

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;

		sb.append(" update reply_info ");
		sb.append(" 	set contents = ? ");
		sb.append(" where num = ? ");

		System.out.println(sb.toString());

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.clearParameters();
			pstmt.setString(1, contents);
			pstmt.setString(2, num);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 댓글 삭제
	public int replyDeleteByNum(String num) throws Exception {
		int result = 0;

		PreparedStatement pstmt = null;

		StringBuffer sb = new StringBuffer();

		sb.append(" DELETE ");
		sb.append(" 	FROM ");
		sb.append(" 		reply_info ");
		sb.append(" WHERE ");
		sb.append(" 	num = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.clearParameters();
			pstmt.setString(1, num);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 아이디 중복 확인
	public int checkId(String id) {
		int count = 0;

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;

		sb.append(" select count(*) ");
		sb.append(" 	from board_member ");
		sb.append(" where id = '" + id + "'");

		System.out.println(sb.toString());

		try {
			pstmt = conn.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	// 아이디 비밀번호 확인
	public int compareIdPwd(String id, String password) {
		int result = 3;

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;

		sb.append(" select password ");
		sb.append(" from	board_member ");
		sb.append(" where id = '" + id + "'");

		System.out.println(sb.toString());

		try {
			pstmt = conn.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (password.equals(rs.getString("password"))) {
					result = 1; // 다맞음
				} else
					result = 0; // 비번틀림
			} else {
				result = -1; // 모두 틀림
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 회원 가입
	public int addUserInfo(String id, String password, String name, String email) {
		int result = 0;

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;

		sb.append(" insert into ");
		sb.append(" 	board_member (id, password, name, email, reg_date)");
		sb.append(" VALUES ");
		sb.append(" 	('" + id + "', '" + password + "', '" + name + "', '" + email + "', now())");

		System.out.println(sb.toString());

		try {
			pstmt = conn.prepareStatement(sb.toString());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 게시물 추가
	public int addContentInfo(String title, String contents, String reg_id) {
		int result = 0;

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;

		sb.append(" insert into ");
		sb.append(" 	content_info (title, contents, readcount, like_count, hate_count, reg_id, reg_date)");
		sb.append(" VALUES ");
		sb.append(" 	('" + title + "', '" + contents + "', '" + 0 + "', '" + 0 + "', '" + 0 + "', '" + reg_id
				+ "', now())");

		System.out.println(sb.toString());

		try {
			pstmt = conn.prepareStatement(sb.toString());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 게시물 수정
	public int updateContent(String num, String title, String contents) {
		int result = 0;

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;

		sb.append(" update content_info ");
		sb.append(" 	SET title = '" + title + "', contents = '" + contents + "' ");
		sb.append(" where num = '" + num + "'");

		System.out.println(sb.toString());

		try {
			pstmt = conn.prepareStatement(sb.toString());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 게시물 조회수
	public int updateContentRCInfo(String num) {
		int result = 0;

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;

		sb.append(" update content_info ");
		sb.append(" 	SET readcount = readcount + 1 ");
		sb.append(" where num = '" + num + "'");

		System.out.println(sb.toString());

		try {
			pstmt = conn.prepareStatement(sb.toString());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 좋/싫 게시물or댓글 정보 존재 여부
	public String isExistFavorInfo(String board_num, String reply_num, String isReply, String reg_id) {
		String res = "2";

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;

		sb.append(" select count(*) ");
		sb.append(" from	favor_info ");
		if (isReply.equals("1") && reply_num.equals("0")) {
			sb.append(" where board_num = '" + board_num + "' ");
			sb.append(" 	and	isReply = '" + isReply + "' ");
			sb.append(" 	and	reg_id = '" + reg_id + "' ");
		} else {
			sb.append(" where board_num = '" + board_num + "' ");
			sb.append(" 	and	reply_num = '" + reply_num + "' ");
			sb.append(" 	and	reg_id = '" + reg_id + "' ");
		}
//		sb.append(" 	and	reply_num = '" + reply_num + "' ");
//		sb.append(" 	and	isReply = '" + isReply + "' ");
//		sb.append(" 	and	isLike = '" + isLike + "' ");
//		sb.append(" 	and	reg_id = '" + reg_id + "' ");

		System.out.println(sb.toString());

		try {
			pstmt = conn.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				res = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	// 좋/싫 게시물 or 댓글 정보 가져오기
	public ArrayList getFavorInfos(String board_num, String reply_num, String isReply, String reg_id) {
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" 	* ");
		sb.append(" FROM ");
		sb.append(" 	favor_info ");
		sb.append(" where ");
		sb.append(" 	board_num = '" + board_num + "' ");
		sb.append(" 	and reg_id = '" + reg_id + "' ");
		if (isReply.equals("0")) {
			sb.append(" 	and reply_num = '" + reply_num + "' ");
		}

		ArrayList res = new ArrayList();
		try {
			rs = stmt.executeQuery(sb.toString());

			while (rs.next()) {
				res.add(rs.getString(1));
				res.add(rs.getString(2));
				res.add(rs.getString(3));
				res.add(rs.getString(4));
				res.add(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());

		return res;
	}

	// 좋/싫 게시물or댓글 정보 추가
	public int addFavorInfo(String board_num, String reply_num, String isReply, String isLike, String reg_id) {
		int result = 0;

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;

		sb.append(" insert into favor_info ");
		sb.append(" (board_num, reply_num, isReply, isLike, reg_id)");
		sb.append(" values ('" + board_num + "', '" + reply_num + "', ");
		sb.append("		 '" + isReply + "', '" + isLike + "', " + "'" + reg_id + "')");

		System.out.println(sb.toString());

		try {
			pstmt = conn.prepareStatement(sb.toString());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateFavorInfo(String board_num, String reply_num, String isReply, String isLike, String reg_id) {
		int result = 0;

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;

		sb.append(" update favor_info ");
		sb.append(" 	SET isLike = '" + isLike + "' ");
		sb.append(" where board_num = '" + board_num + "' ");
		sb.append(" 	and reply_num = '" + reply_num + "' ");
		sb.append("		and reg_id = '" + reg_id + "' ");

		System.out.println(sb.toString());

		try {
			pstmt = conn.prepareStatement(sb.toString());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int deleteFavorInfo(String board_num, String reply_num, String isReply, String isLike, String reg_id) {
		int result = 0;

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;

		int isReplyNum = Integer.parseInt(isReply == null ? "0" : isReply);

		sb.append(" DELETE ");
		sb.append(" 	FROM favor_info ");
		sb.append(" where board_num = '" + board_num + "' ");
		if (isReplyNum == 0)
			sb.append(" and reply_num = '" + reply_num + "' ");

		System.out.println(sb.toString());

		try {
			pstmt = conn.prepareStatement(sb.toString());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
