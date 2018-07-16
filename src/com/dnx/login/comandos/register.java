package com.dnx.login.comandos;

import java.sql.SQLException;

import com.dnx.login.Main;
import com.dnx.login.api.PlayerProfile;
import com.dnx.login.mysql.MysqlManager;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class register extends Command {
	public register(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage("§cCommand only for player !");
			return;
		}
		ProxiedPlayer p = (ProxiedPlayer)sender;
		PlayerProfile pf = PlayerProfile.getPlayerProfile(p.getUniqueId());
		if(pf.isRegistered()) {
			if(!pf.isLoggedIn()) {
				p.sendMessage(Main.getAlreadyRegisteredButNotLoggedIn());
			}
			p.sendMessage(Main.getAlreadyRegistered());
			return;
		}
		if (args.length == 0) {
			sender.sendMessage("§cError: /register <password>");
			return;
		}
		
		/*StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			strBuilder.append(args[i]);
			strBuilder.append(" ");
		}*/
		String pass = args[0];
		
		String ip = p.getAddress().getAddress().getHostAddress();
		try {
			if(MysqlManager.getPlayersWithTheIP(ip).size() >= Main.getMaxAccountsPerIp()) {
				p.sendMessage(Main.getNumberMaxAccountsReached());
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		try {
			pf.registerPlayer(pass);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.sendMessage(Main.getRegisterSuccess());
	}
}