package com.jyore.mongo.migrate.exception;



public class MongoMigrateConnectionException extends Exception {

	private static final long serialVersionUID = 1080829123462124035L;

	
	public MongoMigrateConnectionException() {
		super();
	}
	
	public MongoMigrateConnectionException(String message) {
		super(message);
	}
	
	public MongoMigrateConnectionException(String message, Throwable exception) {
		super(message,exception);
	}
}
