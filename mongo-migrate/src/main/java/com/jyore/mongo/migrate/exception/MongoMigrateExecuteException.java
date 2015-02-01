package com.jyore.mongo.migrate.exception;



public class MongoMigrateExecuteException extends Exception {
	
	private static final long serialVersionUID = -2024721748437958242L;

	public MongoMigrateExecuteException() {
		super();
	}
	
	public MongoMigrateExecuteException(String message) {
		super(message);
	}
	
	public MongoMigrateExecuteException(String message, Throwable exception) {
		super(message,exception);
	}
}
