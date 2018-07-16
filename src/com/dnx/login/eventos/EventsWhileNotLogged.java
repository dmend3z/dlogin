package com.dnx.login.eventos;

import com.dnx.login.Main;
import com.dnx.login.api.PlayerProfile;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class EventsWhileNotLogged implements Listener {

	private final Main instance;

	public EventsWhileNotLogged() {
		instance = (Main) Main.getInstance();
		instance.getProxy().getPluginManager().registerListener(instance, this);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void quandoFalar(ChatEvent e) {
		PlayerProfile pf = PlayerProfile.getPlayerProfile(((ProxiedPlayer) e.getSender()).getUniqueId());
		if (!pf.isLoggedIn()) {
			if (e.getMessage().contains("/login") || e.getMessage().contains("register")) {

			} else {
				e.setCancelled(true);
				((ProxiedPlayer) e.getSender()).sendMessage(Main.getCantDoCommands());
			}
		}
	}

	@EventHandler
	public void conectar(ServerConnectEvent e) {
		if(PlayerProfile.getPlayerProfile(e.getPlayer().getUniqueId()) == null) return;
		if (!PlayerProfile.getPlayerProfile(e.getPlayer().getUniqueId()).isLoggedIn()) {
			if (!e.getTarget().getName().equalsIgnoreCase(Main.getLobby())) {
				e.setCancelled(true);
				e.setTarget(ProxyServer.getInstance().getServerInfo(Main.getLobby()));
			}
		}
	}

}
