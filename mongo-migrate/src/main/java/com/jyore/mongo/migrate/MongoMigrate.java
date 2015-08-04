package com.jyore.mongo.migrate;



public class MongoMigrate extends AbstractMongoMigrate {

	public MongoMigrate() {
		this.configure().connection("mongodb://localhost:27017/admin").dbName("test").locations("META-INF/mongo");
	}
}
