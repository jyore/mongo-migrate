package com.jyore.mongo.test;

import com.jyore.mongo.migrate.MongoMigrate;
import com.jyore.mongo.migrate.exception.MongoMigrateConfigurationException;
import com.jyore.mongo.migrate.exception.MongoMigrateConnectionException;
import com.jyore.mongo.migrate.exception.MongoMigrateExecuteException;
import com.jyore.resource.exception.ResourceLoadException;

public class MongoMigrateManager {


	public void doMigrate() throws MongoMigrateConnectionException {
		try {
			new MongoMigrate()
				.configure()
					.connection("mongodb://localhost:27017/admin")
					.locations("/mongo/scripts")
				.migrate()
			;
			
			
		} catch (MongoMigrateConfigurationException	| MongoMigrateExecuteException | ResourceLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
