package com.dnx.login.eventos;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import com.dnx.login.Main;
import com.dnx.login.api.API;
import com.dnx.login.api.PlayerProfile;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerConnectionListener implements Listener {

	private final Main instance;

	public PlayerConnectionListener() {
		instance = (Main) Main.getInstance();
		instance.getProxy().getPluginManager().registerListener(instance, this);
	}

	@EventHandler
	public void playerDisconnect(PlayerDisconnectEvent e) {
		PlayerProfile.removePlayerProfile(e.getPlayer().getUniqueId());
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void postLoginEvent(final PostLoginEvent e) throws ClassNotFoundException, SQLException {
		final ProxiedPlayer player = e.getPlayer();
		PendingConnection connection = player.getPendingConnection();
		final PlayerProfile pf = new PlayerProfile(player.getUniqueId());
		if(connection.isOnlineMode()) {
			if(!pf.isRegistered()) {
				pf.registerPremiumPlayer();
			}else{
				pf.setLogged(true);
			}
			player.sendMessage(Main.getPremiumLogin());
		}else{
			if(!pf.isRegistered()) {
				player.sendMessage(Main.getCrackedLogin());
				player.sendMessage("§c/Register <password>");
				ProxyServer.getInstance().getScheduler().schedule(Main.getInstance(), new Runnable() {
					public void run() {
						if(!pf.isLoggedIn()) {
							player.disconnect(Main.getLongTimeToLogin());
						}
					}
				}, 20, TimeUnit.SECONDS);
			}else{
				player.sendMessage(Main.getCrackedLogin());
				player.sendMessage("§c/Login <password>");
				ProxyServer.getInstance().getScheduler().schedule(Main.getInstance(), new Runnable() {
					public void run() {
						if(!pf.isLoggedIn()) {
							player.disconnect(Main.getLongTimeToLogin());
						}
					}
				}, 20, TimeUnit.SECONDS);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void quandoLogar(PreLoginEvent e) throws ClassNotFoundException, SQLException {
		PendingConnection connection = e.getConnection();
		String playername = connection.getName();
		if (!playername.matches("[a-zA-Z0-9_]{3,16}")) {
			e.setCancelled(true);
			e.setCancelReason(Main.getInvalidName());
			return;
		}
		ProxiedPlayer localProxiedPlayer = ProxyServer.getInstance().getPlayer(playername);
		if (localProxiedPlayer != null) {
			e.setCancelled(true);
			e.setCancelReason(Main.getAlreadyOnline());
			return;
		}
		connection.setOnlineMode(API.nickJaExiste(playername));
	}

}
