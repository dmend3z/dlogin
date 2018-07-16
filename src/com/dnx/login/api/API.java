package com.dnx.login.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {

	public static boolean nickJaExiste(String player) {
		Boolean mode = Boolean.valueOf(false);
		try {
			HttpURLConnection conn = getConnection("https://api.mojang.com/users/profiles/minecraft/" + player);
			if (conn.getResponseCode() == 200) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = reader.readLine();
				if ((line != null) && (!line.equals("null")))
					mode = Boolean.valueOf(true);
			}
		} catch (Exception ex) {
		}
		return mode;
	}

	private static HttpURLConnection getConnection(String url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setConnectTimeout(1000);
		connection.setReadTimeout(1000);

		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("User-Agent", "Premium-Checker");

		return connection;
	}

}
