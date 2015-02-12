package com.jyore.mongo.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import com.jyore.mongo.migrate.exception.MongoMigrateConnectionException;
import com.jyore.mongo.migrate.exception.MongoMigrateExecuteException;
import com.jyore.mongo.migrate.util.MongoConnectionHelper;
import com.jyore.mongo.migrate.util.MongoEvalHelper;
import com.mongodb.DB;

@Mojo(name = "clean")
public class CleanMongoMigrateMojo extends AbstractMongoMigrateMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			DB db;
			
			if(username != null && password != null) {
				db = MongoConnectionHelper.getConnection(host, port, username, password, dbDefault);
			} else {
				db = MongoConnectionHelper.getConnection(host, port, dbDefault);
			}
			
			
			StringBuilder sb = new StringBuilder("var dbs=db.getMongo().getDBNames();for(var i in dbs){db=db.getMongo().getDB(dbs[i]);");
			if(noCleanDbs != null && noCleanDbs.length > 0) {
				sb.append("if(");
				for(int i=0,l=noCleanDbs.length;i<l;++i) {
					if(i < l-1) {
						sb.append("db != '").append(noCleanDbs[i]).append("' && ");
					} else {
						sb.append("db != '").append(noCleanDbs[i]).append("'");
					}
				}
				sb.append(") {").append("db.dropDatabase();").append("}}");
				
			} else {
				sb.append("db.dropDatabase();").append("}");
			}
			
			MongoEvalHelper.eval(db, sb.toString());
			
		} catch(MongoMigrateConnectionException | MongoMigrateExecuteException e) {
			throw new MojoExecutionException("Unable to execute clean",e);
		}
	}

}
