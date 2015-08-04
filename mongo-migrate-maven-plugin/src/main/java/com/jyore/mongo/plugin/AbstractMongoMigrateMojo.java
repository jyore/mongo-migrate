package com.jyore.mongo.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;


public abstract class AbstractMongoMigrateMojo extends AbstractMojo {

	@Parameter(defaultValue="mongodb://localhost:27017/admin",required=true)
	protected String connectionString;
	
	@Parameter(required=true)
	protected String dbName;
	
	@Parameter(required=true)
	protected String[] locations;
	
	@Parameter(required=false)
	protected String[] noCleanDbs;
}
