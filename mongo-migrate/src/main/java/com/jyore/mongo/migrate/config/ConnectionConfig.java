package com.jyore.mongo.migrate.config;

import com.jyore.mongo.migrate.exception.MongoMigrateConfigurationException;
import com.jyore.mongo.migrate.exception.MongoMigrateConnectionException;
import com.jyore.mongo.migrate.util.MongoConnectionHelper;
import com.mongodb.DB;

/**
 * DB Connection Configuration Builder
 * 
 * @author jyore
 * @version 1.0
 */
public class ConnectionConfig {

	protected final String host;
	protected final String port;
	protected String dbDefault = "test";
	protected String username = null;
	protected String password = null;
		
	/**
	 * Constructor to define minimal required data
	 * 
	 * @param host The host of the mongo server
	 * @param port The port of the mongo server
	 */
	public ConnectionConfig(String host, String port) {
		this.host = host;
		this.port = port;
	}
		
	/**
	 * Update the default db to connect to
	 * 
	 * If this is never set through this method, 'test' will be used
	 * 
	 * @param dbDefault The name the default db should be set to
	 * @return Reference to the {@link ConnectionConfig} caller
	 */
	public ConnectionConfig dbDefault(String dbDefault) {
		this.dbDefault = dbDefault;
		return this;
	}
	
	/**
	 * Update the username to connect with
	 * 
	 * If this is never set through this method, null will be used (no auth)
	 * 
	 * @param username The username to use
	 * @return Reference to the {@link ConnectionConfig} caller
	 */
	public ConnectionConfig username(String username) {
		this.username = username;
		return this;
	}
		
	/**
	 * Update the password to connect with
	 * 
	 * If this is never set through this method, null will be used (no auth)
	 * 
	 * @param password The password to use
	 * @return Reference to the {@link ConnectionConfig} caller
	 */
	public ConnectionConfig password(String password) {
		this.password = password;
		return this;
	}
	
	/**
	 * Build a connection to the database using the configuration
	 * 
	 * @return The {@link DB} Connection object
	 * @throws MongoMigrateConfigurationException when a connection failure occurs
	 */
	public DB getConnection() throws MongoMigrateConfigurationException {
		try {
			if(username == null || password == null) {
				return MongoConnectionHelper.getConnection(host, port, dbDefault);
			} else {
				return MongoConnectionHelper.getConnection(host, port, username, password, dbDefault);
			}
		} catch(MongoMigrateConnectionException e) {
			throw new MongoMigrateConfigurationException("Connection configuration is not valid",e);
		}
	}
}