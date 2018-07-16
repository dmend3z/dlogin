package com.dnx.login.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.dnx.login.Main;

import net.md_5.bungee.api.ProxyServer;

public class MysqlManager {

public static MySQL db;
	
	public static void setupDBBungee() throws SQLException {
		db = new MySQL(Main.getHost(), "3306", Main.getDatabase(), Main.getUser(), Main.getPassword());
		try {
			db.openConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Statement statement = db.getConnection().createStatement();
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users(UUID varchar(64), Password varchar(64), Premium int, ip varchar(32));");
	}

	public void closeDB() {
		try {
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updatePassword(String uuid, String password) throws ClassNotFoundException, SQLException {
		db.updateSQL("UPDATE Users SET Password= '" + password + "' WHERE UUID= '" + uuid + "';");
	}

	public static List<String> getPlayersWithTheIP(String ip) throws ClassNotFoundException, SQLException {
		ResultSet rs = db.querySQL("SELECT * FROM Users WHERE ip= '" + ip + "'");
		List<String> coisas = new ArrayList<>();
		while (rs.next()) {
			coisas.add(rs.getString("UUID"));
		}
		return coisas;
	}
	
	public static void setPremium(String uuid,boolean option) throws ClassNotFoundException, SQLException {
		if(option == true) {
			db.updateSQL("UPDATE Users SET Premium= '1' WHERE UUID= '" + uuid + "';");
		}else if(option == false) {
			db.updateSQL("UPDATE Users SET Premium= '0' WHERE UUID= '" + uuid + "';");
		}
	}
	
	public static void setIP(String uuid,String ip) throws ClassNotFoundException, SQLException {
		db.updateSQL("UPDATE Users SET ip= '"+ip+"' WHERE UUID= '" + uuid + "';");
	}
	
	public static String getIP(String uuid) throws SQLException, ClassNotFoundException {
		String kills = null;
		ResultSet rs = db.querySQL("SELECT * FROM Users WHERE UUID= '" + uuid + "';");
		if (!rs.next()) {
			;
		}
		kills = rs.getString("ip");
		return kills;
	}
	
	public static String getPassword(String uuid) throws SQLException, ClassNotFoundException {
		String kills = null;
		ResultSet rs = db.querySQL("SELECT * FROM Users WHERE UUID= '" + uuid + "';");
		if (!rs.next()) {
			;
		}
		kills = rs.getString("Password");
		return kills;
	}
	
	public static void registerPlayer(String uuid, String password) {
		if (!playerisRegistered(uuid)) {
			try {
				db.updateSQL("INSERT INTO Users(UUID, Password, Premium, ip) VALUES ('" + uuid + "', '"+password+"', '0', '"+ProxyServer.getInstance().getPlayer(UUID.fromString(uuid)).getAddress().getAddress().getHostAddress()+"');");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isPlayerPremium(String uuid) throws ClassNotFoundException, SQLException {
		int kills = 0;
		ResultSet rs = db.querySQL("SELECT * FROM Users WHERE UUID= '" + uuid + "';");
		if (!rs.next()) {
			;
		}
		kills = rs.getInt("Premium");
		if(kills == 0) {
			return false;
		}else if(kills == 1) {
			return true;
		}
		return false;
	}
	
	
	public static boolean playerisRegistered(String uuid) {
		try {
			ResultSet rs = db.querySQL("SELECT * FROM Users WHERE UUID= '" + uuid + "'");
			if (rs.next()) {
				return rs.getString("UUID") != null;
			}
			return false;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
}

