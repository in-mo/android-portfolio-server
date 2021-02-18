package AppDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() throws Exception {
		System.out.println("DB연결시도");
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jspdb?useUnicode=true&characterEncoding=utf8"
				+ "&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul",
				"myid", "mypwd");
	}

	public static Connection getConnection(String ip, int port, String db, String user, String pw) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jspdb?useUnicode=true&characterEncoding=utf8"
				+ "&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul"
						+ ip + ":" + port + ":" + db,
				user, pw);
	}
}
