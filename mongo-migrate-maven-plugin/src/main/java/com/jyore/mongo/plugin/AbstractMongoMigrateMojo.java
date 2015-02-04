package com.jyore.mongo.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;


public abstract class AbstractMongoMigrateMojo extends AbstractMojo {

	@Parameter(defaultValue="localhost",required=false)
	protected String host;
	
	@Parameter(defaultValue="27017",required=false)
	protected String port;
	
	@Parameter(defaultValue="test",required=false)
	protected String dbDefault;
	
	@Parameter(required=false)
	protected String username;
	
	@Parameter(required=false)
	protected String password;
	
	@Parameter(required=true)
	protected String[] locations;
}
