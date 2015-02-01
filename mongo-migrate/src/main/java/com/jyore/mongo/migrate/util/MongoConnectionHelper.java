package com.jyore.mongo.migrate.util;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyore.mongo.migrate.exception.MongoMigrateConnectionException;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;



public class MongoConnectionHelper {

	private static final Logger log = LoggerFactory.getLogger(MongoConnectionHelper.class); 
	
	public static DB getConnection(String host, String port, String dbName) throws MongoMigrateConnectionException {
		try {
			return new MongoClient(host,Integer.parseInt(port)).getDB(dbName);
		} catch (NumberFormatException|UnknownHostException e) {
			log.error("Could not establish connection with mongo server",e);
			throw new MongoMigrateConnectionException("Could not establish connection with mongo server",e);
		}
	}
	
	public static DB getConnection(String host, String port, String username, String password, String dbName) throws MongoMigrateConnectionException {
		try {
			ServerAddress addr = new ServerAddress(host,Integer.parseInt(port));
			MongoCredential cred = MongoCredential.createMongoCRCredential(username, dbName, password.toCharArray());
			return new MongoClient(addr,Arrays.asList(cred)).getDB(dbName);
		} catch (NumberFormatException|UnknownHostException e) {
			log.error("Could not establish connection with mongo server",e);
			throw new MongoMigrateConnectionException("Could not establish connection with mongo server",e);
		}
	}
}
