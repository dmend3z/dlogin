package com.dnx.login.comandos;

import java.sql.SQLException;
import java.util.UUID;

import com.dnx.login.Main;
import com.dnx.login.api.PlayerProfile;
import com.dnx.login.mysql.MysqlManager;
import com.google.common.base.Charsets;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class changePassword extends Command {
	public changePassword(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage("§cCommand only for player !");
			return;
		}
		ProxiedPlayer p = (ProxiedPlayer) sender;
		if(!p.hasPermission("sLogin.changepass")) {
			p.sendMessage(Main.getNoPermissionMessage());
			return;
		}
		if(args.length < 1) {
			p.sendMessage("§c/changepassword <Nick> <Password>");
			return;
		}
		if(!MysqlManager.playerisRegistered(getUniqueId(args[0]).toString())) {
			p.sendMessage(Main.getPlayerNotRegisteredMessage());
			return;
		}
		PlayerProfile pf = PlayerProfile.getPlayerProfile(getUniqueId(args[0]));
		if (!pf.isRegistered()) {
			p.sendMessage(Main.getPlayerNotRegisteredMessage());
			return;
		}
		try {
			pf.setPassword(args[1]);
			p.sendMessage(Main.getChangePassSuccess());
		} catch (ClassNotFoundException | SQLException e) {
			p.sendMessage(Main.getChangePassFail());
			e.printStackTrace();
		}
		PlayerProfile.removePlayerProfile(getUniqueId(args[0]));
		
	}
	
	public UUID getUniqueId(String name) {
		return UUID.nameUUIDFromBytes(name.getBytes(Charsets.UTF_8));
	}
}