package com.jyore.mongo.migrate.exception;

public class MongoMigrateConfigurationException extends Exception {

	private static final long serialVersionUID = 2099964675514198380L;

	public MongoMigrateConfigurationException() {
		super();
	}
	
	public MongoMigrateConfigurationException(String message) {
		super(message);
	}
	
	public MongoMigrateConfigurationException(String message, Throwable exception) {
		super(message,exception);
	}
}
