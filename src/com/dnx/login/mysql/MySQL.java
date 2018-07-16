package com.dnx.login.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL extends Database
{
	  private final String user;
	  private final String database;
	  private final String password;
	  private final String port;
	  private final String hostname;
	  
	  public MySQL(String hostname, String port, String username, String password)
	  {
	    this(hostname, port, null, username, password);
	  }
	  
	  public MySQL(String hostname, String port, String database, String username, String password)
	  {
	    this.hostname = hostname;
	    this.port = port;
	    this.database = database;
	    this.user = username;
	    this.password = password;
	  }
	  
	  public Connection openConnection()
	    throws SQLException, ClassNotFoundException
	  {
	    if (checkConnection()) {
	      return this.connection;
	    }
	    String connectionURL = "jdbc:mysql://" + this.hostname + ":" + this.port;
	    if (this.database != null) {
	      connectionURL = connectionURL + "/" + this.database;
	    }
	    Class.forName("com.mysql.jdbc.Driver");
	    this.connection = DriverManager.getConnection(connectionURL, this.user, this.password);
	    return this.connection;
	  }
	}