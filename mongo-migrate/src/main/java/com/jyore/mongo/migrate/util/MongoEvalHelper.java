package com.jyore.mongo.migrate.util;

import com.jyore.mongo.migrate.exception.MongoMigrateExecuteException;
import com.mongodb.CommandResult;
import com.mongodb.DB;

public class MongoEvalHelper {
	
	public static void eval(DB db, String code) throws MongoMigrateExecuteException {
		CommandResult result = db.doEval("(function() {" + code + "})();", new Object[0]);
		if(!result.ok()) {
			throw new MongoMigrateExecuteException("Could not execute code to evaluate -- " + result.getErrorMessage());
		}
	}
}
