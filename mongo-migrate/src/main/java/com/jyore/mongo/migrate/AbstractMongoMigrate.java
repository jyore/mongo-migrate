package com.jyore.mongo.migrate;

import com.jyore.mongo.migrate.config.ConnectionConfig;
import com.jyore.mongo.migrate.exception.MongoMigrateConnectionException;
import com.mongodb.DB;



public abstract class AbstractMongoMigrate {

	public void testBlock() throws MongoMigrateConnectionException {
		DB schema = new ConnectionConfig("localhost", "21017")
			.dbName("mongomigrate_schema")
			.username("admin")
			.password("password")
		.getConnection();
	}
	
	
}
