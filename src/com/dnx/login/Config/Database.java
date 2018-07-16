package com.dnx.login.Config;

import java.io.File;

import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Comments;
import net.cubespace.Yamler.Config.Config;
import net.md_5.bungee.api.plugin.Plugin;

@SuppressWarnings("deprecation")
public class Database extends Config {

	public Database(Plugin plugin) {
		CONFIG_FILE = new File(plugin.getDataFolder(), "Config.yml");
		CONFIG_HEADER = new String[] { "Here you can set all the things that will make the plugin work! (by dnx)" };
	}

	@Comment("Here you will set the user that you use to login in your mysql")
	public String User = "User";
	@Comment("Here you will set the password that you use to login in your mysql")
	public String Password = "Password";
	@Comment("Here you will set the database that you use in your mysql")
	public String Database = "Database";
	@Comment("Here you will set the ip that you use to login in your mysql")
	public String Host = "Host";
	@Comment("Here you will put your lobby server, it will be used as auth server")
	public String Lobby = "Lobby";
	@Comment("Here you will put the max ammount of accounts any player can have per ip")
	public int MaxAccountPerIp = 2;
	@Comments({ "Bellow you will see all messages, if you want to change just change it", "Here you will put the no permission for commands message" })
	public String NoPermissionMessage = "&cSorry but you don't have permission for this command!";
	@Comment("Here you will put the player is not registered message")
	public String PlayerNotRegistered = "&cSorry but the player is not registered on the server!";
	@Comment("Here you will put the change password success message")
	public String PasswordChangeSuccess = "&aSuccessfully changed the player password";
	@Comment("Here you will put the change password fail message")
	public String PasswordChangeFail = "&cChange password failed!";
	@Comment("Here you will put the message when a player logs in and he has a premium account")
	public String LoginPlayerPremium = "&aYou have a &9&lPREMIUM &aaccount so you dont need to login!";
	@Comment("Here you will put the message when a player logs in and he has a cracked account")
	public String LoginPlayerCracked = "&aYou have a &9&lCRACKED &aaccount so you have to register and login!";
	@Comment("Here you will put the message when a player has an invalid nickname")
	public String InvalidNickaname = "&cYou have an invalid nickname, please change it to play in our server";
	@Comment("Here you will put the message when a player nickname is already online")
	public String PlayerAlreadyOnline = "&cYour nickname is already online!";
	@Comment("Here you will put the message when a player took a long time to login or register")
	public String TookALongTimeToLogOrRegister = "&cYou took a long time to login or register!";
	@Comment("Here you will put the message when a player tries to make commands but is not logged in")
	public String PlayerNotLoggedInInteract = "&cYou can't execute commands while not logged in!";
	@Comment("Here you will put the message when a player tries to login but is not registered")
	public String LoginButIsNotReg = "&cYou need to register first!";
	@Comment("Here you will put the message when a player logs in successfully")
	public String LoginSuccess = "&aSuccessfully logged in!";
	@Comment("Here you will put the message when a player login fail!")
	public String LoginFail = "&cYour password is wrong!";
	@Comment("Here you will put the message when a player tries to register but he is already registered!")
	public String AlreadyRegistered = "&cYou are already registered";
	@Comment("Here you will put the message when a player tries to register but he has already more then the accounts registered")
	public String RegisterNumberMaxAccountsReached = "&cYou have reached the limit accounts you can register!";
	@Comment("Here you will put the message when a player registers successfully")
	public String RegisterSuccess = "&aSuccessfully registered your new account!";
	@Comment("Here you will put the message when a player is already logged in")
	public String AlreadyLoggedIn = "&cYou are already logged in";
	@Comment("Here you will put the message when a player tries to register but is already registered and is not logged in")
	public String AlreadyRegisteredButNotLoggedIn = "&cYou are already registered, use /login instead";
}
