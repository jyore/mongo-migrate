package com.jyore.mongo.migrate;



public class MongoMigrate extends AbstractMongoMigrate {

	public MongoMigrate() {
		this.configure().connection("localhost", "27017").dbDefault("test").locations("META-INF/mongo");
	}
}
