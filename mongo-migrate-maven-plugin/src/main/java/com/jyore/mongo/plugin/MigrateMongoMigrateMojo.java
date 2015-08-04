package com.jyore.mongo.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import com.jyore.mongo.migrate.MongoMigrate;
import com.jyore.mongo.migrate.exception.MongoMigrateConfigurationException;
import com.jyore.mongo.migrate.exception.MongoMigrateConnectionException;
import com.jyore.mongo.migrate.exception.MongoMigrateExecuteException;
import com.jyore.resource.exception.ResourceLoadException;

@Mojo(name = "migrate")
public class MigrateMongoMigrateMojo extends AbstractMongoMigrateMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		
		try {
			new MongoMigrate()
				.configure()
					.connection(connectionString)
						.dbName(dbName)
					.locations(locations)
				.migrate()
			;
		} catch (MongoMigrateConfigurationException | MongoMigrateExecuteException | ResourceLoadException | MongoMigrateConnectionException e) {
			throw new MojoExecutionException("Failed to migrate: ",e);
		}
	}

}
