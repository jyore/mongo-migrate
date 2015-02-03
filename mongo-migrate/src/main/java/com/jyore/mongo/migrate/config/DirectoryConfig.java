package com.jyore.mongo.migrate.config;

import java.util.Collection;

import com.jyore.resource.Resource;
import com.jyore.resource.ResourceLoader;
import com.jyore.resource.exception.ResourceLoadException;


/**
 * Directory Configuration Builder
 * 
 * @author jyore
 * @version 1.0
 */
public class DirectoryConfig {

	protected final String[] locations;
	
	/**
	 * Constructor that adds any number of file locations to scan
	 * 
	 * @param locations The directories to scan for files
	 */
	public DirectoryConfig(String ... locations) {
		this.locations = locations;
	}
	
	/**
	 * Retrieve a collection of resources from the configured locations
	 * 
	 * @return Collection of resources
	 * @throws ResourceLoadException When resources are failed to load
	 */
	public Collection<Resource> getResources() throws ResourceLoadException {
		ResourceLoader rl = new ResourceLoader();
		return rl.load(locations);
	}
	
	
}
