package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class Database {
    private Connection conn = null;
    private Statement stat = null;
    
	public Database() {
		try {
			Class.forName( "com.mysql.cj.jdbc.Driver" );
			String url = "jdbc:mysql://localhost:3306/chat_system";
			String user = "chat_user";
			String pwd = "pwd";
			this.conn = DriverManager.getConnection(url, user, pwd);
			this.stat = this.conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createTables() {
			this.createUserTable();
			this.createLocalUserTable();
			this.createConversationTable();
	}
		
	public void createUserTable() {
		String sql = "CREATE TABLE User ("
				+ "id VARCHAR(36) PRIMARY KEY)";
		try {
			this.stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertUser(int id) {
		String sql = "INSERT INTO User "
				+ "VALUES("+ id +")";
		try {
			this.stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createLocalUserTable() {
		String sql = "CREATE TABLE LocalUser ("
				+ "id VARCHAR(36) PRIMARY KEY REFERENCES User(id)"
				+ ")";
		try {
			this.stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertLocalUser(UUID id) {
		String sql1 = "INSERT INTO User "
				+ "VALUES('"+ id.toString() + "')";
		String sql2 = "INSERT INTO LocalUser "
				+ "VALUES('"+ id.toString() + "')";
		try {
			this.stat.executeUpdate(sql1);
			this.stat.executeUpdate(sql2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createConversationTable() {
		String sql = "CREATE TABLE Conversation ("
				+ "numHistory INTEGER AUTO_INCREMENT PRIMARY KEY,"
				+ "id VARCHAR(36) REFERENCES User(id)"
				+ ")";
		try {
			this.stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createHistoryTable(int i) {
		String sql = "CREATE TABLE History"+i+" ("
				+ "id INTEGER AUTO_INCREMENT PRIMARY KEY,"
				+ "sender VARCHAR(36) NOT NULL REFERENCES User(id), "
				+ "receiver VARCHAR(36) NOT NULL REFERENCES User(id), "
				+ "message TEXT, "
				+ "date TIMESTAMP "
				+ ")";
		try {
			this.stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public UUID getLocalUserId() throws SQLException {
		String sql = "SELECT id FROM LocalUser";
		ResultSet rs = this.stat.executeQuery(sql);
		rs.next();
		return UUID.fromString(rs.getString(1));
	}
}
