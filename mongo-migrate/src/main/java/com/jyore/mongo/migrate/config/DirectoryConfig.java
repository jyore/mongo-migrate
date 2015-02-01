package com.jyore.mongo.migrate.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jyore.mongo.migrate.exception.MongoMigrateConfigurationException;


/**
 * Directory Configuration Builder
 * 
 * @author jyore
 * @version 1.0
 */
public class DirectoryConfig {

	protected final List<String> locations = new ArrayList<String>();
	
	/**
	 * Constructor that adds any number of file locations to scan
	 * 
	 * @param locations The directories to scan for files
	 */
	public DirectoryConfig(String ... locations) {
		for(String location : locations) {
			this.locations.add(location);
		}
	}
	
	/**
	 * Returns a sorted list of all files in the configured directory locations
	 * 
	 * @return The file list
	 * @throws MongoMigrateConfigurationException When non-directories have been configured
	 */
	public List<File> getFiles() throws MongoMigrateConfigurationException {
		List<File> files = new ArrayList<File>();
		
		for(String location : locations) {
			File dir = new File(location);
			
			if(!dir.isDirectory()) {
				throw new MongoMigrateConfigurationException("Location is not a directory: " + dir.getName());
			}
			
			files.addAll(Arrays.asList(dir.listFiles()));
		}
		
		Collections.sort(files, new Comparator<File>() {
			@Override
			public int compare(File f1, File f2) {
				return f1.getName().compareTo(f2.getName());
			}
			
		});
		
		
		return files;
	}
}
