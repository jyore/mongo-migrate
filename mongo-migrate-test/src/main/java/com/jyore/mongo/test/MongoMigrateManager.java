package com.jyore.mongo.test;

import com.jyore.mongo.migrate.MongoMigrate;
import com.jyore.mongo.migrate.exception.MongoMigrateConfigurationException;
import com.jyore.mongo.migrate.exception.MongoMigrateExecuteException;
import com.jyore.resource.exception.ResourceLoadException;

public class MongoMigrateManager {


	public void doMigrate() {
		try {
			new MongoMigrate()
				.configure()
					.connection("localhost", "27017")
					.locations("/mongo/scripts")
				.migrate()
			;
			
			
		} catch (MongoMigrateConfigurationException	| MongoMigrateExecuteException | ResourceLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
