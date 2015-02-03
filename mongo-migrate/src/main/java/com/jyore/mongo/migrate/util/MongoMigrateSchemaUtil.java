package com.jyore.mongo.migrate.util;

import com.mongodb.BasicDBObject;

public class MongoMigrateSchemaUtil {

	public static BasicDBObject generateSchemaEntry(Integer version, String name, String fullName, Boolean success) {
		return new BasicDBObject("version",version).append("name",name).append("fullName",fullName).append("success",success);
	}

	public static BasicDBObject generateMaxVersionSort() {
		return new BasicDBObject("version",-1);
	}
	
	public static BasicDBObject generateSchemaCheckEntry(Integer version) {
		return new BasicDBObject("version",new BasicDBObject("$gte",version));
	}
}
