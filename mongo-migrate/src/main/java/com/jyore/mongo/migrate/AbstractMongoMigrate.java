package com.jyore.mongo.migrate;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyore.mongo.migrate.config.ConnectionConfig;
import com.jyore.mongo.migrate.config.DirectoryConfig;
import com.jyore.mongo.migrate.exception.MongoMigrateConfigurationException;
import com.jyore.mongo.migrate.exception.MongoMigrateExecuteException;
import com.jyore.mongo.migrate.util.MongoEvalHelper;
import com.jyore.mongo.migrate.util.MongoMigrateSchemaUtil;
import com.jyore.resource.Resource;
import com.jyore.resource.exception.ResourceLoadException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;




public abstract class AbstractMongoMigrate {
	private static final Logger log = LoggerFactory.getLogger(AbstractMongoMigrate.class); 
	private static final String MONGO_MIGRATE_DB = "mongo_migrate";
	private static final String MONGO_MIGRATE_HISTORY = "migration_history";
	private static final Pattern FILE_PATTERN = Pattern.compile("^[Vv]\\d+__.*\\.(js)$");
	
	protected ConnectionConfig schema;
	protected ConnectionConfig user;
	protected DirectoryConfig locations;
	
	
	public Configuration configure() {
		return new Configuration(this);
	}
	
	public void migrate() throws MongoMigrateConfigurationException,MongoMigrateExecuteException, ResourceLoadException {
		DB schemadb = schema.getConnection();
		DBCollection migrations = schemadb.getCollection(MONGO_MIGRATE_HISTORY);
		
		DB userdb = user.getConnection();
		
		Integer lastVersion = -1;
		DBCursor cur = migrations.find().sort(MongoMigrateSchemaUtil.generateMaxVersionSort()).limit(1);
		if(cur.hasNext()) {
			lastVersion = (Integer)cur.next().get("version");
		}
		
		for(Resource resource : locations.getResources()) {
			String name = resource.getLocation();
			if(name.contains("/")) {
				name = name.substring(name.lastIndexOf("/") + 1);
			}
			
			Matcher m = FILE_PATTERN.matcher(name);
			if(!m.matches()) {
				log.warn("Invalid file name {} -- skipping",name);
				continue;
			}
			
			String[] fn_parts = name.split("__");
			Integer version = Integer.parseInt(fn_parts[0].substring(1)); //remove the "V" and create an int
			String fn = fn_parts[1].split("\\.")[0]; //strip the .js
			
			if(version.compareTo(lastVersion) <= 0) {
				log.info("Migration {} already applied or a later migration already exists -- skipping",name);
				continue;
			}
			
			try {
				MongoEvalHelper.eval(userdb, resource.load("UTF-8"));
				migrations.insert(MongoMigrateSchemaUtil.generateSchemaEntry(version, fn, name, true));
				log.info("Successfully applied migration {}", name);
			} catch(MongoMigrateExecuteException | IOException e) {
				migrations.insert(MongoMigrateSchemaUtil.generateSchemaEntry(version, fn, name, false));
				log.error("Failed to apply migration " + name,e);
				throw new MongoMigrateExecuteException("Failed to apply migration",e);
			}
		}
	}

	
	public static class Configuration {
		private final AbstractMongoMigrate parent;
		
		public Configuration(AbstractMongoMigrate parent) {
			this.parent = parent;
		}
		
		public Connection connection(String host, String port) {
			return new Connection(this,host,port);
		}
		
		public Locations locations(String ... locations) {
			return new Locations(this,locations);
		}
		
		//Parent methods to support easier fluid api
		public void migrate() throws MongoMigrateConfigurationException, MongoMigrateExecuteException, ResourceLoadException {
			parent.migrate();
		}
	}
	
	public static class Connection {
		private final Configuration parent;
		
		public Connection(Configuration parent, String host, String port) {
			this.parent = parent;
			this.parent.parent.schema = new ConnectionConfig(host,port).dbDefault(MONGO_MIGRATE_DB);
			this.parent.parent.user = new ConnectionConfig(host,port);
		}
		
		public Connection dbDefault(String dbDefault) {
			parent.parent.user.dbDefault(dbDefault);
			return this;
		}
		
		public Connection username(String username) {
			parent.parent.schema.username(username);
			parent.parent.user.username(username);
			return this;
		}
		
		public Connection password(String password) {
			parent.parent.schema.password(password);
			parent.parent.user.password(password);
			return this;
		}
		
		//Parent methods to support easier fluid api
		public Locations locations(String ... locations) {
			return this.parent.locations(locations);
		}
		
		public void migrate() throws MongoMigrateConfigurationException, MongoMigrateExecuteException, ResourceLoadException {
			parent.migrate();
		}
	}
	
	public static class Locations {
		private final Configuration parent;
		
		public Locations(Configuration parent,String ...locations) {
			this.parent = parent;
			this.parent.parent.locations = new DirectoryConfig(locations);
		}
		
		//Parent methods to support easier fluid api
		public Connection connection(String host, String port) {
			return this.parent.connection(host, port);
		}
		
		public void migrate() throws MongoMigrateConfigurationException, MongoMigrateExecuteException, ResourceLoadException {
			parent.migrate();
		}
	}
}