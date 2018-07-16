package com.dnx.login;

import java.sql.SQLException;

import com.dnx.login.Config.Database;
import com.dnx.login.comandos.changePassword;
import com.dnx.login.comandos.login;
import com.dnx.login.comandos.register;
import com.dnx.login.eventos.EventsWhileNotLogged;
import com.dnx.login.eventos.PlayerConnectionListener;
import com.dnx.login.mysql.MysqlManager;

import net.cubespace.Yamler.Config.InvalidConfigurationException;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

	private static Plugin instance;
	public static Database dbConfig = null;
	
	@SuppressWarnings("deprecation")
	public void onEnable() {
		instance = this;
		new EventsWhileNotLogged();
		new PlayerConnectionListener();
		getProxy().getPluginManager().registerCommand(this, new register("register"));
		getProxy().getPluginManager().registerCommand(this, new login("login"));
		getProxy().getPluginManager().registerCommand(this, new changePassword("changepassword"));
		dbConfig = new Database(this);
		try {
			dbConfig.init();
			getProxy().getConsole().sendMessage("§aLoaded config!");
		} catch (InvalidConfigurationException e) {
			getProxy().getConsole().sendMessage("§cSomething is wrong with your config.");
			e.printStackTrace();
		}
		
		try {
			MysqlManager.setupDBBungee();
			getProxy().getConsole().sendMessage("§aConnected to mysql!");
		} catch (SQLException e) {
			getProxy().getConsole().sendMessage("§cSomething of mysql is wrong!");
			e.printStackTrace();
		}
	}
		
	public static Plugin getInstance() {
		return instance;
	}
	
	public static String getHost() {
		return dbConfig.Host;
	}
	
	public static String getDatabase() {
		return dbConfig.Database;
	}
	
	public static String getUser() {
		return dbConfig.User;
	}
	
	public static String getPassword() {
		return dbConfig.Password;
	}
	
	public static String getLobby() {
		return dbConfig.Lobby;
	}
	
	public static int getMaxAccountsPerIp() {
		return dbConfig.MaxAccountPerIp;
	}
	
	public static String getNoPermissionMessage() {
		String message = dbConfig.NoPermissionMessage;
		message = message.replaceAll("&", "§");
		return message;
	}
	
	public static String getPlayerNotRegisteredMessage() {
		String message = dbConfig.PlayerNotRegistered;
		message = message.replaceAll("&", "§");
		return message;
	}
	public static String getChangePassSuccess() {
		String message = dbConfig.PasswordChangeSuccess;
		message = message.replaceAll("&", "§");
		return message;
	}
	
	public static String getChangePassFail() {
		String message = dbConfig.PasswordChangeFail;
		message = message.replaceAll("&", "§");
		return message;
	}
	
	public static String getPremiumLogin() {
		String message = dbConfig.LoginPlayerPremium;
		message = message.replaceAll("&", "§");
		return message;
	}
	
	public static String getCrackedLogin() {
		String message = dbConfig.LoginPlayerCracked;
		message = message.replaceAll("&", "§");
		return message;
	}
	
	public static String getInvalidName() {
		String message = dbConfig.InvalidNickaname;
		message = message.replaceAll("&", "§");
		return message;
	}
	
	public static String getAlreadyOnline() {
		String message = dbConfig.PlayerAlreadyOnline;
		message = message.replaceAll("&", "§");
		return message;
	}
	
	public static String getLongTimeToLogin() {
		String message = dbConfig.TookALongTimeToLogOrRegister;
		message = message.replaceAll("&", "§");
		return message;
	}
	
	public static String getCantDoCommands() {
		String message = dbConfig.TookALongTimeToLogOrRegister;
		message = message.replaceAll("&", "§");
		return message;
	}
	
	public static String getIsNotReg() {
		String message = dbConfig.LoginButIsNotReg;
		message = message.replaceAll("&", "§");
		return message;
	}
	
	public static String getLoginSuccess() {
		String message = dbConfig.LoginSuccess;
		message = message.replaceAll("&", "§");
		return message;
	}
	
	public static String getLoginFail() {
		String message = dbConfig.LoginFail;
		message = message.replaceAll("&", "§");
		return message;
	}
	
	public static String getAlreadyRegistered() {
		String message = dbConfig.AlreadyRegistered;
		message = message.replaceAll("&", "§");
		return message;
	}
	
	public static String getNumberMaxAccountsReached() {
		String message = dbConfig.RegisterNumberMaxAccountsReached;
		message = message.replaceAll("&", "§");
		return message;
	}
	public static String getRegisterSuccess() {
		String message = dbConfig.RegisterSuccess;
		message = message.replaceAll("&", "§");
		return message;
	}
	
	public static String getAlreadyloggedin() {
		String message = dbConfig.AlreadyLoggedIn;
		message = message.replaceAll("&", "§");
		return message;
	}
	
	public static String getAlreadyRegisteredButNotLoggedIn() {
		String message = dbConfig.AlreadyRegisteredButNotLoggedIn;
		message = message.replaceAll("&", "§");
		return message;
	}

}
