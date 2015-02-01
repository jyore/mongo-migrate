package com.jyore.mongo.migrate;

import java.io.File;
import java.util.List;

import com.jyore.mongo.migrate.config.ConnectionConfig;
import com.jyore.mongo.migrate.config.DirectoryConfig;
import com.jyore.mongo.migrate.exception.MongoMigrateConfigurationException;
import com.jyore.mongo.migrate.exception.MongoMigrateConnectionException;
import com.mongodb.DB;



public abstract class AbstractMongoMigrate {

	public void testBlock() throws MongoMigrateConnectionException, MongoMigrateConfigurationException {
		DB schema = new ConnectionConfig("localhost", "21017")
			.dbDefault("mongomigrate_schema")
			.username("admin")
			.password("password")
		.getConnection();
		
		List<File> files = new DirectoryConfig("location","location").getFiles();
	}
	
	
}
