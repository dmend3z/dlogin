package com.dnx.login.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import com.dnx.login.mysql.MysqlManager;

public class PlayerProfile {

	private UUID u;
	private String password,ip;
	private boolean registered, logged, premium;
	
	public static ArrayList<PlayerProfile> pf = new ArrayList<>();
	
	public PlayerProfile(UUID u) throws ClassNotFoundException, SQLException {
		this.u = u;
		if(MysqlManager.playerisRegistered(u.toString())) {
			password = MysqlManager.getPassword(u.toString());
			registered = true;
			premium = MysqlManager.isPlayerPremium(u.toString());
			ip = MysqlManager.getIP(u.toString());
		}else{
			password = null;
			registered = false;
			premium = false;
			ip = null;
		}
		pf.add(this);
	}
	
	public static PlayerProfile getPlayerProfile(UUID u) {
		for(int i=0;i<pf.size();i++) {
			if(pf.get(i).getUuid() == u) {
				return pf.get(i);
			}
		}
		return null;
	}
	
	public static void removePlayerProfile(UUID u) {
		pf.remove(getPlayerProfile(u));
	}
	
	public UUID getUuid() {
		return this.u;
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public void setIp(String ip) throws ClassNotFoundException, SQLException {
		this.ip = ip;
		MysqlManager.setIP(u.toString(), ip);
	}
	
	public boolean isPremium() {
		return this.premium;
	}
	
	public void setPassword(String password) throws ClassNotFoundException, SQLException {
		this.password = password;
		MysqlManager.updatePassword(this.u.toString(), password);
	}
	
	public void setPremium(boolean option) throws ClassNotFoundException, SQLException {
		this.premium = option;
		MysqlManager.setPremium(this.u.toString(), option);
	}
	
	public boolean isRegistered() {
		return this.registered;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean isLoggedIn() {
		return this.logged;
	}
	
	public void setLogged(boolean option) {
		this.logged = option;
	}
	
	public void registerPlayer(String password) throws ClassNotFoundException, SQLException {
		MysqlManager.registerPlayer(this.u.toString(), password);
		this.ip = MysqlManager.getIP(u.toString());
		this.registered = true;
		this.premium = false;
		setLogged(true);
	}
	
	public void registerPremiumPlayer() throws ClassNotFoundException, SQLException {
		MysqlManager.registerPlayer(this.u.toString(), this.u.toString()+"derp");
		this.registered = true;
		setPremium(true);
		setLogged(true);
		this.ip = MysqlManager.getIP(u.toString());
	}

}
