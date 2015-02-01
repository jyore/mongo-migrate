package com.jyore.mongo.migrate.config;

import com.jyore.mongo.migrate.exception.MongoMigrateConnectionException;
import com.jyore.mongo.migrate.util.MongoConnectionHelper;
import com.mongodb.DB;


public class ConnectionConfig {

	private final String host;
	private final String port;
	private String dbName = "test";
	private String username = null;
	private String password = null;
		
	public ConnectionConfig(String host, String port) {
		this.host = host;
		this.port = port;
	}
		
	public ConnectionConfig dbName(String dbName) {
		this.dbName = dbName;
		return this;
	}
		
	public ConnectionConfig username(String username) {
		this.username = username;
		return this;
	}
		
	public ConnectionConfig password(String password) {
		this.password = password;
		return this;
	}
	
	public DB getConnection() throws MongoMigrateConnectionException {
		if(username == null || password == null) {
			return MongoConnectionHelper.getConnection(host, port, dbName);
		} else {
			return MongoConnectionHelper.getConnection(host, port, username, password, dbName);
		}
	}
}