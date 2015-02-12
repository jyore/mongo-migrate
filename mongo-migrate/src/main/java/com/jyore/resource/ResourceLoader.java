package com.jyore.resource;

import java.net.URL;
import java.util.Collection;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyore.resource.exception.ResourceLoadException;
import com.jyore.resource.scan.Scanner;

public class ResourceLoader {
	
	private static final Logger log = LoggerFactory.getLogger(ResourceLoader.class);

	public Collection<Resource> load(String ... locations) throws ResourceLoadException {
		Collection<Resource> resources = new TreeSet<Resource>();
		
		for(String location : locations) {
			log.info("Loading files from location: " + location);
			URL url = Thread.currentThread().getContextClassLoader().getResource(location);
			if(url != null) {
				resources.addAll(Scanner.findResources(location,url));
			} else {
				resources.addAll(Scanner.findResources(location));
			}
		}
		
		return resources;
	}
}
