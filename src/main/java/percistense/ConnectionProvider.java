package percistense;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionProvider {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}	
	
	private ConnectionProvider() {}
	
	public static final Connection getConnection() throws SQLException {
		
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/mini?" +
				"user=root&password=root");

		return conn;
	}
}
