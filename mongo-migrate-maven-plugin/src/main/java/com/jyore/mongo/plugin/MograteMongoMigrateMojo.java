package com.jyore.mongo.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import com.jyore.mongo.migrate.MongoMigrate;
import com.jyore.mongo.migrate.exception.MongoMigrateConfigurationException;
import com.jyore.mongo.migrate.exception.MongoMigrateExecuteException;
import com.jyore.resource.exception.ResourceLoadException;

@Mojo(name = "migrate")
public class MograteMongoMigrateMojo extends AbstractMongoMigrateMojo {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		
		try {
			new MongoMigrate()
				.configure()
					.connection(host, port)
						.dbDefault(dbDefault)
						.username(username)
						.password(password)
					.locations(locations)
				.migrate()
			;
		} catch (MongoMigrateConfigurationException | MongoMigrateExecuteException | ResourceLoadException e) {
			throw new MojoExecutionException("Failed to migrate: ",e);
		}
	}

}
