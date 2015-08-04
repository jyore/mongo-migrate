package com.jyore.mongo.migrate.config;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyore.mongo.migrate.exception.MongoMigrateConfigurationException;
import com.jyore.mongo.migrate.exception.MongoMigrateConnectionException;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * DB Connection Configuration Builder
 * 
 * @author jyore
 * @version 1.0
 */
public class ConnectionConfig {
	
	private static final Logger log = LoggerFactory.getLogger(ConnectionConfig.class); 

	protected final String connectionString;
	protected String dbName = "test";
		
	/**
	 * Constructor to define minimal required data
	 * 
	 * @param host The host of the mongo server
	 * @param port The port of the mongo server
	 */
	public ConnectionConfig(String connectionString) {
		this.connectionString = connectionString;
	}
		
	/**
	 * Update the db to connect to
	 * 
	 * If this is never set through this method, 'test' will be used
	 * 
	 * @param dbDefault The name the default db should be set to
	 * @return Reference to the {@link ConnectionConfig} caller
	 */
	public ConnectionConfig dbName(String dbName) {
		this.dbName = dbName;
		return this;
	}
	
	
	/**
	 * Build a connection to the database using the configuration
	 * 
	 * @return The {@link DB} Connection object
	 * @throws MongoMigrateConfigurationException when a connection failure occurs
	 * @throws MongoMigrateConnectionException when a connection could not be established
	 */
	public DB getConnection() throws MongoMigrateConfigurationException, MongoMigrateConnectionException {
		try {
			return new MongoClient(new MongoClientURI(connectionString)).getDB(dbName);
		} catch (NumberFormatException|UnknownHostException e) {
			log.error("Could not establish connection with mongo server",e);
			throw new MongoMigrateConnectionException("Could not establish connection with mongo server",e);
		}
	}
}