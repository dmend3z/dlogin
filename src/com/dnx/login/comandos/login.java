package com.dnx.login.comandos;

import com.dnx.login.Main;
import com.dnx.login.api.PlayerProfile;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class login extends Command {
	public login(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage("§cCommand only for player !");
			return;
		}
		ProxiedPlayer p = (ProxiedPlayer) sender;
		PlayerProfile pf = PlayerProfile.getPlayerProfile(p.getUniqueId());
		if (!pf.isRegistered()) {
			p.sendMessage(Main.getIsNotReg());
			return;
		}
		if(pf.isLoggedIn()) {
			p.sendMessage(Main.getAlreadyloggedin());
			return;
		}
		if (args.length == 0) {
			sender.sendMessage("§cError: /login <password>");
			return;
		}

		/*StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			strBuilder.append(args[i]);
			strBuilder.append(" ");
		}*/
		String pass = args[0];

		if(pass.equals(pf.getPassword())) {
			p.sendMessage(Main.getLoginSuccess());
			pf.setLogged(true);
		}else{
			p.sendMessage(Main.getLoginFail());
		}
	}
}