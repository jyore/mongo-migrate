package com.jyore.mongo.migrate.util;

import com.mongodb.BasicDBObject;

public class MongoMigrateSchemaUtil {

	public static BasicDBObject generateSchemaEntry(String version, String name, Boolean success) {
		return new BasicDBObject("version",version).append("name",name).append("success",success);
	}
	

}
