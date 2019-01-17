package main.database;

import main.Message;
import main.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
	
	/* Create the tables of the database */
	public void createTables() {
			this.createUserTable();
			this.createLocalUserTable();
			this.createHistoryTable();
			this.createMessageTable();
	}
		
	/* Create the user table */
	private void createUserTable() {
		String sql = "CREATE TABLE User ("
				+ "id VARCHAR(36) PRIMARY KEY)";
		try {
			this.stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Insert a user in the user table */
	public void insertUser(UUID id) {
		String sql = "INSERT INTO User(id) "
				+ "VALUES('"+ id +"')";
		try {
			this.stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Create the local user table */
	private void createLocalUserTable() {
		String sql = "CREATE TABLE LocalUser ("
				+ "id VARCHAR(36) PRIMARY KEY REFERENCES User(id)"
				+ ")";
		try {
			this.stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Insert the local user in the local user table */
	public void insertLocalUser(UUID id) {
		String sql1 = "INSERT INTO User(id) "
				+ "VALUES('"+ id + "')";
		String sql2 = "INSERT INTO LocalUser(id) "
				+ "VALUES('"+ id + "')";
		try {
			this.stat.executeUpdate(sql1);
			this.stat.executeUpdate(sql2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Create the history table */
	private void createHistoryTable() {
		String sql = "CREATE TABLE History ("
				+ "id INTEGER AUTO_INCREMENT PRIMARY KEY,"
				+ "user VARCHAR(36) REFERENCES User(id)"
				+ ")";
		try {
			this.stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Insert a new history
	 * Return the generated key */
	public int insertHistory(UUID id) {
		ResultSet rs;
		int i = 0;
		String sql = "INSERT INTO History(user) "
				+ "VALUES('"+ id +"')";
		try {
			this.stat.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			rs = stat.getGeneratedKeys();
			rs.next();
			i = rs.getInt(1);
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	/* Create the message table */
	private void createMessageTable() {
		String sql = "CREATE TABLE Message ("
				+ "id INTEGER AUTO_INCREMENT PRIMARY KEY,"
				+ "history INTEGER NOT NULL REFERENCES History(id),"
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
	
	/* Get the local user id */
	public UUID getLocalUserId() throws SQLException {
		String sql = "SELECT id FROM LocalUser";
		ResultSet rs = this.stat.executeQuery(sql);
		rs.next();
		UUID id = UUID.fromString(rs.getString("id"));
		if (rs != null) {
			rs.close();
		}
		return id;
	}
	
	/* Get the history id associated to this user id */
	private int getHistoryId(UUID userId) {
		String sql = "SELECT id FROM History WHERE user='"+ userId +"'";
		ResultSet rs;
		int histId = 0;
		try {
			rs = this.stat.executeQuery(sql);
			/* If no conversation exists with this user yet */
			if (rs.next() == false) {
				System.out.println("This user is not in the database yet, a new history is created");
				/* Add this new user */
				this.insertUser(userId);
				/* Create a new history and get the generated history id*/
				histId = this.insertHistory(userId);
			}
			else {
				histId = rs.getInt("id");
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return histId;
	}
	
	/* Get the history associated to a remote user in the database */
	public List<Message> getHistory(User local, User dist) {
		List<Message> history = new ArrayList<>();
		int histId = getHistoryId(dist.getId());
		String sql = "SELECT * FROM Message WHERE history='"+ histId +"'";
		ResultSet rs;
		try {
			rs = this.stat.executeQuery(sql);
			while(rs.next()) {
				String srcUserId = rs.getString("sender");
				Message msg;
				/* If the source user is the sender */
				if (srcUserId.equals(local.getId().toString())) {
					msg = new Message(rs.getString("message"), local, dist, rs.getTimestamp("date"));
				}
				/* If the distant user is the sender */
				else {
					msg = new Message(rs.getString("message"), dist, local, rs.getTimestamp("date"));
				}
				history.add(msg);
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return history;
	}
	
	/* Insert a message from/to a distant user */
	public void insertMessage(User user, Message msg) {
		int histId = this.getHistoryId(user.getId());
		String sql = "INSERT INTO Message(history,sender,receiver,message,date) "
				+ "VALUES('"+histId+"','"+msg.getSrcUser().getId()+"','"+msg.getDestUser().getId()+"','"+msg.getMsg()+"','"+msg.getHorodate()+"')";
		try {
			this.stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Close the connection to the database */
	public void closeDatabase() {
		try {
			if (this.stat != null) {
				this.stat.close();
			}
			if (this.conn != null) {
				this.conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
