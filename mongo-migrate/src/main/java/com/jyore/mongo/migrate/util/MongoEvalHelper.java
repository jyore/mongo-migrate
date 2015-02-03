package com.jyore.mongo.migrate.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.jyore.mongo.migrate.exception.MongoMigrateExecuteException;
import com.mongodb.CommandResult;
import com.mongodb.DB;

public class MongoEvalHelper {
	
	public static String readScript(File file) throws MongoMigrateExecuteException {
		try {
			InputStream is = new FileInputStream(file);
			
			Reader reader = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(reader);
			StringBuffer buffer = new StringBuffer();
			String line;
			
			while((line = in.readLine()) != null) {
				line.trim();
				buffer.append(line).append("\n");
			}
			reader.close();
			in.close();
			
			return buffer.toString();
			
		} catch (IOException e) {
			throw new MongoMigrateExecuteException("Unable to process script file",e);
		}
	}
	
	public static void eval(DB db, String code) throws MongoMigrateExecuteException {
		CommandResult result = db.doEval("(function() {" + code + "})();", new Object[0]);
		if(!result.ok()) {
			throw new MongoMigrateExecuteException("Could not execute code to evaluate -- " + result.getErrorMessage());
		}
	}
}
